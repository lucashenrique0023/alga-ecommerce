package lab.lhss.ecommerce.advancedmapping;

import lab.lhss.ecommerce.EntityManagerTest;
import lab.lhss.ecommerce.model.Invoice;
import lab.lhss.ecommerce.model.Item;
import lab.lhss.ecommerce.model.Order;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class SavingFilesTests extends EntityManagerTest {

    @Test
    public void savingXmlFile()  {

        entityManager.getTransaction().begin();
        Invoice invoice = new Invoice();
        invoice.setXml(loadFile("/nota-fiscal.xml"));
        invoice.setOrder(entityManager.find(Order.class, 1));
        invoice.setEmissionDate(new Date());

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

    @Test
    public void savingItemPicture() {

        entityManager.getTransaction().begin();
        Item item = entityManager.find(Item.class, 1);
        item.setPicture(loadFile("/kindle.jpg"));
        entityManager.getTransaction().commit();

        entityManager.clear();

        Item itemVerify = entityManager.find(Item.class, item.getId());
        Assert.assertNotNull(itemVerify.getPicture());
        Assert.assertTrue(itemVerify.getPicture().length > 0);

//        try {
//            OutputStream out = new FileOutputStream(
//                    Files.createFile(Paths.get(
//                            System.getProperty("user.home") + "/kindle.jpg")).toFile());
//
//            out.write(itemVerify.getPicture());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    private static byte[] loadFile(String fileName) {
        try {
            return SavingFilesTests.class.getResourceAsStream(fileName).readAllBytes();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

}
