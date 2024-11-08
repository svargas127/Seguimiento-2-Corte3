package org.carlosYvargas.InfraEstructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.carlosYvargas.Domain.Vuelo;
import org.carlosYvargas.Interface.ObjectRepository;

import java.util.List;

public class FileVueloRepository implements ObjectRepository {

    private final EntityManagerFactory emf;

    public FileVueloRepository() {
        emf = Persistence.createEntityManagerFactory("VueloUp");
    }


    @Override
    public void safeObject(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteObject(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Object object = em.find(Object.class, id);
            if (object != null) {
                em.remove(object);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateObject(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Object findObject(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Object.class, id);
        } finally {
            em.close();
        }
    }


    @Override
    public List<Vuelo> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Vuelo p", Vuelo.class)
                    .getResultList();
        }
    }



}
