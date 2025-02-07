package org.example.blibliotecafx.DAO;

import org.example.blibliotecafx.Entities.Socio;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SocioDAO {

    // Agregar socio
    public void save(Socio socio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socio);
            transaction.commit();
        }
    }

    // Actualizar socio
    public void update(Socio socio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(socio);
            transaction.commit();
        }
    }

    // Eliminar socio
    public void delete(Socio socio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(socio);
            transaction.commit();
        }
    }

    // Buscar socio por nombre
    public List<Socio> findByName(String nombre) {
        String query = "FROM Socio WHERE nombre LIKE :nombre";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Socio.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }

    // Buscar socio por teléfono
    public List<Socio> findByTelefono(String telefono) {
        String query = "FROM Socio WHERE telefono LIKE :telefono";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Socio.class)
                    .setParameter("telefono", "%" + telefono + "%")
                    .list();
        }
    }

    // Listar todos los socios
    public List<Socio> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socio", Socio.class).list();
        }
    }
}
