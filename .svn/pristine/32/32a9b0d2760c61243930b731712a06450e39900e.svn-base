package com.kintiger.xplatform.station.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.station.IStationService;
import com.kintiger.xplatform.api.station.bo.Station;
import com.kintiger.xplatform.api.user.IUserService;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.JsonUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * 权限岗位 包括station_user.
 * 
 * @author xujiakun
 * 
 */
public class StationAction extends BaseAction {

	private static final long serialVersionUID = -5001266762985455515L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(StationAction.class);

	private IStationService stationService;

	private IUserService userService;

	private List<Station> stationList;
	@Decode
	private String stationId;
	@Decode
	private String stationName;

	private String orgId;

	private int total;

	private Station station;

	private String passport;

	@Decode
	private String userName;

	private String userIds;

	private String listJson;

	private String id;

	/**
	 * 进入查询页面.
	 */
	@ActionLog(actionName = "权限岗位查询")
	public String searchStation() {
		return "searchStation";
	}

	/**
	 * 查询权限岗位.
	 */
	@JsonResult(field = "stationList", include = { "stationId", "stationName", "userCount", "roleCount" }, total = "total")
	public String getStationJsonList() {
		Station s = new Station();
		s = getSearchInfo(s);

		if (StringUtil.isNotEmpty(stationId) && StringUtil.isNotEmpty(stationId.trim())) {
			s.setStationId(stationId.trim());
		}

		if (StringUtil.isNotEmpty(stationName) && StringUtil.isNotEmpty(stationName.trim())) {
			s.setStationName(stationName.trim());
		}

		total = stationService.getStationJsonListCount(s);

		if (total != 0) {
			stationList = stationService.getStationJsonList(s);
		}

		return JSON;
	}

	/**
	 * 验证station有效.
	 * 
	 * @param station
	 * @return
	 */
	private boolean validate(Station station) {
		if (station == null) {
			this.setFailMessage(IStationService.ERROR_INPUT_MESSAGE);
			return false;
		}

		if (StringUtil.isEmpty(station.getStationId()) || StringUtil.isEmpty(station.getStationId().trim())
			|| StringUtil.isEmpty(station.getStationName()) || StringUtil.isEmpty(station.getStationName().trim())) {
			this.setFailMessage("权限岗位编号和权限岗位名称不能为空！");
			return false;
		}

		if (station.getStationId().trim().length() > 30) {
			this.setFailMessage("权限岗位编号长度不能超过30！");
			return false;
		}

		if (station.getStationName().trim().length() > 80) {
			this.setFailMessage("权限岗位编号长度不能超过80！");
			return false;
		}

		return true;
	}

