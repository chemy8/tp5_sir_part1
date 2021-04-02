package jpa.dao;

import jpa.Tableau;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class TableauDAO {
    private EntityManager em;

    public TableauDAO(EntityManager manager) {
        this.em = manager;
    }

    public void enregistrer(Tableau tab) {
        EntityTransaction tx = this.em.getTransaction();
        tx.begin();
        this.em.persist(tab);
        tx.commit();
    }

    public List<Tableau> getAllTableau() {
        String req = "select t from Tableau as t";
        Query query = this.em.createQuery(req);
        return query.getResultList();
    }
}
