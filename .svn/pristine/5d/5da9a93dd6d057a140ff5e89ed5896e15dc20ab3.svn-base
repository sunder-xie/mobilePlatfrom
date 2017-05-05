package com.kintiger.xplatform.api.org;

import java.util.List;

import com.kintiger.xplatform.api.org.bo.Borg;
import com.kintiger.xplatform.framework.bo.BooleanResult;

/**
 * 組織接口.
 * 
 */
public interface IOrgService {

	/**
	 * 根据userId查询所在组织（包括映射组织）含下级 cms.cms_api_pack.fn_user_org_list
	 * 
	 * @param userId
	 * @return
	 */
	List<Borg> getOrgListByUserId(String userId);

	/**
	 * 根据orgId查询所在组织（包括映射组织）
	 * 
	 * @param orgId
	 * @return
	 */
	List<Borg> getOrgTreeListByOrgId(String orgId);

	/**
	 * 根据pOrgId父级组织查询所在组织（包括映射组织）
	 * 
	 * @param pOrgId
	 * @return
	 */
	List<Borg> getOrgTreeListByPorgId(String pOrgId);

	/**
	 * 根据orgId查询组织
	 * 
	 * @param orgId
	 * @return
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
	 * 创建组织
	 * 
	 * @return
	 */
	BooleanResult createOrg(Borg borg);

	/**
	 * 
	 * 更新borg的ad域相关信息 group
	 * 
	 * @param borg
	 * @return BooleanResult
	 */
	BooleanResult updateBorgWithADInfo(Borg borg);

	/**
	 * 
	 * 更新borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	BooleanResult updateBorg(Borg borg);

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
	Borg getFnUserOrg(String userId);

	/**
	 * 根据orgId查询所有上级组织
	 * 
	 * @param orgId
	 * @return List<Borg>
	 */
	List<Borg> getAllParentOrgs(String orgId);

	/**
	 * 获得组织个数
	 * 
	 * @param borg
	 * @return
	 */
	int getOrgCount(Borg borg);

	/**
	 * 获得组织
	 */
	List<Borg> getOrgList(Borg borg);

}
