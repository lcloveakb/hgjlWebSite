package com.senseId.social.dao.impl;

import org.springframework.stereotype.Repository;

import com.senseId.social.dao.DeveloperItemCategoryDao;
import com.senseId.social.entity.DeveloperItemCategory;

@Repository("developerItemCategoryDao")
public class DeveloperItemCategoryDaoImpl extends
		HibernateGenericDaoImpl<DeveloperItemCategory, Integer> implements DeveloperItemCategoryDao {


}
