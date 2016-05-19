package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logica.BajaUsuarioHelper;
import modelo.Usuario;
import org.hibernate.SessionFactory;

/**
 * Controlador que permite eliminar un usuario a la base de datos.
 * @author daniel
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class BajaUsuario {
    
    /* Obtiene información de las peticiones. */
    private final HttpServletRequest httpServletRequest;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Permite el envio de mensajes entre el bean y la vista. */
    private FacesMessage message;
    private SessionFactory factory; 
    private final BajaUsuarioHelper bh;
    
    public BajaUsuario(Usuario usuario){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
        bh = new BajaUsuarioHelper();
        usuario = usuario;
    }
    
    public void darBaja(Usuario usuario){
        bh.darBaja(usuario);
    }
    
    
}
