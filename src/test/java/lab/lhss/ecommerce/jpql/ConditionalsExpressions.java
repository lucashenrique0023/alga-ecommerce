package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Item;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class ConditionalsExpressions extends EntityManagerTest {

    // LIKE //

    @Test
    public void likeConditionalTest() {

        String jpql = "select c from Client c where c.name like concat(:name, '%')";

        TypedQuery<Client> typedQuery = entityManager.createQuery(jpql, Client.class);
        typedQuery.setParameter("name", "Lucas");
        List<Client> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.get(0).getName().contains("Lucas"));
    }

    // EMPTY //

    @Test
    public void emptyConditionalTest() {
        String jpql = "select i from Item i where i.categories is empty";
        TypedQuery<Item> typedQuery = entityManager.createQuery(jpql, Item.class);
        List<Item> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    // NULL //

    @Test
    public void nullConditionalTest() {
        String jpql = "select i from Item i where i.picture is null";
        TypedQuery<Item> typedQuery = entityManager.createQuery(jpql, Item.class);
        List<Item> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

}
