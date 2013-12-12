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

package org.free_erp.client.ui.forms.sale;



import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.forms.CChooseIndentDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Currency;
import java.util.Date;
import javax.swing.JScrollPane;

/**
 *
 * @author
 */
public class CSaleChooseindentDialog extends CChooseIndentDialog implements ActionListener
{

    public CSaleChooseindentDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
      super(parent, dataSource,dbSupport);

    }

    @Override
    protected void initTableColumns()
    {
        ITableColumnModel columnModel = this.table.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("�ֿ�");
		column.setColumnName("storage.name");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("���ݱ��");
		column.setColumnName("number");
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("��������");
		column.setColumnName("formDate");
		column.setWidth(80);
		column.setValueType(Date.class);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("ҵ��Ա");
		column.setColumnName("employee");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("����");
		column.setColumnName("department");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("�ͻ�");
		column.setColumnName("customer");
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("�ϼƽ��");
		column.setColumnName("totalMoney");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("��ע");
		column.setColumnName("comments");
		column.setWidth(120);
		columnModel.addColumn(column);
    }


}

