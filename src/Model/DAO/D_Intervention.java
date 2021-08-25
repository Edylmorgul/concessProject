package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Intervention;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.Global;
import oracle.jdbc.OracleTypes;

public class D_Intervention extends DAO<C_Intervention>{
	
	public D_Intervention(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Intervention obj) {
		CallableStatement call = null;
		String sql = "{call P_INTERVENTION.creer(?,?,?,?)}";
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getTypeIntervention().getId());
			call.setInt(2, obj.getDuree());
			call.setDouble(3, obj.getCt());
			call.registerOutParameter(4, Types.INTEGER); 
			call.execute();
			
			obj.setId(call.getInt(4));			
			call.close();			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch INTERVENTION creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Intervention obj) {
		CallableStatement call = null;
		String sql = "{call P_INTERVENTION.modifier(?,?,?,?,?)}";
		ResultSet resultSet = null;
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.setInt(2, obj.getTypeIntervention().getId());
			call.setInt(3, obj.getDuree());
			call.setDouble(4, obj.getCt());
			call.registerOutParameter(5, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(5);
						
			if(resultSet != null && resultSet.next()
				&& obj.getId() == resultSet.getInt(1)
				&& obj.getTypeIntervention().getId() == resultSet.getInt(2)
				&& obj.getDuree() == resultSet.getInt(3)
				&& obj.getCt() == resultSet.getDouble(4)
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
			System.out.println("Catch INTERVENTION modifier " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean supprimer(C_Intervention obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_TypeIntervention typeInter = new C_TypeIntervention();
		String sql = "{call P_INTERVENTION.supprimer(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getInt(1));
				typeInter = Global.getFactory().getTypeInterventionDAO().rechercher(resultSet.getInt(2));
				obj.setTypeIntervention(typeInter);
				obj.setDuree(resultSet.getInt(3));
				obj.setCt(resultSet.getDouble(4));
			}
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch INTERVENTION supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_Intervention rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Intervention inter = new C_Intervention();
		C_TypeIntervention typeInter = new C_TypeIntervention();
		String sql = "{call P_INTERVENTION.rechercher(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				inter.setId(resultSet.getInt(1));
				typeInter = Global.getFactory().getTypeInterventionDAO().rechercher(resultSet.getInt(2));
				inter.setTypeIntervention(typeInter);
				inter.setDuree(resultSet.getInt(3));
				inter.setCt(resultSet.getDouble(4));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch INTERVENTION rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return inter;
	}

	@Override
	public List<C_Intervention> lister() {
		CallableStatement call = null;
		ResultSet resultSet = null;
		List<C_Intervention> liste = new LinkedList<>();
		String sql = "{call P_INTERVENTION.lister(?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Intervention inter = new C_Intervention();
					inter.setId(resultSet.getInt(1));
					C_TypeIntervention typeInter = new C_TypeIntervention();
					typeInter = Global.getFactory().getTypeInterventionDAO().rechercher(resultSet.getInt(2));
					inter.setTypeIntervention(typeInter);
					inter.setDuree(resultSet.getInt(3));
					inter.setCt(resultSet.getDouble(4));
					liste.add(inter);
				}				
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch INTERVENTION lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
