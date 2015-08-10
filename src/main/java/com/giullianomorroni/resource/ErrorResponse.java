package com.giullianomorroni.resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 7508697358498306364L;

	@XmlElement
	private List<Error> errors = new ArrayList<Error>();

	public ErrorResponse() {
		super();
	}
	
	public ErrorResponse(Exception e) {
		errors.add(new Error(e.getMessage(), "system"));
	}
	
	public ErrorResponse add(String msg,String ctg) {
		errors.add(new Error(msg, ctg));
		return this;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}

class Error {
	
	private String message;
	private String category;
	
	public Error(String message, String category) {
		super();
		this.message = message;
		this.category = category;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}