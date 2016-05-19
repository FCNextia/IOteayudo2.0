package controlador;

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
public class AltaUsuario {
    
    /* DATOS SUFICIENTES PARA DAR DE ALTA A UN USUARIO. */
    
    /* ID del usuario. */
    private int id;
    /* Correo electrónico del usuario. */
    private String correo;
    /* Contraseña del usuario. */
    private String contrasenia;
    /* Confirmación de la contraseña. */
    private String confirmacion;
    /* Nombre del usuario. */
    private String nombre;
    /* Apellido paterno del usuario. */
    private String apellidop;
    /* Apellido materno del usario. */
    private String apellidom;
    /* Nos indica si es alumno o tutor. */
    private boolean esAlumno;
    /* Nos indica si es alumno o tutor. */
    private boolean esTutor;
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
    public AltaUsuario() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
        rh = new RegistroHelper();
    }
    
    /**
     * Da de alta al usuario/alumno y lo redirige a su perfil.
     * @return Dirección de la vista perfil.
     */
    public String darDeAltaUsuario() {
        if(!esTutor){
            try {
                rh.registraUsuarioAlumno(getId(), getCorreo(), getNombre(), getApellidop(), getApellidom(), getContrasenia());
                return "perfilalumno";
            } catch (Exception e) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incorrectos", null);
                faceContext.addMessage(null, message);
            }
        }else{
            try {
                rh.registraUsuarioTutor(getId(), getCorreo(), getNombre(), getApellidop(), getApellidom(), getContrasenia());
                return "perfiltutor";
            } catch (Exception e ) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incorrectos", null);
                faceContext.addMessage(null, message);
            }
        }
        return "registro";
    }
    
    /** MÉTODOS DE MODIFICADORES Y DE ACCESO PARA COMUNICARNOS CON LA VISTA 
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public boolean isEsAlumno() {
        return esAlumno;
    }

    public void setEsAlumno(boolean esAlumno) {
        this.esAlumno = esAlumno;
    }

    public boolean isEsTutor() {
        return esTutor;
    }

    public void setEsTutor(boolean esTutor) {
        this.esTutor = esTutor;
    }
    
    
}
