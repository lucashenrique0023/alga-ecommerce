package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Item;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BasicJPQL extends EntityManagerTest {


    @Test
    public void findById() {
        TypedQuery<Order> typedQuery = entityManager.createQuery(
                "select o from Order o where o.id = 1",
                Order.class
        );

//        This throw errors on empty results or multiple results
        Order order = typedQuery.getSingleResult();
        Assert.assertNotNull(order);

//        This avoid errors on empty results or multiple results
//        List<Order> orderList = typedQuery.getResultList();
//        Assert.assertFalse(orderList.isEmpty());
    }

    @Test
    public void differenceBetweenQueryAndTypedQuery() {

        String jpql = "select o from Order o where o.id = 1";

        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        Order order1 = typedQuery.getSingleResult();
        Assert.assertNotNull(order1);

        Query query = entityManager.createQuery(jpql);
        Order order2 = (Order) query.getSingleResult();
        Assert.assertNotNull(order2);

    }

    @Test
    public void selectReturnAttribute() {

        String jpql = "select i.name from Item i";
        TypedQuery<String> itemTypedQuery = entityManager.createQuery(jpql, String.class);
        List<String> listItemNames = itemTypedQuery.getResultList();
        Assert.assertTrue(String.class.equals(listItemNames.get(0).getClass()));

        String jpqlClient = "select o.client from Order o";
        TypedQuery<Client> clientTypedQuery = entityManager.createQuery(jpqlClient, Client.class);
        List<Client> clientList = clientTypedQuery.getResultList();
        Assert.assertTrue(Client.class.equals(clientList.get(0).getClass()));
    }

}
