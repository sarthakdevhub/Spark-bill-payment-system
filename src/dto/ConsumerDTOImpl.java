package dto;

public class ConsumerDTOImpl implements ConsumerDTO {
	private String conId;
	private String name;
	private String username;
	private String password;
	private Integer isActive;
	
	public ConsumerDTOImpl(String conId, String name, String username, String password, Integer isActive) {
		this.conId = conId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}
	
	public ConsumerDTOImpl() {}
	
	@Override
	public String getConId() {
		return conId;
	}
	@Override
	public void setConId(String conId) {
		this.conId = conId;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public Integer getIsActive() {
		return isActive;
	}
	@Override
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "  "+conId +" | "+  name +" | "+ username +" | "+  password+ " | "+ isActive ;
	}
	
	
	
}
