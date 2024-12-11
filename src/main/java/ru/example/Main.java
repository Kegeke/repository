package ru.example;

import ru.example.db.DataBaseConnections;
import ru.example.model.Owner;
import ru.example.repository.OwnerRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        Statement statement = dataBaseConnections.connect().createStatement();

        OwnerRepository ownerRepository = new OwnerRepository();

        Owner owner = ownerRepository.findById(1);
    }
}
