/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Municipio;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ALIEN 34
 */
class MunicipioRowMapper implements RowMapper<Municipio>{

    @Override
    public Municipio mapRow(ResultSet rs, int rowNum) throws SQLException {
        Municipio municipio = new Municipio();
        
        municipio.setIdMunicipio(rs.getInt("IdMunicipio"));
        municipio.setNombre(rs.getString("Nombre"));
        
        return municipio;
    }
    
}
