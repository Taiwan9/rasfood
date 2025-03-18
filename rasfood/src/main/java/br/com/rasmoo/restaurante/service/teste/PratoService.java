package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.entity.Prato;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PratoService {
    public static void main(String[] args) {
        Prato risoto = new Prato();
        risoto.setNome("Risoto de Camarão");
        risoto.setDescricao("Risoto de frutos do mar e camarão");
        risoto.setDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("rasfood");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(risoto);
        manager.getTransaction().commit();
        manager.close();
    }
}
