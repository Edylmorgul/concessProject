package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Administrateur;
import Model.BEANS.C_Utilisateur;
import Model.BEANS.Global;
import oracle.jdbc.OracleTypes;

public class D_Administrateur extends DAO<C_Administrateur>{
	
	public D_Administrateur(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Administrateur obj) {
		CallableStatement call = null;
		String sql = "{call P_ADMINISTRATEUR.creer(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(1, Types.INTEGER);
			call.execute();	
			
			obj.setId(call.getInt(1));			
			call.close();			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch ADMINISTRATEUR creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Administrateur obj) {		
		return Global.getFactory().getUtilisateurDAO().modifier(obj);
	}

	@Override
	public boolean supprimer(C_Administrateur obj) {		
		return Global.getFactory().getUtilisateurDAO().supprimer(obj);
	}

	@Override
	public C_Administrateur rechercher(int id) {		
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Utilisateur uti = null;
		C_Administrateur admin = null;
		String sql = "{call P_ADMINISTRATEUR.rechercher(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				admin = new C_Administrateur();
				uti = Global.getFactory().getUtilisateurDAO().rechercher(id);
				admin.setId(resultSet.getInt(1));
				admin.setEmail(uti.getEmail());
				admin.setMdp(uti.getMdp());
				admin.setNom(uti.getNom());
				admin.setPrenom(uti.getPrenom());
				admin.setGenre(uti.getGenre());
				admin.setTelephone(uti.getTelephone());
				admin.setActif(uti.getActif());
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch ADMINISTRATEUR rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return admin;
	}

	@Override
	public List<C_Administrateur> lister() {		
		CallableStatement call = null;
		List<C_Administrateur> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_ADMINISTRATEUR.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Administrateur admin = new C_Administrateur();
					C_Utilisateur uti = new C_Utilisateur();
					uti.setId(resultSet.getInt(1));	
					uti = Global.getFactory().getUtilisateurDAO().rechercher(admin.getId());
					admin.setEmail(uti.getEmail());
					admin.setMdp(uti.getMdp());
					admin.setNom(uti.getNom());
					admin.setPrenom(uti.getPrenom());
					admin.setGenre(uti.getGenre());
					admin.setTelephone(uti.getTelephone());
					admin.setActif(uti.getActif());
					liste.add(admin);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch ADMINISTRATEUR lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
