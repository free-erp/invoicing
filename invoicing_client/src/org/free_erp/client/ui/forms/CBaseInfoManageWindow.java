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
package org.free_erp.client.ui.forms;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.data.DataRowChangedListener;
import com.jdatabeans.beans.data.DataRowEvent;
import com.jdatabeans.beans.data.DataRowRolledEvent;
import com.jdatabeans.beans.data.DataRowRolledListener;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.core.ObjectsPool;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ObjectInvokerUtil;
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.EmployeeCatalog;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.exception.LogicException;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.ICustomerService;
import org.free_erp.service.logic.IEmployeeService;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.IProductService;
import org.free_erp.service.logic.IStorageService;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
/**
 * Changer: liufei
 */
public abstract class CBaseInfoManageWindow extends CBaseFrame implements ActionListener, TreeSelectionListener, DataRowChangedListener, DataRowRolledListener{
    private JToolBar toolBar;
    protected JButton newButton;
    protected JButton editButton;
    protected JButton removeButton;
    protected JButton importButton;
    protected JButton exitButton;
    protected JButton printButton;
    protected JButton newTypeButton;
    protected JButton editTypeButton;
    protected JButton removeTypeButton;
    protected JButton stopButton;
    protected JButton activeButton;
    protected JButton newType;
    protected JButton editType;
    protected JButton removeType;
    protected JTree typesTree;
    protected JDataTable dataTable;
    protected IDataSource typeDataSource;
    protected JTextField searchField;
    protected DefaultTreeModel treeModel;
    protected JPanel leftPane;
    protected IDataSource dataSource;
    protected static IDbSupport dbSupport;
    protected CCatalogDialog typeDialog;
    protected JMenuItem newMenuItem;
    protected JMenuItem editMenuItem;
    protected JMenuItem removeMenuItem;
    protected JMenuItem newTypeMenuItem;
    protected JMenuItem editTypeMenuItem;
    protected JMenuItem removeTypeMenuItem;
    protected JPopupMenu typePopMenu;
    protected JPopupMenu mainPopMenu;
    protected MouseAdapter typeMouseAdapter;
    public void valueChanged(TreeSelectionEvent e) {
        Object obj = e.getPath().getLastPathComponent();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
        Object userObject = node.getUserObject();
        if (userObject instanceof IDataRow) {
            this.typeDataSource.setCurrentDataRow((IDataRow) userObject);
        }
        doFilter(e.getPath());
    }
    public CBaseInfoManageWindow(String title) {
        super(title);
        this.createDbSupport();
        initComponents();
        this.dataSource = this.dataTable.getDataSource();
        this.dataSource.setKeyColumnName("id");
        
        //this.dataTable.addMouseListener(this);
        this.editButton.setVisible(false);
        this.dataTable.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent evt)
            {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    doEdit();
                }
            }
        });
    }
    
    public void selectCatalog(Object catalog)
    {
        MutableTreeNode node = this.getTreeNode((Integer) ObjectInvokerUtil.getProperty(catalog, "id"));
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
    }
   protected void permission(Integer per) {
        if (Main.getMainFrame().getCompany() != null) {
            if (!PermissionUtilities.hasAddPermission(per)) {
                this.newButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasEditPermission(per)) {
                this.editButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasRemovePermission(per)) {
                this.removeButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasPrintPermission(per)) {
                this.printButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasAddTypePermission(per)) {
                this.newTypeButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasEditypePermission(per)) {
                this.editTypeButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasRemoveTypePermission(per)) {
                this.removeTypeButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasImportPermission(per)) {
                this.importButton.setEnabled(false);
            }
        }
    }
    private void createDbSupport()
    {
        dbSupport = new IDbSupport() {
        private ObjectsPool pool = Main.getMainFrame().getObjectsPool();
        private IProductService productService = Main.getServiceManager().getProductService();
        private ICustomerService customerService = Main.getServiceManager().getCustomerService();
        private IEmployeeService employeeService = Main.getServiceManager().getEmployeeService();
        private IStorageService storageService = Main.getServiceManager().getStorageService();
        private IPermissionsService permissionsService = Main.getServiceManager().getPermissionsService();
        private IAccountingService accountingService = Main.getMainFrame().systemType == 0?Main.getServiceManager().getAccountingService():null;
        public void delete(Object obj) {
            if (obj instanceof Product)
            {
                Product p = (Product) obj;
                productService.deleteProduct(p);
                pool.refreshProducts();
                pool.refreshUsableProducts();
            }
            else if (obj instanceof Subject)
            {
                accountingService.deleteSubject((Subject)obj);
                pool.refreshSubjects();
                pool.refreshBankSubjectType();
                pool.refreshCommonExpenseType();
                pool.refreshOtherIncomeType();
                pool.refreshBussinessExpenseType();
            }
            else if (obj instanceof ProductCatalog)
            {
                ProductCatalog c = (ProductCatalog) obj;
                productService.deleteCatalog(c);
                pool.refreshProducts();
                pool.refreshUsableProducts();
            } 
            else if (obj instanceof CustomerCatalog)
            {
                CustomerCatalog c = (CustomerCatalog) obj;
                customerService.deleteCatalog(c);
                pool.refreshAllCustomers();
                pool.refreshCustomers();
            }
            else if (obj instanceof Customer)
            {
                Customer c = (Customer) obj;
                customerService.deleteCustomer(c);
                pool.refreshAllCustomers();
                pool.refreshCustomers();
            } 
            else if (obj instanceof EmployeeCatalog)
            {
                EmployeeCatalog c = (EmployeeCatalog) obj;
                employeeService.deleteCatalog(c);
                pool.refreshDepartments();
                pool.refreshEmployees();
                pool.refreshAllEmployees();
            } 
            else if (obj instanceof Employee)
            {
                Employee c = (Employee) obj;
                employeeService.deleteEmployee(c);
                pool.refreshEmployees();
                pool.refreshAllEmployees();
            } 
            else if (obj instanceof Storage)
            {
                Storage s = (Storage) obj;
                storageService.removeStorage(s);
                pool.refreshStorages();
                pool.refreshStoragesOut();
                pool.refreshAllStorages();
            } 
            else if (obj instanceof User)
            {
                User s = (User) obj;
                permissionsService.removeUser(s);
            }
            else {
                throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
            }
        }
        public void save(Object obj)
        {
            if (obj instanceof Product)
            {
                Product p = (Product) obj;
                productService.saveProduct(p);
                pool.refreshProducts(); //单机版本没有问题！
                pool.refreshUsableProducts();
            }
            else if (obj instanceof Subject)
            {
                accountingService.saveSubject((Subject)obj);
                pool.refreshSubjects();
                pool.refreshBankSubjectType();
                pool.refreshCommonExpenseType();
                pool.refreshOtherIncomeType();
                pool.refreshBussinessExpenseType();
            }
            else if (obj instanceof ProductCatalog)
            {
                ProductCatalog c = (ProductCatalog) obj;
                productService.saveCatalog(c);
                pool.refreshProductCatalogs();
                pool.refreshProducts();
                pool.refreshUsableProducts();
            } else if (obj instanceof CustomerCatalog)
            {
                CustomerCatalog c = (CustomerCatalog) obj;
                customerService.saveCatalog(c);
                pool.refreshCustomerCatalogs();
                pool.refreshAllCustomers();
                pool.refreshCustomers();
            }
            else if (obj instanceof Customer)
            {
                Customer c = (Customer) obj;
                customerService.saveCustomer(c);
                pool.refreshAllCustomers();
                pool.refreshCustomers();
            }
            else if (obj instanceof EmployeeCatalog)
            {
                EmployeeCatalog c = (EmployeeCatalog) obj;
                employeeService.saveCatalog(c);
                pool.refreshDepartments();
                pool.refreshEmployees();
                pool.refreshAllEmployees();
            } 
            else if (obj instanceof Employee)
            {
                Employee c = (Employee) obj;
                employeeService.saveEmployee(c);
                pool.refreshEmployees();
                pool.refreshAllEmployees();
            }
            else if (obj instanceof Storage)
            {
                Storage s = (Storage) obj;
                storageService.saveStorage(s);
                pool.refreshStorages();
                pool.refreshStoragesOut();
                pool.refreshAllStorages();
            }
            else if (obj instanceof User)
            {
                User s = (User) obj;
                permissionsService.saveUser(s);
            }
            else {
                throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
            }
        }
        public void deleteAll(Collection<Object> objs) {
            // TODO Auto-generated method stub
            JOptionPane.showMessageDialog(null, "暂未实现!");
        }
        public void saveAll(Collection<Object> objs) {
            // TODO Auto-generated method stub
            JOptionPane.showMessageDialog(null, "暂未实现!");
        }
      };
    }
    protected void initComponents() {
        MainFrame mainFrame = Main.getMainFrame();
        this.toolBar = new JToolBar();
        this.toolBar.setFloatable(false);
        
        newButton = new CButton("新建(&N)");
        newButton.setIcon(mainFrame.getIcon("new"));
        editButton = new CButton("修改(&M)");
        editButton.setIcon(mainFrame.getIcon("edit"));
        removeButton = new CButton("删除(&D)");
        removeButton.setIcon(mainFrame.getIcon("delete"));
        newTypeButton = new CButton("添加类别(&A)");
        newTypeButton.setIcon(mainFrame.getIcon("addtype"));
        editTypeButton = new CButton("修改类别(&E)");
        editTypeButton.setIcon(mainFrame.getIcon("edittype"));
        removeTypeButton = new CButton("删除类别(&R)");
        removeTypeButton.setIcon(mainFrame.getIcon("deletetype"));
        this.printButton = new CButton("打印(&P)");
        this.printButton.setIcon(mainFrame.getIcon("print"));
        this.exitButton = new CButton("关闭(&C)");
        this.exitButton.setIcon(mainFrame.getIcon("close"));
        importButton = new CButton("导入(&I)");
        importButton.setIcon(mainFrame.getIcon("import"));
        newMenuItem = new JMenuItem(newButton.getText());
        editMenuItem = new JMenuItem(editButton.getText());
        removeMenuItem = new JMenuItem(removeButton.getText());
        newTypeMenuItem = new JMenuItem(newTypeButton.getText());
        editTypeMenuItem = new JMenuItem(editTypeButton.getText());
        removeTypeMenuItem = new JMenuItem(removeTypeButton.getText());
        stopButton = new JButton("停用");
        stopButton.setIcon(mainFrame.getIcon("deletetype"));
        activeButton = new JButton("启用");
        activeButton.setIcon(mainFrame.getIcon("addtype"));

        newMenuItem.addActionListener(this);
        editMenuItem.addActionListener(this);
        removeMenuItem.addActionListener(this);
        newTypeMenuItem.addActionListener(this);
        editTypeMenuItem.addActionListener(this);
        removeTypeMenuItem.addActionListener(this);
        newButton.addActionListener(this);
        editButton.addActionListener(this);
        removeButton.addActionListener(this);
        newTypeButton.addActionListener(this);
        editTypeButton.addActionListener(this);
        removeTypeButton.addActionListener(this);
        importButton.addActionListener(this);
        this.printButton.addActionListener(this);
        this.exitButton.addActionListener(this);
        this.stopButton.addActionListener(this);
        this.activeButton.addActionListener(this);
        this.toolBar.add(newButton);
        this.toolBar.add(editButton);
        this.toolBar.add(removeButton);
        this.toolBar.addSeparator();
        this.toolBar.add(newTypeButton);
        this.toolBar.add(editTypeButton);
        this.toolBar.add(removeTypeButton);
        this.toolBar.addSeparator();
        this.toolBar.add(importButton);
        //this.toolBar.addSeparator();
        this.toolBar.add(printButton);
        this.toolBar.add(stopButton);
        this.toolBar.add(activeButton);
        //this.toolBar.addSeparator();
        this.toolBar.add(new JPanel());
        this.toolBar.add(this.exitButton);
        this.getContentPane().add("North", this.toolBar);
        leftPane = new JPanel();
        leftPane.setPreferredSize(new Dimension(130, 300));
        leftPane.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("分类");
        titleLabel.setSize(130, 30);
        leftPane.add("North", titleLabel);
        this.typesTree = new JTree();
        this.typesTree.setFont((Font)UIManager.getDefaults().get("menu.font"));//new Font("宋体", Font.PLAIN, 12));//UIManager.getFont("menu.font"));
        this.treeModel = (DefaultTreeModel) this.typesTree.getModel();
        JScrollPane p = new JScrollPane();
        p.setViewportView(this.typesTree);
        leftPane.add("Center", p);
        JPanel rightPane = new JPanel();
        rightPane.setLayout(new BorderLayout());
        JPanel rightTopPane = new JPanel();
        rightTopPane.setPreferredSize(new Dimension(600, 40));
        rightTopPane.setLayout(null);
        this.searchField = new JTextField();
        JLabel label = new JLabel("查询条件:");
        rightTopPane.add(label);
        rightTopPane.add(this.searchField);
        label.setBounds(10, 5, 80, 30);
        this.searchField.setBounds(100, 10, 160, 20);
        JScrollPane clientPane = new JScrollPane();
        rightPane.add("Center", clientPane);
        dataTable = new JDataTable();
        dataTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2) {
                    doEdit();
                }
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
        });
        clientPane.setViewportView(dataTable);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
        splitPane.setDividerLocation(130);
        this.getContentPane().add("Center", splitPane);
        //this.getContentPane().add("Center", rightPane);
        this.typesTree.addTreeSelectionListener(this);
        this.initTypes();
        //仓库信息没有typetree,对于尚未实现typeDataSource的避免空指针
        if (this.typeDataSource != null) {
            this.typeDataSource.addDataRowChangedListener(this);
            this.typeDataSource.addDataRowRolledListener(this);
            this.initTypeTreeNodes();
        }
        //
        typePopMenu = new JPopupMenu();
        
        typePopMenu.add(newTypeMenuItem);
        
         
        typePopMenu.add(editTypeMenuItem);
         
         
        typePopMenu.add(removeTypeMenuItem);
    
        typePopMenu.addSeparator();
        typePopMenu.add(newMenuItem);
        this.typesTree.add(typePopMenu);
        
        mainPopMenu = new JPopupMenu();
          
        mainPopMenu.add(newMenuItem);
          
        mainPopMenu.add(editMenuItem);
            
        mainPopMenu.add(removeMenuItem);
           
        this.dataTable.add(mainPopMenu);
        typeMouseAdapter = new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e) {
                int mods = e.getModifiers();
                if ((mods & InputEvent.BUTTON3_MASK) != 0) {
                    typePopMenu.show((Component) e.getSource(), e.getX(), e.getY());
                }
            }
        };
        this.typesTree.addMouseListener(typeMouseAdapter);
        this.dataTable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e) {
                int mods = e.getModifiers();
                if ((mods & InputEvent.BUTTON3_MASK) != 0) {
                    mainPopMenu.show((Component) e.getSource(), e.getX(), e.getY());
                }
            }
        });
    }
    /**
     * 子类重载此方法!
     */
    protected void doAdd() {
        JOptionPane.showMessageDialog(this, "尚未实现!");
    }
    protected void doEdit() {
        JOptionPane.showMessageDialog(this, "尚未实现!");
    }
    protected void doRemove() {
        if (this.dataTable.getSelectedRow() < 0) {
            MessageBox.showErrorDialog(this, "没有数据行被选中!");
            return;
        }
        if (MessageBox.showQuestionDialog(this, "您确信要删除当前记录吗?") == 0) {
            IDataRow dataRow = this.dataTable.getSelectedDataRow();
            try {
                this.dataSource.removeDataRow(dataRow);
                this.dataSource.clearTempDataRows();
            }
            catch(LogicException ex)
            {
                MessageBox.showErrorDialog(this, ex.getMessage());
            }
            catch(RuntimeException ex)
            {
                //ex.printStackTrace();
                MessageBox.showErrorDialog(this, "不能删除，有些表单依赖该信息!");
            }
            catch (Exception ex) {
                MessageBox.showErrorDialog(this, "无法删除,请与系统管理员联系!\n" + ex.getMessage());
                //ex.printStackTrace();
            }
            this.updateDatas();
        }
    }
    protected void updateDatas()
    {
        
    }
    protected void doImport() {
        JOptionPane.showMessageDialog(this, "尚未实现!");
    }
    protected void doAddType() {
        if (this.typesTree.getSelectionPath() != null && this.typesTree.getSelectionPath().getPathCount() > 5) {
            JOptionPane.showMessageDialog(this, "最多支持5级分类!");
            return;
        }
        Object currentCatalog = this.getCurrentCatalog();
        int parentId = -1;
        if (currentCatalog != null) {
            Integer id = (Integer) ObjectInvokerUtil.getProperty(currentCatalog, "id");
            //System.out.println("id:" + id);
            if (id != null) {
                parentId = id;
            }
        }
        this.typeDialog.newDataRow(parentId);
        this.typeDialog.setVisible(true);
        this.typeDataSource.clearTempDataRows();
        //Main.getMainFrame().refreshDataBase();////
        this.refreshDatas();
    }
    protected void doEditType() {
        if (this.getCurrentCatalog() == null) {
            MessageBox.showErrorDialog(this, "没有要修改的类别被选中!");
            return;
        }
        this.typeDialog.setVisible(true);
        this.typeDataSource.clearTempDataRows();
        //Main.getMainFrame().refreshDataBase();////
        this.refreshDatas();
    }
    protected void initDatas()
    {
        
    }
    public void refreshDatas()
    {
        this.dataSource.clear();
        this.initDatas();
        this.initTypes();
        if (this.typeDataSource != null)
        {
            this.typeDataSource.addDataRowChangedListener(this);
            this.typeDataSource.addDataRowRolledListener(this);
            this.initTypeTreeNodes();
        }
    }
    protected void doRemoveType() {
        if (this.getCurrentCatalog() == null)//this.typeDataSource.getCurrentDataRow()
        {
            MessageBox.showErrorDialog(this, "没有数据行被选中!");
            return;
        }
        if (MessageBox.showQuestionDialog(this, "您确信要删除选中类别、子类别和类别中的所有记录吗?") == 0) {
            try {
                IDataRow parentDataRow  = this.typeDataSource.getCurrentDataRow();
                List<IDataRow> dataRows = this.getCurrentTreeDataRows();
                for(IDataRow dataRow:dataRows)
                {
                    this.typeDataSource.removeDataRow(dataRow);
                }
                this.typeDataSource.clearTempDataRows();
                deleteTypeTreeNode(parentDataRow);//delete tree
                //Main.getMainFrame().refreshDataBase();//
                this.refreshDatas();
            }
            catch(LogicException ex)
            {
                MessageBox.showErrorDialog(this, ex.getMessage());
            }
            catch (Exception ex) {
                MessageBox.showErrorDialog(this, "无法删除,请与系统管理员联系!\n" + ex.getMessage());
                //ex.printStackTrace();
            }
            /*
            IDataRow dataRow = this.typeDataSource.getCurrentDataRow();
            try {
                this.typeDataSource.removeDataRow(dataRow);
                this.typeDataSource.clearTempDataRows();
            } catch (Exception ex) {
                MessageBox.showErrorDialog(this, "无法删除,请与系统管理员联系!\n" + ex.getMessage());
                ex.printStackTrace();
            }
             */
        }
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == this.newButton || source == this.newMenuItem) {
            this.doAdd();
        } else if (source == this.editButton || source == this.editMenuItem) {
            this.doEdit();
        } else if (source == this.removeButton || source == this.removeMenuItem) {
            this.doRemove();
        } else if (source == this.newTypeButton || source == this.newTypeMenuItem) {
            this.doAddType();
        } else if (source == this.editTypeButton || source == this.editTypeMenuItem) {
            this.doEditType();
        } else if (source == this.removeTypeButton || source == this.removeTypeMenuItem) {
            this.doRemoveType();
        } else if (source == this.printButton) {
            doPrint();
        } else if (source == this.exitButton) {
            doClose();
        } else if (source == this.importButton) {
            doImport();
        }
        else if (source == this.stopButton)
        {
            doStop();

        }
        else if (source == this.activeButton)
        {
            doActive();
        }
    }

    protected void doStop()
    {
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null)
        {
            dataRow.setColumnValue("status", 1);
        }
        else
        {
            dataRow.setColumnValue("status", 0);
        }
        //this.refreshDatas();
    }

    protected void doActive()
    {
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null)
        {
            dataRow.setColumnValue("status", 0);
        }
        else
        {
            dataRow.setColumnValue("status", 1);
        }
    }
    protected void doClose() {
        this.setVisible(false);
        //this.dispose();
    }
    protected void doPrint() {
    }
    /**
     * 必须在子类的这个方法里面初始化typeDataSource,创建typeDialog实例
     * 设置root结点
     */
    protected abstract void initTypes();
    protected void initTypeTreeNodes() {
        typeDataSource.sortById(IDataSource.ASC_SORT);//????????????????????
        
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
    /*
    MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
    List<IDataRow> dataRows = typeDataSource.getDataRows();
    int i = 0;
    for (IDataRow dataRow : dataRows)
    {
    MutableTreeNode node = new DefaultMutableTreeNode(dataRow);
    this.treeModel.insertNodeInto(node, root, i++);
    }
    TreePath path = new TreePath(new Object[]{root});
    this.typesTree.expandPath(path);
     */
    }
    private void addSortedNodeInto(MutableTreeNode node, MutableTreeNode parentNode)
    {
        Enumeration enums = parentNode.children();
        int index = 0;
        int nodeId = (Integer)((IDataRow)((DefaultMutableTreeNode)node).getUserObject()).getColumnValue("id");
        while(enums.hasMoreElements())
        {
           DefaultMutableTreeNode childNode = (DefaultMutableTreeNode)enums.nextElement();
           Object userObject = childNode.getUserObject();
           if (userObject != null && userObject instanceof IDataRow)
           {
               int id = (Integer)((IDataRow)userObject).getColumnValue("id");
               if (id > nodeId)
               {
                   break;
               }
           }
           index++;
        }
        this.treeModel.insertNodeInto(node, parentNode, index);
    }
    //private HashMap<Integer, MutableTreeNode> tempMap = new HashMap<Integer, MutableTreeNode>();
    private MutableTreeNode addInitTreeNode(IDataRow dataRow) {
        Integer parentId = (Integer) dataRow.getColumnValue("parentId");
        MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
        if (parentId == null || parentId <= 0) {
            MutableTreeNode node = new DefaultMutableTreeNode(dataRow);
            //this.treeModel.insertNodeInto(node, root, root.getChildCount());
            this.addSortedNodeInto(node, root);
            return node;
        } else {
            MutableTreeNode parentNode = this.getTreeNode(parentId);//this.getDataRowTreeNode(parentId);
            if (parentNode != null) {
                MutableTreeNode node = new DefaultMutableTreeNode(dataRow);
                //this.treeModel.insertNodeInto(node, parentNode, parentNode.getChildCount());
                this.addSortedNodeInto(node, parentNode);
                return node;
            }
            return null;
        }
    }
    private void deleteTypeTreeNode(IDataRow dataRow) {
        //要删除所有子节点的内容
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getTreeNode((Integer) dataRow.getColumnValue("id"));//(DefaultMutableTreeNode)this.tempMap.get(dataRow.getColumnValue("id"));
        if (node == null) {
            return; //should never occurs!
        }
        //if (node.getUserObject() instanceof IDataRow)
        //{
        this.treeModel.removeNodeFromParent(node);
    //children!
    //}
    /*
    MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
    int count = root.getChildCount();
    for (int i = 0; i < count; i++)
    {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
    if (node.getUserObject() == dataRow)
    {
    this.treeModel.removeNodeFromParent(node);
    break;
    }
    }
    this.typesTree.setSelectionPath(new TreePath(new Object[]{root}));
     */
    }
    private void insertTypeTreeNode(IDataRow dataRow) {
        Integer parentId = (Integer) dataRow.getColumnValue("parentId");
        MutableTreeNode parentNode = this.getTreeNode(parentId);//this.tempMap.get(parentId);
        if (parentNode == null) {
            parentNode = (MutableTreeNode) this.treeModel.getRoot();
        }
        MutableTreeNode node = new DefaultMutableTreeNode(dataRow);
        //this.treeModel.insertNodeInto(node, parentNode, parentNode.getChildCount());
        this.addSortedNodeInto(node, parentNode);
    //this.tempMap.put((Integer)dataRow.getColumnValue("id"), node);//id is default value!
    //TreePath path = new TreePath(new Object[]{root, node});
    //this.typesTree.setSelectionPath(path);
        /*
    MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
    MutableTreeNode node = new DefaultMutableTreeNode(dataRow);
    this.treeModel.insertNodeInto(node, root, 0);
    TreePath path = new TreePath(new Object[]{root, node});
    this.typesTree.setSelectionPath(path);
     */
    }
    private MutableTreeNode getTreeNode(int id) {
        MutableTreeNode root = (MutableTreeNode) this.treeModel.getRoot();
        return getTreeNode(root, id);
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
        if (node != null)
        {
            this.addDataRows(node, dataRows);
        }
        return dataRows;
    }
    
    private void addDataRows(DefaultMutableTreeNode node, List<IDataRow> dataRows)
    {
        //System.out.println("node:" + node);
        dataRows.add((IDataRow)node.getUserObject());
        for(int i = 0; i < node.getChildCount(); i++)
        {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode)node.getChildAt(i);
            this.addDataRows(n, dataRows);
            
        }
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
    /**
     * 类型改变的时候的样子
     * @param evt
     */
    public void rowDeleted(DataRowEvent evt) {
        deleteTypeTreeNode(evt.getDataRow());//afa 2009-6-24
    }
    public void rowInserted(DataRowEvent evt) {
        this.insertTypeTreeNode(evt.getDataRow());
    }
    public void rowUpdated(DataRowEvent evt) {
        this.typesTree.repaint();
    }
    public void rowRolled(DataRowRolledEvent evt) {
        this.setSelectedType(typeDataSource.getCurrentDataRow());
    }
    public void dispose()
    {
        if (dataSource != null)
        {
            this.dataSource.dispose();
        }
        if (this.typeDataSource != null)
        {
            this.typeDataSource.dispose();
        }
        if (this.typeDialog != null)
        {
            this.typeDialog.dispose();
        }
        super.dispose();
    }
}
