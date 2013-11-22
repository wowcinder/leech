package com.voole.leech.dao.authorize;

import java.util.List;
import java.util.Set;

import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.authorize.AuthorizeGroup;

public interface AuthorizeDao {
	Set<String> getAuthorizeTokens(Integer uid);

	/**
	 * @param group
	 * @return
	 */
	Integer queryAuthorizeGroupIdByName(String group);

	/**
	 * @param ag
	 * @return
	 */
	AuthorizeGroup saveAuthorizeGroup(AuthorizeGroup ag);

	/**
	 * @param id
	 * @param value
	 * @return
	 */
	Authorize queryAuthorizeIdByName(Integer id, String value);

	/**
	 * @param authority
	 */
	Authorize saveAuthorize(Authorize authority);

	/**
	 * @return
	 */
	List<Authorize> getAllocatenbeAuthorizes();
}
