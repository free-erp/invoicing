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
package org.free_erp.client.ui.report.basic;

/**
 *�ͻ�����WINDOW
 * @author tengzhuolin
 */
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.forms.CBaseInfoManageWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Administrator
 */
public class CCustomerContactsReportWindow extends CBaseInfoManageWindow implements ActionListener {

    private JDataTable datatable;
    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;
    private JDataField numberField;
    private JDataField nameField;
    private CButton selectButton;

    public CCustomerContactsReportWindow(String title) {
        super(title);
        this.initComps();
        this.initTableColumns();
    }

    public void initComps() {
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("�ͻ�������ˮ");
        //this.setMaximizable(true);
        this.setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.setPreferredSize(new Dimension(660, 150));
        tabbedPane.setBounds(10, 5, 890, 120);
        this.getContentPane().add("North", p);
        CPanel panel = new CPanel();
        panel.setLayout(null);
        beginDateField = new JDataDatePicker();
        endDateField = new JDataDatePicker();
        numberField = new JDataField();
        nameField = new JDataField();

        selectButton = new CButton("��ѯ(&Q)");
        this.selectButton.addActionListener(this);
        int x = 100;
        int y = 20;
        panel.addComponent(beginDateField, x, y, 100, 20, "��ѯʱ��", 60);
        panel.addComponent(endDateField, x + 180, y, 100, 20, "��", 60);
        panel.addComponent(numberField, x + 350, y, 100, 20, "�ͻ����", 60);
        panel.addComponent(nameField, x + 520, y, 100, 20, "�ͻ�����", 60);
        panel.addComponent(selectButton, x + 640, y, 100, 25);
        tabbedPane.addTab("��ѯ����", panel);
        datatable = new JDataTable();
        JScrollPane tablePane = new JScrollPane(datatable);
        this.getContentPane().add("Center", tablePane);
    }

    public void initTableColumns() {
        ITableColumnModel columnModel = this.datatable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
        column.setHeaderText("��ˮ��");
        column.setColumnName("id");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("�ͻ����");
        column.setColumnName("id");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("�ͻ�����");
        column.setColumnName("id");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("��������");
        column.setColumnName("id");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("���ݱ��");
        column.setColumnName("id");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("ҵ������");
        column.setColumnName("id");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("ҵ��Ա");
        column.setColumnName("number");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("���۽��");
        column.setColumnName("name");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�տ���");
        column.setColumnName("catalogName");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("������");
        column.setColumnName("spec");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("ë��");
        column.setColumnName("unit");
        column.setWidth(50);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("������");
        column.setColumnName("price");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("Ӧ�����");
        column.setColumnName("amount");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("��ע");
        column.setColumnName("shelf");
        column.setWidth(80);
        columnModel.addColumn(column);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectButton) {
        }
    }

    @Override
    protected void initTypes() {
    }
}
