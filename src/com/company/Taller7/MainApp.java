package com.company.Taller7;
import com.company.Taller7.Taller7.DatabaseGUI;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Base de Datos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(new DatabaseGUI());
            frame.setVisible(true);
        });
    }
}
