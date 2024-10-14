/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.AlumnoDireccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Colonia;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Direccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Estado;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Municipio;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Semestre;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ALIEN 34
 */
class AlumnoRowMapper implements RowMapper<AlumnoDireccion>{

    @Override
    public AlumnoDireccion mapRow(ResultSet rs, int rowNum) throws SQLException {
        AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
        alumnoDireccion.Alumno = new Alumno();
        alumnoDireccion.Alumno.setIdAlumno(rs.getInt("IdAlumno"));
        alumnoDireccion.Alumno.setNombre(rs.getString("NombreAlumno"));
//        alumnoDireccion.Alumno.setFechaNacimiento(rs.getString(""));
        alumnoDireccion.Alumno.setUserName(rs.getString("UserName"));
        alumnoDireccion.Alumno.setEmail(rs.getString("Email"));
        alumnoDireccion.Alumno.setPassword(rs.getString("Password"));
        alumnoDireccion.Alumno.Semestre = new Semestre();
        alumnoDireccion.Alumno.Semestre.setIdSemestre(rs.getInt("IdSemestre"));
        alumnoDireccion.Alumno.Semestre.setNombre(rs.getString("NombreSemestre"));
        
        alumnoDireccion.Direccion = new Direccion();
        alumnoDireccion.Direccion.setIdDireccion(rs.getInt("IdDireccion"));
        alumnoDireccion.Direccion.setCalle(rs.getString("Calle"));
        alumnoDireccion.Direccion.setNumeroInterior(rs.getString("NumeroInterior"));
        alumnoDireccion.Direccion.setNumeroExterior(rs.getString("NumeroExterior"));
        alumnoDireccion.Direccion.Colonia = new Colonia();
        alumnoDireccion.Direccion.Colonia.setIdColonia(rs.getInt("IdColonia"));
        alumnoDireccion.Direccion.Colonia.setNombre(rs.getString("NombreColonia"));
        alumnoDireccion.Direccion.Colonia.setCodigoPostal(rs.getString("CodigoPostal"));
        alumnoDireccion.Direccion.Colonia.Municipio = new Municipio();
        alumnoDireccion.Direccion.Colonia.Municipio.setIdMunicipio(rs.getInt("IdMunicipio"));
        alumnoDireccion.Direccion.Colonia.Municipio.setNombre(rs.getString("NombreMunicipio"));
        alumnoDireccion.Direccion.Colonia.Municipio.Estado = new Estado();
        alumnoDireccion.Direccion.Colonia.Municipio.Estado.setIdEstado(rs.getInt("IdEstado"));
        alumnoDireccion.Direccion.Colonia.Municipio.Estado.setNombre(rs.getString("NombreEstado"));
           
        
        
        return alumnoDireccion;
    }
    
}
