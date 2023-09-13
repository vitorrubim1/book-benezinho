package br.com.fiap;

import br.com.fiap.domain.entity.Author;
import br.com.fiap.domain.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        //  addDados(manager);
        //   var aut = new Author("Paulo Coelho");

        manager.createQuery("FROM Author", Author.class).getResultList().forEach(System.out::println);

        manager.close();
        factory.close();
    }

    private static void addDados(EntityManager manager) {
        String nome = JOptionPane.showInputDialog("Autor");
        String book = JOptionPane.showInputDialog("Livro");

        var author = new Author(nome);

        var livro = new Book();
        livro.setName(book)
                .setLancamento(LocalDate.now())
                .setISBN(UUID.randomUUID().toString())
                .addAuthor(author);

        manager.getTransaction().begin();
        manager.persist(livro);
        manager.getTransaction().commit();
    }
}