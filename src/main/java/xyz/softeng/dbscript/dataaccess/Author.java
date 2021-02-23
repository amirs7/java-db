package xyz.softeng.dbscript.dataaccess;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Author {
    @Id
    private int id;

    private String firstname;

    @JoinTable(
            name = "bookauthor",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;
}
