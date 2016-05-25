package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import logica.BuscarTutorHelper;
import logica.EnviaSolicitudHelper;
import modelo.Alumno;
import modelo.Asesoria;
import modelo.Materia;
import modelo.Solicitud;
import modelo.Tutor;

/**
 * Clase para envíar, aceptar y rechazar solicitudes.
 * @author Manuel Soto Romero
 */
@ManagedBean
@RequestScoped
public class ProcesarSolicitudes {
    
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Para mostrar mensajes en la vista. */
    private FacesMessage message;
    /* Lógica para enviar solicitudes. */
    private EnviaSolicitudHelper esh;
    
    /**
     * Constructor por omisión.
     */
    public ProcesarSolicitudes() {
        faceContext = FacesContext.getCurrentInstance();
        esh = new EnviaSolicitudHelper();
    }
    
    /**
     * Método que envía una solicitud.
     * @return String perfil del alumno.
     */
    public String enviaSolicitud(String materia, int tutor) {
        Solicitud solicitud = new Solicitud();
        solicitud.setAsesoria(new Asesoria());
        solicitud.setEstado('p');
        CerrarSesion cs = new CerrarSesion();
        String mail = cs.getCorreo();
        int idMateria = obtenID(materia);
        esh.guardaSolicitud(solicitud, mail, tutor, idMateria);
        return "perfilalumno";
    }
    
    private int obtenID(String s) {
        if (s == null)
            return -1;
        String r = new BuscarTutorHelper().encontrarMateria(s);
        switch(r) {
            case "Matemáticas":
                return 1;
            case "Física":
                return 2;
            case "Química":
                return 3;
            case "Biología":
                return 4;
            case "Informática":
                return 5;
            case "Historia":
                return 6;
            case "Geografía":
                return 7;
            case "Literatura":
                return 8;
            case "Derecho":
                return 9;
            default:
                return -1;
        }
    }
}
