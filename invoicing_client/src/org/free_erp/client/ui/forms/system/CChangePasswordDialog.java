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

package org.free_erp.client.ui.forms.system;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.CPasswordField;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.Md5;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.logic.ISystemManageService;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Administrator
 */
public class CChangePasswordDialog extends CDialog implements ActionListener
{
    private CPasswordField oldPswField;
    private CPasswordField newPswField;
    private CPasswordField affirmNewPswField;
    private CButton confirmButton;
    private CButton cancelButton;
    private User user;
    public CChangePasswordDialog(Frame parent)
    {
        super(parent);
        initDialog();
    }
    public void initDialog()
    {
        this.setSize(350,200);
        this.setTitle("修改密码");
        CPanel panel = new CPanel();
        oldPswField = new CPasswordField();
        newPswField = new CPasswordField();
        affirmNewPswField = new CPasswordField();
        confirmButton = new CButton("确定(&O)");
        cancelButton = new CButton("取消(&C)");
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        panel.addComponent(oldPswField, 100, 20, 200, 20, "旧 密 码", 60);
        panel.addComponent(newPswField, 100, 50, 200, 20, "新 密 码", 60);
        panel.addComponent(affirmNewPswField, 100, 80, 200, 20, "确认密码", 60);
        panel.addComponent(confirmButton, 130, 120, 80, 25);
        panel.addComponent(cancelButton, 220, 120, 80, 25);
        
        this.getContentPane().add("Center", panel);
        this.setDefaultButton(confirmButton);
		this.setCancelButton(cancelButton);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == confirmButton)
        {
            String oldPasswordF = oldPswField.getText();
            oldPasswordF = Md5.md5Encode(oldPasswordF);
            String oldPassword = Main.getMainFrame().getUser().getPassword();
            String newPassword = newPswField.getText();
            String affirmPassword = affirmNewPswField.getText();
            if(oldPasswordF.equals(oldPassword))
            {
                if(!"".equals(newPassword))
                {
                    if(newPassword.equals(affirmPassword))
                    {
                        newPassword = Md5.md5Encode(newPassword);
                        user =  Main.getMainFrame().getUser();
                        user.setPassword(newPassword);
                        ISystemManageService service = Main.getServiceManager().getSystemManageService();
                        service.upDateUser(user);
                        MessageBox.showMessageDialog(this, "密码修改成功!");
                        this.setVisible(false);
                        //this.dispose();
                    }else
                    {
                        MessageBox.showErrorDialog(this,"新密码不一致，请重新输入！");
                    }
                }else
                {
                    MessageBox.showErrorDialog(this,"新密码不能为空，请输入！");
                }


            }else
            {
                MessageBox.showErrorDialog(this,"旧密码错误，请重新输入！");
            }
        }
        else if (e.getSource() == cancelButton)
        {
            this.setVisible(false);
            //this.dispose();
        }
    }

}
