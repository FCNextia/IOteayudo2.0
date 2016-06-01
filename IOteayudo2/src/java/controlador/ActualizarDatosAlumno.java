package controlador;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import logica.ActualizarDatosAlumnoHelper;

/**
 * Controlador que permite agregar un usuario a la base de datos.
 */
@ManagedBean
@RequestScoped
public class ActualizarDatosAlumno {
    
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
    /* Fecha de nacimiento. */
    private Date fecha;
    private String celular;
    /* Descripción del alumno. */
    private String acercaDeMi;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Permite el envio de mensajes entre el bean y la vista. */
    private FacesMessage message;
    /* Helper que se encargará de acltualizar los datos. */
    private final ActualizarDatosAlumnoHelper adah;
    
    /**
     * Constructor por omisión.
     * Inicializa los objetos necesarios para comunicarnos con la vista.
     */
    public ActualizarDatosAlumno() {
        faceContext = FacesContext.getCurrentInstance();
        adah = new ActualizarDatosAlumnoHelper();
        CerrarSesion cs = new CerrarSesion();
    }
    
    /**
     * Da de alta al usuario y lo redirige a su perfil.
     * @return Dirección de la vista perfil.
     */
    public String actualizarDatos() {
        if (!getContrasenia().equals(getConfirmacion()) 
                && !getContrasenia().isEmpty()) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Las contraseñas no coinciden", null);
            faceContext.addMessage(null, message);
            return "configuracionalumno";
        }
        long cel = 0;
        /* Convertimos el celular a número. */
        if (!getCelular().isEmpty())
            cel = Long.parseLong(getCelular());
        if (getFecha() == null)
            setFecha(new Date(94,10,10));
        /* Obtenemos la sesión actual */
        CerrarSesion cs = new CerrarSesion();
        String mail = cs.getCorreo();
        /* Obtenemos la fecha. */
        int id = adah.actualizaDatos(mail, getContrasenia(), getNombre(), 
                getApellidop(),getApellidom(), cel, getAcercaDeMi());
        /* Actualizamos los datos del alumno. */
        adah.actualizaDatosAlumno(id, getFecha());
        /* Redirigimos al perfil del alumno. */
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
