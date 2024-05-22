package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.DataSource;
import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import es.ieslavereda.proyectoservidor.repository.model.Valoracion;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ValoracionRepository implements IValoracionRepository {


    @Override
    public Valoracion addValoracion(Valoracion valoracion) throws SQLException {
        String query = "INSERT INTO valoracion( idValoracion,idContenido, dniCliente, puntuacion) VALUES(ID_VALORACION.NEXTVALUE,?,?,?)";
        Valoracion valoracion1 = getValoracion(valoracion.getIdValoracion());

        if (valoracion1 != null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1,valoracion.getIdContenido());
            ps.setString(2,valoracion.getDniCliente());
            ps.setInt(3,valoracion.getPuntuacion());


            ps.executeUpdate();
        }
        return valoracion;
    }

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
