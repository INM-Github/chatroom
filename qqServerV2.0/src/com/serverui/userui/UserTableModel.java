package com.serverui.userui;

import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.dao.MySQLUserDao;



public class UserTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List columnNameList;

	private List dataList;
	
	public UserTableModel(List columnNameList, List dataList) {
		this.columnNameList = columnNameList;
		this.dataList = dataList;
	}

	public int getColumnCount() {
		if(columnNameList != null)
			return columnNameList.size();
		else 
			return 0;
	}

	public int getRowCount() {
		if(dataList != null)
			return dataList.size();
		else
			return 0;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Class getColumnClass(int columnIndex) {
		Object o = getValueAt(0, columnIndex);
		if (o == null) {
			return String.class;
		} else {
			return o.getClass();
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((List) dataList.get(rowIndex)).get(columnIndex);
	}

	public String getColumnName(int columnIndex) {
		return (String) columnNameList.get(columnIndex);
	}

	public String getRowId(int row) {
		return (String) getValueAt(row, 0);
	}

	public void setData(List dataList) {
		this.dataList = dataList;
		this.fireTableDataChanged();
	}

	public void update(HashMap hm) {
		List list =  MySQLUserDao.getInstance().getUserModel(hm);
		this.dataList = list;
		this.fireTableDataChanged();
	}
	
	
	
}