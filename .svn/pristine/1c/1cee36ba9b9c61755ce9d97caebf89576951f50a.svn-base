package com.kintiger.xplatform.file.dao.impl;

import java.util.List;

import com.kintiger.xplatform.api.file.bo.FileInfo;
import com.kintiger.xplatform.file.dao.IFileDao;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;

/**
 * file dao.
 * 
 * @author xujiakun
 * 
 */
public class FileDaoImpl extends BaseDaoImpl implements IFileDao {

	public FileInfo getFileInfo(FileInfo fileInfo) {
		return (FileInfo) getSqlMapClientTemplate().queryForObject("file.getFileInfo", fileInfo);
	}

	public String createFileInfo(FileInfo fileInfo) {
		return (String) getSqlMapClientTemplate().insert("file.createFileInfo", fileInfo);
	}

	public int getFileCount(FileInfo fileInfo) {
		return (Integer) getSqlMapClientTemplate().queryForObject("file.getFileCount", fileInfo);
	}

	@SuppressWarnings("unchecked")
	public List<FileInfo> getFileList(FileInfo fileInfo) {
		return (List<FileInfo>) getSqlMapClientTemplate().queryForList("file.getFileList", fileInfo);
	}

	public int deleteFile(FileInfo fileInfo) {
		return getSqlMapClientTemplate().update("file.deleteFile", fileInfo);
	}

}
