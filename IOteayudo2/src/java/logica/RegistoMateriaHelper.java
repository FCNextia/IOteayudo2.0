package logica;

import modelo.Materia;
import modelo.Tutor;
import modelo.TutorMateria;
import modelo.TutorMateriaId;
import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

/**
 * Clase encargada de proporciona toda la lógica para el registro de materias.
 * @author Manuel Soto Romero
 */
public class RegistoMateriaHelper {
    
    /* Para actualizar la base. */
    private final Session session;
    
    /**
     * Constructor por omisión. Inicializa el objeto para trabajar con la base.
     */
    public RegistoMateriaHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    /**
     * Método que consturye un TutorMateriaId.
     */
    public TutorMateriaId construyeTMI(Materia m, int id) {
        TutorMateriaId tmi = new TutorMateriaId();
        tmi.setIdMateria(m.getIdMateria());
        tmi.setIdUsuario(id);
        return tmi;
    }
    
    /**
     * Método que construye un TutorMateria.
     */
    public TutorMateria construyeTM(TutorMateriaId tmi, Materia m, Tutor t) {
        TutorMateria tm = new TutorMateria();
        tm.setId(tmi);
        tm.setMateria(m);
        tm.setTutor(t);
        return tm;
    }
    
    /**
     * Método encargado de registrar una materia.
     * @param materia La materia a registrar
     * @throws TransactionException Se lanza la excepción en caso de un error
     * durante la transacción.
     */
    public void registraMateria(int idMateria, int idTutor) throws TransactionException {
        Transaction tx = session.beginTransaction();
        Query p = session.getNamedQuery("BuscaMateriaPorID").setInteger("idMateria", idMateria);
        Materia m = (Materia)p.uniqueResult();
        TutorMateriaId tmi = construyeTMI(m, idTutor);
        Query q = session.getNamedQuery("BuscaTutorPorID").setInteger("idUsuario", idTutor);
        Tutor t = (Tutor)q.uniqueResult();
        TutorMateria tm = construyeTM(tmi, m, t);
        session.persist(tm);
        tx.commit();
    }
}
