package logica;

import java.util.List;
import modelo.Asesoria;
//import modelo.Solicitud;
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
    private Session session;
    
    /**
     * Constructor por omisión. Inicializa el objeto para trabajar con la base.
     */
    public MuestraHistorialHelper() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    /**
     * Método para obtener la lista de solicitudes asociadas al usuario.
     * @param idUsuario Usuario a buscar.
     * @return Lista con las solicitududes
     * @throws TransactionException Si hay algún error al realizar la 
     * transacción.
     */
    public List<Asesoria> getSolicitudesAlumno(int idUsuario) throws TransactionException {
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaSolicitudPorIDA").setInteger("idAlumno", idUsuario);
        List<Asesoria> solicitudes = (List<Asesoria>)q.list();
        tx.commit();
        return solicitudes;
    }
    
    /**
     * Método para obtener la lista de solicitudes asociadas al usuario.
     * @param idUsuario Usuario a buscar.
     * @return Lista con las solicitududes
     * @throws TransactionException Si hay algún error al realizar la 
     * transacción.
     */
    public List<Asesoria> getSolicitudesTutor(int idUsuario) throws TransactionException {
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaSolicitudPorIDT").setInteger("idTutor", idUsuario);
        List<Asesoria> solicitudes = (List<Asesoria>)q.list();
        tx.commit();
        return solicitudes;
    }
}
