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

package org.free_erp.client.ui.forms.basic;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataComboBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import javax.swing.JDialog;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CCustomerRejiggerTypeDialog extends CRejiggerTypeDialog
{
    protected Customer customer;

    public CCustomerRejiggerTypeDialog(JDialog parent, IDataSource dataSource)
    {
        super(parent,dataSource);
    }

    @Override
    protected void getCurrentProduct()
    {
        if(dataSource != null)
        {
            int customerID = (Integer) this.dataSource.getCurrentDataRow().getColumnValue("id");
            this.customer = Main.getServiceManager().getCustomerService().getCustomer(customerID);
        }
    }

    @Override
    protected void initComps()
    {
        this.getContentPane().setLayout(null);
        this.setBounds(0, 300, 360, 200);
        CPanel panel = new CPanel();
		panel.setLayout(null);
        panel.setBounds(10, 10, 340, 180);
        this.getContentPane().add(panel);
        oldType = new CField();
        if(this.customer != null)
        {
            oldType.setText(this.customer.getCatalog().getName() + "[" + this.customer.getCatalog().getNumber() + "]");
        }
        oldType.setEnabled(false);

        newType = new JDataComboBox(CustomerCatalog.class,Main.getMainFrame().getObjectsPool().getCustomerCatalogs());
        okButton = new CButton("确定(&O)");
        cancelButton = new CButton("取消(&C)");
        panel.addComponent(this.oldType, 100, 10, 160, 20, "从类别：", 60);
        panel.addComponent(this.newType, 100, 50, 160, 20, "移  至：", 60);
        panel.addComponent(this.okButton, 100, 100, 75, 25);
        panel.addComponent(this.cancelButton, 180, 100, 75, 25);
        this.okButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
    }

    @Override
    protected void doSave()
    {
        Object obj = newType.getSelectedItem();
        if(obj != null && obj instanceof CustomerCatalog )
        {
            CustomerCatalog catalog = (CustomerCatalog)obj;
            customer.setCatalog(catalog);
            Main.getServiceManager().getCustomerService().saveCustomer(customer);
            Main.getMainFrame().getDataBaseObjectPool().refreshAllCustomers();
            Main.getMainFrame().getDataBaseObjectPool().refreshCustomers();
        }
        this.doClose();
    }
}
