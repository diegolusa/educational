/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.prog.projeto.base.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class GenericDao<T,K> {
    private Connection conn;
    
    public GenericDao(Connection conn){
        this.conn = conn;
    }
   

    protected Connection getConnection(){
        return this.conn;
    }
    
    public abstract K addRow(T object) throws SQLException;
    public abstract void removeRow(T object) throws SQLException;
    public abstract void updateRow(T object) throws SQLException;
    public abstract List<T> getAllRows(RowMapper<T> mapper) throws SQLException;
    public abstract T getRowById(K key,RowMapper<T> mapper) throws SQLException;
}
