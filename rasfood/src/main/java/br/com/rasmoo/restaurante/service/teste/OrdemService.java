package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.EnderecoDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.entity.OrdensCardapio;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class OrdemService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        entityManager.getTransaction().begin();
        CargaDeDadosUtil.cadastarCategorias(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
        CargaDeDadosUtil.cadastrarClientes(entityManager);
        CargaDeDadosUtil.cadastrarOrdensClientes(entityManager);
        //OrdemDao ordemDao = new OrdemDao(entityManager);

        //ClienteDao clienteDao = new ClienteDao(entityManager);

        EnderecoDao enderecoDao = new EnderecoDao(entityManager);

        //ordemDao.consultarItensMaisVendido().forEach(item->System.out.println("Item: "+item[0]+"\t-\tQuantidade: "+item[1]));
        //System.out.println(ordemDao.consultarItensMaisVendido());
        //System.out.println(clienteDao.consultarPorNome("costa"));
        System.out.println(enderecoDao.consultarClientes("SP", "Sao Paulo", null));
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
