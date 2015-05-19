package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 21.04.2015.
 */

@Entity
@Table(name = ("storearea"))
@NamedQueries({
        @NamedQuery(name = "deleteStoreAreaById", query = "delete from entity.StoreArea s " +
                "where s.storearea_id = :storearea_id"),
        @NamedQuery(name = "getStoreAreaById", query = "from entity.StoreArea s " +
                "where s.storearea_id = :storearea_id"),
        @NamedQuery(name = "findStoreAreaByName", query = "from entity.StoreArea s " +
        "where s.storearea_name like concat('%', :storearea_name, '%')")})

public class StoreArea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "storearea_id")
    private int storearea_id;

    @Column(name = "storearea_name")
    private String storearea_name;

    public StoreArea() {
    }

    public StoreArea(String storearea_name) {
        this.storearea_name = storearea_name;
    }

    public StoreArea(StoreArea storeArea) {
        this.storearea_name = storeArea.getStorearea_name();
    }

    public int getStorearea_id() {
        return storearea_id;
    }

    public String getStorearea_name() {
        return storearea_name;
    }

    public void setStorearea_id(int storearea_id) {
        this.storearea_id = storearea_id;
    }

    public void setStorearea_name(String storearea_name) {
        this.storearea_name = storearea_name;
    }

    @Override
    public String toString() {
        return "StoreArea: id = " + storearea_id + ", name = " + storearea_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreArea)) return false;

        StoreArea storeArea = (StoreArea) o;

        if (storearea_id != storeArea.storearea_id) return false;
        if (storearea_name != null ? !storearea_name.equals(storeArea.storearea_name) : storeArea.storearea_name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storearea_id;
        result = 31 * result + (storearea_name != null ? storearea_name.hashCode() : 0);
        return result;
    }
}
