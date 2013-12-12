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
import com.jdatabeans.beans.CPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author afa
 */
public class CPasswordDialog extends CDialog{
    private CPasswordField passwordField;

    private JButton okButton;
    
	public CPasswordDialog(JFrame parent)
	{
		super(parent);
		initComp();
		this.setDefaultButton(okButton);
       

	}

    public CPasswordDialog(JDialog parent)
	{
		super(parent);
		initComp();
		this.setDefaultButton(okButton);
        

	}


	public String getPassword()
    {
        return this.passwordField.getText();
    }

	private void initComp()
	{
		this.getContentPane().setLayout(null);
		this.setTitle("需要先输入套帐密码");
		this.setSize(300, 130);
        int y = 10;
        int x = 10;
		    
        addDisplayText("请输入管理员帐号的密码(admin帐号):", x, y, 300, 20);
        y += 25;
        passwordField = new CPasswordField();
        okButton = new CButton("确定(&O)");
		super.addComponent(passwordField, x + 100, y, 170, 20, "密码", 40);
		y += 30;
        this.addComponent(okButton, x + 190, y, 80, 25);


		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				doOK();
			}
		});

        this.setDefaultButton(okButton);
       
    }

	private void doOK()
	{
         this.setVisible(false);
	}

    


    


}