package xyz.softeng.dbscript.dataaccess;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    List<Author> findAllByFirstname(String firstname);
}
