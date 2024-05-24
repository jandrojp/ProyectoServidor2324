package es.ieslavereda.proyectoservidor.service;

import es.ieslavereda.proyectoservidor.repository.ValoracionRepository;
import es.ieslavereda.proyectoservidor.repository.model.DataSource;
import es.ieslavereda.proyectoservidor.repository.model.Valoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Se añade la valoración al repositorio
 * @author: Alejandro Jorge, Alejandro Paul, Marcos Martínez
 * version: 2024 v1
 */
@Service
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;


    public Valoracion addValoracion(Valoracion valoracion) throws SQLException {
        return valoracionRepository.addValoracion(valoracion);
    }
}
