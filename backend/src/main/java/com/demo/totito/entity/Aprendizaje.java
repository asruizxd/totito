package com.demo.totito.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aprendizaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estadoTablero;
    private int movimiento;
    private double valor;
    private int vecesUsado;
    public Long getId() {
        return id;
    }
    public String getEstadoTablero() {
        return estadoTablero;
    }
    public int getMovimiento() {
        return movimiento;
    }
    public double getValor() {
        return valor;
    }
    public int getVecesUsado() {
        return vecesUsado;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setEstadoTablero(String estadoTablero) {
        this.estadoTablero = estadoTablero;
    }
    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setVecesUsado(int vecesUsado) {
        this.vecesUsado = vecesUsado;
    }

    
}
