/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.AlumnoDireccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Estado;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Result;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALIEN 34
 */
@Repository
public class EstadoDAOImplementation  implements IEstadoDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result GetAll() {
        
        Result result = new Result();
        /* lógica para traer alumnos y sus direcciones */
        try {
            List<Estado> listaAlumnoDireccion = jdbcTemplate.execute("{CALL EstadoGetAll(?)}", 
                    (CallableStatementCallback<List<Estado>>) callableStatement -> {
                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                List<Estado> listaEstados = new ArrayList<>();
                EstadoRowMapper estadoRowMapper = new EstadoRowMapper();
                while (resultSet.next()) {
                    listaEstados.add(estadoRowMapper.mapRow(resultSet, resultSet.getRow()));
                    // AlumnoDireccion alumnoDireccion = alumnoRowMapper.
                    // return alumnoRowMapper.map 
                }
                return listaEstados;
            });
            
            result.correct = true;
            result.object = listaAlumnoDireccion;
            
        } catch (Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
                
    }
 
    
    
}
