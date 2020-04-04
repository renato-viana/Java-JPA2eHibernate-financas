package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setTitular("Danilo");
		conta.setAgencia("123");
		conta.setBanco("Banco do Brasil");
		conta.setNumero("456");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		conta = em.find(Conta.class, 1);
		
		em.remove(conta);
		
		conta.setBanco("Bradesco");
		
		em.getTransaction().commit();

		em.close();

	}
}
