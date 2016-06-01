package controlador;

import org.hibernate.TransactionException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import logica.RegistroHelper;
import modelo.Usuario;

/**
 * Controlador que permite agregar un usuario a la base de datos.
 *
 * @author
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class AltaUsuario {

    /*
     * DATOS SUFICIENTES PARA DAR DE ALTA A UN USUARIO.
     */

    /*
     * ID del usuario.
     */
    private int id;
    /*
     * Correo electrónico del usuario.
     */
    private String correo;
    /*
     * Contraseña del usuario.
     */
    private String contrasenia;
    /*
     * Confirmación de la contraseña.
     */
    private String confirmacion;
    /*
     * Nombre del usuario.
     */
    private String nombre;
    /*
     * Apellido paterno del usuario.
     */
    private String apellidop;
    /*
     * Apellido materno del usario.
     */
    private String apellidom;
    /*
     * Nos indica si es alumno o tutor.
     */
    private boolean esAlumno;
    /*
     * Nos indica si es alumno o tutor.
     */
    private boolean esTutor;
    /*
     * Obtiene información de la aplicación.
     */
    private final FacesContext faceContext;
    /*
     * Permite el envio de mensajes entre el bean y la vista.
     */
    private FacesMessage message;
    private final RegistroHelper rh;

    /**
     * Constructor por omisión. Inicializa los objetos necesarios para
     * comunicarnos con la vista.
     */
    public AltaUsuario() {
        faceContext = FacesContext.getCurrentInstance();
        rh = new RegistroHelper();
    }

    /**
     * Construye un usuario con la información recibida por el
     * formulario.
     *
     * @return Un nuevo usuario.
     */
    private Usuario nuevoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombre);
        usuario.setApp(apellidop);
        usuario.setApm(apellidom);
        usuario.setContrasenia(contrasenia);
        usuario.setCorreo(correo);
        usuario.setTelefono(new Long(0));
        usuario.setAcercaDe("Bienvenido a mi perfil");
        usuario.setAsesorias(0);
        usuario.setCalificacion(0);
        return usuario;
    }

    /**
     * Da de alta al usuario/alumno y lo redirige a su perfil.
     *
     * @return La página de inicio si se creo correctamente el
     * usuario, en caso contrario a la página de registro.
     */
    public String darDeAltaUsuario() {
        if (!validaNombre()) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre o apellidos inválidos", null);
            faceContext.addMessage(null, message);
            return "registro";
        }
        if (getContrasenia().equals(getConfirmacion())) {
            try {
                Usuario usuario = nuevoUsuario();
                if (!esTutor) {
                    rh.registraUsuarioAlumno(usuario);
                } else {
                    rh.registraUsuarioTutor(usuario);
                }
                return "pantallainicial";
            } catch (Exception e) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo inválido", null);
                faceContext.addMessage(null, message);
                return "registro";
            }
        }
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", null);
        faceContext.addMessage(null, message);
        return "registro";
    }
    
    private boolean validaNombre() {
        return sonLetras(nombre) && sonLetras(apellidop) && sonLetras(apellidom); 
    }
    
    private boolean sonLetras(String cadena) {
        cadena = cadena.replace(" ", "");
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (!Character.isLetter(c))
                return false;
        }
        return true;
    }

    /**
     * MÉTODOS DE MODIFICADORES Y DE ACCESO PARA COMUNICARNOS CON LA
     * VISTA
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
