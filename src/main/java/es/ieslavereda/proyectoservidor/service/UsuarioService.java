package es.ieslavereda.proyectoservidor.service;

import es.ieslavereda.proyectoservidor.repository.UsuarioRepository;
import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
/**
 * se realiza el CRUD del usuario a partir de su Pk que es el DNI
 * @author: Alejandro Jorge, Alejandro Paul, Marcos Martínez
 * version: 2024 v1
 */
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;


    public List<Usuario> getAllUsuarios() throws SQLException {
        return repository.getAllUsuarios();
    }

    public Usuario getUsuario(String dni) throws SQLException {
        return repository.getUsuario(dni);
    }

    public Usuario deleteUsuario(String dni) throws SQLException {
        return repository.deleteUsuario(dni);
    }

    public Usuario addUsuario(Usuario usuario) throws SQLException {
        return repository.addUsuario(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        return repository.updateUsuario(usuario);
    }


    public boolean authenticate(String login, String passwd) throws SQLException {
        return repository.authenticate(login, passwd);
    }


}