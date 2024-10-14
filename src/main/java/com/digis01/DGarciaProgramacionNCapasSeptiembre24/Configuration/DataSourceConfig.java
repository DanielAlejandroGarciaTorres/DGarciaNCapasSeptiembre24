/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasSeptiembre24.Configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
    
    @Bean // Configuraciones personalizadas 
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUsername("DGarciaProgramacionNCapasSep2024");
        dataSource.setPassword("password1");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
        
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate (DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
