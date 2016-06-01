package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logica.BajaUsuarioHelper;
import org.hibernate.SessionFactory;

/**
 * Controlador que permite eliminar un usuario a la base de datos.
 */
@ManagedBean
@RequestScoped
public class BajaUsuario {
    
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Permite el envio de mensajes entre el bean y la vista. */
    private FacesMessage message;
    /* Lógica para conectarse con la base de datos. */
    private final BajaUsuarioHelper bh;
    
    /**
     * Constructor por omisión. Inicializa los atributos a un estado válido.
     */
    public BajaUsuario(){
        faceContext = FacesContext.getCurrentInstance();
        bh = new BajaUsuarioHelper();
    }
    
    /**
     * Método encargado de dar de baja un usuario de la base de datos.
     * @return Dirección de la página inicial.
     */
    public String darBaja(){
        IniciarSesion is = new IniciarSesion();
        String correo = is.getCorreo();
        bh.darBaja(correo);
        return "pantallainicial";
    }  
}
