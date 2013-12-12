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

import org.free_erp.client.ui.util.ObjectInvokerUtil;
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.service.entity.system.Permission;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author afa
 */
public class CPermissionTree extends JTree
{
    private DefaultTreeModel model;
    private Permission permission;
    private boolean modified = false;
    public CPermissionTree(Permission permission)
    {
        this.permission = permission;
        this.model = (DefaultTreeModel)this.getModel();
        this.setEditable(true);
        this.setCellEditor(new PermissionTreeEditor());
        this.setCellRenderer(new PermissionTreeCellRenderer());
        initPermissionTree();

    }
    public int getCurrentModulePermissionValue()
    {
        TreePath path = this.getSelectionPath();
        if (path == null)
        {
            return -1;
        }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        Object obj = node.getUserObject();
        if (obj instanceof PermissionItem)
        {
            return ((PermissionItem)obj).getPermissionValue();
        }
        return -1;
    }

    public void saveCurrentModulePermissionValue(int value)
    {
        TreePath path = this.getSelectionPath();
        if (path == null)
        {
            throw   new RuntimeException("没有Module结点被选中");
        }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
        Object obj = node.getUserObject();
        if (obj instanceof PermissionItem)
        {
            ((PermissionItem)obj).setPermissionValue(value);
            this.modified = true;
        }
        else
        {
            throw  new RuntimeException("没有Module结点被选中");
        }
    }
public Permission getPermisson(){
return  permission;

}
    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }



    class PermissionItem
    {
        private String title;
        private String bindingProperty;
        public PermissionItem(String title, String bindingProperty)
        {
            this.title = title;
            this.bindingProperty = bindingProperty;
        }

//        public PermissionItem(String title) //临时性用，完成后删除
//        {
//            this.title = title;
//            this.bindingProperty = "baseProduct";
//        }

        public Integer getPermissionValue()
        {
            if (permission != null)
            {
                return (Integer)ObjectInvokerUtil.getProperty(permission, this.bindingProperty);
            }
            return -1;
        }

        public void setPermissionValue(int value)
        {
            if (permission != null)
            {
        ObjectInvokerUtil.setProperty(permission, this.bindingProperty, Integer.class, value);
            }
        }

        public String toString()
        {
            return this.title;
        }

        public boolean hasModulePermission() {
            return PermissionUtilities.hasModulePermission(this.getPermissionValue());
        }

        public void setHasModulePermission(boolean hasModulePermission) {
            this.setPermissionValue(PermissionUtilities.setModulePermission(this.getPermissionValue(), hasModulePermission));
     }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


    }

    private void initPermissionTree()
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("功能模块");
        model.setRoot(root);
        DefaultMutableTreeNode moduleNode = new DefaultMutableTreeNode("基础数据");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        //
        //this.expandPath(new TreePath(new Object[]{root, moduleNode}));
        DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(new PermissionItem("商品管理", "baseProduct"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("客户管理", "baseCustomer"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("员工管理", "baseEmployee"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("仓库管理", "baseStorage"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("会计科目", "cwkjkm"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        moduleNode = new DefaultMutableTreeNode("系统期初");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("库存期初", "initKcqc"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("科目余额期初","cwkmyeqc"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("应收期初","cwysqc"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("应付期初","cwyfqc"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        moduleNode = new DefaultMutableTreeNode("采购管理");
//        model.insertNodeInto(moduleNode, root, root.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("库存期初"));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("科目余额期初"));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("应收期初"));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("应付期初"));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("采购管理");
        model.insertNodeInto(moduleNode, root, root.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("采购订单","cgdd"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("采购单","cgd"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("采购退货","cgth"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("销售管理");
        model.insertNodeInto(moduleNode, root, root.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("商品报价","xsbj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("商品调价","xssptj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("销售订单","xsdd"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("销售单","xsd"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("销售退货","xsth"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("仓库管理");
        model.insertNodeInto(moduleNode, root, root.getChildCount());

                itemNode = new DefaultMutableTreeNode(new PermissionItem("商品入库","storageSprk"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("商品出库","storageCpck"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("库存报溢","storageKcby"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("库存报损","storageKcbs"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("移库管理","storageYkgl"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("盘点管理","storagePdgl"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("库存变价","storageKcbj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("上下限制","storageSxxz"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("应收应付");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("销售预收","cwxsyf"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("收款结算","cwskjs"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("采购预付","cwcgyf"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("付款结算","cwfkjs"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("现金银行");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("经营费用","cwjyfy"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("一般费用","cwybfy"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("其他收入","cwqtsr"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("现金银行","cwxjyh"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());


        moduleNode = new DefaultMutableTreeNode("系统维护");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("用户管理","systemYhxx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("公司信息","systemGsxx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("修改密码","systemXgmm"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("系统日志","systemXtrz"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("采购报表");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("采购订单查询","reportCdcx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单商品统计","reportCdsptj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单商品查询","reportCdspcx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单余额查询",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//         itemNode = new DefaultMutableTreeNode(new PermissionItem("订单余额统计",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单商品余额统计",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单商品余额查询",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单业务员统计","reportCdywytj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
       itemNode = new DefaultMutableTreeNode(new PermissionItem("订单客商统计","reportCdkstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

               itemNode = new DefaultMutableTreeNode(new PermissionItem("商品采购统计","reportCgtj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

               itemNode = new DefaultMutableTreeNode(new PermissionItem("商品采购明细","reportCgmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("供应商采购统计","reportCgkstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("供应商采购明细","reportCgksmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("职员采购统计","reportCgywytj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("职员采购明细","reportCgywymx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());



//        itemNode = new DefaultMutableTreeNode(new PermissionItem("商品采购汇总",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("商品采购价格波动",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("销售报表");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单查询","reportXdcx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单商品统计","reportXdsptj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单商品查询","reportXdspcx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("订单客户统计","reportXdkstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("订单职员统计","reportXdywytj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("商品销售明细","reportXsmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
       itemNode = new DefaultMutableTreeNode(new PermissionItem("客商销售统计","reportXstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("供应商销售统计","reportXskstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("供应商销售明细","reportXsksmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("职员销售统计","reportXsywytj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("职员销售明细","reportXywymx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("仓库报表");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存商品余额","reportKcspye"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存入库明细","reportKcrkmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存出库明细","reportKcckmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存商品入库","reportKccprk"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存商品出库","reportKccpck"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存商品上下限","reportKccpsxx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存移库明细","reportKcykmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存盘点明细","reportKcpdmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存报损明细","reportKcbsmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存报溢明细","reportKcbymx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存变价明细","reportKcbjmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存移库查询","reportKcyk"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存报损查询","reportKcbs"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("库存报溢查询","reportKcby"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        moduleNode = new DefaultMutableTreeNode("财务报表");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("科目余额查询","reportCwkmye"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("现金银行月报表","reportCwxjyhybb"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("现金银行明细","reportCwxjyhmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("日常费用报表","reportCwrcfybb"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("日常费用明细","reportCwrcfymx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("其他收入月报表","reportCwqtsrybb"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("其他收入明细","reportCwqtsrmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("结算报表");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        this.expandPath(new TreePath(new Object[]{root, root.getChildAt(0)}));
        itemNode = new DefaultMutableTreeNode(new PermissionItem("供应商应付余额","reportJsgxsyfye"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("供应商未结明细","reportJsgxswjmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
       itemNode = new DefaultMutableTreeNode(new PermissionItem("客商应付余额","reportJsgksysye"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
       itemNode = new DefaultMutableTreeNode(new PermissionItem("客商未结明细","reportJsgkswfmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

    }

    @Override
    public boolean isPathEditable(TreePath path) {
        if (path.getPathCount() < 3)
        {
            return false;
        }
        return true;
    }

class PermissionTreeEditor extends AbstractCellEditor implements TreeCellEditor
    {
        private JCheckBox checkBox = new JCheckBox();
        private JTextField textField = new JTextField();
        private JComponent editingComp;
        private PermissionItem item;
        public PermissionTreeEditor()
        {
            checkBox.setForeground(Color.RED);
            textField.setForeground(Color.RED);
        }
        public Object getCellEditorValue() {
            if (editingComp == checkBox)
            {
                boolean r = checkBox.isSelected();
                item.setHasModulePermission(r);
                return r;
            }
            else
            {
                return textField.getText();
            }
        }

        public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object obj = node.getUserObject();
            if (obj instanceof PermissionItem)
            {
                item = (PermissionItem)obj;
                checkBox.setSelected(item.hasModulePermission());
                checkBox.setText(item.toString());
                //checkBox.setOpaque(true);
                //checkBox.setBackground(Color.blue);
                editingComp = checkBox;
            }
            else
            {
                textField.setText(String.valueOf(value));
                editingComp = textField;
            }
            return editingComp;
        }
    }
    class PermissionTreeCellRenderer implements TreeCellRenderer
    {

        private JCheckBox checkBox = new JCheckBox();
        private JLabel label = new JLabel();
       

        public PermissionTreeCellRenderer()
        {
            
           checkBox.setOpaque(false);
           label.setOpaque(false);
           checkBox.setBackground(Color.white);//fix bug
//            checkBox.setBackground(Color.BLUE);
//            label.setBackground(Color.BLUE);
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object obj = node.getUserObject();
            if (obj instanceof PermissionItem)
            {
                PermissionItem item = (PermissionItem)obj;
                checkBox.setSelected(item.hasModulePermission());
                checkBox.setText(item.toString());
                if (selected)
                {
                    //checkBox.setOpaque(true);
                    //checkBox.setFont(selectedFont);
                    checkBox.setForeground(Color.red);
                }
                else
                {
                    //.setFont(oldFont);
                    checkBox.setForeground(Color.black);
                    //checkBox.setOpaque(false);
                }
                return checkBox;
            }
            else
            {
                label.setText(String.valueOf(value));
                if (selected)
                {
                    //label.setFont(selectedFont);
                    label.setForeground(Color.red);
                    //label.setOpaque(true);
                }
                else
                {
                    //label.setFont(oldFont);
                    label.setForeground(Color.black);
                    //label.setOpaque(false);
                }
                return label;
            }
        }
    }

    public void setDefaultSelectedNode()
    {
        DefaultMutableTreeNode  rootNode = (DefaultMutableTreeNode)this.treeModel.getRoot();
        DefaultMutableTreeNode catalogNode = (DefaultMutableTreeNode)rootNode.getChildAt(0);
        DefaultMutableTreeNode moduleNode = (DefaultMutableTreeNode)catalogNode.getChildAt(0);
        TreePath path = new TreePath(new Object[]{rootNode, catalogNode, moduleNode});
        this.setSelectionPath(path);
    }
}
