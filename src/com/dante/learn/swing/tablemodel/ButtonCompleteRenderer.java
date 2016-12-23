package com.dante.learn.swing.tablemodel;
//package com.dante.learn.ui.tablemodel;
//
//import java.awt.Component;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.EventListener;
//import java.util.EventObject;
//import java.util.Vector;
//
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JComponent;
//import javax.swing.JLabel;
//import javax.swing.JTable;
//import javax.swing.event.CellEditorListener;
//import javax.swing.event.ChangeEvent;
//import javax.swing.table.TableCellEditor;
//import javax.swing.table.TableCellRenderer;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.loanlogics.amazonsimpleworkflowpoc.constants.UIConstants;
//
//public class ButtonCompleteRenderer extends JComponent implements TableCellRenderer, TableCellEditor {
//
//	public interface TableButtonListener extends EventListener {
//		public void tableButtonClicked(ActionEvent e, int row, int col);
//	}
//
//	private static final long serialVersionUID = 8260024918409249324L;
//
//	private JLabel labelCompleted = new JLabel(UIConstants.WorkflowExecutionPanel.LABEL_TEXT_COMPLETED);
//	private JLabel labelFailed = new JLabel(UIConstants.WorkflowExecutionPanel.LABEL_TEXT_FAILED);
//
//	private JButton buttonCompleting = new JButton(UIConstants.WorkflowExecutionPanel.LABEL_TEXT_COMPLETING);
//
//	private JButton buttonComplete = new JButton(UIConstants.WorkflowExecutionPanel.BUTTON_TEXT_COMPLETE);
//
//	private int selectedRow;
//
//	private int selectedColumn;
//
//	Vector<TableButtonListener> listener;
//
//	public ButtonCompleteRenderer() {
//		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
//		add(Box.createHorizontalGlue());
//		add(buttonComplete);
//		add(buttonCompleting);
//		add(labelCompleted);
//		add(labelFailed);
//		add(Box.createHorizontalGlue());
//
//		buttonCompleting.setEnabled(false);
//
//		listener = new Vector<TableButtonListener>();
//		buttonComplete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				for (TableButtonListener l : listener) {
//					l.tableButtonClicked(e, selectedRow, selectedColumn);
//				}
//			}
//		});
//	}
//
//	public void addTableButtonListener(TableButtonListener l) {
//		listener.add(l);
//	}
//
//	public void removeTableButtonListener(TableButtonListener l) {
//		listener.remove(l);
//	}
//
//	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//		labelFailed.setVisible(false);
//		labelCompleted.setVisible(false);
//		buttonCompleting.setVisible(false);
//		buttonComplete.setVisible(false);
//		String strValue = (String) value;
//		if (!StringUtils.isEmpty(strValue)) {
//			switch (strValue) {
//			case UIConstants.WorkflowExecutionPanel.LABEL_TEXT_COMPLETED:
//				labelCompleted.setVisible(true);
//				break;
//			case UIConstants.WorkflowExecutionPanel.LABEL_TEXT_FAILED:
//				labelFailed.setVisible(true);
//				break;
//			case UIConstants.WorkflowExecutionPanel.LABEL_TEXT_COMPLETING:
//				buttonCompleting.setVisible(true);
//				break;
//			default:
//				buttonComplete.setVisible(true);
//				break;
//			}
//		}
//
//		return this;
//	}
//
//	@Override
//	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//		selectedRow = row;
//		selectedColumn = column;
//		return getTableCellRendererComponent(table, value, isSelected, true, row, column);
//	}
//
//	@Override
//	public void addCellEditorListener(CellEditorListener arg0) {
//	}
//
//	@Override
//	public void removeCellEditorListener(CellEditorListener arg0) {
//	}
//
//	@Override
//	public void cancelCellEditing() {
//	}
//
//	@Override
//	public Object getCellEditorValue() {
//		return this.buttonComplete;
//	}
//
//	@Override
//	public boolean isCellEditable(EventObject e) {
//		return true;
//	}
//
//	@Override
//	public boolean shouldSelectCell(EventObject arg0) {
//		return true;
//	}
//
//	@Override
//	public boolean stopCellEditing() {
//		JTable table = (JTable) this.getParent();
//		if (table != null) {
//			table.editingStopped(new ChangeEvent(table));
//		}
//		return true;
//	}
//}