/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.idao.IDAO;
import com.mycompany.modelo.Empleado;
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
public class EmpleadoDAO implements IDAO<Empleado> {

    private ResultSet rs;
    private PreparedStatement ps;
    Empleado emple = new Empleado();

    @Override
    public boolean guardar(Empleado pojo) {
        String insert = "INSERT INTO empleado (id,nombre,direccion,telefono) VALUES (?,?,?,?)";
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            ps.setLong(1, pojo.getId());
            ps.setString(2, pojo.getNombre());
            ps.setString(3, pojo.getDireccion());
            ps.setString(4, pojo.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean actualizar(Empleado pojo) {
        String insert = "UPDATE empleado SET nombre=?, direccion=?, telefono=? WHERE id=?";
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, pojo.getNombre());
            ps.setString(2, pojo.getDireccion());
            ps.setString(3, pojo.getTelefono());
            ps.setLong(4, pojo.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar(long id) {
        String insert = "DELETE FROM empleado WHERE id=?";
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Empleado buscarById(long id) {
        String insert = "SELECT * FROM empleado WHERE id =?";
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Empleado p = new Empleado();
                p.setId(rs.getLong(1));
                p.setNombre(rs.getString(2));
                p.setDireccion(rs.getString(3));
                p.setTelefono(rs.getString(4));
                emple = p;
            } else {

                emple = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emple;
    }

    @Override
    public List<Empleado> buscarAll() {
        String insert = "SELECT * FROM empleado ORDER BY id";
        List<Empleado> lista = new ArrayList<Empleado>();
        try {
            ps=Conexion.getInstance().getConnection().prepareStatement(insert);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado p = new Empleado();
                p.setId(rs.getLong(1));
                p.setNombre(rs.getString(2));
                p.setDireccion(rs.getString(3));
                p.setTelefono(rs.getString(4));
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
