package com.dante.learn.swing.sample;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Main extends JFrame {
	
	Vector v = new Vector();
	public Main() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());
		v.add("Europe");
		v.add(new JCheckBox("Brussels", false));
		v.add(new JCheckBox("Paris", false));
		v.add(new JCheckBox("London", false));
		v.add("United States");
		v.add(new JCheckBox("New York", false));
		v.add(new JCheckBox("Detroit", false));
		v.add(new JCheckBox("San Francisco", false));
		
		JComboCheckBox jcb = new JComboCheckBox(v);

		getContentPane().add(jcb);
		
		
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.setSize(300, 300);
		main.setVisible(true);
	}
}
