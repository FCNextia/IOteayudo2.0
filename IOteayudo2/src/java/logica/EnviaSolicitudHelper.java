package logica;

import java.util.Date;
import modelo.Alumno;
import modelo.Asesoria;
import modelo.Materia;
import modelo.Solicitud;
import modelo.Tutor;
import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

/**
 * Clase para enviar solicitudes.
 * @author Manuel Soto Romero
 */
public class EnviaSolicitudHelper {
    
    /* Para comunicarnos con la base. */
    private final Session session;
    
    /**
     * Constructor por omisión.
     */
    public EnviaSolicitudHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    /**
     * Método encargado de guardar la solicitud.
     * @param solicitud Solicitud a guardar.
     * @throws TransactionException En caso de error durante la transacción.
     */
    public void guardaSolicitud(Solicitud solicitud, String mail, int idTutor, int idMateria) throws TransactionException {
        Transaction tx = session.beginTransaction();
        Query p = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", mail);
        Usuario u = (Usuario)p.uniqueResult();
        Query q = session.getNamedQuery("BuscaAlumnoPorID").setInteger("idUsuario", u.getIdUsuario());
        Alumno a = (Alumno)q.uniqueResult();
        Query s = session.getNamedQuery("BuscaTutorPorID").setInteger("idUsuario", idTutor);
        Tutor t = (Tutor)s.uniqueResult();
        Query r = session.getNamedQuery("BuscaMateriaPorID").setInteger("idMateria", idMateria);
        Materia m = (Materia)r.uniqueResult();
        solicitud.setAlumno(a);
        solicitud.setTutor(t);
        solicitud.setMateria(m);
        Asesoria as = new Asesoria();
        as.setCosto(260);
        as.setFecAsesoria(new Date());
        as.setDireccion("Pendiente");
        solicitud.setAsesoria(as);
        session.persist(solicitud.getAsesoria());
        session.persist(solicitud);
        tx.commit();
    }
}
