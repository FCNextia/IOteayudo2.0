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
            String ap, String am, long cel, String ad) {
        try {
            //Session session = HibernateUtil.getSessionFactory().openSession();
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
            e.printStackTrace();
            return -1;
        }
    }
    
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
