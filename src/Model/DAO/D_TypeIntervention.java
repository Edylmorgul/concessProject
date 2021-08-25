package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Specialisation;
import Model.BEANS.C_TypeIntervention;
import oracle.jdbc.OracleTypes;

public class D_TypeIntervention extends DAO<C_TypeIntervention>{
	
	public D_TypeIntervention(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_TypeIntervention obj) {
        CallableStatement call = null;
        String sql = "{call P_TYPEINTERVENTION.creer(?,?,?,?,?)}";
        try {
            call = connect.prepareCall(sql);
            call.setString(1, obj.getNom());
            call.setDouble(2, obj.getHonoraire());
            call.setInt(3, obj.getDureepr());
            call.setInt(4, obj.getSpecialisation().getId());
            call.registerOutParameter(5, Types.INTEGER); 
            call.execute();

            obj.setId(call.getInt(5));
            call.close();
            return true;
        }

        catch(SQLException e) {
            System.out.println("Catch TYPEINTERVENTION creer " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

	@Override
	public boolean modifier(C_TypeIntervention obj) {
		CallableStatement call = null;
		String sql = "{call P_TYPEINTERVENTION.modifier(?,?,?,?,?,?)}";
		ResultSet resultSet = null;
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.setString(2, obj.getNom());
			call.setDouble(3, obj.getHonoraire());
			call.setInt(4, obj.getDureepr());
			call.setInt(5, obj.getSpecialisation().getId());
			call.registerOutParameter(6, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(6);
						
			if(resultSet != null && resultSet.next()
				&& obj.getId() == resultSet.getInt(1)
				&& obj.getNom().equals(resultSet.getString(2))
				&& obj.getHonoraire() == resultSet.getDouble(3)
				&& obj.getDureepr() == resultSet.getInt(4)
				&& obj.getSpecialisation().getId() == resultSet.getInt(5)
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
			System.out.println("Catch TYPEINTERVENTION modifier " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean supprimer(C_TypeIntervention obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		String sql = "{call P_TYPEINTERVENTION.supprimer(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getInt(1));
				obj.setNom(resultSet.getString(2));
				obj.setHonoraire(resultSet.getDouble(3));
				obj.setDureepr(resultSet.getInt(4));
			}
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch TYPEINTERVENTION supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_TypeIntervention rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_TypeIntervention typeInter = new C_TypeIntervention();
		C_Specialisation spe = new C_Specialisation();
		String sql = "{call P_TYPEINTERVENTION.rechercher(?,?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				typeInter.setId(resultSet.getInt(1));
				typeInter.setNom(resultSet.getString(2));
				typeInter.setHonoraire(resultSet.getDouble(3));
				typeInter.setDureepr(resultSet.getInt(4));
				typeInter.setSpecialisation(spe.trouver(resultSet.getInt(5)));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch TYPEINTERVENTION rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return typeInter;
	}

	@Override
	public List<C_TypeIntervention> lister() {
		CallableStatement call = null;
		List<C_TypeIntervention> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_TYPEINTERVENTION.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_TypeIntervention typeInter = new C_TypeIntervention();
					C_Specialisation spe = new C_Specialisation();
					typeInter.setId(resultSet.getInt(1));
					typeInter.setNom(resultSet.getString(2));
					typeInter.setHonoraire(resultSet.getDouble(3));
					typeInter.setDureepr(resultSet.getInt(4));
					typeInter.setSpecialisation(spe.trouver(resultSet.getInt(5)));
					liste.add(typeInter);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch TYPEINTERVENTION lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
