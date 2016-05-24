package logica;

import java.util.LinkedList;
import java.util.List;
import modelo.Asesoria;
import modelo.Solicitud;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

/**
 *
 * @author manu
 */
public class MuestraHistorialHelper {
    
    /* Para actualizar la base. */
    private final Session session;
    
    /**
     * Constructor por omisión. Inicializa el objeto para trabajar con la base.
     */
    public MuestraHistorialHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List<Solicitud> getSolicitudesAlumno(int idUsuario) throws TransactionException {
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaSolicitudPorIDA").setInteger("idAlumno", idUsuario);
        List<Solicitud> solicitudes = (List<Solicitud>)q.list();
        tx.commit();
        return solicitudes;
    }
    
    public List<Solicitud> getSolicitudesTutor(int idUsuario) throws TransactionException {
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaSolicitudPorIDT").setInteger("idTutor", idUsuario);
        List<Solicitud> solicitudes = (List<Solicitud>)q.list();
        tx.commit();
        return solicitudes;
    }
}
