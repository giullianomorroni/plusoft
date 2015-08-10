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
public class Field implements Serializable {

	private static final long serialVersionUID = -2203788540002310041L;

	@XmlElement
	private String label;

	@XmlElement
	private Boolean required;

	@XmlElement
	private Boolean readOnly;

	@XmlElement
	private String placeHolder;

	@XmlElement
	private Integer maxLenght;

	@XmlElement
	private String type;

	@XmlElement
	private Object defaulfValue;

	@XmlElement
	private List<Option> options = new ArrayList<Option>();
	
	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	public Integer getMaxLenght() {
		return maxLenght;
	}

	public void setMaxLenght(Integer maxLenght) {
		this.maxLenght = maxLenght;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Object getDefaulfValue() {
		return defaulfValue;
	}

	public void setDefaulfValue(Object defaulfValue) {
		this.defaulfValue = defaulfValue;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maxLenght == null) ? 0 : maxLenght.hashCode());
		result = prime * result
				+ ((placeHolder == null) ? 0 : placeHolder.hashCode());
		result = prime * result
				+ ((required == null) ? 0 : required.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (maxLenght == null) {
			if (other.maxLenght != null)
				return false;
		} else if (!maxLenght.equals(other.maxLenght))
			return false;
		if (placeHolder == null) {
			if (other.placeHolder != null)
				return false;
		} else if (!placeHolder.equals(other.placeHolder))
			return false;
		if (required == null) {
			if (other.required != null)
				return false;
		} else if (!required.equals(other.required))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
