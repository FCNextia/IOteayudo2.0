package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import logica.CalificaUsuarioHelper;

/**
 * Clase para calificar usuarios.
 * @author manu
 */
@ManagedBean
@RequestScoped
public class CalificarUsuario {
    
    /* Calificación del usuario. */
    private int calificacion;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Para mostrar mensajes en la vista. */
    private FacesMessage message;
    /* Lógica para calificar usuarios. */
    private CalificaUsuarioHelper cuh;
    
    public CalificarUsuario() {
        faceContext = FacesContext.getCurrentInstance();
        cuh = new CalificaUsuarioHelper();
    }
    
    public String calificaAlumno(int idAsesoria) {
        cuh.calificaAlumno(idAsesoria, calificacion);
        return "historialasesoriastutor";
    }
    
    public String calificaTutor(int idAsesoria) {
        cuh.calificaTutor(idAsesoria, calificacion);
        return "historialasesoriasalumno";
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
