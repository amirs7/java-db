package xyz.softeng.dbscript;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import xyz.softeng.dbscript.dataaccess.Author;
import xyz.softeng.dbscript.dataaccess.AuthorRepository;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {
    private final AuthorRepository authorRepository;

    private List<Author> authors;

    @ShellMethod("Get list of all authors.")
    public Iterable<Author> getAll() {
        return authorRepository.findAll();
    }

    @ShellMethod("Find an author.")
    public List<Author> find(String firstname) {
        authors = authorRepository.findAllByFirstname(firstname);
        return authors;
    }

    @ShellMethod("Clear current authors list.")
    public void clearAuthors() {
        this.authors = null;
    }

    @ShellMethod("Select an author.")
    @ShellMethodAvailability("selectAvailability")
    public Author select(int index) {
        return authors.get(index);
    }

    public Availability selectAvailability() {
        if (authors == null)
            return Availability.unavailable("Authors list is empty.");
        return Availability.available();
    }

    @ShellMethod("Create an author")
    public Author create(String firstname) {
        Author author = new Author();
        author.setFirstname(firstname);
        return authorRepository.save(author);
    }

}
