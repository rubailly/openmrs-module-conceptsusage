/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.conceptsusage.api.db.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.conceptsusage.api.db.ConceptsUsageDAO;

/**
 * It is a default implementation of  {@link ConceptsUsageDAO}.
 */
public class HibernateConceptsUsageDAO implements ConceptsUsageDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * Hibernate session factory
	 */
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//We'll probably make a global property for the database name.
	@Override
	public List<Integer> getConceptsFromTables(String databaseName) {
		String sql = "select TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME='concept_id' and TABLE_SCHEMA = '"+databaseName+"' and TABLE_NAME not like 'concept%' ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> tableNames = query.list();
		List<Integer> conceptIds = new ArrayList<Integer>();
		for (String tableName : tableNames) {
			String sql1 = "select distinct concept_id from "+tableName;
			Query query1 = sessionFactory.getCurrentSession().createSQLQuery(sql1);
			@SuppressWarnings("unchecked")
			List<Integer> concepts = query1.list();
			conceptIds.addAll(concepts);
		}
		return conceptIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getConceptsFromAllObs() {
		String sql = "select distinct concept_id from obs";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

}
