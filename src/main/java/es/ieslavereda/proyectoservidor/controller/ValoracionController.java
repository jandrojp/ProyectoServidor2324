package es.ieslavereda.proyectoservidor.controller;

import es.ieslavereda.proyectoservidor.repository.model.Pelicula;
import es.ieslavereda.proyectoservidor.repository.model.Valoracion;
import es.ieslavereda.proyectoservidor.service.ValoracionService;
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
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    /**
     * se recoge la valoración realizada por el usuario
     * @param valoracion
     * @return si se ha almacenado la valoración
     * @throws SQLException si se ha producido un error durante el proceso
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/valoraciones")
    public ResponseEntity<?> addValoracion(@RequestBody Valoracion valoracion) {
        try {
            Valoracion valoracion1 = valoracionService.addValoracion(valoracion);
            if (valoracion1 == null)
                return new ResponseEntity<>("La valoracion ya existe", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(valoracion1,HttpStatus.OK);

        }  catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
