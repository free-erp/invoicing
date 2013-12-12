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
import com.jdatabeans.beans.CComboBoxPane;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.DefaultDataSource;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author afa
 */
public class ProductSelectPanel extends CComboBoxPane implements ActionListener, MouseListener ,TreeSelectionListener,KeyListener
{
	private Company company;
	private JDataTable dataTable;
	private CButton selectButton;
    private IDataSource dataSource;
    private JTree typesTree;
    private CField quickInputField = new CField();
    protected DefaultTreeModel treeModel;
    protected IDataSource typeDataSource;

    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        findSelectedDataRow();
        //System.out.println("keyCode:" + e. + "   enter:" + KeyEvent.VK_ENTER);
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            //结束
            actionPerformed(new ActionEvent(this, 0, "act"));
        }
    }

    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        
    }

    private void  findSelectedDataRow()
    {
        String quickKeyString = this.quickInputField.getText().trim();
        
        if (!quickKeyString.equals(""))
        {
            IDataRow dataRow = this.dataSource.partlyFindStartWith("code", quickKeyString);
            if (dataRow != null)
            {
                this.dataTable.setSelectedRow(dataRow);
            }
        }
        
    }

    public void valueChanged(TreeSelectionEvent e) {
        Object obj = e.getPath().getLastPathComponent();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
        Object userObject = node.getUserObject();
        if (userObject instanceof IDataRow) {
            this.typeDataSource.setCurrentDataRow((IDataRow) userObject);
        }
        doFilter(e.getPath());
    }

	
	public ProductSelectPanel(Company company)
	{
		this.company = company;
		this.initComponents();
		this.initTableColumns();		
		refreshDatas();
		this.dataTable.setSelectedRow(0);
        refreshCatalogs();
        initTypeTreeNodes();
        this.typesTree.addTreeSelectionListener(this);
        this.quickInputField.requestFocus();
	}

	public void mouseClicked(MouseEvent e)
	{
		if (e.getClickCount() >= 2)
		{
			this.fireActionPerformed(null);
		}
	}

	public void mouseEntered(MouseEvent e)
	{
		
	}

	public void mouseExited(MouseEvent e)
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		
	}

	public void mouseReleased(MouseEvent e)
	{
		
	}

    protected void initTypeTreeNodes() {
        List<IDataRow> dataRows = typeDataSource.getDataRows();
        HashSet<IDataRow> tempSet = new HashSet<IDataRow>();
        HashSet<IDataRow> cpSet = new HashSet<IDataRow>();
        HashMap<Integer, MutableTreeNode> tempMap = new HashMap<Integer, MutableTreeNode>();
        for (IDataRow dataRow : dataRows) {
            tempSet.add(dataRow);
            cpSet.add(dataRow);
        }
        int times = 0; //最多转10圈，如果10圈没有结束，就说明数据的parent有问题，舍弃
        while (!tempSet.isEmpty()) {
            times++;
            for (IDataRow dataRow : cpSet) {
                if (tempSet.contains(dataRow)) {
                    MutableTreeNode node = this.addInitTreeNode(dataRow);
                    if (node != null) {
                        //this.tempMap.put((Integer)dataRow.getColumnValue("id"), node);
                        tempSet.remove(dataRow);
                    }
                }
            }

            if (times > 10)//最多转10圈，如果10圈没有结束，就说明数据的parent有问题，舍弃
            {
                //有parentId没有正常设置的数据
                break;
            }

        }
        tempSet.clear();
        cpSet.clear();
        MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
        TreePath path = new TreePath(new Object[]{root});
        this.typesTree.expandPath(path);
   

    }


    //private HashMap<Integer, MutableTreeNode> tempMap = new HashMap<Integer, MutableTreeNode>();
    private MutableTreeNode addInitTreeNode(IDataRow dataRow) {
        Integer parentId = (Integer) dataRow.getColumnValue("parentId");
        MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
        if (parentId == null || parentId <= 0) {
            MutableTreeNode node = new DefaultMutableTreeNode(dataRow);
            this.treeModel.insertNodeInto(node, root, root.getChildCount());
            return node;
        } else {
            MutableTreeNode parentNode = this.getTreeNode(parentId);//this.getDataRowTreeNode(parentId);
            if (parentNode != null) {
                MutableTreeNode node = new DefaultMutableTreeNode(dataRow);
                this.treeModel.insertNodeInto(node, parentNode, parentNode.getChildCount());
                return node;
            }
            return null;
        }
    }

     private MutableTreeNode getTreeNode(int id) {
        MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
        return getTreeNode(root, id);
    }
     
    private MutableTreeNode getTreeNode(MutableTreeNode node, int dataRowId) {
        Object uObject = ((DefaultMutableTreeNode)node).getUserObject();
        if (uObject instanceof IDataRow) {
            if ((Integer) ((IDataRow) uObject).getColumnValue("id") == dataRowId) {
                return node;
            }
        }
        Enumeration<MutableTreeNode> children = node.children();
        while (children.hasMoreElements()) {
            MutableTreeNode tempNode = children.nextElement();
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) tempNode;
            Object userObject = n.getUserObject();
            if (userObject instanceof IDataRow) {
                if ((Integer) ((IDataRow) userObject).getColumnValue("id") == dataRowId) {
                    return n;
                }
            }
            if (node.getChildCount() > 0) {
                MutableTreeNode tNode = getTreeNode(tempNode, dataRowId);
                if (tNode != null)
                {
                    return tNode;
                }
            }
        }
        return null;
    }
    private void setSelectedType(IDataRow dataRow) {
        MutableTreeNode node = this.getTreeNode((Integer) dataRow.getColumnValue("id"));
        if (node == null) {
            //should never come here!
            return;
        }
        MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
        ArrayList<MutableTreeNode> pathNodes = new ArrayList<MutableTreeNode>();
        pathNodes.add(0, node);
        while (node.getParent() != root) {
            node = (MutableTreeNode) node.getParent();
            if (node == null) {
                break;
            }
            pathNodes.add(0, node);
        }
        pathNodes.add(0, root);
        this.typesTree.setSelectionPath(new TreePath(pathNodes.toArray()));
    /*
    MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
    int count = root.getChildCount();
    for (int i = 0; i < count; i++)
    {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
    if (node.getUserObject() == dataRow)
    {
    this.typesTree.setSelectionPath(new TreePath(new Object[]{root, node}));
    return;
    }
    }
    this.typesTree.setSelectionPath(new TreePath(new Object[]{root}));
     */
    }

    //private MutableTreeNode get
    public void doFilter(TreePath path) {
        if (this.dataSource == null) {
            return; //尚未初始化
        }

        Object obj = path.getLastPathComponent();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
        Object userObject = node.getUserObject();
        if (userObject instanceof ObjectDataRow) {
           /*
            ObjectDataRow dataRow = (ObjectDataRow) userObject;
            this.dataSource.setFilter("catalog == ?", new Object[]{dataRow.getUserObject()});
             */
            //2009-6-24
            List<IDataRow> catalogs = this.getCurrentTreeDataRows();
            if (catalogs == null)
            {
                return;
            }
            ArrayList<Object> objs = new ArrayList<Object>();
            for(IDataRow dr:catalogs)
            {
                objs.add(((ObjectDataRow)dr).getUserObject());
            }
            this.dataSource.setMutipleEqualFilter("catalog", objs.toArray());


        } else {
            this.dataSource.setFiltered(false);
        }
        //
        if (this.dataSource.getRowCount() > 0)
        {
            this.dataTable.setSelectedRow(0);
            //this.dataSource.first();//.setCurrentDataRow(this.da);
        }
    }

     protected List<IDataRow> getCurrentTreeDataRows()
    {
        IDataRow dataRow = this.typeDataSource.getCurrentDataRow();
        if (dataRow == null)
        {
            return null;
        }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.getTreeNode((Integer)dataRow.getColumnValue("id"));
        ArrayList<IDataRow> dataRows = new ArrayList<IDataRow>();
        this.addDataRows(node, dataRows);
        return dataRows;
    }

    private void addDataRows(DefaultMutableTreeNode node, List<IDataRow> dataRows)
    {
        dataRows.add((IDataRow)node.getUserObject());
        for(int i = 0; i < node.getChildCount(); i++)
        {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode)node.getChildAt(i);
            this.addDataRows(n, dataRows);
        }
    }

    public Object getCurrentCatalog() {
        TreePath path = this.typesTree.getSelectionModel().getSelectionPath();
        if (path != null) {
            Object obj = path.getLastPathComponent();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
            Object userObject = node.getUserObject();
            if (userObject instanceof ObjectDataRow) {
                return ((ObjectDataRow) userObject).getUserObject();
            }
        }
        return null;
    }

    public void refresh()
    {
        this.refreshCatalogs();
        this.refreshDatas();
    }
    public void refreshCatalogs()
    {
        List<ProductCatalog> catalogs = Main.getMainFrame().getObjectsPool().getProductCatalogs();
        this.typeDataSource = DefaultDataSource.createDataSource("id", catalogs, null);
        MutableTreeNode root = new DefaultMutableTreeNode("商品分类");
	    this.treeModel.setRoot(root);        
    }
	
	private void initComponents()
	{
        this.setSize(600, 300);
        this.typesTree = new JTree();
        this.treeModel = (DefaultTreeModel) this.typesTree.getModel();

        JScrollPane p = new JScrollPane();
        p.setViewportView(this.typesTree);
        //this.add("West", p);


		this.dataTable = new JDataTable();
        this.dataSource  = this.dataTable.getDataSource();
		this.setLayout(new BorderLayout());
		JScrollPane tablePane = new JScrollPane();
		tablePane.setViewportView(this.dataTable);
		//this.add("Center", tablePane);
        p.setPreferredSize(new Dimension(120, 100));
        //JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p, tablePane);
        this.add("West", p);
        this.add("Center", tablePane);
		this.selectButton = new CButton("选择(&S)");
		CPanel sPanel = new CPanel();
		sPanel.setPreferredSize(new Dimension(600, 30));
		//sPanel.setLayout(null);
		      
        sPanel.addComponent(this.quickInputField, 180, 3, 120, 20, "助记码", 60);
        sPanel.addComponent(selectButton, 10, 3, 80, 25);
		this.add("South", sPanel);
		selectButton.addActionListener(this);
		this.dataTable.addMouseListener(this);
        this.quickInputField.addKeyListener(this);
	}	
	
	protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("分类编码");
		column.setColumnName("catalog.number");
		column.setWidth(60);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("分类名称");
		column.setColumnName("catalog.name");
		column.setWidth(60);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("商品编号");
		column.setColumnName("number");
		column.setWidth(80);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("商品名称");
		column.setColumnName("name");
		column.setWidth(140);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("规格");
		column.setColumnName("spec");
		column.setWidth(80);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("生产厂商");
		column.setColumnName("factory");
		column.setWidth(160);
		columnModel.addColumn(column);
