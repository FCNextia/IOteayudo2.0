package logica;

import modelo.Materia;
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
     * Método encargado de registrar una materia.
     * @param materia La materia a registrar
     * @throws TransactionException Se lanza la excepción en caso de un error
     * durante la transacción.
     */
    public void registraMateria(Materia materia) throws TransactionException {
        Transaction tx = session.beginTransaction();
        session.persist(materia);
        tx.commit();
    }
}
