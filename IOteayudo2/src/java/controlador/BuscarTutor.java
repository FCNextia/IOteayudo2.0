package controlador;

import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import logica.BuscarTutorHelper;
import modelo.Usuario;

/**
 * Clase encargada de realizar las búsquedas del usuario.
 */
@ManagedBean
@ApplicationScoped
public class BuscarTutor {
    private List<Usuario> tutores; //Lista en donde guardaremos los tutores de la busqueda
    private Usuario tutor;         //Lista donde guardaremos el tutor a buscar
    private String materia;        //La materia por la cual buscaremos un tutor
    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String showFirstTutor(){
        encontrarTutor();
        return tutores.get(0).getNombreUsuario();
    }

    public void encontrarTutor(){
        BuscarTutorHelper helper = new BuscarTutorHelper();
        //Como este metodo se invoca despues de haberle asignado la materia
        //al bean. Se supone que el String materia no es nulo.
        //Aun asi, se preguntara por si un usuario invoca primero este metodo
        if(materia != null){
            //Si temp == nueva significa que no debemos mostrar un mensaje
            //de "quiza quisiste decir"
            //Asignamos la variable nueva a materia, ya que esta
            //esta correcta. Temp puede o no estar mal escrita.
            setTutores(helper.verificaTutor(materia));
        }
    }

    public String solicitud(){
        materia = null;
        tutores = null;
        flag = true;
        return "perfiltutor";
    }
    
    public Usuario getTutor() {
        return tutor;
    }
    

    public void setTutor(Usuario tutor) {
        this.tutor = tutor;
    }

    public List<Usuario> getTutores() {
        return tutores;
    }

    public void setTutores(List<Usuario> tutores) {
        this.tutores = tutores;
    }

    /**
     * Metodo contructor de la clase
     */
    public BuscarTutor() {
        System.out.println("Iniciando Buscar Tutor");
    }

}
