package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Invoice;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SavingFilesTests extends EntityManagerTest {

    @Test
    public void savingXmlFile()  {

        entityManager.getTransaction().begin();
        Invoice invoice = new Invoice();
        invoice.setXml(loadXml());
        invoice.setOrder(entityManager.find(Order.class, 1));
        entityManager.persist(invoice);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Invoice invoiceVerify = entityManager.find(Invoice.class, 1);
        Assert.assertNotNull(invoiceVerify.getXml());
        Assert.assertTrue(invoiceVerify.getXml().length > 0);

//        try {
//            OutputStream out = new FileOutputStream(
//                    Files.createFile(Paths.get(
//                            System.getProperty("user.home") + "/xml.xml")).toFile());
//
//            out.write(invoiceVerify.getXml());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }


    private static byte[] loadXml() {
        try {
            return SavingFilesTests.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

}
