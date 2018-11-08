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

import br.edu.ifrs.prog.projeto.base.dao.GenericDao;
import br.edu.ifrs.prog.projeto.base.dao.RowMapper;
import br.edu.ifrs.prog.projeto.base.entities.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego A. Lusa
 */
public class PersonDaoImpl extends GenericDao<Person, Integer> {

    public PersonDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public Integer addRow(Person p) throws SQLException {

        PreparedStatement prepStmt = this.getConnection().prepareStatement("insert into person(name, birth_date,heigth,city_address_id) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

        prepStmt.setString(1, p.getName());
        prepStmt.setDate(2, p.getBirthDate());
        prepStmt.setFloat(3, p.getHeigth());
        prepStmt.setInt(4, p.getCity().getId());
        prepStmt.execute();

        if (prepStmt.getGeneratedKeys().next()) {
            p.setId(prepStmt.getGeneratedKeys().getInt(1));
        }

        prepStmt.close();
        return p.getId();

    }

    @Override
    public void removeRow(Person object) throws SQLException {
        PreparedStatement prepStmt = this.getConnection().prepareStatement("delete from person where id = ?");
        prepStmt.setInt(1, object.getId());
        prepStmt.execute();
        prepStmt.close();
    }

    @Override
    public void updateRow(Person object) throws SQLException {
        PreparedStatement prepStmt = this.getConnection().prepareStatement("update person set name = ?, birth_date = ?,heigth = ?, city_address_id = ? where id = ?");

        prepStmt.setString(1, object.getName());
        prepStmt.setDate(2, object.getBirthDate());
        prepStmt.setFloat(3, object.getHeigth());
        prepStmt.setInt(4, object.getCity().getId());
        prepStmt.setInt(5, object.getId());
        prepStmt.execute();
        prepStmt.close();
    }

    @Override
    public List<Person> getAllRows(RowMapper<Person> mapper) throws SQLException {
        PreparedStatement prepStmt = this.getConnection().prepareStatement("select p.*, c.id as city_id, c.name as city_name from person p join city c on p.city_address_id = c.id order by p.name");
        ResultSet rs = prepStmt.executeQuery();
        ArrayList<Person> pList = new ArrayList<>();
        while (rs.next()) {
            pList.add(mapper.map(rs));
        }
        prepStmt.close();
        return pList;
    }

    @Override
    public Person getRowById(Integer key, RowMapper<Person> mapper) throws SQLException {

        PreparedStatement prepStmt = this.getConnection().prepareStatement("select * from person where id =?");
        prepStmt.setInt(1, key);
        ResultSet rs = prepStmt.executeQuery();        
        rs.next();
        return mapper.map(rs);
    }
}
