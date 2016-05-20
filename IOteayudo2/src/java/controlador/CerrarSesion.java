package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Caso de uso para cerrar sesión.
 * @author Manuel Soto Romero
 */
@ManagedBean
@RequestScoped
public class CerrarSesion {
    
    private String correo;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;
    
    public CerrarSesion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("sessionUsuario") != null) {
            correo = httpServletRequest.getSession().getAttribute("sessionUsuario").toString();
        }
    }
    
    public String cerrarSession() {
        httpServletRequest.getSession().removeAttribute("sessionUsuario");
        return "pantallainicial";
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
