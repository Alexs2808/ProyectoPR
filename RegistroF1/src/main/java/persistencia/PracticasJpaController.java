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
import logica.Pistas;
import logica.Pilotos;
import logica.Practicas;
import logica.TipoTester;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alexs
 */
public class PracticasJpaController implements Serializable {

    public PracticasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PracticasJpaController() {
        emf = Persistence.createEntityManagerFactory("RegistroEstPU");
        //cuando se necesite usar cualquier metodo, crea una instancia para poder hacer uso de los metodos
    }      
    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Practicas practicas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pistas pista = practicas.getPista();
            if (pista != null) {
                pista = em.getReference(pista.getClass(), pista.getIdPista());
                practicas.setPista(pista);
            }
            Pilotos piloto = practicas.getPiloto();
            if (piloto != null) {
                piloto = em.getReference(piloto.getClass(), piloto.getIdPiloto());
                practicas.setPiloto(piloto);
            }
            TipoTester tipoTester = practicas.getTipoTester();
            if (tipoTester != null) {
                tipoTester = em.getReference(tipoTester.getClass(), tipoTester.getIdTipoTester());
                practicas.setTipoTester(tipoTester);
            }
            em.persist(practicas);
            if (pista != null) {
                pista.getListaPracticas().add(practicas);
                pista = em.merge(pista);
            }
            if (piloto != null) {
                piloto.getListaPracticas().add(practicas);
                piloto = em.merge(piloto);
            }
            if (tipoTester != null) {
                tipoTester.getListaPracticas().add(practicas);
                tipoTester = em.merge(tipoTester);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Practicas practicas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Practicas persistentPracticas = em.find(Practicas.class, practicas.getIdPractica());
            Pistas pistaOld = persistentPracticas.getPista();
            Pistas pistaNew = practicas.getPista();
            Pilotos pilotoOld = persistentPracticas.getPiloto();
            Pilotos pilotoNew = practicas.getPiloto();
            TipoTester tipoTesterOld = persistentPracticas.getTipoTester();
            TipoTester tipoTesterNew = practicas.getTipoTester();
            if (pistaNew != null) {
                pistaNew = em.getReference(pistaNew.getClass(), pistaNew.getIdPista());
                practicas.setPista(pistaNew);
            }
            if (pilotoNew != null) {
                pilotoNew = em.getReference(pilotoNew.getClass(), pilotoNew.getIdPiloto());
                practicas.setPiloto(pilotoNew);
            }
            if (tipoTesterNew != null) {
                tipoTesterNew = em.getReference(tipoTesterNew.getClass(), tipoTesterNew.getIdTipoTester());
                practicas.setTipoTester(tipoTesterNew);
            }
            practicas = em.merge(practicas);
            if (pistaOld != null && !pistaOld.equals(pistaNew)) {
                pistaOld.getListaPracticas().remove(practicas);
                pistaOld = em.merge(pistaOld);
            }
            if (pistaNew != null && !pistaNew.equals(pistaOld)) {
                pistaNew.getListaPracticas().add(practicas);
                pistaNew = em.merge(pistaNew);
            }
            if (pilotoOld != null && !pilotoOld.equals(pilotoNew)) {
                pilotoOld.getListaPracticas().remove(practicas);
                pilotoOld = em.merge(pilotoOld);
            }
            if (pilotoNew != null && !pilotoNew.equals(pilotoOld)) {
                pilotoNew.getListaPracticas().add(practicas);
                pilotoNew = em.merge(pilotoNew);
            }
            if (tipoTesterOld != null && !tipoTesterOld.equals(tipoTesterNew)) {
                tipoTesterOld.getListaPracticas().remove(practicas);
                tipoTesterOld = em.merge(tipoTesterOld);
            }
            if (tipoTesterNew != null && !tipoTesterNew.equals(tipoTesterOld)) {
                tipoTesterNew.getListaPracticas().add(practicas);
                tipoTesterNew = em.merge(tipoTesterNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = practicas.getIdPractica();
                if (findPracticas(id) == null) {
                    throw new NonexistentEntityException("The practicas with id " + id + " no longer exists.");
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
            Practicas practicas;
            try {
                practicas = em.getReference(Practicas.class, id);
                practicas.getIdPractica();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The practicas with id " + id + " no longer exists.", enfe);
            }
            Pistas pista = practicas.getPista();
            if (pista != null) {
                pista.getListaPracticas().remove(practicas);
                pista = em.merge(pista);
            }
            Pilotos piloto = practicas.getPiloto();
            if (piloto != null) {
                piloto.getListaPracticas().remove(practicas);
                piloto = em.merge(piloto);
            }
            TipoTester tipoTester = practicas.getTipoTester();
            if (tipoTester != null) {
                tipoTester.getListaPracticas().remove(practicas);
                tipoTester = em.merge(tipoTester);
            }
            em.remove(practicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Practicas> findPracticasEntities() {
        return findPracticasEntities(true, -1, -1);
    }

    public List<Practicas> findPracticasEntities(int maxResults, int firstResult) {
        return findPracticasEntities(false, maxResults, firstResult);
    }

    private List<Practicas> findPracticasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Practicas.class));
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

    public Practicas findPracticas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Practicas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPracticasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Practicas> rt = cq.from(Practicas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
