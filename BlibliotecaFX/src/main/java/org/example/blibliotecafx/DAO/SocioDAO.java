package org.example.blibliotecafx.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Entities.Socio;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SocioDAO {


    @PersistenceContext
    private EntityManager entityManager;


    // Guardar autor
    public void save(Socio socio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socio);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Actualizar autor
    public void update(Socio socio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(socio);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String nombre) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Socio socio = session.createQuery("SELECT a FROM Socio a WHERE a.nombre = :nombre", Socio.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();

            session.remove(socio);
            transaction.commit();
        }  catch (Exception e) {
            e.printStackTrace();

        }
    }



    // Buscar autor por nombre
    public List<Socio> findByName(String nombre) {
        String query = "FROM Socio WHERE nombre LIKE :nombre";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Socio.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }

    // Buscar autor por ID
    public Socio findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Socio.class, id);
        }
    }

    // Listar todos los autores
    public List<Socio> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socio", Socio.class).list();
        }
    }
}
