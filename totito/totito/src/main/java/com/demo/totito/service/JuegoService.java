package com.demo.totito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.totito.entity.Aprendizaje;
import com.demo.totito.entity.Juego;
import com.demo.totito.entity.Movimiento;
import com.demo.totito.repository.AprendizajeRepository;
import com.demo.totito.repository.JuegoRepository;
import com.demo.totito.repository.MovimientoRepository;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepo;

    @Autowired
    private MovimientoRepository movRepo;

    @Autowired
    private AprendizajeRepository aprRepo;

    @Autowired
    private IAService iaService;

    // =========================
    // 🎮 CREAR JUEGO
    // =========================
    public Juego crearJuego() {
        Juego juego = new Juego();
        juego.setTablero("---------");
        juego.setTurno("HUMANO");
        juego.setEstado("EN_CURSO");
        return juegoRepo.save(juego);
    }

    // =========================
    // 👤 JUGADA HUMANO
    // =========================
    public Juego jugarHumano(Long juegoId, int posicion) {

        Juego juego = juegoRepo.findById(juegoId).orElseThrow();

        if (!juego.getEstado().equals("EN_CURSO")) {
            throw new RuntimeException("Juego finalizado");
        }

        String tablero = juego.getTablero();

        if (tablero.charAt(posicion) != '-') {
            throw new RuntimeException("Posición ocupada");
        }

        String nuevoTablero = colocar(tablero, posicion, 'X');

        guardarMovimiento(juegoId, "HUMANO", posicion, tablero, nuevoTablero);

        juego.setTablero(nuevoTablero);

        // 🏆 gana humano
        if (hayGanador(nuevoTablero, 'X')) {
            juego.setGanador("HUMANO");
            juego.setEstado("FINALIZADO");
            actualizarAprendizaje(juegoId, -1);
            return juegoRepo.save(juego);
        }

        // 🤝 empate
        if (esEmpate(nuevoTablero)) {
            juego.setGanador("EMPATE");
            juego.setEstado("FINALIZADO");
            actualizarAprendizaje(juegoId, 0);
            return juegoRepo.save(juego);
        }

        // 🤖 turno IA
        return jugarIA(juego);
    }

    // =========================
    // 🤖 JUGADA IA
    // =========================
    private Juego jugarIA(Juego juego) {

        String tablero = juego.getTablero();

        int movimientoIA = iaService.obtenerMejorMovimiento(tablero);

        String nuevoTablero = colocar(tablero, movimientoIA, 'O');

        guardarMovimiento(juego.getId(), "IA", movimientoIA, tablero, nuevoTablero);

        juego.setTablero(nuevoTablero);

        // 🏆 gana IA
        if (hayGanador(nuevoTablero, 'O')) {
            juego.setGanador("IA");
            juego.setEstado("FINALIZADO");
            actualizarAprendizaje(juego.getId(), +1);
            return juegoRepo.save(juego);
        }

        // 🤝 empate
        if (esEmpate(nuevoTablero)) {
            juego.setGanador("EMPATE");
            juego.setEstado("FINALIZADO");
            actualizarAprendizaje(juego.getId(), 0);
            return juegoRepo.save(juego);
        }

        juego.setTurno("HUMANO");
        return juegoRepo.save(juego);
    }

    // =========================
    // 🧠 APRENDIZAJE IA
    // =========================
    private void actualizarAprendizaje(Long juegoId, int resultado) {

        List<Movimiento> movimientos = movRepo.findAll()
            .stream()
            .filter(m -> m.getJuegoId().equals(juegoId) && m.getJugador().equals("IA"))
            .toList();

        for (Movimiento mov : movimientos) {

            Aprendizaje apr = aprRepo
                .findByEstadoTableroAndMovimiento(mov.getTableroAntes(), mov.getPosicion())
                .orElse(new Aprendizaje());

            apr.setEstadoTablero(mov.getTableroAntes());
            apr.setMovimiento(mov.getPosicion());

            double nuevoValor = apr.getValor() + (resultado * 0.1);
            apr.setValor(nuevoValor);

            apr.setVecesUsado(apr.getVecesUsado() + 1);

            aprRepo.save(apr);
        }
    }

    // =========================
    // 💾 GUARDAR MOVIMIENTO
    // =========================
    private void guardarMovimiento(Long juegoId, String jugador, int posicion,
                                  String antes, String despues) {

        Movimiento mov = new Movimiento();
        mov.setJuegoId(juegoId);
        mov.setJugador(jugador);
        mov.setPosicion(posicion);
        mov.setTableroAntes(antes);
        mov.setTableroDespues(despues);

        movRepo.save(mov);
    }

    // =========================
    // 🧩 UTILIDADES
    // =========================
    private String colocar(String tablero, int pos, char jugador) {
        char[] arr = tablero.toCharArray();
        arr[pos] = jugador;
        return new String(arr);
    }

    private boolean esEmpate(String tablero) {
        return !tablero.contains("-");
    }

    private boolean hayGanador(String t, char j) {
        int[][] w = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };

        for (int[] c : w) {
            if (t.charAt(c[0]) == j &&
                t.charAt(c[1]) == j &&
                t.charAt(c[2]) == j) {
                return true;
            }
        }
        return false;
    }
}