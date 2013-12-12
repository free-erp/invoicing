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
import com.jdatabeans.beans.data.DataRowChangedListener;
import com.jdatabeans.beans.data.DataRowEvent;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.beans.data.JDataDisplayMoneyField;
import com.jdatabeans.beans.data.JDataMoneyField;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableModel;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.main.Main;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public abstract class CBaseSelectDetailDialog extends JDataDialog implements ActionListener, DataRowChangedListener
{
    protected JToolBar toolBar;
	protected CButton printButton;
	protected CButton closeButton;
    protected IDbSupport dbSupport;
    protected JDataTable table;
	protected JDataMoneyField sumField;
    protected JDataDisplayMoneyField chineseMoneyField;
    
    public CBaseSelectDetailDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
		super(parent, dataSource);
        this.setSize(900, 600);
        this.initComps();
		this.setModal(true);
		this.setResizable(true);
		this.initColumns();
		this.table.getDataSource().setKeyColumnName("id");
        this.refreshTableDataSource();
    }

    protected void initComps()
    {
		this.getContentPane().setLayout(new BorderLayout());
		MainFrame mainFrame = Main.getMainFrame();
		this.toolBar = new JToolBar();
		this.getContentPane().add("North", this.toolBar);
        printButton = new CButton("打印(&P)");
		printButton.setIcon(mainFrame.getIcon("print"));
		closeButton = new CButton("关闭(&C)");
		closeButton.setIcon(mainFrame.getIcon("close"));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.getContentPane().add("Center", panel);
		panel.add("North", this.getMainPanel());
		table = new JDataTable();
		JScrollPane tablePane = new JScrollPane(table);
		panel.add("Center", tablePane);

		CPanel bPanel = new CPanel();
		bPanel.setPreferredSize(new Dimension(500, 40));
		bPanel.setLayout(null);

		panel.add("South", bPanel);
        Font font = new Font("宋体", Font.PLAIN, 14);
        sumField = new JDataMoneyField("totailMoney", this.dataSource, this.dbSupport);
        sumField.setForeground(Color.red);
        sumField.setHorizontalAlignment(JLabel.RIGHT);
        sumField.setFont(font);
		bPanel.addComponent(sumField, 400, 10, 150, 25, "合计金额", 60);
        chineseMoneyField = new JDataDisplayMoneyField(JDataDisplayMoneyField.CHINESE_MONEY, "totailMoney", this.dataSource);
        chineseMoneyField.setBorder(sumField.getBorder());
        chineseMoneyField.setForeground(Color.red);
        chineseMoneyField.setFont(font);
        bPanel.addComponent(chineseMoneyField, 620, 10, 250, 25, "大写:人民币", 70);
        
        this.toolBar.add(printButton);
		this.toolBar.addSeparator();
		this.toolBar.add(closeButton);
        this.printButton.addActionListener(this);
		this.closeButton.addActionListener(this);
    }

    protected void refreshTableDataSource()
    {
		IDataRow dataRow = this.dataSource.getCurrentDataRow();
		if (dataRow == null) {
			return;
		}
		Object d = dataRow.getColumnValue("details");
		if (d != null && d instanceof Set) {
			Set details = (Set) d;
			ArrayList<IDataRow> dataRows = new ArrayList<IDataRow>();
			for (Object obj : details) {
				dataRows.add(new ObjectDataRow(obj.getClass(), obj, "id"));
			}
			IDataSource oldDataSource = this.table.getDataSource();
			oldDataSource.removeDataRowChangedListener(this);
			ITableModel model = new JDataTableModel(dataRows);
			this.table.setModel(model);
			this.table.getDataSource().setKeyColumnName("id");
			model.addDataRowChangedListener(this);
		} else {
			this.table.setDataSource(null);
		}
	}

    protected abstract CPanel getMainPanel();
	protected abstract void initColumns();

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == this.printButton)
        {
			this.doPrint();
		}
        else if (source == this.closeButton)
        {
           this.doClose();
		}
    }

    protected abstract void doPrint();

    protected void doClose()
    {
        this.dispose();
    }

    public void rowInserted(DataRowEvent evt)
    {
        
    }

    public void rowDeleted(DataRowEvent evt)
    {
        
    }

    public void rowUpdated(DataRowEvent evt)
    {
        
    }
}
