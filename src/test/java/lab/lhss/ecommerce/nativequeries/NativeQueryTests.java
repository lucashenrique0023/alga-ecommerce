package lab.lhss.ecommerce.nativequeries;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

public class NativeQueryTests extends EntityManagerTest {

    @Test
    public void executeSQL() {

        String sql = "select * from item";

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> result = query.getResultList();

        result.forEach(arr -> System.out.printf("Item -> ID: %s, Name: %s%n", arr[0], arr[1]));

    }

    @Test
    public void returnEntity() {
        String sql = "select * from item";

        Query query = entityManager.createNativeQuery(sql, Item.class);

        List<Item> result = query.getResultList();

        result.forEach(item -> System.out.printf("Item -> ID: %s, Name: %s %n", item.getId(),
                item.getName()));
    }

    @Test
    public void queryWithParameters() {

//      String sql = "select id, name, description, created_date, last_modify_date, price, picture from item where id = ?1";

        String sql = "select id, name, description, created_date, last_modify_date, price, picture " +
                "from item where id = :id";

        Query query = entityManager.createNativeQuery(sql, Item.class);
        query.setParameter("id", 1);
//      query.setParameter(1, 1);

        Item item = (Item) query.getSingleResult();

        System.out.printf("ID: %s, Name: %s", item.getId(), item.getName());
    }

}
