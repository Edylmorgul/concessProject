package Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Model.BEANS.C_Intervention;
import Model.BEANS.C_InterventionTypeInterventionPiece;
import Model.BEANS.C_Piece;
import Model.BEANS.C_TypeIntervention;
import oracle.jdbc.OracleTypes;

public class D_InterventionTypeInterventionPiece extends DAO<C_InterventionTypeInterventionPiece> {

	public D_InterventionTypeInterventionPiece(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean creer(C_InterventionTypeInterventionPiece obj) {
		CallableStatement call = null;
        String sql = "{call P_INTERVENTION_TYPEINTERVENTION_PIECE.creer(?,?,?,?)}";
        try {
            call = connect.prepareCall(sql);
            call.setInt(1, obj.getIntervention().getId());
            call.setInt(2, obj.getTypeIntervention().getId());
            call.setInt(3, obj.getPiece().getId());
            call.setInt(4, obj.getNombre());
            call.execute();

            call.close();
            return true;
        }

        catch(SQLException e) {
            System.out.println("Catch P_INTERVENTION_TYPEINTERVENTION_PIECE creer " + e.getMessage());
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean modifier(C_InterventionTypeInterventionPiece obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(C_InterventionTypeInterventionPiece obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public C_InterventionTypeInterventionPiece rechercher(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<C_InterventionTypeInterventionPiece> lister() {
		CallableStatement call = null;
		List<C_InterventionTypeInterventionPiece> liste = new LinkedList<>();
		ResultSet resultSet = null;
		String sql = "{call P_INTERVENTION_TYPEINTERVENTION_PIECE.lister(?)}";
		
		try {
			call = connect.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			resultSet = (ResultSet) call.getObject(1);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					C_InterventionTypeInterventionPiece typeInterPiece = new C_InterventionTypeInterventionPiece();
					C_Piece piece = new C_Piece();
					C_TypeIntervention typeInter = new C_TypeIntervention();
					C_Intervention inter = new C_Intervention();
					typeInterPiece.setIntervention(inter.trouver(resultSet.getInt(1)));
					typeInterPiece.setTypeIntervention(typeInter.trouver(resultSet.getInt(2)));
					typeInterPiece.setPiece(piece.trouver(resultSet.getInt(3)));
					typeInterPiece.setNombre(resultSet.getInt(4));
					liste.add(typeInterPiece);
				}
			}
			
			resultSet.close();
			call.close();
		}
		
		catch(SQLException e) {
			System.out.println("Catch P_INTERVENTION_TYPEINTERVENTION_PIECE lister " + e.getMessage());
			e.printStackTrace();
		}
		
		return liste;
	}
}
