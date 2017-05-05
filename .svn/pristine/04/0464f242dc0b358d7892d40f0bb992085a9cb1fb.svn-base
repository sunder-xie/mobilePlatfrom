package com.kintiger.xplatform.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.alibaba.common.lang.StringUtil;

/**
 * File Util.
 * 
 * @author xujiakun
 * 
 */
public final class FileUtil {

	private static final Logger logger = Logger.getLogger(FileUtil.class);

	private static final int BUFFER_SIZE = 16 * 1024;

	private FileUtil() {

	}

	public static boolean saveFile(String path, String content) {
		boolean flag = false;
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(path), BUFFER_SIZE);
			out.write(content.getBytes("GBK"));
			flag = true;
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		return flag;
	}

	/**
	 * 文件另存为.
	 * 
	 * @param source
	 *            源文件
	 * @param target
	 *            目标文件
	 * @return 保存是否成功
	 */
	public static boolean saveAsFile(File source, File target) {
		boolean flag = false;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(source), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(target), BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			while (in.read(buffer) > 0) {
				out.write(buffer);
			}

			flag = true;
		} catch (Exception e) {
			logger.error("文件另存为失败！", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		return flag;
	}

	public static String readFile(String filePath) {
		StringBuilder fileContent = new StringBuilder();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(new FileInputStream(filePath), "GBK");
			br = new BufferedReader(isr);

			String tempStr = br.readLine();
			while (tempStr != null) {
				fileContent.append(tempStr);
				tempStr = br.readLine();
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		return fileContent.toString();
	}

	/**
	 * 获取文件类型名 (.txt).
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtention(String fileName) {
		if (StringUtil.isEmpty(fileName)) {
			return null;
		}

		return fileName.substring(fileName.lastIndexOf('.'));
	}

	/**
	 * 获取文件名.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		if (StringUtil.isEmpty(fileName)) {
			return null;
		}

		if (fileName.lastIndexOf('.') == -1) {
			return fileName;
		} else {
			return fileName.substring(0, fileName.lastIndexOf('.'));
		}
	}

	/**
	 * 获取文件类型名 (txt).
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		if (StringUtil.isEmpty(fileName)) {
			return null;
		}

		if (fileName.lastIndexOf('.') == -1) {
			return "";
		} else {
			return fileName.substring(fileName.lastIndexOf('.') + 1);
		}
	}

}
