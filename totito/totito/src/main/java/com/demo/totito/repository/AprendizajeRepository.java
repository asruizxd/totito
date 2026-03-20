package com.demo.totito.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.totito.entity.Aprendizaje;

public interface AprendizajeRepository extends JpaRepository<Aprendizaje, Long> {
    Optional<Aprendizaje> findByEstadoTableroAndMovimiento(String estado, int movimiento);

    List<Aprendizaje> findByEstadoTableroOrderByValorDesc(String estado);

}
