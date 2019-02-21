package core.statCover;

public class admin {

	private int adminId;
    private String adminFname;
    private String adminLname;
    private String adminEmail;
    private String adminContactNum;
    private String adminPassword;
    
  //constructor for Admin
    public admin(int A_adminId, String A_Fname, String A_Lname, String A_email, String A_contactNum, String A_password)
    {
        this.adminId = A_adminId;
        this.adminFname = A_Fname;
        this.adminLname = A_Lname;
        this.adminEmail = A_email;
        this.adminContactNum = A_contactNum;
        this.adminPassword = A_password;    
    }

  public admin() {
		// TODO Auto-generated constructor stub
	}

	//Setter and Getters for Admin parameters
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminFname() {
		return adminFname;
	}

	public void setAdminFname(String adminFname) {
		this.adminFname = adminFname;
	}

	public String getAdminLname() {
		return adminLname;
	}

	public void setAdminLname(String adminLname) {
		this.adminLname = adminLname;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminContactNum() {
		return adminContactNum;
	}

	public void setAdminContactNum(String adminContactNum) {
		this.adminContactNum = adminContactNum;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "admin [adminId=" + adminId + ", adminFname=" + adminFname + ", adminLname=" + adminLname
				+ ", adminEmail=" + adminEmail + ", adminContactNum=" + adminContactNum + ", adminPassword="
				+ adminPassword + "]";
	}
	
}
