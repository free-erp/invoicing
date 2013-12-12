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

import org.free_erp.client.ui.forms.CBaseFrame;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.CPanel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author alin
 */
public class CReportWindow extends CBaseFrame{
       private JPanel functionsPanel;
            private JPanel salePanel;
        public CReportWindow(String title) {
        super(title);
        initComponents();
        }

        protected void initComponents() {
        MainFrame mainFrame =new MainFrame();
        
        CPanel pPanel = new CPanel();
        pPanel.setLayout(null);
        JScrollPane salePane = new JScrollPane();
        //permissonList = new JList();
        this.salePanel =new JPanel();
        this.salePanel.setBackground(Color.white);
        salePane.setViewportView(salePanel);
        pPanel.addDisplayText("销售报表", 10, 10);
        pPanel.addComponent(salePane, 20, 30, 200, 320);
        pPanel.addDisplayText("采购报表:", 250, 10);
        this.functionsPanel = new JPanel();
        this.functionsPanel.setBackground(Color.white);
        JScrollPane subPane = new JScrollPane();
        subPane.setViewportView(this.functionsPanel);
        pPanel.addComponent(subPane, 260, 30, 200, 320);
 
//        tabbedPane.addTab("权限设置", pPanel);


        }
}
