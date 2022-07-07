package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class GroupBy extends EntityManagerTest {

    @Test
    public void groupResults() {

        // Get amount of items by Category (Items can have multiple category)
        //String jpql = "select c.name, count(i.id) from Category c join c.items i group by c.id";

        // Total price of orders by day number
        //String jpql = "select day(o.createDate) as day, sum(o.total) from Order o group by day";

        // Total price of orders by day name
        //String jpql = "select function('dayname', o.createDate) as day, sum(o.total) from Order o group by day";


        // Total price of orders by month grouped by year and month
        String jpql = "select concat(year(o.createDate) ,' ', function('monthname', o.createDate)), sum(o.total) " +
                " from Order o " +
                " group by year(o.createDate), month(o.createDate)";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
        list.forEach(arr -> System.out.println(arr[0] + " : " + arr[1]));
    }

}
