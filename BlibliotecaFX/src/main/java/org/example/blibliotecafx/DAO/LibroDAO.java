package org.example.blibliotecafx.DAO;

import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Entities.Libro;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class LibroDAO extends GenericDAO<Libro> {

    public LibroDAO() {
        super(Libro.class);
    }

    // Puedes agregar métodos específicos para los libros, por ejemplo, buscar libros por autor
    public List<Libro> findByAutor(Autor autor) {
        String query = "FROM Libro WHERE autor = :autor";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Libro.class)
                    .setParameter("autor", autor)
                    .list();
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

    // Obtener libros no prestados
    public List<Libro> findLibrosDisponibles() {
        String query = "FROM Libro WHERE prestado = false";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Libro.class).list();
        }
    }
}

