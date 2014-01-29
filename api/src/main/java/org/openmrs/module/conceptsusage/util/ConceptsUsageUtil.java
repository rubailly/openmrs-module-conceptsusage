package org.openmrs.module.conceptsusage.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.OpenmrsObject;
import org.openmrs.api.context.Context;
import org.openmrs.module.conceptsusage.api.ConceptsUsageService;
import org.openmrs.module.htmlformentry.HtmlForm;
import org.openmrs.module.htmlformentry.HtmlFormEntryService;
import org.openmrs.module.htmlformentry.HtmlFormExporter;


/** 
 * Concept Cleanup utility methods
 */
public class ConceptsUsageUtil {
	/** Logger for this class and subclasses */
	protected final static Log log = LogFactory.getLog(ConceptsUsageUtil.class);
	
	/**
	 * Returns the Concept Cleanup service from the Context
	 * 
	 * @return Concept Cleanup service
	 */
	public static ConceptsUsageService getService() {
		return Context.getService(ConceptsUsageService.class);
	}
	
	/**
	 * Return a list of unretired concepts sorted by concept id ascending and
	 * 
	 * @return a List<Concept> object containing all of the sorted concepts
	 */
	public static List<Concept> getAllConcepts() {
		return Context.getConceptService().getAllConcepts();
	}
	
	/**
	 * Return a list of all the concepts found in all html forms 
	 * 
	 * @return a List<Concept> object containing concepts
	 */
	public static List<Concept> getConceptsInForms() {
		List<Concept> formConcepts = new ArrayList<Concept>();
		List<HtmlForm> forms = Context.getService(HtmlFormEntryService.class)
				.getAllHtmlForms();
		Set<Integer> conceptIds= new TreeSet<Integer>();
		if (forms.size() > 0) {
			for (HtmlForm htmlForm : forms) {
				if (htmlForm != null) {
					HtmlForm form = new HtmlForm();
					form.setXmlData(htmlForm.getXmlData());
					HtmlFormExporter exporter = new HtmlFormExporter(form);
					HtmlForm formClone = exporter
							.export(true, true, true, true);
					Collection<OpenmrsObject> obj = formClone.getDependencies();

					if (obj != null && obj.size() > 0) {
						for (OpenmrsObject openmrsObject : obj) {
							if (openmrsObject != null
									&& openmrsObject instanceof Concept) {
								Concept c = (Concept) openmrsObject;
								if(c.isRetired() == false)
								conceptIds.add(c.getId());
							}

						}
					}
				}

			}
		}
		for (Integer integer : conceptIds) {
			formConcepts.add(Context.getConceptService().getConcept(integer));
		}
		return formConcepts;
	}
	
	/**
	 * Return a list of all the concepts in all tables in the OpenMRS database that have a concept_id foreign key
	 * 
	 * @return a List<Concept> object containing concepts
	 */
	public static List<Concept> getConceptsFromTables() {
		String databaseName =Context.getAdministrationService().getGlobalProperty(ConceptsUsageGlobalProperties.OPENMRS_DATABASE_NAME);
		if(databaseName == "" || databaseName==" " || databaseName == null)
			throw new RuntimeException("Invalid value for global property conceptcleanup.databaseName (Value should be the database name)");
		List<Concept> conceptsFromTables = new ArrayList<Concept>();
		List<Integer> c = getService().getConceptsFromTables(databaseName);
		Set<Integer> conceptIds= new TreeSet<Integer>();
		for (Integer integer : c) {
			if (integer != null) {
				int i = (int) integer;
				Concept concept = Context.getConceptService().getConcept(i);
				if(concept.isRetired() == false)
				conceptIds.add(concept.getConceptId());
			}

		}
		for (Integer integer : conceptIds) {
			conceptsFromTables.add(Context.getConceptService().getConcept(integer));
		}
		return conceptsFromTables;
	}
	
	/**
	 * Return a list of all the concepts for which a conceptId was found in the obs table of the OpenMRS database 
	 * 
	 * @return a List<Concept> object containing concepts
	 */
	public static List<Concept> getAllConceptsFromObs(){
		Set<Integer> conceptIds= new TreeSet<Integer>();
		List<Integer> c = getService().getConceptsFromAllObs();
		List<Concept> obsConcepts = new ArrayList<Concept>();
		for (Integer i : c) {
			conceptIds.add(i);
		}

		for (Integer i : conceptIds) {
			obsConcepts.add(Context.getConceptService().getConcept(i)); 

		}
		return obsConcepts;

	}
	/**
	 * Return a list of all concepts in the OpenMRS database against which observations have not been recorded
	 * 
	 * @return a List<Concept> object containing concepts
	 */
	public static List<Concept> getConceptsNotfoundInObs(){
		List<Concept> c = getAllConcepts();
		List<Concept> o = getAllConceptsFromObs();
		for (Concept concept : o) {
			if(c.contains(concept))
				c.remove(concept);
		}
 		return c;

	}
	
	/**
	 * Return a list of all concepts in the OpenMRS database against which observations have not been recorded and are not used in any of the forms and not found in 
	 * any of the tables with a concept_id foreign key
	 * 
	 * @return a List<Concept> object containing concepts
	 */
	public static List<Concept> getUnusedConcepts(){
		List<Concept> u = new ArrayList<Concept>();
		List<Concept> o = getConceptsNotfoundInObs();
		List<Concept> f  = getConceptsInForms();
		List<Concept> t = getConceptsFromTables();
		for (Concept concept : o) {
			if (!f.contains(concept) && !t.contains(concept)) {
				u.add(concept);
			}
		}
		return u;
		
	}
}
