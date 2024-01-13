package soloLeveling.vehicleRentel;

public class User {
	
	private int userId;
	private String userEmail;
	private String userName;
	private String userAddress;
	private String userMobile;
	private String userPassword;
	
	public User()
	{
		this.userId= 0;
	}
	public User(String userEmail, String userName, String userAddress, String userMobile, String userPassword)
	{
		this.userName= userName;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userMobile = userMobile;
		this.userPassword = userPassword;
	}
	public User(int userId, String userEmail, String userName, String userAddress, String userMobile, String userPassword)
	{
		this.userId = userId;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userMobile = userMobile;
		this.userPassword = userPassword;
	}
	
	//setters
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public void setUserAddress(String userAddress)
	{
		this.userAddress = userAddress;
	}
	
	//getters
	public int getUserId()
	{
		return this.userId;
	}
	public String getUserEmail()
	{
		return this.userEmail;
	}
	public String getUserName()
	{
		return this.userName;
	}
	public String getUserAddress()
	{
		return this.userAddress;
	}
	public String getUserMobile()
	{
		return this.userMobile;
	}
	public String getUserPassword()
	{
		return this.userPassword;
	}
	
	@Override
	public String toString()
	{
		return "{\n\tUser ID		:	"+this.userId+
				"\n\tUser Email	:	"+this.userEmail+
				"\n\tUser Name	:	"+this.userName+
				"\n\tUser Address	:	"+this.userAddress+
				"\n\tUser Mobile	:	"+this.userMobile+
				"\n}";
	}
	
}