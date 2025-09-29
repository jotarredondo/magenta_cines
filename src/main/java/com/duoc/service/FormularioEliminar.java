package com.duoc.service;

import com.duoc.dao.PeliculaDao;
import com.duoc.model.Pelicula;

import javax.swing.*;
import java.awt.*;

public class FormularioEliminar extends JFrame {
    private JTextField txtId;
    private JButton btnEliminar, btnLimpiar;

    private PeliculaDao dao = new PeliculaDao();

    public FormularioEliminar() {
        setTitle("Eliminar Película");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        txtId = new JTextField();
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        add(new JLabel("ID:")); add(txtId);
        add(btnEliminar); add(btnLimpiar);

        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> txtId.setText(""));
    }

    private void eliminar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Pelicula p = dao.buscarPorId(id);
            if (p != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Eliminar la película '" + p.getTitulo() + "'?",
                        "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dao.eliminarPelicula(id);
                    JOptionPane.showMessageDialog(this, "Película eliminada");
                }
            } else {
                JOptionPane.showMessageDialog(this, "La película no existe");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser numérico");
        }
    }
}

