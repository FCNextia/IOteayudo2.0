package logica;

import modelo.Tutor;
import org.hibernate.Query;
import org.hibernate.Session;
import modelo.Usuario;

/**
 * Clase que se encarga de actualizar tutores.
 * @author Manuel Soto Romero
 */
public class ActualizarDatosTutorHelper {

    /* Sesión. */
    private Session session;

    /**
     * Constructor por omisión que se encarga de obtener la sesión actual.
     */
    public ActualizarDatosTutorHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Método que se encarga de actualizar los datos generales de un tutor.
     * @param correo Correo del tutor
     * @param contrasenia Contrasenia del tutor
     * @param nombre Nombre del tutor
     * @param ap Apellido paterno del tutor
     * @param am Apellido materno del tutor
     * @param cel Celular del tutor
     * @param ad Información del tutor
     * @return  ID del tutor.
     */
    public int actualizaDatos(String correo, String contrasenia, String nombre, 
            String ap, String am, long cel, String ad) {
        try {
            session.beginTransaction();
            Query p = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", correo);
            Usuario u = (Usuario)p.uniqueResult();
            u.setCorreo(correo);
            if (!contrasenia.isEmpty())
                u.setContrasenia(contrasenia);
            if (!nombre.isEmpty())
                u.setNombreUsuario(nombre);
            if (!ap.isEmpty())
                u.setApp(ap);
            if (!am.isEmpty())
                u.setApm(am);
            u.setTelefono(cel);
            if (!ad.isEmpty())
                u.setAcercaDe(ad);
            session.persist(u);
            session.getTransaction().commit();
            return u.getIdUsuario();
        } catch (Exception e) {
            return -1;
        }
    }
    
    /**
     * Método que se encarga de actualizar los datos particulares de un tutor.
     * @param id ID del tutor.
     * @param nivel_estudios Nivel de estudios del tutor.
     */
    public void actualizaDatosTutor(int id, String nivel_estudios) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query p = session.getNamedQuery("BuscaTutorPorID").setInteger("idUsuario", id);
        Tutor t = (Tutor)p.uniqueResult();
        if (!nivel_estudios.isEmpty())
            t.setNivelEstudio(nivel_estudios);
        session.persist(t);
        session.getTransaction().commit();
    }
}
