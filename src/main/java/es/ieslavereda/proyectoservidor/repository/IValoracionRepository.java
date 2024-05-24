package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import es.ieslavereda.proyectoservidor.repository.model.Valoracion;

import java.sql.SQLException;
/**
 * @author: Alejandro Jorge, Alejandro Paul, Marcos Martínez
 * version: 2024 v1
 */
public interface IValoracionRepository {
    /**
     * Interfaz que define el método que debe implementar el repositorio de valoracion
     */
    Valoracion addValoracion(Valoracion valoracion) throws SQLException;
    Valoracion getValoracion(int idValoracion) throws SQLException;
}
