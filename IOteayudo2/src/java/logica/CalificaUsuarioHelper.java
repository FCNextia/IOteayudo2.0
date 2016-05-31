package logica;

import modelo.Asesoria;
import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author manu
 */
public class CalificaUsuarioHelper {
    
    /* Para comunicarnos con la base. */
    private final Session session;
    
    public CalificaUsuarioHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void calificaAlumno(int idAsesoria, int calificacion) {
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaSolicitudPorID").setInteger("idAsesoria", idAsesoria);
        Asesoria a = (Asesoria)q.uniqueResult();
        a.setEstado('c');
        a.setCalificacionAlumno(calificacion);
        Usuario u = a.getAlumno().getUsuario();
        u.setAsesorias(u.getAsesorias() + 1);
        u.setCalificacion((u.getCalificacion() + calificacion)/u.getAsesorias());
        session.persist(u);
        session.persist(a);
        tx.commit();
    }
    
    public void rechazaSolicitud(int idAsesoria) {
        Transaction tx = session.beginTransaction();
        Query p = session.getNamedQuery("BuscaSolicitudPorID").setInteger("idAsesoria", idAsesoria);
        Asesoria a = (Asesoria)p.uniqueResult();
        a.setEstado('r');
        session.persist(a);
        tx.commit();
    }
}
