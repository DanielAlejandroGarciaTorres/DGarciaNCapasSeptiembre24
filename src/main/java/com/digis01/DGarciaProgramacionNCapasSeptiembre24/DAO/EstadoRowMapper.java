/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Estado;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ALIEN 34
 */
public class EstadoRowMapper implements RowMapper<Estado>{

    @Override
    public Estado mapRow(ResultSet rs, int rowNum) throws SQLException {
        Estado estado = new Estado();
        
        estado.setIdEstado(rs.getInt("IdEstado"));
        estado.setNombre(rs.getString("Nombre"));
        
        return estado;
    }
    
}
