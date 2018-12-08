package Database;

import java.io.Serializable;

public class User implements Serializable{
	private String userId;
	private String pwd;
	private String name;
	private String birth;
	private String addr;
	private String phoneNum;
	private Float avgGrade = null;
	
	public User() {super();}
	public User(String userId) {
		super();
		this.userId = userId;
	}
	public String getUserId() {return userId;}
	public void setUserId(String userId) {this.userId = userId;}
	public String getPwd() {return pwd;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getBirth() {return birth;}
	public void setBirth(String birth) {this.birth = birth;}
	public String getAddr() {return addr;}
	public void setAddr(String addr) {this.addr = addr;}
	public String getPhoneNum() {return phoneNum;}
	public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}
	public Float getavgGrade() {return avgGrade;}
	public void setavgGrade(Float avgGrade) {this.avgGrade = avgGrade;}
	@Override
	public String toString() {
		return "<USER>UserId : "+userId+" pwd : "+ pwd+" name : "+name
				+" birth : "+birth +" addr : "+addr+" phoneNum : "+phoneNum
				+" avgGrade : "+avgGrade;
	}
}
