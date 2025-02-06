package org.example.blibliotecafx.DAO;

import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AutorDAO extends GenericDAO<Autor> {

    public AutorDAO() {
        super(Autor.class);
    }

    // Buscar autores por nombre
    public List<Autor> findByName(String nombre) {
        String query = "FROM Autor WHERE nombre LIKE :nombre";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Autor.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }
}
