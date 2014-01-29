package org.openmrs.module.conceptsusage.api.impl;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface ConceptsUsageService extends OpenmrsService{

	 public List<Integer> getConceptsFromTables(String databaseName);
	 public List<Integer> getConceptsFromAllObs();
}
