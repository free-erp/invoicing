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

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.ObjectsPool;
import org.free_erp.client.ui.forms.CBaseListDetailedDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.system.EmployeeDuty;
import org.free_erp.service.logic.IOptionSetService;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CEmpDutyAddDialog extends CBaseListDetailedDialog implements ActionListener
{
    public JDataField addField;
    public CEmpDutyAddDialog(JDialog parent, IDataSource dataSource)
    {
        super(parent ,dataSource);
        this.setTitle("添加员工职务");
        this.createDbSupport();
    }

    @Override
    protected CPanel getMainPanel()
    {
        CPanel panel = new CPanel();
        this.addField = new JDataField("empDuty", String.class, dataSource);
        this.addField.setRequired("员工职务", true);
        this.addField.setMaxLength(30);
        panel.addComponent(this.addField, 70, 5, 100, 20, "员工职务：", 60);
        return panel;
    }

    @Override
    protected boolean isExist()
    {
        for(int i=0;i<this.dataSource.getDataRows().size();i++)
        {
            String empDuty = (String)this.dataSource.getDataRows().get(i).getColumnValue("empDuty");
            if(empDuty != null)
            {
                if(empDuty.equals(this.addField.getText()))
                {
                    MessageBox.showMessageDialog(this, "“" + empDuty +"”已经存在！");
                    return true;
                }
            }
        }
        return false;
    }

    public void newDataRow()
    {
		EmployeeDuty employeeDuty = new EmployeeDuty();
		employeeDuty.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(employeeDuty, "id", this.dbSupport);
		this.dataSource.insertDataRow(dataRow);
		this.dataSource.last();
	}

    public void createDbSupport()
    {
		dbSupport = new IDbSupport()
        {
			ObjectsPool pool = Main.getMainFrame().getObjectsPool();
			IOptionSetService optionSetService = Main.getServiceManager().getOptionSetService();

			public void delete(Object obj)
            {
                if (obj instanceof EmployeeDuty)
                {
					EmployeeDuty po = (EmployeeDuty) obj;
					optionSetService.deleteEmployeeDuty(po);
					pool.refreshEmployeeDutys();
				}
                else
                {
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}

			public void save(Object obj)
            {
				if (obj instanceof EmployeeDuty)
                {
					EmployeeDuty po = (EmployeeDuty) obj;
					optionSetService.saveEmployeeDuty(po);
					pool.refreshEmployeeDutys();
				}
                else
                {
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}

			public void deleteAll(Collection<Object> objs)
            {
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}

			public void saveAll(Collection<Object> objs)
            {
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
		};
	}
}
