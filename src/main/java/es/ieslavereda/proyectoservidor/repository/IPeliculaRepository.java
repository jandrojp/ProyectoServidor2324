package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.Pelicula;
import java.sql.SQLException;
import java.util.List;
/**
 * @author: Alejandro Jorge, Alejandro Paul, Marcos Martínez
 * version: 2024 v1
 */
public interface IPeliculaRepository {
    /**
     * Interfaces que definen qué métodos se deben implementar en los repositorios (CRUD) de películas
     */
    Pelicula getPelicula(int id) throws SQLException;
    Pelicula addPelicula(Pelicula pelicula) throws SQLException;
    Pelicula updatePelicula(Pelicula pelicula) throws SQLException;
    Pelicula deletePelicula(int id) throws SQLException;
    List<Pelicula> getAllPeliculas() throws SQLException;
}
