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
import br.edu.ifrs.prog.projeto.base.entities.City;
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
public class CityDaoImpl extends GenericDao<City, Integer> {

    public CityDaoImpl(Connection conn) {
        super(conn);

    }

    @Override
    public Integer addRow(City c) throws SQLException {

        PreparedStatement prepStmt = this.getConnection().prepareStatement("insert into city(name) values (?)", PreparedStatement.RETURN_GENERATED_KEYS);

        prepStmt.setString(1, c.getName());
        prepStmt.execute();

        if (prepStmt.getGeneratedKeys().next()) {
            c.setId(prepStmt.getGeneratedKeys().getInt(1));
        }

        prepStmt.close();
        return c.getId();

    }

    @Override
    public void removeRow(City object) throws SQLException {
        PreparedStatement prepStmt = this.getConnection().prepareStatement("delete from city where id = ?");
        prepStmt.setInt(1, object.getId());
        prepStmt.execute();
        prepStmt.close();
    }

    @Override
    public void updateRow(City object) throws SQLException {
        PreparedStatement prepStmt = this.getConnection().prepareStatement("update city set name = ? where id = ?");
        prepStmt.setString(1, object.getName());
        prepStmt.setInt(2, object.getId());
        prepStmt.execute();
        prepStmt.close();
    }

    @Override
    public List<City> getAllRows(RowMapper<City> mapper) throws SQLException {
        PreparedStatement prepStmt = this.getConnection().prepareStatement("select * from city order by name");
        ResultSet rs = prepStmt.executeQuery();
        ArrayList<City> pList = new ArrayList<>();
        while (rs.next()) {
            pList.add(mapper.map(rs));
        }
        prepStmt.close();
        return pList;
    }

    @Override
    public City getRowById(Integer key, RowMapper<City> mapper) throws SQLException {
        PreparedStatement prepStmt = this.getConnection().prepareStatement("select * from city where id = ?");
        prepStmt.setInt(1, key);
        ResultSet rs = prepStmt.executeQuery();
        rs.next();
        return mapper.map(rs);
    }
}
