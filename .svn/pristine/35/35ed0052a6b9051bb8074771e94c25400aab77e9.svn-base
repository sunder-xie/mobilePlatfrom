package com.kintiger.xplatform.api.file;

import java.io.File;
import java.io.OutputStream;

/**
 * DFS.
 * 
 * @author xujiakun
 * 
 */
public interface IDFSService {

	String DFS_TYPE_HDFS = "hdfs";

	/**
	 * get a file's name.
	 * 
	 * @param fileId
	 * @return fileName if save successully, or null if fail
	 */
	String getFileName(String fileId);

	/**
	 * fetch a file to local disk.
	 * 
	 * @param fileId
	 * @param localFilePath
	 * @return
	 */
	boolean fetchFile(String fileId, String localFilePath);

	/**
	 * fetch a file to output stream.
	 * 
	 * @param fileId
	 * @param output
	 * @return
	 */
	boolean fetchFile(String fileId, OutputStream output);

	/**
	 * save a local file to hdfs.
	 * 
	 * @param fileName
	 * @param suffix
	 * @param localFileName
	 * @return fileId if save successully, or null if fail
	 */
	String saveFile(String fileName, String suffix, String localFileName);

	/**
	 * save a local file(file) to hdfs.
	 * 
	 * @param fileName
	 * @param suffix
	 * @param localFile
	 * @return fileId if save successully, or null if fail
	 */
	String saveFile(String fileName, String suffix, File localFile);

	/**
	 * save a local file(byte) to hdfs.
	 * 
	 * @param fileName
	 * @param suffix
	 * @param bytes
	 * @return fileId if save successully, or null if fail
	 */
	String saveFile(String fileName, String suffix, byte[] bytes);

}
