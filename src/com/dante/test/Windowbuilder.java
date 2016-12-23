package com.dante.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Windowbuilder extends JPanel{

	private JFrame frame;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pnlRegexTestMain;
	private JTextField txtChooseFile;
	private JTextField txtInputString;
	private JTextField txtInputRegex;
	private JButton btnChooseFile;
	private JButton btnProcessFile;
	private JButton btnProcessSingleLine;
	private JFileChooser fc;
	private JComboBox<String> cbAction;
	private JLabel lblInputRegex;
	private JLabel lblInputString;


	private JLabel lblOutputFileNotify;

	private JLabel lblOutputStrNotify;

	private JTextArea txaLog;

	private JButton btnClearLog;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Windowbuilder window = new Windowbuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Windowbuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1077, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel pnlOperator = new JPanel();
		frame.getContentPane().add(pnlOperator, BorderLayout.NORTH);
		
		GridBagLayout gblRegexTestMain = new GridBagLayout();
		gblRegexTestMain.columnWidths = new int[] { 0, 0 };
		gblRegexTestMain.rowHeights = new int[] { 133, 0, 0 };
		gblRegexTestMain.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gblRegexTestMain.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		pnlOperator.setLayout(gblRegexTestMain);

//		JPanel pnlOperator = new JPanel();
//		GridBagConstraints gbcOperator = new GridBagConstraints();
//		gbcOperator.insets = new Insets(0, 5, 5, 5);
//		gbcOperator.fill = GridBagConstraints.BOTH;
//		gbcOperator.gridx = 0;
//		gbcOperator.gridy = 0;
//		pnlRegexTestMain.add(pnlOperator, gbcOperator);
//		GridBagLayout gblOperator = new GridBagLayout();
//		gblOperator.columnWidths = new int[] { 0, 0 };
//		gblOperator.rowHeights = new int[] { 0, 0 };
//		gblOperator.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
//		gblOperator.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
//		pnlOperator.setLayout(gblOperator);

		JPanel pnlProcess = new JPanel();
		pnlProcess.setBorder(new TitledBorder(null, "Operator",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbcProcess = new GridBagConstraints();
		gbcProcess.fill = GridBagConstraints.BOTH;
		gbcProcess.gridx = 0;
		gbcProcess.gridy = 0;
		pnlOperator.add(pnlProcess, gbcProcess);
		GridBagLayout gblProcessPanel = new GridBagLayout();
		gblProcessPanel.columnWidths = new int[] { 40, 0, 301, 89, 309, 0, 0 };
		gblProcessPanel.rowHeights = new int[] { 14, 22, 14, 22, 14, 22, 14, 0 };
		gblProcessPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gblProcessPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlProcess.setLayout(gblProcessPanel);
		
		JLabel lblInputFile = new JLabel("Input File");
		GridBagConstraints gbcInputFileLabel = new GridBagConstraints();
		gbcInputFileLabel.anchor = GridBagConstraints.NORTHWEST;
		gbcInputFileLabel.insets = new Insets(0, 0, 5, 5);
		gbcInputFileLabel.gridx = 2;
		gbcInputFileLabel.gridy = 0;
		pnlProcess.add(lblInputFile, gbcInputFileLabel);
		
		btnChooseFile = new JButton("Choose");
		GridBagConstraints gbcChooseFileButton = new GridBagConstraints();
		gbcChooseFileButton.fill = GridBagConstraints.HORIZONTAL;
		gbcChooseFileButton.insets = new Insets(0, 0, 5, 5);
		gbcChooseFileButton.gridx = 1;
		gbcChooseFileButton.gridy = 1;
		pnlProcess.add(btnChooseFile, gbcChooseFileButton);
				
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
//				if (e.getSource() == btnChooseFile) {
//					int returnVal = fc.showOpenDialog(SimpleRegexTestPanel.this);
//
//					if (returnVal == JFileChooser.APPROVE_OPTION) {
//						File file = fc.getSelectedFile();
//						String filePath = file.getAbsolutePath();
//						if (filePath.endsWith(".txt")) {
//							txtChooseFile.setText(filePath);
//						} else {
//							JOptionPane.showMessageDialog(null, String.format("Please select txt file!"));
//						}
//
//					}
//				}
			}
		});

		txtChooseFile = new JTextField();
		txtChooseFile.setEnabled(false);
		txtChooseFile.setMargin(new Insets(0, 5, 0, 0));
		GridBagConstraints gbcChooseFileTextField = new GridBagConstraints();
		gbcChooseFileTextField.fill = GridBagConstraints.BOTH;
		gbcChooseFileTextField.insets = new Insets(0, 0, 5, 5);
		gbcChooseFileTextField.gridx = 2;
		gbcChooseFileTextField.gridy = 1;
		pnlProcess.add(txtChooseFile, gbcChooseFileTextField);
		txtChooseFile.setColumns(30);

		btnProcessFile = new JButton("Process");
		GridBagConstraints gbcProcessFileButton = new GridBagConstraints();
		gbcProcessFileButton.fill = GridBagConstraints.HORIZONTAL;
		gbcProcessFileButton.insets = new Insets(0, 15, 5, 15);
		gbcProcessFileButton.gridx = 3;
		gbcProcessFileButton.gridy = 1;
		pnlProcess.add(btnProcessFile, gbcProcessFileButton);

		/**
		 * Process file
		 */

		btnProcessFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				lblOutputFileNotify.setText("");
