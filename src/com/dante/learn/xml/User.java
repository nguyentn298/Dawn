package com.dante.learn.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	/*	Field like this: private int id;
	 * 	Property(Get or set method) like this: public int getId() {return id;}
	 * 
	 * <> XmlAccessType (PUBLIC_MEMBER, PROPERTY, FIELD, or NONE)
	 *		- XmlAccessType.PUBLIC_MEMBER :  is the default access type in JAXB, It means that a JAXB implementation will generate bindings for:
										
										* public fields (public String name;)
										* annotated fields ( @XmlAttribute public String name;)
										* properties 
										
	 *		- XmlAccessType.PROPERTY: 	Some JPA implementations inject byte code into the properties to trigger "lazy loading".  
										If you use the FIELD access type your JAXB marshal operation will not bring in this data.
										
										Some JPA implementations inject fields to support such things as change tracking.  
										If you use FIELD access you may find your JAXB implementation complaining about fields that you did not explicitly add to your domain model.
										
										* annotated properties (just work with field)
										
			- XmlAccessType.FIELD: 		Omit the properties to save space.  
										In reality this access type is most useful in scenarios where not all of the fields are exposed through properties.
										
										* annotated fields (just work with property)
										
			- XmlAccessType.NONE: not allow marshall with all field or property
	 */
	
	private int id;
	
	// Don't have set() or get() method
	// It works with XmlAccessType.PUBLIC_MEMBER, and not work with XmlAccessType.PROPERTY
	public String name;
	
	@XmlTransient
	private String job;

	public User() {

	};

	public User(int id, String name, String job) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getName() {
//		return name;
//	}
//
//	@XmlElement
//	public void setName(String name) {
//		this.name = name;
//	}

	public String getJob() {
		return job;
	}

//	@XmlElement
	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return String.format("User[%s - %s - %s]", id, name, job);
	}
}
