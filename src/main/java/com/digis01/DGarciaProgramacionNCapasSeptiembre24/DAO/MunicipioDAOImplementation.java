/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Estado;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Municipio;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Result;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // logica de base de datos
public class MunicipioDAOImplementation implements IMunicipioDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result GetByIdEstado(int idEstado) {
        Result result = new Result();
        /* lógica para traer alumnos y sus direcciones */
        try {
            List<Municipio> listaMunicipios = jdbcTemplate.execute("{CALL MunicipioGetByIdEstado(?,?)}", 
                    (CallableStatementCallback<List<Municipio>>) callableStatement -> {
                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.setInt(2, idEstado);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                List<Municipio> listaMunicipio = new ArrayList<>();
                MunicipioRowMapper municipioRowMapper = new MunicipioRowMapper();
                while (resultSet.next()) {
                    listaMunicipio.add(municipioRowMapper.mapRow(resultSet, resultSet.getRow()));
                    // AlumnoDireccion alumnoDireccion = alumnoRowMapper.
                    // return alumnoRowMapper.map 
                }
                return listaMunicipio;
            });
            
            result.correct = true;
            result.object = listaMunicipios;
            
        } catch (Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
        
    }
    
}
