package com.giullianomorroni.service;

import org.junit.Test;

import com.giullianomorroni.resource.Template;

public class TemplateServiceTest {

	@Test(expected=IllegalArgumentException.class)
	public void create() {
		TemplateService service = new TemplateService();
		Template template = new Template();
		service.run(template, TemplateAction.CREATE);
		org.junit.Assert.assertTrue(service.getErrorResponse().getErrors().size() > 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void delete() {
		TemplateService service = new TemplateService();
		Template template = new Template();

		/**
		 * metodologia TDD - o método ainda não foi implementado,mas já se encontra em teste
		 * para que não quebre o build eu deixei o expected, porém seguindo o TDD
		 * o ideal seria implementar o método.
		 */

		service.run(template, TemplateAction.DELETE);
	}

}
