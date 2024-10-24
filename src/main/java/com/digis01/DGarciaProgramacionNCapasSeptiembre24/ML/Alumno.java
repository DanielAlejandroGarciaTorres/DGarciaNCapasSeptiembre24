/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ALIEN 34
 */
public class Alumno {
    
    /*atributo  public int numero*/
    
    private int IdAlumno;
    
    @NotNull
    @NotEmpty(message = "No puede estar vacio el campo")
    @Size(min = 3, max = 5, message = "Respeta el rango")
    private String Nombre;
    private String Apellido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    private String UserName;
    private String Email;
    private String Password;
    public Semestre Semestre; // Propiedad de navegación
    private String Imagen;
    private int Status;

    public Alumno() {
    }

    public Alumno(com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Alumno alumnoJPA) {
        this.IdAlumno = alumnoJPA.getIdAlumno();
        this.Nombre = alumnoJPA.getNombre();
        this.Apellido = alumnoJPA.getApellido();
        this.FechaNacimiento = alumnoJPA.getFechaNacimiento();
        this.UserName = alumnoJPA.getUserName();
        this.Email = alumnoJPA.getEmail();
        this.Password = alumnoJPA.getPassword();
        this.Semestre = new Semestre();
        this.Semestre.setIdSemestre(alumnoJPA.Semestre.getIdSemestre());
        this.Imagen = alumnoJPA.getImagen();
        this.Status = alumnoJPA.getStatus();
    }
    
    

    public Alumno(String Nombre, String Apellido, Date FechaNacimiento) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
    }
    
    
    
    public Alumno(int IdAlumno, String Nombre, String Apellido, Date FechaNacimiento) {
        this.IdAlumno = IdAlumno;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
    }

    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int IdAlumno) {
        this.IdAlumno = IdAlumno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Semestre getSemestre() {
        return Semestre;
    }

    public void setSemestre(Semestre Semestre) {
        this.Semestre = Semestre;
    }
    
     public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
    
    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
}
