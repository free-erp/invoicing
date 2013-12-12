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
import com.jdatabeans.beans.data.FieldRequiredException;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.logic.IOptionSetService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public abstract class CBaseListDetailedDialog extends JDataDialog implements ActionListener
{
    protected IDbSupport dbSupport;
    protected CButton addButton;
    protected CButton cancelButton;
    protected CPanel inputPanel;
    protected IOptionSetService service;
    protected Company company;

    public CBaseListDetailedDialog(JDialog parent, IDataSource dataSource)
    {
        super(parent,dataSource);
        this.initComps();
        this.service = Main.getServiceManager().getOptionSetService();
        this.company = Main.getMainFrame().getCompany();
    }

    public void initComps()
    {
        this.getContentPane().setLayout(null);
        this.setBounds(0, 200, 200, 120);
        CPanel panel = new CPanel();
        this.inputPanel = this.getMainPanel();
		panel.setLayout(null);
        panel.setBounds(0, 0, 200, 150);
        this.getContentPane().add(panel);
        this.addButton = new CButton("确定(&O)");
        this.cancelButton = new CButton("取消(&C)");
        this.addButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
        panel.addComponent(inputPanel, 0, 10, 170, 30);
        panel.addComponent(this.addButton, 10, 50, 80, 25);
        panel.addComponent(this.cancelButton, 100, 50, 80, 25);
        this.setDefaultButton(addButton);
		this.setCancelButton(cancelButton);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == this.addButton)
        {
            this.doAdd();
            this.dispose();
        }
        else if(source == this.cancelButton)
        {
            this.doClose();
        }
    }

    public void doAdd()
    {
        if(this.isExist())
        {
            return;
        }
         if (this.dataSource == null || this.dataSource.getCurrentDataRow() == null)
        {
            this.modified = false;
        }
        try
        {
            this.save();
            this.modified = false;
        }
        catch (FieldRequiredException ex)
        {
            //ex.printStackTrace();
            MessageBox.showErrorDialog(this, "\"" + ex.getFieldDisplayName() + "\"不能为空!");
        }
        catch (Exception ex)
        {
            MessageBox.showErrorDialog(this, "系统故障无法保存,请与系统管理员联系!\n" + ex.getMessage());
        }
    }

    protected abstract CPanel getMainPanel();

    protected boolean isExist()
    {
        return false;
    }
    
    public void doClose()
    {
        this.dispose();
    }
}
