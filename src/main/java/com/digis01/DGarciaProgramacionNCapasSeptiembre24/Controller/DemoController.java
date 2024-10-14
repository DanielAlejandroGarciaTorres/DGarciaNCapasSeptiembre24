/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Demo")// define la ruta base del controllador
public class DemoController {

    @GetMapping
    public String Saludar(Model model){
        model.addAttribute("Saludo", "Hola Alexis");
        return "Hola"; // template hola.html
    }
    
}
