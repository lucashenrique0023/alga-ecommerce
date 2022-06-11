package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ColumnDetailsTest extends EntityManagerTest {

    @Test
    public void preventInsertRegisterWithLastModifyDate() {

        Item item = new Item();
        item.setName("USB Cable");
        item.setCreateDate(LocalDateTime.now());
        item.setLastModifyDate(LocalDateTime.now());
        item.setDescription("5M Cable");
        item.setPrice(new BigDecimal("3.99"));

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Item itemVerify = entityManager.find(Item.class, item.getId());

        Assert.assertNull(itemVerify.getLastModifyDate());
        Assert.assertNotNull(itemVerify.getCreateDate());
    }


    @Test
    public void preventChangeCreationDateOnRegisterAlreadyCreated() {

        Item item = entityManager.find(Item.class, 1);

        item.setCreateDate(LocalDateTime.now());
        item.setLastModifyDate(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Item itemVerify = entityManager.find(Item.class, item.getId());

        System.out.println(item.getCreateDate());
        System.out.println(item.getCreateDate().truncatedTo(ChronoUnit.SECONDS));

        Assert.assertNotEquals(item.getCreateDate().truncatedTo(ChronoUnit.SECONDS),
                itemVerify.getCreateDate().truncatedTo(ChronoUnit.SECONDS));

        Assert.assertEquals(item.getLastModifyDate().truncatedTo(ChronoUnit.SECONDS),
                itemVerify.getLastModifyDate().truncatedTo(ChronoUnit.SECONDS));

    }
}
