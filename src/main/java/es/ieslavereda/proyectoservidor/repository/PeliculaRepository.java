package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.DataSource;
import es.ieslavereda.proyectoservidor.repository.model.Pelicula;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculaRepository implements IPeliculaRepository {

    @Override
    public Pelicula updatePelicula(Pelicula pelicula) throws SQLException {

        String query = "UPDATE contenido SET valoracion_media = ? WHERE idcontenido = ?";
        Pelicula pelicula1 = getPelicula(pelicula.getId());

        if (pelicula1 == null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            /*
            ps.setString(1,pelicula1.getTipo());
            ps.setString(2,pelicula1.getTitulo());
            ps.setString(3,pelicula1.getIdioma());
            ps.setString(4,pelicula1.getGenero());
            ps.setString(5,pelicula1.getDescripcion());
            ps.setString(6,pelicula1.getDirector());
            ps.setString(7,pelicula1.getActores());
            ps.setInt(8,pelicula1.getDuracion());

             */
            ps.setDouble(1,pelicula1.getValoracion_media());
            ps.setInt(2,pelicula1.getId());

            ps.executeUpdate();
        }

        return pelicula;
    }



    @Override
    public Pelicula deletePelicula(int id) throws SQLException {
        Pelicula pelicula = getPelicula(id);
        String query = "DELETE FROM contenido WHERE EXISTS (SELECT idcontenido FROM pelicula WHERE pelicula.idcontenido = contenido.idcontenido) AND idcontenido = ?";

        if (pelicula == null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return pelicula;
    }




    @Override
    public List<Pelicula> getAllPeliculas() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String query = "SELECT c.idcontenido, c.TIPO, c.TITULO, c.IDIOMA, c.GENERO, c.DESCRIPCION, c.DIRECTOR, c.ACTORES, c.DURACION, c.VALORACION_MEDIA, c.portada, t.PRECIOVISIONADO FROM contenido c JOIN pelicula p ON c.idcontenido = p.idcontenido JOIN tarifa t ON p.CODIGO_TARIFA = t.COD_TARIFA WHERE c.TIPO = 'pelicula'";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while (rs.next()) {
                peliculas.add(Pelicula.builder().id(rs.getInt(1))
                                .tipo(rs.getString(2))
                                .titulo(rs.getString(3))
                                .idioma(rs.getString(4))
                                .genero(rs.getString(5))

                                .descripcion(rs.getString(6))
                                .director(rs.getString(7))
                                .actores(rs.getString(8))
                                .duracion(rs.getInt(9))
                                .valoracion_media(rs.getDouble(10))
                                .portada(rs.getString(11))
                                .preciovisionado(rs.getDouble(12))
                                .build());
            }
        }
        return peliculas;
    }


    @Override
    public Pelicula getPelicula(int id) throws SQLException {
        Pelicula pelicula = null;
        String query = "SELECT idcontenido, TIPO, TITULO, IDIOMA, GENERO, DESCRIPCION, DIRECTOR, ACTORES, DURACION, VALORACION_MEDIA  FROM contenido WHERE EXISTS(SELECT idcontenido from pelicula) AND idcontenido = ?";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                pelicula = Pelicula.builder().id(rs.getInt(1))
                        .tipo(rs.getString(2))
                        .titulo(rs.getString(3))
                        .idioma(rs.getString(4))
                        .genero(rs.getString(5))
                        .descripcion(rs.getString(6))
                        .director(rs.getString(7))
                        .actores(rs.getString(8))
                        .duracion(rs.getInt(9))
                        .valoracion_media(rs.getDouble(10))
                        .build();
        }
        return pelicula;
    }

    @Override
    public Pelicula addPelicula(Pelicula pelicula) throws SQLException {
        String query = "INSERT INTO contenido (idcontenido, TIPO, TITULO, IDIOMA, GENERO, DESCRIPCION, DIRECTOR, ACTORES, DURACION, VALORACION_MEDIA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Pelicula pelicula1 = getPelicula(pelicula.getId());

        if (pelicula1 != null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1,pelicula1.getId());
            ps.setString(2,pelicula1.getTipo());
            ps.setString(3,pelicula1.getTitulo());
            ps.setString(4,pelicula1.getIdioma());
            ps.setString(5,pelicula1.getGenero());
            ps.setString(6,pelicula1.getDescripcion());
            ps.setString(7,pelicula1.getDirector());
            ps.setString(8,pelicula1.getActores());
            ps.setInt(9,pelicula1.getDuracion());
            ps.setDouble(10,pelicula1.getValoracion_media());

            ps.executeUpdate();
        }
        return pelicula1;
    }



}


