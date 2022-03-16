package pojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class DataBase {
	ResultSet resultat = null;
	private Statement statement;
	private static DataBase db;

	public static DataBase getDataBase() {
		if (db == null)
			db = new DataBase();
		return db;
	}

	public DataBase() {

		try {
//				--module-path "C:\Program Files\openjfx-17.0.2_windows-x64_bin-sdk\javafx-sdk-17.0.2\lib" --add-modules javafx.controls,javafx.fxml
//				Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ars", "root", "");
//				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ars", "root", "root");
			statement = conn.createStatement();
		} catch (Exception ex) {
			System.out.println("Echec de chargement du driver");
		}

	}

	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ars", "root", "");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ars", "root", "root");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;

		}

	}

	public ResultSet SelectFun(String Query) throws SQLException {
		resultat = statement.executeQuery(Query);
		return resultat;
	}

	public void InsertFun(String Query) throws SQLException {
		statement.executeUpdate(Query);
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	
}
