package controlador;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import logica.ProcesaSolicitudHelper;
import modelo.Asesoria;

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
    private ProcesaSolicitudHelper esh;
    /* Materia a solicitar */
    private static String materia;
    /* Id del tutor a solicitar. */
    private static int tutor;
    /* Nombre del tutor. */
    private String nombreTutor;
    /* Fecha de la asesoria. */
    private Date fecha;
    /* Dirección de la asesoría. */
    private String direccion;
    /* Detalles extra */
    private String detalles;
    
    /**
     * Constructor por omisión.
     */
    public ProcesarSolicitudes() {
        faceContext = FacesContext.getCurrentInstance();
        esh = new ProcesaSolicitudHelper();
    }
    
    /**
     * Método que prepara la solicitud para el registro.
     * @param materia Nombre de la materia a solicitar.
     * @param tutor Id del tutor de la materia.
     * @param nombreTutor
     * @return Dirección a la página de solicitud.
     */
    public String preparaSolicitud(String materia, int tutor, String nombreTutor) {
        ProcesarSolicitudes.materia = materia;
        ProcesarSolicitudes.tutor = tutor;
        this.nombreTutor = nombreTutor;
        return "registroasesoria";
    }
    
    /* Método que crea un objeto asesoria. */
    private Asesoria creaAsesoria() {
        Asesoria asesoria = new Asesoria();
        asesoria.setEstado('p');
        asesoria.setFecAsesoria(fecha);
        asesoria.setDireccion(direccion);
        asesoria.setComentario(detalles);
        return asesoria;
    }
    
    /**
     * Método que envía una solicitud.
     * @return String perfil del alumno.
     */
    public String enviaSolicitud() {
        Asesoria asesoria = creaAsesoria();
        CerrarSesion cs = new CerrarSesion();
        String mail = cs.getCorreo();
        int idMateria = obtenID(materia);
        esh.guardaAsesoria(asesoria, mail, tutor, idMateria);
        return "perfilalumno";
    }
    
    /**
     * Método que acepta una solicitud.
     * @param idAsesoria
     * @return String de la página de solicitudes.
     */
    public String aceptaSolicitud(int idAsesoria) {
        esh.aceptaSolicitud(idAsesoria);
        return "historialasesoriastutor";
    }
    
    public String rechazaSolicitud(int idAsesoria) {
        esh.rechazaSolicitud(idAsesoria);
        return "historialasesoriastutor";
    }
    
    public String cancelarAsesoriaAlumno(int idAsesoria) {
        esh.cancelaAsesoria(idAsesoria);
        return "historialasesoriasalumno";
    }
    
    public String cancelarAsesoriaTutor(int idAsesoria) {
        esh.cancelaAsesoria(idAsesoria);
        return "historialasesoriastutor";
    }
    
    private int obtenID(String s) {
        if (s == null)
            return -1;
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
            default:
                return -1;
        }
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        ProcesarSolicitudes.materia = materia;
    }

    public int getTutor() {
        return tutor;
    }

    public void setTutor(int tutor) {
        ProcesarSolicitudes.tutor = tutor;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
