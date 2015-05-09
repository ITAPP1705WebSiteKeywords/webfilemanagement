package edu.cqu.filemanager.dao;

import java.util.List;
import edu.cqu.filemanager.domain.User;
import edu.cqu.filemanager.exception.FileManagerException;

public interface UserDAO extends DAO {

	User selectUser(String userID)throws FileManagerException;

	List selectAllUsers()throws FileManagerException;

}
