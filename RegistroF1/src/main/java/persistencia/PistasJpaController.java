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
import logica.Pistas;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Alexs
 */
public class PistasJpaController implements Serializable {

    public PistasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PistasJpaController() {
        emf = Persistence.createEntityManagerFactory("RegistroEstPU");
        //cuando se necesite usar cualquier metodo, crea una instancia para poder hacer uso de los metodos
    }  
    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pistas pistas) {
        if (pistas.getListaPracticas() == null) {
            pistas.setListaPracticas(new ArrayList<Practicas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Practicas> attachedListaPracticas = new ArrayList<Practicas>();
            for (Practicas listaPracticasPracticasToAttach : pistas.getListaPracticas()) {
                listaPracticasPracticasToAttach = em.getReference(listaPracticasPracticasToAttach.getClass(), listaPracticasPracticasToAttach.getIdPractica());
                attachedListaPracticas.add(listaPracticasPracticasToAttach);
            }
            pistas.setListaPracticas(attachedListaPracticas);
            em.persist(pistas);
            for (Practicas listaPracticasPracticas : pistas.getListaPracticas()) {
                Pistas oldPistaOfListaPracticasPracticas = listaPracticasPracticas.getPista();
                listaPracticasPracticas.setPista(pistas);
                listaPracticasPracticas = em.merge(listaPracticasPracticas);
                if (oldPistaOfListaPracticasPracticas != null) {
                    oldPistaOfListaPracticasPracticas.getListaPracticas().remove(listaPracticasPracticas);
                    oldPistaOfListaPracticasPracticas = em.merge(oldPistaOfListaPracticasPracticas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pistas pistas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pistas persistentPistas = em.find(Pistas.class, pistas.getIdPista());
            List<Practicas> listaPracticasOld = persistentPistas.getListaPracticas();
            List<Practicas> listaPracticasNew = pistas.getListaPracticas();
            List<Practicas> attachedListaPracticasNew = new ArrayList<Practicas>();
            for (Practicas listaPracticasNewPracticasToAttach : listaPracticasNew) {
                listaPracticasNewPracticasToAttach = em.getReference(listaPracticasNewPracticasToAttach.getClass(), listaPracticasNewPracticasToAttach.getIdPractica());
                attachedListaPracticasNew.add(listaPracticasNewPracticasToAttach);
            }
            listaPracticasNew = attachedListaPracticasNew;
            pistas.setListaPracticas(listaPracticasNew);
            pistas = em.merge(pistas);
            for (Practicas listaPracticasOldPracticas : listaPracticasOld) {
                if (!listaPracticasNew.contains(listaPracticasOldPracticas)) {
                    listaPracticasOldPracticas.setPista(null);
                    listaPracticasOldPracticas = em.merge(listaPracticasOldPracticas);
                }
            }
            for (Practicas listaPracticasNewPracticas : listaPracticasNew) {
                if (!listaPracticasOld.contains(listaPracticasNewPracticas)) {
                    Pistas oldPistaOfListaPracticasNewPracticas = listaPracticasNewPracticas.getPista();
                    listaPracticasNewPracticas.setPista(pistas);
                    listaPracticasNewPracticas = em.merge(listaPracticasNewPracticas);
                    if (oldPistaOfListaPracticasNewPracticas != null && !oldPistaOfListaPracticasNewPracticas.equals(pistas)) {
                        oldPistaOfListaPracticasNewPracticas.getListaPracticas().remove(listaPracticasNewPracticas);
                        oldPistaOfListaPracticasNewPracticas = em.merge(oldPistaOfListaPracticasNewPracticas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pistas.getIdPista();
                if (findPistas(id) == null) {
                    throw new NonexistentEntityException("The pistas with id " + id + " no longer exists.");
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
            Pistas pistas;
            try {
                pistas = em.getReference(Pistas.class, id);
                pistas.getIdPista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pistas with id " + id + " no longer exists.", enfe);
            }
            List<Practicas> listaPracticas = pistas.getListaPracticas();
            for (Practicas listaPracticasPracticas : listaPracticas) {
                listaPracticasPracticas.setPista(null);
                listaPracticasPracticas = em.merge(listaPracticasPracticas);
            }
            em.remove(pistas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pistas> findPistasEntities() {
        return findPistasEntities(true, -1, -1);
    }

    public List<Pistas> findPistasEntities(int maxResults, int firstResult) {
        return findPistasEntities(false, maxResults, firstResult);
    }

    private List<Pistas> findPistasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pistas.class));
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

    public Pistas findPistas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pistas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPistasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pistas> rt = cq.from(Pistas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
