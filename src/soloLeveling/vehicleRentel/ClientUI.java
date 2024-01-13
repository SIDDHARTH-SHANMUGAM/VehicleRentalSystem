package soloLeveling.vehicleRentel;

import java.util.Scanner;

public class ClientUI {
	public static void clientInterface(Scanner sc,User userObject, DBConnection con)
	{
		while(true)
		{

			System.out.println("----------------------Thaijobu-Vehicle-Rentals--------------------");
			System.out.println("Enter 1 to See available vehicles for rent");
			System.out.println("Enter 2 to Search a vehicle for rent");
			System.out.println("Enter 3 to CheckOut Cart and get Rent");
			System.out.println("Enter 4 to extent Tenure or return vehicle");
			System.out.println("Enter 5 to see Deposit");
			System.out.println("Enter 6 to log Out");
			
			byte opt = sc.nextByte();
			switch (opt)
			{
			case (byte)1:
					con.displayAllAvailableVehicles();
					break;
			case (byte)2:
				System.out.println("Enter 1 to search vehicle by vehicle ID");
				System.out.println("Enter 2 to search vehicle by vehicle Name");
				opt = sc.nextByte();
				if(opt==1)
				{
					System.out.println("Enter vehicle Id");
					String vehicleId=sc.next();
					con.rentingAvailableVehicleBy("vehicleId", vehicleId, userObject.getUserId(), sc);					
				}
				else
				{
					sc.nextLine();
					System.out.println("Enter vehicle Name");
					String vehicleName=sc.nextLine();
					con.rentingAvailableVehicleBy("vehicleName", vehicleName, userObject.getUserId(), sc);
				}
				break;					
			case (byte)3:
				con.displayCart(userObject.getUserId());
				System.out.println("Enter 1 to get Rent");
				System.out.println("Enter 2 to remove from cart");
				opt =sc.nextByte();
				if(opt ==1)
				{
					System.out.println("Enter vehicle id to get Rent");
					int vehicleId = sc.nextInt();
					if(con.searchAvailableVehicleById(vehicleId))
					{
						if(con.addRent(userObject.getUserId(), vehicleId))
						{
							System.out.println("Rented successfully");
						}
					}
					System.out.println("Vehicle is not available Currently");
					
				}
				break;
			case (byte)4:
				System.out.println("");
				return;
				
			case (byte)5:
				System.out.println("Current Deposit Balance");
				Deposit depositObject =con.getDepositsBalance(userObject.getUserId());
				System.out.println(depositObject);
				System.out.println("Enter 1 to Pay Security deposit for bike");
				System.out.println("Enter 2 to Pay Security deposit for car");
				System.out.println("Enter 3 to Pay Caution deposit");
				opt =sc.nextByte();
				if(opt==1)
				{
					if(!(depositObject.getSecurityDeposit()<3000))
					{
						System.out.println("deposit balance is enough to rent bike");
					}
					else
					{
						if(con.updateSecurityDepositForBike(userObject.getUserId()))
							System.out.println("Security Deposit is updated");
						else
							System.out.println("Some thing went wrong");
					}
				}
				if(opt==2)
				{
					if(!(depositObject.getSecurityDeposit()<10000))
					{
						System.out.println("deposit balance is enough to rent car");
					}
					else
					{
						if(con.updateSecurityDepositForCar(userObject.getUserId()))
							System.out.println("Security Deposit is updated");
						else
							System.out.println("Some thing went wrong");
					}
				}
				if(opt==3)
				{
					if(!(depositObject.getCautionDeposit()<3000))
					{
						System.out.println("deposit balance is enough to rent vehicle");
					}
					else
					{
						if(con.updateCautionDeposit(userObject.getUserId()))
							System.out.println("Security Deposit is updated");
						else
							System.out.println("Some thing went wrong");
					}
				}
				break;
			}
			
		}
	}
}
