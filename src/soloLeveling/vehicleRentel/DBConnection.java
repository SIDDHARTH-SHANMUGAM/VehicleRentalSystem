package soloLeveling.vehicleRentel;

import java.sql.*;
import java.util.Scanner;

public class DBConnection {
	Connection con;
	PreparedStatement pstmt;
	String query;
	
// Connection
	public void getVRSDBconnection() 
	{
		String url="jdbc:mysql://localhost:3306/VRS";
		String userName="root";
		String password="";
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			System.out.println("DB CONNECTION ERROR\n"+e);
		}
		return;
	}
	
//User
	public boolean addNewUser(User userObject)
	{
		query = "INSERT INTO user( userEmail, userName, userAddress, userMobile, userPassword) VALUES( ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userObject.getUserEmail());
			pstmt.setString(2, userObject.getUserName());
			pstmt.setString(3, userObject.getUserAddress());
			pstmt.setString(4, userObject.getUserMobile());
			pstmt.setString(5, userObject.getUserPassword());
			byte res = (byte) pstmt.executeUpdate();
			if(res>(byte)0)
			{
				addDeposit(getUserIdByEmail(userObject.getUserEmail()));
				return true;
			}
		} catch (SQLException e) {
			System.out.println("DB User INSERT ERROR\n"+e);
		}
		return false;
	}
	public int getUserIdByEmail(String userEmail)
	{
		query = "SELECT userId FROM user WHERE userEmail = ? ";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEmail);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next())
				return rs.getInt("userId");
		} catch (SQLException e) {
			System.out.println("DB ERROR\n"+e);
		}
		return 0;
	}
	public boolean isUserEmailAvailable(String userEmail)
	{
		query = "SELECT * FROM user WHERE userEmail = ? ";
	
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEmail);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println("DB ERROR\n"+e);
		}
		return false;
	}
	public User getUserByEmail(String userEmail)
	{
		query = "SELECT * FROM user WHERE userEmail = ? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEmail);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next())
			{
				int userId = rs.getInt("userId");
				String userName = rs.getString("userName");
                String userAddress = rs.getString("userAddress");
                String userMobile = rs.getString("userMobile");
                String userPassword = rs.getString("userPassword");
                
                return new User(userId, userEmail, userName, userAddress, userMobile, userPassword);
			}
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return new User();
	}
	
//	Admin
	public boolean isAdmin(int userId)
	{
		query = "SELECT * FROM admin WHERE userId = ? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println("DB User Admin Verification ERROR\n"+e);
		}
		return false;
	}
	
	
	
	
