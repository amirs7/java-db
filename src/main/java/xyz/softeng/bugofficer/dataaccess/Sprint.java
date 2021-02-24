package xyz.softeng.bugofficer.dataaccess;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Sprint {
    @Id
    private Long id;

    private LocalDateTime start;

    private LocalDateTime end;

    @ManyToMany
    private List<Person> bugOfficers = new ArrayList<>();
}
