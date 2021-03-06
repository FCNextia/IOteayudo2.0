package modelo;
// Generated 30/05/2016 09:56:51 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Alumno generated by hbm2java
 */
public class Alumno  implements java.io.Serializable {


     private int idUsuario;
     private Usuario usuario;
     private Date fecNac;
     private Set asesorias = new HashSet(0);

    public Alumno() {
    }

	
    public Alumno(Usuario usuario, Date fecNac) {
        this.usuario = usuario;
        this.fecNac = fecNac;
    }
    public Alumno(Usuario usuario, Date fecNac, Set asesorias) {
       this.usuario = usuario;
       this.fecNac = fecNac;
       this.asesorias = asesorias;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getFecNac() {
        return this.fecNac;
    }
    
    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }
    public Set getAsesorias() {
        return this.asesorias;
    }
    
    public void setAsesorias(Set asesorias) {
        this.asesorias = asesorias;
    }




}


