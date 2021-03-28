package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	//METODO PER ESEGUIRE QUERY
	    //abbiamo bisogno di tutte le info riguardanti i corsi
	    //PATTERN T??
	public List<Corso> getCorsiByPeriodo(Integer periodo){
		String sql = "SELECT * "
				+ "FROM corso "
				+ "WHERE pd=?";
		
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); //serve per inserire il parametro '?'
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			//Riempio lista dei risultati
			while (rs.next()){
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"), rs.getInt("pd"));
			    result.add(c);
			}
			
			rs.close();
			st.close();
			conn.close(); //IMPORTANTE
		}
		
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}

	public Map<Corso, Integer> getIscrittiByPeriodo(Integer periodo){
		String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) as tot " 
	                 + "FROM corso c, iscrizione i "
			       	 + "WHERE c.codins=i.codins AND c.pd= ? "
				     + "GROUP BY c.codins, c.nome, c.crediti, c.pd";
						
		Map<Corso,Integer> result = new HashMap<Corso,Integer>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); //serve per inserire il parametro '?'
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			//Riempio lista dei risultati
			while (rs.next()){
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"), rs.getInt("pd"));
			    Integer n = rs.getInt("tot"); //recupero numero iscritti
				result.put(c,n);
			}
			
			rs.close();
			st.close();
			conn.close(); //IMPORTANTE
		}
		
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
 
}
