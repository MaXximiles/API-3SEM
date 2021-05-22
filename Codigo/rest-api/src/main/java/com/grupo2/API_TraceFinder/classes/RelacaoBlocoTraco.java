package com.grupo2.API_TraceFinder.classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "relacao_bloco_traco")
public class RelacaoBlocoTraco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="relacao_bloco_traco_id")
	private Long relacaotracoblocoid;
	
	@Column(name="traco_id")
	private String tracoid;
	
	@Column(name="bloco_id")
	private String blocoid;
	
	

	public Long getRelacaotracoblocoid() {
		return relacaotracoblocoid;
	}

	public void setRelacaotracoblocoid(Long relacaotracoblocoid) {
		this.relacaotracoblocoid = relacaotracoblocoid;
	}


	public String getTracoid() {
		return tracoid;
	}

	public void setTracoid(String tracoid) {
		this.tracoid = tracoid;
	}

	public String getBlocoid() {
		return blocoid;
	}

	public void setBlocoid(String blocoid) {
		this.blocoid = blocoid;
	}

	


	
	
	

}
