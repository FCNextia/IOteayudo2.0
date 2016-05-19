/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

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
public class ActualizarDatosTutorHelper {

    private Session session;

    public ActualizarDatosTutorHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Método que se encarga de actualizar los datos de un alumno.
     * @param contrasenia
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
            e.printStackTrace();
            return -1;
        }
    }
    
    public void actualizaDatosTutor(int id, String nivel_estudios) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query p = session.getNamedQuery("BuscaTutorPorID").setInteger("idUsuario", id);
        Tutor t = (Tutor)p.uniqueResult();
        t.setNivelEstudio(nivel_estudios);
        session.persist(t);
        session.getTransaction().commit();
    }
}
