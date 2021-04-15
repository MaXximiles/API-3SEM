package com.grupo2.API_TraceFinder.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Bloco;
import com.grupo2.API_TraceFinder.classes.Documento;

@Repository
public class BlocoCustomRepository {
	
	private final EntityManager em;
	
	public BlocoCustomRepository(EntityManager em){	this.em = em;}
		
	 
	public List<Bloco> SelectJoinDocumento(Long blocoid, String blocosecao,  String blocosubsecao, 
											String bloconomebloco, String blococodebloco, String blococaminho)
	{
		// O nome da tabela tem q ser o nome da classe para mapear os parametros
		// crie um alias (Documento AS D) para dizer que esta selecionando a classe ao invés da tabela
		// e na class estará os apontamentos para os campos da tabela e a tabela

		/* ************ 
		 * @Query(value = "select * from pergunta where ativo = 1", nativeQuery = true)
		List<Pergunta> findAllAtivas(); **************** */
		
		
		
		
		/* String query = "SELECT C FROM Codelist AS C ";
			   query += " JOIN C.Documento D ";
			   query += " WHERE D.documento_id = 1 ";
		String condicao = " WHERE "; */

		/*if(documentonome != null) 
		{
			query += condicao + " documento_nome = :documentonome";
			condicao = " AND ";
		}
		if(documentopn != null)
		{
			query += condicao + " documento_pn = :documentopn";
		}*/
		
		//var sql = em.createQuery(query, Codelist.class);
		/*if(documentoid != null){sql.setParameter("documento_id", documentoid );}
		if(documentonome != null){sql.setParameter("documentonome", documentonome );}
		if(documentopn != null){sql.setParameter("documentopn", documentopn );}
		if(documentocaminho != null){sql.setParameter("documento_caminho", documentocaminho );}*/
		
		//return sql.getResultList();
		return null;
	}
	 
}
