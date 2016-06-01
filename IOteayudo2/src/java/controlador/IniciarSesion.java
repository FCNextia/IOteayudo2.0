package controlador;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logica.IniciarSesionHelper;
import modelo.Usuario;

/**
 * Clase encargada de iniciar sesión en el sistema.
 */
@ManagedBean
@RequestScoped
public class IniciarSesion {

    /* Lógica para conectarnos con la base de datos. */
    private final IniciarSesionHelper helper;
    /* Para manejar los atributos de la sesión. */
    private final HttpSession session;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Permite el envío de mensajes entre el bean y la vista. */
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista
    /* Correo del usuario a ingresar en el sistema. */
    private String correo;
    /* Constraseña del usuario a ingresar en el sistema. */
    private String contrasenia;
    /* Para manejar los atributos de la sesión. */
    private final HttpServletRequest httpServletRequest;

    /**
     * Constructor por omisión. Inicializa los atributos en un estado válido.
     */
    public IniciarSesion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = 
                (HttpServletRequest)faceContext.getExternalContext().getRequest();
        session = httpServletRequest.getSession(true);
        helper = new IniciarSesionHelper();
    }

    /**
     * Método encargado de iniciar la sesión.
     * @return Dirección de la página de perfil del usuario.
     */
    public String iniciarSesion() {
        Usuario usuario = helper.getLoginPorCorreo(getCorreo());
        /* Verificamos que el usuario existan. */
        if (usuario != null) {
            /* Verificamos que la contraseña coincida. */
            if (getContrasenia().equals(usuario.getContrasenia())) {
                session.setAttribute("sessionUsuario", correo);
                session.setAttribute("idUsuario", usuario.getIdUsuario());
                String nombreYApp = usuario.getNombreUsuario() + " " 
                        + usuario.getApp();
                session.setAttribute("nombreYApp", nombreYApp);
                String nombreC = usuario.getNombreUsuario() + " " 
                        +  usuario.getApp() + " " + usuario.getApm();
                session.setAttribute("nombre", nombreC);
                session.setAttribute("calificacion", usuario.getCalificacion());
                session.setAttribute("acercade", usuario.getAcercaDe());
                if (usuario.getAlumno() != null) // si es alumno
                    return "perfilalumno";
                if (usuario.getTutor() != null) // si es tutor
                    return "perfiltutor";
            } else {
                /* En caso de que la contraseña no coincida. */
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Contraseña incorrecta", null);
                faceContext.addMessage(null, message);
                return "iniciosesion";
            }
        }
        /* En caso de que el usuario no exista. */
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Usuario no registrado.", null);
        faceContext.addMessage(null, message);
        return "iniciosesion";
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return httpServletRequest.getSession().getAttribute("nombre").toString();
    }
    
    public String getNombreYApp(){
        return httpServletRequest.getSession().getAttribute("nombreYApp").toString();
    }

    public int getCalificacion() {
        return Integer.parseInt(httpServletRequest.getSession().getAttribute("calificacion").toString());
    }

    public String getAcercaDe() {
        return httpServletRequest.getSession().getAttribute("acercade").toString();
    }
}
