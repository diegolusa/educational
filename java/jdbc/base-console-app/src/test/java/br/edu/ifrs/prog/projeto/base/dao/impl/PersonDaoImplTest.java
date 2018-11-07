/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.prog.projeto.base.dao.impl;

import br.edu.ifrs.prog.projeto.base.connection.DatabaseConnection;
import br.edu.ifrs.prog.projeto.base.entities.City;
import br.edu.ifrs.prog.projeto.base.entities.Person;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author diego
 */
public class PersonDaoImplTest {

    private Connection conn;
    private City defaultCity;
    private  PersonDaoImpl pDao ;

    @Before
    public void setUp() {
        try {
            this.conn = DatabaseConnection.getNewDefaultConnection();
             this.pDao = new PersonDaoImpl(this.conn);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PersonDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllRows(){
        try {
            ArrayList<Person> pList = new ArrayList<>();
            pList.addAll(
                    this.pDao.getAllRows(
                            (rs) -> {
                                Person p = new Person();
                                p.setId(rs.getInt("id"));
                                p.setName(rs.getString("name"));
                                p.setHeigth(rs.getFloat("heigth"));                                                   p.setBirthDate(rs.getDate("birth_date"));
                                return p;
                                
                            }
                            
                            
                    ));
            System.out.println(pList);
        } catch (SQLException ex) {
            Logger.getLogger(PersonDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testAdd() throws Exception {        
        Person p = new Person(this.defaultCity, "diego", new Date(System.currentTimeMillis()), 2.5f);
        CityDaoImpl cDao = new CityDaoImpl(this.conn);
        City city = new City("Erebanco");
        cDao.addRow(city);

        
        p.setName("Diego 2");
        p.setHeigth(1.8f);
        p.setCity(city);
        p.setBirthDate(new Date(System.currentTimeMillis()));

        Integer id = this.pDao.addRow(p);

        this.conn.commit();

        assertNotNull(id);
        System.out.println(id);
    }

}
