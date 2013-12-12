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
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Currency;
import java.util.Date;
import javax.swing.JScrollPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CChooseIndentDialog extends JDataDialog implements ActionListener
{
    protected CButton chooseButton;
	protected CButton cancelButton;
    protected JDataTable table;
    protected IDbSupport dbSupport;
    protected CBaseFormDialog formDialog;
    public CChooseIndentDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
        super(parent, dataSource);
        this.setSize(900, 600);
        this.setModal(true);
		this.setResizable(true);
        this.dbSupport = dbSupport;
        this.initPanel();
        this.initTableColumns();
        this.dataSource = this.table.getDataSource();
		this.dataSource.setKeyColumnName("id");
        this.initData();
    }

    protected void initTableColumns()
    {
        ITableColumnModel columnModel = this.table.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("单据编号");
		column.setColumnName("number");
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("formDate");
		column.setWidth(80);
		column.setValueType(Date.class);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("业务员");
		column.setColumnName("chargePerson");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("部门");
		column.setColumnName("department");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("供应商");
		column.setColumnName("supplier");
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("合计金额");
		column.setColumnName("totalMoney");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("仓库");
		column.setColumnName("storage.name");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(120);
		columnModel.addColumn(column);
    }

    protected void initData()
    {
        
    }

    protected void initPanel()
    {
        this.getContentPane().setLayout(new BorderLayout());
        table = new JDataTable();
        table.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() >= 2)
				{
					doChoose();
				}
			}
			public void mouseEntered(MouseEvent e)
			{
			}
			public void mouseExited(MouseEvent e)
			{
			}
			public void mousePressed(MouseEvent e)
			{
			}
			public void mouseReleased(MouseEvent e)
			{
			}
		});
        table.addKeyListener(new KeyAdapter()
        {
           public void keyPressed(KeyEvent evt)
           {
               if (evt.getKeyCode() == KeyEvent.VK_ENTER)
               {
                   doChoose();
               }
           }
        });
        JScrollPane tablePane = new JScrollPane(table);
        CPanel panel = new CPanel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", tablePane);
        this.getContentPane().add("Center", panel);

        CPanel bPanel = new CPanel();
        bPanel.setPreferredSize(new Dimension(500, 40));
        bPanel.setLayout(null);

        this.chooseButton = new CButton("选择(&S)");
        this.cancelButton = new CButton("取消(&C)");

        bPanel.addComponent(this.chooseButton, 10, 10, 100, 25);
        bPanel.addComponent(this.cancelButton, 120, 10, 100, 25);

        panel.add("South", bPanel);

        this.chooseButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == chooseButton)
        {
            this.doChoose();
        }
        else if(source == cancelButton)
        {
            this.dispose();
        }
    }

    protected void doChoose()
    {
        
    }

    protected CBaseFormDialog getFormDialog()
    {
        return formDialog;
    }
}
