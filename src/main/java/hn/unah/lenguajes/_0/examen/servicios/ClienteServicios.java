package hn.unah.lenguajes._0.examen.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes._0.examen.modelos.Cliente;
import hn.unah.lenguajes._0.examen.modelos.Cuotas;
import hn.unah.lenguajes._0.examen.modelos.Prestamos;
import hn.unah.lenguajes._0.examen.repositorios.ClienteRepositorio;
import hn.unah.lenguajes._0.examen.repositorios.PrestamosRepositorio;

@Service
public class ClienteServicios {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PrestamosRepositorio prestamosRepositorio;

    public List<Cliente> obtenerTodos() {
        return this.clienteRepositorio.findAll();
    }

    public Cliente crearCliente(Cliente nuevoCliente) {
        if (this.clienteRepositorio.existsById(nuevoCliente.getDni())) {
            return null;
        }

        List<Prestamos> prestamos = nuevoCliente.getPrestamos();
        if (prestamos != null) {
            for (Prestamos prestamo : prestamos) {
                double i = (0.09 / 12);
                double cuota = (prestamo.getMonto() * i * Math.pow(1 + i, prestamo.getPlazo() * 12))
                        / (Math.pow(1 + i, prestamo.getPlazo() * 12) - 1);
                prestamo.setCuota(cuota);

                
                prestamo.setCliente(nuevoCliente);
            }
        }
        return this.clienteRepositorio.save(nuevoCliente);
    }
}
