/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.Controller;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO.EstadoDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO.MunicipioDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO.SemestreDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.AlumnoDireccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Colonia;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Direccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Estado;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Municipio;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Result;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Semestre;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Alumno") // definir una ruta base
public class AlumnoController {

    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;

    @Autowired
    private SemestreDAOImplementation semestreDAOImplementation;

    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;

    @GetMapping
    public String GetAll(Model model) {
        Result result = alumnoDAOImplementation.GetAll();

        Alumno alumnoBusqueda = new Alumno();
        alumnoBusqueda.Semestre = new Semestre();
        
        model.addAttribute("alumnoBusqueda", alumnoBusqueda);
        
        List<Semestre> semestres = new ArrayList<>();
        semestres.add(new Semestre(1, "1er Semestre"));
        semestres.add(new Semestre(2, "2do Semestre"));
        semestres.add(new Semestre(3, "3er Semestre"));

        model.addAttribute("semestres", semestres);
        
        model.addAttribute("AlumnosDireccion", (List<AlumnoDireccion>) result.object);

        return "AlumnoIndex";

    }

    @PostMapping
    public String GetAll(@ModelAttribute Alumno alumnoBusqueda, Model model){
        
        model.addAttribute("alumnoBusqueda", alumnoBusqueda);
        
        List<Semestre> semestres = new ArrayList<>();
        semestres.add(new Semestre(1, "1er Semestre"));
        semestres.add(new Semestre(2, "2do Semestre"));
        semestres.add(new Semestre(3, "3er Semestre"));

        model.addAttribute("semestres", semestres);
        
        
        Result result = alumnoDAOImplementation.GetAllBusqueda(alumnoBusqueda);
        
        model.addAttribute("AlumnosDireccion", (List<AlumnoDireccion>) result.object);
        
        return "AlumnoIndex";
    }
    
    // GET (url, a) - Preparar una vista  
    // POST (Formulario) - Envío de infomación 
    @GetMapping("/modify/{IdAlumno}")
    public String AddUpdate(@PathVariable int IdAlumno, Model model) {

        List<Semestre> semestres = new ArrayList<>();
        semestres.add(new Semestre(1, "1er Semestre"));
        semestres.add(new Semestre(2, "2do Semestre"));
        semestres.add(new Semestre(3, "3er Semestre"));

        model.addAttribute("semestres", semestres);
        model.addAttribute("estados", (List<Estado>) estadoDAOImplementation.GetAll().object);

//        model.addAttribute("semestres", (List<Semestre>) semestreDAOImplementation.GetAll().object);
        if (IdAlumno == 0) { // Agregar
            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
            alumnoDireccion.Alumno = new Alumno();
            alumnoDireccion.Alumno.Semestre = new Semestre();
            alumnoDireccion.Direccion = new Direccion();
            alumnoDireccion.Direccion.Colonia = new Colonia();

            model.addAttribute("alumnoDireccion", alumnoDireccion);
        } else { //actualizar
            
            // alumnoDAOImplentation.getById(idAlumno); - Result - object = AlumnoDireccion
            
            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
            alumnoDireccion.Alumno = new Alumno();
            alumnoDireccion.Alumno.setIdAlumno(13);
            alumnoDireccion.Alumno.setNombre("Aldo");
            alumnoDireccion.Alumno.Semestre = new Semestre();
            alumnoDireccion.Alumno.Semestre.setIdSemestre(2);
            alumnoDireccion.Alumno.Semestre.setNombre("2do Semestre");
            
            alumnoDireccion.Direccion = new Direccion();
            alumnoDireccion.Direccion.Colonia = new Colonia();
//            model.addAttribute("alumnoDireccion", alumnoDireccion);
//            model.addAttribute("Estados", estadoDAOImplementation.getByIdPais(usuarioDireccion..Direccion.Colonia.Municipio.Estado.Pais.getIdPais()).object);
//            model.addAtribute("Municipio",municipioDAOImplementation.getMunicipioByIdEstado(usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado()).object);
            // SP GEtBYID
            // alumno.IdALumno -> 6,7,48

        }
        return "AlumnoForm";
    }

    @PostMapping("/form")
    public String AddUpdate(@ModelAttribute AlumnoDireccion alumnoDireccion,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        Result result = new Result();

        try {
            if (!imagenFile.isEmpty()) {
                byte[] bytes = imagenFile.getBytes();
                String imagenBase64 = Base64.getEncoder().encodeToString(bytes);
                alumnoDireccion.Alumno.setImagen(imagenBase64);
                
                
                //Siempre manden una imagen -- lograr la incersion
                //  Poner una imagen por defecto en el formulario
                //  si lo que recupero es la imagen por defecto 
                        // no almaceno la imagen por defecto en la base de datos
                      
                //UPDATE
                    // si el usuario tiene una imagen 
                        // mostrar la imagen asignada y no la de por defecto 
                
            }
        } catch (Exception ex) {

        }

        if (alumnoDireccion.Alumno.getIdAlumno() == 0) { // Agregar
            result = alumnoDAOImplementation.Add(alumnoDireccion);
        } else {
            // crear un metodo para actualizar    
        }

        return "nada";// redireccionar al metodo de get all
    }

    @GetMapping("/getMunicipioByEstado")
    @ResponseBody // retorna información
    public List<Municipio> GetMunicipioByEstado(@RequestParam("IdEstado") int idEstado) {

        List<Municipio> municipios = (List<Municipio>) municipioDAOImplementation.GetByIdEstado(idEstado).object;

        return municipios;
    }

}
