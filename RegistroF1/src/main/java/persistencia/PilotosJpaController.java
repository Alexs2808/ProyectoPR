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
import logica.Pilotos;
import logica.Usuarios;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alexs
 */
public class PilotosJpaController implements Serializable {

    public PilotosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PilotosJpaController() {
        emf = Persistence.createEntityManagerFactory("RegistroEstPU");
        //cuando se necesite usar cualquier metodo, crea una instancia para poder hacer uso de los metodos
    }      
    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pilotos pilotos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios equipo = pilotos.getEquipo();
            if (equipo != null) {
                equipo = em.getReference(equipo.getClass(), equipo.getIdEquipo());
                pilotos.setEquipo(equipo);
            }
            em.persist(pilotos);
            if (equipo != null) {
                equipo.getListaPilotos().add(pilotos);
                equipo = em.merge(equipo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pilotos pilotos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pilotos persistentPilotos = em.find(Pilotos.class, pilotos.getIdPiloto());
            Usuarios equipoOld = persistentPilotos.getEquipo();
            Usuarios equipoNew = pilotos.getEquipo();
            if (equipoNew != null) {
                equipoNew = em.getReference(equipoNew.getClass(), equipoNew.getIdEquipo());
                pilotos.setEquipo(equipoNew);
            }
            pilotos = em.merge(pilotos);
            if (equipoOld != null && !equipoOld.equals(equipoNew)) {
                equipoOld.getListaPilotos().remove(pilotos);
                equipoOld = em.merge(equipoOld);
            }
            if (equipoNew != null && !equipoNew.equals(equipoOld)) {
                equipoNew.getListaPilotos().add(pilotos);
                equipoNew = em.merge(equipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pilotos.getIdPiloto();
                if (findPilotos(id) == null) {
                    throw new NonexistentEntityException("The pilotos with id " + id + " no longer exists.");
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
            Pilotos pilotos;
            try {
                pilotos = em.getReference(Pilotos.class, id);
                pilotos.getIdPiloto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pilotos with id " + id + " no longer exists.", enfe);
            }
            Usuarios equipo = pilotos.getEquipo();
            if (equipo != null) {
                equipo.getListaPilotos().remove(pilotos);
                equipo = em.merge(equipo);
            }
            em.remove(pilotos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pilotos> findPilotosEntities() {
        return findPilotosEntities(true, -1, -1);
    }

    public List<Pilotos> findPilotosEntities(int maxResults, int firstResult) {
        return findPilotosEntities(false, maxResults, firstResult);
    }

    private List<Pilotos> findPilotosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pilotos.class));
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

    public Pilotos findPilotos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pilotos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPilotosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pilotos> rt = cq.from(Pilotos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
