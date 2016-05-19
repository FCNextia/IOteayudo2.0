package logica;

import java.util.Date;
import modelo.Alumno;
import modelo.Tutor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modelo.Usuario;

/**
 *
 * @author miguel
 */
public class RegistroHelper {

    private Session session;

    public RegistroHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void registraUsuarioAlumno(int id, String correo, String nombre, String apellidoPa, String apellidoMa, String contrasenia){
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       Usuario usuario = new Usuario();
       usuario.setIdUsuario(id);
       usuario.setCorreoUsuario(correo);
       usuario.setNombreUsuario(nombre);
       usuario.setApellidoPaternoUsuario(apellidoPa);
       usuario.setApellidoMaternoUsuario(apellidoMa);
       usuario.setContraseniaUsuario(contrasenia);
       usuario.setTelefonoUsuario(0);
       usuario.setAcercaDeUsuario("vacio");
       session.persist(usuario);
       usuario.setIdUsuario(id);
       Alumno a = new Alumno(usuario);
       a.setFechaNacimientoAlumno(new Date(93,10,27));
       session.persist(a);
       session.getTransaction().commit();
       
    }
    
    public void registraUsuarioTutor(int id, String correo, String nombre, String apellidoPa, String apellidoMa, String contrasenia){
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       Usuario usuario = new Usuario();
       usuario.setIdUsuario(id);
       usuario.setCorreoUsuario(correo);
       usuario.setNombreUsuario(nombre);
       usuario.setApellidoPaternoUsuario(apellidoPa);
       usuario.setApellidoMaternoUsuario(apellidoMa);
       usuario.setContraseniaUsuario(contrasenia);
       usuario.setTelefonoUsuario(0);
       usuario.setAcercaDeUsuario("vacio");
       session.persist(usuario);
       usuario.setIdUsuario(id);
       Tutor t = new Tutor(usuario, "vacio");
       session.persist(t);
       session.getTransaction().commit();     
    }
}
