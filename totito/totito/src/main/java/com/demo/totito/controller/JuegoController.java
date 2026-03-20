package com.demo.totito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.totito.entity.Juego;
import com.demo.totito.service.JuegoService;

@RestController
@RequestMapping("/api/juego")
@CrossOrigin(origins = "*") // permite conexión con Angular
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    // =========================
    // 🎮 CREAR NUEVO JUEGO
    // =========================
    @PostMapping
    public Juego crearJuego() {
        return juegoService.crearJuego();
    }

    // =========================
    // 👤 JUGADA DEL HUMANO
    // =========================
    @PostMapping("/{id}/movimiento")
    public Juego jugar(@PathVariable Long id, @RequestBody MovimientoRequest request) {
        return juegoService.jugarHumano(id, request.getPosicion());
    }
}