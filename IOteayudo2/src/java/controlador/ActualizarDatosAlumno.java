package controlador;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logica.ActualizarDatosAlumnoHelper;
import logica.IniciarSesionHelper;
import modelo.Usuario;

/**
 * Controlador que permite agregar un usuario a la base de datos.
 * @author Manuel Soto Romero.
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class ActualizarDatosAlumno {
    
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
    /* Día de nacimiento. */
    private int dia;
    /* Mes de nacimiento. */
    private int mes;
    /* Año de nacimiento. */
    private int anio;
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
    private ActualizarDatosAlumnoHelper adah;
    
    /**
     * Constructor por omisión.
     * Inicializa los objetos necesarios para comunicarnos con la vista.
     */
    public ActualizarDatosAlumno() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = 
                (HttpServletRequest)faceContext.getExternalContext().getRequest();
        adah = new ActualizarDatosAlumnoHelper();
    }
    
    /**
     * Da de alta al usuario y lo redirige a su perfil.
     * @return Dirección de la vista perfil.
     */
    public String actualizarDatos() {
        int cel = Integer.parseInt(getCelular());
        Date fecha = new Date(getAnio(), getMes()-1, getDia());
        int id = adah.actualizaDatos(getCorreo(), getContrasenia(), getNombre(), 
                getApellidop(),getApellidom(), cel, getAcercaDeMi());
        adah.actualizaDatosAlumno(id, fecha);
        return "perfilalumno";
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

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
