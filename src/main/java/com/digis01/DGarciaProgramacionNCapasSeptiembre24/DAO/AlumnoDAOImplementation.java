/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.AlumnoDireccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Direccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // manejando logica de base datos
public class AlumnoDAOImplementation implements IAlumnoDAO {

    @Autowired  // inyección de dependencias
    private JdbcTemplate jdbcTemplate; // data-jdbc

    @Autowired
    private EntityManager entityManager; // data-jpa

    @Override
    public Result GetAll() {
        Result result = new Result();
        /* lógica para traer alumnos y sus direcciones */
        try {
            List<AlumnoDireccion> listaAlumnoDireccion = jdbcTemplate.execute("{CALL AlumnoGetAll(?)}",
                    (CallableStatementCallback<List<AlumnoDireccion>>) callableStatement -> {
                        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                        callableStatement.execute();
                        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                        List<AlumnoDireccion> listaAlumnos = new ArrayList<>();
                        AlumnoRowMapper alumnoRowMapper = new AlumnoRowMapper();
                        while (resultSet.next()) {
                            listaAlumnos.add(alumnoRowMapper.mapRow(resultSet, resultSet.getRow()));
                            // AlumnoDireccion alumnoDireccion = alumnoRowMapper.
                            // return alumnoRowMapper.map 
                        }
                        return listaAlumnos;
                    });

            result.correct = true;
            result.object = listaAlumnoDireccion;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result GetAllBusqueda(Alumno alumnoBusqueda) {

        Result result = new Result();

        String queryBusqueda = "SELECT * FROM ViewAlumnoDireccion WHERE UPPER(NombreAlumno) LIKE UPPER(?)";

        if (alumnoBusqueda.Semestre.getIdSemestre() != 0) {
            queryBusqueda += " AND IdSemestre = " + alumnoBusqueda.Semestre.getIdSemestre();
        }

        List<AlumnoDireccion> alumnosDireccion = jdbcTemplate.query(queryBusqueda,
                new AlumnoRowMapper(),
                "%" + alumnoBusqueda.getNombre() + "%");

        result.object = alumnosDireccion;
        result.correct = true;

        return result;
    }

    @Override
    public Result Add(AlumnoDireccion alumnoDireccion) {

        // callable -> stored procedure
        //              -- mandar parametros o preparar la consulta
        // aquí llega el parametro nuevo de imagen 
        Result result = new Result();
        /* lógica para traer alumnos y sus direcciones */
        try {
            int rowAffected = jdbcTemplate.execute("{CALL AlumnoAdd(?,?,?,?,?,?,?,?,?,?,?,?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {
                        callableStatement.setString(1, alumnoDireccion.Alumno.getNombre());
                        callableStatement.setString(2, alumnoDireccion.Alumno.getApellido());
                        callableStatement.setDate(3, new java.sql.Date(alumnoDireccion.Alumno.getFechaNacimiento().getTime()));
                        callableStatement.setString(4, alumnoDireccion.Alumno.getUserName());
                        callableStatement.setString(5, alumnoDireccion.Alumno.getEmail());
                        callableStatement.setString(6, alumnoDireccion.Alumno.getPassword());
                        callableStatement.setInt(7, alumnoDireccion.Alumno.Semestre.getIdSemestre());
                        callableStatement.setString(8, alumnoDireccion.Alumno.getImagen());
                        callableStatement.setString(8, alumnoDireccion.Alumno.getImagen());
                        callableStatement.execute();
                        return 0;
                    });

            result.correct = true;
            result.object = null;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;

    }

    @Override
    public Result GetAllJPA() {
        // JPQL

        Result result = new Result();

        TypedQuery<com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Alumno> queryAlumnos = entityManager.createQuery("FROM Alumno", com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Alumno.class);
        List<com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Alumno> listaAlumnos = queryAlumnos.getResultList();
        List<AlumnoDireccion> listaAlumnoDireccion = new ArrayList<>();

        for (com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Alumno alumnoJPA : listaAlumnos) {
            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
            alumnoDireccion.Alumno = new Alumno(alumnoJPA);

            try {
                TypedQuery<com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Alumno.IdAlumno = :pIdAlumno", com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Direccion.class);
                queryDireccion.setParameter("pIdAlumno", alumnoJPA.getIdAlumno());
                com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Direccion direccionJPA = queryDireccion.getSingleResult();

                alumnoDireccion.Direccion = new Direccion(direccionJPA);
            } catch (Exception ex) {
                continue;
            } finally {
                listaAlumnoDireccion.add(alumnoDireccion);
            }
        }

        result.correct = true;
        result.object = listaAlumnoDireccion;

        return result;
    }

    //inserción doble,  generaba una inserción de alumno y otra de direccion 
    // JPA - > ML -- contructor en clase de ML
    // ML -> JPA -- constructor en clase de JPA

    @Override
    @Transactional
    public Result AddJPA(AlumnoDireccion alumnoDireccion) {

        com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Alumno alumno = new com.digis01.DGarciaProgramacionNCapasSeptiembre24.JPA.Alumno(alumnoDireccion.Alumno);

        entityManager.persist(alumno);
        
        //generar un dirección JPA
        // direccion.Usuario = 
        // entityManager.merge
        
//        eliminar
        // entityManager.remove

        return null;

    }
}
