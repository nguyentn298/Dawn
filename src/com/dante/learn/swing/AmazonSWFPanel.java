package com.dante.learn.swing;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class AmazonSWFPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtAccessID;
	private JTextField txtSecretKey;
	private JTextField txtDomainName;
	private JTextField txtServiceEndpoint;
	private JTextField txtVersion;
	private JTable tableWorkFlowInstances;
	private JTable tblActivitiesInstances;
	private JPanel pnlWorkFlowMain;
	private JPanel pnlTop;
	private JPanel pnlMid;
	private JTabbedPane tabInfo;
	private JScrollPane scrWorkflowInstances;
	private JScrollPane scrActivitiesInstances;
//	private AmazonSWFTableModel amazonSWFTableModel;
//	private AmazonActivityTableModel amazonActivityTableModel;
	private JButton btnReset;
	private JButton btnWorkFlowAdd;
	private JButton btnWorkFlowRemove;
	private JButton buttonWfClearLog;
	private JButton btnActivitiesAdd;
	private JButton btnActivitiesRemove;
	private JButton btnActClearLogButton;
	private JSpinner spnWorkFlowInstances;
	private JSpinner spinnerActivities;
	private JTextArea txaWorkflowLog;
	private JTextArea txaActivitiesLog;
	private JButton btnClearAllLog;

	private MainForm mainForm;
	/**
	 * Create the application.
	 */
	public AmazonSWFPanel(MainForm mainForm) {
		this.mainForm = mainForm;
		setLayout(new BorderLayout(0, 0));

		pnlWorkFlowMain = new JPanel();
		add(pnlWorkFlowMain, BorderLayout.CENTER);
		GridBagLayout gblStatistics = new GridBagLayout();
		gblStatistics.columnWidths = new int[]{789, 102, 0};
		gblStatistics.rowHeights = new int[]{75, 25, 0, 0};
		gblStatistics.columnWeights = new double[]{0.0, 1.0, 0.0};
		gblStatistics.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnlWorkFlowMain.setLayout(gblStatistics);

		/**
		 *	init panel
		 */

		createInformationOfSWF();
		CreateWofkflowTab();
		createActivitiesTab();

		/**
		 *	init workflow data table
		 */

		fillDataToWorkflowTableModel();

		initStacticWorkflowTable();

		/**
		 *	init activity data table
		 */

//		fillDataToActivityTableModel();

		initStacticActivityTable();

		resetButton();

		/**
		 *	Workflow button
		 */

		addWofkflowButton();
		
		removeWofkflowButton();
		
		/**
		 *	Activity button
		 */
		
		addActivityButton();
		
		removeActivityButton();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void createInformationOfSWF() {
		pnlTop = new JPanel();
		pnlTop.setBorder(new TitledBorder(null, "SWF Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		GridBagConstraints gbcTop = new GridBagConstraints();
		UIHelper.createGridBagConstraints(gbcTop, new Insets(0, 5, 0, 5), 0, 0, GridBagConstraints.BOTH, null, 2, null);
		pnlWorkFlowMain.add(pnlTop, gbcTop);
		
		GridBagLayout gblTop = new GridBagLayout();
		gblTop.columnWidths = new int[]{80, 300, 20, 200, 20, 61, 20, 0, 0};
		gblTop.rowHeights =  new int[]{14, 20, 14, 23, 0};
		gblTop.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gblTop.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlTop.setLayout(gblTop);
		
		JLabel lblAccessKey = new JLabel("Access Id");
		GridBagConstraints gbcAccessKeyLabel = new GridBagConstraints();
		gbcAccessKeyLabel.anchor = GridBagConstraints.NORTHWEST;
		gbcAccessKeyLabel.insets = new Insets(0, 0, 5, 5);
		gbcAccessKeyLabel.gridx = 1;
		gbcAccessKeyLabel.gridy = 0;
		pnlTop.add(lblAccessKey, gbcAccessKeyLabel);
		
		JLabel lblServiceEndpoint = new JLabel("Service Endpoint");
		GridBagConstraints gbcServiceEnpointLabel = new GridBagConstraints();
		gbcServiceEnpointLabel.anchor = GridBagConstraints.NORTHWEST;
		gbcServiceEnpointLabel.insets = new Insets(0, 0, 5, 5);
		gbcServiceEnpointLabel.gridx = 3;
		gbcServiceEnpointLabel.gridy = 0;
		pnlTop.add(lblServiceEndpoint, gbcServiceEnpointLabel);
		
		JLabel lblVersion = new JLabel("Version");
		GridBagConstraints gbcVersionLabel = new GridBagConstraints();
		gbcVersionLabel.anchor = GridBagConstraints.NORTHWEST;
		gbcVersionLabel.insets = new Insets(0, 0, 5, 0);
		gbcVersionLabel.gridx = 7;
		gbcVersionLabel.gridy = 0;
		pnlTop.add(lblVersion, gbcVersionLabel);
		
		txtAccessID = new JTextField();
		GridBagConstraints gbcAccessIDTextField = new GridBagConstraints();
		gbcAccessIDTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcAccessIDTextField.insets = new Insets(0, 0, 5, 5);
		gbcAccessIDTextField.gridx = 1;
		gbcAccessIDTextField.gridy = 1;
		pnlTop.add(txtAccessID, gbcAccessIDTextField);
		txtAccessID.setColumns(35);
		
		txtServiceEndpoint = new JTextField();
		txtServiceEndpoint.setColumns(35);
		GridBagConstraints gbcServiceEndpointtxt = new GridBagConstraints();
		gbcServiceEndpointtxt.fill = GridBagConstraints.HORIZONTAL;
		gbcServiceEndpointtxt.insets = new Insets(0, 0, 5, 5);
		gbcServiceEndpointtxt.gridwidth = 3;
		gbcServiceEndpointtxt.gridx = 3;
		gbcServiceEndpointtxt.gridy = 1;
		pnlTop.add(txtServiceEndpoint, gbcServiceEndpointtxt);
		
		txtVersion = new JTextField();
		txtVersion.setColumns(10);
		GridBagConstraints gbcVersionTextField = new GridBagConstraints();
		gbcVersionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcVersionTextField.insets = new Insets(0, 0, 5, 0);
		gbcVersionTextField.gridx = 7;
		gbcVersionTextField.gridy = 1;
		pnlTop.add(txtVersion, gbcVersionTextField);
		
		JLabel lblSecretKey = new JLabel("Secret Key");
		GridBagConstraints gbcSecretKeyLabel = new GridBagConstraints();
		gbcSecretKeyLabel.anchor = GridBagConstraints.NORTHWEST;
		gbcSecretKeyLabel.insets = new Insets(0, 0, 5, 5);
		gbcSecretKeyLabel.gridx = 1;
		gbcSecretKeyLabel.gridy = 2;
		pnlTop.add(lblSecretKey, gbcSecretKeyLabel);
		
		JLabel lblDomainName = new JLabel("Domain Name");
		GridBagConstraints gbcDomainNameLabel = new GridBagConstraints();
		gbcDomainNameLabel.anchor = GridBagConstraints.NORTHWEST;
		gbcDomainNameLabel.insets = new Insets(0, 0, 5, 5);
		gbcDomainNameLabel.gridx = 3;
		gbcDomainNameLabel.gridy = 2;
		pnlTop.add(lblDomainName, gbcDomainNameLabel);
		
		txtSecretKey = new JTextField();
		txtSecretKey.setColumns(35);
		GridBagConstraints gbcSecretKeytxt = new GridBagConstraints();
		gbcSecretKeytxt.fill = GridBagConstraints.HORIZONTAL;
		gbcSecretKeytxt.insets = new Insets(0, 0, 0, 5);
		gbcSecretKeytxt.gridx = 1;
		gbcSecretKeytxt.gridy = 3;
		pnlTop.add(txtSecretKey, gbcSecretKeytxt);
		
		txtDomainName = new JTextField();
		txtDomainName.setColumns(35);
		GridBagConstraints gbcDomainNameTextField = new GridBagConstraints();
		gbcDomainNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcDomainNameTextField.insets = new Insets(0, 0, 5, 5);
		gbcDomainNameTextField.gridx = 3;
		gbcDomainNameTextField.gridy = 3;
		pnlTop.add(txtDomainName, gbcDomainNameTextField);
		
		btnReset = new JButton("Reset");
		GridBagConstraints gbcResetButton = new GridBagConstraints();
		gbcResetButton.anchor = GridBagConstraints.NORTH;
		gbcResetButton.fill = GridBagConstraints.HORIZONTAL;
		gbcResetButton.insets = new Insets(0, 0, 0, 5);
		gbcResetButton.gridx = 5;
		gbcResetButton.gridy = 3;
		pnlTop.add(btnReset, gbcResetButton);
		
		btnClearAllLog = new JButton("Clear All Log");
		btnClearAllLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				String logFilePath = swfWorkflowManager.getLogFilePath();
//				if (StringUtils.isNotEmpty(logFilePath)) {
//					Lib.clearFileContent(logFilePath);
//				}
//				swfWorkflowManager.clearAllSwfInstanceLog();
//				swfActivityManager.clearAllSwfInstanceLog();
//				SwfWorkflowExecutionManager.getInstance().clearAllSwfInstanceLog();
				txaWorkflowLog.setText("");
				txaActivitiesLog.setText("");
			}
		});
		GridBagConstraints gbcClearAllLogButton = new GridBagConstraints();
		gbcClearAllLogButton.anchor = GridBagConstraints.NORTH;
		gbcClearAllLogButton.fill = GridBagConstraints.HORIZONTAL;
		gbcClearAllLogButton.gridx = 7;
		gbcClearAllLogButton.gridy = 3;
		pnlTop.add(btnClearAllLog, gbcClearAllLogButton);
		
		setDefaultSWFConfigValues();
	}
	private void CreateWofkflowTab() {
		
		pnlMid = new JPanel();
		GridBagConstraints gbcMid = new GridBagConstraints();
		UIHelper.createGridBagConstraints(gbcMid, new Insets(-18, 6, 3, 6), 0, 2, GridBagConstraints.BOTH, null, 2, null);
		pnlWorkFlowMain.add(pnlMid, gbcMid);
		GridBagLayout gblMidPanel = new GridBagLayout();
		gblMidPanel.columnWidths = new int[]{0, 0};
		gblMidPanel.rowHeights = new int[]{0, 0};
		gblMidPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblMidPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlMid.setLayout(gblMidPanel);
		
		tabInfo = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbctabInfo = new GridBagConstraints();
		gbctabInfo.fill = GridBagConstraints.BOTH;
		gbctabInfo.gridx = 0;
		gbctabInfo.gridy = 0;
		pnlMid.add(tabInfo, gbctabInfo);
		
		JPanel pnlWorkflow = new JPanel();
		tabInfo.addTab("Workflow", null, pnlWorkflow, null);
		GridBagLayout gblWorkflowPanel = new GridBagLayout();
		gblWorkflowPanel.columnWidths = new int[]{0, 0};
		gblWorkflowPanel.rowHeights = new int[]{56, 0, 0};
		gblWorkflowPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblWorkflowPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		pnlWorkflow.setLayout(gblWorkflowPanel);
		
		JPanel pnlWorkflowExecution = new JPanel();
		pnlWorkflowExecution.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operator Of Workflow", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbcWorkflowExecutionPanel = new GridBagConstraints();
		gbcWorkflowExecutionPanel.insets = new Insets(0, 0, 5, 0);
		gbcWorkflowExecutionPanel.fill = GridBagConstraints.BOTH;
		gbcWorkflowExecutionPanel.gridx = 0;
		gbcWorkflowExecutionPanel.gridy = 0;
		pnlWorkflow.add(pnlWorkflowExecution, gbcWorkflowExecutionPanel);
		GridBagLayout gblWorkflowExecutionPanel = new GridBagLayout();
		gblWorkflowExecutionPanel.columnWidths = new int[]{20, 0, 70, 0, 0, 0, 0, 0};
		gblWorkflowExecutionPanel.rowHeights = new int[]{0, 0};
		gblWorkflowExecutionPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gblWorkflowExecutionPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlWorkflowExecution.setLayout(gblWorkflowExecutionPanel);
		
		JLabel lblWorkFlowInstances = new JLabel("Workflow Instances");
		GridBagConstraints gbcWorkFlowInstancesLabel = new GridBagConstraints();
		gbcWorkFlowInstancesLabel.insets = new Insets(0, 0, 0, 5);
		gbcWorkFlowInstancesLabel.gridx = 1;
		gbcWorkFlowInstancesLabel.gridy = 0;
		pnlWorkflowExecution.add(lblWorkFlowInstances, gbcWorkFlowInstancesLabel);
		
		spnWorkFlowInstances = new JSpinner();
		spnWorkFlowInstances.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbcWorkFlowInstancesSpinner = new GridBagConstraints();
		gbcWorkFlowInstancesSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbcWorkFlowInstancesSpinner.insets = new Insets(0, 0, 0, 5);
		gbcWorkFlowInstancesSpinner.gridx = 2;
		gbcWorkFlowInstancesSpinner.gridy = 0;
		pnlWorkflowExecution.add(spnWorkFlowInstances, gbcWorkFlowInstancesSpinner);
		
		btnWorkFlowAdd = new JButton("Add More Workflow");
		
		GridBagConstraints gbcWorkFlowAdd = new GridBagConstraints();
		gbcWorkFlowAdd.insets = new Insets(0, 0, 0, 5);
		gbcWorkFlowAdd.gridx = 4;
		gbcWorkFlowAdd.gridy = 0;
		pnlWorkflowExecution.add(btnWorkFlowAdd, gbcWorkFlowAdd);
		
		btnWorkFlowRemove = new JButton("Remove Workflow");
		
		GridBagConstraints gbcWorkFlowRemove = new GridBagConstraints();
		gbcWorkFlowRemove.insets = new Insets(0, 0, 0, 5);
		gbcWorkFlowRemove.gridx = 6;
		gbcWorkFlowRemove.gridy = 0;
		pnlWorkflowExecution.add(btnWorkFlowRemove, gbcWorkFlowRemove);
		
		buttonWfClearLog = new JButton("Clear Log");
		buttonWfClearLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				swfWorkflowManager.clearAllSwfInstanceLog();
				txaWorkflowLog.setText("");
			}
		});
		
		GridBagConstraints gbcClearLogWfButton = new GridBagConstraints();
		gbcClearLogWfButton.gridx = 8;
		gbcClearLogWfButton.gridy = 0;
		pnlWorkflowExecution.add(buttonWfClearLog, gbcClearLogWfButton);
		
		JPanel pnlWorkFlowInfo = new JPanel();
		GridBagConstraints gbcWorkFlowInfoPanel = new GridBagConstraints();
		gbcWorkFlowInfoPanel.fill = GridBagConstraints.BOTH;
		gbcWorkFlowInfoPanel.gridx = 0;
		gbcWorkFlowInfoPanel.gridy = 1;
		pnlWorkflow.add(pnlWorkFlowInfo, gbcWorkFlowInfoPanel);
		GridBagLayout gblWorkFlowInfoPanel = new GridBagLayout();
		gblWorkFlowInfoPanel.columnWidths = new int[]{0, 0};
		gblWorkFlowInfoPanel.rowHeights = new int[]{0, 0};
		gblWorkFlowInfoPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblWorkFlowInfoPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlWorkFlowInfo.setLayout(gblWorkFlowInfoPanel);
		
		JSplitPane splWorkFlowInfo = new JSplitPane();
		splWorkFlowInfo.setResizeWeight(1.0);
		splWorkFlowInfo.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbcWorkFlowInfoSpinner = new GridBagConstraints();
		gbcWorkFlowInfoSpinner.fill = GridBagConstraints.BOTH;
		gbcWorkFlowInfoSpinner.gridx = 0;
		gbcWorkFlowInfoSpinner.gridy = 0;
		pnlWorkFlowInfo.add(splWorkFlowInfo, gbcWorkFlowInfoSpinner);
		
		JPanel pnlWorkFlowTable = new JPanel();
		splWorkFlowInfo.setLeftComponent(pnlWorkFlowTable);
		GridBagLayout gblWorkFlowTablePanel = new GridBagLayout();
		gblWorkFlowTablePanel.columnWidths = new int[]{1, 0};
		gblWorkFlowTablePanel.rowHeights = new int[]{1, 0};
		gblWorkFlowTablePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblWorkFlowTablePanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlWorkFlowTable.setLayout(gblWorkFlowTablePanel);
		
		scrWorkflowInstances = new JScrollPane();
		GridBagConstraints gbcWorkflowInstancesScrollPane = new GridBagConstraints();
		gbcWorkflowInstancesScrollPane.fill = GridBagConstraints.BOTH;
		gbcWorkflowInstancesScrollPane.gridx = 0;
		gbcWorkflowInstancesScrollPane.gridy = 0;
		pnlWorkFlowTable.add(scrWorkflowInstances, gbcWorkflowInstancesScrollPane);
		
		JPanel pnlWorkFlowLog = new JPanel();
		pnlWorkFlowLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log Workflow", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splWorkFlowInfo.setRightComponent(pnlWorkFlowLog);
		GridBagLayout gblWorkFlowLogPanel = new GridBagLayout();
		gblWorkFlowLogPanel.columnWidths = new int[]{460, 0};
		gblWorkFlowLogPanel.rowHeights = new int[]{202, 0};
		gblWorkFlowLogPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblWorkFlowLogPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlWorkFlowLog.setLayout(gblWorkFlowLogPanel);
		
		JScrollPane scrWorkflowLog = new JScrollPane();
		GridBagConstraints gbcWorkflowLogScrollPane = new GridBagConstraints();
		gbcWorkflowLogScrollPane.fill = GridBagConstraints.BOTH;
		gbcWorkflowLogScrollPane.gridx = 0;
		gbcWorkflowLogScrollPane.gridy = 0;
		pnlWorkFlowLog.add(scrWorkflowLog, gbcWorkflowLogScrollPane);
		
		txaWorkflowLog = new JTextArea();
		scrWorkflowLog.setViewportView(txaWorkflowLog);
		
	}
	
	private void createActivitiesTab() {
		JPanel pnlActivities = new JPanel();
		tabInfo.addTab("Activities", null, pnlActivities, null);
		GridBagLayout gblActivitiesPanel = new GridBagLayout();
		gblActivitiesPanel.columnWidths = new int[]{0, 0};
		gblActivitiesPanel.rowHeights = new int[]{56, 0, 0};
		gblActivitiesPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblActivitiesPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		pnlActivities.setLayout(gblActivitiesPanel);
		
		JPanel pnlActivitiesExecution = new JPanel();
		pnlActivitiesExecution.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operator Of Activities", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbcActivitiesExecutionPanel = new GridBagConstraints();
		gbcActivitiesExecutionPanel.insets = new Insets(0, 0, 5, 0);
		gbcActivitiesExecutionPanel.fill = GridBagConstraints.BOTH;
		gbcActivitiesExecutionPanel.gridx = 0;
		gbcActivitiesExecutionPanel.gridy = 0;
		pnlActivities.add(pnlActivitiesExecution, gbcActivitiesExecutionPanel);
		GridBagLayout gblActivitiesExecutionPanel = new GridBagLayout();
		gblActivitiesExecutionPanel.columnWidths = new int[]{20, 0, 70, 0, 0, 0, 0, 0};
		gblActivitiesExecutionPanel.rowHeights = new int[]{0, 0};
		gblActivitiesExecutionPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gblActivitiesExecutionPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlActivitiesExecution.setLayout(gblActivitiesExecutionPanel);
		
		JLabel lblActivitesInstances = new JLabel("Activites Instances");
		GridBagConstraints gbcActivitesInstancesLabel = new GridBagConstraints();
		gbcActivitesInstancesLabel.insets = new Insets(0, 0, 0, 5);
		gbcActivitesInstancesLabel.gridx = 1;
		gbcActivitesInstancesLabel.gridy = 0;
		pnlActivitiesExecution.add(lblActivitesInstances, gbcActivitesInstancesLabel);
		
		spinnerActivities = new JSpinner();
		spinnerActivities.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbcActivitiesSpinner = new GridBagConstraints();
		gbcActivitiesSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbcActivitiesSpinner.insets = new Insets(0, 0, 0, 5);
		gbcActivitiesSpinner.gridx = 2;
		gbcActivitiesSpinner.gridy = 0;
		pnlActivitiesExecution.add(spinnerActivities, gbcActivitiesSpinner);
		
		btnActivitiesAdd = new JButton("Add More Activities");

		GridBagConstraints gbcActivitiesAdd = new GridBagConstraints();
		gbcActivitiesAdd.insets = new Insets(0, 0, 0, 5);
		gbcActivitiesAdd.gridx = 4;
		gbcActivitiesAdd.gridy = 0;
		pnlActivitiesExecution.add(btnActivitiesAdd, gbcActivitiesAdd);
		
		btnActivitiesRemove = new JButton("Remove Activities");
		
		GridBagConstraints gbcActivitiesRemove = new GridBagConstraints();
		gbcActivitiesRemove.insets = new Insets(0, 0, 0, 5);
		gbcActivitiesRemove.gridx = 6;
		gbcActivitiesRemove.gridy = 0;
		pnlActivitiesExecution.add(btnActivitiesRemove, gbcActivitiesRemove);
		
		btnActClearLogButton = new JButton("Clear Log");
		btnActClearLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				swfActivityManager.clearAllSwfInstanceLog();
				txaActivitiesLog.setText("");
			}
		});
		
		GridBagConstraints gbcClearLogActivityButton = new GridBagConstraints();
		gbcClearLogActivityButton.gridx = 8;
		gbcClearLogActivityButton.gridy = 0;
		pnlActivitiesExecution.add(btnActClearLogButton, gbcClearLogActivityButton);
		
		JPanel pnlActivitiesInfo = new JPanel();
		GridBagConstraints gbcActivitiesInfoPanel = new GridBagConstraints();
		gbcActivitiesInfoPanel.fill = GridBagConstraints.BOTH;
		gbcActivitiesInfoPanel.gridx = 0;
		gbcActivitiesInfoPanel.gridy = 1;
		pnlActivities.add(pnlActivitiesInfo, gbcActivitiesInfoPanel);
		GridBagLayout gblActivitiesInfoPanel = new GridBagLayout();
		gblActivitiesInfoPanel.columnWidths = new int[]{0, 0};
		gblActivitiesInfoPanel.rowHeights = new int[]{0, 0};
		gblActivitiesInfoPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblActivitiesInfoPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlActivitiesInfo.setLayout(gblActivitiesInfoPanel);
		
		JSplitPane splActivitiesInfo = new JSplitPane();
		splActivitiesInfo.setResizeWeight(1.0);
		splActivitiesInfo.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbcActivitiesInfoSplitPane = new GridBagConstraints();
		gbcActivitiesInfoSplitPane.fill = GridBagConstraints.BOTH;
		gbcActivitiesInfoSplitPane.gridx = 0;
		gbcActivitiesInfoSplitPane.gridy = 0;
		pnlActivitiesInfo.add(splActivitiesInfo, gbcActivitiesInfoSplitPane);
		
		JPanel pnlActivitiesTable = new JPanel();
		splActivitiesInfo.setLeftComponent(pnlActivitiesTable);
		GridBagLayout gbllActivitiesTablePanel = new GridBagLayout();
		gbllActivitiesTablePanel.columnWidths = new int[]{1, 0};
		gbllActivitiesTablePanel.rowHeights = new int[]{1, 0};
		gbllActivitiesTablePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbllActivitiesTablePanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlActivitiesTable.setLayout(gbllActivitiesTablePanel);
		
		scrActivitiesInstances = new JScrollPane();
		GridBagConstraints gbcActivitiesInstances = new GridBagConstraints();
		gbcActivitiesInstances.fill = GridBagConstraints.BOTH;
		gbcActivitiesInstances.gridx = 0;
		gbcActivitiesInstances.gridy = 0;
		pnlActivitiesTable.add(scrActivitiesInstances, gbcActivitiesInstances);
		
		tblActivitiesInstances = new JTable();
		scrActivitiesInstances.setViewportView(tblActivitiesInstances);
		
		JPanel pnlActivitiesLog = new JPanel();
		pnlActivitiesLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log Activities", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splActivitiesInfo.setRightComponent(pnlActivitiesLog);
		GridBagLayout gblActivitiesLog = new GridBagLayout();
		gblActivitiesLog.columnWidths = new int[]{460, 0};
		gblActivitiesLog.rowHeights = new int[]{202, 0};
		gblActivitiesLog.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblActivitiesLog.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlActivitiesLog.setLayout(gblActivitiesLog);
		
		JScrollPane scrActivitiesLog = new JScrollPane();
		GridBagConstraints gbcActivitiesLog = new GridBagConstraints();
		gbcActivitiesLog.fill = GridBagConstraints.BOTH;
		gbcActivitiesLog.gridx = 0;
		gbcActivitiesLog.gridy = 0;
		pnlActivitiesLog.add(scrActivitiesLog, gbcActivitiesLog);
		
		txaActivitiesLog = new JTextArea();
		scrActivitiesLog.setViewportView(txaActivitiesLog);
	}
	
	private void setDefaultSWFConfigValues() {
//		txtAccessID.setText(ConfigManager.getInstance().getSwfAccessId());
//		txtSecretKey.setText(ConfigManager.getInstance().getSwfSecretKey());
//		txtDomainName.setText(ConfigManager.getInstance().getSwfDomainName());
//		txtServiceEndpoint.setText(ConfigManager.getInstance().getSwfServiceEndpoint());
//		txtVersion.setText(ConfigManager.getInstance().getSwfVersion());
	}

	/**
	 *	init Workflow data table
	 */

	private void initStacticWorkflowTable() {
		tableWorkFlowInstances = new JTable();
//		tableWorkFlowInstances.setModel(amazonSWFTableModel);
		scrWorkflowInstances.setViewportView(tableWorkFlowInstances);
		
		/**
		 * add checkbox column
		 */
//		TableColumn actionTableColumn = tableWorkFlowInstances.getColumnModel().getColumn(UIConstants.AmazonSWFPanel.INDEX_CHECKBOX);
//		actionTableColumn.setPreferredWidth(10);
//		actionTableColumn.setHeaderRenderer(new CheckboxHeader(
//				new ItemListener() {
//					@Override
//					public void itemStateChanged(ItemEvent e) {
//						Object source = e.getSource();
//						if (source instanceof AbstractButton == false)
//							return;
//						boolean checked = e.getStateChange() == ItemEvent.SELECTED;
//						for (int i = 0, j = tableWorkFlowInstances.getRowCount(); i < j; i++) {
//							tableWorkFlowInstances.setValueAt(new Boolean(checked), i, UIConstants.AmazonSWFPanel.INDEX_CHECKBOX);
//						}
//					}
//				}
//			)
//		);

		/**
		 * Get column to align, resize...
		 */

//		TableColumn wfIdentity = tableWorkFlowInstances.getColumnModel().getColumn(UIConstants.AmazonSWFPanel.INDEX_IDENTITY);
//		TableColumn wfNumber = tableWorkFlowInstances.getColumnModel().getColumn(UIConstants.AmazonSWFPanel.INDEX_NUMBER);
//		wfIdentity.setPreferredWidth(800);
//		wfNumber.setPreferredWidth(10);
//
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		wfNumber.setCellRenderer(centerRenderer);
//		
//		DefaultTableCellHeaderRenderer leftHeaderRenderer = new DefaultTableCellHeaderRenderer();
//		leftHeaderRenderer.setHorizontalAlignment(SwingConstants.LEFT);
//		wfIdentity.setHeaderRenderer(leftHeaderRenderer);
		
		/**
		 * Show workflow log when selected row
		 */

		tableWorkFlowInstances.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
			 *      This event to show log in TextArea.
			 */
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
//				int wfNumber = Lib.getIntValue(getSelectedInstance(tableWorkFlowInstances, UIConstants.AmazonSWFPanel.INDEX_NUMBER));
//				if(wfNumber > 0) {
//					ISwfMonitor swfMonitor = swfWorkflowManager.getSwfMonitorByWorkflowInstance(wfNumber);
//					String path = swfMonitor.getSwfBean().getLogFilePath();
//					String logContent =  Lib.readLogFileContent(path);
//					
//					if (logContent != null) {
//						txaWorkflowLog.setText(logContent);
//					}
//				}

			}
		});
	}
	
	private void filterWorkflowTable() {
		int selectedRow = -1;
		if (tableWorkFlowInstances != null) {
			selectedRow = tableWorkFlowInstances.getSelectedRow();
		}
		fillDataToWorkflowTableModel();
		if (selectedRow != -1 && selectedRow < tableWorkFlowInstances.getRowCount()) {
			tableWorkFlowInstances.addRowSelectionInterval(selectedRow, selectedRow);
		}

	}
	
	private void fillDataToWorkflowTableModel() {
//		List<ISwfMonitor> workflowInstancesBeans = swfWorkflowManager.getAllSwfMonitor();
//		if (amazonSWFTableModel == null) {
//			amazonSWFTableModel = new AmazonSWFTableModel();
//		}
//		amazonSWFTableModel.setSwfMonitors(workflowInstancesBeans);
//		amazonSWFTableModel.fireTableDataChanged();
	}

	/**
	 *	init Activity data table
	 */

	private void initStacticActivityTable() {
		tblActivitiesInstances = new JTable();
//		tblActivitiesInstances.setModel(amazonActivityTableModel);
		scrActivitiesInstances.setViewportView(tblActivitiesInstances);

		/**
		 * add checkbox column
		 */

//		TableColumn actionTableColumn = tblActivitiesInstances.getColumnModel().getColumn(UIConstants.AmazonSWFPanel.INDEX_CHECKBOX);
//		actionTableColumn.setPreferredWidth(10);
//		actionTableColumn.setHeaderRenderer(new CheckboxHeader(
//				new ItemListener() {
//					@Override
//					public void itemStateChanged(ItemEvent e) {
//						Object source = e.getSource();
//						if (source instanceof AbstractButton == false)
//							return;
//						boolean checked = e.getStateChange() == ItemEvent.SELECTED;
//						for (int i = 0, j = tblActivitiesInstances.getRowCount(); i < j; i++) {
//							tblActivitiesInstances.setValueAt(new Boolean(checked), i, UIConstants.AmazonSWFPanel.INDEX_CHECKBOX);
//						}
//					}
//				}
//			)
//		);

		/**
		 * Get column to align, resize...
		 */

//		TableColumn activityNumber = tblActivitiesInstances.getColumnModel().getColumn(UIConstants.AmazonSWFPanel.INDEX_NUMBER);
//		TableColumn activityIdentity = tblActivitiesInstances.getColumnModel().getColumn(UIConstants.AmazonSWFPanel.INDEX_IDENTITY);
//		activityNumber.setPreferredWidth(10);
//		activityIdentity.setPreferredWidth(800);
//		
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		activityNumber.setCellRenderer(centerRenderer);
//		
//		DefaultTableCellHeaderRenderer leftHeaderRenderer = new DefaultTableCellHeaderRenderer();
//		leftHeaderRenderer.setHorizontalAlignment(SwingConstants.LEFT);
//		activityIdentity.setHeaderRenderer(leftHeaderRenderer);
		/**
		 * Show activity log when selected row
		 */

		tblActivitiesInstances.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
			 *      This event to show log in TextArea.
			 */
			@Override
			public void valueChanged(ListSelectionEvent arg0) {

//				int activityNumber = Lib.getIntValue(getSelectedInstance(tblActivitiesInstances, UIConstants.AmazonSWFPanel.INDEX_NUMBER));
				
//				if(activityNumber > 0) {
//					ISwfMonitor swfMonitor = swfActivityManager.getSwfMonitorByActivityInstance(activityNumber);
//					String path = swfMonitor.getSwfBean().getLogFilePath();
//					String logContent =  Lib.readLogFileContent(path);
//					
//					if (logContent != null) {
//						txaActivitiesLog.setText(logContent);
//					}
//				}
			}
		});
	}

	private void filterActivityTable() {
		int selectedRow = -1;
		if (tblActivitiesInstances != null) {
			selectedRow = tblActivitiesInstances.getSelectedRow();
		}
//		fillDataToActivityTableModel();
		if (selectedRow != -1 && selectedRow < tblActivitiesInstances.getRowCount()) {
			tblActivitiesInstances.addRowSelectionInterval(selectedRow, selectedRow);
		}

	}

	/**
	 *	Reset button
	 */

	private void resetButton() {
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setDefaultSWFConfigValues();
			}
		});
	}

	/**
	 *	Workflow button
	 */

	private void addWofkflowButton() {
		this.getComponent(0).getParent().getParent();
		btnWorkFlowAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		});
	}


	private void removeWofkflowButton() {
		btnWorkFlowRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {


//				amazonSWFTableModel.fireTableDataChanged();
			}
		});
	}

	/**
	 *	Activity button
	 */

	private void addActivityButton() {
		btnActivitiesAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			}
		});
	}


	private void removeActivityButton() {
		btnActivitiesRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {


//				amazonActivityTableModel.fireTableDataChanged();
			}
		});
	}


	/**
	 *	Get instance when selected row
	 */

	private int getSelectedInstance(JTable table, int instanceType) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			return 0;
		}
		int instance = (int) table.getValueAt(selectedRow, instanceType);
		return instance;
	}

}
