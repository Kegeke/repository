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

public class OwnerRepository {
    public void save(Owner owner) throws SQLException {
        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        String sql = "INSERT INTO owners (id, name, pet) VALUES (?, ?, ?)";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, String.valueOf(owner.getId()));
            statement.setString(2, owner.getName());
            statement.setString(3, String.valueOf(owner.getPet()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Не удалось создать пользователя");
            }

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public Owner findById(Integer id) throws SQLException {
        Owner owner = null;

        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        String sql = "SELECT id, name, pet FROM owners WHERE id = ?";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (owner == null) {
                    owner = mapOwner(resultSet);
                }
            }

            if (owner == null) {
                throw new SQLException("Не удалось найти пользователя с id " + id);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return owner;
    }

    public List<Owner> findAll() throws SQLException {
        List<Owner> owner = new ArrayList<>();
        DataBaseConnections dataBaseConnections = new DataBaseConnections();

        String sql = "SELECT * FROM owners";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                owner.add(mapOwner(resultSet));
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return owner;
    }

    public void update(Owner owner) throws SQLException {
        DataBaseConnections dataBaseConnections = new DataBaseConnections();

        String sql = "UPDATE owners SET name = ?, pet = ?, WHERE id = ?";

        try (Connection connection = dataBaseConnections.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, owner.getName());
            statement.setString(2, String.valueOf(owner.getPet()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Пользователя с id " + owner.getId() + " не существует");
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(Integer id) throws SQLException {
        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        String sql = "DELETE FROM users WHERE id = ?";

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

    private Owner mapOwner(ResultSet resultSet) throws SQLException {
        return new Owner.OwnerBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setPet(resultSet.getObject("pet", Pet.class))
                .build();
    }
}