	/**
	 * 
	 * 进入创建页面.
	 */
	public String createStationPrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * 创建保存.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "权限岗位创建")
	public String createStation() {
		if (stationService.getStation(stationId) != null) {
			this.setFailMessage("权限岗位信息创建失败，相同的权限岗位编号已存在！");
			return RESULT_MESSAGE;
		}

		if (!validate(station)) {
			return RESULT_MESSAGE;
		}

		String id = stationService.createStation(station);
		if (id != null) {
			this.setSuccessMessage("权限岗位创建成功!");
		} else {
			this.setFailMessage("权限岗位创建失败!");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 进入修改页面.
	 * 
	 * @return
	 */
	public String updateStationPrepare() {
		try {
			stationId = new String(stationId.getBytes("ISO8859-1"), "utf-8");
			station = stationService.getStation(stationId);
		} catch (UnsupportedEncodingException e) {
			logger.error("stationId:" + stationId + "stationName:" + stationName, e);
		}

		return UPDATE_PREPARE;
	}

	/**
	 * 修改保存.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "权限岗位修改")
	public String updateStation() {
		if (!validate(station)) {
			return RESULT_MESSAGE;
		}

		int r = stationService.updateStation(station);
		if (r > 0) {
			this.setSuccessMessage("权限岗位修改成功!");
		} else {
			this.setFailMessage("权限岗位修改失败!");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 删除保存.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "权限岗位删除")
	public String deleteStation() {
		if (StringUtil.isEmpty(stationId) && StringUtil.isEmpty(stationId.trim())) {
			this.setFailMessage("被删除权限岗位不能为空！");
			return RESULT_MESSAGE;
		}

		try {
			stationId = new String(stationId.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("stationId:" + stationId, e);
			this.setFailMessage(IStationService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		BooleanResult r = stationService.deleteStation(stationId.trim());
		if (r.getResult()) {
			this.setSuccessMessage(r.getCode());
		} else {
			this.setFailMessage(r.getCode());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 进入權限岗位人员配置页面.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "权限岗位人员查询")
	public String configStationUserPrepare() {
		try {
			stationId = new String(stationId.getBytes("ISO8859-1"), "utf-8");
			stationName = new String(stationName.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("stationId:" + stationId + "stationName:" + stationName, e);
		}

		return "configStationUserPrepare";
	}

	/**
	 * 查询岗位人员.
	 * 
	 * @return
	 */
	@JsonResult(field = "stationList", include = { "id", "userId", "passport", "userName" }, total = "total")
	public String getStationUserJsonList() {
		Station s = new Station();
		s = getSearchInfo(s);

		if (StringUtil.isNotEmpty(stationId) && StringUtil.isNotEmpty(stationId.trim())) {
			s.setStationId(stationId.trim());
		}

		if (StringUtil.isNotEmpty(passport) && StringUtil.isNotEmpty(passport.trim())) {
			s.setPassport(passport.trim());
		}

		if (StringUtil.isNotEmpty(userName) && StringUtil.isNotEmpty(userName.trim())) {
			s.setUserName(userName.trim());
		}

		total = stationService.getStationUserCount(s);
		if (total != 0) {
			stationList = stationService.getStationUserList(s);
		}

		return JSON;
	}

	public String chooseStationUser() {
		try {
			stationId = new String(stationId.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("stationId:" + stationId, e);
		}

		return "chooseStationUser";
	}

	/**
	 * decode.
	 */
	private void decodeStationId() {
		if (StringUtil.isNotEmpty(stationId)) {
			try {
				stationId = new String(stationId.getBytes("ISO8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("stationId:" + stationId, e);
			}
		}
	}

	/**
	 * 展开人员组织树.
	 * 
	 * @return
	 */
	public String getOrgPeopleTree() {
		decodeStationId();

		this.actionName = "stationAction!getPeopleSelector.htm?stationId=" + stationId;
		return "orgTreeAjaxInfo";
	}

	public String getPeopleSelector() {
		decodeStationId();

		if (StringUtil.isNotEmpty(orgId)) {
			List<User> users = userService.getUsersByOrgId(orgId);

			if (users != null && users.size() > 0) {
				listJson = JsonUtil.bean2Json(users.getClass(), users);
				StringBuffer temp = new StringBuffer();
				temp.append(listJson);
				temp.insert(0, "{values:");
				temp.append(",total:");
				temp.append(users.size());
				temp.append("}");
				listJson = temp.toString();
			}
		}

		return "peopleSelector";
	}

	/**
	 * 保存权限岗位人员.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "权限岗位人员配置")
	public String configStationUser() {
		if (StringUtil.isEmpty(stationId) || StringUtil.isEmpty(userIds) || StringUtil.isEmpty(stationId.trim())
			|| StringUtil.isEmpty(userIds.trim())) {
			this.setFailMessage("岗位编号和人员编号不能为空！");
			return RESULT_MESSAGE;
		}

		String[] ids = userIds.split(",");

		Station s = new Station();
		s.setStationId(stationId.trim());
		s.setCodes(ids);
		List<Station> l = stationService.getStationUserList(s);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (l != null && l.size() > 0) {
			for (Station ss : l) {
				map.put(ss.getUserId(), true);
			}
		}

		List<Station> stations = new ArrayList<Station>();
		for (String userId : ids) {
			Station st = new Station();
			st.setStationId(stationId.trim());
			st.setUserId(userId.trim());
			if (!map.containsKey(userId)) {
				stations.add(st);
			}
		}

		if (stations.size() == 0) {
			this.setFailMessage("人员都已维护权限岗位！");
			return RESULT_MESSAGE;
		}

		int c = stationService.batchCreateStationUser(stations);

		this.setSuccessMessage("已成功维护" + c + "个人员权限岗位！");

		return RESULT_MESSAGE;
	}

	/**
	 * 删除岗位人员.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "权限岗位人员删除")
	public String deleteStationUser() {
		Station s = new Station();

		if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(id.trim())) {
			s.setId(id.trim());
		} else {
			this.setFailMessage("删除编号不能为空!");
			return RESULT_MESSAGE;
		}

		int r = stationService.deleteStationUser(s);

		if (r > 0) {
			this.setSuccessMessage("权限岗位人员删除成功!");
		} else {
			this.setFailMessage("权限岗位人员删除失败!");
		}

		return RESULT_MESSAGE;
	}

	public IStationService getStationService() {
		return stationService;
	}

	public void setStationService(IStationService stationService) {
		this.stationService = stationService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getListJson() {
		return listJson;
	}

	public void setListJson(String listJson) {
		this.listJson = listJson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
