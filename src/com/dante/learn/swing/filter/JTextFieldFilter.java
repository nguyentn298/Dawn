package com.dante.learn.swing.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A filter uses for creating a JTextField that only allows specific characters
 * @author JASON
 *
 */
public class JTextFieldFilter extends PlainDocument {
	private static final long serialVersionUID = 1L;

	public static final String NUMERIC = "0123456789";

	protected String acceptedChars = null;

	protected int maxLength = 10;

	public JTextFieldFilter() {
		this(NUMERIC);
	}

	/**
	 * 
	 * @param acceptedchars Characters is allowed for input 
	 */
	public JTextFieldFilter(String acceptedchars) {
		acceptedChars = acceptedchars;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if ((super.getLength() + str.length()) > maxLength) {
			return;
		}

		for (int i = 0; i < str.length(); i++) {
			if (acceptedChars.indexOf(String.valueOf(str.charAt(i))) == -1) {
				return;
			}
		}
		super.insertString(offset, str, attr);
	}
}