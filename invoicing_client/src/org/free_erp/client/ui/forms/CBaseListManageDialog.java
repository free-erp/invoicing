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
import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.logic.IOptionSetService;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public abstract class CBaseListManageDialog extends CDialog implements ActionListener
{
    protected CButton addButton;
    protected CButton modifyButton;
    protected CButton removeButton;
    protected CButton closeButton;
    protected static IDbSupport dbSupport;
    protected JDataTable dataTable;
    protected IDataSource dataSource;
    protected IOptionSetService service;
    protected Company company;
    
    public CBaseListManageDialog(Frame parent)
    {
        super(parent);
        this.service = Main.getServiceManager().getOptionSetService();
        this.company = Main.getMainFrame().getCompany();
        this.initComps();
        this.initColumns();
        this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");
    }
    
    protected void initComps()
    {
        this.getContentPane().setLayout(null);
        this.setBounds(0, 200, 350, 350);
        CPanel panel = new CPanel();
		panel.setLayout(null);
        panel.setBounds(10, 10, 310, 300);
        this.getContentPane().add(panel);
        this.addButton = new CButton("添加(&A)");
        this.modifyButton = new CButton("修改(&M)");
        this.removeButton = new CButton("删除(&D)");
        this.closeButton = new CButton("关闭(&C)");
        this.addButton.addActionListener(this);
        this.modifyButton.addActionListener(this);
        this.removeButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        this.dataTable = new JDataTable();
		JScrollPane tablePane = new JScrollPane(dataTable);
        panel.addComponent(tablePane, 10, 10, 200, 300);
        panel.addComponent(this.addButton, 230, 10, 75, 25);
        panel.addComponent(this.modifyButton, 230, 40, 75, 25);
        panel.addComponent(this.removeButton, 230, 70, 75, 25);
        panel.addComponent(this.closeButton, 230, 270, 75, 25);
        this.setCancelButton(closeButton);
        dataTable.addMouseListener(new MouseListener()
        {
			public void mouseClicked(MouseEvent e)
            {
				if (e.getClickCount() >= 2)
                {
					doEdit();
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
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == this.addButton)
        {
            this.doAdd();
        }
        else if(source == this.modifyButton)
        {
            this.doEdit();
        }
        else if(source == this.removeButton)
        {
            this.doRemove();
        }
        else if(source == this.closeButton)
        {
            this.doClose();
        }
    }

    public void doRemove()
    {
        if (this.dataTable.getSelectedRow() < 0)
		{
			MessageBox.showMessageDialog(Main.getMainFrame(), "没有数据行被选中!");
            return;
		}
		if (MessageBox.showQuestionDialog(Main.getMainFrame(), "您确信要删除当前记录吗?") == 0)
		{
			IDataRow dataRow = this.dataTable.getSelectedDataRow();
			try
			{
                this.dataSource.removeDataRow(dataRow);
                this.dataSource.clearTempDataRows();
			}
			catch(Exception ex)
			{
				MessageBox.showErrorDialog(Main.getMainFrame(), "无法删除,请与系统管理员联系!\n" + ex.getMessage());
				ex.printStackTrace();
			}
		}
    }

    public void doClose()
    {
        this.dispose();
    }
    public abstract void initDatas();
    public abstract void doAdd();
    public abstract void doEdit();
    protected abstract void initColumns();

}
