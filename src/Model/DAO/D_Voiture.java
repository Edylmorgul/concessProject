package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Client;
import Model.BEANS.C_Voiture;
import Model.BEANS.Global;
import oracle.jdbc.OracleTypes;

public class D_Voiture extends DAO<C_Voiture>{
	
	public D_Voiture(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Voiture obj) {
		CallableStatement call = null;
		String sql = "{call P_VOITURE.creer(?,?,?,?,?,?,?,?,?)}";
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getClient().getId());
			call.setString(2, obj.getNumimm());
			call.setString(3, obj.getNumchas());
			call.setString(4, obj.getCouleur());
			call.setInt(5, obj.getKm());
			call.setDate(6, new java.sql.Date(obj.getPrimm().getTime()));
			call.setString(7, obj.getMarque());
			call.setString(8, obj.getModele());
			call.registerOutParameter(9, Types.INTEGER); 
			call.execute();
			
			obj.setId(call.getInt(9));			
			call.close();			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch VOITURE creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Voiture obj) {
		CallableStatement call = null;
		String sql = "{call P_VOITURE.modifier(?,?,?,?,?,?,?,?,?,?)}";
		ResultSet resultSet = null;
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.setInt(2, obj.getClient().getId());
			call.setString(3, obj.getNumimm());
			call.setString(4, obj.getNumchas());
			call.setString(5, obj.getCouleur());
			call.setInt(6, obj.getKm());
			call.setDate(7, new java.sql.Date(obj.getPrimm().getTime()));
			call.setString(8, obj.getMarque());
			call.setString(9, obj.getModele());
			call.registerOutParameter(10, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(10); 
			
			if(resultSet != null && resultSet.next()
				&& obj.getId() == resultSet.getInt(1)
				&& obj.getClient().getId() == resultSet.getInt(2)
				&& obj.getNumimm().equals(resultSet.getString(3))
				&& obj.getNumchas().equals(resultSet.getString(4))
				&& obj.getCouleur().equals(resultSet.getString(5))
				&& obj.getKm() == resultSet.getInt(6)
				&& new java.sql.Date(obj.getPrimm().getTime()).equals(resultSet.getDate(7))
				&& obj.getMarque().equals(resultSet.getString(8))
				&& obj.getModele().equals(resultSet.getString(9))
					){
				resultSet.close();
				call.close();
				return true;
			}
			
			resultSet.close();
			call.close();
			return false;
		}
		
		catch(SQLException e) {
			System.out.println("Catch VOITURE modifier " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean supprimer(C_Voiture obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Client cli = new C_Client();
		String sql = "{call P_VOITURE.supprimer(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getInt(1));
				cli = Global.getFactory().getClientDAO().rechercher(resultSet.getInt(2));
				obj.setClient(cli);
				obj.setNumimm(resultSet.getString(3));
				obj.setNumchas(resultSet.getString(4));
				obj.setCouleur(resultSet.getString(5));
				obj.setKm(resultSet.getInt(6));
				obj.setPrimm(resultSet.getDate(7));
				obj.setMarque(resultSet.getString(8));
				obj.setModele(resultSet.getString(9));
			}
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch VOITURE supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_Voiture rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Voiture voiture = new C_Voiture();
		C_Client cli = new C_Client();
		String sql = "{call P_VOITURE.rechercher(?,?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				voiture.setId(resultSet.getInt(1));
				cli = Global.getFactory().getClientDAO().rechercher(resultSet.getInt(2));
				voiture.setClient(cli);
				voiture.setNumimm(resultSet.getString(3));
				voiture.setNumchas(resultSet.getString(4));
				voiture.setCouleur(resultSet.getString(5));
				voiture.setKm(resultSet.getInt(6));
				voiture.setPrimm(new java.util.Date(resultSet.getDate(7).getTime()));
				voiture.setMarque(resultSet.getString(8));
				voiture.setModele(resultSet.getString(9));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch VOITURE rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return voiture;
	}

	@Override
	public List<C_Voiture> lister() {
		CallableStatement call = null;
		ResultSet resultSet = null;
		List<C_Voiture> liste = new LinkedList<>();
		C_Client cli = new C_Client();
		String sql = "{call P_VOITURE.lister(?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Voiture voiture = new C_Voiture();
					voiture.setId(resultSet.getInt(1));
					cli = Global.getFactory().getClientDAO().rechercher(resultSet.getInt(2));
					voiture.setClient(cli);
					voiture.setNumimm(resultSet.getString(3));
					voiture.setNumchas(resultSet.getString(4));
					voiture.setCouleur(resultSet.getString(5));
					voiture.setKm(resultSet.getInt(6));
					voiture.setPrimm(new java.util.Date(resultSet.getDate(7).getTime()));
					voiture.setMarque(resultSet.getString(8));
					voiture.setModele(resultSet.getString(9));
					liste.add(voiture);
				}				
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch VOITURE lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
