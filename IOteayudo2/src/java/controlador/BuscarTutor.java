/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import logica.BuscarTutorHelper;
import modelo.Usuario;

/**
 *
 * @author rtaboada
 */
@Named(value = "buscarTutor")
@ApplicationScoped
public class BuscarTutor {
    private List<Usuario> tutores; //Lista en donde guardaremos los tutores de la busqueda
    private Usuario tutor; //Lista donde guardaremos el tutor a buscar
    private String materia; //La materia por la cual buscaremos un tutor

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
        setTutores(helper.verificaTutor(materia));
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
