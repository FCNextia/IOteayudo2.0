/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Date;
import modelo.Alumno;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modelo.Usuario;

/**
 *
 * @author miguel
 */
public class ActualizarDatosAlumnoHelper {

    private Session session;

    public ActualizarDatosAlumnoHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Método que se encarga de actualizar los datos de un alumno.
     * @param correo
     * @param contrasenia
     * @param nombre
     * @param ap
     * @param am
     * @param cel
     * @param ad
     */
    public int actualizaDatos(String correo, String contrasenia, String nombre, 
            String ap, String am, int cel, String ad) {
        try {
            //Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query p = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", correo);
            Usuario u = (Usuario)p.uniqueResult();
            u.setCorreoUsuario(correo);
            u.setContraseniaUsuario(contrasenia);
            u.setNombreUsuario(nombre);
            u.setApellidoPaternoUsuario(ap);
            u.setApellidoMaternoUsuario(am);
            u.setTelefonoUsuario(cel);
            u.setAcercaDeUsuario(ad);
            session.persist(u);
            session.getTransaction().commit();
            return u.getIdUsuario();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public void actualizaDatosAlumno(int id, Date fecha) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query p = session.getNamedQuery("BuscaAlumnoPorID").setInteger("idUsuario", id);
        Alumno a = (Alumno)p.uniqueResult();
        a.setFechaNacimientoAlumno(fecha);
        session.persist(a);
        session.getTransaction().commit();
    }
    
    
}
