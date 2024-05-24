package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.DataSource;
import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import es.ieslavereda.proyectoservidor.repository.model.Valoracion;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author: Alejandro Jorge, Alejandro Paul, Marcos Martínez
 * version: 2024 v1
 */
@Repository
public class ValoracionRepository implements IValoracionRepository {
    /**
     * recoge la valoración que hace el cliente de un contenido y lo pasa a la BDD para calcular la media,
     * luego recoge la puntuación media y la muestra
     * @param valoracion
     * @return valoración introducida por el usuario
     * @throws SQLException
     */

    @Override
    public Valoracion addValoracion(Valoracion valoracion) throws SQLException {
        String query = "{call votar(?,?,?)}";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1,valoracion.getIdContenido());
            ps.setString(2,valoracion.getDniCliente());
            ps.setInt(3,valoracion.getPuntuacion());


            ps.executeUpdate();
        }
        return valoracion;
    }

    /**
     * obtiene la valoración media de un contenido
     * @param idValoracion
     * @return
     * @throws SQLException
     */
    @Override
    public Valoracion getValoracion(int idValoracion) throws SQLException {
        Valoracion valoracion = null;
        String query = "SELECT * FROM valoracion WHERE idValoracion = ?";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1,idValoracion);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                valoracion = Valoracion.builder().idValoracion(rs.getInt(1))
                        .idContenido(rs.getInt(2))
                        .dniCliente(rs.getString(3))
                        .idValoracion(rs.getInt(4))
                        .build();
        }
        return valoracion;
    }


}
