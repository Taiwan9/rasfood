package br.com.rasmoo.restaurante.dao;



import br.com.rasmoo.restaurante.entity.Endereco;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoDao {
    private EntityManager entityManager;

    public EnderecoDao(EntityManager entityManager){
        this.entityManager= entityManager;
    }

    public void cadastrar(final Endereco endereco) {
        this.entityManager.persist(endereco);
        System.out.println("Entidade Cadastrada: " + endereco);
    }

    public Endereco consultarId(final Integer id) {
        return this.entityManager.find(Endereco.class, id);
    }
    public List<Endereco> consultarTodos() {
        String jpql = "SELECT c FROM Endereco c";
        return this.entityManager.createQuery(jpql,Endereco.class).getResultList();
    }

    public void atualizar(final Endereco endereco){
        this.entityManager.merge(endereco);
    }

    public void excluir(final Endereco endereco){
        this.entityManager.remove(endereco);
    }
}
