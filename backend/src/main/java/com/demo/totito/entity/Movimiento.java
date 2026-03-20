package com.demo.totito.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long juegoId;
    private String jugador; // HUMANO / IA
    private int posicion;

    private String tableroAntes;
    private String tableroDespues;
    public Long getId() {
        return id;
    }
    public Long getJuegoId() {
        return juegoId;
    }
    public String getJugador() {
        return jugador;
    }
    public int getPosicion() {
        return posicion;
    }
    public String getTableroAntes() {
        return tableroAntes;
    }
    public String getTableroDespues() {
        return tableroDespues;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }
    public void setJugador(String jugador) {
        this.jugador = jugador;
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    public void setTableroAntes(String tableroAntes) {
        this.tableroAntes = tableroAntes;
    }
    public void setTableroDespues(String tableroDespues) {
        this.tableroDespues = tableroDespues;
    }
    

}
