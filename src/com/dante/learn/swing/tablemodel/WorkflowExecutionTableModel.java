package com.dante.learn.swing.tablemodel;
//package com.dante.learn.ui.tablemodel;
//
//import java.util.List;
//
//import javax.swing.table.AbstractTableModel;
//
//import com.loanlogics.amazonsimpleworkflowpoc.bean.WorkflowExecutionBean;
//import com.loanlogics.amazonsimpleworkflowpoc.constants.UIConstants.WorkflowExecutionPanel;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.ISwfMonitor;
//import com.loanlogics.amazonsimpleworkflowpoc.util.DateUtil;
//import com.loanlogics.amazonsimpleworkflowpoc.util.Lib;
//
//public class WorkflowExecutionTableModel extends AbstractTableModel {
//
//	private static final long serialVersionUID = 1L;
//
//	private List<ISwfMonitor> swfMonitors;
//
//	public WorkflowExecutionTableModel() {
//		super();
//	}
//
//	public List<ISwfMonitor> getSwfMonitors() {
//		return swfMonitors;
//	}
//
//	public void setSwfMonitors(List<ISwfMonitor> swfMonitors) {
//		this.swfMonitors = swfMonitors;
//	}
//
//	private String[] colNames = new String[] { "No.", "RunId", "WorkflowId", "DE Pairs","Started time", "Completed time","isEnded", "Action" };
//
//	@Override
//	public int getRowCount() {
//		if (this.swfMonitors == null) {
//			return 0;
//		}
//
//		return this.swfMonitors.size();
//	}
//
//	@Override
//	public int getColumnCount() {
//		return colNames.length;
//	}
//
//	public String getColumnName(int columnIndex) {
//		return colNames[columnIndex];
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public Class getColumnClass(int column) {
//		switch (column) {
//		case WorkflowExecutionPanel.INDEX_NUMBER:
//			return Integer.class;
//		case WorkflowExecutionPanel.INDEX_RUNID:
//			return String.class;
//		case WorkflowExecutionPanel.INDEX_WORKFLOW_ID:
//			return String.class;
//		case WorkflowExecutionPanel.INDEX_DE_PAIRS:
//			return Integer.class;
//		case WorkflowExecutionPanel.INDEX_START_TIME:
//			return String.class;
//		case WorkflowExecutionPanel.INDEX_COMPLETE_TIME:
//			return String.class;
//		case WorkflowExecutionPanel.IS_ENDED:
//			return String.class;
//		case WorkflowExecutionPanel.INDEX_CHECKBOX:
//			return Boolean.class;
//		default:
//			return WorkflowExecutionBean.class;
//		}
//	}
//
//	@Override
//	public Object getValueAt(int rowIndex, int columnIndex) {
//		ISwfMonitor swfMonitors = this.swfMonitors.get(rowIndex);
//		WorkflowExecutionBean workflowExecutionBean = (WorkflowExecutionBean) swfMonitors.getSwfBean();
//		Object returnValue = null;
//		switch (columnIndex) {
//		case WorkflowExecutionPanel.INDEX_NUMBER:
//			returnValue = workflowExecutionBean.getIndex();
//			break;
//		case WorkflowExecutionPanel.INDEX_RUNID:
//			returnValue = workflowExecutionBean.getRunId();
//			break;
//		case WorkflowExecutionPanel.INDEX_WORKFLOW_ID:
//			returnValue = workflowExecutionBean.getWorkflowId();
//			break;
//		case WorkflowExecutionPanel.INDEX_DE_PAIRS:
//			returnValue = workflowExecutionBean.getSwfRequestBean().getNoOfDePairs();
//			break;
//		case WorkflowExecutionPanel.INDEX_START_TIME:
//			returnValue = workflowExecutionBean.getStartedTime() == null ? null : DateUtil.convertDateTimeToString(workflowExecutionBean.getStartedTime().getTime());
//			break;
//		case WorkflowExecutionPanel.INDEX_COMPLETE_TIME:
//			returnValue = workflowExecutionBean.getCompletedTime() == null ? null : DateUtil.convertDateTimeToString(workflowExecutionBean.getCompletedTime().getTime());
//			break;
//		case WorkflowExecutionPanel.IS_ENDED:
//			boolean isCompleted = workflowExecutionBean.isCompleted();
//			returnValue = ( isCompleted ? "Yes" : "No");
//			break;
//		case WorkflowExecutionPanel.INDEX_CHECKBOX:
//			returnValue = workflowExecutionBean.isSelected();
//			break;
//		}
//		return returnValue;
//	}
//
//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		ISwfMonitor swfMonitors = this.swfMonitors.get(rowIndex);
//		if (columnIndex == WorkflowExecutionPanel.INDEX_CHECKBOX) {
//			WorkflowExecutionBean workflowExecutionBean = (WorkflowExecutionBean) swfMonitors.getSwfBean();
//			workflowExecutionBean.setSelected(Lib.getBooleanValue(aValue));
//		} else {
//			super.setValueAt(aValue, rowIndex, columnIndex);
//		}
//		fireTableCellUpdated(rowIndex, columnIndex);
//	}
//
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		return columnIndex == WorkflowExecutionPanel.INDEX_CHECKBOX;
//	}
//}
