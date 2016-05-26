package modelo;
// Generated 26/05/2016 04:28:37 PM by Hibernate Tools 4.3.1



/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private int idUsuario;
     private String correo;
     private String nombreUsuario;
     private String app;
     private String apm;
     private String contrasenia;
     private Long telefono;
     private String acercaDe;
     private Integer calificacion;
     private Alumno alumno;
     private Tutor tutor;

    public Usuario() {
    }

	
    public Usuario(int idUsuario, String correo, String nombreUsuario, String app, String apm, String contrasenia) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.app = app;
        this.apm = apm;
        this.contrasenia = contrasenia;
    }
    public Usuario(int idUsuario, String correo, String nombreUsuario, String app, String apm, String contrasenia, Long telefono, String acercaDe, Integer calificacion, Alumno alumno, Tutor tutor) {
       this.idUsuario = idUsuario;
       this.correo = correo;
       this.nombreUsuario = nombreUsuario;
       this.app = app;
       this.apm = apm;
       this.contrasenia = contrasenia;
       this.telefono = telefono;
       this.acercaDe = acercaDe;
       this.calificacion = calificacion;
       this.alumno = alumno;
       this.tutor = tutor;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getApp() {
        return this.app;
    }
    
    public void setApp(String app) {
        this.app = app;
    }
    public String getApm() {
        return this.apm;
    }
    
    public void setApm(String apm) {
        this.apm = apm;
    }
    public String getContrasenia() {
        return this.contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public Long getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    public String getAcercaDe() {
        return this.acercaDe;
    }
    
    public void setAcercaDe(String acercaDe) {
        this.acercaDe = acercaDe;
    }
    public Integer getCalificacion() {
        return this.calificacion;
    }
    
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    public Alumno getAlumno() {
        return this.alumno;
    }
    
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    public Tutor getTutor() {
        return this.tutor;
    }
    
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }




}


