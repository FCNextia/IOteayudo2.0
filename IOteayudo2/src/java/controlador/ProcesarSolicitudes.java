package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
    
    /* Materia que se está pidiendo. */
    private String materia;
    /* Tutor elegido. */
    private int tutor;
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
    public String enviaSolicitud() {
        Solicitud solicitud = new Solicitud();
        solicitud.setAsesoria(new Asesoria());
        solicitud.setEstado('e');
        CerrarSesion cs = new CerrarSesion();
        String mail = cs.getCorreo();
        int idMateria = obtenID("Matemáticas");
        esh.guardaSolicitud(solicitud, mail, tutor, idMateria);
        return "perfilalumno";
    }
    
    private int obtenID(String s) {
        switch(s) {
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
        }
        return -1;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getTutor() {
        return tutor;
    }

    public void setTutor(int tutor) {
        this.tutor = tutor;
    }
}
