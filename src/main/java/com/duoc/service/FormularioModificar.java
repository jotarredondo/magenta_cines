package com.duoc.service;

import com.duoc.dao.PeliculaDao;
import com.duoc.model.Pelicula;

import javax.swing.*;
import java.awt.*;

public class FormularioModificar extends JFrame {
    private JTextField txtId, txtTitulo, txtDirector, txtAnio, txtDuracion;
    private JComboBox<String> cmbGenero;
    private JButton btnBuscar, btnGuardar, btnLimpiar;

    private PeliculaDao dao = new PeliculaDao();

    public FormularioModificar() {
        setTitle("Modificar Película");
        setSize(400, 350);
        setLayout(new GridLayout(8, 2));

        txtId = new JTextField();
        txtTitulo = new JTextField();
        txtDirector = new JTextField();
        txtAnio = new JTextField();
        txtDuracion = new JTextField();
        cmbGenero = new JComboBox<>(new String[]{"Acción", "Comedia", "Drama", "Terror", "Animación", "Ciencia Ficción"});

        btnBuscar = new JButton("Buscar");
        btnGuardar = new JButton("Guardar cambios");
        btnLimpiar = new JButton("Limpiar");

        add(new JLabel("ID:")); add(txtId);
        add(btnBuscar); add(new JLabel(""));
        add(new JLabel("Título:")); add(txtTitulo);
        add(new JLabel("Director:")); add(txtDirector);
        add(new JLabel("Año:")); add(txtAnio);
        add(new JLabel("Duración:")); add(txtDuracion);
        add(new JLabel("Género:")); add(cmbGenero);
        add(btnGuardar); add(btnLimpiar);

        btnBuscar.addActionListener(e -> buscar());
        btnGuardar.addActionListener(e -> guardar());
        btnLimpiar.addActionListener(e -> limpiar());
    }

    private void buscar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Pelicula p = dao.buscarPorId(id);
            if (p != null) {
                txtTitulo.setText(p.getTitulo());
                txtDirector.setText(p.getDirector());
                txtAnio.setText(String.valueOf(p.getAnio()));
                txtDuracion.setText(String.valueOf(p.getDuracion()));
                cmbGenero.setSelectedItem(p.getGenero());
            } else {
                JOptionPane.showMessageDialog(this, "Película no encontrada");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser numérico");
        }
    }

    private void guardar() {
        if (txtId.getText().isEmpty() || txtTitulo.getText().isEmpty() ||
                txtDirector.getText().isEmpty() || txtAnio.getText().isEmpty() ||
                txtDuracion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }
        try {
            Pelicula p = new Pelicula(
                    Integer.parseInt(txtId.getText()),
                    txtTitulo.getText(),
                    txtDirector.getText(),
                    Integer.parseInt(txtAnio.getText()),
                    Integer.parseInt(txtDuracion.getText()),
                    cmbGenero.getSelectedItem().toString()
            );
            dao.actualizarPelicula(p);
            JOptionPane.showMessageDialog(this, "Película modificada correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtTitulo.setText("");
        txtDirector.setText("");
        txtAnio.setText("");
        txtDuracion.setText("");
        cmbGenero.setSelectedIndex(0);
    }
}