//				btnProcessFile.setEnabled(false);
//				try {
//					File outputFile = simpleRegexTestProcessor.processFileRegex(txtChooseFile.getText());
//					if (outputFile != null) {
//						lblOutputFileNotify.setText(String.format("Saved output file to '%s'", outputFile.getAbsolutePath()));
//					}
//				} finally {
//					btnProcessFile.setEnabled(true);
//				}
			}
		});

		lblOutputFileNotify = new JLabel();
		GridBagConstraints gbcOutputFileNotifyLabel = new GridBagConstraints();
		gbcOutputFileNotifyLabel.fill = GridBagConstraints.BOTH;
		gbcOutputFileNotifyLabel.insets = new Insets(0, 0, 5, 5);
		gbcOutputFileNotifyLabel.gridx = 4;
		gbcOutputFileNotifyLabel.gridy = 1;
		pnlProcess.add(lblOutputFileNotify, gbcOutputFileNotifyLabel);
		
				JLabel lblInputSingleLine = new JLabel("Action");
				GridBagConstraints gbcInputSingleLineLabel = new GridBagConstraints();
				gbcInputSingleLineLabel.anchor = GridBagConstraints.NORTHWEST;
				gbcInputSingleLineLabel.insets = new Insets(0, 0, 5, 5);
				gbcInputSingleLineLabel.gridx = 1;
				gbcInputSingleLineLabel.gridy = 2;
				pnlProcess.add(lblInputSingleLine, gbcInputSingleLineLabel);
		
		lblInputString = new JLabel("Input String");
		GridBagConstraints gbcInputStringlabel = new GridBagConstraints();
		gbcInputStringlabel.anchor = GridBagConstraints.WEST;
		gbcInputStringlabel.insets = new Insets(0, 0, 5, 5);
		gbcInputStringlabel.gridx = 2;
		gbcInputStringlabel.gridy = 2;
		pnlProcess.add(lblInputString, gbcInputStringlabel);
		
		cbAction = new JComboBox<String>();
		cbAction.setModel(new DefaultComboBoxModel<String>(new String[] {"r", "s"}));
		GridBagConstraints gbcComboBox = new GridBagConstraints();
		gbcComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbcComboBox.insets = new Insets(0, 0, 5, 5);
		gbcComboBox.gridx = 1;
		gbcComboBox.gridy = 3;
		pnlProcess.add(cbAction, gbcComboBox);

		txtInputString = new JTextField();
		txtInputString.setMargin(new Insets(0, 3, 0, 0));
		
		GridBagConstraints gbcSingleLineTextField = new GridBagConstraints();
		gbcSingleLineTextField.fill = GridBagConstraints.BOTH;
		gbcSingleLineTextField.insets = new Insets(0, 0, 5, 5);
		gbcSingleLineTextField.gridx = 2;
		gbcSingleLineTextField.gridy = 3;
		pnlProcess.add(txtInputString, gbcSingleLineTextField);
		txtInputString.setColumns(10);

		btnProcessSingleLine = new JButton("Process");
		GridBagConstraints gbcProcessSingleLineButton = new GridBagConstraints();
		gbcProcessSingleLineButton.fill = GridBagConstraints.HORIZONTAL;
		gbcProcessSingleLineButton.insets = new Insets(0, 15, 5, 15);
		gbcProcessSingleLineButton.gridx = 3;
		gbcProcessSingleLineButton.gridy = 3;
		pnlProcess.add(btnProcessSingleLine, gbcProcessSingleLineButton);

		/**
		 * Process Single line
		 */

		btnProcessSingleLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				lblOutputStrNotify.setText("");
