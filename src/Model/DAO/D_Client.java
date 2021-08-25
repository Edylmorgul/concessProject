package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Client;
import Model.BEANS.C_Utilisateur;
import Model.BEANS.Global;
import oracle.jdbc.OracleTypes;

public class D_Client extends DAO<C_Client>{
	
	public D_Client(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Client obj) {		
		CallableStatement call = null;
		String sql = "{call P_CLIENT.creer(?)}";
		
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
			System.out.println("Catch CLIENT creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Client obj) {		
		return Global.getFactory().getUtilisateurDAO().modifier(obj);
	}

	@Override
	public boolean supprimer(C_Client obj) {		
		return Global.getFactory().getUtilisateurDAO().supprimer(obj);
	}

	@Override
	public C_Client rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Utilisateur uti = null;
		C_Client cli = null;
		String sql = "{call P_CLIENT.rechercher(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {	
				cli = new C_Client();
				uti = Global.getFactory().getUtilisateurDAO().rechercher(id);
				cli.setId(resultSet.getInt(1));
				cli.setEmail(uti.getEmail());
				cli.setMdp(uti.getMdp());
				cli.setNom(uti.getNom());
				cli.setPrenom(uti.getPrenom());
				cli.setGenre(uti.getGenre());
				cli.setTelephone(uti.getTelephone());
				cli.setActif(uti.getActif());
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch CLIENT rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return cli;
	}

	@Override
	public List<C_Client> lister() {
		CallableStatement call = null;
		List<C_Client> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_CLIENT.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Client cli = new C_Client();
					C_Utilisateur uti = new C_Utilisateur();					
					cli.setId(resultSet.getInt(1));
					uti = Global.getFactory().getUtilisateurDAO().rechercher(cli.getId());
					cli.setEmail(uti.getEmail());
					cli.setMdp(uti.getMdp());
					cli.setNom(uti.getNom());
					cli.setPrenom(uti.getPrenom());
					cli.setGenre(uti.getGenre());
					cli.setTelephone(uti.getTelephone());
					cli.setActif(uti.getActif());
					liste.add(cli);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch CLIENT lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
