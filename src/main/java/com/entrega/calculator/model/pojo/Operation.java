package com.entrega.calculator.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "operations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operacion")
    private String tipoOperacion;

    @ElementCollection
    @Column(name = "elementos")
    private List<Double> elementos;

    @Column(name = "resultado")
    private Double resultado;

    public void update(OperationDto operationDto) {
        this.tipoOperacion = operationDto.getTipoOperacion();
        this.elementos = operationDto.getElementos();
        this.resultado = operationDto.getResultado();

    }

}
