package controlador;

import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logica.MuestraHistorialHelper;
//import modelo.Solicitud;

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
    //private List<Solicitud> solicitudes;
    private final HttpServletRequest httpServletRequest;
    private int idUsuario;
    
    /**
     * Constructor por omisión.
     */
    public MostrarHistorial() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
        idUsuario = (Integer.parseInt(httpServletRequest.getSession().getAttribute("idUsuario").toString()));
        mhh = new MuestraHistorialHelper();
    }
    
    /**
     * Método encargado de obtener las solicitudes asociadas a un alumno.
     */
    public void obtenSolicitudesAlumno() {
    //    solicitudes = mhh.getSolicitudesAlumno(idUsuario);
    }
    
    /**
     * Método encargado de obtener las solicitadas asociadas a un tutor.
     */
    public void obtenSolicitudesTutor() {
    //    solicitudes = mhh.getSolicitudesTutor(idUsuario);
    }
/*
    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(LinkedList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
*/
}
