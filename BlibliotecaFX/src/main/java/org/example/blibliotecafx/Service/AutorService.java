package org.example.blibliotecafx.Service;

import org.example.blibliotecafx.DAO.AutorDAO;
import org.example.blibliotecafx.Entities.Autor;

import java.util.List;

public class AutorService {
    private AutorDAO autorDAO = new AutorDAO();

    // Agregar autor
    public void agregarAutor(Autor autor) {
        autorDAO.save(autor);
    }

    // Actualizar autor
    public void actualizarAutor(Autor autor) {
        autorDAO.update(autor);
    }

    // Eliminar autor
    public void eliminarAutor(String nombre) {
        autorDAO.delete(nombre);
    }

    // Buscar autor por nombre
    public List<Autor> buscarAutorPorNombre(String nombre) {
        return autorDAO.findByName(nombre);
    }

    // Listar todos los autores
    public List<Autor> listarAutores() {
        return autorDAO.findAll();
    }
}
