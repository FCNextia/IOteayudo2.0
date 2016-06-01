package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logica.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Caso de uso para cerrar sesión.
 * @author Manuel Soto Romero
 */
@ManagedBean
@RequestScoped
public class CerrarSesion {
    
    private String correo;
    private int id;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;
    private final HttpSession session;
    
    public CerrarSesion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        session = httpServletRequest.getSession(true);
        if (httpServletRequest.getSession().getAttribute("sessionUsuario") != null) {
            correo = httpServletRequest.getSession().getAttribute("sessionUsuario").toString();
            id = Integer.parseInt(httpServletRequest.getSession().getAttribute("idUsuario").toString());
        }
    }
    
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
