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
        char estado = a.getEstado();
        if (estado == 't') // no ha calificado nadie
            a.setEstado('m');
        if (estado == 'n') // ya calificó el alumno
            a.setEstado('c');
        a.setCalificacionAlumno(calificacion);
        Usuario u = a.getAlumno().getUsuario();
        u.setAsesorias(u.getAsesorias() + 1);
        u.setCalificacion((u.getCalificacion() + calificacion)/u.getAsesorias());
        session.persist(u);
        session.persist(a);
        tx.commit();
    }
    
    public void calificaTutor(int idAsesoria, int calificacion) {
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaSolicitudPorID").setInteger("idAsesoria", idAsesoria);
        Asesoria a = (Asesoria)q.uniqueResult();
        char estado = a.getEstado();
        if (estado == 't') // no ha calificado el tutor
            a.setEstado('n');
        if (estado == 'm') // ya calificaron los dos
            a.setEstado('c');
        a.setCalificacionTutor(calificacion);
        Usuario u = a.getTutor().getUsuario();
        u.setAsesorias(u.getAsesorias() + 1);
        u.setCalificacion((u.getCalificacion() + calificacion)/u.getAsesorias());
        session.persist(u);
        session.persist(a);
        tx.commit();
    }
}
