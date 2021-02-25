package xyz.softeng.bugofficer.dataaccess.person;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId(mutable = true)
    private String name;

    private String team;

    private Role role;

    private int noSelected = 0;

    private boolean isOfficer = false;

    public Person(String name, String team, Role role) {
        this.name = name;
        this.team = team;
        this.role = role;
    }

    public Person(String name, String team, Role role, int noSelected) {
        this(name, team, role);
        this.noSelected = noSelected;
    }

    public Person(String name, String team, Role role, int noSelected, boolean isOfficer) {
        this(name, team, role, noSelected);
        this.isOfficer = isOfficer;
    }
}
