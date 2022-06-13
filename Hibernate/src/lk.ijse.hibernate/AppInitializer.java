package lk.ijse.hibernate;

import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.entity.OrderDetail;
import lk.ijse.hibernate.entity.Orders;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AppInitializer {
    public static void main(String[] args) {
        Customer c1 = new Customer("C001", "Saman", 50000, "Galle");
        Customer c2 = new Customer("C002", "Kamal", 40000, "Colombo");

        Item i1 = new Item("I001", "Lipton Ceylon Tea", new BigDecimal(500).setScale(2), 1000);
        Item i2 = new Item("I002", "Anchor N/F Milk powder", new BigDecimal(1080).setScale(2), 1000);

        Orders o1 = new Orders("O001", c1);
        Orders o2 = new Orders("O002", c2);
        Orders o3 = new Orders("O003", c1);

        OrderDetail detail1 = new OrderDetail();
        detail1.setId("OD001");
        detail1.setOrder(o1);
        detail1.setItem(i1);
        detail1.setQty(20);

        OrderDetail detail2 = new OrderDetail();
        detail2.setId("OD002");
        detail2.setOrder(o1);
        detail2.setItem(i2);
        detail2.setQty(10);

        OrderDetail detail3 = new OrderDetail();
        detail3.setId("OD003");
        detail3.setOrder(o2);
        detail3.setItem(i1);
        detail3.setQty(15);

        OrderDetail detail4 = new OrderDetail();
        detail4.setId("OD004");
        detail4.setOrder(o2);
        detail4.setItem(i2);
        detail4.setQty(15);

        OrderDetail detail5 = new OrderDetail();
        detail5.setId("OD005");
        detail5.setOrder(o3);
        detail5.setItem(i2);
        detail5.setQty(15);

        // Methodology 1
        List<Orders> orders = new ArrayList<>();
        orders.add(o1);
        orders.add(o3);
        c1.setOrdersList(orders);

        // Methodology 2
        c2.getOrdersList().add(o2);

        i1.getOrderDetails().add(detail1);
        i1.getOrderDetails().add(detail3);
        i2.getOrderDetails().add(detail2);
        i2.getOrderDetails().add(detail4);
        i2.getOrderDetails().add(detail5);

        o1.getOrderDetails().add(detail1);
        o1.getOrderDetails().add(detail2);
        o2.getOrderDetails().add(detail3);
        o2.getOrderDetails().add(detail4);
        o3.getOrderDetails().add(detail5);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        /* Customer CRUD operations */
        // save
        session.save(c1);
        session.save(c2);

        //update
        session.update(c1);

        //get
        Customer cus = session.get(Customer.class, "C001");
        System.out.println(cus);

        //delete
        //method 1
        Customer delCus1 = new Customer();
        delCus1.setId("C001");
        session.delete(delCus1);

        //method 2
        Customer delCus2 = session.get(Customer.class, "C001");
        session.delete(delCus2);

        /* Item CRUD operations */
        //save
        session.save(i1);
        session.save(i2);

        //update
        session.update(i2);

        //get
        Item itm = session.get(Item.class, "I001");
        System.out.println(itm);

        //delete
        //method 1
        Item delItm1 = new Item();
        delItm1.setCode("I001");
        session.delete(delItm1);

        //method 2
        Item delItm2 = session.get(Item.class, "I001");
        session.delete(delItm2);

        /* save Orders */
        session.save(o1);
        session.save(o2);
        session.save(o3);

        /* save OrderDetail */
        session.save(detail1);
        session.save(detail2);
        session.save(detail3);
        session.save(detail4);
        session.save(detail5);

        transaction.commit();
        session.close();
    }
}
