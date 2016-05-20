package logica;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Alumno;
import modelo.Tutor;
import modelo.Usuario;

/**
 * Realiza operaciones con la base de datos relacionadas con el inicio
 * de sesión.
 *
 * @author miguel
 */
public class IniciarSesionHelper {

    private final Session session;
    private Transaction tx;

    /**
     * Constructor para iniciar la sesión de Hibernate.
     */
    public IniciarSesionHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Busca usuarios por el correo.
     *
     * @param correo El correo electrónico de la persona.
     *
     * @return El usuario si existe.
     */
    public Usuario getLoginPorCorreo(String correo) {
        tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", correo);
        Usuario usuario = (Usuario) q.uniqueResult();
        tx.commit();
        return usuario;
    }

    /**
     * Regresa verdadero si el usuario es un alumno.
     *
     * @param id El id del usuario.
     *
     * @return Verdadero si el usuario es un alumno, falso en caso
     * contrario.
     */
    public boolean esAlumno(int id) {
        tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaAlumnoPorID").setInteger("idUsuario", id);
        Alumno a = (Alumno) q.uniqueResult();
        tx.commit();
        return a != null;
    }

    /**
     * Regresa verdadero si el usuario es un tutor, falso en caso
     * contrario.
     *
     * @param id El id del usuario.
     *
     * @return Verdadero si el usuario es un alumno, falso en caso
     * contrario.
     */
    public boolean esTutor(int id) {
        tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaTutorPorID").setInteger("idUsuario", id);
        Tutor t = (Tutor) q.uniqueResult();
        tx.commit();
        return t != null;
    }
}
