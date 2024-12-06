package ru.example;

import ru.example.db.DataBaseConnections;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        Statement statement = dataBaseConnections.connect().createStatement();
    }
}
