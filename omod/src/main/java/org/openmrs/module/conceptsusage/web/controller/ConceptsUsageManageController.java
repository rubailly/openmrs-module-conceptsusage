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
package org.openmrs.module.conceptsusage.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.module.conceptsusage.util.ConceptsUsageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The main controller.
 */
@Controller
public class  ConceptsUsageManageController {
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	@RequestMapping("/module/conceptsusage/show_ConceptList")
	public String showConceptListForm(ModelMap map,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		map.addAttribute("allConcepts", ConceptsUsageUtil.getAllConcepts());
		map.addAttribute("formConcepts",  ConceptsUsageUtil.getConceptsInForms());
		map.addAttribute("tableConcepts", ConceptsUsageUtil.getConceptsFromTables());
		map.addAttribute("unusedConcepts", ConceptsUsageUtil.getUnusedConcepts());
		return "/module/conceptsusage/conceptListForm";
	}



	@RequestMapping("/module/conceptsusage/show_unusedConcepts")
	public String showUnusedConceptsForm(ModelMap map,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		map.addAttribute("unusedConcepts", ConceptsUsageUtil.getUnusedConcepts());
		return "/module/conceptsusage/unusedConceptsListForm";
	}
	
	@RequestMapping("/module/conceptsusage/show_usedConcepts")
	public String showusedConceptsForm(ModelMap map,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Concept> usedConcepts = ConceptsUsageUtil.getAllConcepts();
		List<Concept> unusedConcepts = ConceptsUsageUtil.getUnusedConcepts();
		for (Concept concept : unusedConcepts) {
			usedConcepts.remove(concept);
		}
		map.addAttribute("usedConcepts", usedConcepts);
		
		return "/module/conceptsusage/usedConceptsListForm";
	}
}
