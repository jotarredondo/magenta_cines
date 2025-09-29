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

    public Pelicula buscarPorId(int id) {
        String sql = "SELECT * FROM Cartelera WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getInt("anio"),
                        rs.getInt("duracion"),
                        rs.getString("genero")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void actualizarPelicula(Pelicula p) {
        String sql = "UPDATE Cartelera SET titulo=?, director=?, anio=?, duracion=?, genero=? WHERE id=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getDirector());
            stmt.setInt(3, p.getAnio());
            stmt.setInt(4, p.getDuracion());
            stmt.setString(5, p.getGenero());
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPelicula(int id) {
        String sql = "DELETE FROM Cartelera WHERE id=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
