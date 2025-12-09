/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Practicas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.TipoTester;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alexs
 */
public class TipoTesterJpaController implements Serializable {

    public TipoTesterJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

     public TipoTesterJpaController() {
        emf = Persistence.createEntityManagerFactory("RegistroEstPU");
        //cuando se necesite usar cualquier metodo, crea una instancia para poder hacer uso de los metodos
    }  

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   
    
    public void create(TipoTester tipoTester) {
        if (tipoTester.getListaPracticas() == null) {
            tipoTester.setListaPracticas(new ArrayList<Practicas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Practicas> attachedListaPracticas = new ArrayList<Practicas>();
            for (Practicas listaPracticasPracticasToAttach : tipoTester.getListaPracticas()) {
                listaPracticasPracticasToAttach = em.getReference(listaPracticasPracticasToAttach.getClass(), listaPracticasPracticasToAttach.getIdPractica());
                attachedListaPracticas.add(listaPracticasPracticasToAttach);
            }
            tipoTester.setListaPracticas(attachedListaPracticas);
            em.persist(tipoTester);
            for (Practicas listaPracticasPracticas : tipoTester.getListaPracticas()) {
                TipoTester oldTipoTesterOfListaPracticasPracticas = listaPracticasPracticas.getTipoTester();
                listaPracticasPracticas.setTipoTester(tipoTester);
                listaPracticasPracticas = em.merge(listaPracticasPracticas);
                if (oldTipoTesterOfListaPracticasPracticas != null) {
                    oldTipoTesterOfListaPracticasPracticas.getListaPracticas().remove(listaPracticasPracticas);
                    oldTipoTesterOfListaPracticasPracticas = em.merge(oldTipoTesterOfListaPracticasPracticas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoTester tipoTester) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTester persistentTipoTester = em.find(TipoTester.class, tipoTester.getIdTipoTester());
            List<Practicas> listaPracticasOld = persistentTipoTester.getListaPracticas();
            List<Practicas> listaPracticasNew = tipoTester.getListaPracticas();
            List<Practicas> attachedListaPracticasNew = new ArrayList<Practicas>();
            for (Practicas listaPracticasNewPracticasToAttach : listaPracticasNew) {
                listaPracticasNewPracticasToAttach = em.getReference(listaPracticasNewPracticasToAttach.getClass(), listaPracticasNewPracticasToAttach.getIdPractica());
                attachedListaPracticasNew.add(listaPracticasNewPracticasToAttach);
            }
            listaPracticasNew = attachedListaPracticasNew;
            tipoTester.setListaPracticas(listaPracticasNew);
            tipoTester = em.merge(tipoTester);
            for (Practicas listaPracticasOldPracticas : listaPracticasOld) {
                if (!listaPracticasNew.contains(listaPracticasOldPracticas)) {
                    listaPracticasOldPracticas.setTipoTester(null);
                    listaPracticasOldPracticas = em.merge(listaPracticasOldPracticas);
                }
            }
            for (Practicas listaPracticasNewPracticas : listaPracticasNew) {
                if (!listaPracticasOld.contains(listaPracticasNewPracticas)) {
                    TipoTester oldTipoTesterOfListaPracticasNewPracticas = listaPracticasNewPracticas.getTipoTester();
                    listaPracticasNewPracticas.setTipoTester(tipoTester);
                    listaPracticasNewPracticas = em.merge(listaPracticasNewPracticas);
                    if (oldTipoTesterOfListaPracticasNewPracticas != null && !oldTipoTesterOfListaPracticasNewPracticas.equals(tipoTester)) {
                        oldTipoTesterOfListaPracticasNewPracticas.getListaPracticas().remove(listaPracticasNewPracticas);
                        oldTipoTesterOfListaPracticasNewPracticas = em.merge(oldTipoTesterOfListaPracticasNewPracticas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipoTester.getIdTipoTester();
                if (findTipoTester(id) == null) {
                    throw new NonexistentEntityException("The tipoTester with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTester tipoTester;
            try {
                tipoTester = em.getReference(TipoTester.class, id);
                tipoTester.getIdTipoTester();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoTester with id " + id + " no longer exists.", enfe);
            }
            List<Practicas> listaPracticas = tipoTester.getListaPracticas();
            for (Practicas listaPracticasPracticas : listaPracticas) {
                listaPracticasPracticas.setTipoTester(null);
                listaPracticasPracticas = em.merge(listaPracticasPracticas);
            }
            em.remove(tipoTester);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoTester> findTipoTesterEntities() {
        return findTipoTesterEntities(true, -1, -1);
    }

    public List<TipoTester> findTipoTesterEntities(int maxResults, int firstResult) {
        return findTipoTesterEntities(false, maxResults, firstResult);
    }

    private List<TipoTester> findTipoTesterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoTester.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoTester findTipoTester(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoTester.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoTesterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoTester> rt = cq.from(TipoTester.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
