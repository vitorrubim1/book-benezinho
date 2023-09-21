package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.PessoaFisica;
import br.com.fiap.domain.repository.PessoaFisicaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaFisicaService implements Service<PessoaFisica, Long> {
    private PessoaFisicaRepository repository;

    public PessoaFisicaService() {
        this.repository = new PessoaFisicaRepository();
    }

    @Override
    public List<PessoaFisica> findAll() {
        return repository.findAll();
    }

    @Override
    public PessoaFisica findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PessoaFisica> findByName(String name) {
        if (Objects.isNull(name)) return new ArrayList<>();

        return repository.findByName(name.toLowerCase());
    }

    @Override
    public PessoaFisica persist(PessoaFisica body) {
        if (Objects.isNull(body)) return null;

        return repository.persist(body);
    }
}
