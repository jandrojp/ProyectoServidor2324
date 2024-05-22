package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import es.ieslavereda.proyectoservidor.repository.model.Valoracion;

import java.sql.SQLException;

public interface IValoracionRepository {

    Valoracion addValoracion(Valoracion valoracion) throws SQLException;
    Valoracion getValoracion(int idValoracion) throws SQLException;
}
