package es.ieslavereda.proyectoservidor.service;

import es.ieslavereda.proyectoservidor.repository.PeliculaRepository;
import es.ieslavereda.proyectoservidor.repository.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;


@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository repository;

    public List<Pelicula> getAllPeliculas() throws SQLException {
        return repository.getAllPeliculas();
    }

    public Pelicula getPelicula(int id) throws SQLException {
        return repository.getPelicula(id);
    }

    public Pelicula deletePelicula(int id) throws SQLException {
        return repository.deletePelicula(id);
    }

    public Pelicula addPelicula(Pelicula pelicula) throws SQLException {
        return repository.addPelicula(pelicula);
    }

    public Pelicula updatePelicula(Pelicula pelicula) throws SQLException {
        return repository.updatePelicula(pelicula);
    }

}


