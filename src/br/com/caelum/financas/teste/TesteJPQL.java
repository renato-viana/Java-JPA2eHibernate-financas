package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPQL {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		String jpql = "Select m from Movimentacao m where m.conta = :pConta" +
		" and m.tipo = :pTipo" +
		" order by m.valor desc";
		
		String jpql2 = "Select c from Conta c where c.id = :pId";
		
		Query query = em.createQuery(jpql);
		Query query2 = em.createQuery(jpql2);
		
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		query2.setParameter("pId", conta.getId());
		
		List<Movimentacao> resultados = query.getResultList();
		List<Conta> resultados2 = query2.getResultList();
		
		for(Movimentacao movimentacao : resultados) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}
		
		for(Conta contas : resultados2) {
			System.out.println("Titular: " + contas.getTitular());
			System.out.println("Conta.id: " + contas.getId());
		}
		
		em.getTransaction().commit();
		em.close();
	}

}