//				btnProcessSingleLine.setEnabled(false);
//				try {
//					String action = cbAction.getSelectedItem().toString();;
//					String inputString = txtInputString.getText();
//					String inputRegex = txtInputRegex.getText();
//					String outputStr = simpleRegexTestProcessor.processSimpleAction(action, inputString, inputRegex);
//					if (outputStr != null) {
//						lblOutputStrNotify.setText(String.format("Result: '%s'", outputStr));
//					}
//				} finally {
//					btnProcessSingleLine.setEnabled(true);
//				}
			}
		});

		lblOutputStrNotify = new JLabel();
		GridBagConstraints gbcOutputStrNotifyLabel = new GridBagConstraints();
		gbcOutputStrNotifyLabel.insets = new Insets(0, 0, 5, 5);
		gbcOutputStrNotifyLabel.fill = GridBagConstraints.BOTH;
		gbcOutputStrNotifyLabel.gridx = 4;
		gbcOutputStrNotifyLabel.gridy = 3;
		pnlProcess.add(lblOutputStrNotify, gbcOutputStrNotifyLabel);
		
		btnClearLog = new JButton("Clear log");
		
		btnClearLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String logFilePath = simpleRegexTestProcessor.getLogFilePath();
//				Lib.clearFileContent(logFilePath);
			}
		});
		
		GridBagConstraints gbcClearLogButton = new GridBagConstraints();
		gbcClearLogButton.insets = new Insets(0, 5, 5, 5);
		gbcClearLogButton.gridx = 3;
		gbcClearLogButton.gridy = 5;
		pnlProcess.add(btnClearLog, gbcClearLogButton);
		
		txtInputRegex = new JTextField();
		txtInputRegex.setMargin(new Insets(0, 3, 0, 0));
		
		lblInputRegex = new JLabel("Input Regex");
		GridBagConstraints gbcInputRegexlabel = new GridBagConstraints();
		gbcInputRegexlabel.anchor = GridBagConstraints.WEST;
		gbcInputRegexlabel.insets = new Insets(0, 0, 5, 5);
		gbcInputRegexlabel.gridx = 2;
		gbcInputRegexlabel.gridy = 4;
		pnlProcess.add(lblInputRegex, gbcInputRegexlabel);
		
		GridBagConstraints gbctxtInputRegex = new GridBagConstraints();
		gbctxtInputRegex.insets = new Insets(0, 0, 5, 5);
		gbctxtInputRegex.fill = GridBagConstraints.BOTH;
		gbctxtInputRegex.gridx = 2;
		gbctxtInputRegex.gridy = 5;
		pnlProcess.add(txtInputRegex, gbctxtInputRegex);
		txtInputRegex.setColumns(10);
		
	}

}
