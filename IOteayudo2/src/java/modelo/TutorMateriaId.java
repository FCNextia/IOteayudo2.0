package modelo;
// Generated 19/05/2016 12:03:59 AM by Hibernate Tools 4.3.1



/**
 * TutorMateriaId generated by hbm2java
 */
public class TutorMateriaId  implements java.io.Serializable {


     private int idUsuario;
     private int idMateria;

    public TutorMateriaId() {
    }

    public TutorMateriaId(int idUsuario, int idMateria) {
       this.idUsuario = idUsuario;
       this.idMateria = idMateria;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdMateria() {
        return this.idMateria;
    }
    
    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TutorMateriaId) ) return false;
		 TutorMateriaId castOther = ( TutorMateriaId ) other; 
         
		 return (this.getIdUsuario()==castOther.getIdUsuario())
 && (this.getIdMateria()==castOther.getIdMateria());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdUsuario();
         result = 37 * result + this.getIdMateria();
         return result;
   }   


}


