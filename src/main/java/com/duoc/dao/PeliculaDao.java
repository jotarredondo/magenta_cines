package com.duoc.dao;

import com.duoc.connection.Conexion;
import com.duoc.model.Pelicula;

import java.sql.*;

public class PeliculaDao {
    public void agregarPelicula(Pelicula p) {
        String sql = "INSERT INTO Cartelera (titulo, director, anio, duracion, genero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getDirector());
            stmt.setInt(3, p.getAnio());
            stmt.setInt(4, p.getDuracion());
            stmt.setString(5, p.getGenero());

            stmt.executeUpdate();
            System.out.println("Película agregada correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
