package logica;

import org.hibernate.Session;

import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Transaction;
/**
 *
 * @author daniel
 */
public class BajaUsuarioHelper {
    
    private Session session;
    
    public BajaUsuarioHelper(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void darBaja(String correo){
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", correo);
        Usuario u = (Usuario)q.uniqueResult();
        session.delete(u);
        tx.commit();
    }
}
