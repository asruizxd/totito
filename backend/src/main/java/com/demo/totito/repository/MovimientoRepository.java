package com.demo.totito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.totito.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

}
