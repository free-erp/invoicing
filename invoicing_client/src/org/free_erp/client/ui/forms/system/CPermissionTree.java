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
            throw   new RuntimeException("û��Module��㱻ѡ��");
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
            throw  new RuntimeException("û��Module��㱻ѡ��");
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

//        public PermissionItem(String title) //��ʱ���ã���ɺ�ɾ��
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
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("����ģ��");
        model.setRoot(root);
        DefaultMutableTreeNode moduleNode = new DefaultMutableTreeNode("��������");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        //
        //this.expandPath(new TreePath(new Object[]{root, moduleNode}));
        DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ����", "baseProduct"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�ͻ�����", "baseCustomer"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("Ա������", "baseEmployee"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�ֿ����", "baseStorage"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��ƿ�Ŀ", "cwkjkm"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        moduleNode = new DefaultMutableTreeNode("ϵͳ�ڳ�");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("����ڳ�", "initKcqc"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ŀ����ڳ�","cwkmyeqc"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("Ӧ���ڳ�","cwysqc"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("Ӧ���ڳ�","cwyfqc"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        moduleNode = new DefaultMutableTreeNode("�ɹ�����");
//        model.insertNodeInto(moduleNode, root, root.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("����ڳ�"));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ŀ����ڳ�"));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("Ӧ���ڳ�"));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("Ӧ���ڳ�"));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("�ɹ�����");
        model.insertNodeInto(moduleNode, root, root.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("�ɹ�����","cgdd"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�ɹ���","cgd"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�ɹ��˻�","cgth"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("���۹���");
        model.insertNodeInto(moduleNode, root, root.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ����","xsbj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ����","xssptj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("���۶���","xsdd"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("���۵�","xsd"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("�����˻�","xsth"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("�ֿ����");
        model.insertNodeInto(moduleNode, root, root.getChildCount());

                itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ���","storageSprk"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ����","storageCpck"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��汨��","storageKcby"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��汨��","storageKcbs"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�ƿ����","storageYkgl"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�̵����","storagePdgl"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("�����","storageKcbj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        itemNode = new DefaultMutableTreeNode(new PermissionItem("��������","storageSxxz"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("Ӧ��Ӧ��");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("����Ԥ��","cwxsyf"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�տ����","cwskjs"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�ɹ�Ԥ��","cwcgyf"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�������","cwfkjs"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("�ֽ�����");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ӫ����","cwjyfy"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("һ�����","cwybfy"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��������","cwqtsr"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�ֽ�����","cwxjyh"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());


        moduleNode = new DefaultMutableTreeNode("ϵͳά��");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�û�����","systemYhxx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��˾��Ϣ","systemGsxx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�޸�����","systemXgmm"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("ϵͳ��־","systemXtrz"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("�ɹ�����");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("�ɹ�������ѯ","reportCdcx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("������Ʒͳ��","reportCdsptj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("������Ʒ��ѯ","reportCdspcx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("��������ѯ",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//         itemNode = new DefaultMutableTreeNode(new PermissionItem("�������ͳ��",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("������Ʒ���ͳ��",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("������Ʒ����ѯ",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("����ҵ��Աͳ��","reportCdywytj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
       itemNode = new DefaultMutableTreeNode(new PermissionItem("��������ͳ��","reportCdkstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

               itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ�ɹ�ͳ��","reportCgtj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

               itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ�ɹ���ϸ","reportCgmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ӧ�̲ɹ�ͳ��","reportCgkstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ӧ�̲ɹ���ϸ","reportCgksmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("ְԱ�ɹ�ͳ��","reportCgywytj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("ְԱ�ɹ���ϸ","reportCgywymx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());



//        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ�ɹ�����",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
//        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ�ɹ��۸񲨶�",""));
//        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("���۱���");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("������ѯ","reportXdcx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("������Ʒͳ��","reportXdsptj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("������Ʒ��ѯ","reportXdspcx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("�����ͻ�ͳ��","reportXdkstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("����ְԱͳ��","reportXdywytj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ʒ������ϸ","reportXsmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
       itemNode = new DefaultMutableTreeNode(new PermissionItem("��������ͳ��","reportXstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ӧ������ͳ��","reportXskstj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ӧ��������ϸ","reportXsksmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("ְԱ����ͳ��","reportXsywytj"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
               itemNode = new DefaultMutableTreeNode(new PermissionItem("ְԱ������ϸ","reportXywymx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("�ֿⱨ��");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("�����Ʒ���","reportKcspye"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("��������ϸ","reportKcrkmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("��������ϸ","reportKcckmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("�����Ʒ���","reportKccprk"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("�����Ʒ����","reportKccpck"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("�����Ʒ������","reportKccpsxx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("����ƿ���ϸ","reportKcykmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("����̵���ϸ","reportKcpdmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("��汨����ϸ","reportKcbsmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("��汨����ϸ","reportKcbymx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("�������ϸ","reportKcbjmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("����ƿ��ѯ","reportKcyk"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("��汨���ѯ","reportKcbs"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
          itemNode = new DefaultMutableTreeNode(new PermissionItem("��汨���ѯ","reportKcby"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        moduleNode = new DefaultMutableTreeNode("���񱨱�");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ŀ����ѯ","reportCwkmye"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("�ֽ������±���","reportCwxjyhybb"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("�ֽ�������ϸ","reportCwxjyhmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("�ճ����ñ���","reportCwrcfybb"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("�ճ�������ϸ","reportCwrcfymx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("���������±���","reportCwqtsrybb"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
         itemNode = new DefaultMutableTreeNode(new PermissionItem("����������ϸ","reportCwqtsrmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());

        moduleNode = new DefaultMutableTreeNode("���㱨��");
        model.insertNodeInto(moduleNode, root, root.getChildCount());
        this.expandPath(new TreePath(new Object[]{root, root.getChildAt(0)}));
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ӧ��Ӧ�����","reportJsgxsyfye"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
        itemNode = new DefaultMutableTreeNode(new PermissionItem("��Ӧ��δ����ϸ","reportJsgxswjmx"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
       itemNode = new DefaultMutableTreeNode(new PermissionItem("����Ӧ�����","reportJsgksysye"));
        model.insertNodeInto(itemNode, moduleNode, moduleNode.getChildCount());
       itemNode = new DefaultMutableTreeNode(new PermissionItem("����δ����ϸ","reportJsgkswfmx"));
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
