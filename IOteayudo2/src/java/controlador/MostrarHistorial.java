package controlador;

import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import logica.MuestraHistorialHelper;
import modelo.Solicitud;

/**
 * Clase encargada de desplegarl el historial de asesorías.
 * @author Manuel Soto Romero
 */
@ManagedBean
@ApplicationScoped
public class MostrarHistorial {
    
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Para mostrar mensajes en la vista. */
    private FacesMessage message;
    /* Clase encargada de la lógica. */
    private MuestraHistorialHelper mhh;
    /* Solcitudes pendientes. */
    private List<Solicitud> solicitudes;
    
    public MostrarHistorial() {
        faceContext = FacesContext.getCurrentInstance();
        mhh = new MuestraHistorialHelper();
    }
    
    public void obtenSolicitudesAlumno() {
        IniciarSesion is = new IniciarSesion();
        int idUsuario = is.getIDUsuario();
        solicitudes = mhh.getSolicitudesAlumno(idUsuario);
    }
    
    public void obtenSolicitudesTutor() {
        IniciarSesion is = new IniciarSesion();
        int idUsuario = is.getIDUsuario();
        solicitudes = mhh.getSolicitudesTutor(idUsuario);
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(LinkedList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
