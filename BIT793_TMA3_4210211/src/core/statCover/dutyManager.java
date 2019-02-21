package core.statCover;

public class dutyManager {
	
	private int dmId;
	private String dmFname;
	private String dmLname;
	private String dmAffiliation;
	private String dmEmail;
	private String dmContactNum;
	private String dmPassword;
	
	//constructor for Duty Manager
    public dutyManager(int D_dmId, String D_Fname, String D_Lname, String D_affiliation, 
    		String D_email, String D_contactNum, String D_password)
    {
        this.dmId = D_dmId;
        this.dmFname = D_Fname;
        this.dmLname = D_Lname;
        this.dmAffiliation = D_affiliation;
        this.dmEmail = D_email;
        this.dmContactNum = D_contactNum;
        this.dmPassword = D_password;
        
    }

    public dutyManager() {
		// TODO Auto-generated constructor stub
	}

	//setters and getters for duty manager parameters
	public int getDmId() {
		return dmId;
	}

	public void setDmId(int dmId) {
		this.dmId = dmId;
	}

	public String getDmFname() {
		return dmFname;
	}

	public void setDmFname(String dmFname) {
		this.dmFname = dmFname;
	}

	public String getDmLname() {
		return dmLname;
	}

	public void setDmLname(String dmLname) {
		this.dmLname = dmLname;
	}

	public String getDmAffiliation() {
		return dmAffiliation;
	}

	public void setDmAffiliation(String dmAffiliation) {
		this.dmAffiliation = dmAffiliation;
	}

	public String getDmEmail() {
		return dmEmail;
	}

	public void setDmEmail(String dmEmail) {
		this.dmEmail = dmEmail;
	}

	public String getDmContactNum() {
		return dmContactNum;
	}

	public void setDmContactNum(String dmContactNum) {
		this.dmContactNum = dmContactNum;
	}

	public String getDmPassword() {
		return dmPassword;
	}

	public void setDmPassword(String dmPassword) {
		this.dmPassword = dmPassword;
	}

	@Override
	public String toString() {
		return "dutyManager [dmId=" + dmId + ", dmFname=" + dmFname + ", dmLname=" + dmLname + ", dmAffiliation="
				+ dmAffiliation + ", dmEmail=" + dmEmail + ", dmContactNum=" + dmContactNum + ", dmPassword="
				+ dmPassword + "]";
	}

}
