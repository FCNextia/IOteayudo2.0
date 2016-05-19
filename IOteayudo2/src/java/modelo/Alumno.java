package modelo;
// Generated 19/05/2016 12:03:59 AM by Hibernate Tools 4.3.1


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
     private Set solicituds = new HashSet(0);

    public Alumno() {
    }

	
    public Alumno(Usuario usuario, Date fecNac) {
        this.usuario = usuario;
        this.fecNac = fecNac;
    }
    public Alumno(Usuario usuario, Date fecNac, Set solicituds) {
       this.usuario = usuario;
       this.fecNac = fecNac;
       this.solicituds = solicituds;
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
    public Set getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set solicituds) {
        this.solicituds = solicituds;
    }




}


