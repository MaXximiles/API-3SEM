package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Codelist;

@Repository
public class CodelistCustomRepository {
	
	private final EntityManager em;
	
	public CodelistCustomRepository(EntityManager em){	this.em = em;}
	
	 
	public List<Codelist> findJoinDocumento(Long codelistid, String codelistsecao,  String codelistsubsecao, 
											String codelistnbloco, String codelistbloco, String codelistcaminho, String codelistdocumentoid)
	{
		// O nome da tabela tem q ser o nome da classe para mapear os parametros
		// crie um alias (Documento AS D) para dizer que esta selecionando a classe ao invés da tabela
		// e na class estará os apontamentos para os campos da tabela e a tabela
		String query = "SELECT C FROM Codelist AS C ";
			   query += " LEFT JOIN Documento D ON D.documento_id = C.documentoid";
		String condicao = " WHERE ";
				
		/*if(documentonome != null)
		{
			query += condicao + " documento_nome = :documentonome";
			condicao = " AND ";
		}
		if(documentopn != null)
		{
			query += condicao + " documento_pn = :documentopn";
		}*/
		
		var sql = em.createQuery(query, Codelist.class);
		/*if(documentoid != null){sql.setParameter("documento_id", documentoid );}
		if(documentonome != null){sql.setParameter("documentonome", documentonome );}
		if(documentopn != null){sql.setParameter("documentopn", documentopn );}
		if(documentocaminho != null){sql.setParameter("documento_caminho", documentocaminho );}*/
		
		return sql.getResultList();
	}
	 
}
