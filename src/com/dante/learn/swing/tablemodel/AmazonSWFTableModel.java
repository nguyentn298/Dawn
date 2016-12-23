package com.dante.learn.swing.tablemodel;
//package com.dante.learn.ui.tablemodel;
//
//import java.util.List;
//
//import javax.swing.table.AbstractTableModel;
//
//import com.loanlogics.amazonsimpleworkflowpoc.bean.WorkflowBean;
//import com.loanlogics.amazonsimpleworkflowpoc.constants.UIConstants;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.ISwfMonitor;
//import com.loanlogics.amazonsimpleworkflowpoc.util.Lib;
//
//public class AmazonSWFTableModel extends AbstractTableModel {
//	private static final long serialVersionUID = 1L;
//
//	private List<ISwfMonitor> swfMonitors;
//	
//	public AmazonSWFTableModel() {
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
//	private String[] colNames = new String[] { "No.", "Identity", "Action" };
//
//	@Override
//	public int getRowCount() {
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
//		case UIConstants.AmazonSWFPanel.INDEX_NUMBER:
//			return Integer.class;
//		case UIConstants.AmazonSWFPanel.INDEX_IDENTITY:
//			return String.class;
//		case UIConstants.AmazonSWFPanel.INDEX_CHECKBOX:
//			return Boolean.class;
//		default:
//			return WorkflowBean.class;
//		}
//	}
//
//	@Override
//	public Object getValueAt(int rowIndex, int columnIndex) {
//		ISwfMonitor swfMonitors = this.swfMonitors.get(rowIndex);
//		WorkflowBean workflowBean = (WorkflowBean) swfMonitors.getSwfBean();
//		Object returnValue = null;
//		switch (columnIndex) {
//		case UIConstants.AmazonSWFPanel.INDEX_NUMBER:
//			returnValue = workflowBean.getIndex();
//			break;
//		case UIConstants.AmazonSWFPanel.INDEX_IDENTITY:
//			returnValue = workflowBean.getIdentity();
//			break;
//		case UIConstants.AmazonSWFPanel.INDEX_CHECKBOX:
//			returnValue = workflowBean.isSelected();
//			break;
//		}
//		return returnValue;
//	}
//
//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		ISwfMonitor swfMonitors = this.swfMonitors.get(rowIndex);
//		if (columnIndex == UIConstants.AmazonSWFPanel.INDEX_CHECKBOX) {
//			WorkflowBean workflowBean = (WorkflowBean) swfMonitors.getSwfBean();
//			workflowBean.setSelected(Lib.getBooleanValue(aValue));
//		} else {
//			super.setValueAt(aValue, rowIndex, columnIndex);
//		}
//		fireTableCellUpdated(rowIndex, columnIndex);
//	}
//
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		return columnIndex == UIConstants.AmazonSWFPanel.INDEX_CHECKBOX;
//	}
//}
