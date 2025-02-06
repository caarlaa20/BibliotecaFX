package org.example.blibliotecafx.Service;

import org.example.blibliotecafx.DAO.SocioDAO;
import org.example.blibliotecafx.Entities.Socio;

import java.util.List;

public class SocioService {
    private SocioDAO socioDAO = new SocioDAO();

    // Agregar socio
    public void agregarSocio(Socio socio) {
        socioDAO.save(socio);
    }

    // Actualizar socio
    public void actualizarSocio(Socio socio) {
        socioDAO.update(socio);
    }

    // Eliminar socio
    public void eliminarSocio(Socio socio) {
        socioDAO.delete(socio);
    }

    // Buscar socio por nombre
    public List<Socio> buscarSocioPorNombre(String nombre) {
        return socioDAO.findByName(nombre);
    }

    // Buscar socio por teléfono
    public List<Socio> buscarSocioPorTelefono(String telefono) {
        return socioDAO.findByTelefono(telefono);
    }

    // Listar todos los socios
    public List<Socio> listarSocios() {
        return socioDAO.findAll();
    }
}
