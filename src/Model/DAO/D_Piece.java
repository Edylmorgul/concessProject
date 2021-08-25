package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Piece;
import oracle.jdbc.OracleTypes;

public class D_Piece extends DAO<C_Piece>{
	
	public D_Piece(Connection conn) {
		super(conn);
	}

	@Override
	public boolean creer(C_Piece obj) {
		CallableStatement call = null;
		String sql = "{call P_PIECE.creer(?,?,?)}";
		try {
			call = connect.prepareCall(sql);
			call.setString(1, obj.getNom());
			call.setDouble(2, obj.getPrix());
			call.registerOutParameter(3, Types.INTEGER); 
			call.execute();
			
			obj.setId(call.getInt(3));		
			call.close();			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch PIECE creer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(C_Piece obj) {
		CallableStatement call = null;
		String sql = "{call P_PIECE.modifier(?,?,?,?)}";
		ResultSet resultSet = null;
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.setString(2, obj.getNom());
			call.setDouble(3, obj.getPrix());
			call.registerOutParameter(4, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(4);
						
			if(resultSet != null && resultSet.next()
				&& obj.getId() == resultSet.getInt(1)
				&& obj.getNom().equals(resultSet.getString(2))
				&& obj.getPrix() == resultSet.getDouble(3)
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
			System.out.println("Catch PIECE modifier " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean supprimer(C_Piece obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		String sql = "{call P_PIECE.supprimer(?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getInt(1));
				obj.setNom(resultSet.getString(2));
				obj.setPrix(resultSet.getDouble(3));
			}
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch PIECE supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_Piece rechercher(int id) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		C_Piece piece = new C_Piece();
		String sql = "{call P_PIECE.rechercher(?,?)}";
		
		try {			
			call = connect.prepareCall(sql);
			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(2);
			
			if(resultSet != null && resultSet.next()) {
				piece.setId(resultSet.getInt(1));
				piece.setNom(resultSet.getString(2));
				piece.setPrix(resultSet.getDouble(3));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch PIECE rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return piece;
	}

	@Override
	public List<C_Piece> lister() {
		CallableStatement call = null;
		List<C_Piece> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_PIECE.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_Piece piece = new C_Piece();
					piece.setId(resultSet.getInt(1));
					piece.setNom(resultSet.getString(2));
					piece.setPrix(resultSet.getDouble(3));
					liste.add(piece);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch PIECE lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
