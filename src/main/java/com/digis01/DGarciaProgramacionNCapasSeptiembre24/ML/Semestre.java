/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML;

/**
 *
 * @author ALIEN 34
 */
public class Semestre {
 
    private int IdSemestre;
    private String Nombre;

    public Semestre() {
    }
    

    public Semestre(int IdSemestre, String Nombre) {
        this.IdSemestre = IdSemestre;
        this.Nombre = Nombre;
    }

    public int getIdSemestre() {
        return IdSemestre;
    }

    public void setIdSemestre(int IdSemestre) {
        this.IdSemestre = IdSemestre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
}
