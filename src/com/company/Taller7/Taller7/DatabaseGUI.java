package com.company.Taller7.Taller7;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatabaseGUI extends JPanel {
    private JButton insertFacultadButton;
    private DatabaseManager databaseManager;
    private JButton insertDepartamentoButton;
    private JButton insertProfesorButton;
    private JButton modificarProfesorButton;
    private JButton modificarNombreTemaButton;
    private JButton consultarTemasButton;
    private JButton consultarDepartamentosButton;
    private JButton consultarProfesoresButton;
    public DatabaseGUI() {
        databaseManager = new DatabaseManager();
        initUI();
    }

    private void initUI() {
        insertFacultadButton = new JButton("Insertar Nueva Facultad");
        insertDepartamentoButton = new JButton("Insertar Nuevo Departamento");
        insertProfesorButton = new JButton("Insertar Nuevo Profesor");
        modificarProfesorButton = new JButton("Modificar Profesor Existente");
        modificarNombreTemaButton = new JButton("Modificar Nombre del Tema");
        consultarTemasButton = new JButton("Consultar Temas");
        consultarProfesoresButton = new JButton("Consultar Profesores por Facultad");
        databaseManager = new DatabaseManager();

        insertFacultadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para insertar nueva facultad utilizando databaseManager
                String nombreFacultad = JOptionPane.showInputDialog("Ingrese el nombre de la nueva facultad:");
                int codigoFacultad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la nueva facultad:"));

                databaseManager.insertarNuevaFacultad(nombreFacultad, codigoFacultad);
            }
        });

        add(insertFacultadButton);

        insertDepartamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para insertar nuevo departamento utilizando databaseManager
                String nombreDepartamento = JOptionPane.showInputDialog("Ingrese el nombre del nuevo departamento:");
                int codigoDepartamento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del nuevo departamento:"));
                int codigoFacultad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la facultad a la que pertenece el departamento:"));

                databaseManager.insertarNuevoDepartamento(nombreDepartamento, codigoDepartamento, codigoFacultad);
            }
        });
        add(insertDepartamentoButton);
        insertProfesorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para insertar nuevo profesor utilizando databaseManager
                String nombreProfesor = JOptionPane.showInputDialog("Ingrese el nombre del nuevo profesor:");
                String emailProfesor = JOptionPane.showInputDialog("Ingrese el email del nuevo profesor:");
                int cedulaProfesor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cédula del nuevo profesor:"));
                int codigoDepartamento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del departamento al que pertenece el profesor:"));

                databaseManager.insertarNuevoProfesor(nombreProfesor, emailProfesor, cedulaProfesor, codigoDepartamento);
            }
        });

        add(insertProfesorButton);
        modificarProfesorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para modificar un profesor existente utilizando databaseManager
                    int cedulaProfesor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cédula del profesor que desea modificar:"));
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del profesor:");
                    String nuevoEmail = JOptionPane.showInputDialog("Ingrese el nuevo email del profesor:");
                    int nuevoCodigoDepartamento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo código del departamento al que pertenece el profesor:"));

                    databaseManager.modificarProfesor(cedulaProfesor, nuevoNombre, nuevoEmail, nuevoCodigoDepartamento);
                }
            });

            add(modificarProfesorButton);
        modificarNombreTemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para modificar el nombre de un tema utilizando databaseManager
                int codigoTema = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del tema que desea modificar:"));
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del tema:");

                databaseManager.modificarNombreTema(codigoTema, nuevoNombre);
            }
        });

        add(modificarNombreTemaButton);
        consultarTemasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para consultar temas utilizando databaseManager
                databaseManager.consultarTemas();
            }
        });

        add(consultarTemasButton);

        consultarDepartamentosButton = new JButton("Consultar Departamentos por Codigo de Facultad");

            consultarDepartamentosButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para consultar departamentos por facultad utilizando databaseManager
                    int codigoFacultad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la facultad:"));
                    databaseManager.consultarDepartamentosPorFacultad(codigoFacultad);
                }});
            add(consultarDepartamentosButton);
        consultarProfesoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para consultar profesores por facultad utilizando databaseManager
                int codigoFacultad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la facultad:"));
                databaseManager.consultarProfesoresPorFacultad(codigoFacultad);
            }
        });

        add(consultarProfesoresButton);
    }
}
