package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.Usuario;

import java.sql.SQLException;
import java.util.List;
/**
 * @author: Alejandro Jorge, Alejandro Paul, Marcos Martínez
 * version: 2024 v1
 */
public interface IUsuarioRepository {
    /**
     * Interfaces que definen qué métodos se deben implementar en los repositorios (CRUD) de usuarios
     */
    Usuario getUsuario(String dni) throws SQLException;
    Usuario addUsuario(Usuario usuario) throws SQLException;
    Usuario updateUsuario(Usuario usuario) throws SQLException;
    Usuario deleteUsuario(String dni) throws SQLException;
    List<Usuario> getAllUsuarios() throws SQLException;
    boolean authenticate(String login, String passwd) throws SQLException;
}
