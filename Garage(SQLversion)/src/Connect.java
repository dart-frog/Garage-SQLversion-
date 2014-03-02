import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Connect {
	private java.sql.Connection con = null;
	private final String url = "jdbc:sqlserver://";
	private final String serverName= "fourwaylo.com";
	private final String portNumber = "8889";
	private final String databaseName = "csproj";
	private final String userName = "csproj";
	private final String password = "DoYourHomework";
	private final String selectMethod = "cursor";
	
	public Connect(){}
	
	private String getConnectionUrl(){
		return url+serverName+":"+portNumber+";databaseName="+databaseName+";selectMethod=" + selectMethod+ ";";
	}
	
	public java.sql.Connection getConnection(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerConnection");
				con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
				if(con != null) System.out.println("Connection Successful");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error Trace in getConnection() : " + e.getMessage());
			}
			return con;
	}
		/*
		public void displayDbProperties(){
			java.sql.DatabaseMetaData dm = null;
			java.sql.ResultSet rs = null;
			try{
				con = this.getConnection();
				if(con!=null){
					dm = con.getMetaData();
					System.out.println("Driver Information");
					System.out.println("\tDriver Name: " + dm.getDriverName());
					System.out.println("\tDriver Version: " + dm.getDriverVersion());
					System.out.println("\nDatabase Information: ");
					System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
					System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());
					System.out.println("Avalilable Catalogs ");
					rs = dm.getCatalogs();
					while(rs.next()){
						System.out.println("\tcatalog: " + rs.getString(1));
					}
					rs.close();
					rs = null;
					closeConnection();
			}else System.out.println("Error: No active Connection");
		}catch(Exception e){
			e.printStackTrace();
		}
		dm = null;
	}
	*/
	private void closeConnection(){
		try{
			if(con!= null)
				con.close();
			con = null;
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) throws Exception{
		Connect myDbTest = new Connect();
		//myDbTest.retrieve();
		
	}
	public void addNewBoat(String make, String model, int year, int range){
		Connection con = getConnection();
		try{
			String sql = "INSERT INTO nate.Vehicle(make,model,year,typeId, range) VALUES ('" + make + "', '" + model + "', " + year + ", " + 1 + "," + range +")";
			Statement stmt = con.createStatement();
			int count = stmt.executeUpdate(sql);
			System.out.println("ROWS AFFECTED:" + count);
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void addNewCar(String make, String model, int year, int type, double efficiency, double capacity){
		Connection con = getConnection();
		try{
			String sql = "INSERT INTO nate.Vehicle(make,model,year,typeId, range, capacity, efficiency) VALUES ('" + make + "', '" + model + "', " + year + ", " + type + "," + (int)(efficiency * capacity) +"," + efficiency + "," + capacity +")";
			Statement stmt = con.createStatement();
			int count = stmt.executeUpdate(sql);
			System.out.println("ROWS AFFECTED:" + count);
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public String retrieve(){
		Connection con = getConnection();
		String g = "";
		try{
			String sql = "SELECT make,model,year FROM nate.Vehicle";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				g += rs.getString("make") + ", " + rs.getString("model") + ", " + rs.getInt("year") + "\n";
			}
			rs.close();
			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return g;
	}
	
}

