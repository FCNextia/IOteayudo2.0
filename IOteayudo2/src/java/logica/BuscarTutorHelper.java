package logica;

import java.util.List;
import modelo.Materia;
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
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.getNamedQuery("BuscarTutor").setString("nombreMateria", materia);
            tutores = (List<Usuario>) q.list(); 
            tx.commit();
            return tutores;
        }catch(Error e){
            System.err.println("CHECA ESTE ERROR DE verificatutor");
            return null;
        }
    }

    public String encontrarMateria(String s) {
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.getNamedQuery("BuscarMateria").setString("nombre_materia", s);
            Materia m = (Materia)q.uniqueResult();
            tx.commit();
            return m.getNombreMateria();
        }catch(Error e){
            System.err.println("CHECA ERROR DE encontrarMateria");
            return null;
        }
    }
}
