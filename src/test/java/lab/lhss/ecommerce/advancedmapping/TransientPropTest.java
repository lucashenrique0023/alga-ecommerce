package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.OrderItem;
import lab.lhss.ecommerce.model.OrderItemId;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransientPropTest extends EntityManagerTest {

    @Test
    public void testTransientNotPersisting() {

        OrderItem orderItem = entityManager.find(OrderItem.class, new OrderItemId(1, 1));

        Assert.assertEquals((BigDecimal.valueOf(10.00)).setScale(2, RoundingMode.HALF_EVEN), orderItem.getTotal());
    }
}
