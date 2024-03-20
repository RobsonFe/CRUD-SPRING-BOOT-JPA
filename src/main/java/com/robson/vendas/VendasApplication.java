package com.robson.vendas;

import com.robson.vendas.domain.entity.Cliente;
import com.robson.vendas.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init (@Autowired Clientes clientes){
		return args -> {
			System.out.println("Salvando clientes");
			clientes.salvar(new Cliente("Robson"));
			clientes.salvar(new Cliente("Adams"));
			clientes.salvar(new Cliente("Pedro"));

			System.out.println("Buscando todos cliente");
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Buscando cliente por nome");
			clientes.buscarPorNome("a").forEach(System.out::println);

			System.out.println("Atualizando Cliente");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " Atualizou");
				clientes.atualizar(c);
			});

			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Deletando Cliente");
			clientes.obterTodos().forEach(
					c-> {
						clientes.deletar(c);
					}
			);

			todosClientes = clientes.obterTodos();
			if(todosClientes.isEmpty()){
				System.out.println("nenhum cliente encontrado");
			}else {
				todosClientes.forEach(System.out::println);
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
