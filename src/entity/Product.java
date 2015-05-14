package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 07.04.2015.
 */

@Entity
@Table(name = ("product"))
@NamedQueries({
        @NamedQuery(name = "deleteProductById", query = "delete from entity.Product p " +
                "where p.product_id = :product_id"),
        @NamedQuery(name = "getProductByMarking", query = "from entity.Product p " +
                "where p.product_marking = :product_marking"),
        @NamedQuery(name = "getProductsByRegime", query = "from entity.Product p " +
                "where p.customsRegimeType = :regime"),
        @NamedQuery(name = "getProductById", query = "from entity.Product p " +
                "where p.product_id = :product_id"),
        @NamedQuery(name = "findProductByName", query = "from entity.Product p " +
                "where p.product_name like concat('%', :product_name, '%')"),
        @NamedQuery(name = "findProductByAcount", query = "from entity.Product p " +
                "where p.acount like concat('%', :acount, '%')"),
        @NamedQuery(name = "findProductByUnit", query = "from entity.Product p " +
                "where p.measuring_unit like concat('%', :measuring_unit, '%')"),
        @NamedQuery(name = "findProductByMarking", query = "from entity.Product p " +
                "where p.product_marking like concat('%', :product_marking, '%')")})

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "acount")
    private int acount;

    @Column(name = "measuring_unit")
    private String measuring_unit;

    @Column(name = "storing_features")
    private String storing_features;

    @Column(name = "product_marking")
    private String product_marking;

    private CustomsRegimeType customsRegimeType;

    public Product() {

    }

    public Product(Product product) {
        this.acount = product.getAcount();
        this.customsRegimeType = product.getCustomsRegimeType();
        this.measuring_unit = product.getMeasuring_unit();
        this.product_marking = product.getProduct_marking();
        this.storing_features = product.getStoring_features();
        this.product_name = product.getProduct_name();
    }

    public Product(int acount, CustomsRegimeType customsRegimeType, String measuring_unit,
                   String product_marking, String storing_features, String product_name) {
        this.acount = acount;
        this.customsRegimeType = customsRegimeType;
        this.measuring_unit = measuring_unit;
        this.product_marking = product_marking;
        this.storing_features = storing_features;
        this.product_name = product_name;
    }

    public int getAcount() {
        return acount;
    }

    public String getMeasuring_unit() {
        return measuring_unit;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_marking() {
        return product_marking;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getStoring_features() {
        return storing_features;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "regime_id", referencedColumnName = "regime_id")
    public CustomsRegimeType getCustomsRegimeType() {
        return customsRegimeType;
    }

    public void setAcount(int acount) {
        this.acount = acount;
    }

    public void setCustomsRegimeType(CustomsRegimeType customsRegimeType) {
        this.customsRegimeType = customsRegimeType;
    }

    public void setMeasuring_unit(String measuring_unit) {
        this.measuring_unit = measuring_unit;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setProduct_marking(String product_marking) {
        this.product_marking = product_marking;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setStoring_features(String storing_features) {
        this.storing_features = storing_features;
    }

    @Override
    public String toString() {
        return "Product: id = " + product_id + " acount = " + acount +  ", name = " + product_name
                + ", measuring unit = " + measuring_unit + ", storing features = " + storing_features
                + ", product marking = " + product_marking + ", regime type = " + customsRegimeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (acount != product.acount) return false;
        if (product_id != product.product_id) return false;
        if (customsRegimeType != null ? !customsRegimeType.equals(product.customsRegimeType) : product.customsRegimeType != null)
            return false;
        if (measuring_unit != null ? !measuring_unit.equals(product.measuring_unit) : product.measuring_unit != null)
            return false;
        if (product_marking != null ? !product_marking.equals(product.product_marking) : product.product_marking != null)
            return false;
        if (product_name != null ? !product_name.equals(product.product_name) : product.product_name != null)
            return false;
        if (storing_features != null ? !storing_features.equals(product.storing_features) : product.storing_features != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = product_id;
        result = 31 * result + (product_name != null ? product_name.hashCode() : 0);
        result = 31 * result + acount;
        result = 31 * result + (measuring_unit != null ? measuring_unit.hashCode() : 0);
        result = 31 * result + (storing_features != null ? storing_features.hashCode() : 0);
        result = 31 * result + (product_marking != null ? product_marking.hashCode() : 0);
        result = 31 * result + (customsRegimeType != null ? customsRegimeType.hashCode() : 0);
        return result;
    }
}


