package logica;

import java.util.Date;
import modelo.Alumno;
import org.hibernate.Query;
import org.hibernate.Session;
import modelo.Usuario;

/**
 * Clase que permite actualizar los datos del alumno.
 * @author Manuel Soto Romero
 */
public class ActualizarDatosAlumnoHelper {

    /* Sesión */
    private Session session;

    /**
     * Constructor que simplemente obtiene la sesión actual.
     */
    public ActualizarDatosAlumnoHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Método que se encarga de actualizar los datos generales del usuario 
     * alumno.
     * @param correo Correo del alumno
     * @param contrasenia Contraseña del alumno
     * @param nombre Nombre del alumno
     * @param ap Apellido paterno del alumno 
     * @param am Apellido materno del alumno
     * @param cel Celular del alumno
     * @param ad Información del alumno
     * @return Id del usuario
     */
    public int actualizaDatos(String correo, String contrasenia, String nombre, 
            String ap, String am, long cel, String ad) {
        try {
            session.beginTransaction();
            Query p = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", correo);
            Usuario u = (Usuario)p.uniqueResult();
            u.setCorreo(correo);
            u.setContrasenia(contrasenia);
            u.setNombreUsuario(nombre);
            u.setApp(ap);
            u.setApm(am);
            u.setTelefono(cel);
            u.setAcercaDe(ad);
            session.persist(u);
            session.getTransaction().commit();
            return u.getIdUsuario();
        } catch (Exception e) {
            return -1;
        }
    }
    
    /**
     * Método que se encarga  de actualizar los datos particulares del alumno.
     * @param id Id del alumno.
     * @param fecha Fecha del alumno.
     */
    public void actualizaDatosAlumno(int id, Date fecha) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query p = session.getNamedQuery("BuscaAlumnoPorID").setInteger("idUsuario", id);
        Alumno a = (Alumno)p.uniqueResult();
        a.setFecNac(fecha);
        session.persist(a);
        session.getTransaction().commit();
    }
}
