package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Documento;

@Repository
public class DocumentoCustomRepository {
	
	private final EntityManager em;
	
	public DocumentoCustomRepository(EntityManager em){	this.em = em;}
	
	public List<Documento> find(Long documentoid, String documentonome,  String documentopn, String documentocaminho)
	{
		// O nome da tabela tem q ser o nome da classe para mapear os parametros
		// crie um alias (Documento AS D) para dizer que esta selecionando a classe ao invés da tabela
		// e na class estará os apontamentos para os campos da tabela e a tabela
		String query = "SELECT D FROM Documento AS D";
		String condicao = " WHERE ";
				
		if(documentonome != null)
		{
			query += condicao + " documento_nome LIKE %:documentonome%";
			condicao = " AND ";
		}
		if(documentopn != null)
		{
			query += condicao + " documento_pn LIKE %:documentopn%";
		}
		
		
		var sql = em.createQuery(query, Documento.class);
		if(documentoid != null){sql.setParameter("documentoid", documentoid );}
		if(documentonome != null){sql.setParameter("documentonome", documentonome );}
		if(documentopn != null){sql.setParameter("documentopn", documentopn );}
		if(documentocaminho != null){sql.setParameter("documentocaminho", documentocaminho );}
		
		return sql.getResultList();
	}
	

}