// Vehicles
	public void addVehicle(Vehicle vehicleObject)
	{
		query = "INSERT INTO vehicle(vehicleType,vehicleName, vehicleModel , numberPlate, vehicleMilage, totalTravelledDistance) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vehicleObject.getVehicleType());
			pstmt.setString(2, vehicleObject.getVehicleName());
			pstmt.setString(3, vehicleObject.getVehicleModel());
			pstmt.setString(4, vehicleObject.getNumberPlate());
			pstmt.setInt(5, vehicleObject.getVehicleMilage());
			pstmt.setFloat(6, vehicleObject.getTotalTravelledDistance());
			byte res = (byte) pstmt.executeUpdate();
			if(res>(byte)0)
			{
				System.out.println("Vehicle added Successfully");
				addToAvailable(getVehicleIdByNumberPlate(vehicleObject.getNumberPlate()));
			}
			else
			{
				System.out.println("Something went wrong");
			}
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
	}
	public String getVehicleType(int vehicleId)
	{
		try {
			query ="Select * from vehicle where vehicleId = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, vehicleId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getString("vehicleType");
				
			}			
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return "";
		
	}
	public int getVehicleIdByNumberPlate(String numberPlate)
	{
		query = "SELECT * FROM vehicle WHERE numberPlate = ? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, numberPlate);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next())
				return rs.getInt("vehicleId");
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return 0;
	}
	public boolean searchVehicleById(int vehicleId)
	{
		query ="Select * from vehicle where vehicleId = ?";
		try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, vehicleId);
				ResultSet res = pstmt.executeQuery();
				if(res.next())
					return true;
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public boolean searchVehicleByNumberPlate(String numberPlate)
	{
		query ="Select * from vehicle where numberPlate = ?";
		try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, numberPlate);
				ResultSet res = pstmt.executeQuery();
				if(res.next())
					return true;
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public boolean searchVehicleByVehicleName(String vehicleName)
	{
		query ="Select * from vehicle where vehicleName = ?";
		try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, vehicleName);
				ResultSet res = pstmt.executeQuery();
				if(res.next())
					return true;
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public boolean removeVehicle(int vehicleId)
	{
		if(searchVehicleById(vehicleId))
		{
			removeFromAvailable(vehicleId);
			query ="DELETE FROM vehicle where vehicleId = ?";
			try {
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1, vehicleId);
					byte res = (byte) pstmt.executeUpdate();
					if(res>(byte)0)
					{
						return true;
					}
			} catch (SQLException e) {
				System.out.println("DB INSERTION ERROR\n"+e);
			}
		}
		return false;
	}
	public void displayVehiclesBy(String columnName, String value)
	{
		try {
			if(columnName.equals("vehicleId"))
			{
				if(searchVehicleById(Integer.parseInt(value)))
				{
					query ="Select * from vehicle where vehicleId = ? ";
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, value);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{
						System.out.println(rs.getInt("vehicleId")+"\t"+rs.getString("vehicleType")+"\t"+rs.getString("vehicleName")+"\t "+rs.getString("vehicleModel")+"\t"+rs.getString("numberPlate"));
					}
				}
				else
					System.out.println("Invalid vehicle Id");
			}
			else if(columnName.equals("numberPlate"))
			{
				if(searchVehicleByNumberPlate(value))
				{
					query ="Select * from vehicle where numberPlate = ?";
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, value);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{
						System.out.println(rs.getInt("vehicleId")+"\t"+rs.getString("vehicleType")+"\t"+rs.getString("vehicleName")+"\t "+rs.getString("vehicleModel")+"\t"+rs.getString("numberPlate"));
					}
				}
			}
			else if(columnName.equals("vehicleName"))
			{
				if(searchVehicleByVehicleName(value))
				{
					query ="Select * from vehicle where vehicleName = ?";
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, value);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{
						System.out.println(rs.getInt("vehicleId")+"\t"+rs.getString("vehicleType")+"\t"+rs.getString("vehicleName")+"\t "+rs.getString("vehicleModel")+"\t"+rs.getString("numberPlate"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
	}	
	
	public void displayAllVehicles()
	{
		query = "SELECT * FROM vehicle order by vehicleName asc";
		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs= pstmt.executeQuery();
			boolean b =true;
			while(rs.next())
			{
				if(b)
					b=false;
					System.out.println(rs.getInt("vehicleId")+"\t"+rs.getString("vehicleType")+"\t"+rs.getString("vehicleName")+"\t"+rs.getString("vehicleModel")+"\t"+rs.getString("numberPlate"));					
			}
			if(b)
				System.out.println("No vehicles are available");
		} catch (SQLException e) {
			System.out.println("DB FETCHING ERROR\n"+e);
		} 
	}
	

	
// available Vehicles
	public void displayAllAvailableVehicles()
	{
		query = "SELECT * FROM availableVehicle a inner join vehicle v on a.vehicleId = v.vehicleId order by vehicleName asc";
		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs= pstmt.executeQuery();
			boolean b =true;
			while(rs.next())
			{
				if(b)
					b=false;
					System.out.println(rs.getInt("vehicleId")+"\t"+rs.getString("vehicleType")+"\t"+rs.getString("vehicleName")+"\t"+rs.getString("vehicleModel")+"\t"+rs.getString("numberPlate"));					
			}
			if(b)
				System.out.println("No vehicles are available");
		} catch (SQLException e) {
			System.out.println("DB FETCHING ERROR\n"+e);
		} 
	}
	public boolean addToAvailable(int vehicleId)
	{
		if(searchVehicleById(vehicleId))
		{
			query ="INSERT INTO availableVehicle(vehicleId) VALUES(?)";
			try {
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1, vehicleId);
					byte res = (byte) pstmt.executeUpdate();
					if(res>(byte)0)
					{
						System.out.println("Vehicle set to Available");
						return true;
					}
			} catch (SQLException e) {
				System.out.println("DB INSERTION ERROR\n"+e);
			}
		}
		return false;
	}
	public boolean removeFromAvailable(int vehicleId)
	{
		if(searchVehicleById(vehicleId)&&searchAvailableVehicleById(vehicleId))
		{
			query ="DELETE FROM availableVehicle where vehicleId = ?";
			try {
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1, vehicleId);
					byte res = (byte) pstmt.executeUpdate();
					if(res>(byte)0)
						return true;
			} catch (SQLException e) {
				System.out.println("DB INSERTION ERROR\n"+e);
			}
		}
		return false;
		
	}
	public boolean searchAvailableVehicleById(int vehicleId)
	{
		query ="Select * from availableVehicle where vehicleId = ?";
		try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, vehicleId);
				ResultSet res = pstmt.executeQuery();
				if(res.next())
					return true;
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public boolean searchAvailableVehicleByName(String vehicleName)
	{
		query ="Select * from vehicle v inner join availableVehicle a on v.vehicleId= a.vehicleId where v.vehicleName = ?";
		try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, vehicleName);
				ResultSet res = pstmt.executeQuery();
				if(res.next())
					return true;
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public void rentingAvailableVehicleBy(String columnName, String value,int userId, Scanner sc)
	{
		try {
			if(columnName.equals("vehicleId"))
			{
				if(searchAvailableVehicleById(Integer.parseInt(value)))
				{
					query ="Select * from availableVehicle a inner join vehicle v on a.vehicleId = v.vehicleId where v.vehicleId = ?";
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, value);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next())
					{
						System.out.println(rs.getInt("vehicleId")+"\t"+rs.getString("vehicleType")+"\t"+rs.getString("vehicleName")+"\t"+rs.getString("vehicleModel")+"\t"+rs.getString("numberPlate"));
//						get rent
						System.out.println("Enter 1 add to cart");
						System.out.println("Enter 2 to get rent");
						byte option = sc.nextByte();
						if(option==(byte)1)
						{
							if(addToCart(userId, Integer.parseInt(value)))
							{
								System.out.println("Added to Cart");
							}
							else
								System.out.println("Some thing went wrong try again later");
						}
						else if(option==(byte)2)
						{
							if(addRent(userId, Integer.parseInt(value)))
							{
								System.out.println("Rent Confirmed");
							}
							else
								System.out.println("Some thing went wrong try again later");
						}
						
						
					}
				}
				else
					System.out.println("Currently Not Available");
			}
			else if(columnName.equals("vehicleName"))
			{
				if(searchAvailableVehicleByName(value))
				{
					query ="Select * from vehicle where vehicleName = ?";
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, value);
					ResultSet rs = pstmt.executeQuery();
					byte counter =0;
					int vehicleId=0;
					while(rs.next())
					{
						counter++;
						vehicleId = rs.getInt("vehicleId");
						System.out.println(rs.getInt("vehicleId")+"\t"+rs.getString("vehicleType")+"\t"+rs.getString("vehicleModel")+"\t"+rs.getString("numberPlate"));
					}
					if(counter>1)
					{
						System.out.println("There is more then one vehicle available in "+columnName+" model\nSo Enter vehicleId to get more specify");
					}
					else
					{
						System.out.println("Enter 1 add to cart");
						System.out.println("Enter 2 to get rent");
						byte option = sc.nextByte();
						if(option==(byte)1)
						{
							if(addToCart(userId, vehicleId))
							{
								System.out.println("Added to Cart");
							}
							else
								System.out.println("Some thing went wrong try again later");
						}
						else if(option==(byte)2)
						{
							if(addRent(userId, vehicleId))
							{
								System.out.println("Rent Confirmed");
							}
							else
								System.out.println("Some thing went wrong try again later");
							
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
	}
//		Cart
	public boolean addToCart( int userId, int vehicleId )
	{
		query = "INSERT INTO cart(userId, vehicleId) VALUES( ?, ?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, vehicleId);
			byte res = (byte) pstmt.executeUpdate();
			if(res>(byte)0)
				return true;
		} catch (SQLException e) {
			System.out.println("DB INSERT ERROR\n"+e);
		}
		return false;
	}
	public boolean searchFromCart(int userId, int vehicleId)
	{
		query ="Select * from cart where vehicleId = ? and userId=?";
		try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, vehicleId);
				pstmt.setInt(2, userId);
				ResultSet res = pstmt.executeQuery();
				if(res.next())
					return true;
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public boolean removeFromCart(int userId, int vehicleId )
	{
		if(searchFromCart( userId, vehicleId))
		{
			query ="DELETE FROM cart where vehicleId = ? and userId=?";
			try {
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1, vehicleId);
					pstmt.setInt(2, userId);
					byte res = (byte) pstmt.executeUpdate();
					if(res>(byte)0)
						return true;
			} catch (SQLException e) {
				System.out.println("DB INSERTION ERROR\n"+e);
			}
		}
		return false;
	}
	public void displayCart(int userId)
	{
		try {
			query ="Select * from cart c inner join vehicle v on v.vehicleId=c.vehicleId where c.userId = ?";
				pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			int n=1;
			while(rs.next())
			{
				boolean b = searchAvailableVehicleById(rs.getInt("vehicleId"));
				System.out.println((n++)+"\t"+rs.getInt("userId")+"\t"+rs.getInt("vehicleId")+"\t"+rs.getString("vehicleType")+"\t"+rs.getString("vehicleModel")+"\t"+rs.getString("numberPlate")+"\t"+(b?"Available Currently":"Not available Currently"));
			}
		} catch (SQLException e) {
			System.out.println("Error in Displaying cart");
		}
	}
	
