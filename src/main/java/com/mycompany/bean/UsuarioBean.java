/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.modelo.Usuario;
import com.mycompany.pool.UsuarioDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private UsuarioDAO usuariodao;
    
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        usuario = new Usuario();
        usuariodao = new UsuarioDAO();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public UsuarioDAO getUsuarioh() {
        return usuariodao;
    }

    public void setUsuarioh(UsuarioDAO usuariodao) {    
        this.usuariodao = usuariodao;
    }

    public void registrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            boolean result  = usuariodao.register(this.usuario);
            if (result != false) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito", "Usuario Registrado \n" +
                        usuario.getUname()));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
        }
    }
    
    public String iniciarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us;
        String redireccion = null;
        try {
            us = usuariodao.login(this.usuario);
            if(us != null){
                context.getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "/index?faces-redirect=true";
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Credenciales Incorrectas"));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
        }
        return redireccion;
    }
    
    public void verificarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                context.getExternalContext().redirect("login.xhtml");
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
    
    public void cerrarSesion(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().invalidateSession();
        } catch (Exception e) {
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error")); 
        }    
    }
}
