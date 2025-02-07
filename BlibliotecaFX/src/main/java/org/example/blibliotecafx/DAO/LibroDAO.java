package org.example.blibliotecafx.DAO;

import org.example.blibliotecafx.Entities.Libro;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroDAO {

    // Guardar un libro
    public void save(Libro libro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(libro);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Actualizar un libro
    public void update(Libro libro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(libro);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar un libro
    public void delete(Libro libro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(libro);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Buscar libro por ID
    public Libro findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Libro.class, id);
        }
    }

    // Buscar libros por título
    public List<Libro> findByTitulo(String titulo) {
        String query = "FROM Libro WHERE titulo LIKE :titulo";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Libro.class)
                    .setParameter("titulo", "%" + titulo + "%")
                    .list();
        }
    }

    // Obtener libros disponibles
    public List<Libro> findLibrosDisponibles() {
        String query = "FROM Libro WHERE prestado = false";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Libro.class).list();
        }
    }
}
