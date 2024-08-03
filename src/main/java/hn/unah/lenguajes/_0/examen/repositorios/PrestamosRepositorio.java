package hn.unah.lenguajes._0.examen.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.lenguajes._0.examen.modelos.Prestamos;

public interface PrestamosRepositorio extends JpaRepository<Prestamos, Long> {
    
}
