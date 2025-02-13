package org.example.blibliotecafx.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "socio")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    // Relación con préstamos
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos;

    // Sobrescribir el método toString para que devuelva el nombre del socio
    @Override
    public String toString() {
        return nombre;  // Solo mostrar el nombre del socio
    }

    // Constructores, getters y setters
    public Socio() {}

    public Socio(String nombre, String telefono, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
