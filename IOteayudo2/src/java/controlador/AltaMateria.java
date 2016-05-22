package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import logica.RegistoMateriaHelper;
import modelo.Materia;
import org.hibernate.Query;
import javax.servlet.http.HttpServletRequest;

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
    /* Lógica para registrar la materia. */
    private final RegistoMateriaHelper rmh;
    /* Para trabajar con sesiones. */
    private final HttpServletRequest httpServletRequest;
    
    /**
     * Constructor por omisión. Inicializa los objetos necesarios para 
     * comunicarnos con la vista.
     */
    public AltaMateria() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
        rmh = new RegistoMateriaHelper();
    }
    
    /**
     * Método para construir materias. Construye una materia con la información
     * recibida por el formulario correspondiente.
     * @return Materia lista para usarse.
     */
    public Materia construyeMateria() {
        Materia materia = new Materia();
        materia.setIdMateria(1729); // tenemos que agregar un serial
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
        /* Obtenemos el id del usuario actual. */
        int idTutor = Integer.parseInt(httpServletRequest.getSession().getAttribute("idUsuario").toString());
        rmh.registraMateria(materia, idTutor);
        return "perfiltutor";
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
