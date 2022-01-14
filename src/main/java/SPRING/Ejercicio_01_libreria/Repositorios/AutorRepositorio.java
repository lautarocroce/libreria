package SPRING.Ejercicio_01_libreria.Repositorios;

import SPRING.Ejercicio_01_libreria.Entidades.Autor;
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
public interface AutorRepositorio extends JpaRepository<Autor, String> {

    @Query("SELECT c FROM Autor c ORDER BY c.nombre")
    public List<Autor> listarAutores();

    @Query("SELECT c from Autor c WHERE c.alta = true ORDER BY c.nombre ASC")
    public List<Autor> listarAltas();

    @Query("SELECT c from Autor c WHERE c.alta = false ORDER BY c.nombre ASC")
    public List<Autor> listarBajas();

    @Query("SELECT c FROM Autor c WHERE c.id = :id")
    public Autor buscarPorId(@Param("id") String id);
    
    @Query("SELECT c FROM Autor c WHERE c.nombre = :nombre")
    public Autor buscarAutorPorNombre(@Param("nombre") String nombre);

}
