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
            session.remove(libro);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Buscar un libro por ID
    public Libro findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Libro.class, id);
        }
    }

    // Listar todos los libros disponibles (que no estén prestados)
    public List<Libro> findLibrosDisponibles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libro WHERE prestado = false", Libro.class).list();
        }
    }

    // Listar todos los autores
    public List<Libro> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libro", Libro.class).list();
        }
    }
    // Buscar libros por título
    public List<Libro> findByTitulo(String titulo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libro WHERE titulo LIKE :titulo", Libro.class)
                    .setParameter("titulo", "%" + titulo + "%")
                    .list();
        }
    }
    // Buscar libros por autor
    public List<Libro> findByAutor(String autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libro WHERE autor LIKE :autor", Libro.class)
                    .setParameter("autor", "%" + autor + "%")
                    .list();
        }
    }

    // Buscar libros por autor
    public List<Libro> findByIsbn(String isbn) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libro WHERE isbn LIKE :isbn", Libro.class)
                    .setParameter("isbn", "%" + isbn + "%")
                    .list();
        }
    }
}
