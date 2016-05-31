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
    private static String correo;
    private String nombre;
    private String nombreYApp;
    private String contrasenia;
    private int calificacion;
    private String acercaDe;
    private final HttpServletRequest httpServletRequest;

    public IniciarSesion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        session = httpServletRequest.getSession(true);
        helper = new IniciarSesionHelper();
    }

    public String iniciarSesion() {
        calificacion = 0;
        Usuario usuario = helper.getLoginPorCorreo(getCorreo());
        if (usuario != null) {
            if (getContrasenia().equals(usuario.getContrasenia())) {
                session.setAttribute("sessionUsuario", correo);
                session.setAttribute("idUsuario", usuario.getIdUsuario());
                String nombreYApp = usuario.getNombreUsuario() + " " + usuario.getApp();
                session.setAttribute("nombreYApp", nombreYApp);
                String nombreC = usuario.getNombreUsuario() + " " +  usuario.getApp() + " " + usuario.getApm();
                session.setAttribute("nombre", nombreC);
                session.setAttribute("calificacion", usuario.getCalificacion());
                session.setAttribute("acercade", usuario.getAcercaDe());
                if (usuario.getAlumno() != null)
                    return "perfilalumno";
                if (usuario.getTutor() != null)
                    return "perfiltutor";
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña incorrecto", null);
                faceContext.addMessage(null, message);
                return "iniciosesion";
            }
        }
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario no registrado.", null);
        faceContext.addMessage(null, message);
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

    public String getNombre() {
        return httpServletRequest.getSession().getAttribute("nombre").toString();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombreYApp(){
        return httpServletRequest.getSession().getAttribute("nombreYApp").toString();
    }
    
    public void setNombreYApp(String nombreYApp){
        this.nombreYApp = nombreYApp;
    }

    public int getCalificacion() {
        return Integer.parseInt(httpServletRequest.getSession().getAttribute("calificacion").toString());
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }    
    
    public int getIDUsuario() {
        return Integer.parseInt(httpServletRequest.getSession().getAttribute("idUsuario").toString());
    }

    public String getAcercaDe() {
        return httpServletRequest.getSession().getAttribute("acercade").toString();
    }

    public void setAcercaDe(String acercaDe) {
        this.acercaDe = acercaDe;
    }
}
