package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import logica.RegistroHelper;
import modelo.Usuario;

/**
 * Controlador que permite agregar un usuario a la base de datos.
 */
@ManagedBean
@RequestScoped
public class AltaUsuario {

    /* Id del usuario a registrar. */
    private int id;
    /* Correo electrónico del usuario a registrar. */
    private String correo;
    /* Contraseña del usuario a registrar. */
    private String contrasenia;
    /* Confirmación de la contraseña */
    private String confirmacion;
    /* Nombre del usuario a registrar. */
    private String nombre;
    /* Apellido paterno del usuario a registrar. */
    private String apellidop;
    /* Apellido materno del usuario a registrar. */
    private String apellidom;
    /* Nos indica si el usuario a registrar es tutor o no. */
    private boolean esTutor;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Permite el envío de mensajes entre el bean y la vista. */
    private FacesMessage message;
    /* Lógica que permitirá la conexión con la base de datos. */
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
     * @return La página de inicio si se creo correctamente el
     * usuario, en caso contrario a la página de registro.
     */
    public String darDeAltaUsuario() {
        /* Validamos el nombre y apellidos para que no contengan números o 
           caracteres extraños. */
        if (!validaNombre()) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Nombre o apellidos inválidos", null);
            faceContext.addMessage(null, message);
            return "registro";
        }
        /* Validamos que la contraseña y la confirmación sean iguales. */
        if (getContrasenia().equals(getConfirmacion())) {
            try {
                // construimos el nuevo usuario
                Usuario usuario = nuevoUsuario();
                if (!esTutor)
                    rh.registraUsuarioAlumno(usuario);
                else
                    rh.registraUsuarioTutor(usuario);
                return "pantallainicial";
            } catch (Exception e) {
                /* Le dejamos la validación del correo a la expresión regular 
                   de la base de datos. */
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Correo inválido", null);
                faceContext.addMessage(null, message);
                return "registro";
            }
        }
        /* Si las contraseñas no existen, mostramos un mensaje de error. */
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Las contraseñas no coinciden", null);
        faceContext.addMessage(null, message);
        return "registro";
    }
    
    /* Método auxiliar que valida que tanto el nombre como los apellidos estén
       formados únicamente de letras. */
    private boolean validaNombre() {
        return sonLetras(nombre) && sonLetras(apellidop) 
                && sonLetras(apellidom); 
    }
    
    /* Método auxiliar que verifica que una cadena esté formada de letras. */
    private boolean sonLetras(String cadena) {
        /* Quitamos espacios en blanco, pues deben ser aceptados. */
        cadena = cadena.replace(" ", "");
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (!Character.isLetter(c))
                return false;
        }
        return true;
    }

    /* MÉTODOS MODIFICADORES Y DE ACCESO. */
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

    public boolean isEsTutor() {
        return esTutor;
    }

    public void setEsTutor(boolean esTutor) {
        this.esTutor = esTutor;
    }
}
