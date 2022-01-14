package SPRING.Ejercicio_01_libreria.Repositorios;

import SPRING.Ejercicio_01_libreria.Entidades.Autor;
import SPRING.Ejercicio_01_libreria.Entidades.Editorial;
import SPRING.Ejercicio_01_libreria.Entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {

    @Query("SELECT l FROM Libro l ORDER BY l.titulo ASC")
    public List<Libro> ListarLibros();

    @Query("SELECT l FROM Libro l WHERE l.id = :id")
    public Libro buscarPorId(@Param("id") String id);

    @Query("SELECT l FROM Libro l WHERE l.isbn= :isbn")
    public Libro buscarporIsbn(@Param("isbn") Long isbn);

    @Query("SELECT l FROM Libro l WHERE l.autor= :autor")
    public List<Libro> buscarLibroporAutor(@Param("autor") Autor autor);

    @Query("SELECT l FROM Libro l WHERE l.editorial= :editorial")
    public List<Libro> buscarLibroporEditorial(@Param("editorial") Editorial editorial);
}
