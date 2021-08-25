package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Specialisation;
import oracle.jdbc.OracleTypes;

public class D_Specialisation extends DAO<C_Specialisation>{
	
	public D_Specialisation(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Specialisation obj) {
		CallableStatement call = null;
		String sql = "{call P_SPECIALISATION.creer(?,?)}";
		try {
			call = connect.prepareCall(sql);
			call.setString(1, obj.getNom());
			call.registerOutParameter(2, Types.INTEGER); 
			call.execute();
			
			obj.setId(call.getInt(2));			
			call.close();			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch SPECIALISATION creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Specialisation obj) {
		CallableStatement call = null;
		String sql = "{call P_SPECIALISATION.modifier(?,?)}";
		ResultSet resultSet = null;
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.setString(2, obj.getNom());
			call.registerOutParameter(3, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(3);
						
			if(resultSet != null && resultSet.next()
				&& obj.getId() == resultSet.getInt(1)
				&& obj.getNom().equals(resultSet.getString(2))
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
			System.out.println("Catch SPECIALISATION modifier " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean supprimer(C_Specialisation obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		String sql = "{call P_SPECIALISATION.supprimer(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getInt(1));
				obj.setNom(resultSet.getString(2));
			}
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch SPECIALISATION supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_Specialisation rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Specialisation spec = new C_Specialisation();
		String sql = "{call P_SPECIALISATION.rechercher(?,?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				spec.setId(resultSet.getInt(1));
				spec.setNom(resultSet.getString(2));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch SPECIALISATION rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return spec;
	}

	@Override
	public List<C_Specialisation> lister() {
		CallableStatement call = null;
		List<C_Specialisation> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_SPECIALISATION.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Specialisation spec = new C_Specialisation();
					spec.setId(resultSet.getInt(1));
					spec.setNom(resultSet.getString(2));
					liste.add(spec);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch SPECIALISATION lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
