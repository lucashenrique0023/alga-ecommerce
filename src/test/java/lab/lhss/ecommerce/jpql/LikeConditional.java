package lab.lhss.ecommerce.jpql;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Client;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class LikeConditional extends EntityManagerTest {

    @Test
    public void likeQueryTest() {

        String jpql = "select c from Client c where c.name like concat(:name, '%')";

        TypedQuery<Client> typedQuery = entityManager.createQuery(jpql, Client.class);
        typedQuery.setParameter("name", "Lucas");
        List<Client> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.get(0).getName().contains("Lucas"));
    }

}
