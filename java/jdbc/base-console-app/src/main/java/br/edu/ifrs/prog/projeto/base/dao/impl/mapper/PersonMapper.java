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
package br.edu.ifrs.prog.projeto.base.dao.impl.mapper;

import br.edu.ifrs.prog.projeto.base.dao.RowMapper;
import br.edu.ifrs.prog.projeto.base.entities.City;
import br.edu.ifrs.prog.projeto.base.entities.Person;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class PersonMapper implements RowMapper<Person>{

    @Override
    public Person map(ResultSet rs) throws SQLException {       
        Person p = new Person();
        p.setBirthDate(rs.getDate("birth_date"));
        p.setHeigth(rs.getFloat("heigth"));
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        City c = new City();
        c.setId(rs.getInt("city_id"));
        c.setName(rs.getString("city_name"));
        p.setCity(c);        
        return p;
    }
    
}
