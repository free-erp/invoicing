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
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.ObjectsPool;
import org.free_erp.client.ui.forms.CBaseListDetailedDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.system.ProductTaxrate;
import org.free_erp.service.logic.IOptionSetService;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CProTaxrateAddDialog extends CBaseListDetailedDialog implements ActionListener
{
    public JDataNumberField addField;
    public CProTaxrateAddDialog(JDialog parent, IDataSource dataSource)
    {
        super(parent ,dataSource);
        this.setTitle("添加商品税率");
        this.createDbSupport();
    }

    @Override
    protected CPanel getMainPanel()
    {
        CPanel panel = new CPanel();
        this.addField = new JDataNumberField("proTaxrate", Integer.class, dataSource);
        this.addField.setRequired("商品税率", true);
        panel.addComponent(this.addField, 70, 5, 100, 20, "商品税率：", 60);
        return panel;
    }

    @Override
    protected boolean isExist()
    {
        for(int i=0;i<this.dataSource.getDataRows().size();i++)
        {
            Integer proTaxrate = (Integer)this.dataSource.getDataRows().get(i).getColumnValue("proTaxrate");
            if(proTaxrate != null)
            {
                if(String.valueOf(proTaxrate).equals(this.addField.getText()))
                {
                    MessageBox.showMessageDialog(this, "“" + proTaxrate +"”已经存在！");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void doAdd()
    {
        String strNumber = this.addField.getText();
        try
        {
            int number = Integer.valueOf(strNumber);
            super.doAdd();
        }
        catch(Exception ex)
        {
            MessageBox.showErrorDialog(Main.getMainFrame(), "请输入整数!");
        }
    }

    public void newDataRow()
    {
		ProductTaxrate productTaxrate = new ProductTaxrate();
		productTaxrate.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(productTaxrate, "id", this.dbSupport);
		this.dataSource.insertDataRow(dataRow);
		this.dataSource.last();
	}

    public void createDbSupport()
    {
		dbSupport = new IDbSupport()
        {
			private ObjectsPool pool = Main.getMainFrame().getObjectsPool();
			private IOptionSetService optionSetService = Main.getServiceManager().getOptionSetService();

			public void delete(Object obj)
            {
                if (obj instanceof ProductTaxrate)
                {
					ProductTaxrate po = (ProductTaxrate) obj;
					optionSetService.deleteProductTaxrate(po);
					pool.refreshProductTaxrates();
				}
                else
                {
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}

			public void save(Object obj)
            {
				if (obj instanceof ProductTaxrate)
                {
					ProductTaxrate po = (ProductTaxrate) obj;
					optionSetService.saveProductTaxrate(po);
					pool.refreshProductTaxrates();
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
