package org.example.blibliotecafx.Service;

import org.example.blibliotecafx.DAO.SocioDAO;
import org.example.blibliotecafx.Entities.Socio;

import java.util.List;

public class SocioService {

    private SocioDAO socioDAO;

    // Constructor
    public SocioService() {
        socioDAO = new SocioDAO();
    }

    // Agregar un socio
    public void agregarSocio(Socio socio) {
        socioDAO.save(socio);
    }

    // Modificar un socio
    public void modificarSocio(Socio socio) {
        socioDAO.update(socio);
    }

    // Eliminar un socio
    /*public void eliminarSocio(int id) {
        Socio socio = socioDAO.findById(id);
        if (socio != null) {
            socioDAO.delete(socio);
        }
    }*/

    // Buscar socio por nombre
    public List<Socio> buscarSocioPorNombre(String nombre) {
        return socioDAO.findByName(nombre);
    }

    // Listar todos los socios
    public List<Socio> listarTodosLosSocios() {
        return socioDAO.findAll();
    }
}
