package soloLeveling.vehicleRentel;

import java.util.Scanner;

public class AdminInterface {
	public static void adminInterface(Scanner sc,DBConnection con)
	{
		while(true)
		{
			System.out.println("------------------------ Admin Interface-------------------");
			System.out.println("Enter 1 to Update Vehicle Inventory");
			System.out.println("Enter 2 to Update Available Vehicles");
			System.out.println("Enter 3 to Change User Security Deposit");
			System.out.println("Enter 4 to log Out");
			
			
			byte opt = sc.nextByte();
			switch (opt)
			{
			case (byte)1:
				System.out.println("-----------------------------Update Vehicle Inventory-----------------------");
				System.out.println("Enter 1 to Add Vehicle");
				System.out.println("Enter 2 to Remove Vehicle");
				System.out.println("Enter 3 to Search Vehicle");
				System.out.println("Enter 4 to See Vehicle List");
				byte opt2 = sc.nextByte();
				switch(opt2)
				{
				case (byte)1:
					sc.nextLine();
					System.out.println("------------Add Vehicle------------");
					System.out.println("Enter vehicle Type (car OR bike)");
					String vehicleType = sc.nextLine();
					System.out.println("Enter vehicle Name");
					String vehicleName = sc.nextLine();
					System.out.println("Enter vehicle Model");
					String vehicleModel = sc.nextLine();
					System.out.println("Enter vehicle Number Plate");
					String numberPlate= sc.next();
					System.out.println("Enter vehicle milage");
					int vehicleMilage= sc.nextInt();
					con.addVehicle(new Vehicle(vehicleMilage, numberPlate, vehicleType, vehicleName, vehicleModel));						
					break;
				case (byte)2:
					System.out.println("Enter vehicle Id to Remove vehicle");
					int vehicleId = sc.nextInt();
					if(con.searchVehicleById(vehicleId))
						con.removeVehicle(vehicleId);
					else
						System.out.println("Invalid vehilce Id");
					break;
				case (byte)3:
					System.out.println("Enter 1 to search by veicle Id");
					System.out.println("Enter 2 to search by Number plate");
					System.out.println("Enter 3 to search by vehicle Name");
					opt =sc.nextByte();
					if(opt==(byte)1)
					{
						System.out.println("Enter vehicle ID");
						vehicleId = sc.nextInt();
						con.displayVehiclesBy("vehicleId", vehicleId+"");
					}
					else if(opt == (byte) 2)
					{

						System.out.println("Enter number plate");
						numberPlate = sc.next();
						con.displayVehiclesBy("numberPlate", numberPlate);
					}
					else if(opt == (byte)3)
					{
						sc.nextLine();
						System.out.println("Enter vehicle Name");
						vehicleName=sc.nextLine();
						con.displayVehiclesBy("vehicleName", vehicleName);
					}
					break;
				case (byte)4:
					System.out.println("Enter 1 to display all vehicles");
					System.out.println("Enter 2 to display all Availabale vehicles");
					opt2 = sc.nextByte();
					if(opt2==(byte)1)
					{
						con.displayAllVehicles();
					}
					else if(opt2==(byte)2)
						con.displayAllAvailableVehicles();
					break;
				}
				break;
			case (byte)2:
			System.out.println("------------- Available vehicles --------------");
			con.displayAllAvailableVehicles();
			System.out.println("Enter 1 to add new to availables");
			System.out.println("Enter 2 to delete from availables");
			byte opt3 = sc.nextByte();
			if(opt3==(byte)1)
			{
				System.out.println("Enter new Vehicle Id");
				int vehicleId = sc.nextInt();
				if(con.searchVehicleById(vehicleId))
					con.addToAvailable(vehicleId);
				else
					System.out.println("Invalid Vehicle Id");
			}
			else if(opt3==(byte)2)
			{
				System.out.println("Enter availableVehicle Id");
				int vehicleId = sc.nextInt();
				if(con.searchAvailableVehicleById(vehicleId))
					con.removeFromAvailable(vehicleId);
				else
					System.out.println("Invalid vehicle Id");
			}	
			break;
			
			case (byte)5:
				System.out.println("security Deposit");
				break;
				
			case (byte)6:
				return;
			}
			
		}
	}
	

}
