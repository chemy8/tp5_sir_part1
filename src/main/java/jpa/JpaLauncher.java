package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaLauncher {

    private EntityManager manager;

    public JpaLauncher(EntityManager manager) {

        this.manager = manager;
    }
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        JpaLauncher test = new JpaLauncher(manager);
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            //test.createUser();
            test.createFiches();
            test.createSondageDate();
            test.createSondageLieu();
            test.selectSondage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tx.commit();

        //test.listEmployees();

        manager.close();
        System.out.println(".. done");


    }
    /**
     * cr�e une fiche
     */
    private void createFiches() {
        int numOfEmployees = manager.createQuery("Select f From Fiche f", Fiche.class).getResultList().size();
        if (numOfEmployees == 0) {
            User user = new User("Bah", "Adama", 20);
            Tableau tab = new Tableau("Kanban", user);
            Section section  = new Section("En Attente", tab);
            manager.persist(section);
            manager.persist(new Fiche("Conception", "2021-03-21", "tags", "Rennes",
                    "https://trello.com/b/YAgZ49uf/mod%C3%A8le-kanban",
                    "Une liste des choses que nous pensons vouloir faire",section));

        }

    }

    /**
     * cr�e un sondage avec une requ�te pr�par�e
     */
    private void createSondageDate() {

        manager.createNativeQuery("INSERT INTO TypeSondage (sondage_type, libelle, nbreSondage)"
                + " VALUES ( :a, :b, :c)")
                .setParameter("a", "D")
                .setParameter("b", "Sondage date")
                .setParameter("c", "45")
                .executeUpdate();

    }

    private void createSondageLieu() {

        manager.createNativeQuery("INSERT INTO TypeSondage (sondage_type, libelle, nbreSondage)"
                + " VALUES ( :a, :b, :c)")
                .setParameter("a", "L")
                .setParameter("b", "Sondage lieu")
                .setParameter("c", "50")
                .executeUpdate();

    }
    /**
     *  selection des sondages aevc une requ�te nomm�e
     */

    private void selectSondage() {
        List<TypeSondage> resultList = manager.createQuery("Select a From TypeSondage  a", TypeSondage.class).getResultList();
        System.out.println("num of TypeSondage:" + resultList.size());
        for ( TypeSondage next : resultList) {
            System.out.println("next TypeSondage: " + next.getNom());
        }



    }
}
