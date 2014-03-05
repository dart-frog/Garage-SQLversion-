import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


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
			//String sql = "INSERT INTO nate.Vehicle(make,model,year,typeId, range, capacity, efficiency) VALUES ('" + make + "', '" + model + "', " + year + ", " + type + "," + (int)(efficiency * capacity) +"," + efficiency + "," + capacity +")";
			String sql = "INSERT INTO nate.Vehicle(make,model,year,typeId, range, capacity, efficiency) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, make);
			pstmt.setString(2, model);
			pstmt.setLong(3, year);
			pstmt.setLong(4, type);
			pstmt.setLong(5, (int)(efficiency * capacity));
			pstmt.setLong(6, (long)capacity);
			pstmt.setLong(7, (long)efficiency);
			int count = pstmt.executeUpdate();
			System.out.println("ROWS AFFECTED:" + count);
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void deleteVehicle(int id){
		Connection con = getConnection();
		try{
		String sql = "DELETE FROM nate.Vehicle WHERE id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, id);
		int count = pstmt.executeUpdate();
		System.out.println("ROWS AFECTED:" + count);
		pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	  
	
	public String[][] retrieve(){
		Connection con = getConnection();
		ArrayList<String[]> g = new ArrayList<String[]>();
		try{
			String sql = "SELECT make,model,year,typeId,range,capacity, efficiency FROM nate.Vehicle";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()){
				
				g.add(new String[7]);
				g.get(i)[0] = rs.getString("make");
				g.get(i)[1] = rs.getString("model");
				g.get(i)[2] = String.valueOf(rs.getLong("year"));
				int t = rs.getInt("typeId");
				g.get(i)[3] = typeToString(t);
				g.get(i)[4] = String.valueOf(rs.getLong("range"));
				if (t == 1){
					g.get(i)[5] = null;
					g.get(i)[6] = null;
				}
				else {
					g.get(i)[5] = String.valueOf(rs.getLong("capacity"));
					g.get(i)[6] = String.valueOf(rs.getLong("efficiency"));
				}
				i++;
				//g += rs.getString("make") + ", " + rs.getString("model") + ", " + rs.getInt("year") + "\n";
			}
			rs.close();
			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String[][] n = new String[g.size()][7]; 
		for (int i = 0; i < g.size(); i++ ){
			for (int j = 0; j < 7; j++){
				n[i][j]=g.get(i)[j];
			}
		}
		return n;
	}
	public String typeToString(int x){
		if (x == 1){
			return "Boat";
		}
		else if (x == 2)
			return "Electric Car";
		else{
			return "Gas Car";
		}
	}
	
}

