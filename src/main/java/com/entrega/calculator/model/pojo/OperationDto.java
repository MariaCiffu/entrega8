package com.entrega.calculator.model.pojo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OperationDto {
    private String tipoOperacion;
    private List<Double> elementos;
    private Double resultado;
}
