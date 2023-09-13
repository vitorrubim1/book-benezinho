package br.com.fiap.domain.entity;


import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AUTHOR")
    @Column(name = "ID_AUTHOR")
    private Long id;

    @Column(name = "NM_AUTHOR", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "writers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("name asc")
    private Set<Book> obras;

    public Author() {
        obras = new LinkedHashSet<>();
    }

    public Author(String name) {
        this.name = name;
        obras = new LinkedHashSet<>();
    }

    public Author(Long id, String name, Set<Book> obras) {
        this.id = id;
        this.name = name;
        this.obras = Objects.nonNull(obras) ? obras : new LinkedHashSet<>();
    }


    public Author addObra(Book b) {
        obras.add(b);
        return this;
    }

    public Author removeObra(Book b) {
        obras.remove(b);
        return this;
    }

    public Set<Book> getObras() {
        return Collections.unmodifiableSet(obras);
    }

    public Long getId() {
        return id;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", obras=" + obras +
                '}';
    }
}
