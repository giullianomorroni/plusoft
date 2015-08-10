package com.giullianomorroni.resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Template implements Serializable {

	private static final long serialVersionUID = 8871923520462923723L;

	@XmlElement
	private String id; 
	
	@XmlElement
	private String title;
	
	@XmlElement
	private List<Field> fields = new ArrayList<Field>();

	public Map<String, String> validateNewTemplate() {
		final Map<String, String> errors = new HashMap<String, String>();
		if(this.title == null || this.title.isEmpty()) {
			errors.put("Titulo não informado", "title");
		}
		if(this.fields.size() == 0) {
			errors.put("Ao menos um campo é requerido", "fields");
		}

		for (Field f : this.fields) {
			if(f.getType() == null || f.getType().isEmpty()) {
				errors.put("Tipo não informado", "type");
			}
			if(f.getLabel() == null || f.getLabel().isEmpty()) {
				errors.put("Label não informado", "label");
			}
		}
		return errors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
