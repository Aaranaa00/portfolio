package com.micarrera.dashboardbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class TerminalDTO {
    private String salida;
    private String tipo;
    private Object datos;
}
