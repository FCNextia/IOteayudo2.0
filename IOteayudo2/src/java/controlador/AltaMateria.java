package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Materia;

/**
 * Clase para dar de alta una materia.
 * @author Manuel Soto Romero
 */
@ManagedBean
@RequestScoped
public class AltaMateria {
    
    /* Id de la materia a crear. */
    private int idMateria;
    /* Nombre de la materia a crear. */
    private String nombre;
    /* Área de la materia a crear. */
    private int area;
    /* Obtiene información de la aplicación. */
    private final FacesContext faceContext;
    /* Para mostrar mensajes en la vista. */
    private FacesMessage message;
    
    /**
     * Constructor por omisión. Inicializa los objetos necesarios para 
     * comunicarnos con la vista.
     */
    public AltaMateria() {
        faceContext = FacesContext.getCurrentInstance();
    }
    
    /**
     * Método para construir materias. Construye una materia con la información
     * recibida por el formulario correspondiente.
     * @return Materia lista para usarse.
     */
    public Materia construyeMateria() {
        Materia materia = new Materia();
        materia.setIdMateria(idMateria);
        materia.setNombreMateria(nombre);
        materia.setAreaMateria(area);
        return materia;
    }
    
    /**
     * Método para dar de alta una materia. Da de alta la materia y redirige al
     * usuario (tutor) a su perfil.
     * @return Dirección de la página de perfil si los datos son correctos, en
     * caso contrario, se mantiene en la página de registro de materias.
     */
    public String daDeAltaMateria() {
        Materia materia = construyeMateria();
        // logica para guardar materia
        return "perfiltutor";
    }
}
