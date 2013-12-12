/*
 * Copyright 2013, TengJianfa , and other individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.free_erp.client.ui.forms;
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author Administrator
 */
public class CSelectDraftDialog extends CBaseStorageMainDetailDialog {

	private IDataSource dataSource;
	private JDataTable datatable;
	private JDataDatePicker beginDateField;
	private JDataDatePicker endDateField;
	private CField singleNumField;
	private CButton openButton;
	private CButton cancelButton;
	public CSelectDraftDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport) {
		super(parent, dataSource, dbSupport);
		this.toolBar.setVisible(false);
		initCs();
		initColumns();
	}
	public void initCs() {
		this.setTitle("ѡ��ݸ�");
		this.setModal(true);
		this.setSize(800, 500);
		this.setResizable(true);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.getContentPane().add("Center", panel);
		CPanel topPanel = new CPanel();
		topPanel.setPreferredSize(new Dimension(500, 100));
		panel.add("North", topPanel);
		topPanel.setLayout(null);

		beginDateField = new JDataDatePicker();
		endDateField = new JDataDatePicker();
		singleNumField = new CField();

		int x = 100;
		int y = 20;
		topPanel.addComponent(beginDateField, x, y, 100, 20, "��ѯʱ���", 75);
		topPanel.addComponent(endDateField, x + 140, y, 100, 20, "��", 20);
		topPanel.addComponent(singleNumField, x + 310, y, 100, 20, "���ݱ��", 60);
		datatable = new JDataTable();
		JScrollPane tablePane = new JScrollPane(datatable);
		panel.add("Center", tablePane);

		CPanel bPanel = new CPanel();
		bPanel.setPreferredSize(new Dimension(500, 40));
		bPanel.setLayout(null);
		panel.add("South", bPanel);
		openButton = new CButton("��  ��");
		cancelButton = new CButton("ȡ  ��");
		this.openButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
		bPanel.addComponent(openButton, 600, 10, 70, 20, "", 20);
		bPanel.addComponent(cancelButton, 700, 10, 70, 20, "", 20);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			JOptionPane.showMessageDialog(this, "��ʱδʵ��!");
		}
		if (e.getSource() == cancelButton) {
			super.doClose();
		}
	}
	@Override
	public void newDataRow() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	@Override
	protected CPanel getMainPanel() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	@Override
	protected void initColumns() {
		ITableColumnModel columnModel = this.datatable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("״̬");
		column.setColumnName("status");
		column.setWidth(70);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("���ݱ��");
		column.setColumnName("id");
		column.setWidth(70);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("��������");
		column.setColumnName("indate");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("����Ա");
		column.setColumnName("admin");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("�ϼƽ��");
		column.setColumnName("moneys");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("��ע");
		column.setColumnName("comment");
		column.setWidth(200);
		columnModel.addColumn(column);
	}
	protected Class getDetailClass() {
		return null;
	}

   
}
