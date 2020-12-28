package cookbook.model.entities;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Address implements Serializable {
    public Address() {
    }

    private String street;
    private String postalCode;
    private String city;
    private String country;

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
