package edu.cqu.filemanager.domain;

import java.sql.Date;

import edu.cqu.common.Entity;

public class User extends Entity {

	private String userID;

	private String userName;

	private String userMail;

	private String userPassword;

	private Date userCreated;

	private int userType = 0;// normal user

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String getKey() {

		return "UserID";
	}

	@Override
	public String getKeyValue() {
		return getUserID();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "tblUser";
	}

	public Date getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Date userCreated) {
		this.userCreated = userCreated;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null) {
			return this.hashCode() == obj.hashCode();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return userID.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userID + "," + userName + "," + userMail + "," + userPassword
				+ "," + userType + "," + userCreated;
	}

}
