package soloLeveling.vehicleRentel;

public class Vehicle {
	
	static int NoOfVehicle;
	
	private int vehicleId;
	private int vehicleMilage;
	private float totalTravelledDistance;
	private String numberPlate;
	private String vehicleType;
	private String vehicleName;
	private String vehicleModel;
	
	public Vehicle(int vehicleMilage,String numberPlate, String vehicleType,String vehicleName, String model)
	{
		this.vehicleMilage = vehicleMilage;
		this.numberPlate = numberPlate;
		this.vehicleType = vehicleType;
		this.vehicleName = vehicleName;
		this.vehicleModel = model;
	}
	public Vehicle(int vehicleId, int vehicleMilage,String numberPlate, String vehicleType,String vehicleName, String model)
	{
		this.vehicleMilage = vehicleMilage;
		this.numberPlate = numberPlate;
		this.vehicleId = vehicleId;
		this.vehicleType = vehicleType;
		this.vehicleName = vehicleName;
		this.vehicleModel = model;
	}
	
	//setter
	public void setMilage(int vehicleMilage)
	{
		this.vehicleMilage= vehicleMilage;
	}
	public void setTotalTravelledDistance(float totalTravelledDistance)
	{
		this.totalTravelledDistance= totalTravelledDistance;
	}
	
	//getter
	public int getVehicleId()
	{
		return this.vehicleId;
	}
	public float getTotalTravelledDistance()
	{
		return this.totalTravelledDistance;
	}
	public String getNumberPlate()
	{
		return this.numberPlate;
	}
	public String getVehicleType()
	{
		return this.vehicleType;
	}
	public int getVehicleMilage()
	{
		return this.vehicleMilage;
	}
	public String getVehicleName()
	{
		return this.vehicleName;
	}
	public String getVehicleModel()
	{
		return this.vehicleModel;
	}
	
	@Override
	public String toString()
	{
		return "{\n\tVehicle ID			:	"+this.vehicleId+
				"\n\tNumber plate		:	"+this.numberPlate+
				"\n\tType				:	"+this.vehicleType+
				"\n\tName				:	"+this.vehicleName+
				"\n\tModel				:	"+this.vehicleModel+
				"\n\tMilage				:	"+this.vehicleMilage+
				"\n\tTotally Travelled	:	"+this.totalTravelledDistance+
				"\n}";
	}
}