/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.Controller;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Alumno;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ALIEN 34
 */
@Controller
@RequestMapping("/CargaMasiva")
public class CargaMasivaController {
    
    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;            
    
    @GetMapping
    public String GetView() {
        return "CargaMasivaIndex";
    }
    
    @PostMapping
    public String SetView(@RequestParam MultipartFile archivo) { // aquí llegan txt o xlsx
        
        // validar que extensión tiene mi archivo
        
        // SI TXT  hacer tal 
        try {
            InputStream inputStream = archivo.getInputStream();
            BufferedReader bufferedRead = new BufferedReader(new InputStreamReader(inputStream));
            
            String linea = "";
            
            while ((linea = bufferedRead.readLine()) != null) {
                
                String[] campos = linea.split("\\|");
                
                Alumno alumno = new Alumno();
                alumno.setNombre(campos[0]);
                alumno.setApellido(campos[1]);
                
//                alumno.Semestre = new Semestre ();
//                alumno.Semestre.setIdSemestre(campos[7]);
                
                // add
                
                
//                alumnoDAOImplementation.Add(alumno)
                
                
            }
            
        } catch (Exception ex) {
            
        }
            
            
        
        
        return "";
    }
    
}
