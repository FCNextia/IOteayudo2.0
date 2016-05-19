/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Usuario;
/**
 *
 * @author daniel
 */
public class BajaUsuarioHelper {
    
    Usuario usuario;
    private Session session;
    
    public BajaUsuarioHelper(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void darBaja(Usuario usuario){
        session.delete(usuario);
        return;
    }
}
