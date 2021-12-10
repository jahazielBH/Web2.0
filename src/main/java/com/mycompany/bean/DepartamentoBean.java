/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.modelo.Departamento;
import com.mycompany.pool.DepartamentoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
@Named(value = "departamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Departamento departamento;
    private DepartamentoDAO departamentodao;
    private List<Departamento> lista;
    
    /**
     * Creates a new instance of DepartamentoBean
     */
    public DepartamentoBean() {
        departamento = new Departamento();
        departamentodao = new DepartamentoDAO();
    }
    
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public DepartamentoDAO getDepartamentoDAO() {
        return departamentodao;
    }

    public void setDepartamentoDAO(DepartamentoDAO departamentodao) {
        this.departamentodao = departamentodao;
    }

    public List<Departamento> getLista() {
        lista = departamentodao.buscarAll();
        return lista;
    }

    public void setLista(List<Departamento> lista) {
        this.lista = lista;
    }

    public void insertar() {
        boolean res;
        try {
            res = departamentodao.guardar(this.departamento);
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
            res = departamentodao.actualizar(this.departamento);
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
            res = departamentodao.eliminar(this.departamento.getId());
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
        lista = new ArrayList<>();
        boolean res;
        try {
            res = lista.add(departamentodao.buscarById(this.departamento.getId()));
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
    
    public void limpiarDepartamento() {
        departamento = new Departamento();
    }

}
