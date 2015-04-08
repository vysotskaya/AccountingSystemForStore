package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 07.04.2015.
 */

@Entity
@Table(name = ("position"))
@NamedQueries({
        @NamedQuery(name = "deletePositionById", query = "delete from entity.Position p " +
                "where p.position_id = :position_id"),
        @NamedQuery(name = "getPositionById", query = "from entity.Position p " +
                "where p.position_id = :position_id")})

public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "position_id")
    private int position_id;

    @Column(name = "position_name")
    private String position_name;

    public Position() {

    }

    public Position(Position position) {
        this.position_name = position.getPosition_name();
    }

    public Position(String position_name) {
        this.position_name = position_name;
    }

    public int getPosition_id() {
        return position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    @Override
    public String toString() {
        return "Position: id = " + position_id + ", name = " + position_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (position_id != position.position_id) return false;
        if (position_name != null ? !position_name.equals(position.position_name) : position.position_name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position_id;
        result = 31 * result + (position_name != null ? position_name.hashCode() : 0);
        return result;
    }
}
