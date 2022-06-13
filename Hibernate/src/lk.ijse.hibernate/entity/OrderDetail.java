package lk.ijse.hibernate.entity;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item item;

    @Column(name = "orderQty")
    private int qty;

    public OrderDetail() {
    }

    public OrderDetail(String id, Orders order, Item item, int qty) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id='" + id + '\'' +
                ", order=" + order +
                ", item=" + item +
                ", qty=" + qty +
                '}';
    }
}

