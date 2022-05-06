package lab.lhss.ecommerce.util;

import lab.lhss.ecommerce.model.item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StartPersistenceUnit {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        item item = entityManager.find(item.class, 1);
        System.out.println(item.getName());

        entityManager.close();
        entityManagerFactory.close();
    }

}
