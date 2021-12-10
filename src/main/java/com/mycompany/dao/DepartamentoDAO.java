/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.idao.IDAO;
import com.mycompany.modelo.Departamento;
import com.mycompany.resources.Conexion;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class DepartamentoDAO implements IDAO<Departamento> {

    private ResultSet rs;
    private PreparedStatement ps;
    Departamento depa = new Departamento();
    
    @Override
    public boolean guardar(Departamento pojo) {
        String insert = "INSERT INTO departamento (id,nombre) VALUES (?,?)";
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            ps.setLong(1, pojo.getId());
            ps.setString(2, pojo.getNombre());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean actualizar(Departamento pojo) {
        String insert = "UPDATE departamento SET nombre=? WHERE id=?";
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, pojo.getNombre());
            ps.setLong(2, pojo.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar(long id) {
        String insert = "DELETE FROM departamento WHERE id=?";
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Departamento buscarById(long id) {
        String insert = "SELECT * FROM departamento WHERE id =?";
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Departamento p = new Departamento();
                p.setId(rs.getLong(1));
                p.setNombre(rs.getString(2));
                depa = p;
            } else {

                depa = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depa;
    }

    @Override
    public List<Departamento> buscarAll() {
        String insert = "SELECT * FROM departamento ORDER BY id";
        List<Departamento> lista = new ArrayList<>();
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            rs = ps.executeQuery();
            while (rs.next()) {
                Departamento p = new Departamento();
                p.setId(rs.getLong(1));
                p.setNombre(rs.getString(2));
  
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
