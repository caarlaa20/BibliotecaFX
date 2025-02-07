package org.example.blibliotecafx.DAO;

import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutorDAO {

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

    // Eliminar autor
    public void delete(Autor autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(autor);
            transaction.commit();
        } catch (Exception e) {
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

    // Listar todos los autores
    public List<Autor> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Autor", Autor.class).list();
        }
    }
}
