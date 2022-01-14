/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPRING.Ejercicio_01_libreria.Entidades;

import SPRING.Ejercicio_01_libreria.Servicios.AutorService;
import SPRING.Ejercicio_01_libreria.Servicios.EditorialService;
import com.sun.istack.NotNull;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Homero
 */
@Entity
public class Libro {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @NotNull
    @Column(unique = true)
    private String id;
    private long isbn;
    private String titulo;
    private Integer ano;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;
    @Lob
    @Column(name="imagen")
    private byte[] foto;

    @ManyToOne//(cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL)
    private Autor autor;

    @OneToOne//(cascade = CascadeType.ALL)
    private Editorial editorial;

    public Libro() {
        this.alta = true;
        this.ejemplaresPrestados = 0;
    }
    
    public Libro(String id, long isbn, String titulo, Integer ano, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, byte[] foto, Autor autor, Editorial editorial) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.foto = foto;
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
        this.ejemplaresRestantes = this.ejemplares - this.ejemplaresPrestados;
        this.ejemplaresPrestados = this.ejemplares - this.ejemplaresRestantes;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

//    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
//        this.ejemplaresPrestados = ejemplaresPrestados;
//        this.ejemplaresRestantes = this.ejemplares - this.ejemplaresPrestados;
//    }
    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

//    public void setEjemplares_restantes(Integer ejemplares_restantes) {
//        this.ejemplares_restantes = ejemplares_restantes;
//    }
    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", ano=" + ano + ", ejemplares=" + ejemplares + ", ejemplaresPrestados=" + ejemplaresPrestados + ", ejemplaresRestantes=" + ejemplaresRestantes + ", alta=" + alta + ", autor=" + autor + ", editorial=" + editorial + '}';
    }

    public void setAutor(AutorService as) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEditorial(EditorialService es) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public byte[] getFoto() {
        return foto;
    }
    
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

}
