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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;

import com.jdatabeans.beans.CPasswordField;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataField;
/**
 *
 * @author afa
 */
public class CAccountDetailDialog extends CDetailDialog
{
    protected CPasswordField passwordField;
    protected CPasswordField againField;
    protected boolean passwordChanged = false;
    public CAccountDetailDialog(JDialog parent, IDataSource dataSource)
    {
        super(parent, dataSource);
        this.setTitle("套帐信息");
    }
    
    protected void initComps()
    {
        this.setSize(400, 230);
        nameField = new JDataField("name", String.class, dataSource);
        nameField.setRequired("名称", true);
        commentField = new JDataField("comment", String.class, dataSource);
        this.passwordField = new CPasswordField();
        this.againField = new CPasswordField();
        
        int x = 70;
        int y = 30;
        this.addComponent(nameField, x, y, 300, 20, "名称", 60);
        y += 30;
        this.addComponent(passwordField, x, y, 120, 20, "密码", 60);
        y += 30;
        this.addComponent(againField, x, y, 120, 20, "密码(再次)", 60);
        y += 30;
        this.addComponent(commentField, x, y, 300, 20, "备注", 60);
        y += 30;
        this.addComponent(okButton, x + 120, y, 80, 25);
        this.addComponent(cancelButton, x + 220, y, 80, 25);
        this.okButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
        this.setDefaultButton(okButton);
        this.setCancelButton(cancelButton);
        this.passwordField.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent evt)
            {
                passwordChanged = true;
            }
        });
    }

    protected void doOK()
    {
        /*if (this.dataSource.containsColumnValue("name", this.nameField.getText().trim(), false))
        {
            MessageBox.showErrorDialog(this, "当前系统中已经存在名称为\"" + this.nameField.getText() + "\"的数据行,名称必须唯一!");
            return;
        }
         IDataRow dataRow = this.getDataSource().getCurrentDataRow();
         int id = (Integer)dataRow.getKeyValue();
         String password1 = this.passwordField.getText().trim();
         String password2 = this.againField.getText().trim();
         if (id <= 0 || this.passwordChanged)
         {
                
                if (!password1.equals(password2))
                {
                    MessageBox.showErrorDialog(this, "两次输入的密码不一致!");
                    return;
                }
         }
        try
        {
            this.updateDataSourceFromBeans();
            //

            if (id <= 0 || this.passwordChanged)
            {
                dataRow.setColumnValue("password", Md5.md5Encode(password1));
            }
            //
            this.save();
            this.setVisible(false);
        }
        catch(FieldRequiredException ex)
        {
            MessageBox.showErrorDialog(this, "必填项:\"" + ex.getFieldDisplayName() + "\"不能为空，或者您填写的内容在数据库中没有找到相应的记录!");
        }*/

    }

    public void setVisible(boolean visible)
    {
        if (visible)
        {
            IDataRow dataRow = this.getDataSource().getCurrentDataRow();
            int id = (Integer)dataRow.getKeyValue();
            if (id > 0)
            {
                this.passwordField.setText("123456");
                this.againField.setText("123456");
            }
        }
        super.setVisible(visible);
    }

}
