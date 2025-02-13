package org.example.blibliotecafx.DAO;

import org.example.blibliotecafx.Entities.Prestamo;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamoDAO {

    // Registrar préstamo
    public void save(Prestamo prestamo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
        }
    }

    // Actualizar préstamo
    public void update(Prestamo prestamo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(prestamo);
            transaction.commit();
        }
    }

    // Eliminar préstamo
    public void delete(Prestamo prestamo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(prestamo);
            transaction.commit();
        }
    }

    public List<Prestamo> findPrestamosActivos() {
        String query = "FROM Prestamo WHERE fechaDevolucion IS NULL";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Prestamo.class).list();
        }
    }




    // Buscar historial de préstamos de un socio
    public List<Prestamo> findPrestamosPorSocio(int socioId) {
        String query = "FROM Prestamo WHERE socio.id = :socioId";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Prestamo.class)
                    .setParameter("socioId", socioId)
                    .list();
        }
    }
}
