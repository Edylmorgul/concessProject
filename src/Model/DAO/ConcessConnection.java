package Model.DAO;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public final class ConcessConnection {
	static String connectionString = "jdbc:oracle:thin:@//193.190.64.10:1522/XEPDB1";
	static String userName = "STUDENT03_23";
	static String password = "C4eVr3";
	
	// Local connexion
//	static String connectionString = "jdbc:oracle:thin:@localhost:1521:xe";
//	static String userName = "CONCESS";
//	static String password = "concess";
	public static URI getBaseUri() {
		return UriBuilder.fromUri("http://localhost:8080/ProjectConcessApi/rest").build();
	}
}
