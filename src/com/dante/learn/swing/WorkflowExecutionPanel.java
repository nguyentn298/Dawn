package com.dante.learn.swing;
//package com.dante.learn.ui;
//
//import java.awt.BorderLayout;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.swing.AbstractButton;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JSpinner;
//import javax.swing.JSplitPane;
//import javax.swing.JTabbedPane;
//import javax.swing.JTable;
//import javax.swing.SpinnerNumberModel;
//import javax.swing.SwingConstants;
//import javax.swing.UIManager;
//import javax.swing.border.TitledBorder;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.TableColumn;
//
//import org.apache.commons.lang3.StringUtils;
//
//import sun.swing.table.DefaultTableCellHeaderRenderer;
//
//import com.dante.learn.ui.tablemodel.ButtonCompleteRenderer;
//import com.dante.learn.ui.tablemodel.CheckboxHeader;
//import com.dante.learn.ui.tablemodel.WorkflowDetailTableModel;
//import com.dante.learn.ui.tablemodel.WorkflowExecutionTableModel;
//import com.dante.learn.ui.tablemodel.ButtonCompleteRenderer.TableButtonListener;
//import com.loanlogics.amazonsimpleworkflowpoc.bean.SwfRequestBean;
//import com.loanlogics.amazonsimpleworkflowpoc.bean.WorkflowDetailBean;
//import com.loanlogics.amazonsimpleworkflowpoc.bean.WorkflowExecutionBean;
//import com.loanlogics.amazonsimpleworkflowpoc.constants.UIConstants;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.ISwfManager;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.ISwfMonitor;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.SwfWorkflowExecutionManager;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.worker.ISwfWorker;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.worker.IWorkerInfoUpdateNotifier;
//import com.loanlogics.amazonsimpleworkflowpoc.util.Lib;
//
//public class WorkflowExecutionPanel extends JPanel {
//
//	private static final long serialVersionUID = 1L;
//
//	private JPanel panelMainExecution;
//	private JPanel pnlWorkflowExecutionAction;
//	private JCheckBox chkAutoComplete;
//	private JButton buttonAdd;
//	private JButton buttonRemove;
//	private JSpinner spnWorkFlowExecution;
//	private JSpinner spnDePairs;
//	private JPanel pnlWorkflowExecutionTable;
//	private JTable tableWorkflowExecutions;
//	private WorkflowExecutionTableModel workflowExecutionTableModel;
//	private JPanel pnlWorkflowDetailTable;
//	private JTable tableWorkflowDetail;
//	private WorkflowDetailTableModel workflowDetailTableModel;
//	private JLabel lblExecuteNumber;
//	private JLabel lblDePairs;
//	private JSplitPane splWorkFlowInfo;
//
//	private ISwfManager swfWorkflowExecutionManager = SwfWorkflowExecutionManager.getInstance();
//	private SwfRequestBean swfRequestBean;
//
//	/**
//	 * Create the application.
//	 */
//	public WorkflowExecutionPanel() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		setLayout(new BorderLayout(0, 0));
//
//		panelMainExecution = new JPanel();
//		add(panelMainExecution, BorderLayout.CENTER);
//		GridBagLayout gblWFExec = new GridBagLayout();
//		gblWFExec.columnWidths = new int[] { 789, 102, 0 };
//		gblWFExec.rowHeights = new int[] { 75, 25, 0, 0 };
//		gblWFExec.columnWeights = new double[] { 0.0, 1.0, 0.0 };
//		gblWFExec.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
//		panelMainExecution.setLayout(gblWFExec);
//
//		initializePanelWorkflowExecutionAction();
//		initializePanelWorkflowExecutionTable();
//		initializePanelWorkflowDetailTable();
//	}
//
//	private void initializePanelWorkflowExecutionAction() {
//		pnlWorkflowExecutionAction = new JPanel();
//		pnlWorkflowExecutionAction.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Workflow execution", TitledBorder.LEADING,
//				TitledBorder.TOP, null, null));
//
//		GridBagConstraints gbcTop = new GridBagConstraints();
//		UIHelper.createGridBagConstraints(gbcTop, new Insets(0, 5, 0, 5), 0, 0, GridBagConstraints.BOTH, null, 2, null);
//
//		panelMainExecution.add(pnlWorkflowExecutionAction, gbcTop);
//		GridBagLayout gblTopPanel = new GridBagLayout();
//		gblTopPanel.columnWidths = new int[] { 55, 50, 86, 50, 85, 0 };
//		gblTopPanel.rowHeights = new int[] { 23 };
//		gblTopPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
//		gblTopPanel.rowWeights = new double[] { 0.0 };
//		pnlWorkflowExecutionAction.setLayout(gblTopPanel);
//
//		lblExecuteNumber = new JLabel("No. Of Execution");
//		GridBagConstraints gbcExecuteNumberLabel = new GridBagConstraints();
//		gbcExecuteNumberLabel.insets = new Insets(0, 0, 0, 5);
//		gbcExecuteNumberLabel.gridx = 0;
//		gbcExecuteNumberLabel.gridy = 0;
//		pnlWorkflowExecutionAction.add(lblExecuteNumber, gbcExecuteNumberLabel);
//
//		spnWorkFlowExecution = new JSpinner();
//		spnWorkFlowExecution.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
//		GridBagConstraints gbcSpinner = new GridBagConstraints();
//		gbcSpinner.fill = GridBagConstraints.HORIZONTAL;
//		gbcSpinner.insets = new Insets(0, 0, 0, 5);
//		gbcSpinner.gridx = 1;
//		gbcSpinner.gridy = 0;
//		pnlWorkflowExecutionAction.add(spnWorkFlowExecution, gbcSpinner);
//		
//		buttonAdd = new JButton("Add More Workflow Execution");
//
//		buttonAdd.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//				final JTabbedPane tabbedPane = (JTabbedPane) WorkflowExecutionPanel.this.getParent();
//				AmazonSWFPanel amazonSWFPanel = (AmazonSWFPanel) tabbedPane.getComponentAt(UIConstants.MainForm.TAB_INDEX_AMAZON_SWF_TAB);
//				swfRequestBean = amazonSWFPanel.populateSwfRequestBean();
//				int numberOfWorkflows = Lib.getIntValue(spnWorkFlowExecution.getValue());
//				int numberOfDePairs =  Lib.getIntValue(spnDePairs.getValue());
//				swfRequestBean.setNoOfInstances(numberOfWorkflows);
//				swfRequestBean.setNoOfDePairs(numberOfDePairs);
//				swfRequestBean.setAutoComplete(chkAutoComplete.isSelected());
//				buttonAdd.setEnabled(false);
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						WorflowExecutionInfoUpdateNotifier worflowExecutionInfoUpdateNotifier = new WorflowExecutionInfoUpdateNotifier();
//						Map<String, Object> additionalParams = new HashMap<String, Object>();
//						additionalParams.put(ISwfWorker.INFO_UPDATE_NOTIFIER_PARAM, worflowExecutionInfoUpdateNotifier);
//						swfWorkflowExecutionManager.processSwfRequest(swfRequestBean, additionalParams);
//						filterWorkflowExecutionTable();
//						buttonAdd.setEnabled(true);
//					}
//				}).start();
//			}
//		});
//
//		GridBagConstraints gbcAddButton = new GridBagConstraints();
//		gbcAddButton.anchor = GridBagConstraints.NORTHWEST;
//		gbcAddButton.insets = new Insets(0, 0, 0, 5);
//		gbcAddButton.gridx = 5;
//		gbcAddButton.gridy = 0;
//		pnlWorkflowExecutionAction.add(buttonAdd, gbcAddButton);
//
//		buttonRemove = new JButton("Remove Workflow Execution");
//		buttonRemove.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//				removeSelectedWorkflowExecutions();
//			}
//		});
//
//		GridBagConstraints gbcRemoveButton = new GridBagConstraints();
//		gbcRemoveButton.anchor = GridBagConstraints.NORTHWEST;
//		gbcRemoveButton.gridx = 6;
//		gbcRemoveButton.gridy = 0;
//		pnlWorkflowExecutionAction.add(buttonRemove, gbcRemoveButton);
//
//		lblDePairs = new JLabel("No. Of DE Pairs");
//		GridBagConstraints gbcDePairsLabel = new GridBagConstraints();
//		gbcDePairsLabel.insets = new Insets(0, 10, 0, 5);
//		gbcDePairsLabel.gridx = 2;
//		gbcDePairsLabel.gridy = 0;
//		pnlWorkflowExecutionAction.add(lblDePairs, gbcDePairsLabel);
//
//		spnDePairs = new JSpinner();
//		spnDePairs.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
//		GridBagConstraints gbcDePairspinner = new GridBagConstraints();
//		gbcDePairspinner.fill = GridBagConstraints.HORIZONTAL;
//		gbcDePairspinner.insets = new Insets(0, 0, 0, 5);
//		gbcDePairspinner.gridx = 3;
//		gbcDePairspinner.gridy = 0;
//		pnlWorkflowExecutionAction.add(spnDePairs, gbcDePairspinner);
//
//		chkAutoComplete = new JCheckBox("Auto complete");
//		GridBagConstraints gbcAutoComplete = new GridBagConstraints();
//		gbcAutoComplete.fill = GridBagConstraints.HORIZONTAL;
//		gbcAutoComplete.insets = new Insets(0, 0, 0, 5);
//		gbcAutoComplete.gridx = 4;
//		gbcAutoComplete.gridy = 0;
//		pnlWorkflowExecutionAction.add(chkAutoComplete, gbcAutoComplete);
//
//	}
//
//	private void initializePanelWorkflowExecutionTable() {
//		pnlWorkflowExecutionTable = new JPanel();
//		GridBagConstraints gbcMid = new GridBagConstraints();
//		gbcMid.fill = GridBagConstraints.VERTICAL;
//		UIHelper.createGridBagConstraints(gbcMid, new Insets(-18, 6, 0, 6), 0, 2, GridBagConstraints.BOTH, null, 2, null);
//		panelMainExecution.add(pnlWorkflowExecutionTable, gbcMid);
//		GridBagLayout gblMidPanel = new GridBagLayout();
//		gblMidPanel.columnWidths = new int[] { 0, 0 };
//		gblMidPanel.rowHeights = new int[] { 0, 0 };
//		gblMidPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
//		gblMidPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
//		pnlWorkflowExecutionTable.setLayout(gblMidPanel);
//
//		splWorkFlowInfo = new JSplitPane();
//		splWorkFlowInfo.setResizeWeight(0.5);
//		splWorkFlowInfo.setOrientation(JSplitPane.VERTICAL_SPLIT);
//		GridBagConstraints gbcWorkFlowInfoSpinner = new GridBagConstraints();
//		gbcWorkFlowInfoSpinner.fill = GridBagConstraints.BOTH;
//		gbcWorkFlowInfoSpinner.gridx = 0;
//		gbcWorkFlowInfoSpinner.gridy = 0;
//		pnlWorkflowExecutionTable.add(splWorkFlowInfo, gbcWorkFlowInfoSpinner);
//		
//		JPanel panelUp = new JPanel();
//		splWorkFlowInfo.setLeftComponent(panelUp);
//		panelUp.setLayout(new GridLayout(0, 1, 0, 0));
//
//		JScrollPane scrollPaneItem = new JScrollPane();
//		panelUp.add(scrollPaneItem);
//
//		tableWorkflowExecutions = new JTable();
//		scrollPaneItem.setViewportView(tableWorkflowExecutions);
//		workflowExecutionTableModel = new WorkflowExecutionTableModel();
//		tableWorkflowExecutions.setModel(workflowExecutionTableModel);
//
//		/*
//		 * add checkbox column
//		 */
//		TableColumn actionTableColumn = tableWorkflowExecutions.getColumnModel().getColumn(UIConstants.WorkflowExecutionPanel.INDEX_CHECKBOX);
//		actionTableColumn.setHeaderRenderer(new CheckboxHeader(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				Object source = e.getSource();
//				if (source instanceof AbstractButton == false)
//					return;
//				boolean checked = e.getStateChange() == ItemEvent.SELECTED;
//				for (int i = 0, j = tableWorkflowExecutions.getRowCount(); i < j; i++) {
//					tableWorkflowExecutions.setValueAt(new Boolean(checked), i, UIConstants.WorkflowExecutionPanel.INDEX_CHECKBOX);
//				}
//			}
//		}));
//
//		tableWorkflowExecutions.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 1) {
//					JTable target = (JTable) e.getSource();
//					int row = target.getSelectedRow();
//					displayWorkflowDetail(row);
//				}
//			}
//		});
//		
//		tableWorkflowExecutions.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				int row = tableWorkflowExecutions.getSelectedRow();
//				displayWorkflowDetail(row);
//			}
//		}
//	);
//
//		/*
//		 * Get column to align, resize...
//		 */
//		TableColumn wfNumber= tableWorkflowExecutions.getColumnModel().getColumn(UIConstants.WorkflowExecutionPanel.INDEX_NUMBER);
//		TableColumn wfRunId = tableWorkflowExecutions.getColumnModel().getColumn(UIConstants.WorkflowExecutionPanel.INDEX_RUNID);
//		TableColumn wfWorkFlowId = tableWorkflowExecutions.getColumnModel().getColumn(UIConstants.WorkflowExecutionPanel.INDEX_WORKFLOW_ID);
//		TableColumn wfDePairs = tableWorkflowExecutions.getColumnModel().getColumn(UIConstants.WorkflowExecutionPanel.INDEX_DE_PAIRS);
//		TableColumn wfStartedTime= tableWorkflowExecutions.getColumnModel().getColumn(UIConstants.WorkflowExecutionPanel.INDEX_START_TIME);
//		TableColumn wfCompletedTime = tableWorkflowExecutions.getColumnModel().getColumn(UIConstants.WorkflowExecutionPanel.INDEX_COMPLETE_TIME);
//		TableColumn wfIsEnded = tableWorkflowExecutions.getColumnModel().getColumn(UIConstants.WorkflowExecutionPanel.IS_ENDED);
//		wfNumber.setMaxWidth(35);
//		wfRunId.setPreferredWidth(200);
//		wfWorkFlowId.setPreferredWidth(200);
//		wfDePairs.setMaxWidth(55);
//		wfIsEnded.setMaxWidth(55);
//		actionTableColumn.setMaxWidth(30);
//		
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		wfNumber.setCellRenderer(centerRenderer);
//		wfDePairs.setCellRenderer(centerRenderer);
//		wfStartedTime.setCellRenderer(centerRenderer);
//		wfCompletedTime.setCellRenderer(centerRenderer);
//		wfIsEnded.setCellRenderer(centerRenderer);
//
//
//	}
//
//	private void initializePanelWorkflowDetailTable() {
//		pnlWorkflowDetailTable = new JPanel();
//		pnlWorkflowDetailTable.setBorder(new TitledBorder(null, "Detail of workflow", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		pnlWorkflowDetailTable.setLayout(new GridLayout(0, 1, 0, 0));
//		splWorkFlowInfo.setRightComponent(pnlWorkflowDetailTable);
//
//		JScrollPane scrollPaneDown = new JScrollPane();
//		pnlWorkflowDetailTable.add(scrollPaneDown);
//
//		tableWorkflowDetail = new JTable();
//		workflowDetailTableModel = new WorkflowDetailTableModel();
//		tableWorkflowDetail.setModel(workflowDetailTableModel);
//		scrollPaneDown.setViewportView(tableWorkflowDetail);
//
//		TableColumn nodeNameColumn = tableWorkflowDetail.getColumnModel().getColumn(WorkflowDetailTableModel.INDEX_NODE_NAME);
//		nodeNameColumn.setPreferredWidth(10);
//
//		TableColumn tokenColumn = tableWorkflowDetail.getColumnModel().getColumn(WorkflowDetailTableModel.INDEX_TOKEN);
//		tokenColumn.setPreferredWidth(400);
//
//		TableColumn actionColumn = tableWorkflowDetail.getColumnModel().getColumn(WorkflowDetailTableModel.INDEX_ACTION);
//		actionColumn.setPreferredWidth(25);
//		ButtonCompleteRenderer buttonCompleteRenderer = new ButtonCompleteRenderer();
//		actionColumn.setCellRenderer(buttonCompleteRenderer);
//		
//		ButtonCompleteRenderer buttonCompleteEditor = new ButtonCompleteRenderer();
//		buttonCompleteEditor.addTableButtonListener(new TableButtonListener() {
//			@Override
//			public void tableButtonClicked(ActionEvent e, int row, int column) {
//				WorkflowDetailBean workflowDetailBean = getRowWorkflowDetailBean(row);
//
//				if (!workflowDetailBean.isCompleting()) {
//					ISwfMonitor swfMonitor = swfWorkflowExecutionManager.getSwfMonitor(workflowDetailBean.getWorkflowExcIndex());
//					swfMonitor.completeTask(workflowDetailBean);
//					workflowDetailBean.setCompleting(true);
//					tableWorkflowDetail.editingStopped(new ChangeEvent(tableWorkflowDetail));
//				}
//			}
//		});
//		actionColumn.setCellEditor(buttonCompleteEditor);
//
//		/*
//		 * Get column to align, resize...
//		 */
//
//		TableColumn detailStartedTime = tableWorkflowDetail.getColumnModel().getColumn(WorkflowDetailTableModel.INDEX_START_TIME);
//		TableColumn detailCompletedTime = tableWorkflowDetail.getColumnModel().getColumn(WorkflowDetailTableModel.INDEX_COMPLETE_TIME);
//		
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		detailStartedTime.setCellRenderer(centerRenderer);
//		detailCompletedTime.setCellRenderer(centerRenderer);
//		
//		DefaultTableCellHeaderRenderer leftHeaderRenderer = new DefaultTableCellHeaderRenderer();
//		leftHeaderRenderer.setHorizontalAlignment(SwingConstants.LEFT);
//		nodeNameColumn.setHeaderRenderer(leftHeaderRenderer);
//		
//	}
//
//	private void filterWorkflowExecutionTable() {
//		int selectedRow = tableWorkflowExecutions.getSelectedRow();
//		fillDataToWorkflowExecutionTableModel();
//
//		if (selectedRow != -1 && selectedRow < tableWorkflowExecutions.getRowCount()) {
//			tableWorkflowExecutions.addRowSelectionInterval(selectedRow, selectedRow);
//		}
//
//	}
//
//	public void fillDataToWorkflowExecutionTableModel() {
//		List<ISwfMonitor> workflowInstancesBeans = swfWorkflowExecutionManager.getAllSwfMonitor();
//		workflowExecutionTableModel.setSwfMonitors(workflowInstancesBeans);
//		workflowExecutionTableModel.fireTableDataChanged();
//	}
//
//	private void removeSelectedWorkflowExecutions() {
//		/**
//		 * Stop workflow
//		 **/
//		stopCheckedSWF(workflowExecutionTableModel, tableWorkflowExecutions, swfWorkflowExecutionManager);
//		filterWorkflowExecutionTable();
//
//		/**
//		 * remove selected row
//		 **/
//		List<ISwfMonitor> workflowInstancesBeans = workflowExecutionTableModel.getSwfMonitors();
//		List<ISwfMonitor> selectedWorkflowBeans = new ArrayList<ISwfMonitor>();
//		for (ISwfMonitor swfMonitor : workflowInstancesBeans) {
//			WorkflowExecutionBean workflowExecutionBean = (WorkflowExecutionBean) swfMonitor.getSwfBean();
//			if(workflowExecutionBean.isSelected()) {
//				selectedWorkflowBeans.add(swfMonitor);
//			}
//		}
//
//		workflowInstancesBeans.removeAll(selectedWorkflowBeans);
//		workflowExecutionTableModel.fireTableDataChanged();
//	}
//
//	private WorkflowExecutionBean getRowWorkflowExecutionBean(int row) {
//		if (row == -1) {
//			return null;
//		}
//
//		Integer index = (Integer) tableWorkflowExecutions.getValueAt(row, UIConstants.WorkflowExecutionPanel.INDEX_NUMBER);
//
//		for (ISwfMonitor swfMonitor : workflowExecutionTableModel.getSwfMonitors()) {
//			WorkflowExecutionBean workflowExecutionBean = (WorkflowExecutionBean) swfMonitor.getSwfBean();
//			if (workflowExecutionBean.getIndex() == index) {
//				return workflowExecutionBean;
//			}
//		}
//
//		return null;
//	}
//	
//	private WorkflowDetailBean getRowWorkflowDetailBean(int row) {
//		if (row == -1 || workflowDetailTableModel.getWorkflowDetailBeans() == null || row >= workflowDetailTableModel.getWorkflowDetailBeans().size()) {
//			return null;
//		}
//
//		return workflowDetailTableModel.getWorkflowDetailBeans().get(row);
//	}
//	
//	public void displaySelectedWorkflowDetail() {
//		int selectedRow = this.tableWorkflowExecutions.getSelectedRow();
//		if (selectedRow >= 0) {
//			displayWorkflowDetail(selectedRow);
//		}
//	}
//	
//	private void displayWorkflowDetail(int workflowExecutionRow) {
//		List<WorkflowDetailBean> workflowDetailBeans = Collections.emptyList();
//		String wfIndex = null;
//
//		WorkflowExecutionBean workflowExecutionBean = getRowWorkflowExecutionBean(workflowExecutionRow);
//		if (workflowExecutionBean != null) {
//			workflowDetailBeans = workflowExecutionBean.getWorkflowDetailBeans();
//			wfIndex = String.valueOf(workflowExecutionBean.getIndex());
//		}
//
//		TitledBorder titleBorder = (TitledBorder) pnlWorkflowDetailTable.getBorder();
//		String title = UIConstants.WorkflowExecutionPanel.PANEL_TEXT_DETAIL_OF_WORKFLOW;
//		if (!StringUtils.isEmpty(wfIndex)) {
//			title = String.format(UIConstants.WorkflowExecutionPanel.PANEL_TEXT_DETAIL_OF_WORKFLOW_INDEX,
//					String.valueOf(workflowExecutionBean.getIndex()));
//		}
//		titleBorder.setTitle(title);
//		pnlWorkflowDetailTable.repaint();
//
//		workflowDetailTableModel.setWorkflowDetailBeans(workflowDetailBeans);
//		workflowDetailTableModel.fireTableDataChanged();
//	}
//
//	private void stopCheckedSWF(AbstractTableModel tableModel, JTable table, ISwfManager swfManager) {
//		List<Integer> listInstances = new ArrayList<Integer>();
//		int numRows = tableModel.getRowCount();
//		for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
//			boolean isChecked = (boolean) table.getValueAt(rowIndex, UIConstants.WorkflowExecutionPanel.INDEX_CHECKBOX);
//			if (isChecked) {
//				int instanceIndex = Lib.getIntValue(table.getValueAt(rowIndex, UIConstants.WorkflowExecutionPanel.INDEX_NUMBER));
//				swfManager.stopSwfInstance(instanceIndex);
//				listInstances.add(instanceIndex);
//			} 
//		}
//		if (!listInstances.isEmpty()) {
//			displayWorkflowDetail(-1);
//			JOptionPane.showMessageDialog(null, String.format("Stop WorkflowExecution Index[%s] successfully!", StringUtils.join(listInstances, ", ")));
//		} else {
//			JOptionPane.showMessageDialog(null, String.format("Please select instances to stop!", StringUtils.join(listInstances, ", ")));
//		}
//	}
//	
//	public WorkflowDetailTableModel getWorkflowDetailTableModel() {
//		return workflowDetailTableModel;
//	}
//
//	private class WorflowExecutionInfoUpdateNotifier implements IWorkerInfoUpdateNotifier {
//		@Override
//		public void onInfoUpdate() {
//			WorkflowExecutionPanel.this.fillDataToWorkflowExecutionTableModel();
//		}
//	}
//}
