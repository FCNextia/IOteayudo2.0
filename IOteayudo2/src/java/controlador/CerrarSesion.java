package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Caso de uso para cerrar sesión.
 */
@ManagedBean
@RequestScoped
public class CerrarSesion {
    
    /* Correo del usuario actual. */
    private String correo;
    /* Id del usuario actual. */
    private int id;
    /* Para manejar las sesiones. */
    private final HttpServletRequest httpServletRequest;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Para manejar mensajes. */
    private FacesMessage message;
    /* Para manejar las sesiones. */
    private final HttpSession session;
    
    /**
     * Constructor por omisión. Inicializa los atributos en un estado válido.
     */
    public CerrarSesion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        session = httpServletRequest.getSession(true);
        /* Asigna el valor de los atributos id y correo. */
        if (httpServletRequest.getSession().getAttribute("sessionUsuario") != null) {
            correo = httpServletRequest.getSession().getAttribute("sessionUsuario").toString();
            id = Integer.parseInt(httpServletRequest.getSession().getAttribute("idUsuario").toString());
        }
    }
    
    /**
     * Método encargado de cerrar sesión. Elimina todos los atributos agregados
     * a la sesión actual.
     * @return Dirección a la pantalla inicial.
     */
    public String cerrarSession() {
        session.removeAttribute("sessionUsuario");
        session.removeAttribute("idUsuario");
        session.removeAttribute("nombreYApp");
        session.removeAttribute("nombre");
        session.removeAttribute("calificacion");
        session.removeAttribute("acercade");
        session.invalidate();
        return "pantallainicial";
    }
    
    /* MÉTODOS DE ACCESO Y MODIFICADORES */

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
