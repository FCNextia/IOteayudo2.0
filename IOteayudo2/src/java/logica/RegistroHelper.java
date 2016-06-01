package logica;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import modelo.Alumno;
import modelo.Tutor;
import modelo.Usuario;

/**
 * Clase con la lógica necesaria para comunicarnos con la base y registrar a un
 * usuario.
 */
public class RegistroHelper {

    /* Nos permite manejar la sesión actual .*/
    private final Session session;

    /**
     * Constructor por omisión. Inicializa el objeto a un estado válido.
     */
    public RegistroHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Método encargado de registrar un usuario como alumno. La fecha
     * de nacimiento del alumno queda por default como la fecha
     * generada por new Date() (i.e. 01-01-1969).
     * @param usuario El usuario a registrar.
     * @throws TransactionException Se lanza la excepción en caso de
     * un error durante la transacción.
     */
    public void registraUsuarioAlumno(Usuario usuario)  {
        Transaction tx = session.beginTransaction();
        try {
        Alumno alumno = new Alumno(usuario, new Date(94,10,10));
        session.persist(usuario);
        session.persist(alumno);
        tx.commit();
        } catch (TransactionException e) {
            tx.rollback();
        }
    }

    /**
     * Método encargado de registrar un usuario como tutor.
     * @param usuario El usuario a registrar.
     * @throws TransactionException Lanza la excepción en caso de un
     * error durante la transacción.
     */
    public void registraUsuarioTutor(Usuario usuario) {
        Transaction tx = session.beginTransaction();
        try {
            Tutor tutor = new Tutor(usuario);
            session.persist(usuario);
            session.persist(tutor);
            tx.commit();
        } catch (TransactionException e) {
            tx.rollback();
        }
    }
}
