package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.DataSource;
import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * @author: Alejandro Jorge, Alejandro Paul, Marcos Martínez
 * version: 2024 v1
 */
@Repository
public class UsuarioRepository implements IUsuarioRepository {
    /**
     * implementa el CRUD definido en la interfaz
     * actualiza usuario
     * @param usuario usuario a actualizar
     * @return el usuario actualizado con los datos del usaurio que se ha pasado por parámetro
     * @throws SQLException
     */
    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE cliente SET usuario = ?, contraseña = ?, nombre = ?, apellidos = ?, email = ?, domicilio = ?, codigo_postal = ?, tarjeta_credito = ? where dniCliente = ?";
        Usuario usuario1 = getUsuario(usuario.getDni());

        if (usuario1 == null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1,usuario.getUsuario());
            ps.setString(2,usuario.getContrasenya());
            ps.setString(3,usuario.getNombre());
            ps.setString(4,usuario.getApellidos());
            ps.setString(5,usuario.getEmail());
            ps.setString(6,usuario.getDomicilio());
            ps.setString(7,usuario.getCodigo_postal());
            ps.setString(8,usuario.getTarjeta_credito());
            ps.setString(9,usuario.getDni());

            ps.executeUpdate();
        }

        return usuario;
    }

    /**
     * borra el usaurio a partir del dni que se pasa por parámetro. El dni es la pk del usuario
     * @param dni
     * @return el usuario que se ha borrado
     * @throws SQLException
     */

    @Override
    public Usuario deleteUsuario(String dni) throws SQLException {
        Usuario usuario = getUsuario(dni);
        //Usuario usuario = new Usuario("1A", null, null, null, null, null, null, null, null, null);
        String query = "DELETE FROM cliente WHERE dniCliente = ?";

        if (usuario == null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, dni);
            ps.executeUpdate();
        }
        return usuario;
    }

    /*
    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "{ call obtener_usuarios() }";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(query)){

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
               usuarios.add(Usuario.builder().dni(rs.getString(1))
                       .usuario(rs.getString(2))
                       .contrasenya(rs.getString(3))
                       .nombre(rs.getString(4))
                       .apellidos(rs.getString(5))
                       .email(rs.getString(6))
                       .domicilio(rs.getString(7))
                       .codigo_postal(rs.getString(8))
                       .fecha_nacimiento(rs.getDate(9))
                       .tarjeta_credito(rs.getString(10))
                       .build());
            }
        }
        return usuarios;
    }

     */

    /**
     * Se obtiene el listado de clientes con todos sus datos a partir de una query a la BDD
     * @return la lista de usuarios
     * @throws SQLException
     */
    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM cliente";

        try(Connection connection = DataSource.getMyOracleDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while (rs.next()) {
                usuarios.add(Usuario.builder().dni(rs.getString(1))
                        .usuario(rs.getString(2))
                        .contrasenya(rs.getString(3))
                        .nombre(rs.getString(4))
                        .apellidos(rs.getString(5))
                        .email(rs.getString(6))
                        .domicilio(rs.getString(7))
                        .codigo_postal(rs.getString(8))
                        .fecha_nacimiento(rs.getString(9))
                        .tarjeta_credito(rs.getString(10))
                        .build());
            }
        }
        return usuarios;
    }

    /**
     * obtiene el usuario del dni que se pasa por parámetro
     * @param dni
     * @return el usuario generado a partir de los datos/atributos obtenidos de la query
     * @throws SQLException
     */
    @Override
    public Usuario getUsuario(String dni) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT * FROM cliente WHERE dniCliente = ?";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1,dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                usuario = Usuario.builder().dni(rs.getString(1))
                        .usuario(rs.getString(2))
                        .contrasenya(rs.getString(3))
                        .nombre(rs.getString(4))
                        .apellidos(rs.getString(5))
                        .email(rs.getString(6))
                        .domicilio(rs.getString(7))
                        .codigo_postal(rs.getString(8))
                        .fecha_nacimiento(rs.getString(9))
                        .tarjeta_credito(rs.getString(10))
                        .build();
        }
        return usuario;
    }

    /**
     * Añade un usuario pasado por parámetro a partir de todos sus datos. Se realiza a través de una query dentro de la BDD
     * @param usuario
     * @return el usuario introducido
     * @throws SQLException
     */
    @Override
    public Usuario addUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO cliente(dniCliente, usuario, contraseña, nombre, apellidos, email, domicilio, codigo_postal, fecha_nacimiento, tarjeta_credito) VALUES(?,?,?,?,?,?,?,?,?,?)";
        Usuario usuario1 = getUsuario(usuario.getDni());

        if (usuario1 != null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1,usuario.getDni());
            ps.setString(2,usuario.getUsuario());
            ps.setString(3,usuario.getContrasenya());
            ps.setString(4,usuario.getNombre());
            ps.setString(5,usuario.getApellidos());
            ps.setString(6,usuario.getEmail());
            ps.setString(7,usuario.getDomicilio());
            ps.setString(8,usuario.getCodigo_postal());
            ps.setString(9,usuario.getFecha_nacimiento());
            ps.setString(10,usuario.getTarjeta_credito());

            ps.executeUpdate();
        }
        return usuario;
    }

    /**
     *
     * @param login
     * @param passwd
     * @return
     * @throws SQLException
     */
    @Override
    public boolean authenticate(String login, String passwd) throws SQLException {
        boolean autenticado = false;

        String query = "SELECT COUNT(*) FROM cliente WHERE usuario = ? AND contraseña = ?";
        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query)
        ){
            ps.setString(1,login);
            ps.setString(2,passwd);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            if (resultSet.getInt(1) > 0)
                autenticado = true;

        }

        return autenticado;
    }


}
