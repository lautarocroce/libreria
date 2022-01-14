/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPRING.Ejercicio_01_libreria.Servicios;

import SPRING.Ejercicio_01_libreria.Entidades.Editorial;
import SPRING.Ejercicio_01_libreria.Errores.ErrorService;
import SPRING.Ejercicio_01_libreria.Repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Homero
 */
@Service
public class EditorialService {

    @Autowired  // Al poner autowired le decimos al servidor de aplicaciones que inicialice la variable
    private EditorialRepositorio editorialRepositorio;

    /* MÉTODO PARA CARGAR UNA NUEVA EDITORIAL EN LA BASE DE DATOS */
    @Transactional
    public void cargarEditorial(String nombre) throws ErrorService {

        validarDatos(nombre);

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    /* MÉTODO PARA MODIFICAR UNA EDITORIAL DE LA BASE DE DATOS */
    @Transactional
    public void modificarEditorial(String id, String nombre) throws ErrorService {

        validarDatos(nombre);

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ LA EDITORIAL BUSCADA");
        }
    }

    /* MÉTODO PARA DAR DE BAJA SIN ELIMINAR UNA EDITORIAL DE LA BASE DE DATOS */
    @Transactional
    public void bajaEditorial(String id) throws ErrorService {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            editorial.setAlta(false);

            editorialRepositorio.save(editorial);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ LA EDITORIAL BUSCADA");
        }
    }

    /* MÉTODO PARA DAR DE ALTA UNA EDITORIAL DADA DE BAJA EN LA BASE DE DATOS */
    @Transactional
    public void altaEditorial(String id) throws ErrorService {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            editorial.setAlta(true);

            editorialRepositorio.save(editorial);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ LA EDITORIAL BUSCADA");
        }
    }

    /* MÉTODO PARA ELIMINAR UNA EDITORIAL DE LA BASE DE DATOS */
    @Transactional
    public void eliminarEditorial(String id) throws ErrorService {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            editorialRepositorio.delete(editorial);

        } else {
            throw new ErrorService("NO SE ENCONTRÓ LA EDITORIAL BUSCADA");
        }
    }

    /* MÉTODO PARA MOSTAR UNA EDITORIAL ESPECÍFICA DE LA BASE DE DATOS */
    public Editorial mostarEditorial(String id) throws ErrorService {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            return editorial;

        } else {
            throw new ErrorService("NO SE ENCONTRÓ LA EDITORIAL BUSCAD");
        }
    }

    /* MÉTODO PARA MOSTAR LA LISTA DE EDITORIALES DE LA BASE DE DATOS */
    public List<Editorial> mostarEditoriales() {

        List<Editorial> listaEditoriales = editorialRepositorio.ListarEditoriales();

        return listaEditoriales;
    }
    
     public Editorial buscarEditorialPorNombre (String nombre){
        
        Editorial editorial = editorialRepositorio.buscarEditorialPorNombre(nombre);
        
        return editorial;
    }
        
     /* MÉTODO PARA MOSTAR LA LISTA DE EDITORIALES DE ALTA EN LA BASE DE DATOS */
    public List<Editorial> mostrarAltas() {

        List<Editorial> listaAltas = editorialRepositorio.listarAltas();

        return listaAltas;
    }

    /* MÉTODO PARA MOSTAR LA LISTA DE EDITORIALES DADAS DE BAJA EN LA BASE DE DATOS */
    public List<Editorial> mostrarBajas() {

        List<Editorial> listaBajas = editorialRepositorio.listarBajas();

        return listaBajas;
    }

    /* MÉTODO PARA VALIDAR LOS DATOS PASADOS POR PARÁMETRO A CADA MÉTODO ANTERIOR */
    private void validarDatos(String nombre) throws ErrorService {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorService("EL NOMBRE DEL AUTOR NO PUEDE SER NULO NI ESTAR VACÍO");
        }
    }
}
