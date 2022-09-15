import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Person {
    private String surename, forename, dob, locality, street, occupation, other;

    Person(String surename, String forename, String dob, String locality, String street, String occupation, String other) {
        this.surename = surename;
        this.forename = forename;
        this.dob = dob;
        this.locality = locality;
        this.street = street;
        this.occupation = occupation;
        this.other = other;
    }
}
