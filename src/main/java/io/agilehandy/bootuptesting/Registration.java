package io.agilehandy.bootuptesting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *  By Haytham Mohamed
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Registration {

    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String className;

    public Registration(String n, String cn) {
        name = n;
        className = cn;
    }


}
