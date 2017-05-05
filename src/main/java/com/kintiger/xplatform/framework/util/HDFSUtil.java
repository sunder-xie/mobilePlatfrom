package com.kintiger.xplatform.framework.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import com.kintiger.xplatform.framework.exception.ServiceException;

/***
 * HDFS Util.
 * 
 */
public final class HDFSUtil {

	private static final int BUFF_SIZE = 4 * 1024;

	private HDFSUtil() {

	}

	public static FileSystem getFileSystem(String ip, int port) throws ServiceException {
		FileSystem fs = null;

		try {
			String url = "hdfs://" + ip + ":" + port;
			Configuration config = new Configuration();
			config.set("fs.default.name", url);

			fs = FileSystem.get(config);
		} catch (IOException e) {
			throw new ServiceException("getFileSystem failed :", e);
		}

		return fs;
	}

	/**
	 * 创建目录和父目录.
	 * 
	 * @param fs
	 * @param dirName
	 * @throws ServiceException
	 */
	public static void mkdirs(FileSystem fs, String dirName) throws ServiceException {
		String dir = null;

		try {
			Path workDir = fs.getWorkingDirectory();
			dir = workDir + "/" + dirName;
			Path src = new Path(dir);
			boolean succ = fs.mkdirs(src);

			if (!succ) {
				throw new ServiceException("mkdirs error");
			}
		} catch (IOException e) {
			throw new ServiceException("create directory " + dir, e);
		}
	}

	/**
	 * 删除目录和子目录.
	 * 
	 * @param fs
	 * @param dirName
	 * @throws ServiceException
	 */
	public static void rmdirs(FileSystem fs, String dirName) throws ServiceException {
		String dir = null;

		try {
			Path workDir = fs.getWorkingDirectory();
			dir = workDir + "/" + dirName;
			Path src = new Path(dir);
			boolean succ = fs.delete(src, true);

			if (!succ) {
				throw new ServiceException("rmdirs error");
			}
		} catch (IOException e) {
			throw new ServiceException("remove directory " + dir, e);
		}
	}

	/**
	 * 上传目录或文件.
	 * 
	 * @param fs
	 * @param local
	 * @param remote
	 * @throws ServiceException
	 */
	public static void upload(FileSystem fs, String local, String remote) throws ServiceException {
		try {
			Path workDir = fs.getWorkingDirectory();
			Path dst = new Path(workDir + "/" + remote);
			Path src = new Path(local);

			fs.copyFromLocalFile(false, true, src, dst);
		} catch (IOException e) {
			throw new ServiceException("upload " + local + " to  " + remote, e);
		}
	}

	/**
	 * 下载目录或文件.
	 * 
	 * @param fs
	 * @param local
	 * @param remote
	 * @throws ServiceException
	 */
	public static void download(FileSystem fs, String local, String remote) throws ServiceException {
		try {
			Path dst = new Path(remote);
			Path src = new Path(local);

			fs.copyToLocalFile(false, dst, src);
		} catch (IOException e) {
			throw new ServiceException("download from " + remote + " to  " + local, e);
		}
	}

	/**
	 * 写文件.
	 * 
	 * @param fs
	 * @param path
	 * @param data
	 * @throws ServiceException
	 */
	public static void write(FileSystem fs, String path, byte[] bytes) throws ServiceException {
		FSDataOutputStream out = null;

		try {
			Path workDir = fs.getWorkingDirectory();
			Path dst = new Path(workDir + "/" + path);

			out = fs.create(dst);
			out.write(bytes);
		} catch (IOException e) {
			throw new ServiceException("write content to " + path, e);
		} finally {
			IOUtils.closeStream(out);
		}
	}

	/**
	 * 写文件.
	 * 
	 * @param fs
	 * @param path
	 * @param data
	 * @throws ServiceException
	 * 
	 */
	public static void write(FileSystem fs, String path, File data) throws ServiceException {
		InputStream in = null;
		FSDataOutputStream out = null;

		try {
			Path workDir = fs.getWorkingDirectory();
			Path dst = new Path(workDir + "/" + path);

			in = new BufferedInputStream(new FileInputStream(data));
			out = fs.create(dst);

			IOUtils.copyBytes(in, out, BUFF_SIZE, true);
		} catch (IOException e) {
			throw new ServiceException("write content to " + path, e);
		} finally {
			IOUtils.closeStream(out);
			IOUtils.closeStream(in);
		}
	}

	/**
	 * 读文件.
	 * 
	 * @param fs
	 * @param path
	 * @return
	 * @throws ServiceException
	 */
	public static String read(FileSystem fs, String path) throws ServiceException {
		FSDataInputStream in = null;

		try {
			Path dst = new Path(path);

			// reading
			in = fs.open(dst);
			return in.readUTF();
		} catch (IOException e) {
			throw new ServiceException("read content from " + path, e);
		} finally {
			IOUtils.closeStream(in);
		}
	}

	/**
	 * 读文件.
	 * 
	 * @param fs
	 * @param path
	 * @return
	 * @throws ServiceException
	 */
	public static void read(FileSystem fs, String path, OutputStream output) throws ServiceException {
		FSDataInputStream in = null;

		try {
			Path dst = new Path(path);

			// reading
			in = fs.open(dst);

			IOUtils.copyBytes(in, output, BUFF_SIZE, true);
		} catch (IOException e) {
			throw new ServiceException("read content from " + path, e);
		} finally {
			IOUtils.closeStream(in);
		}
	}

}
