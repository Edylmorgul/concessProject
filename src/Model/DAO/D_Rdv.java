package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Intervention;
import Model.BEANS.C_Rdv;
import Model.BEANS.C_Voiture;
import Model.BEANS.Global;
import oracle.jdbc.OracleTypes;

public class D_Rdv extends DAO<C_Rdv>{
	
	public D_Rdv(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Rdv obj) {
		CallableStatement call = null;
		String sql = "{call P_RDV.creer(?,?,?,?,?,?)}";
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getIntervention().getId());
			call.setInt(2, obj.getVoiture().getId());
			call.setInt(3, obj.getGaragiste().getId());
			call.setTimestamp(4, obj.getDebut());
			call.setTimestamp(5, obj.getFin());
			call.registerOutParameter(6, Types.INTEGER); 
			call.execute();
			
			obj.setId(call.getInt(6));		
			call.close();			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch RDV creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Rdv obj) {
		CallableStatement call = null;
		String sql = "{call P_RDV.modifier(?,?,?,?,?,?,?)}";
		ResultSet resultSet = null;
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.setInt(2, obj.getIntervention().getId());
			call.setInt(3, obj.getVoiture().getId());
			call.setInt(4, obj.getGaragiste().getId());
			call.setTimestamp(5, obj.getDebut());
			call.setTimestamp(6, obj.getFin());
			call.registerOutParameter(7, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(7);
						
			if(resultSet != null && resultSet.next()
				&& obj.getId() == resultSet.getInt(1)
				&& obj.getIntervention().getId() == resultSet.getInt(2)
				&& obj.getVoiture().getId() == resultSet.getInt(3)
				&& obj.getGaragiste().getId() == resultSet.getInt(4)
				&& obj.getDebut().equals(resultSet.getTimestamp(5))
				&& obj.getDebut().equals(resultSet.getTimestamp(6))
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
			System.out.println("Catch RDV modifier " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean supprimer(C_Rdv obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Intervention inter = new C_Intervention();
		C_Voiture v = new C_Voiture();
		C_Garagiste gara = new C_Garagiste();
		String sql = "{call P_RDV.supprimer(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getInt(1));
				inter = Global.getFactory().getInterventionDAO().rechercher(resultSet.getInt(2));
				obj.setIntervention(inter);
				v = Global.getFactory().getVoitureDAO().rechercher(resultSet.getInt(3));
				obj.setVoiture(v);
				gara = Global.getFactory().getGaragisteDAO().rechercher(resultSet.getInt(4));
				obj.setGaragiste(gara);
				obj.setDebut(resultSet.getTimestamp(5));
				obj.setFin(resultSet.getTimestamp(6));
			}
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch RDV supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_Rdv rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Rdv rdv = new C_Rdv();
		C_Intervention inter = new C_Intervention();
		C_Voiture v = new C_Voiture();
		C_Garagiste gara = new C_Garagiste();
		String sql = "{call P_RDV.rechercher(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				rdv.setId(resultSet.getInt(1));
				inter = Global.getFactory().getInterventionDAO().rechercher(resultSet.getInt(2));
				rdv.setIntervention(inter);
				v = Global.getFactory().getVoitureDAO().rechercher(resultSet.getInt(3));
				rdv.setVoiture(v);
				gara = Global.getFactory().getGaragisteDAO().rechercher(resultSet.getInt(4));
				rdv.setGaragiste(gara);
				rdv.setDebut(resultSet.getTimestamp(5));
				rdv.setFin(resultSet.getTimestamp(6));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch RDV rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return rdv;
	}

	@Override
	public List<C_Rdv> lister() {
		CallableStatement call = null;
		ResultSet resultSet = null;
		List<C_Rdv> liste = new LinkedList<>();
		String sql = "{call P_RDV.lister(?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Rdv rdv = new C_Rdv();
					rdv.setId(resultSet.getInt(1));
					C_Intervention inter = new C_Intervention();
					inter = Global.getFactory().getInterventionDAO().rechercher(resultSet.getInt(2));
					rdv.setIntervention(inter);
					C_Voiture v = new C_Voiture();
					v = Global.getFactory().getVoitureDAO().rechercher(resultSet.getInt(3));
					rdv.setVoiture(v);
					C_Garagiste gara = new C_Garagiste();
					gara = Global.getFactory().getGaragisteDAO().rechercher(resultSet.getInt(4));
					rdv.setGaragiste(gara);
					rdv.setDebut(resultSet.getTimestamp(5));
					rdv.setFin(resultSet.getTimestamp(6));
					liste.add(rdv);
				}				
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch RDV lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}

}
