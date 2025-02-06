package org.example.blibliotecafx.DAO;

import org.example.blibliotecafx.Entities.Prestamo;
import org.example.blibliotecafx.Entities.Socio;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class PrestamoDAO extends GenericDAO<Prestamo> {

    public PrestamoDAO() {
        super(Prestamo.class);
    }

    // Buscar préstamos activos
    public List<Prestamo> findPrestamosActivos() {
        String query = "FROM Prestamo WHERE fechaDevolucion IS NULL";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Prestamo.class).list();
        }
    }

    // Buscar historial de préstamos de un socio
    public List<Prestamo> findPrestamosPorSocio(Socio socio) {
        String query = "FROM Prestamo WHERE socio = :socio";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Prestamo.class)
                    .setParameter("socio", socio)
                    .list();
        }
    }
}
