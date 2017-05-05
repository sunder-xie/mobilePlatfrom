package com.kintiger.xplatform.org.dao;

import java.util.List;

import com.kintiger.xplatform.api.org.bo.Borg;

public interface IOrgDao {
	/**
	 * 根据userId查询所在组织（包括映射组织）
	 * 
	 * @param userId
	 * @return
	 */
	List<Borg> getOrgListByUserId(String userId);

	/**
	 * 根据pOrgId查询所在组织（包括映射组织）
	 * 
	 * @param pOrgId
	 * @return List<Borg>
	 */
	List<Borg> getOrgTreeListByPorgId(String pOrgId);

	/**
	 * 根据userId查询所在组织（包括映射组织）
	 * 
	 * @param orgId
	 * @return List<Borg>
	 */
	List<Borg> getOrgTreeListByOrgId(String orgId);

	/**
	 * 根据orgId查询组织（
	 * 
	 * @param orgId
	 * @return Borg
	 */

	Borg getOrgByOrgId(String orgId);

	/**
	 * 根据org查公司list条数
	 * 
	 * @return
	 */
	int getCompanyListCount(Borg borg);

	/**
	 * 根据org查公司list
	 * 
	 * @return
	 */
	List<Borg> getCompanyList(Borg borg);

	/**
	 * 根据org查公司
	 * 
	 * @return
	 */
	Borg getCompanyName(Borg borg);

	/**
	 * 创建组织
	 * 
	 * @return
	 */
	Long createOrg(Borg org);

	/**
	 * 
	 * 更新borg
	 * 
	 * @param borg
	 * @return
	 */
	int updateBorg(Borg borg);

	/**
	 * 根据userId查询所在组织
	 * 
	 * @param userId
	 * @return Borg
	 */
	Borg getOrgByUserId(String userId);

	/**
	 * cms.cms_api_pack.fn_user_org
	 * 
	 * @param userId
	 * @return
	 */
	String getFnUserOrg(String userId);

	/**
	 * 根据orgId查询所有上级组织
	 * 
	 * @param orgId
	 * @return List<Borg>
	 */
	List<Borg> getAllParentOrgs(String orgId);

	/**
	 * 
	 * @param borg
	 * @return
	 */
	int getOrgCount(Borg borg);

	/**
	 * 
	 * @param borg
	 * @return
	 */
	List<Borg> getOrgList(Borg borg);

}
