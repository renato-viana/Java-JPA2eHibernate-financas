package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaFuncaoCount {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
	    Conta conta = em.find(Conta.class, 2);//id 2 deve existir no banco
	    
	    Query query = em.createQuery("select count(m) from Movimentacao m where m.conta = :pConta");
	    
	    query.setParameter("pConta", conta);
	    
	    Long quantidade = (Long) query.getSingleResult();
	    
	    System.out.println("Total de movimenta��es: " + quantidade);
	}

}
