package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import lab.lhss.ecommerce.model.Item;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Arrays;
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

    // Using greather-than (>) and lower-to (<) in queries!

    @Test
    public void findOrdersFrom2DaysToNow() {
        String jpql = "select o from Order o where o.createDate >= :date";
        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        typedQuery.setParameter("date", LocalDateTime.now().minusDays(2));
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void findWithCase() {

        String jpql = "select o.id, " +
                " case type(o.payment) " +
                " when CreditCardPayment then 'Paid with credit card.' " +
                " when BankSlipPayment then 'Paid with bank slip.' " +
                " else 'Not paid yet.' " +
                " end " +
                " from Order o left join o.payment p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }

    @Test
    public void findWithIN() {

        Client client1 = new Client();
        client1.setId(1);

        Client client2 = new Client();
        client2.setId(2);

        List<Client> clients = Arrays.asList(client1);

        String jpql = "Select o from Order o where o.client IN (:clients)";

        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);

        // At least one client must be passed as parameter, or syntax error will be thrown.
        typedQuery.setParameter("clients", clients);
        List<Order> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void selectDistinct() {
     String jpql = "select distinct o from Order o" +
             " join o.items i";

     TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
     List<Order> list = typedQuery.getResultList();

     Assert.assertFalse(list.isEmpty());
     System.out.println(list.size());
    }

    @Test
    public void subqueries() {

        String jpql = "select c from Client c where " +
                " 500 < (select sum(o.total) from Order o where o.client = c";

        String jpql2 = "select c from Client c " +
                " 500 < (select sum(o.total) from c.orders p";

        String jpql3 = "select o from Order o where " +
                " o.total > (select avg(total) from Order)";

        String jpql4 = "select i from Item i where " +
                " i.price = (select max(proce) from Item)";
    }

}
