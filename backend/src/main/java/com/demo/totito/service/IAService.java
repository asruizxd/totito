package com.demo.totito.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.totito.entity.Aprendizaje;
import com.demo.totito.repository.AprendizajeRepository;

@Service
public class IAService {

    @Autowired
    private AprendizajeRepository aprRepo;

    // =========================
    // 🤖 OBTENER MEJOR MOVIMIENTO
    // =========================
    public int obtenerMejorMovimiento(String tablero) {

        List<Aprendizaje> jugadas = aprRepo
                .findByEstadoTableroOrderByValorDesc(tablero);

        // 🧠 Si ya aprendió algo
        if (!jugadas.isEmpty()) {

            for (Aprendizaje jugada : jugadas) {
                int pos = jugada.getMovimiento();

                // Validar que la posición esté libre
                if (tablero.charAt(pos) == '-') {
                    return pos;
                }
            }
        }

        // 🎲 Si no sabe → aleatorio
        return movimientoAleatorio(tablero);
    }

    // =========================
    // 🎲 MOVIMIENTO ALEATORIO
    // =========================
    private int movimientoAleatorio(String tablero) {

        Random rand = new Random();
        int pos;

        do {
            pos = rand.nextInt(9);
        } while (tablero.charAt(pos) != '-');

        return pos;
    }
}