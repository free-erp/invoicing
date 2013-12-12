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
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.system.NumberPrefix;
import org.free_erp.service.logic.ISystemManageService;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CSystemOptionDialog extends CDialog implements ActionListener
{
    private CButton generalSaveB;
    private CButton generalCancelB;
    private CButton prefixSaveB;
    private CButton prefixCancelB;
    private JRadioButton manualRadioB;
    private JRadioButton autoRadioB;
    private JCheckBox startupCheckB;
    private CField proNumField;
    private CField cusNumField;
    private CField empNumField;
    private CField stoNumField;
    private CField proCatalogNumField;
    private CField cusCatalogNumField;
    private CField empCatalogNumField;
    private CField inStoNumField;
    private CField outStoNumField;
    private CField outFlowNumField;
    private CField lossNumField;
    private CField moveNumField;
    private CField checkNumField;
    private CField changeNumField;
    private CField minMaxNumField;
    private CField initStoNumField;
    private CField purIndentNumField;
    private CField purOrderNumField;
    private CField purBackNumField;
    private CField productQuoteNumField;
    private CField saleIndentNumField;
    private CField saleOrderNumField;
    private CField changePriceNumField;
    private CField saleBackNum;
    private CField saleIncomeNumField;
    private CField gatherBalanceNumField;
    private CField purchasePayNumField;
    private CField payBalanceNumField;
    private CField keepFareNumField;
    private CField genalFareNumField;
    private CField otherIncomeNumField;
    private CField cashBankNumField;

    private Integer MYHEIGHT = 20;
    private ISystemManageService systemManageService;
    private Company company;
    private NumberPrefix numberPrefix;
    
    public CSystemOptionDialog(Frame parent)
	{
		super(parent);
        systemManageService = Main.getServiceManager().getSystemManageService();
        company = Main.getMainFrame().getCompany();
        numberPrefix = systemManageService.getNumberPrefix(company);
        this.ininitDialog();
	}

    public void ininitDialog()
    {
		this.setTitle("编号管理");
		
        CPanel generalPane = new CPanel();
        generalPane.setLayout(null);
        CPanel baseDataPane = new CPanel();
        baseDataPane.setLayout(null);
        CPanel storagePane = new CPanel();
        storagePane.setLayout(null);
        CPanel purchasePane = new CPanel();
        purchasePane.setLayout(null);
        CPanel salePane = new CPanel();
        salePane.setLayout(null);
        CPanel incomePayPane = new CPanel();
        incomePayPane.setLayout(null);
        CPanel cashBankPane = new CPanel();
        cashBankPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(10, 5, 520, 190);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().setLayout(new BorderLayout());
        this.setBounds(0, 200, 550, 280);
        this.getContentPane().add("Center", panel);

        panel.add(tabbedPane);

        //tabbedPane.addTab("常规 ",generalPane);
        tabbedPane.addTab("基础数据",baseDataPane);
        tabbedPane.addTab("仓库管理",storagePane);
        tabbedPane.addTab("采购管理",purchasePane);
        tabbedPane.addTab("销售管理",salePane);
        tabbedPane.addTab("应收应付",incomePayPane);
        tabbedPane.addTab("现金银行",cashBankPane);
        
        this.autoRadioB = new JRadioButton("自动");
        this.manualRadioB = new JRadioButton("手动");
        this.startupCheckB = new JCheckBox("启动");
        this.generalSaveB = new CButton("确定");
        this.generalCancelB = new CButton("取消");
        ButtonGroup bg = new ButtonGroup();
        bg.add(autoRadioB);
        bg.add(manualRadioB);
        this.generalSaveB.addActionListener(this);
        this.generalCancelB.addActionListener(this);

        generalPane.addComponent(new JLabel("编号生成规则："), 5, 5, 100, MYHEIGHT);
        generalPane.addComponent(this.manualRadioB, 10, 35, 60, MYHEIGHT);
        generalPane.addComponent(this.autoRadioB, 80, 35, 60, MYHEIGHT);
        generalPane.addComponent(new JLabel("系统日志检查更新："), 5, 65, 140, MYHEIGHT);
        generalPane.addComponent(new JLabel("更新时间：---分钟"), 5, 95, 100, MYHEIGHT);
        generalPane.addComponent(this.startupCheckB, 5, 125, 60, MYHEIGHT);
        generalPane.addComponent(this.generalSaveB, 335, 245, 60, 25);
        generalPane.addComponent(this.generalCancelB, 425, 245, 60, 25);

        this.proNumField = new CField();
        this.proNumField.setText(numberPrefix.getProNum());
        this.cusNumField = new CField();
        this.cusNumField.setText(numberPrefix.getCusNum());
        this.empNumField = new CField();
        this.empNumField.setText(numberPrefix.getEmpNum());
        this.stoNumField = new CField();
        this.stoNumField.setText(numberPrefix.getStoNum());
        this.proCatalogNumField = new CField();
        this.proCatalogNumField.setText(numberPrefix.getProCatalogNum());
        this.cusCatalogNumField = new CField();
        this.cusCatalogNumField.setText(numberPrefix.getCusCatalogNum());
        this.empCatalogNumField = new CField();
        this.empCatalogNumField.setText(numberPrefix.getEmpCatalogNum());

        this.inStoNumField = new CField();
        this.inStoNumField.setText(numberPrefix.getInStoNum());
        this.outStoNumField = new CField();
        this.outStoNumField.setText(numberPrefix.getOutStoNum());
        this.outFlowNumField = new CField();
        this.outFlowNumField.setText(numberPrefix.getOutFlowNum());
        this.lossNumField = new CField();
        this.lossNumField.setText(numberPrefix.getLossNum());
        this.moveNumField = new CField();
        this.moveNumField.setText(numberPrefix.getMoveNum());
        this.checkNumField = new CField();
        this.checkNumField.setText(numberPrefix.getCheckNum());
        this.changeNumField = new CField();
        this.changeNumField.setText(numberPrefix.getChangeNum());
        this.minMaxNumField = new CField();
        this.minMaxNumField.setText(numberPrefix.getMinMaxNum());
        this.initStoNumField = new CField();
        this.initStoNumField.setText(numberPrefix.getInitStoNum());

        this.purIndentNumField = new CField();
        this.purIndentNumField.setText(numberPrefix.getPurchaseIndentNum());
        this.purOrderNumField = new CField();
        this.purOrderNumField.setText(numberPrefix.getPurchaseOrderNum());
        this.purBackNumField = new CField();
        this.purBackNumField.setText(numberPrefix.getPurchaseBackNum());

        this.productQuoteNumField = new CField();
        this.productQuoteNumField.setText(numberPrefix.getProductQuoteNum());
        this.saleIndentNumField = new CField();
        this.saleIndentNumField.setText(numberPrefix.getSaleIndentNum());
        this.saleOrderNumField = new CField();
        this.saleOrderNumField.setText(numberPrefix.getSaleOrderNum());
        this.changePriceNumField = new CField();
        this.changePriceNumField.setText(numberPrefix.getChangePriceNum());
        this.saleBackNum = new CField();
        this.saleBackNum.setText(numberPrefix.getSaleBackNum());

        this.saleIncomeNumField = new CField();
        this.saleIncomeNumField.setText(numberPrefix.getSaleBeforeIncomeNum());
        this.gatherBalanceNumField = new CField();
        this.gatherBalanceNumField.setText(numberPrefix.getGatherBalanceNum());
        this.purchasePayNumField = new CField();
        this.purchasePayNumField.setText(numberPrefix.getPurchaseBeforePayNum());
        this.payBalanceNumField = new CField();
        this.payBalanceNumField.setText(numberPrefix.getPayBalanceNum());
        
        this.keepFareNumField = new CField();
        this.keepFareNumField.setText(numberPrefix.getKeepFareNum());
        this.genalFareNumField = new CField();
        this.genalFareNumField.setText(numberPrefix.getGenalFareNum());
        this.otherIncomeNumField = new CField();
        this.otherIncomeNumField.setText(numberPrefix.getOtherIncomeNum());
        this.cashBankNumField = new CField();
        this.cashBankNumField.setText(numberPrefix.getCashBankNum());

        this.prefixSaveB = new CButton("确定(&O)");
        this.prefixCancelB = new CButton("取消(&C)");
        this.prefixSaveB.addActionListener(this);
        this.prefixCancelB.addActionListener(this);
        
        baseDataPane.addComponent(this.proNumField, 85, 5, 80, MYHEIGHT, "商品编号", 80);
        baseDataPane.addComponent(this.cusNumField, 250, 5, 80, MYHEIGHT, "客户编号", 80);
        baseDataPane.addComponent(this.empNumField, 85, 35, 80, MYHEIGHT, "员工编号", 80);
        baseDataPane.addComponent(this.stoNumField, 250, 35, 80, MYHEIGHT, "仓库编号", 80);
        //baseDataPane.addComponent(this.proCatalogNumField, 85, 95, 80, MYHEIGHT, "商品类别编号", 80);
        //baseDataPane.addComponent(this.cusCatalogNumField, 250, 95, 80, MYHEIGHT, "客户类别编号", 80);
        //baseDataPane.addComponent(this.empCatalogNumField, 425, 95, 80, MYHEIGHT, "部门类别编号", 90);
        storagePane.addComponent(this.inStoNumField, 85, 5, 80, MYHEIGHT, "入库单编号", 80);
        storagePane.addComponent(this.outStoNumField, 250, 5, 80, MYHEIGHT, "出库单编号", 80);
        storagePane.addComponent(this.outFlowNumField, 425, 5, 80, MYHEIGHT, "报溢单编号", 90);
        storagePane.addComponent(this.lossNumField, 85, 35, 80, MYHEIGHT, "报损单编号", 80);
        storagePane.addComponent(this.moveNumField, 250, 35, 80, MYHEIGHT, "移库单编号", 80);
        storagePane.addComponent(this.checkNumField, 425, 35, 80, MYHEIGHT, "盘点单编号", 90);
        storagePane.addComponent(this.changeNumField, 85, 65, 80, MYHEIGHT, "变价单编号", 80);
        storagePane.addComponent(this.minMaxNumField, 250, 65, 80, MYHEIGHT, "上下限单编号", 80);
        storagePane.addComponent(this.initStoNumField, 425, 65, 80, MYHEIGHT, "库存期初单编号", 90);

        purchasePane.addComponent(this.purIndentNumField, 85, 5, 80, MYHEIGHT, "采购订单编号", 80);
        purchasePane.addComponent(this.purOrderNumField, 250, 5, 80, MYHEIGHT, "采购单编号", 80);
        purchasePane.addComponent(this.purBackNumField, 425, 5, 80, MYHEIGHT, "采购退货单编号", 90);

        salePane.addComponent(this.productQuoteNumField, 85, 5, 80, MYHEIGHT, "商品报价编号", 80);
        salePane.addComponent(this.saleIndentNumField, 250, 5, 80, MYHEIGHT, "销售订单编号", 80);
        salePane.addComponent(this.saleOrderNumField, 405, 5, 90, MYHEIGHT, "销售单编号", 70);
        salePane.addComponent(this.changePriceNumField, 85, 35, 80, MYHEIGHT, "商品调价编号", 80);
        salePane.addComponent(this.saleBackNum, 250, 35, 80, MYHEIGHT, "销售退货编号", 80);

        incomePayPane.addComponent(this.saleIncomeNumField, 85, 5, 80, MYHEIGHT, "销售预售编号", 80);
        incomePayPane.addComponent(this.gatherBalanceNumField, 250, 5, 80, MYHEIGHT, "收款结算编号", 80);
        incomePayPane.addComponent(this.purchasePayNumField, 85, 35, 80, MYHEIGHT, "采购预付编号", 80);
        incomePayPane.addComponent(this.payBalanceNumField, 250, 35, 80, MYHEIGHT, "付款结算编号", 80);

        cashBankPane.addComponent(this.keepFareNumField, 85, 5, 80, MYHEIGHT, "经营费用编号", 80);
        cashBankPane.addComponent(this.genalFareNumField, 250, 5, 80, MYHEIGHT, "一般费用编号", 80);
        cashBankPane.addComponent(this.otherIncomeNumField, 85, 35, 80, MYHEIGHT, "其它收入编号", 80);
        cashBankPane.addComponent(this.cashBankNumField, 250, 35, 80, MYHEIGHT, "现金银行编号", 80);

        panel.add(this.prefixSaveB);
        this.prefixSaveB.setBounds(360, 205, 80, 25);
        panel.add(this.prefixCancelB);
        this.prefixCancelB.setBounds(450, 205, 80, 25);
        this.setDefaultButton(prefixSaveB);
		this.setCancelButton(prefixCancelB);
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == this.generalSaveB)
        {
            this.doSaveGeneral();
        }
        else if(source == this.generalCancelB)
        {
            this.doClose();
        }
        else if(source == this.prefixSaveB)
        {
            this.doSavePrefix();
        }
        else if(source == this.prefixCancelB)
        {
            this.doClose();
        }
    }

    public void doSaveGeneral()
    {
    }

    public void doSavePrefix()
    {
        numberPrefix.setCompany(company);

        numberPrefix.setProNum(this.proNumField.getText());
        numberPrefix.setCusNum(this.cusNumField.getText());
        numberPrefix.setEmpNum(this.empNumField.getText());
        numberPrefix.setStoNum(this.stoNumField.getText());

        numberPrefix.setInStoNum(this.inStoNumField.getText());
        numberPrefix.setOutStoNum(this.outStoNumField.getText());
        numberPrefix.setOutFlowNum(this.outFlowNumField.getText());
        numberPrefix.setLossNum(this.lossNumField.getText());
        numberPrefix.setMoveNum(this.moveNumField.getText());
        numberPrefix.setCheckNum(this.checkNumField.getText());
        numberPrefix.setChangeNum(this.changeNumField.getText());
        numberPrefix.setMinMaxNum(this.minMaxNumField.getText());
        numberPrefix.setInitStoNum(this.initStoNumField.getText());

        numberPrefix.setPurchaseIndentNum(this.purIndentNumField.getText());
        numberPrefix.setPurchaseOrderNum(this.purOrderNumField.getText());
        numberPrefix.setPurchaseBackNum(this.purBackNumField.getText());

        numberPrefix.setSaleBeforeIncomeNum(this.saleIncomeNumField.getText());
        numberPrefix.setGatherBalanceNum(this.gatherBalanceNumField.getText());
        numberPrefix.setPurchaseBeforePayNum(this.purchasePayNumField.getText());
        numberPrefix.setPayBalanceNum(this.payBalanceNumField.getText());
        
        numberPrefix.setKeepFareNum(this.keepFareNumField.getText());
        numberPrefix.setGenalFareNum(this.genalFareNumField.getText());
        numberPrefix.setOtherIncomeNum(this.otherIncomeNumField.getText());
        numberPrefix.setCashBankNum(this.cashBankNumField.getText());

        numberPrefix.setProductQuoteNum(this.productQuoteNumField.getText());
        numberPrefix.setSaleIndentNum(this.saleIndentNumField.getText());
        numberPrefix.setSaleOrderNum(this.saleOrderNumField.getText());
        numberPrefix.setChangePriceNum(this.changePriceNumField.getText());
        numberPrefix.setSaleBackNum(this.saleBackNum.getText());
        
        systemManageService.saveNumberPrefix(numberPrefix);
        this.doClose();
    }

    public void doClose()
    {
        this.dispose();
    }
}
