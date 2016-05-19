package controlador;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logica.RegistroHelper;
import org.hibernate.SessionFactory;

/**
 * Controlador que permite agregar un usuario a la base de datos.
 * @author Manuel Soto Romero.
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class AltaTutor {
    
    /* DATOS SUFICIENTES PARA DAR DE ALTA A UN USUARIO. */
    
    /* ID del usuario. */
    private int id;
    /* Correo electrónico del usuario. */
    private String estudios;
    
    /* Obtiene información de las peticiones. */
    private final HttpServletRequest httpServletRequest;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Permite el envio de mensajes entre el bean y la vista. */
    private FacesMessage message;
    private SessionFactory factory; 
    private RegistroHelper rh;
    
    /**
     * Constructor por omisión.
     * Inicializa los objetos necesarios para comunicarnos con la vista.
     */
    public AltaTutor() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
        rh = new RegistroHelper();
    }
    
    /**
     * Da de alta al usuario/alumno y lo redirige a su perfil.
     * @return Dirección de la vista perfil.
     *
    public void darDeAltaTutor(int id) {
        rh.registraTutor(id);
    }
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    
}
    