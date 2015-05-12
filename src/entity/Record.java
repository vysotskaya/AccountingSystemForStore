package entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by User on 21.04.2015.
 */

@Entity
@Table(name = ("storebook"))
@NamedQueries({
        @NamedQuery(name = "deleteRecordById", query = "delete from entity.Record r " +
                "where r.record_id = :record_id"),
        @NamedQuery(name = "getRecordById", query = "from entity.Record r " +
                "where r.record_id = :record_id"),
        @NamedQuery(name = "getRecordsForPeriod", query = "from entity.Record r " +
                "where r.retention_limit > :retention_limit"),
        @NamedQuery(name = "getRecordByProductId", query = "from entity.Record r " +
                "where r.product = :product")})

public class Record implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private int record_id;

    @Column(name = "retention_limit")
    private String retention_limit;

    @Column(length = 10000000)
    private StoreArea storeArea;

    @Column(length = 10000000)
    private Product product;

    @Column(length = 10000000)
    private Receiver receiver;

    @Column(length = 10000000)
    private Sender sender;

    @Column(length = 10000000)
    private Employee employee;

    public Record() {
    }

    public Record(Record record) {
        this.employee = record.getEmployee();
        this.product = record.getProduct();
        this.receiver = record.getReceiver();
        this.retention_limit = record.getRetention_limit();
        this.sender = record.getSender();
        this.storeArea = record.getStoreArea();
    }

    public Record(Employee employee, Product product, Receiver receiver, String retention_limit,
                  StoreArea storeArea, Sender sender) {
        this.employee = employee;
        this.product = product;
        this.receiver = receiver;
        this.retention_limit = retention_limit;
        this.storeArea = storeArea;
        this.sender = sender;
    }

    public int getRecord_id() {
        return record_id;
    }

    public String getRetention_limit() {
        return retention_limit;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "storearea_id", referencedColumnName = "storearea_id")
    public StoreArea getStoreArea() {
        return storeArea;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    public Product getProduct() {
        return product;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id", referencedColumnName = "receiver_id")
    public Receiver getReceiver() {
        return receiver;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "sender_id")
    public Sender getSender() {
        return sender;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public void setRetention_limit(String retention_limit) {
        this.retention_limit = retention_limit;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setStoreArea(StoreArea storeArea) {
        this.storeArea = storeArea;
    }

    @Override
    public String toString() {
        return "Record : " +" employee = " + employee +", record_id = " + record_id +
                ", retention_limit = " + retention_limit + ", storeArea = " + storeArea +
                ", product = " + product + ", receiver = " + receiver + ", sender=" + sender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        if (record_id != record.record_id) return false;
        if (employee != null ? !employee.equals(record.employee) : record.employee != null) return false;
        if (product != null ? !product.equals(record.product) : record.product != null) return false;
        if (receiver != null ? !receiver.equals(record.receiver) : record.receiver != null) return false;
        if (retention_limit != null ? !retention_limit.equals(record.retention_limit)
                : record.retention_limit != null)
            return false;
        if (sender != null ? !sender.equals(record.sender) : record.sender != null) return false;
        if (storeArea != null ? !storeArea.equals(record.storeArea) : record.storeArea != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = record_id;
        result = 31 * result + (retention_limit != null ? retention_limit.hashCode() : 0);
        result = 31 * result + (storeArea != null ? storeArea.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }
}
