package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	
	private CorsoDAO corsodao;
	
	public Model() {
		//inizializza ogni volta ceh chiamo il costruttore
		corsodao = new CorsoDAO();
	}
	
	public List<Corso> getCorsoByPeriodo(Integer pd){
		//Chiama il metodo omonimo di CorsoDAO 
		return corsodao.getCorsiByPeriodo(pd);
		
	}
	
	public Map <Corso, Integer> getIscrittiByPeriodo(Integer pd){
		//Chiama il metodo omonimo di CorsoDAO
		return corsodao.getIscrittiByPeriodo(pd);
	}
}
