/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import logica.IniciarSesionHelper;
import modelo.Usuario;

/**
 *
 * @author yosh
 */
@ManagedBean
@RequestScoped
public class IniciarSesion {

    private final IniciarSesionHelper helper;
    private final HttpSession session;
    private final FacesContext faceContext; // Obtiene información de la aplicación
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista
    private String correo;
    private String contrasenia;

    public IniciarSesion() {
        faceContext = FacesContext.getCurrentInstance();
        HttpServletRequest httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        session = httpServletRequest.getSession(true);
        helper = new IniciarSesionHelper();
    }

    public String iniciarSesion() {
        Usuario usuario = helper.getLoginPorCorreo(getCorreo());
        if (usuario != null) {
            // TODO(MAPA): Hay que garantizar una mejor forma de validar las contraseñas
            if (getContrasenia().equals(usuario.getContrasenia())) {
                session.setAttribute("sessionUsuario", correo);
                if (usuario.getAlumno() != null) {
                    return "perfilalumno";
                }
                if (usuario.getTutor() != null) {
                    return "perfiltutor";
                } else {
                    // Esto está raro, ¿hay algún caso donde no sea ni tutor ni alumno?
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario no encontrado.", null);
                    faceContext.addMessage(null, message);
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                faceContext.addMessage(null, message);
                return "iniciosesion";
            }
        }
        return "iniciosesion";
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
}
