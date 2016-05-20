package logica;

import org.hibernate.Session;

import modelo.Usuario;
/**
 *
 * @author daniel
 */
public class BajaUsuarioHelper {
    
    Usuario usuario;
    private Session session;
    
    public BajaUsuarioHelper(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void darBaja(Usuario usuario){
        session.delete(usuario);
        return;
    }
}
