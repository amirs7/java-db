package xyz.softeng.bugofficer.dataaccess.sprint;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.softeng.bugofficer.dataaccess.person.Person;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class Sprint {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;

    @Future
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    @ManyToOne
    private Person bugOfficer;

    @Transient
    public boolean isInProgress() {
        LocalDate now = LocalDate.now();
        return (start.isBefore(now) || start.isEqual(now)) &&
                (end.isAfter(now) || end.isEqual(now));
    }
}
