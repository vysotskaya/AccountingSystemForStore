package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 07.04.2015.
 */

@Entity
@Table(name = ("customsregimetype"))
@NamedQueries({
        @NamedQuery(name = "deleteRegimeById", query = "delete from entity.CustomsRegimeType c " +
                "where c.regime_id = :regime_id"),
        @NamedQuery(name = "getRegimeByName", query = "from entity.CustomsRegimeType c " +
                "where c.regime_name = :regime_name"),
        @NamedQuery(name = "getRegimeById", query = "from entity.CustomsRegimeType c " +
                "where c.regime_id = :regime_id")})

public class CustomsRegimeType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "regime_id")
    private int regime_id;

    @Column(name = "regime_name")
    private String regime_name;

    public CustomsRegimeType() {

    }

    public CustomsRegimeType(CustomsRegimeType customsRegimeType) {
        this.regime_name = customsRegimeType.getRegime_name();
    }

    public CustomsRegimeType(String regime_name) {
        this.regime_name = regime_name;
    }

    public int getRegime_id() {
        return regime_id;
    }

    public String getRegime_name() {
        return regime_name;
    }

    public void setRegime_name(String regime_name) {
        this.regime_name = regime_name;
    }

    public void setRegime_id(int regime_id) {
        this.regime_id = regime_id;
    }

    @Override
    public String toString() {
        return "Regime: id = " + regime_id + ", name = " + regime_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomsRegimeType)) return false;

        CustomsRegimeType that = (CustomsRegimeType) o;

        if (regime_id != that.regime_id) return false;
        if (regime_name != null ? !regime_name.equals(that.regime_name) : that.regime_name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = regime_id;
        result = 31 * result + (regime_name != null ? regime_name.hashCode() : 0);
        return result;
    }
}
