/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24;

import com.digis01.DGarciaProgramacionNCapasSeptiembre24.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.AlumnoDireccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Colonia;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Direccion;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Result;
import com.digis01.DGarciaProgramacionNCapasSeptiembre24.ML.Semestre;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author ALIEN 34
 */
@SpringBootTest
public class AlumnoDAOImplementationTest {

//    @Mock
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;

//    @BeforeEach
//    public void setUp() {
//        // Inicializar los mocks manualmente
//        MockitoAnnotations.openMocks(this);
//    }
    
    @Test
    public void testAdd() {
        // Create a mock AlumnoDireccion object
        AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
        Alumno alumno = new Alumno();
        alumno.setNombre("John");
        alumno.setApellido("Doe");
        alumno.setFechaNacimiento(new Date());
        alumno.setUserName("jjs");
        alumno.setEmail("jjj");
        alumno.setPassword("jjs123");
        alumno.Semestre = new Semestre ();
        alumno.Semestre.setIdSemestre(1);
        
        alumnoDireccion.Alumno = alumno;
        
        
        alumnoDireccion.Direccion = new Direccion();
        alumnoDireccion.Direccion.setCalle("test");
        alumnoDireccion.Direccion.setNumeroInterior("test");
        alumnoDireccion.Direccion.setNumeroExterior("test");
        alumnoDireccion.Direccion.Colonia = new Colonia();
        alumnoDireccion.Direccion.Colonia.setIdColonia(1);
        

        // Mock the result of jdbcTemplate.execute
//        when(jdbcTemplate.execute(anyString(), ArgumentMatchers.<CallableStatementCallback<Integer>>any())).thenReturn(0);

        // Execute the method
        Result result = alumnoDAOImplementation.Add(alumnoDireccion);

        // Assertions
        assertNotNull(result);
        assertTrue(result.correct);
        assertNull(result.object);

        // Verify interactions
//        verify(jdbcTemplate).execute(anyString(), any(CallableStatementCallback.class));
    }
}

