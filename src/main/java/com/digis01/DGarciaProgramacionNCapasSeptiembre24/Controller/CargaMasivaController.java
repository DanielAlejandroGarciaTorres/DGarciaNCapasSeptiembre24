/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.Controller;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.AlumnoDireccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.ResultExcel;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    public String GetView(Model model) {
        model.addAttribute("listaErrores", new ArrayList<>());
        return "CargaMasivaIndex";
    }

    @PostMapping
    public String SetView(@RequestParam MultipartFile archivo, Model model, HttpSession session) throws IOException { // aquí llegan txt o xlsx

        // validar que extensión tiene mi archivo
        if (archivo != null && !archivo.isEmpty()) {
            String extensionArchivo = StringUtils.getFilenameExtension(archivo.getOriginalFilename());

            if (extensionArchivo.equals("txt")) {
                //proceso mi txt
                ProcesarArchivoTXT(archivo);
            } else if (extensionArchivo.equals("xlsx")) {
                // validar información de mi excel
                String root = System.getProperty("user.dir"); // dirección o ruta de mi proyecto
                String path = "src/main/resources/static/archivos/";
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String absolutePath = root + "/" + path + fecha + archivo.getOriginalFilename();
                List<AlumnoDireccion> listaAlumnos = LecturaArchivo(archivo);
                archivo.transferTo(new File(absolutePath)); //  generar una copia o un archivo nuevo como el que me mandaron

                if (!listaAlumnos.isEmpty()) {
                    List<ResultExcel> listaErrores = ValidarDatos(listaAlumnos);

                    if (listaErrores.isEmpty()) {
                        model.addAttribute("archivoCorrecto", true);
                        model.addAttribute("listaErrores", new ArrayList<>());
                        session.setAttribute("pathArchivo", absolutePath); // ya guarde información en el servidor
                    } else {
                        model.addAttribute("archivoCorrecto", false);
                        model.addAttribute("listaErroes", listaErrores);
                    }
                }

                // retorno como si fuera erroneo 
                return "CargaMasivaIndex";
            } else {
                // archivo incorrecto
            }

        }

        // SI TXT  hacer tal 
        return "CargaMasivaIndex";
    }

    @GetMapping("/Procesar")
    public String ProcesarArchivo(HttpSession session){ 
        
        String infoRuta =  session.getAttribute("pathArchivo").toString();
        
        // lectura de archivo por la ruta 
        // leo archivo 
        // empiezo a persistir uno a uno 
        
        
        
        return "";
    }
    // tipo de metodo (GEt, PoST)
    // metodo Prcesar
    // ¿como le hago para recuperar 
    //       la ruta sin usar una variable de global?
    // Darle apertura al archivo
    // Leer archivo
    // recorrer
    // persistir cada uno de los elementos. 
    private void ProcesarArchivoTXT(MultipartFile archivo) {
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
    }

    // try catch (finally) / try with resources / throws / throw 
    private List<AlumnoDireccion> LecturaArchivo(MultipartFile archivo) throws IOException {

        List<AlumnoDireccion> listaAlumno = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(archivo.getInputStream()); // abrir el archivo
        Sheet workSheet = workbook.getSheetAt(0);

        for (Row row : workSheet) {
            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
            alumnoDireccion.Alumno = new Alumno();
            alumnoDireccion.Alumno.setNombre(row.getCell(0).toString());
            alumnoDireccion.Alumno.setFechaNacimiento(row.getCell(3).getDateCellValue());

            // cargo toda mi información
            listaAlumno.add(alumnoDireccion);

        }

        workbook.close();

        return listaAlumno;
    }

    private List<ResultExcel> ValidarDatos(List<AlumnoDireccion> alumnosdireccion) {

        int fila = 1;
        String errorMessage = "";
        List<ResultExcel> listaErrores = new ArrayList<>();

        for (AlumnoDireccion alumnoDireccion : alumnosdireccion) {

            if (alumnoDireccion.Alumno.getNombre().equals("")) {
                errorMessage = "Nombre sin información";
                listaErrores.add(new ResultExcel(fila, errorMessage));
            }

            fila++;
        }

        return listaErrores;
    }

}
