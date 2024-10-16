/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML;

/**
 *
 * @author ALIEN 34
 */
public class Direccion {
    
    private int IdDireccion;
    private String Calle;
    private String NumeroInterior;
    private String NumeroExterior;
    public Colonia Colonia; // prop navigation 

    public Direccion() {
    }

    public Direccion(com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Direccion direccionJPA) {
        this.IdDireccion = direccionJPA.getIdDireccion();
        this.Calle = direccionJPA.getCalle();
        this.NumeroInterior = direccionJPA.getNumeroInterior();
        this.NumeroExterior = direccionJPA.getNumeroExterior();
        this.Colonia = new Colonia();
        this.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
    }
    
    
    

    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public void setNumeroInterior(String NumeroInterior) {
        this.NumeroInterior = NumeroInterior;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }

    public Colonia getColonia() {
        return Colonia;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }

    
}
