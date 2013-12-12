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

import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.JDataDatePicker;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Administrator
 */
public class CSystemLogDialog extends CDialog implements ActionListener
{
    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;
    private CField usernameField;
    private CField singleNumField;
    private JButton selectButton;
    public CSystemLogDialog(Frame parent)
    {
		super(parent);
		initComps();
    }
    public void initComps()
    {
                this.getContentPane().setLayout(new BorderLayout());
		this.setTitle("系统日志");
		this.setBounds(0, 200, 920, 600);
                
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new JPanel();
		p.setLayout(null);
		p.add(tabbedPane);
		p.setPreferredSize(new Dimension(660, 486));
		tabbedPane.setBounds(10, 5, 890, 120);
		this.getContentPane().add("Center", p);
		CPanel panel = new CPanel();
		panel.setLayout(null);
                beginDateField = new JDataDatePicker();
                endDateField = new JDataDatePicker();
		usernameField = new CField();
                singleNumField = new CField();
                selectButton = new JButton("查    询");
                this.selectButton.addActionListener(this);
                int x = 100;
                int y = 20;
		panel.addComponent(beginDateField, x, y, 100, 20, "查询时间段", 75);
                panel.addComponent(endDateField, x + 140, y, 100, 20, "至", 20);
                panel.addComponent(usernameField, x + 310, y, 100, 20, "用户名", 45);
                panel.addComponent(singleNumField, x + 480, y, 100, 20, "单据编号", 60);
                panel.addComponent(selectButton, x + 480, y + 30, 100, 20, "", 60);
		tabbedPane.addTab("查询条件", panel);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == selectButton)
        {
            JOptionPane.showMessageDialog(this, "暂时未实现!");
        }
    }
}
