package core.statCover;

import java.util.Date;

public class availability {

	private int availabilityId;
	private int staffId;
	private String staffFname;
	private String staffLname;
	private String staffSpecialty;
	private String staffAffiliation;
	private Date availableDate;
	private String preferredShift;
	private String email;
	private String timeFrom;
	private String timeTo;
	private String requestGUID;
	private int dmid;
	
	//constructor for availability
	public availability(int A_availId, int A_staffId, String A_staffFname, String A_staffLname,
			String A_specialty, String A_affiliation, Date A_availDate, String A_shift, String A_email)
	{
		this.availabilityId = A_availId;
		this.staffId = A_staffId;
		this.staffFname = A_staffFname;
		this.staffLname = A_staffLname;
		this.staffSpecialty = A_specialty;
		this.availableDate = A_availDate;
		this.preferredShift = A_shift;
		this.email = A_email;
	}

	public availability() {
		// TODO Auto-generated constructor stub
	}

	//setters and getters for availability parameters
	public int getAvailabilityId() {
		return availabilityId;
	}

	public void setAvailabilityId(int availabilityId) {
		this.availabilityId = availabilityId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffFname() {
		return staffFname;
	}

	public void setStaffFname(String staffFname) {
		this.staffFname = staffFname;
	}

	public String getStaffLname() {
		return staffLname;
	}

	public void setStaffLname(String staffLname) {
		this.staffLname = staffLname;
	}

	public String getStaffSpecialty() {
		return staffSpecialty;
	}

	public void setStaffSpecialty(String staffSpecialty) {
		this.staffSpecialty = staffSpecialty;
	}

	public String getStaffAffiliation() {
		return staffAffiliation;
	}

	public void setStaffAffiliation(String staffAffiliation) {
		this.staffAffiliation = staffAffiliation;
	}

	public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	public String getPreferredShift() {
		return preferredShift;
	}

	public void setPreferredShift(String preferredShift) {
		this.preferredShift = preferredShift;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String _email) {
		this.email = _email;
	}
	
	public String getTimeFrom() {
		return timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}
	
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	
	public String getRequestGUID() {
		return requestGUID;
	}

	public void setRequestGUID(String requestGUID) {
		this.requestGUID = requestGUID;
	}
	
	
	public int getdmid() {
		return dmid;
	}

	public void setdmid(int dmid) {
		this.dmid = dmid;
	}

	@Override
	public String toString() {
		return "availability [availabilityId=" + availabilityId + ", staffId=" + staffId + ", staffFname=" + staffFname
				+ ", staffLname=" + staffLname + ", staffSpecialty=" + staffSpecialty + ", staffAffiliation="
				+ staffAffiliation + ", availableDate=" + availableDate + ", preferredShift=" + preferredShift + "]";
	}
	
}
