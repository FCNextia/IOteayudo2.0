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
    Transaction tx;
    public BuscarTutorHelper(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();
    }
    
    public List<Usuario> verificaTutor(String materia){
        List<Usuario> tutores;
        Query q = session.getNamedQuery("BuscarTutor").setString("nombreMateria", materia);
        tutores = (List<Usuario>) q.list();
        //tx.rollback();
        return tutores;
    }

    public String verificaMateria(String materia){
        List<Materia> materias;
        try{
            Query q = session.getNamedQuery("BuscarMateria").setString("materia", materia);
            materias = (List<Materia>) q.list();
            //tx.rollback();
            return materias.get(0).getNombreMateria();
           
        }catch(IndexOutOfBoundsException e){
            return "";
        }
    }
}
