package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Specialisation;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.C_Utilisateur;
import Model.BEANS.Global;
import oracle.jdbc.OracleTypes;

public class D_Garagiste extends DAO<C_Garagiste>{
	
	public D_Garagiste(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Garagiste obj) {
		CallableStatement call = null;
		String sql = "{call P_GARAGISTE.creer(?)}";
		
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
			System.out.println("Catch GARAGISTE creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Garagiste obj) {	
		if(Global.getFactory().getUtilisateurDAO().modifier(obj)) {
			boolean nullValue = false;
			CallableStatement call = null;
			String sql = "{call P_GARAGISTE.modifier(?,?,?)}";
			ResultSet resultSet = null;
			try {
				call = connect.prepareCall(sql);
				call.setInt(1, obj.getId());
				if(obj.getSpecialisation() == null || obj.getSpecialisation().getId()  == 0) {
					call.setNull(2, java.sql.Types.INTEGER);
					nullValue = true;
				}
					
				else
					call.setInt(2, obj.getSpecialisation().getId());
				
				call.registerOutParameter(3, OracleTypes.CURSOR);
				call.executeUpdate();
				resultSet = (ResultSet) call.getObject(3);
				
				if(nullValue) {
					if(resultSet != null && resultSet.next()
						&& obj.getId() == resultSet.getInt(1)
						) {
						resultSet.close();
						call.close();
						return true;
					}
				}
				
				else {
					if(resultSet != null && resultSet.next()
						&& obj.getId() == resultSet.getInt(1)
						&& obj.getSpecialisation().getId() == (resultSet.getInt(2))
						) {
						resultSet.close();
						call.close();
						return true;
					}
				}
							
				resultSet.close();
				call.close();
				return false;
			}
			
			catch(SQLException e) {
				System.out.println("Catch GARAGISTE modifier " + e.getMessage());
				e.printStackTrace();
			}
			
			return false;
		}
		else
			return false;
	}

	@Override
	public boolean supprimer(C_Garagiste obj) {		
		return Global.getFactory().getUtilisateurDAO().supprimer(obj);
	}

	@Override
	public C_Garagiste rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Utilisateur uti = null;
		C_Garagiste gara = null;
		C_Specialisation specialisation = null;
		String sql = "{call P_GARAGISTE.rechercher(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				gara = new C_Garagiste();
				uti = Global.getFactory().getUtilisateurDAO().rechercher(id);
				gara.setId(resultSet.getInt(1));
				specialisation = Global.getFactory().getSpecialisationDAO().rechercher(resultSet.getInt(2));
				gara.setSpecialisation(specialisation);
				gara.setEmail(uti.getEmail());
				gara.setMdp(uti.getMdp());
				gara.setNom(uti.getNom());
				gara.setPrenom(uti.getPrenom());
				gara.setGenre(uti.getGenre());
				gara.setTelephone(uti.getTelephone());
				gara.setActif(uti.getActif());
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch GARAGISTE rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return gara;
	}

	@Override
	public List<C_Garagiste> lister() {
		CallableStatement call = null;
		List<C_Garagiste> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_GARAGISTE.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Garagiste gara = new C_Garagiste();
					C_Utilisateur uti = new C_Utilisateur();
					C_Specialisation specialisation = new C_Specialisation();
					gara.setId(resultSet.getInt(1));
					uti = Global.getFactory().getUtilisateurDAO().rechercher(gara.getId());
					specialisation = Global.getFactory().getSpecialisationDAO().rechercher(resultSet.getInt(2));
					gara.setSpecialisation(specialisation);
					gara.setEmail(uti.getEmail());
					gara.setMdp(uti.getMdp());
					gara.setNom(uti.getNom());
					gara.setPrenom(uti.getPrenom());
					gara.setGenre(uti.getGenre());
					gara.setTelephone(uti.getTelephone());
					gara.setActif(uti.getActif());
					liste.add(gara);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch GARAGISTE lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
	
	// Juste pour faire un exemple d'utilisation de fonction en plsql sinon oui, on aurait pu faire ça en code
	public int rechercherGaragistesDispos(C_TypeIntervention typeIntervention, String debut, String fin) {
        CallableStatement call = null;
        String sql = "{? = call P_GARAGISTE.rechercherGaragistesDispos(?,?,?)}";
        int idg = 0;

        try {
            call = connect.prepareCall(sql);
            call.registerOutParameter(1, Types.INTEGER);
            call.setInt(2, typeIntervention.getSpecialisation().getId());
            call.setString(3, debut);
            call.setString(4, fin);
            call.execute();

            idg = call.getInt(1);
            call.close();
            return idg;
        }

        catch(SQLException e) {
            System.out.println("Catch GARAGISTE rechercherGaragistesDispos " + e.getMessage());
            e.printStackTrace();
        }

        return idg;
    }
}