//	Deposit
	public boolean addDeposit(int userId)
	{
		query = "INSERT INTO deposit(userId, securityDeposit, cautionDeposit) VALUES(?, ?, ?)";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
			byte res = (byte) pstmt.executeUpdate();
			if(res>(byte)0)
				return true;
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public Deposit getDepositsBalance(int userId)
	{
		try {
			query ="Select * from deposit where userId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				int securityDeposit = rs.getInt("securityDeposit");
				int cautionDeposit = rs.getInt("cautionDeposit");
				return new Deposit(userId, securityDeposit, cautionDeposit);
			}
		} catch (SQLException e) {
			System.out.println("Error in Displaying cart");
		}
		return new Deposit();
	}
	public boolean updateSecurityDepositForBike(int userId)
	{
		query = "UPDATE deposit set securityDeposit = 3000 where userId = ? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			byte res = (byte) pstmt.executeUpdate();
			if(res>(byte)0)
			{
				return true;
			}
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public boolean updateSecurityDepositForCar(int userId)
	{
		query = "UPDATE deposit set securityDeposit = 10000 where userId = ? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			byte res = (byte) pstmt.executeUpdate();
			if(res>(byte)0)
			{
				return true;
			}
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public boolean updateCautionDeposit(int userId)
	{
		query = "UPDATE deposit set securityDeposit = 30000 where userId = ? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			byte res = (byte) pstmt.executeUpdate();
			if(res>(byte)0)
			{
				return true;
			}
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	
	
	
//	Rent
	public boolean addRent(int userId, int vehicleId)
	{
		Deposit depositObject = getDepositsBalance(userId);
		if(depositObject.getCautionDeposit()<30000)
		{
			System.out.println("Caution Deposit is less then the minimum requirement");
			System.out.println("minimum Caution Deposit is 30000");
			System.out.println("cureent balance is\t:\t"+depositObject.getCautionDeposit());
			return false;
		}
		String vehicleType = getVehicleType(vehicleId);
		if(vehicleType.equals("bike")) {
			if(depositObject.getSecurityDeposit()<3000)
			{
				System.out.println("Security Deposit is less then the minimum requirement to rent a bike");
				System.out.println("minimum Security Deposit for bike is 3000");
				System.out.println("current balance is\t:\t"+depositObject.getSecurityDeposit());
				return false;
			}
			else if(!isUserCanRentBike(userId))
			{
				System.out.println("Only a user can rent One bike at a time");
				return false;
			}
		}
		if(vehicleType.equals("car")){
			if(depositObject.getSecurityDeposit()<10000)
			{
				System.out.println("Security Deposit is less then the minimum requirement to rent a car");
				System.out.println("minimum Security Deposit for car is 10000");
				System.out.println("current balance is\t:\t"+depositObject.getSecurityDeposit());
				return false;
			}
			else if(!isUserCanRentCar(userId)) {
				System.out.println("Only a user can rent One car at a time");
				return false;
			}
		}
		query = "INSERT INTO rent(userId, vehicleId, distanceTravelledPerDistance, damageLevel, isReturned, tenureCount) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, vehicleId);
			pstmt.setInt(3, 0);
			pstmt.setString(4, "");
			pstmt.setBoolean(5, false);
			pstmt.setInt(6, 1);
			byte res = (byte) pstmt.executeUpdate();
			if(res>(byte)0)
			{
				markOnCurrentlyRentingVehicle(vehicleId, userId);
				removeFromAvailable(vehicleId);
				return true;
			}
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	public boolean markOnCurrentlyRentingVehicle(int vehicleId, int userId)
	{
		
		try {
			String vehicleType = getVehicleType(vehicleId);
			if(vehicleType.equals("car"))
			{
				query = "INSERT INTO carRented(userId) VALUES(?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, userId);
				byte res = (byte) pstmt.executeUpdate();
				if(res>(byte)0)
				{
					return true;
				}
			}
			else if(vehicleType.equals("bike"))
			{
				query = "INSERT INTO bikeRented(userId) VALUES(?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, userId);
				byte res = (byte) pstmt.executeUpdate();
				if(res>(byte)0)
				{
					return true;
				}
			}		
		} catch (SQLException e) {
			System.out.println("DB INSERTION ERROR\n"+e);
		}
		return false;
	}
	
//	already Rented
	public boolean isUserCanRentBike(int userId) {
		try {
			query ="Select * from bikeRented where userId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error in Displaying cart");
		}
		return true;
	}
	public boolean isUserCanRentCar(int userId) {
		try {
			query ="Select * from carRented where userId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error in isUserRented Car\n"+e);
		}
		return true;
	}
	
}