package com.duoc;

import com.duoc.service.FormularioAgregar;
import com.duoc.service.FormularioEliminar;
import com.duoc.service.FormularioModificar;

import javax.swing.*;


public class Main extends JFrame {
    public Main() {
        setTitle("Cine Magenta");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuPeliculas = new JMenu("Películas");

        JMenuItem itemAgregar = new JMenuItem("Agregar película");
        itemAgregar.addActionListener(e -> new FormularioAgregar().setVisible(true));

        JMenuItem itemModificar = new JMenuItem("Modificar película");
        itemModificar.addActionListener(e -> new FormularioModificar().setVisible(true));

        JMenuItem itemEliminar = new JMenuItem("Eliminar película");
        itemEliminar.addActionListener(e -> new FormularioEliminar().setVisible(true));

        menuPeliculas.add(itemModificar);
        menuPeliculas.add(itemEliminar);

        menuPeliculas.add(itemAgregar);
        menuBar.add(menuPeliculas);
        setJMenuBar(menuBar);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}