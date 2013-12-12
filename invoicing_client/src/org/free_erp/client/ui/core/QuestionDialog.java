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

import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import org.free_erp.service.entity.base.Company;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Afa
 */
public class QuestionDialog extends CDialog{

    protected JTextField companyName = new JTextField();
    protected JTextField contactPerson = new JTextField();
    protected JTextField phone = new JTextField();
    protected JTextField address = new JTextField();
    protected JTextField mailbox = new JTextField();
    protected JTextArea question = new JTextArea();
    protected CButton okButton = new CButton("提交(&O)");
    protected CButton cancelButton = new CButton("取消(&C)");
    protected JLabel displayText;
    
    public QuestionDialog(JFrame parent)
    {
        super(parent);
        this.setSize(400, 330);
        this.setTitle("问题反馈");
        initC();
        this.question.requestFocus();
    }
    protected void initC()
    {
        super.getContentPane().setLayout(null);
        super.getContentPane().setBackground(Color.white);
        int y = 10;
        super.addComponent(companyName, 80, y, 250, 25, "公司名称:", 60);
        y += 30;
        super.addComponent(contactPerson, 80, y, 60, 25, "联系人:", 60);
        super.addComponent(phone, 80 + 140, y, 110, 25, "电话:", 40);
        y += 30;
        super.addComponent(mailbox, 80, y, 250, 25, "邮箱:", 60);
        y += 30;
        super.addComponent(address, 80, y, 250, 25, "公司地址:", 60);
        y += 30;
        displayText = new JLabel("问题描述");
        super.addComponent(displayText, 20, y, 300, 25);
        //super.addDisplayText("问题描述:", 20, y);
        y += 30;
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(question);
        super.addComponent(sp, 20, y, 310, 100);
        y += 110;
        //question.setBorder(BorderFactory.createLineBorder(Color.black));
        super.addComponent(okButton, 100, y, 100, 25);
        super.addComponent(cancelButton, 220, y, 100, 25);
        Company comp = Main.getMainFrame().getCompany();
        this.companyName.setText(comp.getName());
        this.phone.setText(comp.getPhone());
        this.address.setText(comp.getAddress());
        this.contactPerson.setText(comp.getContact());
        this.mailbox.setText(comp.getEmail());
        this.setCancelButton(cancelButton);
        //this.setDefaultButton(okButton);
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                doAct();
            }
        });
        cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                setVisible(false);
            }
        });
    }
    protected void doAct()
    {
        try
        {
            String comp = this.companyName.getText();
            String contact = this.contactPerson.getText();
            String mail = this.mailbox.getText();
            String address = this.address.getText();
            String q = this.question.getText();
            String args = "http://www.168erp.com/question.asp?comp=" + comp + "&contact=" + contact + "&mail=" + mail + "&address=" + address + "&question=" + q;
            Runtime.getRuntime().exec("explorer  " + args);
            System.out.println("args:" + args);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        setVisible(false);
    }

}
