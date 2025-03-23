package br.com.rasmoo.restaurante.dao;



import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.vo.ClienteVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

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

    public List<ClienteVo> consultarClientes(final String estado, final String cidade, final String rua) {
        String jpql = "SELECT new br.com.rasmoo.restaurante.vo.ClienteVo(e.cliente.clienteId.cpf, e.cliente.nome) " +
                "FROM Endereco e " +
                "WHERE 1=1 ";

        if (Objects.nonNull(estado)){
            jpql = jpql.concat(" AND UPPER(e.estado) = UPPER(:estado)");
        }
        if (Objects.nonNull(cidade)){
            jpql = jpql.concat(" AND UPPER(e.cidade) = UPPER(:cidade)");
        }
        if (Objects.nonNull(rua)){
            jpql = jpql.concat(" AND UPPER(e.rua) = UPPER(:rua)");
        }
        TypedQuery typedQuery =  this.entityManager.createQuery(jpql,ClienteVo.class);
        if (Objects.nonNull(estado)){
            typedQuery.setParameter("estado", estado);
        }
        if (Objects.nonNull(cidade)){
            typedQuery.setParameter("cidade", cidade);
        }
        if (Objects.nonNull(rua)){
            typedQuery.setParameter("rua", rua);
        }
        return typedQuery.getResultList();
    }

    public List<ClienteVo> consultarClientesUsandoCritera(final String estado, final String cidade, final String rua) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClienteVo> criteriaQuery =  criteriaBuilder.createQuery(ClienteVo.class);
        Root<Endereco> root = criteriaQuery.from(Endereco.class);
        criteriaQuery.multiselect(root.get("cliente").get("clienteId").get("cpf"), root.get("cliente").get("nome"));
        Predicate predicate = criteriaBuilder.and();
        if (Objects.nonNull(estado)){
            predicate = criteriaBuilder
                    .and(predicate, criteriaBuilder.equal(criteriaBuilder.upper(root.get("estado")), estado.toUpperCase()));
        }
        if (Objects.nonNull(cidade)){
            predicate = criteriaBuilder
                    .and(predicate, criteriaBuilder.equal(criteriaBuilder.upper(root.get("cidade")), cidade.toUpperCase()));
        }
        if (Objects.nonNull(rua)){
            predicate = criteriaBuilder
                    .and(predicate, criteriaBuilder.equal(criteriaBuilder.upper(root.get("rua")), rua.toUpperCase()));
        }
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();

    }

    public void atualizar(final Endereco endereco){
        this.entityManager.merge(endereco);
    }

    public void excluir(final Endereco endereco){
        this.entityManager.remove(endereco);
    }
}
