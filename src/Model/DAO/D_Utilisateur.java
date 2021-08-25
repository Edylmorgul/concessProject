package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Utilisateur;
import oracle.jdbc.OracleTypes;

public class D_Utilisateur extends DAO<C_Utilisateur> {
	
	public D_Utilisateur(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Utilisateur obj) {
		CallableStatement call = null;
		String sql = "{call P_UTILISATEUR.creer(?,?,?,?,?,?,?,?)}";
		try {
			call = connect.prepareCall(sql);
			call.setString(1, obj.getEmail());
			call.setString(2, obj.getMdp());
			call.setString(3, obj.getNom());
			call.setString(4, obj.getPrenom());
			call.setInt(5, obj.getGenre());
			call.setString(6, obj.getTelephone());
			call.setInt(7, obj.getActif());
			call.registerOutParameter(8, Types.INTEGER); 
			call.execute();
			
			obj.setId(call.getInt(8));			
			call.close();			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch UTILISATEUR creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Utilisateur obj) {
		CallableStatement call = null;
		String sql = "{call P_UTILISATEUR.modifier(?,?,?,?,?,?,?,?,?)}";
		ResultSet resultSet = null;
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.setString(2, obj.getEmail());
			call.setString(3, obj.getMdp());
			call.setString(4, obj.getNom());
			call.setString(5, obj.getPrenom());
			call.setInt(6, obj.getGenre());
			call.setString(7, obj.getTelephone());
			call.setInt(8, obj.getActif());
			call.registerOutParameter(9, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(9);
						
			if(resultSet != null && resultSet.next()
				&& obj.getId() == resultSet.getInt(1)
				&& obj.getEmail().equals(resultSet.getString(2))
				&& obj.getMdp().equals(resultSet.getString(3))
				&& obj.getNom().equals(resultSet.getString(4))
				&& obj.getPrenom().equals(resultSet.getString(5))
				&& obj.getGenre() == resultSet.getInt(6)
				&& obj.getTelephone().equals(resultSet.getString(7))
				&& obj.getActif() == resultSet.getInt(8)
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
			System.out.println("Catch UTILISATEUR modifier " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean supprimer(C_Utilisateur obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		String sql = "{call P_UTILISATEUR.supprimer(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getInt(1));
				obj.setEmail(resultSet.getString(2));
				obj.setMdp(resultSet.getString(3));
				obj.setNom(resultSet.getString(4));
				obj.setPrenom(resultSet.getString(5));
				obj.setGenre(resultSet.getInt(6));
				obj.setTelephone(resultSet.getString(7));
				obj.setActif(resultSet.getInt(8));
			}
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch UTILISATEUR supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_Utilisateur rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Utilisateur uti = new C_Utilisateur();
		String sql = "{call P_UTILISATEUR.rechercher(?,?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				uti.setId(resultSet.getInt(1));
				uti.setEmail(resultSet.getString(2));
				uti.setMdp(resultSet.getString(3));
				uti.setNom(resultSet.getString(4));
				uti.setPrenom(resultSet.getString(5));
				uti.setGenre(resultSet.getInt(6));
				uti.setTelephone(resultSet.getString(7));
				uti.setActif(resultSet.getInt(8));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch UTILISATEUR rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return uti;
	}

	@Override
	public List<C_Utilisateur> lister() {
		CallableStatement call = null;
		List<C_Utilisateur> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_UTILISATEUR.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Utilisateur uti = new C_Utilisateur();
					uti.setId(resultSet.getInt(1));
					uti.setEmail(resultSet.getString(2));
					uti.setMdp(resultSet.getString(3));
					uti.setNom(resultSet.getString(4));
					uti.setPrenom(resultSet.getString(5));
					uti.setGenre(resultSet.getInt(6));
					uti.setTelephone(resultSet.getString(7));
					uti.setActif(resultSet.getInt(8));
					liste.add(uti);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch UTILISATEUR lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}