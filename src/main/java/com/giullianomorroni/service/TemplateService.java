package com.giullianomorroni.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.giullianomorroni.dao.TemplateRepository;
import com.giullianomorroni.resource.ErrorResponse;
import com.giullianomorroni.resource.Template;

@Resource
public class TemplateService implements Serializable {

	private static final long serialVersionUID = -1255565038103190396L;

	private List<Template> templates;
	private ErrorResponse errorResponse;
	private Template template;

	private TemplateRepository repository;

	public TemplateService() {
		repository = new TemplateRepository();
	}
	
	public TemplateService run(Template template, TemplateAction action){
		switch (action) {
		case LIST: list(); break;
		case FIND: find(template.getId()); break;
		case CREATE: create(template); break;
		default: throw new IllegalArgumentException("Operacao nao encontrada");
		}
		return this;
	}

	public TemplateService run(TemplateAction action){
		return run(null, action);
	}

	private void list() {
		this.templates = repository.list();
	}

	private void find(String id) {
		this.template = repository.find(id);
	}
	
	private Template create(Template template) {
		Map<String, String> errors = template.validateNewTemplate();
		if (errors.size() > 0) {
			for (String k : errors.keySet()) {
				this.errorResponse = new ErrorResponse();
				this.errorResponse.add(k, errors.get(k));
			}
			throw new IllegalArgumentException();
		}

		this.repository.create(template);
		return null;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public Template getTemplate() {
		return template;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}


}
