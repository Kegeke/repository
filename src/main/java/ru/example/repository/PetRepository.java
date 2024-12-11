package ru.example.repository;

import ru.example.db.DataBaseConnections;
import ru.example.model.Owner;
import ru.example.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {
    public void save(Pet pet) throws SQLException {
        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        String sql = "INSERT INTO pets (id, name, owner) VALUES (?, ?, ?)";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, String.valueOf(pet.getId()));
            statement.setString(2, pet.getName());
            statement.setString(3, String.valueOf(pet.getOwner()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Не удалось создать пользователя");
            }

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }

    public Pet findById(Integer id) throws SQLException {
        Pet pet = null;

        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        String sql = "SELECT id, name, owner FROM pets WHERE id = ?";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (pet == null) {
                    pet = mapPet(resultSet);
                }
            }

            if (pet == null) {
                throw new SQLException("Не удалось найти пользователя с id " + id);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return pet;
    }

    public List<Pet> findAll() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        DataBaseConnections dataBaseConnections = new DataBaseConnections();

        String sql = "SELECT * FROM pets";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                pets.add(mapPet(resultSet));
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return pets;
    }

    public void update(Pet pet) throws SQLException {
        DataBaseConnections dataBaseConnections = new DataBaseConnections();

        String sql = "UPDATE pets SET name = ?, pet = ?, WHERE id = ?";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pet.getName());
            statement.setString(2, String.valueOf(pet.getOwner()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Пользователя с id " + pet.getId() + " не существует");
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(Integer id) throws SQLException {
        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        String sql = "DELETE FROM pets WHERE id = ?";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Пользователя с id " + id + " не существует");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private Pet mapPet(ResultSet resultSet) throws SQLException {
        return new Pet.PetBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setOwner(resultSet.getObject("owner", Owner.class))
                .build();
    }
}
