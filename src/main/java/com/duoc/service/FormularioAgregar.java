package com.duoc.service;

import com.duoc.connection.Conexion;
import com.duoc.dao.PeliculaDao;
import com.duoc.model.Pelicula;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FormularioAgregar extends JFrame {
        private JTextField txtTitulo, txtDirector, txtAnio, txtDuracion;
        private JComboBox<String> cmbGenero;

        public FormularioAgregar() {
            setTitle("Agregar Película");
            setSize(400, 300);
            setLayout(new GridLayout(7, 2));

            add(new JLabel("Título:"));
            txtTitulo = new JTextField();
            add(txtTitulo);

            add(new JLabel("Director:"));
            txtDirector = new JTextField();
            add(txtDirector);

            add(new JLabel("Año:"));
            txtAnio = new JTextField();
            add(txtAnio);

            add(new JLabel("Duración (min):"));
            txtDuracion = new JTextField();
            add(txtDuracion);

            add(new JLabel("Género:"));
            cmbGenero = new JComboBox<>(new String[]{"Acción", "Comedia", "Drama", "Terror", "Animación", "Ciencia Ficción"});
            add(cmbGenero);

            JButton btnAgregar = new JButton("Agregar");
            btnAgregar.addActionListener(e -> agregarPelicula());
            add(btnAgregar);

            JButton btnLimpiar = new JButton("Limpiar");
            btnLimpiar.addActionListener(e -> limpiarCampos());
            add(btnLimpiar);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

    private void agregarPelicula() {
        if (txtTitulo.getText().isEmpty() || txtDirector.getText().isEmpty() ||
                txtAnio.getText().isEmpty() || txtDuracion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        try {
            int anio = Integer.parseInt(txtAnio.getText());
            int duracion = Integer.parseInt(txtDuracion.getText());

            Pelicula p = new Pelicula(
                    txtTitulo.getText(),
                    txtDirector.getText(),
                    anio,
                    duracion,
                    cmbGenero.getSelectedItem().toString()
            );

            new PeliculaDao().agregarPelicula(p);
            JOptionPane.showMessageDialog(this, "Película agregada correctamente");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Año y duración deben ser numéricos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

        private void limpiarCampos() {
            txtTitulo.setText("");
            txtDirector.setText("");
            txtAnio.setText("");
            txtDuracion.setText("");
            cmbGenero.setSelectedIndex(0);
        }
}




