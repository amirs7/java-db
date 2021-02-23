package xyz.softeng.dbscript.dataaccess;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Book {
    @Id
    private int id;

    private String name;
}
