/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rtaboada
 */
public class BuscarTutorHelper {
    Session session;
    
    public BuscarTutorHelper(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List<Usuario> verificaTutor(String materia){
        List<Usuario> tutores;
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscarTutor").setString("nombreMateria", materia);
        tutores = (List<Usuario>) q.list();
        return tutores;
    }
}
