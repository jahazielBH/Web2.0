/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pool;

import com.mycompany.modelo.Usuario;
import com.mycompany.resources.Pool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class UsuarioDAO {

    private Connection conn;
    private ResultSet rs;
    private PreparedStatement ps;
    private List<Usuario> lista;
    private Usuario usuario;
    
    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }
    
    public boolean register(Usuario pojo){
        usuario = new Usuario();
        try {
            String insert = "INSERT INTO usuario (uname,password) VALUES (?,?)";
            conn = Pool.getInstance().getConnection();
            ps = conn.prepareStatement(insert);
            ps.setLong(1, pojo.getUid());
            ps.setString(2, pojo.getUname());
            ps.setString(3, pojo.getPassword());
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            if (rs.next()) {
                Usuario usr = new Usuario();
                usr.setUid(rs.getLong(1));
                usr.setUname(rs.getString(2));
                usr.setPassword(rs.getString(3));
                lista.add(usr);
            }
            if(!lista.isEmpty()){
                usuario = lista.get(0);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error de registro " + e.getMessage());
            return false;
        }
    }
    
    public Usuario login(Usuario pojo) {
        usuario = new Usuario();
        try {
            String consulta = "SELECT * FROM usuario where uname = ? and password = ?";
            conn = Pool.getInstance().getConnection();
            ps = conn.prepareStatement(consulta);
            ps.setLong(1, pojo.getUid());
            ps.setString(2, pojo.getUname());
            ps.setString(3, pojo.getPassword());
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            if (rs.next()) {
                Usuario usr = new Usuario();
                usr.setUid(rs.getLong(1));
                usr.setUname(rs.getString(2));
                usr.setPassword(rs.getString(3));
                lista.add(usr);
            }
            if(!lista.isEmpty()){
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error de inicio de sesión " + e.getMessage());
        }
        return usuario;
    }
}
