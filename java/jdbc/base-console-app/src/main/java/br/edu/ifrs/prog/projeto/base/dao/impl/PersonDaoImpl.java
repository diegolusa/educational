/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        PreparedStatement prepStmt = this.getConnection().prepareStatement("select * from person");
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

        PreparedStatement prepStmt = this.getConnection().prepareStatement("select * from person");
        ResultSet rs = prepStmt.executeQuery();
        rs.next();
        return mapper.map(rs);
    }
}
