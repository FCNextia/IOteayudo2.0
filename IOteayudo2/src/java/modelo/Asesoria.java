package modelo;
// Generated 19/05/2016 12:03:59 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Asesoria generated by hbm2java
 */
public class Asesoria  implements java.io.Serializable {


     private int idAsesoria;
     private int costo;
     private Date fecAsesoria;
     private String direccion;
     private String comentario;
     private Integer calificacionAlumno;
     private Integer calificacionTutor;
     private Set solicituds = new HashSet(0);

    public Asesoria() {
    }

	
    public Asesoria(int idAsesoria, int costo, Date fecAsesoria, String direccion) {
        this.idAsesoria = idAsesoria;
        this.costo = costo;
        this.fecAsesoria = fecAsesoria;
        this.direccion = direccion;
    }
    public Asesoria(int idAsesoria, int costo, Date fecAsesoria, String direccion, String comentario, Integer calificacionAlumno, Integer calificacionTutor, Set solicituds) {
       this.idAsesoria = idAsesoria;
       this.costo = costo;
       this.fecAsesoria = fecAsesoria;
       this.direccion = direccion;
       this.comentario = comentario;
       this.calificacionAlumno = calificacionAlumno;
       this.calificacionTutor = calificacionTutor;
       this.solicituds = solicituds;
    }
   
    public int getIdAsesoria() {
        return this.idAsesoria;
    }
    
    public void setIdAsesoria(int idAsesoria) {
        this.idAsesoria = idAsesoria;
    }
    public int getCosto() {
        return this.costo;
    }
    
    public void setCosto(int costo) {
        this.costo = costo;
    }
    public Date getFecAsesoria() {
        return this.fecAsesoria;
    }
    
    public void setFecAsesoria(Date fecAsesoria) {
        this.fecAsesoria = fecAsesoria;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Integer getCalificacionAlumno() {
        return this.calificacionAlumno;
    }
    
    public void setCalificacionAlumno(Integer calificacionAlumno) {
        this.calificacionAlumno = calificacionAlumno;
    }
    public Integer getCalificacionTutor() {
        return this.calificacionTutor;
    }
    
    public void setCalificacionTutor(Integer calificacionTutor) {
        this.calificacionTutor = calificacionTutor;
    }
    public Set getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set solicituds) {
        this.solicituds = solicituds;
    }




}


