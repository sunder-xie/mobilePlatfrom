package com.kintiger.xplatform.hadoop.service.impl;

import java.io.File;
import java.io.OutputStream;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.file.IDFSService;
import com.kintiger.xplatform.api.file.IFileService;
import com.kintiger.xplatform.api.file.bo.FileInfo;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.exception.ServiceException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.HDFSUtil;
import com.kintiger.xplatform.framework.util.OidUtil;

/**
 * hdfs service.
 * 
 * @author xujiakun
 * 
 */
public class HDFSServiceImpl implements IDFSService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(HDFSServiceImpl.class);

	private IFileService fileService;

	private String ip;

	private int port;

	public String getFileName(String fileId) {
		try {
			FileInfo fileInfo = getFileInfo(fileId);

			if (fileInfo != null) {
				if (StringUtil.isNotEmpty(fileInfo.getSuffix())) {
					return fileInfo.getFileName() + "." + fileInfo.getSuffix();
				} else {
					return fileInfo.getFileName();
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public boolean fetchFile(String fileId, String localFilePath) {
		FileSystem fs = null;
		try {
			fs = HDFSUtil.getFileSystem(ip, port);

			FileInfo fileInfo = getFileInfo(fileId);

			HDFSUtil.download(fs, localFilePath + "/" + fileInfo.getFileName() + "." + fileInfo.getSuffix(),
				fileInfo.getFilePath());

			return true;
		} catch (Exception e) {
			logger.error(e);
		}

		return false;
	}

	public boolean fetchFile(String fileId, OutputStream output) {
		FileSystem fs = null;
		try {
			fs = HDFSUtil.getFileSystem(ip, port);

			FileInfo fileInfo = getFileInfo(fileId);

			HDFSUtil.read(fs, fileInfo.getFilePath(), output);

			return true;
		} catch (Exception e) {
			logger.error(e);
		}

		return false;
	}

	public String saveFile(String fileName, String suffix, String localFileName) {
		FileSystem fs = null;
		try {
			fs = HDFSUtil.getFileSystem(ip, port);
			// 0. rename fileName
			String fileId = OidUtil.newId();

			// 1. save file 2 hdfs
			HDFSUtil.upload(fs, localFileName, fileId);

			// 2. save info 2 oracle
			Path workDir = fs.getWorkingDirectory();
			createFileInfo(fileId, fileName, suffix, workDir);

			return fileId;
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public String saveFile(String fileName, String suffix, File localFile) {
		FileSystem fs = null;
		try {
			fs = HDFSUtil.getFileSystem(ip, port);
			// 0. rename fileName
			String fileId = OidUtil.newId();

			// 1. save file 2 hdfs
			HDFSUtil.write(fs, fileId, localFile);

			// 2. save info 2 oracle
			Path workDir = fs.getWorkingDirectory();
			createFileInfo(fileId, fileName, suffix, workDir);

			return fileId;
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public String saveFile(String fileName, String suffix, byte[] bytes) {
		FileSystem fs = null;
		try {
			fs = HDFSUtil.getFileSystem(ip, port);
			// 0. rename fileName
			String fileId = OidUtil.newId();

			// 1. save file 2 hdfs
			HDFSUtil.write(fs, fileId, bytes);

			// 2. save info 2 oracle
			Path workDir = fs.getWorkingDirectory();
			createFileInfo(fileId, fileName, suffix, workDir);

			return fileId;
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * getFileInfo.
	 * 
	 * @param fileId
	 * @return
	 */
	private FileInfo getFileInfo(String fileId) {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileId(fileId);
		// fileInfo.setFlag(IFileService.FILE_FLAG_USED)

		return fileService.getFileInfo(fileInfo);
	}

	/**
	 * createFileInfo.
	 * 
	 * @param fileId
	 * @param fileName
	 * @param suffix
	 * @param workDir
	 * @return
	 * @throws ServiceException
	 */
	private void createFileInfo(String fileId, String fileName, String suffix, Path workDir) throws ServiceException {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileId(fileId);
		fileInfo.setFileName(fileName);
		fileInfo.setSuffix(suffix);
		fileInfo.setFilePath(workDir + "/" + fileId);

		BooleanResult result = fileService.createFileInfo(fileInfo);

		if (!result.getResult()) {
			throw new ServiceException("createFileInfo error");
		}
	}

	public IFileService getFileService() {
		return fileService;
	}

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
