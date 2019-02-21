package core.statCover;

public class registration {

	private int registrationId;
	private String FirstName;
	private String LastName;
	private String gender;
	private String  email;
	private String contactNum;
	private String address;
	private String specialty;
	private String position;
	private int yearsOfExperience;
	
	//constructor for registration
	public registration(int Reg_regId, String Reg_Fname, String Reg_Lname, String Reg_gender,
			String Reg_email, String Reg_contactNum, String Reg_add, String Reg_specialty,
			String Reg_position, int Reg_yearsOfEx)
	{
		this.registrationId = Reg_regId;
		this.FirstName = Reg_Fname;
		this.LastName = Reg_Lname;
		this.gender = Reg_gender;
		this.email = Reg_email;
		this.contactNum = Reg_contactNum;
		this.address = Reg_add;
		this.specialty = Reg_specialty;
		this.position = Reg_position;
		this.yearsOfExperience = Reg_yearsOfEx;
	}

	public registration() {
		// TODO Auto-generated constructor stub
	}

	//setters and getters for registration parameters
	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	@Override
	public String toString() {
		return "registration [registrationId=" + registrationId + ", FirstName=" + FirstName + ", LastName=" + LastName
				+ ", gender=" + gender + ", email=" + email + ", contactNum=" + contactNum
				+ ", address=" + address + ", specialty=" + specialty + ", position=" + position
				+ ", yearsOfExperience=" + yearsOfExperience + "]";
	}
	
}
