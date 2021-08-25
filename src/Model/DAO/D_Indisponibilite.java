package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Indisponibilite;
import Model.BEANS.Global;
import oracle.jdbc.OracleTypes;

public class D_Indisponibilite extends DAO<C_Indisponibilite>{
	
	public D_Indisponibilite(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Indisponibilite obj) {
		CallableStatement call = null;
		String sql = "{call P_INDISPONIBILITE.creer(?,?,?,?)}";
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getGaragiste().getId());
			call.setDate(2, new java.sql.Date(obj.getDebut().getTime()));
			call.setDate(3, new java.sql.Date(obj.getFin().getTime()));
			call.registerOutParameter(4, Types.INTEGER); 
			call.execute();
			
			obj.setId(call.getInt(4));			
			call.close();			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch INDISPONIBILITE creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Indisponibilite obj) {
		CallableStatement call = null;
		String sql = "{call P_INDISPONIBILITE.modifier(?,?,?,?,?)}";
		ResultSet resultSet = null;
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.setInt(2, obj.getGaragiste().getId());
			call.setDate(3, new java.sql.Date(obj.getDebut().getTime()));
			call.setDate(4, new java.sql.Date(obj.getFin().getTime()));
			call.registerOutParameter(5, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(5);
						
			if(resultSet != null && resultSet.next()
				&& obj.getId() == resultSet.getInt(1)
				&& obj.getGaragiste().getId() == resultSet.getInt(2)
				&& new java.sql.Date(obj.getDebut().getTime()).equals(resultSet.getDate(3))
				&& new java.sql.Date(obj.getFin().getTime()).equals(resultSet.getDate(4))
				) {
				resultSet.close();
				call.close();
				return true;
			}
			
			resultSet.close();
			call.close();
			return false;
		}
		
		catch(SQLException e) {
			System.out.println("Catch INDISPONIBILITE modifier " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean supprimer(C_Indisponibilite obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Garagiste gara = new C_Garagiste();
		String sql = "{call P_INDISPONIBILITE.supprimer(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getInt(1));
				gara = Global.getFactory().getGaragisteDAO().rechercher(resultSet.getInt(2));
				obj.setGaragiste(gara);
				obj.setDebut(resultSet.getDate(3));
				obj.setFin(resultSet.getDate(4));
			}
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch INDISPONIBILITE supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_Indisponibilite rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Garagiste gara = new C_Garagiste();
		C_Indisponibilite indi = new C_Indisponibilite();
		String sql = "{call P_INDISPONIBILITE.rechercher(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				indi.setId(resultSet.getInt(1));
				gara = Global.getFactory().getGaragisteDAO().rechercher(resultSet.getInt(2));
				indi.setGaragiste(gara);
				indi.setDebut(resultSet.getDate(3));
				indi.setFin(resultSet.getDate(4));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch INDISPONIBILITE rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return indi;
	}

	@Override
	public List<C_Indisponibilite> lister() {
		CallableStatement call = null;
		ResultSet resultSet = null;
		List<C_Indisponibilite> liste = new LinkedList<>();
		String sql = "{call P_INDISPONIBILITE.lister(?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Indisponibilite indi = new C_Indisponibilite();
					indi.setId(resultSet.getInt(1));
					C_Garagiste gara = new C_Garagiste();
					gara = Global.getFactory().getGaragisteDAO().rechercher(resultSet.getInt(2));
					indi.setGaragiste(gara);
					indi.setDebut(resultSet.getDate(3));
					indi.setFin(resultSet.getDate(4));
					liste.add(indi);
				}				
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch INDISPONIBILITE lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
