/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.AlumnoDireccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Result;

public interface IAlumnoDAO {
    
    Result GetAll(); // Métodos abstractos
    
    Result GetAllBusqueda(Alumno alumnoBusqueda);
    
    Result Add(AlumnoDireccion alumnoDireccion); 
    
    // metodos jpa 
    Result GetAllJPA();
    
    Result AddJPA(AlumnoDireccion alumnoDireccion);
    
    Result GetByIdJPA(int idAlumno);
    
    Result UpdateJPA(AlumnoDireccion alumnoDireccion);
}
