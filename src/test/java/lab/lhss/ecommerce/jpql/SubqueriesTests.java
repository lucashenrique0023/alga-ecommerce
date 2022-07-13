package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class SubqueriesTests extends EntityManagerTest {

    @Test
    public void subqueries() {

        String jpql = "select c from Client c where " +
                " 500 < (select sum(o.total) from Order o where o.client = c)";

        String jpql2 = "select c from Client c " +
                " 500 < (select sum(o.total) from c.orders p)";

        String jpql3 = "select o from Order o where " +
                " o.total > (select avg(total) from Order)";

        String jpql4 = "select i from Item i where " +
                " i.price = (select max(proce) from Item)";

        // Select Clients that made at least 2 orders!
        String jpql5 = "select c from Client c where (select count(o.id) from Order o where o.client = c) >= 2";

        // Select all items that was not ordered after it price changes
        String jpql6 = "select i from Item where exists (" +
                " select 1 from OrderItem oi where oi.item = i and oi.itemPrice <> i.price; " +
                ")";
    }

    @Test
    public void queryWithExists() {

        String jpql = "select i from Item i where exists " +
                "(select 1 from OrderItem oi join oi.i i2 where i2 = i)";

    }

    @Test
    public void queryWithAll() {

        // Get all products sold for the same price
        String jpql = "select distinct oi.item from OrderItem oi " +
                " where oi.itemPrice = ALL (select itemPrice from OrderItem where item = oi.item )";

        TypedQuery<Item> typedQuery = entityManager.createQuery(jpql, Item.class);
        List<Item> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

    }
}
