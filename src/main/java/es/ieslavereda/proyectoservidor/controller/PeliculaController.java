package es.ieslavereda.proyectoservidor.controller;

import es.ieslavereda.proyectoservidor.repository.model.Pelicula;
import es.ieslavereda.proyectoservidor.service.PeliculaService;
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

public class PeliculaController {

    @Autowired
    private PeliculaService service;

    /**
     * @return map de películas si las ha podido localizar y error si no ha podido hacerlo
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/peliculas")
    public ResponseEntity<?> getAllPeliculas() {

        try {
            return new ResponseEntity<>(service.getAllPeliculas(), HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id a partir del id de la película que es su PK, se devuelve la película
     * @return devuelve la película a partir del entero pasado por parámetro o si no lo ha encontrado
     * @throws SQLException si no encuentra la película por ese Id, devuelve una excepción informando al usuario
     */
    @GetMapping("/peliculas/{id}")
    public ResponseEntity<?> getByID(@PathVariable("id") int id){
        try {
            Pelicula pelicula = service.getPelicula(id);
            if (pelicula == null)
                return new ResponseEntity<>("Pelicula no encontrada", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(pelicula, HttpStatus.OK);

        } catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/peliculas/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable("id") int id){
        try {
            Pelicula pelicula = service.deletePelicula(id);
            if (pelicula == null)
                return new ResponseEntity<>("Pelicula no encontrada",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(pelicula,HttpStatus.OK);

        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/peliculas")
    public ResponseEntity<?> addPelicula(@RequestBody Pelicula pelicula) {
        try {
            Pelicula pelicula1 = service.addPelicula(pelicula);
            if (pelicula1 == null)
                return new ResponseEntity<>("La pelicula ya existe",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(pelicula1,HttpStatus.OK);

        }  catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/peliculas")
    public ResponseEntity<?> updatePelicula(@RequestBody Pelicula pelicula) {
        try {
            Pelicula pelicula1 = service.updatePelicula(pelicula);
            if (pelicula1 == null)
                return new ResponseEntity<>("Pelicula no encontrada",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(pelicula1,HttpStatus.OK);

        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


