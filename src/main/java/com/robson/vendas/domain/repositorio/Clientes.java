package com.robson.vendas.domain.repositorio;

import com.robson.vendas.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class Clientes {
    @Autowired
    private EntityManager entityManager;


    //Salvar Clientes
    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    //Atualizar Clientes
    @Transactional
    public Cliente atualizar (Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    //Deletar Cliente
    @Transactional
    public void deletar( Cliente cliente){

        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }
    @Transactional
    public void deletar (Integer id){
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    //Buscar Cliente
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome){
        String jpql = " select c from Cliente c where c.nome like :nome ";
       TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
       query.setParameter("nome", "%" + nome + "%");
       return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }

}
