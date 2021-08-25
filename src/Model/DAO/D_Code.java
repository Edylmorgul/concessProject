package Model.DAO;

import java.sql.Connection;
import java.util.List;

import Model.BEANS.C_Code;

public class D_Code extends DAO<C_Code>{

	public D_Code(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean creer(C_Code obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifier(C_Code obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(C_Code obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public C_Code rechercher(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<C_Code> lister() {
		// TODO Auto-generated method stub
		return null;
	}

}
