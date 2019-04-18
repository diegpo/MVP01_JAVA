package sistema.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {
	public static Connection conector() {
		
		java.sql.Connection conexao = null;
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/sistema_mvp2";
		String usr = "usr_mvp";
        String pass = "senha@123";
        
        try {
        	Class.forName(driver);
        	conexao = DriverManager.getConnection(url, usr, pass);
        	return conexao;
        	
        }catch (Exception e) {
        	System.out.println(e);
        	return null;        	
        }		
	}
}