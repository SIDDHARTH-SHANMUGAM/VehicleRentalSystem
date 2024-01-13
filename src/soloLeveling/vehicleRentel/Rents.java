package soloLeveling.vehicleRentel;

public class Rents {
	protected int rentId;
	protected String rentDate;
	protected String rentTime;
	protected int vehicleId;
	protected int userId;
	protected boolean isReturned;
	protected boolean isDamage;
	protected String damageLabel;
	protected int fineAmount;
	protected int totalRentCharge;
	
	//Constructor
	public Rents(int rentId, int vehicleId, int userId)
	{
		this.rentId = rentId;
		this.rentDate = "todayDate";
		this.rentTime = "currentTime";
		this.vehicleId = vehicleId;
		this.userId = userId;
	}
	
	//setter
	public void setIsReturned(boolean isReturned)
	{
		this.isReturned = isReturned;
	}
	public void setIsDamage(boolean isDamage)
	{
		this.isDamage = isDamage;
	}
	public void setDamageLabel(String damageLabel)
	{
		this.damageLabel = damageLabel;
	}
	public void setFineAmount(int fineAmount)
	{
		this.fineAmount= fineAmount;
	}
	public void setTotalRentCharge(int totalRentCharge)
	{
		this.totalRentCharge = totalRentCharge;
	}
	
	//getter
	public int getRentId() 
	{
		return this.rentId;
	}
	public String getRentDate() 
	{
		return this.rentDate;
	}
	public String getRentTime() 
	{
		return this.rentTime;
	}
	public boolean getIsReturned() 
	{
		return this.isReturned;
	}
	public boolean getIsDamage() 
	{
		return this.isDamage;
	}
	public String getDamageLabel() 
	{
		return this.damageLabel;
	}
	public int getFineAmount() 
	{
		return this.fineAmount;
	}
	public int getTotalRentCharge()
	{
		return this.totalRentCharge;
	}
	
	@Override
	public String toString()
	{
		return "{\n\tVehicle ID			:	"+this.vehicleId+
				"\n\tNumber plate		:	"+this.userId+
				"\n\tType				:	"+this.rentDate+
				"\n\tModel				:	"+this.rentId+
				"\n\tMilage				:	"+this.rentTime+
				"\n\tRegistration Date	:	"+this.fineAmount+
				"\n\tTotally Travelled	:	"+this.totalRentCharge+
				"\n\tIsAvailable Now	:	"+this.damageLabel+
				(this.isDamage?"DAMAGED":"NOTDAMAGED")+
				"\n\tTotally Travelled	:	"+this.isDamage+
				"\n\tIsAvailable Now	:	"+this.isReturned+
				"\n}";
	}
	
	
}