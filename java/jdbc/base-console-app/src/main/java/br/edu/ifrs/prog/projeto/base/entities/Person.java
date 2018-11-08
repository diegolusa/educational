
package br.edu.ifrs.prog.projeto.base.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author diego 
 */
public class Person {

    private int id;
    private City city;
    private String name;
    private Date birthDate;
    private float heigth;

    @Override
    public String toString() {
        return String.format("Id: %d Name: %s Date: %s City: %s Heigth: %.2f",
                this.id,
                this.name,
                new SimpleDateFormat("dd-mm-YYYY").format(this.birthDate),
                this.city,
                this.heigth
        );
    }

    public Person() {

    }

    public Person(City city, String name, Date birthDate, float heigth) {
        this.city = city;
        this.name = name;
        this.birthDate = birthDate;
        this.heigth = heigth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public float getHeigth() {
        return heigth;
    }

    public void setHeigth(float heigth) {
        this.heigth = heigth;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
