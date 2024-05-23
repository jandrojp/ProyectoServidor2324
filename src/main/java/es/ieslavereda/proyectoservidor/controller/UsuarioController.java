package es.ieslavereda.proyectoservidor.controller;

import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import es.ieslavereda.proyectoservidor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
/**
 * @author: Alejandro Jorge, Alejandro Paul, Marcos Martínez
 * version: 2024 v1
 */
@RestController
@RequestMapping("/miraveredaAPI")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    /**
     * @return map de usuarios si los ha podido localizar y error si no ha podido hacerlo
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsuarios() {
        try {
            return new ResponseEntity<>(service.getAllUsuarios(), HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * @param dni a partir del dni del usuario que es su PK, se devuelve el usuario
     * @return devuelve el usuario correspondiente al dni pasado por parámetro o si no lo ha encontrado
     * @throws SQLException si no encuentra el dni del usuario buscado, devuelve una excepción informando al usuario
     */
    @GetMapping("/usuarios/{dni}")
    public ResponseEntity<?> getByID(@PathVariable("dni") String dni){
        try {
            Usuario usuario = service.getUsuario(dni);
            if (usuario == null)
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario, HttpStatus.OK);

        } catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * borra usuarios a partir del dni pasado por parámetro
     * @param dni será un número entero
     * @return devuelve si ha podido eliminar el usuario o no
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/usuarios/{dni}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("dni") String dni) {
        try {
            Usuario usuario = service.deleteUsuario(dni);
            if (usuario == null)
                return new ResponseEntity<>("Usuario no encontrado",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario,HttpStatus.OK);

        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * este método añade usuarios desde el cliente a la base de datos, comprueba si existe y si no existe, lo añade
     * @param usuario es un objeto de tipo usuario con sus atributos
     * @return si ha podido añadir el usuario o no
     * @throws SQLException da error si no puede añadir el usuario al map de usuarios
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/usuarios")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuario1 = service.addUsuario(usuario);
            if (usuario1 == null)
                return new ResponseEntity<>("El usuario ya existe",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario1,HttpStatus.OK);

        }  catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * comprueba si el usuario y contraseña que va a utilizar el usuario ya existe y no se puede hacer un nuevo registro con esos datos
     * @param usuario nombre de usuario que se quiere crear
     * @param contrasenya contraseña asignada al usaurio
     * @return devuelve si se produce o no la autenticación del usuario
     * @throws SQLException lanza una excepción si no se ha podido autenticar
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/usuarios/autenticar")
    public ResponseEntity<?> authenticate(@RequestBody String usuario, String contrasenya) {
        try {
            boolean autenticado = service.authenticate(usuario, contrasenya);
            if (autenticado)
                return new ResponseEntity<>("El usuario ya existe",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(autenticado,HttpStatus.OK);

        }  catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Actualiza algún atributo del usuario si  existe en la base de datos
     * @param usuario se pasa el usuario a modificar
     * @return el usuario actualizado o un código de error si no ha podido hacer la actualización
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/usuarios")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {
        try{
            Usuario usuario1 = service.updateUsuario(usuario);
            if (usuario1 == null)
                return new ResponseEntity<>("Usuario no encontrado",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario1,HttpStatus.OK);

        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}