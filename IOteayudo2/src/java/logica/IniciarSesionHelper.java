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
 * @author miguel
 */
public class IniciarSesionHelper {

    private Session session;

    public IniciarSesionHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Usuario getLoginPorCorreo(String correo) {
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", correo);
            return (Usuario)q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}
