package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Piece;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.C_TypeInterventionPiece;
import oracle.jdbc.OracleTypes;

public class D_TypeInterventionPiece extends DAO<C_TypeInterventionPiece>{

	public D_TypeInterventionPiece(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean creer(C_TypeInterventionPiece obj) {
		CallableStatement call = null;
        String sql = "{call P_TYPEINTERVENTION_PIECE.creer(?,?)}";
        try {
            call = connect.prepareCall(sql);
            call.setInt(1, obj.getTypeIntervention().getId());
            call.setInt(2, obj.getPiece().getId());
            call.execute();

            call.close();
            return true;
        }

        catch(SQLException e) {
            System.out.println("Catch P_TYPEINTERVENTION_PIECE creer " + e.getMessage());
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean modifier(C_TypeInterventionPiece obj) {
		CallableStatement call = null;
        String sql = "{call P_TYPEINTERVENTION_PIECE.modifier(?,?,?)}";
        ResultSet resultSet = null;
        
        try {
            call = connect.prepareCall(sql);
            call.setInt(1, obj.getTypeIntervention().getId());
            call.setInt(2, obj.getPiece().getId());
            call.registerOutParameter(3, OracleTypes.CURSOR);
			call.executeUpdate();
			resultSet = (ResultSet) call.getObject(3);

            call.close();
            return true;
        }

        catch(SQLException e) {
            System.out.println("Catch P_TYPEINTERVENTION_PIECE modifier " + e.getMessage());
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean supprimer(C_TypeInterventionPiece obj) {
		CallableStatement call = null;
		ResultSet resultSet = null;
		String sql = "{call P_TYPEINTERVENTION_PIECE.supprimer(?,?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, obj.getTypeIntervention().getId());
            call.setInt(2, obj.getPiece().getId());
			call.registerOutParameter(3, OracleTypes.CURSOR);
			call.executeUpdate();			
			resultSet = (ResultSet) call.getObject(3);
			
			resultSet.close();
			call.close();
			
			return true;
		}
		
		catch(SQLException e) {
			System.out.println("Catch P_TYPEINTERVENTION_PIECE supprimer " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public C_TypeInterventionPiece rechercher(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public C_TypeInterventionPiece rechercher(int idt, int idp) {
		CallableStatement call = null;
		C_TypeInterventionPiece typeInterventionPiece = new C_TypeInterventionPiece();
		C_TypeIntervention typeIntervention = new C_TypeIntervention();
		C_Piece piece = new C_Piece();
		ResultSet resultSet = null;
		String sql = "{call P_TYPEINTERVENTION_PIECE.rechercher(?,?,?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, idt);
			call.setInt(2, idp);
			call.registerOutParameter(3, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null && resultSet.next()) {
				typeInterventionPiece.setTypeIntervention(typeIntervention.trouver(resultSet.getInt(1)));
				typeInterventionPiece.setPiece(piece.trouver(resultSet.getInt(2)));
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch TYPEINTERVENTIONPIECE rechercher " + e.getMessage());
			e.printStackTrace();
		}
		
		return typeInterventionPiece;
	}
	
	@Override
	public List<C_TypeInterventionPiece> lister() {
		CallableStatement call = null;
		List<C_TypeInterventionPiece> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_TYPEINTERVENTION_PIECE.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_TypeInterventionPiece typeInterPiece = new C_TypeInterventionPiece();
					C_Piece piece = new C_Piece();
					C_TypeIntervention typeInter = new C_TypeIntervention();
					typeInterPiece.setTypeIntervention(typeInter.trouver(resultSet.getInt(1)));
					typeInterPiece.setPiece(piece.trouver(resultSet.getInt(2)));
					liste.add(typeInterPiece);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch P_TYPEINTERVENTION_PIECE lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
