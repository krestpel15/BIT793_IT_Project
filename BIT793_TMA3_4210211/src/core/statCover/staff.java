package core.statCover;

import java.util.Date;

public class staff {
	
	private int staffId;
	private String staffFname;
	private String staffLname;
	private String staffEmail;
	private String staffContactNum;
	private String staffAddress;
	private String staffSpecialty;
	private String staffPosition;
	private String staffAffiliation;
	private String staffLicenseNum;
	private Date staffLicenseExpiry;
	private String staffPassword;
	
	//constructor for staff
	public staff(int S_staffId, String S_Fname, String S_Lname, String S_email, String S_contactNum,
			String S_address, String S_specialty, String S_position, String S_affiliation, String S_licenseNum,
			Date S_licenseExpiry, String S_password)
	{
		this.staffId = S_staffId;
		this.staffFname = S_Fname;
		this.staffLname = S_Lname;
		this.staffEmail = S_email;
		this.staffContactNum = S_contactNum;
		this.staffAddress = S_address;
		this.staffSpecialty = S_specialty;
		this.staffPosition = S_position;
		this.staffAffiliation = S_affiliation;
		this.staffLicenseNum = S_licenseNum;
		this.staffLicenseExpiry = S_licenseExpiry;
		this.staffPassword = S_password;
	}

	public staff() {
		// TODO Auto-generated constructor stub
	}

	//setters and getters for Staff parameters
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

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public String getStaffContactNum() {
		return staffContactNum;
	}

	public void setStaffContactNum(String staffContactNum) {
		this.staffContactNum = staffContactNum;
	}

	public String getStaffAddress() {
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	public String getStaffSpecialty() {
		return staffSpecialty;
	}

	public void setStaffSpecialty(String staffSpecialty) {
		this.staffSpecialty = staffSpecialty;
	}

	public String getStaffPosition() {
		return staffPosition;
	}

	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}

	public String getStaffAffiliation() {
		return staffAffiliation;
	}

	public void setStaffAffiliation(String staffAffiliation) {
		this.staffAffiliation = staffAffiliation;
	}

	public String getStaffLicenseNum() {
		return staffLicenseNum;
	}

	public void setStaffLicenseNum(String staffLicenseNum) {
		this.staffLicenseNum = staffLicenseNum;
	}

	public Date getStaffLicenseExpiry() {
		return staffLicenseExpiry;
	}

	public void setStaffLicenseExpiry(Date staffLicenseExpiry) {
		this.staffLicenseExpiry = staffLicenseExpiry;
	}

	public String getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}

	@Override
	public String toString() {
		return "staff [staffId=" + staffId + ", staffFname=" + staffFname + ", staffLname=" + staffLname
				+ ", staffEmail=" + staffEmail + ", staffContactNum=" + staffContactNum + ", staffAddress="
				+ staffAddress + ", staffSpecialty=" + staffSpecialty + ", staffPosition=" + staffPosition
				+ ", staffAffiliation=" + staffAffiliation + ", staffLicenseNum=" + staffLicenseNum
				+ ", staffLicenseExpiry=" + staffLicenseExpiry + ", staffPassword=" + staffPassword + "]";
	}

}
