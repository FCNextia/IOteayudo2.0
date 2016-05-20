/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import logica.BuscarTutorHelper;
import modelo.Usuario;

/**
 *
 * @author rtaboada
 */
@ManagedBean
@ApplicationScoped
public class BuscarTutor {
    private List<Usuario> tutores; //Lista en donde guardaremos los tutores de la busqueda
    private Usuario tutor; //Lista donde guardaremos el tutor a buscar
    private String materia; //La materia por la cual buscaremos un tutor
    private boolean flag = true;

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
            String nueva = helper.verificaMateria(this.materia);
            //Si temp == nueva significa que no debemos mostrar un mensaje
            //de "quiza quisiste decir"
            //Asignamos la variable nueva a materia, ya que esta
            //esta correcta. Temp puede o no estar mal escrita.
            setMateria(nueva);
            setTutores(helper.verificaTutor(materia));
        }
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
