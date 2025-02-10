package org.example.blibliotecafx.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutorDAO {


    @PersistenceContext
    private EntityManager entityManager;


    // Guardar autor
    public void save(Autor autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Actualizar autor
    public void update(Autor autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(autor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String nombre) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Autor autor = session.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre", Autor.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();

            session.remove(autor);
            transaction.commit();
        }  catch (Exception e) {
            e.printStackTrace();

        }
    }



    // Buscar autor por nombre
    public List<Autor> findByName(String nombre) {
        String query = "FROM Autor WHERE nombre LIKE :nombre";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Autor.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }

    // Buscar autor por ID
    public Autor findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Autor.class, id);
        }
    }

    // Listar todos los autores
    public List<Autor> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Autor", Autor.class).list();
        }
    }
}
