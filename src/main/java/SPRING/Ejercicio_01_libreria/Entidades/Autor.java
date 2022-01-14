/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPRING.Ejercicio_01_libreria.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Homero
 */
@Entity
public class Autor {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;
//    @Column(unique = true)
    private String nombre;
//    @Column(unique = true)
//    private String apellido;
    private Boolean alta;

    public Autor() {
        this.alta = true;
    }

    public Autor(String id, String nombre, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
//        this.apellido = apellido;
        this.alta = alta;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the alta
     */
    public Boolean getAlta() {
        return alta;
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

}
