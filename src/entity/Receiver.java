package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 07.04.2015.
 */

@Entity
@Table(name = ("receiver"))
@NamedQueries({
        @NamedQuery(name = "deleteReceiverById", query = "delete from entity.Receiver r " +
                "where r.receiver_id = :receiver_id"),
        @NamedQuery(name = "getReceiverByLegalAddress", query = "from entity.Receiver r " +
                "where r.legal_address = :legal_address"),
        @NamedQuery(name = "getReceiverById", query = "from entity.Receiver r " +
                "where r.receiver_id = :receiver_id"),
        @NamedQuery(name = "findReceiverByNameAddressPhoneEmail", query = "from entity.Receiver r " +
                "where r.receiver_name like concat('%', :receiver_name, '%') " +
                "or r.legal_address like concat('%', :legal_address, '%') " +
                "or r.phone like concat('%', :phone, '%') " +
                "or r.email like concat('%', :email, '%')"),
        @NamedQuery(name = "findReceiverByName", query = "from entity.Receiver r " +
                "where r.receiver_name like concat('%', :receiver_name, '%')")})

public class Receiver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "receiver_id")
    private int receiver_id;

    @Column(name = "receiver_name")
    private String receiver_name;

    @Column(name = "legal_address")
    private String legal_address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;


    public Receiver() {

    }

    public Receiver(Receiver receiver) {
        this.email = receiver.getEmail();
        this.legal_address = receiver.getLegal_address();
        this.phone = receiver.getPhone();
        this.receiver_name = receiver.getReceiver_name();
    }

    public Receiver(String email, String legal_address, String phone, String receiver_name) {
        this.email = email;
        this.legal_address = legal_address;
        this.phone = phone;
        this.receiver_name = receiver_name;
    }

    public String getEmail() {
        return email;
    }

    public String getLegal_address() {
        return legal_address;
    }

    public String getPhone() {
        return phone;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLegal_address(String legal_address) {
        this.legal_address = legal_address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    @Override
    public String toString() {
        return "Receiver: id = " + receiver_id + ", name = " + receiver_name + ", legal address = " + legal_address
                + ", phone = " + phone + ", email = " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receiver)) return false;

        Receiver receiver = (Receiver) o;

        if (receiver_id != receiver.receiver_id) return false;
        if (email != null ? !email.equals(receiver.email) : receiver.email != null) return false;
        if (legal_address != null ? !legal_address.equals(receiver.legal_address) : receiver.legal_address != null)
            return false;
        if (phone != null ? !phone.equals(receiver.phone) : receiver.phone != null) return false;
        if (receiver_name != null ? !receiver_name.equals(receiver.receiver_name) : receiver.receiver_name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = receiver_id;
        result = 31 * result + (receiver_name != null ? receiver_name.hashCode() : 0);
        result = 31 * result + (legal_address != null ? legal_address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
