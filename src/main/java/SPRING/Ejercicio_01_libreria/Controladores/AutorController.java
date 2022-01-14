package SPRING.Ejercicio_01_libreria.Controladores;

import SPRING.Ejercicio_01_libreria.Entidades.Autor;
import SPRING.Ejercicio_01_libreria.Errores.ErrorService;
import SPRING.Ejercicio_01_libreria.Servicios.AutorService;
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

/**
 *
 * @author Homero
 */
@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/Autor_Menu")
    public String autor() {
        return "Autor_Menu.html";
    }

    @GetMapping("/Autor_Cargar")
    public String cargarAutor() {
        return "Autor_Cargar.html";
    }

    @PostMapping("/Autor_Cargar")
    public String cargar(ModelMap modelo, @RequestParam String nombre, RedirectAttributes redirectAttributes) {
        try {
            autorService.cargarAutor(nombre);
            modelo.put("exito", "Registro exitoso del Autor");
            redirectAttributes.addFlashAttribute("exito", "El autor fué cargado de manera exitosa");
            return "redirect:/autor/Autor_Cargar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al cargar el autor");            
            return "redirect:/autor/Autor_Cargar";
        }
    }

    @GetMapping("/Autor_Lista")
    public String mostrarAutores(ModelMap modelo) {
        List<Autor> listaAutores = autorService.mostrarAutores();
        modelo.addAttribute("autores", listaAutores);
        return "Autor_Lista.html";
    }

    @GetMapping("/Autor_Alta/{id}")
    public String altaAutor(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            autorService.altaAutor(id);
            redirectAttributes.addFlashAttribute("exito", "El autor fué dado de alta de manera exitosa");
            return "redirect:/autor/Autor_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al dar de alta el autor");
            return "redirect:/autor/Autor_Lista";
        }
    }

    @GetMapping("/Autor_Baja/{id}")
    public String bajaAutor(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            autorService.bajaAutor(id);
            redirectAttributes.addFlashAttribute("exito", "El autor fué dado de baja de manera exitosa");
            return "redirect:/autor/Autor_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Hubo un error al dar de baja el autor");
           return "redirect:/autor/Autor_Lista";
        }
    }

    @GetMapping("/Autor_Modificar/{id}")
    public String modificarAutor(ModelMap modelo, @PathVariable String id) throws ErrorService {
        modelo.put("autor", autorService.mostarAutor(id));
        return "Autor_Modificar.html";
    }

    @PostMapping("/Autor_Modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre, RedirectAttributes redirectAttributes) {
        try {
            autorService.modificarAutor(id, nombre);
            modelo.put("exito", "Modificación exitosa");
            redirectAttributes.addFlashAttribute("exito", "El autor fué modificado de manera exitosa");
            return "redirect:/autor/Autor_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al modificar el autor");
            return "Autor_Menu.html";
        }
    }

    @GetMapping("/Autor_Eliminar/{id}")
    public String autorEliminar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            autorService.eliminarAutor(id);
            redirectAttributes.addFlashAttribute("exito", "El autor fué eliminado de manera exitosa");
            return "redirect:/autor/Autor_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al eliminar el autor o el autor está asociado a un libro");
            return "redirect:/autor/Autor_Lista";
        }
    }
}
