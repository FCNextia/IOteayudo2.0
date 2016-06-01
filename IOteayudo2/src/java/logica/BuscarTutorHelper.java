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
        try{
            Query q = session.getNamedQuery("BuscarTutor").setString("nombreMateria", materia);
            tutores = (List<Usuario>) q.list();
            tx.commit();        
            return tutores;
        }catch(Error e){
            System.err.println("CHECA ESTE ERROR DE verificatutor");
            tx.rollback();
            return null;
        }
    }
}
