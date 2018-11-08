/*
 * The MIT License
 *
 * Copyright 2018 diego.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package br.edu.ifrs.prog.projeto.base.dao.impl;

import br.edu.ifrs.prog.projeto.base.connection.DatabaseConnection;
import br.edu.ifrs.prog.projeto.base.dao.impl.mapper.PersonMapper;
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
                           (new PersonMapper())::map)); 
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
