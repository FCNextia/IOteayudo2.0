package logica;

import modelo.Alumno;
import modelo.Asesoria;
import modelo.Materia;
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
public class ProcesaSolicitudHelper {
    
    /* Para comunicarnos con la base. */
    private final Session session;
    
    /**
     * Constructor por omisión.
     */
    public ProcesaSolicitudHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    /**
     * Método encargado de guardar la solicitud.
     * @param asesoria
     * @param mail Correo del usuario.
     * @param idTutor id del tutor.
     * @param idMateria id de la materia.
     * @throws TransactionException En caso de error durante la transacción.
     */
    public void guardaAsesoria(Asesoria asesoria, String mail, int idTutor, int idMateria) throws TransactionException {
        Transaction tx = session.beginTransaction();
        Query p = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", mail);
        Usuario u = (Usuario)p.uniqueResult();
        Query q = session.getNamedQuery("BuscaAlumnoPorID").setInteger("idUsuario", u.getIdUsuario());
        Alumno a = (Alumno)q.uniqueResult();
        Query s = session.getNamedQuery("BuscaTutorPorID").setInteger("idUsuario", idTutor);
        Tutor t = (Tutor)s.uniqueResult();
        Query r = session.getNamedQuery("BuscaMateriaPorID").setInteger("idMateria", idMateria);
        Materia m = (Materia)r.uniqueResult();
        asesoria.setAlumno(a);
        asesoria.setMateria(m);
        asesoria.setTutor(t);
        asesoria.setCosto(260);
        session.persist(asesoria);
        tx.commit();
    }
    
    /**
     * Método encargado de procesar la solicitud.
     * @param idAsesoria
     */
    public void aceptaSolicitud(int idAsesoria) {
        Transaction tx = session.beginTransaction();
        Query p = session.getNamedQuery("BuscaSolicitudPorID").setInteger("idAsesoria", idAsesoria);
        Asesoria a = (Asesoria)p.uniqueResult();
        a.setEstado('a');
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
    
    public void cancelaAsesoria(int idAsesoria) {
        Transaction tx = session.beginTransaction();
        Query p = session.getNamedQuery("BuscaSolicitudPorID").setInteger("idAsesoria", idAsesoria);
        Asesoria a = (Asesoria)p.uniqueResult();
        a.setEstado('c');
        session.persist(a);
        tx.commit();
    }
    
    public void terminaAsesoria(int idAsesoria) {
        Transaction tx = session.beginTransaction();
        Query p = session.getNamedQuery("BuscaSolicitudPorID").setInteger("idAsesoria", idAsesoria);
        Asesoria a = (Asesoria)p.uniqueResult();
        a.setEstado('t');
        session.persist(a);
        tx.commit();
    }
}
