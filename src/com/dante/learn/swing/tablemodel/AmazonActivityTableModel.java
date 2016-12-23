package com.dante.learn.swing.tablemodel;
//package com.dante.learn.ui.tablemodel;
//
//import java.util.List;
//
//import javax.swing.table.AbstractTableModel;
//
//import com.loanlogics.amazonsimpleworkflowpoc.bean.ActivityBean;
//import com.loanlogics.amazonsimpleworkflowpoc.constants.UIConstants;
//import com.loanlogics.amazonsimpleworkflowpoc.impl.ISwfMonitor;
//import com.loanlogics.amazonsimpleworkflowpoc.util.Lib;
//
//public class AmazonActivityTableModel extends AbstractTableModel {
//	private static final long serialVersionUID = 1L;
// use another list instead for swfMonitors
//	private List<ISwfMonitor> swfMonitors;
//	
//	public AmazonActivityTableModel() {
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
//		case UIConstants.AmazonSWFPanel.INDEX_NUMBER:
//			return Integer.class;
//		case UIConstants.AmazonSWFPanel.INDEX_IDENTITY:
//			return String.class;
//		case UIConstants.AmazonSWFPanel.INDEX_CHECKBOX:
//			return Boolean.class;
//		default:
//			return ActivityBean.class;
//		}
//	}
//
//	@Override
//	public Object getValueAt(int rowIndex, int columnIndex) {
//		ISwfMonitor swfMonitors = this.swfMonitors.get(rowIndex);
//		ActivityBean activityBean = (ActivityBean) swfMonitors.getSwfBean();
//		Object returnValue = null;
//		switch (columnIndex) {
//		case UIConstants.AmazonSWFPanel.INDEX_NUMBER:
//			returnValue = activityBean.getIndex();
//			break;
//		case UIConstants.AmazonSWFPanel.INDEX_IDENTITY:
//			returnValue = activityBean.getIdentity();
//			break;
//		case UIConstants.AmazonSWFPanel.INDEX_CHECKBOX:
//			returnValue = activityBean.isSelected();
//			break;
//		}
//		return returnValue;
//	}
//
//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		ISwfMonitor swfMonitors = this.swfMonitors.get(rowIndex);
//		if (columnIndex == UIConstants.AmazonSWFPanel.INDEX_CHECKBOX) {
//			ActivityBean activityBean = (ActivityBean) swfMonitors.getSwfBean();
//			activityBean.setSelected(Lib.getBooleanValue(aValue));
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
