package com.dante.learn.swing.sample;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.apache.commons.collections4.map.HashedMap;


@SuppressWarnings("rawtypes")
public class JComboCheckBox extends JComboBox {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	Map<String, Boolean> options = new HashedMap();
	
	public JComboCheckBox() {
		init();
	}

//	@SuppressWarnings("unchecked")
//	public JComboCheckBox(JCheckBox[] items) {
//		super(items);
//		init();
//	}

	@SuppressWarnings("unchecked")
	public JComboCheckBox(Vector items) {
		super(items);
		init();
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		setRenderer(new ComboBoxRenderer());
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				itemSelected();
			}
		});
	}

	public void itemSelected() {
		
		if (getSelectedItem() instanceof JCheckBox) {
			JCheckBox jcb = (JCheckBox) getSelectedItem();
			jcb.setSelected(!jcb.isSelected());
			options.put(jcb.getText(), jcb.isSelected());
		}
	}

	class ComboBoxRenderer implements ListCellRenderer {
		private JLabel label;

		public ComboBoxRenderer() {
			setOpaque(true);
		}

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if (value instanceof Component) {
				Component c = (Component) value;
				if (isSelected) {
					c.setBackground(list.getSelectionBackground());
					c.setForeground(list.getSelectionForeground());
				} else {
					c.setBackground(list.getBackground());
					c.setForeground(list.getForeground());
				}

				return c;
			} else {
				if (label == null) {
					label = new JLabel(value.toString());
				} else {
					label.setText(value.toString());
				}

				return label;
			}
		}
	}

}
