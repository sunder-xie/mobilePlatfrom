package com.kintiger.xplatform.file.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.file.IDFSService;
import com.kintiger.xplatform.api.file.IFileService;
import com.kintiger.xplatform.api.file.bo.FileInfo;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.FileUtil;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * 
 * @author xujiakun
 * 
 */
public class FileAction extends BaseAction {

	private static final long serialVersionUID = 6285367046945881121L;

	private static final int BYTE = 4 * 1024;

	private Logger4jExtend logger = Logger4jCollection.getLogger(FileAction.class);

	private IFileService fileService;

	private IDFSService dfsService;

	private int total;

	private String fileId;

	private List<FileInfo> fileInfoList;

	private String fileName;

	private String flag;

	private File upload;

	private String uploadFileName;

	public String searchFile() {
		return "searchFile";
	}

	@JsonResult(field = "fileInfoList", include = { "fileId", "fileName", "suffix", "filePath", "flag", "createDate",
		"modifyDate" }, total = "total")
	public String getFileJsonList() {
		FileInfo c = new FileInfo();
		c = getSearchInfo(c);

		if (StringUtil.isNotEmpty(fileName)) {
			c.setFileName(fileName.trim());
		}

		if (StringUtil.isNotEmpty(flag)) {
			c.setFlag(flag.trim());
		}

		total = fileService.getFileCount(c);
		if (total != 0) {
			fileInfoList = fileService.getFileList(c);
		}

		return JSON;
	}

	public String fetchFile() {
		OutputStream out = null;

		if (StringUtil.isNotEmpty(fileId)) {
			try {
				HttpServletResponse response = getServletResponse();
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(dfsService.getFileName(fileId).getBytes("GBK"), "ISO8859-1") + "\"");

				out = response.getOutputStream();

				boolean result = dfsService.fetchFile(fileId, out);

				if (result) {
					out.write(BYTE);
					out.flush();
				}
			} catch (IOException e) {
				logger.error(fileId, e);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}
		}

		this.setFailMessage("文件下载失败！");
		return RESULT_MESSAGE;
	}

	public String deleteFile() {

		String[] l = new String[fileInfoList.size()];
		int i = 0;
		FileInfo fileInfo = new FileInfo();

		try {
			for (FileInfo c : fileInfoList) {
				if (StringUtil.isNotEmpty(c.getFileId())) {
					l[i++] = c.getFileId();
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(fileInfoList), e);
			this.setFailMessage(IFileService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		// 无有效的菜单id
		if (i == 0) {
			this.setFailMessage(IFileService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		fileInfo.setCodes(l);
		BooleanResult result = fileService.deleteFile(fileInfo);

		if (result.getResult()) {
			this.setSuccessMessage("已成功删除" + result.getCode() + "个文件！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public void saveFile() {
		PrintWriter out = null;

		try {
			HttpServletResponse response = this.getServletResponse();
			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();

			String id =
				dfsService.saveFile(FileUtil.getFileName(uploadFileName), FileUtil.getFileSuffix(uploadFileName),
					upload);

			out.write(StringUtil.isNotEmpty(id) ? "{success:true,msg:'" + id + "'}" : "{success:false,msg:'failure'}");
			out.flush();
		} catch (Exception e) {
			logger.error(e);
			out.write("{success:false,msg:'failure'}");
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

	public IFileService getFileService() {
		return fileService;
	}

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	public IDFSService getDfsService() {
		return dfsService;
	}

	public void setDfsService(IDFSService dfsService) {
		this.dfsService = dfsService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
