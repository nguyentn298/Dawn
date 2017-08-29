package com.dante.learn.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Users {
	/*
	 * <> XmlAccessType (PUBLIC_MEMBER, PROPERTY, FIELD, or NONE)
	 * XmlAccessType.PUBLIC_MEMBER : Allow public field has @XmlElement
	 * XmlAccessType.PROPERTY: 	public fields
								annotated fields
								properties
	 */
//	@XmlElement(name = "user")
	private List<User> mylist = null;

	public List<User> getMyList() {
		return mylist;
	}

	public void setMyList(List<User> mylist) {
		this.mylist = mylist;
	}

}
