package controlador;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logica.ActualizarDatosTutorHelper;
import modelo.Usuario;

/**
 * Controlador que permite agregar un usuario a la base de datos.
 * @author Manuel Soto Romero.
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class ActualizarDatosTutor {
    
    /* DATOS SUFICIENTES PARA DAR DE ALTA A UN USUARIO. */
    
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
    /* Escolaridad. */
    private String escolaridad;
    /* Nuevo curso. */
    private String nuevoCurso;
    /* Nueva área. */
    private String nuevaArea;
    /* Celular del alumno. */
    private String celular;
    /* Descripción del alumno. */
    private String acercaDeMi;
    /* Obtiene información de las peticiones. */
    private final HttpServletRequest httpServletRequest;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Permite el envio de mensajes entre el bean y la vista. */
    private FacesMessage message;
    private ActualizarDatosTutorHelper adth;
    
    /**
     * Constructor por omisión.
     * Inicializa los objetos necesarios para comunicarnos con la vista.
     */
    public ActualizarDatosTutor() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = 
                (HttpServletRequest)faceContext.getExternalContext().getRequest();
        adth = new ActualizarDatosTutorHelper();
    }
    
    /**
     * Da de alta al usuario y lo redirige a su perfil.
     * @return Dirección de la vista perfil.
     */
    public String actualizarDatos() {
        int cel = Integer.parseInt(getCelular());
        int id = adth.actualizaDatos(getCorreo(), getContrasenia(), getNombre(), 
                getApellidop(),getApellidom(), cel, getAcercaDeMi());
        adth.actualizaDatosTutor(id, getEscolaridad());
        return "perfiltutor";
    }
    
    /* MÉTODOS DE MODIFICADORES Y DE ACCESO PARA COMUNICARNOS CON LA VISTA */
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getAcercaDeMi() {
        return acercaDeMi;
    }

    public void setAcercaDeMi(String acercaDeMi) {
        this.acercaDeMi = acercaDeMi;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getNuevoCurso() {
        return nuevoCurso;
    }

    public void setNuevoCurso(String nuevoCurso) {
        this.nuevoCurso = nuevoCurso;
    }

    public String getNuevaArea() {
        return nuevaArea;
    }

    public void setNuevaArea(String nuevaArea) {
        this.nuevaArea = nuevaArea;
    }
}
