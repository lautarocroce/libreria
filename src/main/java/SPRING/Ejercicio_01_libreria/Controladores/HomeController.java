/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPRING.Ejercicio_01_libreria.Controladores;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Homero
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mensaje", "HOY ES : ");
        model.addAttribute("fecha", new Date());
        return "index.html";
    }
}
