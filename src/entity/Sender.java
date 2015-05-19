package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 07.04.2015.
 */

@Entity
@Table(name = ("sender"))
@NamedQueries({
        @NamedQuery(name = "deleteSenderById", query = "delete from entity.Sender s " +
                "where s.sender_id = :sender_id"),
        @NamedQuery(name = "getSenderByLegalAddress", query = "from entity.Sender s " +
                "where s.legal_address = :legal_address"),
        @NamedQuery(name = "getSenderById", query = "from entity.Sender s " +
                "where s.sender_id = :sender_id"),
        @NamedQuery(name = "findSenderByName", query = "from entity.Sender s " +
                "where s.sender_name like concat('%', :sender_name, '%')"),
        @NamedQuery(name = "findSenderByNameAddressPhoneEmail", query = "from entity.Sender s " +
                "where s.sender_name like concat('%', :sender_name, '%') " +
                "or s.legal_address like concat('%', :legal_address, '%') " +
                "or s.phone like concat('%', :phone, '%') " +
                "or s.email like concat('%', :email, '%')")})

public class Sender implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sender_id")
    private int sender_id;

    @Column(name = "sender_name")
    private String sender_name;

    @Column(name = "legal_address")
    private String legal_address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;


    public Sender() {

    }

    public Sender(Sender sender) {
        this.email = sender.getEmail();
        this.legal_address = sender.getLegal_address();
        this.phone = sender.getPhone();
        this.sender_name = sender.getSender_name();
    }

    public Sender(String email, String legal_address, String phone, String sender_name) {
        this.email = email;
        this.legal_address = legal_address;
        this.phone = phone;
        this.sender_name = sender_name;
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

    public int getSender_id() {
        return sender_id;
    }

    public String getSender_name() {
        return sender_name;
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

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    @Override
    public String toString() {
        return "Sender: id = " + sender_id + ", name = " + sender_name + ", legal address = " + legal_address
                + ", phone = " + phone + ", email = " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sender)) return false;

        Sender sender = (Sender) o;

        if (sender_id != sender.sender_id) return false;
        if (email != null ? !email.equals(sender.email) : sender.email != null) return false;
        if (legal_address != null ? !legal_address.equals(sender.legal_address) : sender.legal_address != null)
            return false;
        if (phone != null ? !phone.equals(sender.phone) : sender.phone != null) return false;
        if (sender_name != null ? !sender_name.equals(sender.sender_name) : sender.sender_name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sender_id;
        result = 31 * result + (sender_name != null ? sender_name.hashCode() : 0);
        result = 31 * result + (legal_address != null ? legal_address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
