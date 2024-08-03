package hn.unah.lenguajes._0.examen.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes._0.examen.modelos.Cuotas;
import hn.unah.lenguajes._0.examen.modelos.Prestamos;
import hn.unah.lenguajes._0.examen.repositorios.PrestamosRepositorio;

@Service
public class PrestamosServicios {

    @Autowired
    private PrestamosRepositorio prestamosRepositorio;

    public List<Prestamos> obtenerTodos() {
        return this.prestamosRepositorio.findAll();
    }

    public Prestamos obtenerPrestamo(Long codigoprestamo) {
        return this.prestamosRepositorio.findById(codigoprestamo);
    }

    public Prestamos crearPrestamo(Prestamos nuevoPrestamos, double monto) {
        if (this.prestamosRepositorio.existsById(nuevoPrestamos.getCodigoprestamo())) {
            return null;
        }

        List<Cuotas> cuotas = nuevoPrestamos.getCuotas();
        long mes = 0;
        double interes = 0;
        double capital = 0;
        double saldo = monto;
        for (Cuotas cuota : cuotas) {
            cuota.setMes(mes);
            cuota.setInteres(interes);
            cuota.setCapital(capital);
            cuota.setSaldo(saldo);

            mes++;
            saldo = saldo * 0.09 / 12;

            cuota.setPrestamos(nuevoPrestamos);
        }
        return this.prestamosRepositorio.save(nuevoPrestamos);
    }
}
