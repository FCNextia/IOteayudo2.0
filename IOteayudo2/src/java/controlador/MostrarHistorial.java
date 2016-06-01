package controlador;

import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logica.MuestraHistorialHelper;
import modelo.Asesoria;
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
    private final HttpServletRequest httpServletRequest;
    
    /**
     * Constructor por omisión.
     */
    public MostrarHistorial() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        mhh = new MuestraHistorialHelper();
    }
    
    /**
     * Método encargado de obtener las solicitudes asociadas a un alumno.
     */
    public List<Asesoria> obtenSolicitudesAlumno(int idUsuario) {
        System.out.println("\n\nEste usuario es: " + idUsuario);
        return mhh.getSolicitudesAlumno(idUsuario);
    }
    
    public int pendientesAlumno(int idUsuario) {
        return obtenSolicitudesAlumno(idUsuario).size();
    }
    
    public List<Asesoria> obtenSolicitudesAprobadasAlumno(int idUsuario) {
        return mhh.getSolicitudesAprobadasAlumno(idUsuario);
    }
    
    public List<Asesoria> obtenSolicitudesTerminadasAlumno(int idUsuario) {
        return mhh.getSolicitudesTerminadasAlumno(idUsuario);
    }
    
    public List<Asesoria> obtenSolicitudesCalificadasAlumno(int idUsuario) {
        return mhh.getSolicitudesCalificadasAlumno(idUsuario);
    }
    
    /**
     * Método encargado de obtener las solicitadas asociadas a un tutor.
     */
    public List<Asesoria> obtenSolicitudesTutor(int idUsuario) {
        return mhh.getSolicitudesTutor(idUsuario);
    }
    
    public int pendientesTutor(int idUsuario) {
        return obtenSolicitudesTutor(idUsuario).size();
    }
    
    public List<Asesoria> obtenSolicitudesAprobadasTutor(int idUsuario) {
        return mhh.getSolicitudesAprobadasTutor(idUsuario);
    }
    
    public List<Asesoria> obtenSolicitudesTerminadasTutor(int idUsuario) {
        return mhh.getSolicitudesTerminadasTutor(idUsuario);
    }
    
    public List<Asesoria> obtenSolicitudesCalificadasTutor(int idUsuario) {
        return mhh.getSolicitudesCalificadasTutor(idUsuario);
    }
}
