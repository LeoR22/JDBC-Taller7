package com.company.Taller7.Taller7;
import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/OBJETOSVIRTUALES";
    static final String USER = "root";
    static final String PASS = "12345";

    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarNuevaFacultad(String nombreFacultad, int codigoFacultad) {
        try {
            String sql = "INSERT INTO facultades VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombreFacultad);
                preparedStatement.setInt(2, codigoFacultad);
                preparedStatement.executeUpdate();
            }
            System.out.println("Facultad insertada exitosamente.");
            JOptionPane.showMessageDialog(null, "Facultad insertada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertarNuevoDepartamento(String nombreDepartamento, int codigoDepartamento, int codigoFacultad) {
        try {
            String sql = "INSERT INTO departamentos VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombreDepartamento);
                preparedStatement.setInt(2, codigoDepartamento);
                preparedStatement.setInt(3, codigoFacultad);
                preparedStatement.executeUpdate();
            }
            System.out.println("Departamento insertado exitosamente.");
            JOptionPane.showMessageDialog(null, "Departamento insertado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertarNuevoProfesor(String nombreProfesor, String emailProfesor, int cedulaProfesor, int codigoDepartamento) {
        try {
            String sql = "INSERT INTO profesores VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombreProfesor);
                preparedStatement.setString(2, emailProfesor);
                preparedStatement.setInt(3, cedulaProfesor);
                preparedStatement.setInt(4, codigoDepartamento);
                preparedStatement.executeUpdate();
            }
            System.out.println("Profesor insertado exitosamente.");
            JOptionPane.showMessageDialog(null, "Profesor insertado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void modificarProfesor(int cedulaProfesor, String nuevoNombre, String nuevoEmail, int nuevoCodigoDepartamento) {
        try {
            String sql = "UPDATE profesores SET nombre=?, email=?, cod_dpto=? WHERE cedula=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nuevoNombre);
                preparedStatement.setString(2, nuevoEmail);
                preparedStatement.setInt(3, nuevoCodigoDepartamento);
                preparedStatement.setInt(4, cedulaProfesor);
                preparedStatement.executeUpdate();
            }
            System.out.println("Profesor modificado exitosamente.");
            JOptionPane.showMessageDialog(null, "Profesor modificado exitosamente..", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cédula inválida. Por favor, ingrese una cédula válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void modificarNombreTema(int codigoTema, String nuevoNombre) {
        try {
            String sql = "UPDATE temas SET nombre=? WHERE codigo=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nuevoNombre);
                preparedStatement.setInt(2, codigoTema);
                preparedStatement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Nombre del tema modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar el nombre del tema. Consulta la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public List<String> consultarTemas() {
        List<String> temas = new ArrayList<>();

        try {
            String sql = "SELECT nombre FROM temas";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    temas.add(resultSet.getString("nombre"));
                }
            }

            if (temas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay temas disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Temas consultados exitosamente:\n" + String.join("\n", temas), "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al consultar temas. Consulta la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return temas;
    }
    public List<String> consultarDepartamentosPorFacultad(int codigoFacultad) {
        List<String> departamentos = new ArrayList<>();

        try {
            String sql = "SELECT nombre FROM departamentos WHERE cod_facultad=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, codigoFacultad);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        departamentos.add(resultSet.getString("nombre"));
                    }
                }
            }

            if (departamentos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay departamentos disponibles para la facultad seleccionada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Departamentos consultados exitosamente:\n" + String.join("\n", departamentos), "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al consultar departamentos. Consulta la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return departamentos;
    }
    public List<String> consultarProfesoresPorFacultad(int codigoFacultad) {
        List<String> profesores = new ArrayList<>();

        try {
            String sql = "SELECT p.nombre AS nombre_profesor, p.email, p.cedula AS codigo_profesor, " +
                    "d.nombre AS nombre_departamento, f.nombre AS nombre_facultad " +
                    "FROM profesores p " +
                    "JOIN departamentos d ON p.cod_dpto = d.codigo " +
                    "JOIN facultades f ON d.cod_facultad = f.codigo " +
                    "WHERE f.codigo = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, codigoFacultad);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String nombreProfesor = resultSet.getString("nombre_profesor");
                        String emailProfesor = resultSet.getString("email");
                        int codigoProfesor = resultSet.getInt("codigo_profesor");
                        String nombreDepartamento = resultSet.getString("nombre_departamento");
                        String nombreFacultad = resultSet.getString("nombre_facultad");

                        profesores.add(String.format("Nombre: %s, Email: %s, Código: %d, Departamento: %s, Facultad: %s",
                                nombreProfesor, emailProfesor, codigoProfesor, nombreDepartamento, nombreFacultad));
                    }
                }
            }

            if (profesores.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay profesores disponibles para la facultad seleccionada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Profesores consultados exitosamente:\n" + String.join("\n", profesores), "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al consultar profesores. Consulta la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return profesores;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
