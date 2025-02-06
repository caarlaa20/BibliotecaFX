package org.example.blibliotecafx.DAO;

import org.example.blibliotecafx.Entities.Socio;
import org.example.blibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SocioDAO extends GenericDAO<Socio> {

    public SocioDAO() {
        super(Socio.class);
    }

    // Buscar socios por nombre
    public List<Socio> findByName(String nombre) {
        String query = "FROM Socio WHERE nombre LIKE :nombre";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Socio.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }

    // Buscar socios por teléfono
    public List<Socio> findByTelefono(String telefono) {
        String query = "FROM Socio WHERE telefono LIKE :telefono";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Socio.class)
                    .setParameter("telefono", "%" + telefono + "%")
                    .list();
        }
    }
}

