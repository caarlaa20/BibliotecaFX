package org.example.blibliotecafx.Service;

import org.example.blibliotecafx.DAO.PrestamoDAO;
import org.example.blibliotecafx.Entities.Prestamo;
import org.example.blibliotecafx.Entities.Socio;

import java.util.List;

public class PrestamoService {
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    // Registrar préstamo
    public void registrarPrestamo(Prestamo prestamo) {
        prestamoDAO.save(prestamo);
    }

    // Actualizar préstamo
    public void actualizarPrestamo(Prestamo prestamo) {
        prestamoDAO.update(prestamo);
    }

    // Eliminar préstamo
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoDAO.delete(prestamo);
    }

    // Buscar préstamos activos
    public List<Prestamo> buscarPrestamosActivos() {
        return prestamoDAO.findPrestamosActivos();
    }

}
