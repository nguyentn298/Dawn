package com.dante.learn.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ConvertXML {
	public static void main(String[] args) throws IOException {

		ConvertObjectToXml();
//		 ConvertXmlToObject();
	}

	public static void ConvertObjectToXml() throws IOException {
		File file = new File("D:/Nguyen/test/myXml.txt");

		if (!file.exists()) {
			file.createNewFile();
			System.out.println("test");
		}
		User user = new User(1, "nguyen", "gamer");
		User user2 = new User(2, "nguyen22", "gamer222");
		
		Users listUsers = new Users();
		listUsers.setMyList(new ArrayList<User>());
		
		
//		List<User> users = list.getList();
//		users.add(user);
//		users.add(user2);
		
		listUsers.getMyList().add(user);
		listUsers.getMyList().add(user2);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Marshaller jaxbMarshall = jaxbContext.createMarshaller();

			jaxbMarshall.setProperty(jaxbMarshall.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshall.marshal(listUsers, file);
			jaxbMarshall.marshal(listUsers, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ConvertXmlToObject() {
		File file = new File("D:/Nguyen/test/myXml.txt");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
			Unmarshaller jaxbUnmarshall = jaxbContext.createUnmarshaller();

			List<User> users = (List<User>) jaxbUnmarshall.unmarshal(file);

			System.out.println(users);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
