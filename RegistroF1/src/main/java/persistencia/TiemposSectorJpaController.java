/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Practicas;
import logica.TiemposSector;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alexs
 */
public class TiemposSectorJpaController implements Serializable {

    public TiemposSectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

     public TiemposSectorJpaController() {
        emf = Persistence.createEntityManagerFactory("RegistroEstPU");
        //cuando se necesite usar cualquier metodo, crea una instancia para poder hacer uso de los metodos
    }      
    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiemposSector tiemposSector) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Practicas practica = tiemposSector.getPractica();
            if (practica != null) {
                practica = em.getReference(practica.getClass(), practica.getIdPractica());
                tiemposSector.setPractica(practica);
            }
            em.persist(tiemposSector);
            if (practica != null) {
                practica.getListaTiemposSector().add(tiemposSector);
                practica = em.merge(practica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiemposSector tiemposSector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiemposSector persistentTiemposSector = em.find(TiemposSector.class, tiemposSector.getIdTiempoSector());
            Practicas practicaOld = persistentTiemposSector.getPractica();
            Practicas practicaNew = tiemposSector.getPractica();
            if (practicaNew != null) {
                practicaNew = em.getReference(practicaNew.getClass(), practicaNew.getIdPractica());
                tiemposSector.setPractica(practicaNew);
            }
            tiemposSector = em.merge(tiemposSector);
            if (practicaOld != null && !practicaOld.equals(practicaNew)) {
                practicaOld.getListaTiemposSector().remove(tiemposSector);
                practicaOld = em.merge(practicaOld);
            }
            if (practicaNew != null && !practicaNew.equals(practicaOld)) {
                practicaNew.getListaTiemposSector().add(tiemposSector);
                practicaNew = em.merge(practicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiemposSector.getIdTiempoSector();
                if (findTiemposSector(id) == null) {
                    throw new NonexistentEntityException("The tiemposSector with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiemposSector tiemposSector;
            try {
                tiemposSector = em.getReference(TiemposSector.class, id);
                tiemposSector.getIdTiempoSector();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiemposSector with id " + id + " no longer exists.", enfe);
            }
            Practicas practica = tiemposSector.getPractica();
            if (practica != null) {
                practica.getListaTiemposSector().remove(tiemposSector);
                practica = em.merge(practica);
            }
            em.remove(tiemposSector);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiemposSector> findTiemposSectorEntities() {
        return findTiemposSectorEntities(true, -1, -1);
    }

    public List<TiemposSector> findTiemposSectorEntities(int maxResults, int firstResult) {
        return findTiemposSectorEntities(false, maxResults, firstResult);
    }

    private List<TiemposSector> findTiemposSectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TiemposSector.class));
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

    public TiemposSector findTiemposSector(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiemposSector.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiemposSectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TiemposSector> rt = cq.from(TiemposSector.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
