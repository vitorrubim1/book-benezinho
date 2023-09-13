package br.com.fiap;

import br.com.fiap.domain.entity.Author;
import br.com.fiap.domain.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        var bene = new Author("Benefrancis");
        var bruno = new Author("Bruno Sudré");

        var livro = new Book();
        livro.setName("Java Mapeamento Objeto Relacional")
                .setLancamento(LocalDate.now())
                .setISBN(UUID.randomUUID().toString())
                .addAuthor(bene)
                .addAuthor(bruno);

        manager.getTransaction().begin();
        manager.persist(livro);
        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}