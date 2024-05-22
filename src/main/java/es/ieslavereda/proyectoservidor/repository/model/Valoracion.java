package es.ieslavereda.proyectoservidor.repository.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Valoracion {

    private int idValoracion;
    private int idContenido;
    private String dniCliente;
    private int puntuacion;
}
