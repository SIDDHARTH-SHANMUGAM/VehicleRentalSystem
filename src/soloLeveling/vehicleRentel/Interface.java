package soloLeveling.vehicleRentel;

import java.util.Scanner;

public class Interface {

	public static void main(String[] args) {
		
		 //Objects
		Scanner sc = new Scanner(System.in);
		User userObject ;
		DBConnection con = new DBConnection();
		con.getVRSDBconnection();
		
		
		System.out.println("---------	Vehicle Rental System	---------");
		
		userObject = Login.getLogging(sc, con);
		if(con.isAdmin(userObject.getUserId()))
		{
			AdminInterface.adminInterface(sc, con);
		}
		else
		{
			ClientUI.clientInterface(sc, userObject, con);			
		}
	}
}
