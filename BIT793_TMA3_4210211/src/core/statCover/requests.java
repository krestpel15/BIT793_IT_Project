package core.statCover;

import java.util.Date;

public class requests {
	
	private int requestId;
	private String position;
	private String specialtyRequirement;
	private Date shiftDate;
	private String shift;
	private String timeFrom;
	private String timeTo;
	private int quantity;
	private int dmId;
	private String dmLname;
	private Date requestDate;
	private String status;
	
	//constructor for requests
	public requests(int R_requestId, String R_position, String R_specialtyReq, Date R_shiftDate, String R_shift,
			String R_timeFrom, String R_timeTo, int R_quantity, int R_dmId, String R_dmLname,
			Date R_requestDate, String R_status)
	{
		this.requestId = R_requestId;
		this.position = R_position;
		this.specialtyRequirement = R_specialtyReq;
		this.shiftDate = R_shiftDate;
		this.shift = R_shift;
		this.timeFrom = R_timeFrom;
		this.timeTo = R_timeTo;
		this.quantity = R_quantity;
		this.dmId = R_dmId;
		this.dmLname = R_dmLname;
		this.requestDate = R_requestDate;
		this.status = R_status;
	}

	public requests() {
		// TODO Auto-generated constructor stub
	}

	//setters and getters for Request parameters
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSpecialtyRequirement() {
		return specialtyRequirement;
	}

	public void setSpecialtyRequirement(String specialtyRequirement) {
		this.specialtyRequirement = specialtyRequirement;
	}

	public Date getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDmId() {
		return dmId;
	}

	public void setDmId(int dmId) {
		this.dmId = dmId;
	}

	public String getDmLname() {
		return dmLname;
	}

	public void setDmLname(String dmLname) {
		this.dmLname = dmLname;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "requests [requestId=" + requestId + ", position=" + position + ", specialtyRequirement=" + specialtyRequirement + ", shiftDate="
				+ shiftDate + ", shift=" + shift + ", timeFrom=" + timeFrom + ", timeTo=" + timeTo + ", quantity="
				+ quantity + ", dmId=" + dmId + ", dmLname=" + dmLname + ", requestDate=" + requestDate + ", requestDate=" + requestDate +"]";
	}
	

}