//		column = new JDataTableColumn();
//		column.setHeaderText("简称");
//		column.setColumnName("alias");
//		column.setWidth(60);
//		columnModel.addColumn(column);
//		column = new JDataTableColumn();
//		column.setHeaderText("助记符");
//		column.setColumnName("code");
//		column.setWidth(50);
//		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("产地");
		column.setColumnName("area");
		column.setWidth(60);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("单位");
		column.setColumnName("smallUnit");
		column.setWidth(40);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("税率");
		column.setColumnName("taxRate");
		column.setWidth(40);
		columnModel.addColumn(column);
//		column = new JDataTableColumn();
//		column.setHeaderText("停用");
//		column.setColumnName("status");
//		column.setWidth(40);
//		column.setValueType(Boolean.class);
//		columnModel.addColumn(column);
	}
	
	public void refreshDatas()
	{
		IDataSource model = this.dataTable.getDataSource();
        model.clear();
		this.dataTable.getDataSource().setKeyColumnName("id");
		List<Product> products = Main.getMainFrame().getObjectsPool().getUsableProducts();
		for(Product product:products)
		{
			IDataRow dataRow = new ObjectDataRow(product, "id", null);
            model.insertDataRow(dataRow);
		}	
	}

    @Override
    public void setSelectedItem(Object value) {
        if (value instanceof Product)
        {
            //Product product = (Product)value;
            List<IDataRow> dataRows = this.dataSource.getDataRows();
            for(IDataRow dataRow:dataRows)
            {
                Object p = ((ObjectDataRow)dataRow).getUserObject();
                if (p.equals(value))
                {
                    this.dataSource.setCurrentDataRow(dataRow);
                    break;
                }
            }
        }
    }


	
	public Object getSelectedItem()
	{
        if (this.dataSource.getRowCount() <= 0)
        {
            return null;
        }
        return this.dataTable.getSelectedDataRow();
        //return ((ObjectDataRow)this.dataTable.getSelectedDataRow()).getUserObject();
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		this.fireActionPerformed(evt);
	}

}
