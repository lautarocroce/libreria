package SPRING.Ejercicio_01_libreria.Controladores;

import SPRING.Ejercicio_01_libreria.Entidades.Editorial;
import SPRING.Ejercicio_01_libreria.Errores.ErrorService;
import SPRING.Ejercicio_01_libreria.Servicios.EditorialService;
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
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/Editorial_Menu")
    public String editorial() {
        return "Editorial_Menu.html";
    }

    @GetMapping("/Editorial_Cargar")
    public String cargarEditorial() {
        return "Editorial_Cargar.html";
    }

    @PostMapping("/Editorial_Cargar")
    public String cargar(ModelMap modelo, @RequestParam String nombre, RedirectAttributes redirectAttributes) {
        try {
            editorialService.cargarEditorial(nombre);
            modelo.put("exito", "Registro exitoso");
            redirectAttributes.addFlashAttribute("exito", "La editorial fué cargada de manera exitosa");
            return "redirect:/editorial/Editorial_Cargar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al cargar la editorial");
            return "redirect:/editorial/Editorial_Cargar";
        }
    }

    @GetMapping("/Editorial_Lista")
    public String mostrarEditoriales(ModelMap modelo) {
        List<Editorial> listaEditoriales = editorialService.mostarEditoriales();
        modelo.addAttribute("editoriales", listaEditoriales);
        return "Editorial_Lista.html";
    }

    @GetMapping("/Editorial_Alta/{id}")
    public String altaEditorial(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            editorialService.altaEditorial(id);
            redirectAttributes.addFlashAttribute("exito", "La editorial fué dada de alta de manera exitosa");
            return "redirect:/editorial/Editorial_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al dar de alta la editorial");
             return "redirect:/editorial/Editorial_Lista";
        }
    }

    @GetMapping("/Editorial_Baja/{id}")
    public String bajaEditorial(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            editorialService.bajaEditorial(id);
            redirectAttributes.addFlashAttribute("exito", "La editorial fué dada de baja de manera exitosa");
            return "redirect:/editorial/Editorial_Lista";
        } catch (Exception e) {
             redirectAttributes.addFlashAttribute("error", "Hubo un error al dar de baja la editorial");
             return "redirect:/editorial/Editorial_Lista";
        }
    }

    @GetMapping("/Editorial_Modificar/{id}")
    public String modificarEditorial(ModelMap modelo, @PathVariable String id) throws ErrorService {
        modelo.put("editorial", editorialService.mostarEditorial(id));
        return "Editorial_Modificar.html";
    }

    @PostMapping("/Editorial_Modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre, RedirectAttributes redirectAttributes) {
        try {
            editorialService.modificarEditorial(id, nombre);
            modelo.put("exito", "Modificación exitosa");
            redirectAttributes.addFlashAttribute("exito", "La editorial fué modificada de manera exitosa");
            return "redirect:/editorial/Editorial_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al modificar la editorial");
             return "redirect:/editorial/Editorial_Lista";
        }
    }

    @GetMapping("/Editorial_Eliminar/{id}")
    public String editorialEliminar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            editorialService.eliminarEditorial(id);
             redirectAttributes.addFlashAttribute("exito", "La editorial fué eliminada de manera exitosa");
            return "redirect:/editorial/Editorial_Lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al eliminar la editorial o la editorial está asociada a un libro");
            return "redirect:/editorial/Editorial_Lista";
        }
    }
}
