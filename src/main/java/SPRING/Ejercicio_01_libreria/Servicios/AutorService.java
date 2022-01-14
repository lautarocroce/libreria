package SPRING.Ejercicio_01_libreria.Servicios;

import SPRING.Ejercicio_01_libreria.Entidades.Autor;
import SPRING.Ejercicio_01_libreria.Errores.ErrorService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SPRING.Ejercicio_01_libreria.Repositorios.AutorRepositorio;

/**
 *
 * @author Homero
 */
@Service
public class AutorService {

    @Autowired  // Al poner autowired le decimos al servidor de aplicaciones que inicialice la variable
    private AutorRepositorio autorRepositorio;

    /* MÉTODO PARA CARGAR UN NUEVO AUTOR EN LA BASE DE DATOS */
 /* 
    @Transactional - QUIERE DECIR QUE SI EL MÉTODO SE EJECUTA SIN LANZAR EXCEPCIONES,
    ENTONCES SE HACE UN COMMIT EN LA BASE DE DATOS Y SE APLICAN TODOS LOS CAMBIOS. DE LO
    CONTRARIO SE HACE UN ROLLBACK, VUELVE TODO ATRÁS
     */
    @Transactional
    public void cargarAutor(String nombre) throws ErrorService {

        validarDatos(nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);
//        autor.setApellido(apellido);

        /* Esto hace que el repositorio guarde los datos en la base de datos */
        autorRepositorio.save(autor);
    }

    /* MÉTODO PARA MODIFICAR UN AUTOR EN LA BASE DE DATOS */
    @Transactional
    public void modificarAutor(String id, String nombre) throws ErrorService {

        validarDatos(nombre);

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autor.setNombre(nombre);
//            autor.setApellido(apellido);

            autorRepositorio.save(autor);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL AUTOR BUSCADO");
        }

//        Autor autor = autorRepositorio.buscarPorId(id);
//
//        autor.setNombre(nombre);
//        autor.setApellido(apellido);
//
//        autorRepositorio.save(autor);
    }

    /* MÉTODO PARA ELIMINAR UN AUTOR EN LA BASE DE DATOS */
    @Transactional
    public void eliminarAutor(String id) throws ErrorService {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autorRepositorio.delete(autor);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL AUTOR BUSCADO");
        }
    }

    /* MÉTODO PARA DAR DE BAJA SIN ELIMINAR UN AUTOR EN LA BASE DE DATOS */
    @Transactional
    public void bajaAutor(String id) throws ErrorService {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autor.setAlta(false);

            autorRepositorio.save(autor);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL AUTOR BUSCADO");
        }
    }

    /* MÉTODO PARA DAR DE ALTA UN AUTOR DADO DE BAJA EN LA BASE DE DATOS */
    @Transactional
    public void altaAutor(String id) throws ErrorService {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autor.setAlta(true);

            autorRepositorio.save(autor);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL AUTOR BUSCADO");
        }
    }

    /* MÉTODO PARA MOSTAR UN AUTOR ESPECÍFICO DE LA BASE DE DATOS */
    public Autor mostarAutor(String id) throws ErrorService {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            return autor;

        } else {
            throw new ErrorService("NO SE ENCONTRÓ EL AUTOR BUSCADO");
        }
    }

    /* MÉTODO PARA MOSTAR LA LISTA DE AUTORES DE LA BASE DE DATOS */
    public List<Autor> mostrarAutores() {

        List<Autor> listaAutores = autorRepositorio.listarAutores();

        return listaAutores;
    }

    /* MÉTODO PARA MOSTAR LA LISTA DE AUTORES DE ALTA EN LA BASE DE DATOS */
    public List<Autor> mostrarAltas() {

        List<Autor> listaAltas = autorRepositorio.listarAltas();

        return listaAltas;
    }

    /* MÉTODO PARA MOSTAR LA LISTA DE AUTORES DADOS DE BAJA EN LA BASE DE DATOS */
    public List<Autor> mostrarBajas() {

        List<Autor> listaBajas = autorRepositorio.listarBajas();

        return listaBajas;
    }
    
    public Autor buscarAutorPorNombreyApellido (String nombre, String apellido){
        
        Autor autor = autorRepositorio.buscarAutorPorNombre(nombre);
        
        return autor;
    }

    /* MÉTODO PARA VALIDAR LOS DATOS PASADOS POR PARÁMETRO A CADA MÉTODO ANTERIOR */
    private void validarDatos(String nombre) throws ErrorService {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorService("EL NOMBRE DEL AUTOR NO PUEDE SER NULO NI ESTAR VACÍO");
        }

//        if (apellido == null || apellido.isEmpty()) {
//            throw new ErrorService("EL APELLIDO DEL AUTOR NO PUEDE SER NULO NI ESTAR VACÍO");
//        }
    }

}
