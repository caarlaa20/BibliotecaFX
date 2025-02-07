package org.example.blibliotecafx.Service;

import org.example.blibliotecafx.DAO.LibroDAO;
import org.example.blibliotecafx.Entities.Libro;

import java.util.List;

public class LibroService {
    private LibroDAO libroDAO = new LibroDAO();

    // Agregar libro
    public void agregarLibro(Libro libro) {
        libroDAO.save(libro);
    }

    // Actualizar libro
    public void actualizarLibro(Libro libro) {
        libroDAO.update(libro);
    }

    // Eliminar libro
    public void eliminarLibro(Libro libro) {
        libroDAO.delete(libro);
    }

    // Buscar libro por ID
    public Libro buscarLibroPorId(int id) {
        return libroDAO.findById(id);
    }

    // Listar libros disponibles
    public List<Libro> listarLibrosDisponibles() {
        return libroDAO.findLibrosDisponibles();
    }

    // Buscar libros por título
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return libroDAO.findByTitulo(titulo);
    }
}
