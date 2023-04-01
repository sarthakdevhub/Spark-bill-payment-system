package dto;

public class ConsumerSignUpDTOImpl implements ConsumerSignUpDTO {
	String Fname;
	String Lname;
	String Username;
	String Password;
	String address;
	String mobile;
	String email;
	String Sques;
	String Sans;
	public ConsumerSignUpDTOImpl(String fname, String lname, String username, String password, String address,
			String mobile, String email, String sques, String sans) {
		Fname = fname;
		Lname = lname;
		Username = username;
		Password = password;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
		Sques = sques;
		Sans = sans;
	}
	@Override
	public String getFname() {
		return Fname;
	}
	@Override
	public void setFname(String fname) {
		Fname = fname;
	}
	@Override
	public String getLname() {
		return Lname;
	}
	@Override
	public void setLname(String lname) {
		Lname = lname;
	}
	@Override
	public String getUsername() {
		return Username;
	}
	@Override
	public void setUsername(String username) {
		Username = username;
	}
	@Override
	public String getPassword() {
		return Password;
	}
	@Override
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String getAddress() {
		return address;
	}
	@Override
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String getMobile() {
		return mobile;
	}
	@Override
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String getSques() {
		return Sques;
	}
	@Override
	public void setSques(String sques) {
		Sques = sques;
	}
	@Override
	public String getSans() {
		return Sans;
	}
	@Override
	public void setSans(String sans) {
		Sans = sans;
	}
		
}
