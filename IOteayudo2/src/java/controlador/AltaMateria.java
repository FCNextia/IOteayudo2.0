package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import logica.RegistoMateriaHelper;
import javax.servlet.http.HttpServletRequest;

/**
 * Clase para dar de alta una materia.
 * @author Manuel Soto Romero
 */
@ManagedBean
@RequestScoped
public class AltaMateria {
    
    /* Id de la materia a crear. */
    private int idMateria;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Para mostrar mensajes en la vista. */
    private FacesMessage message;
    /* Lógica para registrar la materia. */
    private final RegistoMateriaHelper rmh;
    /* Para trabajar con sesiones. */
    private final HttpServletRequest httpServletRequest;
    
    /**
     * Constructor por omisión. Inicializa los objetos necesarios para 
     * comunicarnos con la vista.
     */
    public AltaMateria() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
        rmh = new RegistoMateriaHelper();
    }
    
    /**
     * Método para dar de alta una materia. Da de alta la materia y redirige al
     * usuario (tutor) a su perfil.
     * @return Dirección de la página de perfil si los datos son correctos, en
     * caso contrario, se mantiene en la página de registro de materias.
     */
    public String daDeAltaMateria() {
        /* Obtenemos el id del usuario actual. */
        int idTutor = Integer.parseInt(httpServletRequest.getSession().getAttribute("idUsuario").toString());
        rmh.registraMateria(idMateria, idTutor);
        return "perfiltutor";
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
}
