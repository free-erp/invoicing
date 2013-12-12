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

package org.free_erp.client.ui.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.beans.data.JDataField;

/**
 *
 * @author afa
 */
public class CDetailDialog extends JDataDialog implements ActionListener
{
    protected JDataField nameField;
    protected JDataField commentField;
    protected CButton okButton = new CButton("确定(&O)");
    protected CButton cancelButton = new CButton("取消(&C)");
    public CDetailDialog(JDialog dialog, IDataSource dataSource)
    {
        super(dialog, dataSource);
        initComps();
        this.setTitle(dialog.getTitle());
    }
    protected void initComps()
    {
        this.setSize(400, 170);
        nameField = new JDataField("name", String.class, dataSource);
        nameField.setRequired("名称", true);
        commentField = new JDataField("comment", String.class, dataSource);
        int x = 70;
        int y = 30;
        this.addComponent(nameField, x, y, 300, 20, "名称", 60);
        y += 30;
        this.addComponent(commentField, x, y, 300, 20, "备注", 60);
        y += 30;
        this.addComponent(okButton, x + 120, y, 80, 25);
        this.addComponent(cancelButton, x + 220, y, 80, 25);
        this.okButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
        this.setDefaultButton(okButton);
        this.setCancelButton(cancelButton);
    }

    public void newDataRow(Class<?> entityClass, IDbSupport dbSupport)
    {
        /*try
        {
            Object entity = entityClass.newInstance();
            IDataRow dataRow = ObjectDataRow.newDataRow(entity, "id", dbSupport);
            if (!AccountPo.class.isAssignableFrom(entityClass))
            {
                dataRow.setColumnValue("account", Main.getAccount());
            }
            this.dataSource.insertDataRow(dataRow);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }*/
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == this.okButton)
        {
            this.doOK();
        }
        else
        {
            this.doCancel();
        }
    }

    protected void doOK()
    {
        /*if (this.dataSource.containsColumnValue("name", this.nameField.getText().trim(), false))
        {
            MessageBox.showErrorDialog(this, "当前系统中已经存在名称为\"" + this.nameField.getText() + "\"的数据行,名称必须唯一!");
            return;
        }

        try
        {
            this.updateDataSourceFromBeans();
            this.save();
            this.setVisible(false);
        }
        catch(FieldRequiredException ex)
        {
            MessageBox.showErrorDialog(this, "必填项:\"" + ex.getFieldDisplayName() + "\"不能为空，或者您填写的内容在数据库中没有找到相应的记录!");
        }*/
        
    }
    
    protected void doCancel()
    {
        this.dataSource.clearTempDataRows();
        this.setVisible(false);
    }    
}
