package edu.cqu.filemanager.dao;

import java.util.List;

import edu.cqu.common.Entity;
import edu.cqu.filemanager.exception.FileManagerException;

public interface DAO {
	int save(Entity e) throws FileManagerException;

	int update(Entity e) throws FileManagerException;

	int delete(Entity e) throws FileManagerException;

	List select(String sql) throws FileManagerException;

	
}
