package logica;

import org.hibernate.Session;

import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Transaction;
/**
 * Lógica para eliminar un usuario de la base de datos.
 */
public class BajaUsuarioHelper {
    
    /* Para conectarse a la base de datos. */
    private final Session session;
    
    /**
     * Constructor por omisión. Inicializa los atributos en un estado válido.
     */
    public BajaUsuarioHelper(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    /**
     * Método encargado de dar de baja un usuario.
     * @param correo Dirección de correo del usuario a eliminar.
     */
    public void darBaja(String correo){
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedQuery("BuscaPorCorreo").setString("correoUsuario", correo);
        Usuario u = (Usuario)q.uniqueResult();
        session.delete(u);
        tx.commit();
    }
}
