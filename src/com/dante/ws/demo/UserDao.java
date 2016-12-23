package com.dante.ws.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	public UserDao() {

	}

	public List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();

		try {
			File file = new File("D:/Nguyen/test/user.txt");
			if (file.exists()) {
				FileInputStream fin = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fin);
				users = (List<User>) in.readObject();
					for (User user : users) {
						System.out.println("Id: " + user.getId());
						System.out.println("Name: " + user.getName());
						System.out.println("Job: " + user.getJob());
						System.out.println("");
					}
				in.close();
			} else {
				User user1 = new User(1, "Dante", "Gamer");
				User user2 = new User(2, "test", "test");
				users.add(user1);
				users.add(user2);
				
				saveUser(users);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	public void saveUser(List<User> users) {
		File file = new File("D:/Nguyen/test/user.txt");
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream out;
			out = new ObjectOutputStream(fout);
			out.writeObject(users);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		userDao.getAllUsers();
	}
}
