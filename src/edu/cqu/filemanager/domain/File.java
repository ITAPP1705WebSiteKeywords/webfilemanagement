package edu.cqu.filemanager.domain;

import java.sql.Date;

import edu.cqu.common.Entity;

public class File extends Entity {
	private String fileID;

	private String fileName;

	private String fileSubject;

	private String filePath;

	private String fileType;

	private String fileOwner;

	private Date fileCreated;

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileOwner() {
		return fileOwner;
	}

	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "FileID";
	}

	@Override
	public String getKeyValue() {
		// TODO Auto-generated method stub
		return getFileID();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "tblFile";
	}

	public Date getFileCreated() {
		return fileCreated;
	}

	public void setFileCreated(Date fileCreated) {
		this.fileCreated = fileCreated;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			return this.hashCode() == obj.hashCode();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {

		return fileID.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fileID + "," + fileName + "," + filePath + "," + fileType + ","
				+ fileOwner + "," + fileCreated;
	}

	public String getFileSubject() {
		return fileSubject;
	}

	public void setFileSubject(String fileSubject) {
		this.fileSubject = fileSubject;
	}
}
