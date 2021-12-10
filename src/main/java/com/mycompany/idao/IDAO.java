/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.idao;

import java.util.List;

/**
 *
 * @author Administrador
 * @param <T>
 */
public interface IDAO<T> {
    public boolean guardar(T pojo);
    public boolean actualizar(T pojo);
    public boolean eliminar(long id);
    public T buscarById(long id);
    public List<T> buscarAll();
}