package dataAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import utils.GlobalUtils;
import core.statCover.*;

public class MySQLAccessor {

	public static String driverName = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/statcover_request_booking?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String un = "root";
	public static String pw = "root";
	Connection con;

	// Access MySQL database
	public MySQLAccessor() {
		try {
			Class.forName(driverName).newInstance();
			con = DriverManager.getConnection(url, un, pw);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// fetch data of a duty manager from statcover_dm table with a specified dm id
		public dutyManager getDutyManager(int dmid) {
			dutyManager dm = new dutyManager();
			try {
				String query1 = "SELECT * FROM statcover_dm where dm_id="+String.valueOf(dmid);
				Statement ps = con.createStatement();
				ResultSet rs = ps.executeQuery(query1);
				if (rs.first()) {
					dm.setDmId(rs.getInt("dm_id"));
					dm.setDmFname(rs.getString("dm_firstname"));
					dm.setDmLname(rs.getString("dm_lastname"));
					dm.setDmAffiliation(rs.getString("dm_affiliation"));
					dm.setDmEmail(rs.getString("dm_email"));
					dm.setDmContactNum(rs.getString("dm_contactNum"));
					dm.setDmPassword(rs.getString("dm_password"));
				}
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
			return dm;
		}
		
		// fetch data of a staff from statcover_staff table with a specified staff id
		public staff getStaff(int staffID) {
			staff _staff = new staff();
			try {
				String query1 = "SELECT * FROM statcover_staff where staff_Id="+String.valueOf(staffID);
				Statement ps = con.createStatement();
				ResultSet rs = ps.executeQuery(query1);
				if (rs.first()) {
					_staff.setStaffId(rs.getInt("staff_Id"));
					_staff.setStaffFname(rs.getString("staff_firstname"));
					_staff.setStaffLname(rs.getString("staff_lastname"));
					_staff.setStaffEmail(rs.getString("staff_email"));
					_staff.setStaffContactNum(rs.getString("staff_contactNum"));
					_staff.setStaffAddress(rs.getString("staff_address"));
					_staff.setStaffSpecialty(rs.getString("staff_specialty"));
					_staff.setStaffPosition(rs.getString("staff_position"));
					_staff.setStaffAffiliation(rs.getString("staff_affiliation"));
					_staff.setStaffLicenseNum(rs.getString("staff_licenseNum"));
					_staff.setStaffLicenseExpiry(rs.getDate("staff_licenseExpiry"));
					_staff.setStaffPassword(rs.getString("staff_password"));
				}
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
			return _staff;
		}
		
	// fetch data from mysql duty manager table
	public ArrayList<admin> getAdmin() {
		ArrayList<admin> adminList = new ArrayList<admin>();
		try {
			String query1 = "SELECT * FROM statcover_admin";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query1);
			while (rs.next()) {
				admin admin = new admin();
				admin.setAdminId(rs.getInt("admin_Id"));
				admin.setAdminFname(rs.getString("admin_firstName"));
				admin.setAdminLname(rs.getString("admin_lastName"));
				admin.setAdminEmail(rs.getString("admin_email"));
				admin.setAdminContactNum(rs.getString("admin_contactNum"));
				admin.setAdminPassword(rs.getString("admin_password"));
				adminList.add(admin);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return adminList;
	}

	// fetch data from mysql duty manager table
	public ArrayList<dutyManager> getDutyManagers() {
		ArrayList<dutyManager> dmList = new ArrayList<dutyManager>();
		try {
			String query1 = "SELECT * FROM statcover_dm";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query1);
			while (rs.next()) {
				dutyManager dm = new dutyManager();
				dm.setDmId(rs.getInt("dm_id"));
				dm.setDmLname(rs.getString("dm_firstname"));
				dm.setDmLname(rs.getString("dm_lastname"));
				dm.setDmAffiliation(rs.getString("dm_affiliation"));
				dm.setDmEmail(rs.getString("dm_email"));
				dm.setDmContactNum(rs.getString("dm_contactNum"));
				dm.setDmPassword(rs.getString("dm_password"));
				dmList.add(dm);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return dmList;
	}

	// fetch data from mysql staff table
	public ArrayList<staff> getStaff() {
		ArrayList<staff> staffList = new ArrayList<staff>();
		try {
			String query1 = "SELECT * FROM statcover_staff";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query1);
			while (rs.next()) {
				staff staff = new staff();
				staff.setStaffId(rs.getInt("staff_Id"));
				staff.setStaffFname(rs.getString("staff_firstname"));
				staff.setStaffLname(rs.getString("staff_lastname"));
				staff.setStaffEmail(rs.getString("staff_email"));
				staff.setStaffContactNum(rs.getString("staff_contactNum"));
				staff.setStaffAddress(rs.getString("staff_address"));
				staff.setStaffSpecialty(rs.getString("staff_specialty"));
				staff.setStaffPosition(rs.getString("staff_position"));
				staff.setStaffAffiliation(rs.getString("staff_affiliation"));
				staff.setStaffLicenseNum(rs.getString("staff_licenseNum"));
				staff.setStaffLicenseExpiry(rs.getDate("staff_licenseExpiry"));
				staff.setStaffPassword(rs.getString("staff_password"));
				staffList.add(staff);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return staffList;
	}

	// fetch data from mysql requests table
	public ArrayList<requests> getRequests() {
		ArrayList<requests> requestList = new ArrayList<requests>();
		try {
			String query1 = "SELECT * FROM statcover_requests";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query1);
			while (rs.next()) {
				requests request = new requests();
				request.setRequestId(rs.getInt("requests_Id"));
				request.setSpecialtyRequirement(rs.getString("specialtyRequirements"));
				request.setShiftDate(rs.getDate("shiftDate"));
				request.setShift(rs.getString("shift"));
				request.setTimeFrom(rs.getString("timeFrom"));
				request.setTimeTo(rs.getString("timeTo"));
				request.setQuantity(rs.getInt("quantity"));
				request.setDmId(rs.getInt("dm_Id"));
				request.setDmLname(rs.getString("dm_lastName"));
				request.setRequestDate(rs.getDate("requestDate"));
				requestList.add(request);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return requestList;
	}

	// fetch data from mysql availability table
	public ArrayList<availability> getAvailability() {
		ArrayList<availability> availList = new ArrayList<availability>();
		try {
			String query1 = "SELECT * FROM statcover_staff_availability";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query1);
			while (rs.next()) {
				availability avail = new availability();
				avail.setAvailabilityId(rs.getInt("availability_Id"));
				avail.setStaffId(rs.getInt("staff_id"));
				avail.setStaffFname(rs.getString("staff_firstname"));
				avail.setStaffLname(rs.getString("staff_lastName"));
				avail.setStaffSpecialty(rs.getString("staff_specialty"));
				avail.setStaffAffiliation(rs.getString("staff_affiliation"));
				avail.setAvailableDate(rs.getDate("availability_date"));
				avail.setPreferredShift(rs.getString("preferred_shift"));
				availList.add(avail);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return availList;
	}
	
	//overloaded method of getAvailability which fetches only available nurses depending on day, shift, position, specialty
	public ArrayList <availability> getAvailability(Date day, String shift, String position, String speciality){
        ArrayList <availability> availList = new ArrayList<availability>();
        try
        { 
        	//query that finds shift availability and staff details
        	String findAvailableStaffQuery="select s.*, a.* from statcover_staff_availability a, statcover_staff s where availability_date='"+day+"'"+" and preferred_shift='"+shift+"'"+" and s.staff_Id=a.staff_id and s.staff_specialty='"+speciality +"' and s.staff_position='"+position+"'";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(findAvailableStaffQuery);
            while(rs.next()){
                availability avail = new availability();
                avail.setAvailabilityId(rs.getInt("availability_Id"));
                avail.setStaffId(rs.getInt("staff_id"));
                avail.setStaffFname(rs.getString("staff_firstName"));
                avail.setStaffLname(rs.getString("staff_lastName"));
                avail.setStaffSpecialty(rs.getString("staff_specialty"));
                avail.setStaffAffiliation(rs.getString("staff_affiliation"));
                avail.setAvailableDate(rs.getDate("availability_date"));
                avail.setPreferredShift(rs.getString("preferred_shift"));
                avail.setEmail(rs.getString("staff_email"));
                availList.add(avail);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return availList;
    }
	
	//overloaded method of getAvailability which retrieves shift request that matches a staff availability 
		public availability getAvailability(Integer staffID, Date day, String shift)
		{
	        availability avail = null;
			staff _staff=getStaff(staffID);
	        if (_staff!=null)
	        {
				try
		        { 
		        	//query that finds shift request available 
		        	String findAvailableStaffQuery="select * from statcover_requests where shiftDate='"+day+"'"+" and shift='"+shift+"'"+" and specialtyRequirement='"+_staff.getStaffSpecialty() +"' and position='"+_staff.getStaffPosition()+"'";
		            Statement ps = con.createStatement();
		            ResultSet rs = ps.executeQuery(findAvailableStaffQuery);
		            if (rs.first())
		            {
		                avail=new availability();
		            	avail.setAvailabilityId(-1);
		                avail.setStaffId(staffID);
		                avail.setStaffFname(_staff.getStaffFname());
		                avail.setStaffLname(_staff.getStaffLname());
		                avail.setStaffSpecialty(_staff.getStaffSpecialty());
		                avail.setStaffAffiliation(_staff.getStaffAffiliation());
		                avail.setAvailableDate(day);
		                avail.setPreferredShift(shift);
		                avail.setEmail(_staff.getStaffEmail());
		                avail.setTimeFrom(rs.getString("timeFrom"));
		                avail.setTimeTo(rs.getString("timeTo"));
		                avail.setRequestGUID(rs.getString("guid"));
		                avail.setdmid(rs.getInt("dm_Id"));
		            }
		        }
		        catch(Exception e){
		            System.out.println(e);
		        }
	        }
	        return avail;
	    }
	
	//add data about the email after sending notification email to the nurses
		public String addRequestNotification(int dmID, String staffEmail, String message) {
			try {
				String query1 = "INSERT INTO statcover_request_notification (receiver_email, req_message, req_dm_id, timestamp) values ('"+staffEmail+"','"+message+"',"+String.valueOf(dmID)+","+GlobalUtils.dateToStringQuoted(new java.util.Date())+")";
				Statement ps = con.createStatement();
				//[TODO 2-Uncomment the following line]
				ps.executeUpdate(query1);
				return "";
			} catch (Exception e) {
				System.out.println(e);
				return e.getMessage();
			}
		}
		
		//add data about the confirmation after getting acceptance by the nurses
		public String addAcceptNotification(int staffID, String dmEmail, String message, int request_id) {
			try {
				String query1 = "INSERT INTO statcover_accept_notifications (receiver_email, message, a_staff_id, timestamp, request_id) values ('"+dmEmail+"','"+message+"',"+String.valueOf(staffID)+","+GlobalUtils.dateToStringQuoted(new java.util.Date())+","+String.valueOf(request_id)+")";
				Statement ps = con.createStatement();
				//[TODO 3-Uncomment the following line]
				ps.executeUpdate(query1);
				return "";
			} catch (Exception e) {
				System.out.println(e);
				return e.getMessage();
			}
		}
		
		//add data about the confirmed requests
		public String addConfirmedRequests(int requestid, int dmid, int staffid) {
			try {
				String query1 = "INSERT INTO statcover_confirmed_requests (requestid, dmid, staffid) values ("+String.valueOf(requestid)+","+String.valueOf(dmid)+","+String.valueOf(staffid)+")";
				Statement ps = con.createStatement();
				ps.executeUpdate(query1);
				return "";
			} catch (Exception e) {
				System.out.println(e);
				return e.getMessage();
			}
		}
		
		//fetch a request data through guid
		public requests fetchTheRequestbyGUID(String guid) {
			try {
				String query1 = "select * from statcover_requests where guid='"+guid+"'";
				Statement ps = con.createStatement();
				ResultSet rs = ps.executeQuery(query1);
				if (rs.first())
				{
					requests request=new requests();
					request.setRequestId(rs.getInt("requests_Id"));
					request.setSpecialtyRequirement(rs.getString("specialtyRequirement"));
					request.setShiftDate(rs.getDate("shiftDate"));
					request.setShift(rs.getString("shift"));
					request.setTimeFrom(rs.getString("timeFrom"));
					request.setTimeTo(rs.getString("timeTo"));
					request.setQuantity(rs.getInt("quantity"));
					request.setDmId(rs.getInt("dm_Id"));
					request.setRequestDate(rs.getDate("requestDate"));
					request.setStatus(rs.getString("status"));
					return request;
				}
				else
					return null;
				
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		
	//count how many confirmation a request have
		public int getAcceptanceCount(int requestID) {
			try {
				String query1 = "select count(*) from statcover_confirmed_requests where requestid="+requestID;
				Statement ps = con.createStatement();
				ResultSet rs = ps.executeQuery(query1);
				if (rs.first())
				{
					return rs.getInt(1);	
				}
				else
					return -1;
				
			} catch (Exception e) {
				System.out.println(e);
				return -1;
			}
		}
		
	//check whether the staff is already booked for the same request
		public int checkPreviousRecordWithTheSameStaff(int requestID, int staffid) {
			try {
				String query1 = "select count(*) from statcover_confirmed_requests where requestid="+requestID+" and staffid="+String.valueOf(staffid);
				Statement ps = con.createStatement();
				ResultSet rs = ps.executeQuery(query1);
				if (rs.first())
				{
					return rs.getInt(1);	
				}
				else
					return -1;
				
			} catch (Exception e) {
				System.out.println(e);
				return -1;
			}
		}

	// fetch data from mysql registration table
	public ArrayList<registration> getRegistration() {
		ArrayList<registration> regList = new ArrayList<registration>();
		try {
			String query1 = "SELECT * FROM statcover_submitted_registration";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query1);
			while (rs.next()) {
				registration reg = new registration();
				reg.setRegistrationId(rs.getInt("registration_id"));
				reg.setFirstName(rs.getString("firstname"));
				reg.setLastName(rs.getString("lastname"));
				reg.setGender(rs.getString("gender"));
				reg.setEmail(rs.getString("email"));
				reg.setContactNum(rs.getString("contactNum"));
				reg.setAddress(rs.getString("address"));
				reg.setSpecialty(rs.getString("specialty"));
				reg.setPosition(rs.getString("position"));
				reg.setYearsOfExperience(rs.getInt("yearsOfExperience"));
				regList.add(reg);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return regList;
	}

	// Add duty manager to mysql dutymanager table
	public void AddDm(dutyManager dm) {
		// SQL add dm
		String query = "INSERT INTO statcover_dm (dm_firstName, dm_lastName, dm_affiliation, dm_email, dm_contactNum, dm_password) values (?,?,?,?,?,?)";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setString(1, dm.getDmFname());
			stmt.setString(2, dm.getDmLname());
			stmt.setString(3, dm.getDmAffiliation());
			stmt.setString(4, dm.getDmEmail());
			stmt.setString(5, dm.getDmContactNum());
			stmt.setString(6, dm.getDmPassword());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Edit an entry from duty manager table
	public void EditDm(dutyManager dm) {
		// SQL update dm
		String query = "update statcover_dm set dm_firstName=?, dm_lastName=?, dm_affiliation=?, dm_email=?, dm_contactNum=?, dm_password=? where dm_id=?";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, dm.getDmId());
			stmt.setString(2, dm.getDmFname());
			stmt.setString(3, dm.getDmLname());
			stmt.setString(4, dm.getDmAffiliation());
			stmt.setString(5, dm.getDmEmail());
			stmt.setString(6, dm.getDmContactNum());
			stmt.setString(7, dm.getDmPassword());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// delete an entry from duty manager table
	public void DeleteDm(int dm_id) {

		String query = "delete from statcover_dm where dm_id=?";
		try (PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, dm_id);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Add staff to mysql staff table
	public void AddStaff(staff staff) {
		// SQL add staff
		String query = "insert into statcover_staff (staff_Id, staff_firstName, staff_lastName, staff_email, staff_contactNum, staff_address, staff_specialty, staff_position, staff_affiliation, staff_licenseNum, staff_licenseExpiry, staff_password) values (?,?,?,?,?,?,?,?,?,?,?,?)";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, staff.getStaffId());
			stmt.setString(2, staff.getStaffFname());
			stmt.setString(3, staff.getStaffLname());
			stmt.setString(4, staff.getStaffEmail());
			stmt.setString(5, staff.getStaffContactNum());
			stmt.setString(6, staff.getStaffAddress());
			stmt.setString(7, staff.getStaffSpecialty());
			stmt.setString(8, staff.getStaffPosition());
			stmt.setString(9, staff.getStaffAffiliation());
			stmt.setString(10, staff.getStaffLicenseNum());

			// convert java util date to sql date
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			stmt.setString(11, sqlDate.toString());
			stmt.setString(12, staff.getStaffPassword());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Edit an entry from staff table
	public void editStaff(staff staff) {
		// SQL update staff
		String query = "update statcover_staff set staff_firstName=?, staff_lastName=?, staff_email=?, staff_contactNum=?, staff_address=?, staff_specialty=?, staff_position=?, staff_affiliation=?, staff_licenseNum=?, staff_licenseExpiry=?, staff_password=? where staff_Id = ?";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, staff.getStaffId());
			stmt.setString(2, staff.getStaffFname());
			stmt.setString(3, staff.getStaffLname());
			stmt.setString(4, staff.getStaffEmail());
			stmt.setString(5, staff.getStaffContactNum());
			stmt.setString(6, staff.getStaffAddress());
			stmt.setString(7, staff.getStaffSpecialty());
			stmt.setString(8, staff.getStaffPosition());
			stmt.setString(9, staff.getStaffAffiliation());
			stmt.setString(10, staff.getStaffLicenseNum());

			// convert java util date to sql date
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			stmt.setString(11, sqlDate.toString());
			stmt.setString(12, staff.getStaffPassword());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// delete an entry from staff table
	public void DeleteStaff(int staff_id) {

		String query = "delete from statcover_staff where staff_Id=?";
		try (PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, staff_id);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Add requests to mysql request table
	public void AddRequests(requests req) {
		// SQL add req
		String query = "insert into statcover_requests (requests_Id, specialtyRequirement, shiftDate, shift, timeFrom, timeTo, quantity, dm_Id, dm_lastName, requestDate) values (?,?,?,?,?,?,?,?,?,?)";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, req.getRequestId());
			stmt.setString(2, req.getSpecialtyRequirement());

			// convert java util date to sql date
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			stmt.setString(3, sqlDate.toString());
			stmt.setString(4, req.getShift());
			stmt.setString(5, req.getTimeFrom());
			stmt.setString(6, req.getTimeTo());
			stmt.setInt(7, req.getQuantity());
			stmt.setInt(8, req.getDmId());
			stmt.setString(9, req.getDmLname());
			stmt.setString(10, sqlDate.toString());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Edit an entry from request table
	public void editRequests(requests req) {
		// SQL update req
		String query = "update statcover_requests set specialtyRequirement=?, shiftDate=?, shift=?, timeFrom=?, timeTo=?, quantity=?, dm_Id=?, dm_lastName=?, requestDate=? where requests_Id=? ";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, req.getRequestId());
			stmt.setString(2, req.getSpecialtyRequirement());

			// convert java util date to sql date
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			stmt.setString(3, sqlDate.toString());
			stmt.setString(4, req.getShift());
			stmt.setString(5, req.getTimeFrom());
			stmt.setString(6, req.getTimeTo());
			stmt.setInt(7, req.getQuantity());
			stmt.setInt(8, req.getDmId());
			stmt.setString(9, req.getDmLname());
			stmt.setString(10, sqlDate.toString());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// delete an entry from requests table
	public void DeleteRequests(int req_id) {

		String query = "delete from statcover_requests where requests_Id=?";
		try (PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, req_id);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Add availability to mysql availability table
	public void AddAvailability(availability avail) {
		// SQL add avail
		String query = "insert into statcover_staff_availability (availability_Id, staff_id, staff_firstName, staff_lastName, staff_specialty, staff_affiliation, availability_date, preferred_shift) values (?,?,?,?,?,?,?,?)";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, avail.getAvailabilityId());
			stmt.setInt(2, avail.getStaffId());
			stmt.setString(3, avail.getStaffFname());
			stmt.setString(4, avail.getStaffLname());
			stmt.setString(5, avail.getStaffSpecialty());
			stmt.setString(6, avail.getStaffAffiliation());

			// convert java util date to sql date
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			stmt.setString(7, sqlDate.toString());
			stmt.setString(8, avail.getPreferredShift());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Edit an entry from availability table
	public void editAvailability(availability avail) {
		// SQL update avail
		String query = "update statcover_staff_availability set staff_id=?, staff_firstName=?, staff_lastName=?, staff_specialty=?, staff_affiliation=?, availability_date=?, preferred_shift=? where availability_Id=?";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, avail.getAvailabilityId());
			stmt.setInt(2, avail.getStaffId());
			stmt.setString(3, avail.getStaffFname());
			stmt.setString(4, avail.getStaffLname());
			stmt.setString(5, avail.getStaffSpecialty());
			stmt.setString(6, avail.getStaffAffiliation());

			// convert java util date to sql date
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			stmt.setString(7, sqlDate.toString());
			stmt.setString(8, avail.getPreferredShift());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// delete an entry from staff availability table
	public void DeleteAvailability(int avail_id) {

		String query = "delete from statcover_staff_availability where availabilty_Id=?";
		try (PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, avail_id);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Add registration to mysql registration table
	public void AddRegistration(registration reg) {
		// SQL add reg
		String query = "insert into statcover_submitted_registration (registration_id, firstName, lastName, gender, email, contactNum, address, specialty, position, yearsOfExperience) values (?,?,?,?,?,?,?,?,?,?,)";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, reg.getRegistrationId());
			stmt.setString(2, reg.getFirstName());
			stmt.setString(3, reg.getLastName());
			stmt.setString(4, reg.getGender());
			stmt.setString(5, reg.getEmail());
			stmt.setString(6, reg.getContactNum());
			stmt.setString(7, reg.getAddress());
			stmt.setString(8, reg.getSpecialty());
			stmt.setString(9, reg.getPosition());
			stmt.setInt(10, reg.getYearsOfExperience());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Edit an entry from registration table
	public void editRegistration(registration reg) {
		// SQL update reg
		String query = "update statcover_submitted_registration set firstName=?, lastName=?, gender=?, email=?, contactNum=?, address=?, specialty=?, position=?, yearsOfExperience=? where registration_id=?";

		try (
				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, reg.getRegistrationId());
			stmt.setString(2, reg.getFirstName());
			stmt.setString(3, reg.getLastName());
			stmt.setString(4, reg.getGender());
			stmt.setString(5, reg.getEmail());
			stmt.setString(6, reg.getContactNum());
			stmt.setString(7, reg.getAddress());
			stmt.setString(8, reg.getSpecialty());
			stmt.setString(9, reg.getPosition());
			stmt.setInt(10, reg.getYearsOfExperience());

			// execute the statement
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// delete an entry from registration table
	public void DeleteRegistration(int reg_id) {

		String query = "delete from statcover_registration where registration_id=?";
		try (PreparedStatement stmt = con.prepareStatement(query);) {

			stmt.setInt(1, reg_id);

			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
