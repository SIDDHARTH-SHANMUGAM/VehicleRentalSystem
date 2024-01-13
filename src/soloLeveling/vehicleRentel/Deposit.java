package soloLeveling.vehicleRentel;

public class Deposit {
	private int userId;
	private int securityDeposit;
	private int cautionDeposit;
	
	public Deposit()
	{
		this.userId = 0;
		this.securityDeposit = 0;
		this.cautionDeposit = 0;
	}
	
	public Deposit(int userId,int securityDeposit, int cautionDeposit)
	{
		this.userId = userId;
		this.securityDeposit = securityDeposit;
		this.cautionDeposit = cautionDeposit;
	}
	
	public void setSecurityDeposit(int securityDeposit)
	{
		this.securityDeposit = securityDeposit;
	}
	public void setCautionDeposit(int cautionDeposit)
	{
		this.cautionDeposit= cautionDeposit;
	}
	
	//getter
	public int getSecurityDeposit()
	{
		return this.securityDeposit;
	}
	public int getCautionDeposit()
	{
		return this.cautionDeposit;
	}
	public int getUserID()
	{
		return this.userId;
	}
	
	@Override
	public String toString()
	{
		return "{\nUserId\t:\t"+this.userId+"\nSecurityDeposit\t:\t"+this.securityDeposit+"\nCautionDeposit\t:\t"+this.cautionDeposit;
	}
}
