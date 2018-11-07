/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.prog.projeto.base.dao.impl;

import br.edu.ifrs.prog.projeto.base.dao.GenericDao;
import br.edu.ifrs.prog.projeto.base.dao.RowMapper;
import br.edu.ifrs.prog.projeto.base.entities.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author diego
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRow(City object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<City> getAllRows(RowMapper<City> mapper) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public City getRowById(Integer key,RowMapper<City> mapper) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
