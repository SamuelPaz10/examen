package hn.unah.lenguajes._0.examen.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes._0.examen.modelos.Cliente;
import hn.unah.lenguajes._0.examen.servicios.ClienteServicios;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServicios clienteServicios;

    @GetMapping("/obtener/todos")
    public List<Cliente> obtenerTodos() {
        return this.clienteServicios.obtenerTodos();
    }

    @PostMapping("/crear/nuevo")
    public Cliente crearCliente(@RequestBody Cliente nuevoCliente) {
        return this.clienteServicios.crearCliente(nuevoCliente);
    }

}
