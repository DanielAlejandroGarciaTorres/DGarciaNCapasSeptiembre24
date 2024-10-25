/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.RestController;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Semestre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demoapi")
public class DemoRestController {
    
    @GetMapping
    public Alumno HolaMundo(){
        
        Alumno alumno =  new Alumno();
        alumno.setNombre("Alexis");
        alumno.setApellido("Cifuentes");
        alumno.Semestre = new Semestre(1, "1er Semestre");
        return alumno;
    }
    // vista vacía Andres
    // vista Alexis, Brenda, Ramón, Aldo, Francisco
    // error antonio
    // JSON  Luis
    // Texto
    
    
    
    /*
        
        operaciones de una calculadora
        - sumar - post - recibe infor por cuerpo 
        - resta - GET requestParam
        - multiplicacion - GET  requestParam
        - potencia - GET Pathvariable 
    
        - Crear un objeto y retonar su información 
            - get 
    
    */
}
