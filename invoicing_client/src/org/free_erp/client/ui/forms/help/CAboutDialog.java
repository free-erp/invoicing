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

package org.free_erp.client.ui.forms.help;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import org.free_erp.client.ui.main.Main;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CAboutDialog extends CDialog
{
	public CAboutDialog(Frame parent)
	{
		super(parent);
		initD();
	}
	private void initD()
	{
		this.setSize(560, 270);
		this.setTitle("���ڱ�ϵͳ");
		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/resources/main.png")));
		this.getContentPane().setLayout(null);
		this.getContentPane().add(label);
		label.setBounds(20, 30, 120, 120);
		JLabel title = new JLabel(Main.getMainFrame().getTitle());
		title.setFont(new Font("����", Font.BOLD, 16));
		this.getContentPane().add(title);
		title.setBounds(160, 30, 350, 30);
        String text = "��Ȩ����:�������Ƿ�Ƽ����޹�˾����������Ȩ��\n���ⷴ��QQȺ:39220566 ��ϵ�绰:13003311398  0510-85020681\n��˾��ҳhttp://www.168erp.com ����:13003311398@163.com\nJava��ؿ��Ȩ����Կ�����˾���С�";
        
		//this.addDisplayText("Version0.9", 250, 50);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(text);
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.getContentPane().add(scrollPane);
        scrollPane.setBounds(160, 80, 360, 120);
		CButton button = new CButton("ȷ��(&O)");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				setVisible(false);
			}
		});
		this.addComponent(button, 420, 210, 80, 25);
        this.setDefaultButton(button);
	}
}
