/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPRING.Ejercicio_01_libreria.Repositorios;

import SPRING.Ejercicio_01_libreria.Entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Homero
 */
@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

    @Query("SELECT c FROM Editorial c ORDER BY c.nombre")
    public List<Editorial> ListarEditoriales();

    @Query("SELECT c FROM Editorial c WHERE c.id = :id")
    public Editorial buscarPorId(@Param("id") String id);

    @Query("SELECT c from Editorial c WHERE c.alta = true ")
    public List<Editorial> listarAltas();

    @Query("SELECT c from Editorial c WHERE c.alta = false ")
    public List<Editorial> listarBajas();
    
     @Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
    public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);
}
