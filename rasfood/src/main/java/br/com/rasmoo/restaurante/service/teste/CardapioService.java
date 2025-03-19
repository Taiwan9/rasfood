package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {
    public static void main(String[] args) {
        EntityManager manager = JPAUtil.getEntityManagerRasfood();
        cadastrarProduto(manager, cadastarCategoria(manager));
    }

    private static Categoria cadastarCategoria(EntityManager manager){
        CategoriaDao categoriaDao = new CategoriaDao(manager);
        Categoria pratoPrincipal = new Categoria("Prato Principal");

        manager.getTransaction().begin();
        categoriaDao.cadastrar(pratoPrincipal);
        manager.getTransaction().commit();
        manager.clear();
        return  pratoPrincipal;
    }

    private static  void cadastrarProduto(EntityManager manager, Categoria categoria){
        Cardapio risoto = new Cardapio();
        risoto.setNome("Risoto de Camarão");
        risoto.setDescricao("Risoto de frutos do mar e camarão");
        risoto.setDisponivel(true);
        risoto.setCategoria(categoria);
        risoto.setValor(BigDecimal.valueOf(88.50));

        Cardapio feijoada = new Cardapio();
        feijoada.setNome("Feijoada");
        feijoada.setDescricao("Feijoada tradicional de Carne de porco e seca");
        feijoada.setDisponivel(true);
        feijoada.setCategoria(categoria);
        feijoada.setValor(BigDecimal.valueOf(70.50));

        Cardapio baiaoDeDois = new Cardapio();
        baiaoDeDois.setNome("Baiao de Dois");
        baiaoDeDois.setDescricao("Arroz, feijao tropeiro, queijo, carne e linguiça");
        baiaoDeDois.setDisponivel(false);
        baiaoDeDois.setCategoria(categoria);
        baiaoDeDois.setValor(BigDecimal.valueOf(44.50));

        CardapioDao cardapioDao = new CardapioDao(manager);
        manager.getTransaction().begin();
        cardapioDao.cadastrar(risoto);
        manager.flush();

        cardapioDao.cadastrar(baiaoDeDois);
        manager.flush();

        cardapioDao.cadastrar(feijoada);
        manager.flush();

        System.out.println("O prato consultad foi: "+ cardapioDao.consultar(1));
        System.out.println("O prato consultad foi: "+ cardapioDao.consultar(2));
        System.out.println("O prato consultad foi: "+ cardapioDao.consultar(3));

        manager.close();

    }
}
