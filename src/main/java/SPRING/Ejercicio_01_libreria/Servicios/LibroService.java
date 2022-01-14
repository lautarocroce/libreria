/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPRING.Ejercicio_01_libreria.Servicios;

import SPRING.Ejercicio_01_libreria.Entidades.Autor;
import SPRING.Ejercicio_01_libreria.Entidades.Editorial;
import SPRING.Ejercicio_01_libreria.Entidades.Libro;
import SPRING.Ejercicio_01_libreria.Errores.ErrorService;
import SPRING.Ejercicio_01_libreria.Repositorios.AutorRepositorio;
import SPRING.Ejercicio_01_libreria.Repositorios.EditorialRepositorio;
import SPRING.Ejercicio_01_libreria.Repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired  // Al poner autowired le decimos al servidor de aplicaciones que inicialice la variable
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void cargarLibro(String titulo, Integer ano, Integer ejemplares,
            //            String nombreAutor, String apellidoAutor, String nombreEditorial
            String idAutor, String idEditorial, byte[] foto) throws ErrorService {

//        if (String.valueOf(isbn) == null || String.valueOf(isbn).isEmpty() || isbn <= 0) {
//            throw new ErrorService("EL ISBN NO PUEDE ESTAR VACÍO, SER NULO O MENOR E IGUAL QUE CERO (0)");
//        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorService("EL TÍTULO NO PUEDE ESTAR VACÍO O SER NULO");
        }

        if (ano == null || String.valueOf(ano).isEmpty()) {
            throw new ErrorService("EL AÑO NO PUEDE ESTAR VACÍO O SER NULO");
        }

        if (ejemplares == null || String.valueOf(ejemplares).isEmpty()) {
            throw new ErrorService("LA CANTIDAD DE EJEMPLARES NO PUEDE ESTAR VACÍO O SER NULO");
        }
//
//        if (ejemplaresPrestados == null || String.valueOf(ejemplares).isEmpty() || ejemplaresPrestados < 0) {
//            throw new ErrorService("LA CANTIDAD DE EJEMPLARES NO PUEDE ESTAR VACÍO, SER NULA O MENOR QUE CERO (0)");
//        }
        if (idAutor == null || idAutor.isEmpty()) {
            throw new ErrorService("EL NOMBRE DEL AUTOR NO PUEDE ESTAR VACÍO O SER NULO");
        }
//
//        if (apellidoAutor == null || apellidoAutor.isEmpty()) {
//            throw new ErrorService("EL APELLIDO DEL AUTOR NO PUEDE ESTAR VACÍO O SER NULO");
//        }
//
        if (idEditorial == null || idEditorial.isEmpty()) {
            throw new ErrorService("EL NOMBRE DE LA EDITORIAL NO PUEDE ESTAR VACÍA O SER NULA");
        }

        Libro libro = new Libro();

        long isbn = (long) Math.floor(Math.random() * (13201 - 48779 + 1) + 48779);

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAno(ano);
        libro.setEjemplares(ejemplares);
//        libro.setEjemplaresPrestados(ejemplaresPrestados);

        Autor autor = autorRepositorio.getOne(idAutor);
        Editorial editorial = editorialRepositorio.getOne(idEditorial);

//        Autor autor = new Autor();
//        autor.setNombre(nombreAutor);
//        autor.setApellido(apellidoAutor);
//        Editorial editorial = new Editorial();
//        editorial.setNombre(nombreEditorial);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setFoto(foto);

        libroRepositorio.save(libro);

    }

    @Transactional
    public void modificarLibro(String id, long isbn, String titulo, Integer ano, Integer ejemplares) throws ErrorService {

        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();

            if (String.valueOf(isbn) == null || String.valueOf(isbn).isEmpty() || isbn <= 0) {
                throw new ErrorService("EL ISBN NO PUEDE ESTAR VACÍO, SER NULO O MENOR E IGUAL QUE CERO (0)");
            }

            if (titulo == null || titulo.isEmpty()) {
                throw new ErrorService("EL TÍTULO NO PUEDE ESTAR VACÍO O SER NULO");
            }

            if (ano == null || String.valueOf(ano).isEmpty()) {
                throw new ErrorService("EL AÑO NO PUEDE ESTAR VACÍO O SER NULO");
            }

            if (ejemplares == null || String.valueOf(ejemplares).isEmpty()) {
                throw new ErrorService("LA CANTIDAD DE EJEMPLARES NO PUEDE ESTAR VACÍO O SER NULO");
            }
//
//            if (ejemplaresPrestados == null || String.valueOf(ejemplares).isEmpty() || ejemplaresPrestados < 0) {
//                throw new ErrorService("LA CANTIDAD DE EJEMPLARES NO PUEDE ESTAR VACÍO, SER NULA O MENOR QUE CERO (0)");
//            }
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAno(ano);
            libro.setEjemplares(ejemplares);
//            libro.setEjemplaresPrestados(ejemplaresPrestados);

            libroRepositorio.save(libro);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL LIBRO BUSCADO");
        }

    }

    @Transactional
    public void bajaLibro(String id) throws ErrorService {

        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();

            libro.setAlta(false);

            libroRepositorio.save(libro);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL LIBRO BUSCADO");
        }
    }

    @Transactional
    public void altaLibro(String id) throws ErrorService {

        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();

            libro.setAlta(true);

            libroRepositorio.save(libro);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL LIBRO BUSCADO");
        }
    }

    @Transactional
    public void eliminarLibro(String id) throws ErrorService {

        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();

            libroRepositorio.delete(libro);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL LIBRO BUSCADO");
        }
    }

    public Libro mostarLibro(String id) throws ErrorService {

        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();

            return libro;

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL LIBRO BUSCADO");
        }
    }

    public List<Libro> mostarLibros() {

        List<Libro> listaLibros = libroRepositorio.ListarLibros();

        return listaLibros;
    }
}
