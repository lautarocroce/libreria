package SPRING.Ejercicio_01_libreria.Controladores;

import SPRING.Ejercicio_01_libreria.Entidades.Autor;
import SPRING.Ejercicio_01_libreria.Entidades.Editorial;
import SPRING.Ejercicio_01_libreria.Entidades.Libro;
import SPRING.Ejercicio_01_libreria.Errores.ErrorService;
import SPRING.Ejercicio_01_libreria.Repositorios.AutorRepositorio;
import SPRING.Ejercicio_01_libreria.Servicios.AutorService;
import SPRING.Ejercicio_01_libreria.Servicios.EditorialService;
import SPRING.Ejercicio_01_libreria.Servicios.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private EditorialService editorialService;
    @Autowired
    private AutorRepositorio autorRepositorio;

//    @Autowired
//    Libro libro;
    @GetMapping("/Libro_Menu")
    public String libro() {
        return "Libro_Menu.html";
    }

    @GetMapping("/Libro_Cargar")
    public String cargarLibro(ModelMap modelo) {

        List<Autor> listaAutores = autorService.mostrarAutores();
        modelo.put("autores", listaAutores);

        List<Editorial> listaEditoriales = editorialService.mostarEditoriales();
        modelo.addAttribute("editoriales", listaEditoriales);

        return "Libro_Cargar.html";
    }

    @PostMapping("/Libro_Cargar")
    public String cargar(ModelMap modelo, @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer ano, @RequestParam(required = false) Integer ejemplares,
            @RequestParam(required = false) String idAutor, @RequestParam(required = false) String idEditorial,
            @RequestParam(required = false) byte[] foto, RedirectAttributes redirectAttributes) {
        try {
            libroService.cargarLibro(titulo, ano, ejemplares, idAutor, idEditorial, foto);
            redirectAttributes.addFlashAttribute("exito", "El libro fué cargado de manera exitosa");
            return "redirect:/libro/Libro_Cargar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al cargar el libro");
            return "redirect:/libro/Libro_Cargar";
        }
    }

    @GetMapping("/Libro_Lista")
    public String mostrarLibros(ModelMap modelo) {
        List<Libro> listaLibros = libroService.mostarLibros();
        modelo.addAttribute("libros", listaLibros);
        return "Libro_Lista.html";
    }

    @GetMapping("/Libro_Alta/{id}")
    public String altaLibro(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            libroService.altaLibro(id);
            redirectAttributes.addFlashAttribute("exito", "El libro fué dado de alta de manera exitosa");
            return "redirect:/libro/Libro_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al dar de alta el libro");
            return "redirect:/libro/Libro_Lista";
        }
    }

    @GetMapping("/Libro_Baja/{id}")
    public String bajaLibro(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            libroService.bajaLibro(id);
            redirectAttributes.addFlashAttribute("exito", "El libro fué dado de baja de manera exitosa");
            return "redirect:/libro/Libro_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al dar de baja el autor");
            return "redirect:/libro/Libro_Lista";
        }
    }

    @GetMapping("/Libro_Modificar/{id}")
    public String modificarLibro(ModelMap modelo, @PathVariable String id) throws ErrorService {

        modelo.put("libro", libroService.mostarLibro(id));

        return "Libro_Modificar.html";
    }

    @PostMapping("/Libro_Modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam long isbn, @RequestParam String titulo,
            @RequestParam Integer ano, @RequestParam Integer ejemplares, RedirectAttributes redirectAttributes) {

        try {
            libroService.modificarLibro(id, isbn, titulo, ano, ejemplares);
            redirectAttributes.addFlashAttribute("exito", "El libro fué modificado de manera exitosa");
            return "redirect:/libro/Libro_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al modificar el libro");
            return "redirect:/libro/Libro_Lista";
        }
    }

    @GetMapping("/Libro_Eliminar/{id}")
    public String libroEliminar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            libroService.eliminarLibro(id);
            redirectAttributes.addFlashAttribute("exito", "El libro fué eliminado de manera exitosa");
            return "redirect:/libro/Libro_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al eliminar el libro");
            return "redirect:/libro/Libro_Lista";
        }
    }

    @GetMapping("/Autor_Lista_Seleccion")
    public String seleccionAutores(ModelMap modelo) {
        List<Autor> listaAutores = autorService.mostrarAltas();
        modelo.addAttribute("autores", listaAutores);
        return "Autor_Lista_Seleccion.html";
    }

    @GetMapping("/Autor_Seleccionado/{id}")
    public String autorSeleccioinado(@PathVariable String id, RedirectAttributes redirectAttributes) {

        try {
            autorService.mostarAutor(id);
//            autorRepositorio.buscarPorId(id);
//            libro.setAutor((Autor) autorRepositorio);
            redirectAttributes.addFlashAttribute("exito", "Selección del Autor Exitosa");
            return "redirect:/libro/Libro_Cargar";
        } catch (Exception e) {
            return "/libro/Libro_Menu.html";
        }
    }
}
