package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class NativeFunctions extends EntityManagerTest {

    @Test
    public void executeDBCustomFunction() {
        String jpql = "select o from Order o where function('order_above_average_total', o.total) = 1";
        TypedQuery<Order> typedQuery = entityManager.createQuery(jpql, Order.class);
        List<Order> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void agregationFunction() {
        // avg, count, min, max, sum
        String jpql = "select sum(o.total) from Order o";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);

        List<Number> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(size -> System.out.println(size));
    }


    @Test
    public void colectionFunction() {
        String jpql = "select size(o.items) from Order o where size(o.items) > 1";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);

        List<Integer> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(size -> System.out.println(size));
    }

    @Test
    public void numberFunction() {
        String jpql = "select abs(o.total), mod(o.id, 2), sqrt(o.total) from Order o " +
                " where abs(o.total) > 1000";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }

    @Test
    public void dateFunction() {
        // TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // current_date, current_time, current_timestamp
        // year(o.createDate), month(o.createDate), day(o.createDate)

        String jpql = "select hour(o.createDate), minute(o.createDate), second(o.createDate) " +
                " from Order o where hour(o.createDate) > 6";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }

    @Test
    public void stringFunction() {
        // concat, length, locate, substring, lower, upper, trim

        String jpql = "select c.name, length(c.name) from Category c " +
                " where substring(c.name, 1, 1) = 'F'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());

        list.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }

}
