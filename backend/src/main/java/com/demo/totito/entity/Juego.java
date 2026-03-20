package com.demo.totito.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tablero; // "---------"
    private String turno; // HUMANO / IA
    private String ganador; // HUMANO / IA / EMPATE
    private String estado; // EN_CURSO / FINALIZADO
    public Long getId() {
        return id;
    }
    public String getTablero() {
        return tablero;
    }
    public String getTurno() {
        return turno;
    }
    public String getGanador() {
        return ganador;
    }
    public String getEstado() {
        return estado;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTablero(String tablero) {
        this.tablero = tablero;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
