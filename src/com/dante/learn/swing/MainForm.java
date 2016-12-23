package com.dante.learn.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

//import com.loanlogics.amazonsimpleworkflowpoc.constants.UIConstants;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.SwfActivityManager;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.SwfWorkflowExecutionManager;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.SwfWorkflowManager;
//import com.pagosoft.plaf.PlafOptions;

public class MainForm {
	private AmazonSWFPanel amazonSWFPanel;
//	private WorkflowExecutionPanel workflowExcutionPanel;

	private JFrame formAmazonSWF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// dowload lib com.pagosoft.plaf.PlafOptions 
//		PlafOptions.setAsLookAndFeel();
//		PlafOptions.updateAllUIs();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainForm window = new MainForm();
				window.formAmazonSWF.setVisible(true);
				window.formAmazonSWF.setLocationRelativeTo(null);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		formAmazonSWF = new JFrame();
//		formAmazonSWF.setTitle(UIConstants.MainForm.MAIN_FORM_TITLE);
		formAmazonSWF.setBounds(100, 100, 954, 668);
		formAmazonSWF.setMinimumSize(new Dimension(1000, 500));
		formAmazonSWF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formAmazonSWF.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		formAmazonSWF.getContentPane().add(tabbedPane, BorderLayout.CENTER);

//		workflowExcutionPanel = new WorkflowExecutionPanel();
		
		amazonSWFPanel = new AmazonSWFPanel(this);
		
//		tabbedPane.addTab(UIConstants.MainForm.AMAZON_SWF_TAB, null, amazonSWFPanel, null);
//		tabbedPane.addTab(UIConstants.MainForm.WORK_FLOW_EXECUTION, null, workflowExcutionPanel, null);
		
		formAmazonSWF.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//				SwfWorkflowManager.getInstance().shutdown();
//				SwfActivityManager.getInstance().shutdown();
//				SwfWorkflowExecutionManager.getInstance().shutdown();
			}
		});
	}

//	public WorkflowExecutionPanel getWorkflowExcutionPanel() {
//		return workflowExcutionPanel;
//	}

	
}