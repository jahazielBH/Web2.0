/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.modelo.Empleado;
import com.mycompany.pool.EmpleadoDAO;
import java.io.Serializable;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Empleado empleado;
    private EmpleadoDAO empleadodao;
    private List<Empleado> lista;
    
    /**
     * Creates a new instance of EmpleadoBean
     */
    public EmpleadoBean() {
        empleado = new Empleado();
        empleadodao = new EmpleadoDAO();
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoDAO getEmpleadoDAO() {
        return empleadodao;
    }

    public void setEmpleadoDAO(EmpleadoDAO empleadodao) {
        this.empleadodao = empleadodao;
    }

    public List<Empleado> getLista() {
        lista = empleadodao.buscarAll();
        return lista;
    }

    public void setLista(List<Empleado> lista) {
        this.lista = lista;
    }

    public void insertar() {
        boolean res;
        try {
            res = empleadodao.guardar(this.empleado);
            if (res == false) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCORRECTO", "SIN GUARDAR DATOS"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "DATOS GUARDADOS"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }

    public void actualizar() {
        boolean res;
        try {
            res = empleadodao.actualizar(this.empleado);
            if (res == false) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCORRECTO", "SIN ACTUALIZAR DATOS"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "DATOS ACTUALIZADOS"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }

    public void eliminar() {
        boolean res;
        try {
            res = empleadodao.eliminar(this.empleado.getId());
            if (res == false) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCORRECTO", "DATOS NO ELIMINADOS"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "DATOS ELIMINADOS"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
    
    public void buscarId() {
        boolean res;
        try {
            res = lista.add(empleadodao.buscarById(this.empleado.getId()));
            if (res == false) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCORRECTO", "REGISTRO NO ENCONTRADO"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "REGISTRO ENCONTRADO"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
 
    public void limpiarEmpleado() {
        empleado = new Empleado();
    }  
  
}
