package logica;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import modelo.Alumno;
import modelo.Tutor;
import modelo.Usuario;

/**
 *
 * @author miguel
 */
public class RegistroHelper {

    private final Session session;

    public RegistroHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Método encargado de registrar un usuario como alumno. La fecha
     * de nacimiento del alumno queda por default como la fecha
     * generada por new Date() (i.e. 01-01-1969).
     *
     * @param usuario El usuario a registrar.
     *
     * @throws TransactionException Se lanza la excepción en caso de
     * un error durante la transacción.
     */
    public void registraUsuarioAlumno(Usuario usuario) throws TransactionException {
        Transaction tx = session.beginTransaction();
        
        Alumno alumno = new Alumno(usuario, new Date(94,10,10));
        //alumno.setFecNac(new Date());
        session.persist(usuario);
        session.persist(alumno);
        tx.commit();
    }

    /**
     * Método encargado de registrar un usuario como tutor.
     *
     * @param usuario El usuario a registrar.
     *
     * @throws TransactionException Lanza la excepción en caso de un
     * error durante la transacción.
     */
    public void registraUsuarioTutor(Usuario usuario) throws TransactionException {
        Transaction tx = session.beginTransaction();      
       
        Tutor tutor = new Tutor(usuario);
        session.persist(usuario);
        session.persist(tutor);
        tx.commit();
    }

}
