package edu.cqu.filemanager.dao;

import java.util.List;
import edu.cqu.filemanager.domain.File;
import edu.cqu.filemanager.exception.FileManagerException;

public interface FileDAO extends DAO {

	List selectFilesOfUser(String userID)throws FileManagerException;

	List selectAllFiles()throws FileManagerException;

	File selectFile(String fileID)throws FileManagerException;

}
