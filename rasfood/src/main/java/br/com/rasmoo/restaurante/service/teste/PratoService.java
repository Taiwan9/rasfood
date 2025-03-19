package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.PratoDao;
import br.com.rasmoo.restaurante.entity.Prato;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PratoService {
    public static void main(String[] args) {
        Prato risoto = new Prato();
        risoto.setNome("Risoto de Camarão");
        risoto.setDescricao("Risoto de frutos do mar e camarão");
        risoto.setDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));

        Prato feijoada = new Prato();
        feijoada.setNome("Feijoada");
        feijoada.setDescricao("Feijoada tradicional de Carne de porco e seca");
        feijoada.setDisponivel(true);
        feijoada.setValor(BigDecimal.valueOf(70.50));

        Prato baiaoDeDois = new Prato();
        baiaoDeDois.setNome("Baiao de Dois");
        baiaoDeDois.setDescricao("Arroz, feijao tropeiro, queijo, carne e linguiça");
        baiaoDeDois.setDisponivel(false);
        baiaoDeDois.setValor(BigDecimal.valueOf(44.50));

        EntityManager manager = JPAUtil.getEntityManagerRasood();
        PratoDao pratoDao = new PratoDao(manager);
        manager.getTransaction().begin();
        pratoDao.cadastrar(risoto);
        manager.flush();

        pratoDao.cadastrar(baiaoDeDois);
        manager.flush();

        pratoDao.cadastrar(feijoada);
        manager.flush();
        System.out.println("O prato consultad foi: "+ pratoDao.consultar(2));

        pratoDao.excluir(feijoada);
        System.out.println("O prato consultad foi: "+ pratoDao.consultar(3));

        manager.clear();

        risoto.setValor(BigDecimal.valueOf(77.40));
        pratoDao.atualizar(risoto);
        System.out.println("O prato consultad foi: "+ pratoDao.consultar(1));

    }
}
