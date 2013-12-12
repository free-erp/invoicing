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

import org.free_erp.extbeans.HelpEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.free_erp.client.ui.forms.accounting.CBankCashDialog;
import org.free_erp.client.ui.forms.accounting.CBankCashManageWindow;
import org.free_erp.client.ui.forms.accounting.CBussinessExpenseDialog;
import org.free_erp.client.ui.forms.accounting.CBussinessExpenseManageWindow;
import org.free_erp.client.ui.forms.accounting.CCommonExpenseDialog;
import org.free_erp.client.ui.forms.accounting.CCommonExpenseManageWindow;
import org.free_erp.client.ui.forms.accounting.COtherIncomeDialog;
import org.free_erp.client.ui.forms.accounting.COtherIncomeManageWindow;
import org.free_erp.client.ui.forms.accounting.CPayableClearingDialog;
import org.free_erp.client.ui.forms.accounting.CPayableClearingManageWindow;
import org.free_erp.client.ui.forms.accounting.CPrePayableDialog;
import org.free_erp.client.ui.forms.accounting.CPrePayableManageWindow;
import org.free_erp.client.ui.forms.accounting.CPreReceivableDialog;
import org.free_erp.client.ui.forms.accounting.CPreReceivableManageWindow;
import org.free_erp.client.ui.forms.accounting.CReceivableClearingDialog;
import org.free_erp.client.ui.forms.accounting.CReceivableClearingManageWindow;
import org.free_erp.client.ui.forms.basic.CCustomerManageWindow;
import org.free_erp.client.ui.forms.basic.CEmployeeManageWindow;
import org.free_erp.client.ui.forms.basic.CProductManageWindow;
import org.free_erp.client.ui.forms.basic.CStorageManageWindow;
import org.free_erp.client.ui.forms.basic.CSubjectManageWindow;
import org.free_erp.client.ui.forms.basic.CSupplierManageWindow;
import org.free_erp.client.ui.forms.basic.ProductSelectPanel;
import org.free_erp.client.ui.forms.help.CAboutDialog;
import org.free_erp.client.ui.forms.purchase.CChoosepurchaseOrderChangeDialog;
import org.free_erp.client.ui.forms.purchase.CPurchaseChangeOrderWindow;
import org.free_erp.client.ui.forms.purchase.CPurchaseIndentDialog;
import org.free_erp.client.ui.forms.purchase.CPurchaseIndentWindow;
import org.free_erp.client.ui.forms.purchase.CPurchaseOrderBackDialog;
import org.free_erp.client.ui.forms.purchase.CPurchaseOrderBackWindow;
import org.free_erp.client.ui.forms.purchase.CPurchaseOrderDialog;
import org.free_erp.client.ui.forms.purchase.CPurchaseOrderWindow;
import org.free_erp.client.ui.forms.sale.CSaleChangeMoneyDialog;
import org.free_erp.client.ui.forms.sale.CSaleChangeMoneyWindow;
import org.free_erp.client.ui.forms.sale.CSaleChangeOrderWindow;
import org.free_erp.client.ui.forms.sale.CSaleChooseOrderChangeDialog;
import org.free_erp.client.ui.forms.sale.CSaleDetailDialog;
import org.free_erp.client.ui.forms.sale.CSaleDetailWindow;
import org.free_erp.client.ui.forms.sale.CSaleGartheringWindow;
import org.free_erp.client.ui.forms.sale.CSaleOrderBackDialog;
import org.free_erp.client.ui.forms.sale.CSaleOrderBackWindow;
import org.free_erp.client.ui.forms.sale.CSaleOrderDialog;
import org.free_erp.client.ui.forms.sale.CSaleOrderWindow;
import org.free_erp.client.ui.forms.sale.CSaleQuoteDialog;
import org.free_erp.client.ui.forms.sale.CSaleQuoteWindow;
import org.free_erp.client.ui.forms.start.CBeginPaymentDialog;
import org.free_erp.client.ui.forms.start.CBeginPaymentWindow;
import org.free_erp.client.ui.forms.start.CBeginReceivableDialog;
import org.free_erp.client.ui.forms.start.CBeginReceivableWindow;
import org.free_erp.client.ui.forms.start.CBeginStorageDialog;
import org.free_erp.client.ui.forms.start.CBeginStorageWindow;
import org.free_erp.client.ui.forms.start.CBeginSubjectDialog;
import org.free_erp.client.ui.forms.start.CBeginSubjectWindow;
import org.free_erp.client.ui.forms.system.CChangePasswordDialog;
import org.free_erp.client.ui.forms.system.CCompanyInfoDialog;
import org.free_erp.client.ui.forms.system.CCusInvoiceListDialog;
import org.free_erp.client.ui.forms.system.CCusPriceListDialog;
import org.free_erp.client.ui.forms.system.CCusTypeListDialog;
import org.free_erp.client.ui.forms.system.CEmpDegreeListDialog;
import org.free_erp.client.ui.forms.system.CEmpDutyListDialog;
import org.free_erp.client.ui.forms.system.CEmpHealthListDialog;
import org.free_erp.client.ui.forms.system.CEmpLevelListDialog;
import org.free_erp.client.ui.forms.system.CLoginDialog;
import org.free_erp.client.ui.forms.system.CProShelfListDialog;
import org.free_erp.client.ui.forms.system.CProTaxrateListDialog;
import org.free_erp.client.ui.forms.system.CProUnitListDialog;
import org.free_erp.client.ui.forms.system.CRoleManageWindow;
import org.free_erp.client.ui.forms.system.CStoTypeListDialog;
import org.free_erp.client.ui.forms.system.CSystemLogDialog;
import org.free_erp.client.ui.forms.system.CSystemLogWindow;
import org.free_erp.client.ui.forms.system.CSystemOptionDialog;
import org.free_erp.client.ui.forms.system.CUserManageWindow;
import org.free_erp.client.ui.forms.warehouse.CStorageChangeMoneyDialog;
import org.free_erp.client.ui.forms.warehouse.CStorageChangeMoneyWindow;
import org.free_erp.client.ui.forms.warehouse.CStorageCheckDialog;
import org.free_erp.client.ui.forms.warehouse.CStorageCheckWindow;
import org.free_erp.client.ui.forms.warehouse.CStorageInDialog;
import org.free_erp.client.ui.forms.warehouse.CStorageInWindow;
import org.free_erp.client.ui.forms.warehouse.CStorageLossDialog;
import org.free_erp.client.ui.forms.warehouse.CStorageLossWindow;
import org.free_erp.client.ui.forms.warehouse.CStorageMinMaxDialog;
import org.free_erp.client.ui.forms.warehouse.CStorageMinMaxWindow;
import org.free_erp.client.ui.forms.warehouse.CStorageMoveDialog;
import org.free_erp.client.ui.forms.warehouse.CStorageMoveWindow;
import org.free_erp.client.ui.forms.warehouse.CStorageOutDialog;
import org.free_erp.client.ui.forms.warehouse.CStorageOutWindow;
import org.free_erp.client.ui.forms.warehouse.CStorageOutflowDialog;
import org.free_erp.client.ui.forms.warehouse.CStorageOutflowWindow;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.accounting.CCashBankDetailQueryWindow;
import org.free_erp.client.ui.report.accounting.CCashBankMonthReportWindow;
import org.free_erp.client.ui.report.accounting.CCustomerQueryWindow;
import org.free_erp.client.ui.report.accounting.CCustomerReceiveBalanceQueryWindow;
import org.free_erp.client.ui.report.accounting.CCustomerUnbalanceDetailQueryWindow;
import org.free_erp.client.ui.report.accounting.CDailyFeeDetailQueryWindow;
import org.free_erp.client.ui.report.accounting.CDailyFeeReportWindow;
import org.free_erp.client.ui.report.accounting.COtherIncomeDetailQueryWindow;
import org.free_erp.client.ui.report.accounting.COtherIncomeMonthReportWindow;
import org.free_erp.client.ui.report.accounting.CSubjectQueryWindow;
import org.free_erp.client.ui.report.accounting.CSupplierPayBalanceQueryWindow;
import org.free_erp.client.ui.report.accounting.CSupplierQueryWindow;
import org.free_erp.client.ui.report.accounting.CSupplierUnbalanceDetailQueryWindow;
import org.free_erp.client.ui.report.basic.CCustomersQueryWindow;
import org.free_erp.client.ui.report.basic.CEmployeeQueryWindow;
import org.free_erp.client.ui.report.basic.CProductQueryWindow;
import org.free_erp.client.ui.report.basic.CSuppliersQueryWindow;
import org.free_erp.client.ui.report.manager.CManagerDailyQueryWindow;
import org.free_erp.client.ui.report.manager.CManagerMonthQueryWindow;
import org.free_erp.client.ui.report.manager.CManagerYearQueryWindow;
import org.free_erp.client.ui.report.purchase.CEmployeePurchaseDetailQueryWindow;
import org.free_erp.client.ui.report.purchase.CEmployeePurchaseStatReportWindow;
import org.free_erp.client.ui.report.purchase.CProductPurchaseDetailQueryWindow;
import org.free_erp.client.ui.report.purchase.CProductPurchaseGatherReportWindow;
import org.free_erp.client.ui.report.purchase.CProductPurchasePriceWaveReportWindow;
import org.free_erp.client.ui.report.purchase.CProductPurchaseStatReportWindow;
import org.free_erp.client.ui.report.purchase.CPurchaseIndentBalanceQueryWindow;
import org.free_erp.client.ui.report.purchase.CPurchaseIndentPersonStatWindow;
import org.free_erp.client.ui.report.purchase.CPurchaseIndentProBalDetailWindow;
import org.free_erp.client.ui.report.purchase.CPurchaseIndentProBalStatWindow;
import org.free_erp.client.ui.report.purchase.CPurchaseIndentProductQueryWindow;
import org.free_erp.client.ui.report.purchase.CPurchaseIndentProductStatWindow;
import org.free_erp.client.ui.report.purchase.CPurchaseIndentQueryWindow;
import org.free_erp.client.ui.report.purchase.CPurchaseIndentSupplierStatWindow;
import org.free_erp.client.ui.report.purchase.CSupplierPurchaseDetailQueryWindow;
import org.free_erp.client.ui.report.purchase.CSupplierPurchaseStatReportWindow;
import org.free_erp.client.ui.report.sale.CCustomerMonthSaleCompareWindow;
import org.free_erp.client.ui.report.sale.CCustomerSaleDetailQueryWindow;
import org.free_erp.client.ui.report.sale.CCustomerSaleOrderWindow;
import org.free_erp.client.ui.report.sale.CCustomerSaleStatReportWindow;
import org.free_erp.client.ui.report.sale.CEmployeeSaleDetailQueryWindow;
import org.free_erp.client.ui.report.sale.CEmployeeSaleOrderWindow;
import org.free_erp.client.ui.report.sale.CEmployeeSaleStatReportWindow;
import org.free_erp.client.ui.report.sale.CProductMonthSaleCompareWindow;
import org.free_erp.client.ui.report.sale.CProductPriceQueryWindow;
import org.free_erp.client.ui.report.sale.CProductSaleDetailQueryWindow;
import org.free_erp.client.ui.report.sale.CProductSaleGatherReportWindow;
import org.free_erp.client.ui.report.sale.CProductSaleOrderWindow;
import org.free_erp.client.ui.report.sale.CProductSalePriceWaveReportWindow;
import org.free_erp.client.ui.report.sale.CProductSaleStatReportWindow;
import org.free_erp.client.ui.report.sale.CSaleIndentBalanceQueryWindow;
import org.free_erp.client.ui.report.sale.CSaleIndentCustomerStatWindow;
import org.free_erp.client.ui.report.sale.CSaleIndentPersonStatWindow;
import org.free_erp.client.ui.report.sale.CSaleIndentProBalDetailWindow;
import org.free_erp.client.ui.report.sale.CSaleIndentProBalStatWindow;
import org.free_erp.client.ui.report.sale.CSaleIndentProductQueryWindow;
import org.free_erp.client.ui.report.sale.CSaleIndentProductStatWindow;
import org.free_erp.client.ui.report.sale.CSaleIndentQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageAlertQueryDialog;
import org.free_erp.client.ui.report.warehouse.CStorageChangeMoneyReportWindow;
import org.free_erp.client.ui.report.warehouse.CStorageCheckQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageCheckReportWindow;
import org.free_erp.client.ui.report.warehouse.CStorageDetailMonthQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageDetailQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageInListQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageInReportWindow;
import org.free_erp.client.ui.report.warehouse.CStorageLossQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageLossReportWindow;
import org.free_erp.client.ui.report.warehouse.CStorageMinMaxQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageMoveQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageMoveReportWindow;
import org.free_erp.client.ui.report.warehouse.CStorageOutListQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageOutReportWindow;
import org.free_erp.client.ui.report.warehouse.CStorageOutflowQueryWindow;
import org.free_erp.client.ui.report.warehouse.CStorageOutflowReportWindow;
import org.free_erp.client.ui.report.warehouse.CStorageQueryWindow;
import org.free_erp.client.ui.util.DateUtilities;
import org.free_erp.client.ui.util.Md5;
import org.free_erp.extbeans.CBaseImageItem;
import org.free_erp.extbeans.CGroupPane;
import org.free_erp.extbeans.CHelpInfoPane;
import org.free_erp.extbeans.CImageBackPane;
import org.free_erp.extbeans.CImageItem;
import org.free_erp.extbeans.CLittleImageItem;
import org.free_erp.extbeans.CModulePane;
import org.free_erp.extbeans.CNaviButton;
import org.free_erp.extbeans.CTitlePane;
import org.free_erp.extbeans.HitListener;
import com.jdatabeans.beans.CComboBoxPane;
import com.jdatabeans.beans.CMenu;
import com.jdatabeans.beans.CMenuItem;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.CStatusBar;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.util.MessageBox;
import com.jdatabeans.util.PaintTools;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.storage.CheckStoragePo;
import org.free_erp.service.entity.storage.InStoragePo;
import org.free_erp.service.entity.storage.InitialStoragePo;
import org.free_erp.service.entity.storage.LossStoragePo;
import org.free_erp.service.entity.storage.MinMaxStoragePo;
import org.free_erp.service.entity.storage.MoveStoragePo;
import org.free_erp.service.entity.storage.OutStoragePo;
import org.free_erp.service.entity.storage.OutflowStoragePo;
import org.free_erp.service.entity.storage.PriceStoragePo;
import org.free_erp.service.logic.IStorageService;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

/**
 *
 * @author afa
 */
public class MainFrame extends JFrame implements MouseListener, ActionListener, HitListener {

    protected IDataSource dataSource;
    protected JDataTable table;
    protected JPopupMenu mainPopMenu;
    public static final int systemType = 0;
    private Company company;
    private CStatusBar statusBar;
    private JPanel clientPanel;
    private ObjectsPool listObjectPool;
    private CHelpInfoPane helpPane = new CHelpInfoPane();
    private Image companyLogo;
    private ObjectsPool dataBaseObjectPool;
    private JPanel salePanel;
    private JPanel sPanel;
    private JPanel cPanel;
    private JPanel cgPanel;
    private JPanel cwPanel;
    private JPanel zhPanel;
    // base info
    private JCheckBox checkF = new JCheckBox("审核权限");
    private CMenuItem bProductManageItem = new CMenuItem("商品信息");
    private CMenuItem bCustomerManageItem = new CMenuItem("客户信息");
    private CMenuItem bSupplierManageItem = new CMenuItem("供应商信息");
    private CMenuItem bEmployeeManageItem = new CMenuItem("员工信息");
    private CMenuItem bWarehouseItem = new CMenuItem("仓库信息");
    private CMenuItem bSubjectItem = new CMenuItem("会计科目");
    private CMenuItem mBussinessExpenseItem = new CMenuItem("经营费用");
    private CMenuItem mCommonExpenseItem = new CMenuItem("一般费用");
    private CMenuItem mOtherIncomeItem = new CMenuItem("其它收入");
    private CMenuItem mBankCashItem = new CMenuItem("现金银行");

    //account:
    private CMenuItem mPreReceivableItem = new CMenuItem("销售预收");
    private CMenuItem mReceivableClearingItem = new CMenuItem("收款结算");
    private CMenuItem mPrePayableItem = new CMenuItem("采购预付");
    private CMenuItem mPayableClearingItem = new CMenuItem("付款结算");
    private CMenuItem refreshItem = new CMenuItem("刷新基础数据");
    private CMenuItem bDatabaseItem = new CMenuItem("数据库备份与还原");
    private CMenuItem exitItem = new CMenuItem("退出系统");
    // 系统初始化
    private CMenuItem fStorageBeginManageItem = new CMenuItem("库存期初");
    private CMenuItem bSubjectBeginManageItem = new CMenuItem("科目期初");
    private CMenuItem bReceivableBeginMangeItem = new CMenuItem("应收期初");
    private CMenuItem bPaymentBeginManageItem = new CMenuItem("应付期初");

    //
    private CMenuItem pOrderItem = new CMenuItem("采购订单");
    private CMenuItem pPurchaseItem = new CMenuItem("采购单");
    private CMenuItem pPaymentItem = new CMenuItem("付款结算");
    private CMenuItem pOrderChangedItem = new CMenuItem("订单变更");
    private CMenuItem pOrderBack = new CMenuItem("采购退货");

    //
    private CMenuItem sQuoteItem = new CMenuItem("商品报价");
    private CMenuItem sOrderItem = new CMenuItem("销售订单");
    private CMenuItem sSaleListItem = new CMenuItem("销售单");
    private CMenuItem sGartheringItem = new CMenuItem("收款结算");
    private CMenuItem sAjustPriceItem = new CMenuItem("商品调价");
    private CMenuItem sOrderChangedItem = new CMenuItem("订单变更");
    private CMenuItem sOrderBackItem = new CMenuItem("销售退货");
    // warehouse
    private CMenuItem wStorageInItem = new CMenuItem("商品入库");
    private CMenuItem wStorageOutItem = new CMenuItem("商品出库");
    private CMenuItem wStorageOutflowItem = new CMenuItem("库存报溢");
    private CMenuItem wStorageLossItem = new CMenuItem("库存报损");
    private CMenuItem wStorageCheckItem = new CMenuItem("盘点管理");
    private CMenuItem wStorageMoveItem = new CMenuItem("移库管理");
    private CMenuItem wStorageChangeMoneyItem = new CMenuItem("库存变价");
    private CMenuItem wStorageMaxMinItem = new CMenuItem("库存上下限");
    // produce

    // system
    private CMenuItem sCompanyInfoItem = new CMenuItem("公司信息");
    //private CMenuItem sRoleItem = new CMenuItem("角色管理");
    private CMenuItem sUserItem = new CMenuItem("用户管理");
    private CMenuItem sChangePasswordItem = new CMenuItem("修改密码");
    private CMenuItem sLogItem = new CMenuItem("系统日志");
    private CMenuItem sOptionItem = new CMenuItem("系统选项");
    private CMenuItem sNumberManageItem = new CMenuItem("编号管理");
    private CMenu sListManage = new CMenu("下拉框管理");
    //liufei
    //sListManage
    private CMenuItem sListProUnitItem = new CMenuItem("商品单位");
    private CMenuItem sListProShelfItem = new CMenuItem("商品货位");
    private CMenuItem sListProTaxrateItem = new CMenuItem("商品税率");
    private CMenuItem sListCusTypeItem = new CMenuItem("客户类型");
    private CMenuItem sListCusPriceItem = new CMenuItem("默认价格");
    private CMenuItem sListCusInvoiceItem = new CMenuItem("发票类型");
    private CMenuItem sListEmpDutyItem = new CMenuItem("员工职务");
    private CMenuItem sListEmpDegreeItem = new CMenuItem("员工职称");
    private CMenuItem sListEmpLevelItem = new CMenuItem("文化程度");
    private CMenuItem sListEmpHealthItem = new CMenuItem("健康状况");
    private CMenuItem sListStoTypeItem = new CMenuItem("仓库类型");
    // view
    private JCheckBoxMenuItem vShowNavigationItem = new JCheckBoxMenuItem(
            "显示导航栏");
    private JCheckBoxMenuItem vShowToolbarItem = new JCheckBoxMenuItem("显示工具条");
    private JCheckBoxMenuItem vShowLogItem = new JCheckBoxMenuItem("显示系统日志");
    //private CMenuItem vLookAndFeelItem = new CMenuItem("界面风格");

    //report
    
    private CMenuItem rManagerDailyQueryItem = new CMenuItem("日报表查询");
    private CMenuItem rManagerMonthQueryItem = new CMenuItem("月报表查询");
    private CMenuItem rManagerYearQueryItem = new CMenuItem("年报表查询");
    private CMenuItem rManagerStorageBalanceItem = new CMenuItem("库存商品余额查询");
    private CMenuItem rManagerStorageChangeItem = new CMenuItem("库存商品变化明细查询");
    //private CMenuItem rManagerStorageChangeMonthItem = new CMenuItem("库存商品变化明细月查询");
    private CMenuItem rManagerSubjectBalanceItem = new CMenuItem("科目余额查询");
    private CMenuItem rManagerSupplierPayBalanceItem = new CMenuItem("供应商应付余额查询");
    private CMenuItem rManagerCustomerReceiveBalanceItem = new CMenuItem("客商应收余额查询");

    private CMenu rDataBaseQueryMenu = new CMenu("基础数据");//liufeiadd
    private CMenuItem rProductsQueryItem = new CMenuItem("商品信息查询");
    private CMenuItem rSuppliersQueryItem = new CMenuItem("供应商信息查询");
    private CMenuItem rCustomersQueryItem = new CMenuItem("客户信息查询");
    private CMenuItem rEmployeesQueryItem = new CMenuItem("职员信息查询");



    private CMenu rStorageReportMenu = new CMenu("仓库报表");
    private CMenuItem rStorageBalanceItem = new CMenuItem("库存商品余额查询");
    private CMenuItem rStorageChangeItem = new CMenuItem("库存商品变化明细查询");
    private CMenuItem rStorageChangeMonthItem = new CMenuItem("库存商品变化明细月查询");
    
    private CMenuItem rStorageInDetailedItem = new CMenuItem("库存入库明细查询");
    private CMenuItem rStorageOutDetailedItem = new CMenuItem("库存出库明细查询");
    private CMenuItem rStorageInItem = new CMenuItem("库存商品入库查询");
    private CMenuItem rStorageOutItem = new CMenuItem("库存商品出库查询");
    private CMenuItem rStorageMaxMinItem = new CMenuItem("库存商品上下限查询");
    private CMenuItem rStorageAlertItem = new CMenuItem("库存警报查询");
    private CMenuItem rStorageMoveReporItem = new CMenuItem("库存商品移库明细");
    private CMenuItem rStorageCheckReporItem = new CMenuItem("库存商品盘点明细");
    private CMenuItem rStorageOutflowReporItem = new CMenuItem("库存商品报溢明细");
    private CMenuItem rStorageLossReporItem = new CMenuItem("库存商品报损明细");
    private CMenuItem rStorageChangeMoneyItem = new CMenuItem("库存商品变价明细");
    private CMenuItem rStorageMoveItem = new CMenuItem("库存商品移库查询");
    private CMenuItem rStorageCheckItem = new CMenuItem("库存商品盘点查询");
    private CMenuItem rStorageOutflowItem = new CMenuItem("库存商品报溢查询");
    private CMenuItem rStorageLossItem = new CMenuItem("库存商品报损查询");

    //	private CMenuItem rSupplierTradeDetailItem = new CMenuItem("供应商往来流水查询");
    //	private CMenuItem rCustomerTradeDetailItem = new CMenuItem("往来流水查询");

    //purchase report
    private CMenu rPurchaseReportMenu = new CMenu("采购报表");
    private CMenuItem rPurchaseIndentItem = new CMenuItem("采购订单查询");
    private CMenuItem rPurchaseIndentProStatItem = new CMenuItem("采购订单商品统计");
    private CMenuItem rPurchaseIndentProductItem = new CMenuItem("采购订单商品明细");
    private CMenuItem rPurchaseIndentBalanceItem = new CMenuItem("采购订单余额查询");////暂时屏蔽
    private CMenuItem rPurchaseIndentProBalStatItem = new CMenuItem("采购订单商品余额统计");////暂时屏蔽
    private CMenuItem rPurchaseIndentProBalDetailItem = new CMenuItem("采购订单商品余额明细查询");////暂时屏蔽
    private CMenuItem rPurchaseIndentSupplierStatItem = new CMenuItem("采购订单供应商统计");
    private CMenuItem rPurchaseIndentPersonStatItem = new CMenuItem("采购订单业务员统计");
    private CMenuItem rProductPurchaseStatItem = new CMenuItem("商品采购统计表");
    private CMenuItem rProductPurchaseDetailItem = new CMenuItem("商品采购明细查询");
    private CMenuItem rSupplierPurchaseStatItem = new CMenuItem("供应商采购统计表");
    private CMenuItem rSupplierPurchaseDetailItem = new CMenuItem("供应商采购明细查询");
    private CMenuItem rEmployeePurchaseStatItem = new CMenuItem("职员采购统计表");
    private CMenuItem rEmployeePurchaseDetailItem = new CMenuItem("职员采购明细查询");
    private CMenuItem rProductPurchaseGatherItem = new CMenuItem("商品采购汇总表");////暂时屏蔽
    private CMenuItem rProductPurchasePriceWaveItem = new CMenuItem("商品采购价格波动表");////暂时屏蔽
    private CMenu rSaleReportMenu = new CMenu("销售报表");
    private CMenuItem rSaleIndentItem = new CMenuItem("销售订单查询");
    private CMenuItem rSaleIndentProStatItem = new CMenuItem("销售订单商品统计");
    private CMenuItem rSaleIndentProductItem = new CMenuItem("销售订单商品明细");
    private CMenuItem rSaleIndentBalanceItem = new CMenuItem("销售订单余额查询");////暂时屏蔽
    private CMenuItem rSaleIndentProBalStatItem = new CMenuItem("销售订单商品余额统计");////暂时屏蔽
    private CMenuItem rSaleIndentProBalDetailItem = new CMenuItem("销售订单商品余额明细查询");////暂时屏蔽
    private CMenuItem rSaleIndentCustomerStatItem = new CMenuItem("销售订单客商统计");
    private CMenuItem rSaleIndentPersonStatItem = new CMenuItem("销售订单业务员统计");
//    private CMenuItem rCustomerMonthSaleCompareItem = new CMenuItem("客商月销售比较表");
//    private CMenuItem rProductMonthSaleCompareItem = new CMenuItem("商品月销售比较表");
//    private CMenuItem rCustomerSaleOrderItem = new CMenuItem("客商销售排行榜");
//    private CMenuItem rProductSaleOrderItem = new CMenuItem("商品销售排行榜");
//    private CMenuItem rEmployeeSaleOrderItem = new CMenuItem("职员销售排行榜");
    private CMenuItem rProductSaleStatItem = new CMenuItem("商品销售统计表");
    private CMenuItem rProductSaleDetailItem = new CMenuItem("商品销售明细查询");
    private CMenuItem rCustomerSaleStatItem = new CMenuItem("客商销售统计表");
    private CMenuItem rCustomerSaleDetailItem = new CMenuItem("客商销售明细查询");
    private CMenuItem rEmployeeSaleStatItem = new CMenuItem("职员销售统计表");
    private CMenuItem rEmployeeSaleDetailItem = new CMenuItem("职员销售明细查询");
//    private CMenuItem rProductSaleGatherItem = new CMenuItem("商品销售汇总表");
//    private CMenuItem rProductPriceItem = new CMenuItem("商品价格查询");
//    private CMenuItem rProductSalePriceWaveItem = new CMenuItem("商品销售价格波动表");
    private CMenu rFinanceReportMenu = new CMenu("财务报表");
    private CMenuItem rSubjectBalanceItem = new CMenuItem("科目余额查询");
    private CMenuItem rCashBankMonthReportItem = new CMenuItem("现金银行月报表");////暂时屏蔽
    private CMenuItem rCashBankDetailItem = new CMenuItem("现金银行明细查询");////暂时屏蔽
    private CMenuItem rDailyFeeReportItem = new CMenuItem("日常费用报表");
    private CMenuItem rDailyFeeDetailItem = new CMenuItem("日常费用明细查询");
    private CMenuItem rOtherIncomeMonthReportItem = new CMenuItem("其它收入月报表");////暂时屏蔽
    private CMenuItem rOtherIncomeDetailItem = new CMenuItem("其它收入明细查询");////暂时屏蔽
    private CMenu rBalanceReportMenu = new CMenu("结算报表");
    private CMenuItem rSupplierPayBalanceItem = new CMenuItem("供应商应付余额查询");
    private CMenuItem rSupplierUnbalanceDetailItem = new CMenuItem("供应商未结明细查询");
    private CMenuItem rCustomerReceiveBalanceItem = new CMenuItem("客商应收余额查询");
    private CMenuItem rCustomerUnbalanceDetailItem = new CMenuItem("客商未结明细查询");
    private CMenuItem rSupplierQueryItem = new CMenuItem("供应商往来流水查询");////暂时屏蔽
    private CMenuItem rCustomerQueryItem = new CMenuItem("客商往来流水查询");////暂时屏蔽

    //help
    private CMenuItem hContentItem = new CMenuItem("帮助手册");
    private CMenuItem hAboutItem = new CMenuItem("关于本系统");
    private CMenuItem hQuestionMenuItem = new CMenuItem("问题反馈");
    private CMenuItem hCustomerQuoteMenuItem = new CMenuItem("软件功能扩展(定制模块)");
    
    private Image mainIcon;
    //private IPermissionsService service = Main.getServiceManager().getPermissionsService();
    private User user;
    private ProductSelectPanel productSelectPanel;
    private HashMap<String, ImageIcon> icons = new HashMap<String, ImageIcon>();

    public MainFrame() {
        initComp();
        initHitHelpInfos();
    }

    private void initIcons() {
        this.icons.put("add", new ImageIcon(getClass().getResource(
                "/resources/add.gif")));
        this.icons.put("edit", new ImageIcon(getClass().getResource(
                "/resources/page_edit.gif")));

        this.icons.put("saveandnew", new ImageIcon(getClass().getResource(
                "/resources/saveandnew.gif")));
        this.icons.put("new", new ImageIcon(getClass().getResource(
                "/resources/page_add.gif")));
        this.icons.put("open", new ImageIcon(getClass().getResource(
                "/resources/open.gif")));
        this.icons.put("delete", new ImageIcon(getClass().getResource(
                "/resources/page_delete.gif")));
        this.icons.put("export", new ImageIcon(getClass().getResource(
                "/resources/page_excel.gif")));
        this.icons.put("save", new ImageIcon(getClass().getResource(
                "/resources/page_save.gif")));
        this.icons.put("next", new ImageIcon(getClass().getResource(
                "/resources/next.gif")));
        this.icons.put("previous", new ImageIcon(getClass().getResource(
                "/resources/previous.gif")));

        this.icons.put("addtype", new ImageIcon(getClass().getResource(
                "/resources/page_addlb.gif")));
        this.icons.put("edittype", new ImageIcon(getClass().getResource(
                "/resources/page_modlb.gif")));
        this.icons.put("deletetype", new ImageIcon(getClass().getResource(
                "/resources/page_dellb.gif")));
        this.icons.put("next", new ImageIcon(getClass().getResource(
                "/resources/fastforward.gif")));
        this.icons.put("import", new ImageIcon(getClass().getResource(
                "/resources/page_excel.gif")));
        this.icons.put("previous", new ImageIcon(getClass().getResource(
                "/resources/rewind.gif")));
        this.icons.put("default", new ImageIcon(getClass().getResource(
                "/resources/default.gif")));
        this.icons.put("last", new ImageIcon(getClass().getResource(
                "/resources/endpage.gif")));
        this.icons.put("first", new ImageIcon(getClass().getResource(
                "/resources/homepage.gif")));
        this.icons.put("print", new ImageIcon(getClass().getResource(
                "/resources/page_print.gif")));
//        this.icons.put("printpreview", new ImageIcon(getClass().getResource(
//                "/resources/printpreview.gif")));
//        this.icons.put("close", new ImageIcon(getClass().getResource(
//                "/resources/page_close.gif")));
//        this.icons.put("refresh", new ImageIcon(getClass().getResource(
//                "/resources/main/refresh.png")));
    }

    public Image getMainImage() {
        return this.mainIcon;
    }

    public Image getCompanyLogo() {
        if (this.companyLogo == null) {
            this.companyLogo = Main.getServiceManager().getCompanyService().getCompany(company.getId()).getImage();
        }
        return this.companyLogo;
    }

    public ImageIcon getIcon(String key) {
        ImageIcon icon = this.icons.get(key);
        if (icon == null) {
            return this.icons.get("default");
        }
        return icon;
    }

    private void initComp() {
        try {
            mainIcon = ImageIO.read(this.getClass().getResource("/resources/main.png"));
            this.setIconImage(mainIcon);
            this.setTitle("智丰进销存财管理系统v10.5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initIcons();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(d.width, d.height - 30);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenu();
        this.loadStatusBar();
        this.getContentPane().setBackground(Color.white);
        this.getContentPane().add("North", createTopPane());

        this.getContentPane().add("West", this.createModulePane());
        clientPanel = this.createClientPane();
        clientPanel.setLayout(null);
        this.getContentPane().add("Center", clientPanel);
        initPanels();
        this.clientPanel.add(helpPane);
        installListeners();

    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return this.company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void initMenu() {
        //end listeners;
        //menus
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        CMenu sysMenu = new CMenu("系统(&S)");


        sysMenu.add(sCompanyInfoItem);
        sysMenu.addSeparator();
        sysMenu.add(fStorageBeginManageItem);
        sysMenu.add(bSubjectBeginManageItem);
        sysMenu.add(bReceivableBeginMangeItem);
        sysMenu.add(bPaymentBeginManageItem);
        sysMenu.addSeparator();



        //systemMenu.add(sRoleItem);//liufei
        sysMenu.add(sUserItem);
        sysMenu.add(sChangePasswordItem);
        sysMenu.add(sLogItem);
        sysMenu.addSeparator();
        //systemMenu.add(this.sOptionItem);//no show
        sysMenu.add(this.sNumberManageItem);//
        //systemMenu.add(this.sListManage);
        sysMenu.addSeparator();
        sysMenu.add(this.exitItem);
        menuBar.add(sysMenu);


        CMenu baseMenu = new CMenu("基础资料(&B)");

//        bProductManageItem.setIcon(new ImageIcon(getClass().getResource("/resources/main/product16.png")));
//        bCustomerManageItem.setIcon(new ImageIcon(getClass().getResource("/resources/main/usersinfo16.png")));
//        bEmployeeManageItem.setIcon(new ImageIcon(getClass().getResource("/resources/main/workerinfo16.png")));
//        bWarehouseItem.setIcon(new ImageIcon(getClass().getResource("/resources/main/newsstock16.png")));
        menuBar.add(baseMenu);
        CMenu productMenu = new CMenu("商品(&M)");
        baseMenu.add(productMenu);
        productMenu.add(bProductManageItem);
        productMenu.addSeparator();
        productMenu.add(sListProUnitItem);
        productMenu.add(sListProShelfItem);
        productMenu.add(sListProTaxrateItem);

        CMenu storageMenu = new CMenu("仓库(&S)");
        baseMenu.add(storageMenu);
        storageMenu.add(bWarehouseItem);
        CMenu customerMenu = new CMenu("往来单位(&T)");
        baseMenu.add(customerMenu);
        customerMenu.add(bSupplierManageItem);
        customerMenu.add(bCustomerManageItem);
        CMenu employeeMenu = new CMenu("部门职员(&G)");
        baseMenu.add(employeeMenu);
        employeeMenu.add(this.bEmployeeManageItem);
        baseMenu.addSeparator();
        baseMenu.add(bSubjectItem);
        baseMenu.addSeparator();
        baseMenu.add(refreshItem);

        //*********************
        CMenu subMenu = new CMenu("功能模块(&F)");
        menuBar.add(subMenu);
        //
        CMenu purchaseMenu = new CMenu("采购管理");
        subMenu.add(purchaseMenu);
        //menuBar.add(purchaseMenu);
        purchaseMenu.add(this.pOrderItem);
        purchaseMenu.add(this.pPurchaseItem);
        purchaseMenu.add(this.pPaymentItem);
        purchaseMenu.add(this.pOrderChangedItem);
        purchaseMenu.add(this.pOrderBack);


        CMenu saleMenu = new CMenu("销售管理");
        //menuBar.add(saleMenu);
        subMenu.add(saleMenu);
        saleMenu.add(sQuoteItem);
        saleMenu.add(sOrderItem);
        saleMenu.add(sSaleListItem);
        saleMenu.add(sGartheringItem);
        saleMenu.add(this.sAjustPriceItem);
        saleMenu.add(this.sOrderChangedItem);
        saleMenu.add(this.sOrderBackItem);

        CMenu warehouseMenu = new CMenu("库存管理");
        //menuBar.add(warehouseMenu);
        subMenu.add(warehouseMenu);

        CMenu purchasePaymentMenu = new CMenu("钱流管理");
        purchasePaymentMenu.add(mPreReceivableItem);
        purchasePaymentMenu.add(mReceivableClearingItem);
        purchasePaymentMenu.add(mPrePayableItem);
        purchasePaymentMenu.add(mPayableClearingItem);
        purchasePaymentMenu.addSeparator();
        purchasePaymentMenu.add(mBussinessExpenseItem);
        purchasePaymentMenu.add(mCommonExpenseItem);
        purchasePaymentMenu.add(mOtherIncomeItem);
        purchasePaymentMenu.add(mBankCashItem);
        subMenu.add(purchasePaymentMenu);

//        CMenu moneyBankMenu = new CMenu("财务管理(&K)");
//
//
//        subMenu.add(moneyBankMenu);

        warehouseMenu.add(this.wStorageInItem);
        warehouseMenu.add(this.wStorageOutItem);
        warehouseMenu.add(this.wStorageOutflowItem);
        warehouseMenu.add(this.wStorageLossItem);
        warehouseMenu.add(this.wStorageCheckItem);
        warehouseMenu.add(this.wStorageMoveItem);
        warehouseMenu.add(this.wStorageChangeMoneyItem);
        warehouseMenu.add(this.wStorageMaxMinItem);
        //CMenu produceMenu = new CMenu("??骇绠＄?(&D)");
        //menuBar.add(produceMenu);

        ////liufei
//        sListManage.add(this.sListProUnitItem);
//        sListManage.add(this.sListProShelfItem);
//        sListManage.add(this.sListProTaxrateItem);
//        sListManage.add(this.sListCusTypeItem);
//        //sListManage.add(this.sListCusPriceItem);
//        //sListManage.add(this.sListCusInvoiceItem);
//        sListManage.add(this.sListEmpDutyItem);
//        sListManage.add(this.sListEmpDegreeItem);
//        sListManage.add(this.sListEmpLevelItem);
//        sListManage.add(this.sListEmpHealthItem);
//        sListManage.add(this.sListStoTypeItem);

        CMenu reportMenu = new CMenu("报表(&R)");
        menuBar.add(reportMenu);
        reportMenu.add(rManagerDailyQueryItem );
        reportMenu.add(rManagerMonthQueryItem );
        reportMenu.add(rManagerYearQueryItem );
        reportMenu.add(rManagerStorageBalanceItem );
        reportMenu.add(rManagerStorageChangeItem );
        reportMenu.add(rManagerSubjectBalanceItem );
        reportMenu.add(rManagerSupplierPayBalanceItem );
        reportMenu.add(rManagerCustomerReceiveBalanceItem );
        reportMenu.addSeparator();

        reportMenu.add(rDataBaseQueryMenu);
        rDataBaseQueryMenu.add(this.rProductsQueryItem);
        rDataBaseQueryMenu.add(this.rSuppliersQueryItem);
        rDataBaseQueryMenu.add(this.rCustomersQueryItem);
        rDataBaseQueryMenu.add(this.rEmployeesQueryItem);
       

        reportMenu.add(rPurchaseReportMenu);
        rPurchaseReportMenu.add(rPurchaseIndentItem);
        rPurchaseReportMenu.add(rPurchaseIndentProStatItem);
        rPurchaseReportMenu.add(rPurchaseIndentProductItem);
        //rPurchaseReportMenu.add(rPurchaseIndentBalanceItem);
        //rPurchaseReportMenu.add(rPurchaseIndentProBalStatItem);
        //rPurchaseReportMenu.add(rPurchaseIndentProBalDetailItem);
        rPurchaseReportMenu.add(rPurchaseIndentSupplierStatItem);
        rPurchaseReportMenu.add(rPurchaseIndentPersonStatItem);
        rPurchaseReportMenu.add(rProductPurchaseStatItem);
        rPurchaseReportMenu.add(rProductPurchaseDetailItem);
        rPurchaseReportMenu.add(rSupplierPurchaseStatItem);
        rPurchaseReportMenu.add(rSupplierPurchaseDetailItem);
        rPurchaseReportMenu.add(rEmployeePurchaseStatItem);
        rPurchaseReportMenu.add(rEmployeePurchaseDetailItem);
        //rPurchaseReportMenu.add(rProductPurchaseGatherItem);
        //rPurchaseReportMenu.add(rProductPurchasePriceWaveItem);
        

        reportMenu.add(rSaleReportMenu);
        rSaleReportMenu.add(rSaleIndentItem);
        rSaleReportMenu.add(rSaleIndentProStatItem);
        rSaleReportMenu.add(rSaleIndentProductItem);
        //rSaleReportMenu.add(rSaleIndentBalanceItem);
        //rSaleReportMenu.add(rSaleIndentProBalStatItem);
        //rSaleReportMenu.add(rSaleIndentProBalDetailItem);
        rSaleReportMenu.add(rSaleIndentCustomerStatItem);
        rSaleReportMenu.add(rSaleIndentPersonStatItem);
//        rSaleReportMenu.add(rCustomerMonthSaleCompareItem);
//        rSaleReportMenu.add(rProductMonthSaleCompareItem);
////        rSaleReportMenu.add(rCustomerSaleOrderItem);
//        rSaleReportMenu.add(rProductSaleOrderItem);
//        rSaleReportMenu.add(rEmployeeSaleOrderItem);
        rSaleReportMenu.add(rProductSaleStatItem);
        rSaleReportMenu.add(rProductSaleDetailItem);
        rSaleReportMenu.add(rCustomerSaleStatItem);
        rSaleReportMenu.add(rCustomerSaleDetailItem);
        rSaleReportMenu.add(rEmployeeSaleStatItem);
        rSaleReportMenu.add(rEmployeeSaleDetailItem);

//        rSaleReportMenu.add(rProductSaleGatherItem);
//        rSaleReportMenu.add(rProductPriceItem);
//        rSaleReportMenu.add(rProductSalePriceWaveItem);
        

        reportMenu.add(rStorageReportMenu);
        rStorageReportMenu.add(rStorageBalanceItem);
        rStorageReportMenu.add(rStorageChangeItem);
        rStorageReportMenu.add(rStorageChangeMonthItem);
        rStorageReportMenu.addSeparator();
        rStorageReportMenu.add(rStorageInItem);
        rStorageReportMenu.add(rStorageOutItem);
        //rStorageReportMenu.add(rStorageMoveItem);////???灞??
        rStorageReportMenu.add(rStorageCheckItem);
        rStorageReportMenu.add(rStorageOutflowItem);
        rStorageReportMenu.add(rStorageLossItem);
        rStorageReportMenu.add(rStorageMaxMinItem);
        rStorageReportMenu.add(rStorageAlertItem);
        rStorageReportMenu.addSeparator();
        rStorageReportMenu.add(rStorageInDetailedItem);
        rStorageReportMenu.add(rStorageOutDetailedItem);
        //rStorageReportMenu.add(rStorageMoveReporItem);////???灞??
        rStorageReportMenu.add(rStorageCheckReporItem);
        rStorageReportMenu.add(rStorageOutflowReporItem);
        rStorageReportMenu.add(rStorageLossReporItem);
        rStorageReportMenu.add(rStorageChangeMoneyItem);
        

        reportMenu.add(rFinanceReportMenu);
        rFinanceReportMenu.add(rSubjectBalanceItem);
        rFinanceReportMenu.add(rCashBankMonthReportItem);
        rFinanceReportMenu.add(rCashBankDetailItem);
        rFinanceReportMenu.add(rDailyFeeReportItem);
        rFinanceReportMenu.add(rDailyFeeDetailItem);
        rFinanceReportMenu.add(rOtherIncomeMonthReportItem);
        rFinanceReportMenu.add(rOtherIncomeDetailItem);
       

        reportMenu.add(rBalanceReportMenu);
        rBalanceReportMenu.add(rSupplierPayBalanceItem);
        rBalanceReportMenu.add(rSupplierUnbalanceDetailItem);
        rBalanceReportMenu.add(rCustomerReceiveBalanceItem);
        rBalanceReportMenu.add(rCustomerUnbalanceDetailItem);
        //rBalanceReportMenu.add(rSupplierQueryItem);
        //rBalanceReportMenu.add(rCustomerQueryItem);

//        CMenu viewMenu = new CMenu("视图(&V)");
//        menuBar.add(viewMenu);
//        viewMenu.add(this.vShowNavigationItem);
//        //vShowNavigationItem.setArmed(true);
//        //viewMenu.add(this.vShowToolbarItem);
//        viewMenu.add(this.vShowLogItem);
//        viewMenu.addSeparator();
        //viewMenu.add(this.vLookAndFeelItem);

        CMenu helpMenu = new CMenu("帮助(&H)");

//        hContentItem.setIcon(new ImageIcon(getClass().getResource("/resources/main/help16.png")));
//        hAboutItem.setIcon(new ImageIcon(getClass().getResource("/resources/main/main16.png")));
        menuBar.add(helpMenu);
        helpMenu.add(this.hContentItem);
        //helpMenu.add(hQuestionMenuItem);
        //helpMenu.add(hCustomerQuoteMenuItem);
        helpMenu.add(this.hAboutItem);

        //
        this.vShowLogItem.setSelected(true);
        this.vShowNavigationItem.setSelected(true);
        this.vShowToolbarItem.setSelected(true);



    }

    private void installListeners() {
        bProductManageItem.addActionListener(this);
        bCustomerManageItem.addActionListener(this);
        bEmployeeManageItem.addActionListener(this);
        bWarehouseItem.addActionListener(this);
        bSubjectItem.addActionListener(this);
        mBussinessExpenseItem.addActionListener(this);
        mCommonExpenseItem.addActionListener(this);
        mOtherIncomeItem.addActionListener(this);
        mBankCashItem.addActionListener(this);
        mPreReceivableItem.addActionListener(this);
        mReceivableClearingItem.addActionListener(this);
        mPrePayableItem.addActionListener(this);
        mPayableClearingItem.addActionListener(this);
        refreshItem.addActionListener(this);
        bDatabaseItem.addActionListener(this);
        exitItem.addActionListener(this);
        fStorageBeginManageItem.addActionListener(this);
        bSubjectBeginManageItem.addActionListener(this);
        bReceivableBeginMangeItem.addActionListener(this);
        bPaymentBeginManageItem.addActionListener(this);
        pOrderItem.addActionListener(this);
        pPurchaseItem.addActionListener(this);
        pPaymentItem.addActionListener(this);
        pOrderBack.addActionListener(this);
        sQuoteItem.addActionListener(this);
        sOrderItem.addActionListener(this);
        sSaleListItem.addActionListener(this);
        sGartheringItem.addActionListener(this);
        sAjustPriceItem.addActionListener(this);
        sOrderBackItem.addActionListener(this);
        wStorageInItem.addActionListener(this);
        wStorageOutItem.addActionListener(this);
        wStorageOutflowItem.addActionListener(this);
        wStorageLossItem.addActionListener(this);
        wStorageCheckItem.addActionListener(this);
        wStorageMoveItem.addActionListener(this);
        wStorageChangeMoneyItem.addActionListener(this);
        wStorageMaxMinItem.addActionListener(this);
        sCompanyInfoItem.addActionListener(this);
        sUserItem.addActionListener(this);
        sChangePasswordItem.addActionListener(this);
        sLogItem.addActionListener(this);
        sOptionItem.addActionListener(this);
        sNumberManageItem.addActionListener(this);
        sListManage.addActionListener(this);
        sListProUnitItem.addActionListener(this);
        sListProShelfItem.addActionListener(this);
        sListProTaxrateItem.addActionListener(this);
        sListCusTypeItem.addActionListener(this);
        hQuestionMenuItem.addActionListener(this);
        hCustomerQuoteMenuItem.addActionListener(this);

        sListCusPriceItem.addActionListener(this);
        sListCusInvoiceItem.addActionListener(this);
        sListEmpDutyItem.addActionListener(this);
        sListEmpDegreeItem.addActionListener(this);
        sListEmpLevelItem.addActionListener(this);
        sListEmpHealthItem.addActionListener(this);
        sListStoTypeItem.addActionListener(this);
        bSupplierManageItem.addActionListener(this);

         this.rProductsQueryItem.addActionListener(this);
        this.rCustomersQueryItem.addActionListener(this);
        this.rSuppliersQueryItem.addActionListener(this);
        this.rEmployeesQueryItem.addActionListener(this);

        this.rManagerDailyQueryItem .addActionListener(this);
        this.rManagerMonthQueryItem .addActionListener(this);
        this.rManagerYearQueryItem .addActionListener(this);
        this.rManagerStorageBalanceItem .addActionListener(this);
        this.rManagerStorageChangeItem .addActionListener(this);
        this.rManagerSubjectBalanceItem .addActionListener(this);
        this.rManagerSupplierPayBalanceItem .addActionListener(this);
        this.rManagerCustomerReceiveBalanceItem .addActionListener(this);



        rStorageBalanceItem.addActionListener(this);
        rStorageChangeItem.addActionListener(this);
        rStorageChangeMonthItem.addActionListener(this);
        rStorageInDetailedItem.addActionListener(this);
        rStorageOutDetailedItem.addActionListener(this);
        rStorageInItem.addActionListener(this);
        rStorageOutItem.addActionListener(this);
        rStorageMaxMinItem.addActionListener(this);
        rStorageAlertItem.addActionListener(this);
        rStorageMoveReporItem.addActionListener(this);
        rStorageCheckReporItem.addActionListener(this);
        rStorageOutflowReporItem.addActionListener(this);
        rStorageLossReporItem.addActionListener(this);
        rStorageChangeMoneyItem.addActionListener(this);
        rStorageMoveItem.addActionListener(this);
        rStorageCheckItem.addActionListener(this);
        rStorageOutflowItem.addActionListener(this);
        rStorageLossItem.addActionListener(this);
        rPurchaseIndentItem.addActionListener(this);
        rPurchaseIndentProStatItem.addActionListener(this);
        rPurchaseIndentProductItem.addActionListener(this);
        rPurchaseIndentBalanceItem.addActionListener(this);
        rPurchaseIndentProBalStatItem.addActionListener(this);       //("采购订单商品余额统计");////暂时屏蔽
        rPurchaseIndentProBalDetailItem.addActionListener(this);       //("采购订单商品余额明细查询");////暂时屏蔽
        rPurchaseIndentSupplierStatItem.addActionListener(this);       //("采购订单供应商统计");
        rPurchaseIndentPersonStatItem.addActionListener(this);       //("采购订单业务员统计");
        rProductPurchaseStatItem.addActionListener(this);       //("商品采购统计表");
        rProductPurchaseDetailItem.addActionListener(this);       //("商品采购明细查询");
        rSupplierPurchaseStatItem.addActionListener(this);       //("供应商采购统计表");
        rSupplierPurchaseDetailItem.addActionListener(this);       //("供应商采购明细查询");
        rEmployeePurchaseStatItem.addActionListener(this);       //("职员采购统计表");
        rEmployeePurchaseDetailItem.addActionListener(this);       //("职员采购明细查询");
        rProductPurchaseGatherItem.addActionListener(this);       //("商品采购汇总表");////暂时屏蔽
        rProductPurchasePriceWaveItem.addActionListener(this);       //("商品采购价格波动表");////暂时屏蔽

        rSaleIndentItem.addActionListener(this);       //("销售订单查询");
        rSaleIndentProStatItem.addActionListener(this);       //("销售订单商品统计");
        rSaleIndentProductItem.addActionListener(this);       //("销售订单商品明细");
        rSaleIndentBalanceItem.addActionListener(this);       //("销售订单余额查询");////暂时屏蔽
        rSaleIndentProBalStatItem.addActionListener(this);       //("销售订单商品余额统计");////暂时屏蔽
        rSaleIndentProBalDetailItem.addActionListener(this);       //("销售订单商品余额明细查询");////暂时屏蔽
        rSaleIndentCustomerStatItem.addActionListener(this);       //("销售订单客商统计");
        rSaleIndentPersonStatItem.addActionListener(this);       //("销售订单业务员统计");
//    rCustomerMonthSaleCompareItem.addActionListener(this);       //("客商月销售比较表");
//    rProductMonthSaleCompareItem.addActionListener(this);       //("商品月销售比较表");
//    rCustomerSaleOrderItem.addActionListener(this);       //("客商销售排行榜");
//    rProductSaleOrderItem.addActionListener(this);       //("商品销售排行榜");
//    rEmployeeSaleOrderItem.addActionListener(this);       //("职员销售排行榜");
        rProductSaleStatItem.addActionListener(this);       //("商品销售统计表");
        rProductSaleDetailItem.addActionListener(this);       //("商品销售明细查询");
        rCustomerSaleStatItem.addActionListener(this);       //("客商销售统计表");
        rCustomerSaleDetailItem.addActionListener(this);       //("客商销售明细查询");
        rEmployeeSaleStatItem.addActionListener(this);       //("职员销售统计表");
        rEmployeeSaleDetailItem.addActionListener(this);       //("职员销售明细查询");
//    rProductSaleGatherItem.addActionListener(this);       //("商品销售汇总表");
//    rProductPriceItem.addActionListener(this);       //("商品价格查询");
//    rProductSalePriceWaveItem.addActionListener(this);       //("商品销售价格波动表");

        rSubjectBalanceItem.addActionListener(this);       //("科目余额查询");
        rCashBankMonthReportItem.addActionListener(this);       //("现金银行月报表");////暂时屏蔽
        rCashBankDetailItem.addActionListener(this);       //("现金银行明细查询");////暂时屏蔽
        rDailyFeeReportItem.addActionListener(this);       //("日常费用报表");
        rDailyFeeDetailItem.addActionListener(this);       //("日常费用明细查询");
        rOtherIncomeMonthReportItem.addActionListener(this);       //("其它收入月报表");////暂时屏蔽
        rOtherIncomeDetailItem.addActionListener(this);       //("其它收入明细查询");////暂时屏蔽
        rSupplierPayBalanceItem.addActionListener(this);       //("供应商应付余额查询");
        rSupplierUnbalanceDetailItem.addActionListener(this);       //("供应商未结明细查询");
        rCustomerReceiveBalanceItem.addActionListener(this);       //("客商应收余额查询");
        rCustomerUnbalanceDetailItem.addActionListener(this);       //("客商未结明细查询");
        rSupplierQueryItem.addActionListener(this);       //("供应商往来流水查询");////暂时屏蔽
        rCustomerQueryItem.addActionListener(this);       //("客商往来流水查询");////暂时屏蔽

        //help
        hContentItem.addActionListener(this);       //("帮助手册");
        hAboutItem.addActionListener(this);       //("关于本系统");


        productImageItem.addMouseListener(this);
        customerImageItem.addMouseListener(this);
        supplierImageItem.addMouseListener(this);
        employeeImageItem.addMouseListener(this);
        storageImageItem.addMouseListener(this);
        subjectImageItem.addMouseListener(this);

        website.addMouseListener(this);

        mBussinessExpenseImageItem.addMouseListener(this);// = new CMenuImageItem("缁??璐圭?");
        mBussinessExpenseImageItemList.addMouseListener(this);
        mCommonExpenseImageItem.addMouseListener(this);// = new CMenuItem("涓??璐圭?");
        mCommonExpenseImageItemList.addMouseListener(this);
        mOtherIncomeImageItem.addMouseListener(this);//= new CMenuItem("?跺??跺?");
        mOtherIncomeImageItemList.addMouseListener(this);
        mBankCashImageItem.addMouseListener(this);// = new CMenuItem("?惰?甯??");
        mBankCashImageItemList.addMouseListener(this);

        fStorageBeginManageImageItem.addMouseListener(this);
        fStorageBeginManageImageItemList.addMouseListener(this);
        bSubjectBeginManageImageItem.addMouseListener(this);// = new CImageItem("绉??浣?????绠＄?");
        bSubjectBeginManageImageItemList.addMouseListener(this);
        bReceivableBeginMangeImageItem.addMouseListener(this);// = new CImageItem("搴?????绠＄?");
        bReceivableBeginMangeImageItemList.addMouseListener(this);
        bPaymentBeginManageImageItem.addMouseListener(this);// = new CImageItem("搴?????绠＄?");
        bPaymentBeginManageImageItemList.addMouseListener(this);
        //purchase
        pOrderImageItem.addMouseListener(this);// = new CMenuItem("??喘璁㈠?");
        pOrderImageItemList.addMouseListener(this);
        pPurchaseImageItem.addMouseListener(this);// = new CMenuItem("??喘??);
        pPurchaseImageItemList.addMouseListener(this);

        pPaymentImageItem.addMouseListener(this);// = new CMenuItem("浠??缁??");
        pPaymentImageItemList.addMouseListener(this);

        //pOrderChangedImageItem.addMouseListener(this);// = new CMenuItem("璁㈠????");
        //pOrderChangedImageItemList.addMouseListener(this);

        pOrderBackImageItem.addMouseListener(this);// = new CMenuItem("??喘??揣");
        pOrderBackImageItemList.addMouseListener(this);

        //sale
        sQuoteImageItem.addMouseListener(this);// = new CMenuItem("????ヤ环");
        sQuoteImageItemList.addMouseListener(this);
        sOrderImageItem.addMouseListener(this);// = new CMenuItem("???璁㈠?");
        sOrderImageItemList.addMouseListener(this);
        sSaleListImageItem.addMouseListener(this);// = new CMenuItem("???娓??");
        sSaleListImageItemList.addMouseListener(this);
        sGartheringImageItem.addMouseListener(this);// = new CMenuItem("?舵?缁??");
        sGartheringImageItemList.addMouseListener(this);
        sAjustPriceImageItem.addMouseListener(this);// = new CMenuItem("???璋?环");
        sAjustPriceImageItemList.addMouseListener(this);
        //sOrderChangedImageItem.addMouseListener(this);// = new CMenuItem("璁㈠????");
        //sOrderChangedImageItemList.addMouseListener(this);
        sOrderBackImageItem.addMouseListener(this);// = new CMenuItem("?????揣");
        sOrderBackImageItemList.addMouseListener(this);
        //
        mPreReceivableImageItem.addMouseListener(this);// = new CMenuItem("???棰??绠＄?");
        mPreReceivableImageItemList.addMouseListener(this);
        mReceivableClearingImageItem.addMouseListener(this);// = new CMenuItem("?舵?缁??绠＄?");
        mReceivableClearingImageItemList.addMouseListener(this);
        mPrePayableImageItem.addMouseListener(this);// = new CMenuItem("??喘棰??绠＄?");
        mPrePayableImageItemList.addMouseListener(this);
        mPayableClearingImageItem.addMouseListener(this);// = new CMenuItem("浠??缁??绠＄?");
        mPayableClearingImageItemList.addMouseListener(this);

        // warehouse
        wStorageInImageItem.addMouseListener(this);
        wStorageInImageItemList.addMouseListener(this);
        wStorageOutImageItem.addMouseListener(this);
        wStorageOutImageItemList.addMouseListener(this);
        wStorageOutflowImageItem.addMouseListener(this);
        wStorageOutflowImageItemList.addMouseListener(this);
        wStorageLossImageItem.addMouseListener(this);
        wStorageLossImageItemList.addMouseListener(this);
        wStorageCheckImageItem.addMouseListener(this);
        wStorageCheckImageItemList.addMouseListener(this);
        wStorageMoveImageItem.addMouseListener(this);
        wStorageMoveImageItemList.addMouseListener(this);
        wStorageChangeMoneyImageItem.addMouseListener(this);
        wStorageChangeMoneyImageItemList.addMouseListener(this);
        wStorageMaxMinImageItem.addMouseListener(this);
        wStorageMaxMinImageItemList.addMouseListener(this);
        // produce

        // system
        sCompanyInfoImageItem.addMouseListener(this);
        sUserImageItem.addMouseListener(this);
//        sLogImageItem.addMouseListener(this);
//        sNumberImageItem.addMouseListener(this);
        productBaseItem.addMouseListener(this);
        storageSearchBaseItem.addMouseListener(this);//"库存查询");
        

        rManagerDailyQueryImageItem.addMouseListener(this);
        rManagerMonthQueryImageItem.addMouseListener(this);
        rManagerYearQueryImageItem.addMouseListener(this);
        rManagerStorageBalanceImageItem.addMouseListener(this);
        rManagerStorageChangeImageItem.addMouseListener(this);
        rManagerSubjectBalanceImageItem.addMouseListener(this);
        rManagerSupplierPayBalanceImageItem.addMouseListener(this);
        rManagerCustomerReceiveBalanceImageItem.addMouseListener(this);


        //report purchase
//    rPurchaseIndentImageItem.addMouseListener(this);
//    rPurchaseIndentProStatImageItem.addMouseListener(this);
//    rPurchaseIndentProductImageItem.addMouseListener(this);
//    rPurchaseIndentBalanceImageItem.addMouseListener(this);////???灞??
//    rPurchaseIndentProBalStatImageItem.addMouseListener(this);////???灞??
//    rPurchaseIndentProBalDetailImageItem.addMouseListener(this);////???灞??
//    rPurchaseIndentSupplierStatImageItem.addMouseListener(this);
//    rPurchaseIndentPersonStatImageItem.addMouseListener(this);
//    rProductPurchaseStatImageItem.addMouseListener(this);
//    rProductPurchaseDetailImageItem.addMouseListener(this);
//    rSupplierPurchaseStatImageItem.addMouseListener(this);
//    rSupplierPurchaseDetailImageItem.addMouseListener(this);
//    rEmployeePurchaseStatImageItem.addMouseListener(this);
//    rEmployeePurchaseDetailImageItem.addMouseListener(this);
//    rProductPurchaseGatherImageItem.addMouseListener(this);////???灞??
//    rProductPurchasePriceWaveImageItem.addMouseListener(this);////???灞??
//
//    //report sale
//    rSaleIndentImageItem.addMouseListener(this);
//    rSaleIndentProStatImageItem.addMouseListener(this);
//    rSaleIndentProductImageItem.addMouseListener(this);
//    rSaleIndentBalanceImageItem.addMouseListener(this);////???灞??
//    rSaleIndentProBalStatImageItem.addMouseListener(this);////???灞??
//    rSaleIndentProBalDetailImageItem.addMouseListener(this);////???灞??
//    rSaleIndentCustomerStatImageItem.addMouseListener(this);
//    rSaleIndentPersonStatImageItem.addMouseListener(this);
//    rCustomerMonthSaleCompareImageItem.addMouseListener(this);
//    rProductMonthSaleCompareImageItem.addMouseListener(this);
//    rCustomerSaleOrderImageItem.addMouseListener(this);
//    rProductSaleOrderImageItem.addMouseListener(this);
//    rEmployeeSaleOrderImageItem.addMouseListener(this);
//    rProductSaleStatImageItem.addMouseListener(this);
//    rProductSaleDetailImageItem.addMouseListener(this);
//    rSupplierSaleStatImageItem.addMouseListener(this);
//    rSupplierSaleDetailImageItem.addMouseListener(this);
//    rEmployeeSaleStatImageItem.addMouseListener(this);
//    rEmployeeSaleDetailImageItem.addMouseListener(this);
//    rProductSaleGatherImageItem.addMouseListener(this);
//    rProductPriceImageItem.addMouseListener(this);
//    rProductSalePriceWaveImageItem.addMouseListener(this);
//
//    //report finance
//    rSubjectBalanceImageItem.addMouseListener(this);
//    rCashBankMonthReportImageItem.addMouseListener(this);
//    rCashBankDetailImageItem.addMouseListener(this);
//    rDailyFeeReportImageItem.addMouseListener(this);
//    rDailyFeeDetailImageItem.addMouseListener(this);
//    rOtherIncomeMonthReportImageItem.addMouseListener(this);
//    rOtherIncomeDetailImageItem.addMouseListener(this);
//
//    //report balance
//    rSupplierPayBalanceImageItem.addMouseListener(this);
//    rSupplierUnbalanceDetailImageItem.addMouseListener(this);
//    rCustomerReceiveBalanceImageItem.addMouseListener(this);
//    rCustomerUnbalanceDetailImageItem.addMouseListener(this);
//    rSupplierQueryImageItem.addMouseListener(this);////???灞??
//    rCustomerQueryImageItem.addMouseListener(this);////???灞??


         //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/search.png")), "库存查询");
        storageMinMaxBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/search.png")), "库存上下限");
        payableBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/search.png")), "供应商应付未结");
        receivableBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/search.png")), "应收未结");
        //employeeSaleBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/employee.png")), "业务员业绩");
        //priceBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/money.png")), "价格信息");

        subjectRemainBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/money.png")), "科目余额");
        customerBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/customer.png")), "资料");
        storageBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/storage.png")), "仓库资料");
        subjectBaseItem.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/subject.png")), "会计科目");

        refreshBaseInfo.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/new/png/refresh.png")), "刷新基础信息");
        welcomePage.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/new/png/welcomeicon.png")), "欢迎页面");
        changePassword.addMouseListener(this);  //= new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/new/png/key.png")), "修改密码");

//        salesReportImageItem.addMouseListener(this);
//        storageReportImageItem.addMouseListener(this);
//        purchaseReportImageItem.addMouseListener(this);
//        bankCashReportImageItem.addMouseListener(this);
//        clearingImageItem.addMouseListener(this);
//        initReportImageItem.addMouseListener(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                Main.getSystemConfig().saveSystemConfig();

                close();

            }
        });



    }

    private JPanel createTopPane() {
        JPanel pane = new JPanel();
        pane.setBackground(Color.white);
        pane.setLayout(new BorderLayout());
        CTitlePane titlePane = new CTitlePane();
        Image middleImage = null;
        try {
            middleImage = ImageIO.read(getClass().getResource("/resources/middleback.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JPanel middlePane = new CImageBackPane(middleImage);
        middlePane.setPreferredSize(new Dimension(300, 27));
        middlePane.setLayout(new BorderLayout());
        JPanel tempPane = new JPanel();
        tempPane.setPreferredSize(new Dimension(400, 27));
        middlePane.add("West", tempPane);
        tempPane.setLayout(null);
        JLabel mLabel = new JLabel("主模块", new ImageIcon(getClass().getResource("/resources/bluebutton.png")), JLabel.RIGHT);
        tempPane.add(mLabel);
        mLabel.setBounds(2, 2, 120, 25);
        mLabel.setHorizontalAlignment(JLabel.LEFT);
        mLabel = new JLabel("子功能", new ImageIcon(getClass().getResource("/resources/yellowbutton.png")), JLabel.RIGHT);
        tempPane.add(mLabel);
        mLabel.setBounds(200, 2, 120, 25);
        mLabel.setHorizontalAlignment(JLabel.LEFT);
        JPanel mp = new JPanel();
        mp.setLayout(null);
        mp.setPreferredSize(new Dimension(400, 27));
        mp.add(welcomePage);
        welcomePage.setBounds(10, 0, 80, 27);
        welcomePage.setLineVisible(false);
        mp.add(refreshBaseInfo);
        refreshBaseInfo.setBounds(95, 0, 120, 27);
        mp.add(website);
        website.setLineVisible(false);
        website.setBounds(300, 0, 80, 27);



        this.refreshBaseInfo.setLineVisible(false);
        middlePane.add("East", mp);//this.refreshBaseInfo);

        mp.add(changePassword);
        changePassword.setLineVisible(false);
        changePassword.setBounds(210, 0, 80, 27);
        welcomePage.addMouseListener(this);
        refreshBaseInfo.addMouseListener(this);
       

        pane.add("North", titlePane);
        pane.add("South", middlePane);
        return pane;
    }

    private JPanel createModulePane() {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource("/resources/vmiddle.png"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        CModulePane modulePane = new CModulePane();
        pane.add("Center", modulePane);
        JPanel rPanel = new CImageBackPane(image, PaintTools.Y_SCALED);
        rPanel.setPreferredSize(new Dimension(10, 300));
        pane.add("East", rPanel);
        pane.setPreferredSize(new Dimension(200, 300));
        modulePane.setLayout(null);

        baseButton = new CNaviButton("基础设置", new ImageIcon(getClass().getResource(
                "/resources/png/32/order5.png")));
        modulePane.addButton(baseButton);
        int y = 0;
        baseButton.setBounds(0, y, 200, 40);

        initButton = new CNaviButton("系统期初", new ImageIcon(getClass().getResource(
                "/resources/png/items/begin/init.png")));
        modulePane.addButton(initButton);
        y += 40;
        initButton.setBounds(0, y, 200, 40);

        purchaseButton = new CNaviButton("采购管理", new ImageIcon(getClass().getResource(
                "/resources/png/32/order.png")));
        modulePane.addButton(purchaseButton);
        y += 40;

        purchaseButton.setBounds(0, y, 200, 40);

        saleButton = new CNaviButton("销售管理", new ImageIcon(getClass().getResource(
                "/resources/png/items/sale/sale32.png")));
        modulePane.addButton(saleButton);
        y += 40;
        saleButton.setBounds(0, y, 200, 40);

        storageButton = new CNaviButton("库存管理", new ImageIcon(getClass().getResource(
                "/resources/png/items/storage/storage.png")));
        modulePane.addButton(storageButton);
        y += 40;
        storageButton.setBounds(0, y, 200, 40);

        clearingButton = new CNaviButton("钱流管理", new ImageIcon(getClass().getResource(
                "/resources/png/32/22.png")));
        modulePane.addButton(clearingButton);
        y += 40;
        clearingButton.setBounds(0, y, 200, 40);

        bankCashButton = new CNaviButton("财务管理", new ImageIcon(getClass().getResource(
                "/resources/png/32/23.png")));
        //modulePane.addButton(bankCashButton);
        //y += 40;
        //bankCashButton.setBounds(0, y, 200, 40);

        reportButton = new CNaviButton("报表查询", new ImageIcon(getClass().getResource(
                "/resources/png/items/report/report.png")));
       // modulePane.addButton(reportButton);
        //y += 40;
        //reportButton.setBounds(0, y, 200, 40);

        managerButton = new CNaviButton("老板查询", new ImageIcon(getClass().getResource(
                "/resources/png/32/chart5.png")));
        modulePane.addButton(managerButton);
        y += 70;
        managerButton.setBounds(0, y, 200, 40);
        purchaseButton.addMouseListener(this);
        saleButton.addMouseListener(this);
        storageButton.addMouseListener(this);
        clearingButton.addMouseListener(this);
        bankCashButton.addMouseListener(this);
        reportButton.addMouseListener(this);
        managerButton.addMouseListener(this);
        this.baseButton.addMouseListener(this);
        this.initButton.addMouseListener(this);

        return pane;
    }

    private JPanel createClientPane() {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource("/resources/mainback.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JPanel pane = new CImageBackPane(image, PaintTools.X_SCALED);
        return pane;

    }

    protected void loadStatusBar() {
        this.statusBar = new CStatusBar();
        this.getContentPane().add("South", this.statusBar);
        statusBar.addText("版权所有：无锡市智丰科技有限公司  服务热线：phone:0510-85020681 fax:0510-85020681", 600, 500);
    }

    private static void installDefaults() {
        UIManager.getDefaults().put("menu.font", new Font("宋体", Font.PLAIN, 12));
        UIManager.getDefaults().put("required.background", new Color(255, 255, 204));
        UIManager.getDefaults().put("Table.gridColor", new Color(103, 103, 103));
        UIManager.getDefaults().put("Tree.hash", new Color(103, 103, 103));

        try {

            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

        //UIManager.setLookAndFeel("org.fife.plaf.Office2003.Office2003LookAndFeel");
        //UIManager.setLookAndFeel("org.fife.plaf.OfficeXP.OfficeXPLookAndFeel");
        //UIManager.setLookAndFeel("org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        installDefaults();
        new MainFrame().setVisible(true);
    }

    private ImageIcon getFileIcon(String name) {
        try
        {
            return new ImageIcon(this.getClass().getResource("/resources/png/items/" + name));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return this.getIcon("default");
    }

    private void initPanels() {
//        Image bgImage = null;
//        try
//        {
//            bgImage = ImageIO.read(this.getClass().getResource("/resources/new/png/welcome.png"));
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }
        welcomePanel = new JPanel();//CImageBackPane(bgImage, PaintTools.SCALED);
        JLabel flowPic = new JLabel(new ImageIcon(this.getClass().getResource("/resources/welcome.png")));
        welcomePanel.setSize(750, 560);
        welcomePanel.setLayout(null);
        welcomePanel.add(flowPic);
        flowPic.setBounds(5, 15, 730, 530);
        this.clientPanel.add(welcomePanel);
        this.basePanel = new JPanel();// , image);
        this.basePanel.setSize(750, 400);
        this.basePanel.setLayout(null);
        productImageItem = new CImageItem(this.getFileIcon("base/product2.png"), "商品信息");
        this.basePanel.add(productImageItem);
        int x = 30;
        int y = 10;
        productImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        supplierImageItem = new CImageItem(this.getFileIcon("base/number2.png"), "供应商信息");
        this.basePanel.add(supplierImageItem);
        x += 180;
        supplierImageItem.setBounds(x, y, supplierImageItem.getWidth(), supplierImageItem.getHeight());

        customerImageItem = new CImageItem(this.getFileIcon("base/customer2.png"), "客户信息");
        this.basePanel.add(customerImageItem);
        x += 180;
        customerImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        employeeImageItem = new CImageItem(this.getFileIcon("base/employee.png"), "员工信息");
        this.basePanel.add(employeeImageItem);

        x += 180;
        
        employeeImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        storageImageItem = new CImageItem(this.getFileIcon("base/storage.png"), "仓库信息");
        this.basePanel.add(storageImageItem);
        x = 30;
        y += 150;
        storageImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        subjectImageItem = new CImageItem(this.getFileIcon("base/subject.png"), "会计科目");
        this.basePanel.add(subjectImageItem);
        x += 180;
        subjectImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        sCompanyInfoImageItem = new CImageItem(this.getFileIcon("base/company.png"), "公司信息");
        this.basePanel.add(sCompanyInfoImageItem);
        x += 180;
        sCompanyInfoImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        sUserImageItem = new CImageItem(this.getFileIcon("base/user2.png"), "用户管理");
        this.basePanel.add(sUserImageItem);
        x += 180;
        sUserImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

//        sAccountOptionItem = new CImageItem(this.getFileIcon("base/options.png"), "套帐选项");
//        this.basePanel.add(sAccountOptionItem);
//        x += 150;
//        sAccountOptionItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
//
//        sNumberImageItem = new CImageItem(this.getFileIcon("base/number.png"), "编号管理");
//        this.basePanel.add(sNumberImageItem);
//        x += 150;
//        sNumberImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
//
//        sLogImageItem = new CImageItem(this.getFileIcon("base/log.png"), "系统日志");
//        this.basePanel.add(sLogImageItem);
//        x += 150;
//        sLogImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        this.clientPanel.add(basePanel);


        x = 50;
        y = 10;
        this.purchasePanel = new JPanel();// , image);
        this.purchasePanel.setSize(750, 400);
        this.purchasePanel.setLayout(null);
        pOrderImageItem = new CImageItem(this.getFileIcon("purchase/purchase_order.png"), "采购订单");
        this.purchasePanel.add(pOrderImageItem);
        pOrderImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        pOrderImageItemList = new CLittleImageItem();
        this.purchasePanel.add(pOrderImageItemList);
        pOrderImageItemList.setBounds(x + 100, y, 30, 48);

        JLabel arrow = new JLabel(this.arrowRightIcon);
        this.purchasePanel.add(arrow);
        arrow.setBounds(x + 150, y + 25, 50, 50);

//        arrow = new JLabel(this.arrowDownIcon);
//        this.purchasePanel.add(arrow);
//        arrow.setBounds(x + 25, y + 90, 50, 50);

        pPurchaseImageItem = new CImageItem(this.getFileIcon("purchase/purchase_list.png"), "采购单");
        this.purchasePanel.add(pPurchaseImageItem);
        x += 240;
        pPurchaseImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        arrow = new JLabel(this.arrowRightIcon);
        this.purchasePanel.add(arrow);
        arrow.setBounds(x + 150, y + 25, 50, 50);

        arrow = new JLabel(this.arrowDownIcon);
        this.purchasePanel.add(arrow);
        arrow.setBounds(x + 25, y + 90, 50, 50);


        pPurchaseImageItemList = new CLittleImageItem();
        this.purchasePanel.add(pPurchaseImageItemList);
        pPurchaseImageItemList.setBounds(x + 100, y, 30, 48);

        pPaymentImageItem = new CImageItem(this.getFileIcon("clearing/payclearing.png"), "付款单");
        this.purchasePanel.add(pPaymentImageItem);
        x += 240;
        pPaymentImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        pPaymentImageItemList = new CLittleImageItem();
        this.purchasePanel.add(pPaymentImageItemList);
        pPaymentImageItemList.setBounds(x + 100, y, 30, 48);


//        pOrderChangedImageItem = new CImageItem(this.getFileIcon("purchase/purchase_change.png"), "订单变更");
//        this.purchasePanel.add(pOrderChangedImageItem);
//        x = 50;
//        y += 150;
//        pOrderChangedImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
//
//        pOrderChangedImageItemList = new CLittleImageItem();
//        this.purchasePanel.add(pOrderChangedImageItemList);
//        pOrderChangedImageItemList.setBounds(x + 100, y, 30, 48);

        x = 50;
        y += 150;

        pOrderBackImageItem = new CImageItem(this.getFileIcon("purchase/purchase_back.png"), "采购退货");
        this.purchasePanel.add(pOrderBackImageItem);
        x += 240;
        pOrderBackImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        this.clientPanel.add(purchasePanel);
        pOrderBackImageItemList = new CLittleImageItem();
        this.purchasePanel.add(pOrderBackImageItemList);
        pOrderBackImageItemList.setBounds(x + 100, y, 30, 48);

//
        x = 30;
        y = 10;
        this.salePanel = new JPanel();// , image);
        this.salePanel.setSize(750, 400);
        this.salePanel.setLayout(null);
        sQuoteImageItem = new CImageItem(this.getFileIcon("sale/quote.png"), "商品报价");
        this.salePanel.add(sQuoteImageItem);
        sQuoteImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        sQuoteImageItemList = new CLittleImageItem();
        this.salePanel.add(sQuoteImageItemList);
        sQuoteImageItemList.setBounds(x + 100, y, 30, 48);

        arrow = new JLabel(this.arrowRightIcon);
        this.salePanel.add(arrow);
        arrow.setBounds(x + 130, y + 25, 50, 50);

        sOrderImageItem = new CImageItem(this.getFileIcon("sale/sale_order.png"), "销售订单");
        this.salePanel.add(sOrderImageItem);
        x += 180;
        sOrderImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        arrow = new JLabel(this.arrowRightIcon);
        this.salePanel.add(arrow);
        arrow.setBounds(x + 130, y + 25, 50, 50);

//        arrow = new JLabel(this.arrowDownIcon);
//        this.salePanel.add(arrow);
//        arrow.setBounds(x + 25, y + 90, 50, 50);


        sOrderImageItemList = new CLittleImageItem();
        this.salePanel.add(sOrderImageItemList);
        sOrderImageItemList.setBounds(x + 100, y, 30, 48);

        sSaleListImageItem = new CImageItem(this.getFileIcon("sale/sale_list.png"), "销售单");
        this.salePanel.add(sSaleListImageItem);
        x += 180;
        sSaleListImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        arrow = new JLabel(this.arrowRightIcon);
        this.salePanel.add(arrow);
        arrow.setBounds(x + 130, y + 25, 50, 50);

        arrow = new JLabel(this.arrowDownIcon);
        this.salePanel.add(arrow);
        arrow.setBounds(x + 25, y + 90, 50, 50);


        sSaleListImageItemList = new CLittleImageItem();
        this.salePanel.add(sSaleListImageItemList);
        sSaleListImageItemList.setBounds(x + 100, y, 30, 48);

        sGartheringImageItem = new CImageItem(this.getFileIcon("clearing/receiveclearing.png"), "付款结算单");
        this.salePanel.add(sGartheringImageItem);
        x += 180;
        sGartheringImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        sGartheringImageItemList = new CLittleImageItem();
        this.salePanel.add(sGartheringImageItemList);
        sGartheringImageItemList.setBounds(x + 100, y, 30, 48);

        //注掉商品调价单
        sAjustPriceImageItem = new CImageItem(this.getFileIcon("sale/change_money.png"), "商品调价单");
        //this.salePanel.add(sAjustPriceImageItem);
        x = 30;
        y += 150;
        //sAjustPriceImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        sAjustPriceImageItemList = new CLittleImageItem();
        //this.salePanel.add(sAjustPriceImageItemList);
       // sAjustPriceImageItemList.setBounds(x + 100, y, 30, 48);

//        sOrderChangedImageItem = new CImageItem(this.getFileIcon("sale/sale_change.png"), "订单变更单");
//        this.salePanel.add(sOrderChangedImageItem);
//        x += 180;
//        sOrderChangedImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
//        this.clientPanel.add(salePanel);
//        sOrderChangedImageItemList = new CLittleImageItem();
//        this.salePanel.add(sOrderChangedImageItemList);
//        sOrderChangedImageItemList.setBounds(x + 100, y, 30, 48);
    
        x += 180;

        sOrderBackImageItem = new CImageItem(this.getFileIcon("sale/sale_back.png"), "销售退货单");
        this.salePanel.add(sOrderBackImageItem);
        x += 180;
        sOrderBackImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        this.clientPanel.add(salePanel);
        sOrderBackImageItemList = new CLittleImageItem();
        this.salePanel.add(sOrderBackImageItemList);
        sOrderBackImageItemList.setBounds(x + 100, y, 30, 48);



        this.initPanel = new JPanel();


        x = 30;
        y = 10;
        storagePanel = new JPanel();// , image);
        this.storagePanel.setSize(750, 400);
        this.storagePanel.setLayout(null);
        wStorageInImageItem = new CImageItem(this.getFileIcon("storage/instorage.png"), "商品入库单");
        this.storagePanel.add(wStorageInImageItem);
        wStorageInImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        wStorageInImageItemList = new CLittleImageItem();
        this.storagePanel.add(wStorageInImageItemList);
        wStorageInImageItemList.setBounds(x + 100, y, 30, 48);



        wStorageOutImageItem = new CImageItem(this.getFileIcon("storage/outstorage.png"), "商品出库单");
        this.storagePanel.add(wStorageOutImageItem);
        x += 180;
        wStorageOutImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        wStorageOutImageItemList = new CLittleImageItem();
        this.storagePanel.add(wStorageOutImageItemList);
        wStorageOutImageItemList.setBounds(x + 100, y, 30, 48);


        wStorageOutflowImageItem = new CImageItem(this.getFileIcon("storage/outflow.png"), "库存报溢单");
        this.storagePanel.add(wStorageOutflowImageItem);
        x += 180;
        wStorageOutflowImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());


        wStorageOutflowImageItemList = new CLittleImageItem();
        this.storagePanel.add(wStorageOutflowImageItemList);
        wStorageOutflowImageItemList.setBounds(x + 100, y, 30, 48);


        wStorageLossImageItem = new CImageItem(this.getFileIcon("storage/storage_loss.png"), "库存报损单");
        this.storagePanel.add(wStorageLossImageItem);
        x += 180;
        wStorageLossImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        wStorageLossImageItemList = new CLittleImageItem();
        this.storagePanel.add(wStorageLossImageItemList);
        wStorageLossImageItemList.setBounds(x + 100, y, 30, 48);


        wStorageCheckImageItem = new CImageItem(this.getFileIcon("storage/storage_check.png"), "库存盘点单");
        this.storagePanel.add(wStorageCheckImageItem);
        x = 30;
        y += 150;
        wStorageCheckImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        wStorageCheckImageItemList = new CLittleImageItem();
        this.storagePanel.add(wStorageCheckImageItemList);
        wStorageCheckImageItemList.setBounds(x + 100, y, 30, 48);

        wStorageMoveImageItem = new CImageItem(this.getFileIcon("storage/move_storage.png"), "移库单");
        this.storagePanel.add(wStorageMoveImageItem);
        x += 180;
        wStorageMoveImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        this.clientPanel.add(storagePanel);
        wStorageMoveImageItemList = new CLittleImageItem();
        this.storagePanel.add(wStorageMoveImageItemList);
        wStorageMoveImageItemList.setBounds(x + 100, y, 30, 48);

        wStorageChangeMoneyImageItem = new CImageItem(this.getFileIcon("storage/storage_money.png"), "库存变价单");
        this.storagePanel.add(wStorageChangeMoneyImageItem);
        x += 180;
        wStorageChangeMoneyImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        wStorageChangeMoneyImageItemList = new CLittleImageItem();
        this.storagePanel.add(wStorageChangeMoneyImageItemList);
        wStorageChangeMoneyImageItemList.setBounds(x + 100, y, 30, 48);

        wStorageMaxMinImageItem = new CImageItem(this.getFileIcon("storage/storage_updown.png"), "库存上下限单");
        this.storagePanel.add(wStorageMaxMinImageItem);
        x += 180;
        wStorageMaxMinImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        wStorageMaxMinImageItemList = new CLittleImageItem();
        this.storagePanel.add(wStorageMaxMinImageItemList);
        wStorageMaxMinImageItemList.setBounds(x + 100, y, 30, 48);
        this.clientPanel.add(storagePanel);
        /*//
        this.outStoragePanel = new CGridItemPane(2, 4);


        // ....

        //
        this.checkStoragePanel = new CGridItemPane(2, 4);
         */
        // ....

        // ....

        //
        this.systemPanel = new JPanel();

        x = 30;
        y = 10;
        this.clearingPanel = new JPanel();// , image);
        this.clearingPanel.setSize(750, 400);
        this.clearingPanel.setLayout(null);
        mPreReceivableImageItem = new CImageItem(this.getFileIcon("clearing/prereceive.png"), "预收款单");
        this.clearingPanel.add(mPreReceivableImageItem);
        mPreReceivableImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        mPreReceivableImageItemList = new CLittleImageItem();
        this.clearingPanel.add(mPreReceivableImageItemList);
        mPreReceivableImageItemList.setBounds(x + 100, y, 30, 48);

        arrow = new JLabel(this.arrowRightIcon);
        this.clearingPanel.add(arrow);
        arrow.setBounds(x + 125, y + 25, 50, 50);


        mReceivableClearingImageItem = new CImageItem(this.getFileIcon("clearing/receiveclearing.png"), "收款单");
        this.clearingPanel.add(mReceivableClearingImageItem);
        x += 180;
        mReceivableClearingImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        mReceivableClearingImageItemList = new CLittleImageItem();
        this.clearingPanel.add(mReceivableClearingImageItemList);
        mReceivableClearingImageItemList.setBounds(x + 100, y, 30, 48);


        mBussinessExpenseImageItem = new CImageItem(this.getFileIcon("clearing/prereceive.png"), "经营费用单");
        this.clearingPanel.add(mBussinessExpenseImageItem);
         x += 180;
        mBussinessExpenseImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        mBussinessExpenseImageItemList = new CLittleImageItem();
        this.clearingPanel.add(mBussinessExpenseImageItemList);
        mBussinessExpenseImageItemList.setBounds(x + 100, y, 30, 48);

        mCommonExpenseImageItem = new CImageItem(this.getFileIcon("clearing/receiveclearing.png"), "一般费用单");
        this.clearingPanel.add(mCommonExpenseImageItem);
        x += 180;
        mCommonExpenseImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());


        mCommonExpenseImageItemList = new CLittleImageItem();
        this.clearingPanel.add(mCommonExpenseImageItemList);
        mCommonExpenseImageItemList.setBounds(x + 100, y, 30, 48);



        mPrePayableImageItem = new CImageItem(this.getFileIcon("clearing/prepay.png"), "预付款单");
        this.clearingPanel.add(mPrePayableImageItem);
        x = 30;
        y += 150;
        mPrePayableImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        mPrePayableImageItemList = new CLittleImageItem();
        this.clearingPanel.add(mPrePayableImageItemList);
        mPrePayableImageItemList.setBounds(x + 100, y, 30, 48);

        arrow = new JLabel(this.arrowRightIcon);
        this.clearingPanel.add(arrow);
        arrow.setBounds(x + 125, y + 25, 50, 50);

        mPayableClearingImageItem = new CImageItem(this.getFileIcon("clearing/payclearing.png"), "付款单");
        this.clearingPanel.add(mPayableClearingImageItem);
        x += 180;

        mPayableClearingImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        mPayableClearingImageItemList = new CLittleImageItem();
        this.clearingPanel.add(mPayableClearingImageItemList);
        mPayableClearingImageItemList.setBounds(x + 100, y, 30, 48);

        mOtherIncomeImageItem = new CImageItem(this.getFileIcon("clearing/prepay.png"), "其它收入单");
        this.clearingPanel.add(mOtherIncomeImageItem);
        x += 180;
        mOtherIncomeImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        mOtherIncomeImageItemList = new CLittleImageItem();
        this.clearingPanel.add(mOtherIncomeImageItemList);
        mOtherIncomeImageItemList.setBounds(x + 100, y, 30, 48);



        mBankCashImageItem = new CImageItem(this.getFileIcon("clearing/payclearing.png"), "银行转帐单");
        this.clearingPanel.add(mBankCashImageItem);
        x += 180;

        mBankCashImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        mBankCashImageItemList = new CLittleImageItem();
        this.clearingPanel.add(mBankCashImageItemList);
        mBankCashImageItemList.setBounds(x + 100, y, 30, 48);


        this.clientPanel.add(clearingPanel);
        // ....
        x = 150;
        y = 10;
        this.bankCashPanel = new JPanel();// , image);
        this.bankCashPanel.setSize(750, 400);
        this.bankCashPanel.setLayout(null);
//        mBussinessExpenseImageItem = new CImageItem(this.getFileIcon("clearing/prereceive.png"), "经营费用");
//        this.bankCashPanel.add(mBussinessExpenseImageItem);
//        mBussinessExpenseImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
//
//        mBussinessExpenseImageItemList = new CLittleImageItem();
//        this.bankCashPanel.add(mBussinessExpenseImageItemList);
//        mBussinessExpenseImageItemList.setBounds(x + 100, y, 30, 48);
//
//        mCommonExpenseImageItem = new CImageItem(this.getFileIcon("clearing/receiveclearing.png"), "一般费用");
//        this.bankCashPanel.add(mCommonExpenseImageItem);
//        x += 240;
//        mCommonExpenseImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
//
//
//        mCommonExpenseImageItemList = new CLittleImageItem();
//        this.bankCashPanel.add(mCommonExpenseImageItemList);
//        mCommonExpenseImageItemList.setBounds(x + 100, y, 30, 48);
//
//
//        mOtherIncomeImageItem = new CImageItem(this.getFileIcon("clearing/prepay.png"), "其它收入");
//        this.bankCashPanel.add(mOtherIncomeImageItem);
//        x = 150;
//        y += 150;
//        mOtherIncomeImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
//        mOtherIncomeImageItemList = new CLittleImageItem();
//        this.bankCashPanel.add(mOtherIncomeImageItemList);
//        mOtherIncomeImageItemList.setBounds(x + 100, y, 30, 48);
//
//
//
//        mBankCashImageItem = new CImageItem(this.getFileIcon("clearing/payclearing.png"), "现金银行");
//        this.bankCashPanel.add(mBankCashImageItem);
//        x += 240;
//
//        mBankCashImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
//
//        mBankCashImageItemList = new CLittleImageItem();
//        this.bankCashPanel.add(mBankCashImageItemList);
//        mBankCashImageItemList.setBounds(x + 100, y, 30, 48);
//
//
    this.clientPanel.add(bankCashPanel);

        x = 150;
        y = 10;
        this.initPanel = new JPanel();// , image);
        this.initPanel.setSize(750, 400);
        this.initPanel.setLayout(null);
        fStorageBeginManageImageItem = new CImageItem(this.getFileIcon("begin/storage.png"), "库存期初");
        this.initPanel.add(fStorageBeginManageImageItem);
        fStorageBeginManageImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        fStorageBeginManageImageItemList = new CLittleImageItem();
        this.initPanel.add(fStorageBeginManageImageItemList);
        fStorageBeginManageImageItemList.setBounds(x + 100, y, 30, 48);


        bSubjectBeginManageImageItem = new CImageItem(this.getFileIcon("begin/subject.png"), "会计科目期初");
        this.initPanel.add(bSubjectBeginManageImageItem);
        x += 240;
        bSubjectBeginManageImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());


        bSubjectBeginManageImageItemList = new CLittleImageItem();
        this.initPanel.add(bSubjectBeginManageImageItemList);
        bSubjectBeginManageImageItemList.setBounds(x + 100, y, 30, 48);


        bReceivableBeginMangeImageItem = new CImageItem(this.getFileIcon("begin/receive.png"), "应收款期初");
        this.initPanel.add(bReceivableBeginMangeImageItem);
        x = 150;
        y += 150;
        bReceivableBeginMangeImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        bReceivableBeginMangeImageItemList = new CLittleImageItem();
        this.initPanel.add(bReceivableBeginMangeImageItemList);
        bReceivableBeginMangeImageItemList.setBounds(x + 100, y, 30, 48);


        bPaymentBeginManageImageItem = new CImageItem(this.getFileIcon("begin/pay.png"), "应付款期初");
        this.initPanel.add(bPaymentBeginManageImageItem);
        x += 240;

        bPaymentBeginManageImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        bPaymentBeginManageImageItemList = new CLittleImageItem();
        this.initPanel.add(bPaymentBeginManageImageItemList);
        bPaymentBeginManageImageItemList.setBounds(x + 100, y, 30, 48);


        this.clientPanel.add(initPanel);

        //

        x = 60;
        y = 10;

        this.reportPanel = new CPanel();// , image);
        this.reportPanel.setSize(900, 400);
        this.reportPanel.setLayout(null);
        JScrollPane salePane = new JScrollPane();//销售报表
        this.sPanel = new JPanel();
        this.cPanel = new JPanel();
        this.cgPanel = new JPanel();
        this.cwPanel = new JPanel();
        this.zhPanel = new JPanel();
        this.sPanel.setLayout(new GridLayout(8, 1));
//        this.sPanel.setBackground(Color.white);
        this.cPanel.setLayout(new GridLayout(16, 1));
//        this.cPanel.setBackground(Color.white);
        this.cgPanel.setLayout(new GridLayout(11, 1));
//        this.cgPanel.setBackground(Color.white);
        this.cwPanel.setLayout(new GridLayout(8, 1));
//        this.cwPanel.setBackground(Color.white);
        this.zhPanel.setLayout(new GridLayout(8, 1));
//        this.zhPanel.setBackground(Color.white);


//        sPanel.add(rSaleIndentItem);//Item("销售订单查询");
//        sPanel.add(rSaleIndentProStatItem);//Item("销售订单商品统计");
//        sPanel.add(rSaleIndentProductItem);//Item("销售订单商品明细");
//        sPanel.add(rSaleIndentBalanceItem);//Item("销售订单余额查询");////暂时屏蔽
//        sPanel.add(rSaleIndentProBalStatItem);//Item("销售订单商品余额统计");////暂时屏蔽
//        sPanel.add(rSaleIndentProBalDetailItem);//Item("销售订单商品余额明细查询");////暂时屏蔽
//        sPanel.add(rSaleIndentCustomerStatItem);//Item("销售订单客商统计");
//        sPanel.add(rSaleIndentPersonStatItem);//("销售订单业务员统计");
//
////         private CMenu rPurchaseReportMenu = new CMenu("采购报表");
//        cgPanel.add(rPurchaseIndentItem);//("采购订单查询");
//        cgPanel.add(rPurchaseIndentProStatItem);//("采购订单商品统计");
//        cgPanel.add(rPurchaseIndentProductItem);//("采购订单商品明细");
//        cgPanel.add(rPurchaseIndentSupplierStatItem);//("采购订单供应商统计");
//        cgPanel.add(rPurchaseIndentPersonStatItem);//("采购订单业务员统计");
//        cgPanel.add(rProductPurchaseStatItem);//("商品采购统计表");
//        cgPanel.add(rProductPurchaseDetailItem);//("商品采购明细查询");
//        cgPanel.add(rSupplierPurchaseStatItem);//("供应商采购统计表");
//        cgPanel.add(rSupplierPurchaseDetailItem);//("供应商采购明细查询");
//        cgPanel.add(rEmployeePurchaseStatItem);//("职员采购统计表");
//        cgPanel.add(rEmployeePurchaseDetailItem);//("职员采购明细查询");
//
//        cPanel.add(rStorageBalanceItem);//("库存商品余额查询");
//        cPanel.add(rStorageInDetailedItem);//("库存入库明细查询");
//        cPanel.add(rStorageOutDetailedItem);//("库存出库明细查询");
//        cPanel.add(rStorageInItem);//("库存商品入库查询");
//        cPanel.add(rStorageOutItem);//("库存商品出库查询");
//        cPanel.add(rStorageMaxMinItem);//("库存商品上下限查询");
//        cPanel.add(rStorageAlertItem);//("库存警报查询");
//        cPanel.add(rStorageMoveReporItem);//("库存商品移库明细");
//        cPanel.add(rStorageCheckReporItem);//("库存商品盘点明细");
//        cPanel.add(rStorageOutflowReporItem);//("库存商品报溢明细");
//        cPanel.add(rStorageLossReporItem);//("库存商品报损明细");
//        cPanel.add(rStorageChangeMoneyItem);//("库存商品变价明细");
//        cPanel.add(rStorageMoveItem);//("库存商品移库查询");
//        cPanel.add(rStorageCheckItem);//("库存商品盘点查询");
//        cPanel.add(rStorageOutflowItem);//("库存商品报溢查询");
//        cPanel.add(rStorageLossItem);//("库存商品报损查询");
//
//        cwPanel.add(rSubjectBalanceItem);//("科目余额查询");
//        cwPanel.add(rDailyFeeReportItem);//("日常费用报表");
//        cwPanel.add(rDailyFeeDetailItem);//("日常费用明细查询");
//        cwPanel.add(rSupplierPayBalanceItem);//("供应商应付余额查询");
//        cwPanel.add(rSupplierUnbalanceDetailItem);//("供应商未结明细查询");
//        cwPanel.add(rCustomerReceiveBalanceItem);//("客商应收余额查询");
//        cwPanel.add(rCustomerUnbalanceDetailItem);//("客商未结明细查询");



        this.managerPanel = new JPanel();// , image);
        this.managerPanel.setSize(750, 400);
        this.managerPanel.setLayout(null);
        ImageIcon i = this.getFileIcon("search1.png");
        rManagerSubjectBalanceImageItem = new CImageItem(i, "科目余额查询");
        this.managerPanel.add(rManagerSubjectBalanceImageItem);
        x = 30;
        y = 10;
        rManagerSubjectBalanceImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        rManagerSupplierPayBalanceImageItem = new CImageItem(i, "供应商应付查询");
        this.managerPanel.add(rManagerSupplierPayBalanceImageItem);
        x += 180;
        rManagerSupplierPayBalanceImageItem.setBounds(x, y, supplierImageItem.getWidth(), supplierImageItem.getHeight());

        rManagerCustomerReceiveBalanceImageItem  = new CImageItem(i, "客商应收查询");
        this.managerPanel.add(rManagerCustomerReceiveBalanceImageItem );
        x += 180;
        rManagerCustomerReceiveBalanceImageItem .setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        rManagerStorageBalanceImageItem = new CImageItem(i, "库存余额查询");
        this.managerPanel.add(rManagerStorageBalanceImageItem);

        x += 180;

        rManagerStorageBalanceImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        rManagerStorageChangeImageItem = new CImageItem(i, "库存变化明细查询");
        this.managerPanel.add(rManagerStorageChangeImageItem);
        x = 30;
        y += 150;
        rManagerStorageChangeImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        rManagerDailyQueryImageItem = new CImageItem(i, "日报表查询");
        this.managerPanel.add(rManagerDailyQueryImageItem);
        x += 180;
        rManagerDailyQueryImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());

        rManagerMonthQueryImageItem = new CImageItem(i, "月报表查询");
        this.managerPanel.add(rManagerMonthQueryImageItem);
        x += 180;
        rManagerMonthQueryImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        rManagerYearQueryImageItem = new CImageItem(i, "年报表查询");
        this.managerPanel.add(rManagerYearQueryImageItem);
        x += 180;
        rManagerYearQueryImageItem.setBounds(x, y, productImageItem.getWidth(), productImageItem.getHeight());
        this.clientPanel.add(managerPanel);

////        salePane.setViewportView(sPanel);
//        sPane.setViewportView(cgPanel);
//        cPane.setViewportView(cPanel);
//        wPane.setViewportView(cwPanel);
//        zPane.setViewportView(zhPanel);
        this.createBaseInfo();

    }

    private void createBaseInfo() {
        this.bPanel = new CGroupPane("相关资料");
        this.bPanel.setSize(750, 80);
        this.clientPanel.add(this.bPanel);
        this.bPanel.setVisible(false);
        this.bPanel.setLayout(null);
        this.bPanel.add(productBaseItem);
        this.bPanel.add(storageSearchBaseItem);
        this.bPanel.add(storageMinMaxBaseItem);
        this.bPanel.add(payableBaseItem);
        this.bPanel.add(receivableBaseItem);
        //this.bPanel.add(employeeSaleBaseItem);
        //this.bPanel.add(priceBaseItem);
        this.bPanel.add(subjectRemainBaseItem);
        this.bPanel.add(customerBaseItem);
        this.bPanel.add(storageBaseItem);
        this.bPanel.add(subjectBaseItem);

    //productBaseItem.setBounds(10, 30, productBaseItem.getWidth(), productBaseItem.getHeight());
    }

    private void showInitPane() {

        hideAllPane();
        this.initPanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.initPanel.setBounds((rect.width - this.initPanel.getWidth()) / 2, (rect.height - this.initPanel.getHeight()) / 2, this.initPanel.getWidth(), this.basePanel.getHeight());
        this.helpPane.setHelpInfo("系统期初指在启用本系统管理进、销、存之初，将公司的库存商品、应收应付款的账面结存数录入本系统中作为当前的期初数。");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        //base info
        this.hideAllBaseButtons();
        this.bPanel.setVisible(true);
        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());
        storageSearchBaseItem.setVisible(true);
        storageMinMaxBaseItem.setVisible(true);
        payableBaseItem.setVisible(true);
        receivableBaseItem.setVisible(true);
        //employeeSaleBaseItem.setVisible(true);
        //priceBaseItem.setVisible(true);
        int x = 10;
        int y = 30;
        storageSearchBaseItem.setLocation(x, y);
        x += storageSearchBaseItem.getWidth() + 10;
        storageMinMaxBaseItem.setLocation(x, y);
        x += storageMinMaxBaseItem.getWidth() + 10;
        payableBaseItem.setLocation(x, y);
        x += payableBaseItem.getWidth() + 10;
        receivableBaseItem.setLocation(x, y);
        x += receivableBaseItem.getWidth() + 10;
        //employeeSaleBaseItem.setLocation(x, y);
        //x += employeeSaleBaseItem.getWidth() + 10;
        //priceBaseItem.setLocation(x, y);
    }

    private void showBasePane() {
        hideAllPane();
        this.basePanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.basePanel.setBounds((rect.width - this.basePanel.getWidth()) / 2, (rect.height - this.basePanel.getHeight()) / 2, this.basePanel.getWidth(), this.basePanel.getHeight());
        this.helpPane.setHelpInfo("基础信息是所有操作的基本信息来源，贯穿于整个系统的业务中。基础信息的准确性直接影响到整个系统数据的有效性和准确性！");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        //base info
        this.hideAllBaseButtons();
        this.bPanel.setVisible(true);
        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());
        storageSearchBaseItem.setVisible(true);
        storageMinMaxBaseItem.setVisible(true);
        payableBaseItem.setVisible(true);
        receivableBaseItem.setVisible(true);
        //employeeSaleBaseItem.setVisible(true);
        //priceBaseItem.setVisible(true);
        int x = 10;
        int y = 30;
        storageSearchBaseItem.setLocation(x, y);
        x += storageSearchBaseItem.getWidth() + 10;
        storageMinMaxBaseItem.setLocation(x, y);
        x += storageMinMaxBaseItem.getWidth() + 10;
        payableBaseItem.setLocation(x, y);
        x += payableBaseItem.getWidth() + 10;
        receivableBaseItem.setLocation(x, y);
        x += receivableBaseItem.getWidth() + 10;
        //employeeSaleBaseItem.setLocation(x, y);
        //x += employeeSaleBaseItem.getWidth() + 10;
        //priceBaseItem.setLocation(x, y);

    //productBaseItem.setBounds(10, 30, productBaseItem.getWidth(), productBaseItem.getHeight());

    }

    private void showWelcomePane() {
        hideAllPane();
        welcomePanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.welcomePanel.setBounds((rect.width - this.welcomePanel.getWidth()) / 2, (rect.height - this.welcomePanel.getHeight()) / 2, this.welcomePanel.getWidth(), this.welcomePanel.getHeight());
        this.hideAllBaseButtons();
        this.bPanel.setVisible(false);
        this.helpPane.setVisible(false);
        

    }

//    public void checkReg()
//    {
//        try
//        {
//        ICompanyService service = Main.getServiceManager().getCompanyService();
//        TestPo po = service.getTestPo();
//        String timeString = po.getExt2();
//        String ext = po.getExt();
//        if (ext != null && !ext.trim().equals(""))
//        {
//            return;
//        }
//        if (timeString != null && !timeString.trim().equals(""))
//        {
//            int times = Integer.parseInt(timeString);
//            po.setExt2(String.valueOf(times + 1));
//            service.saveTestPo(po);
//            if (times > 933)
//            {
//                this.showAgain();
//            }
//        }
//        else
//        {
//            po.setExt2("1");
//            service.saveTestPo(po);
//        }
//        }
//        catch(UncategorizedSQLException ex)
//        {
//            MessageBox.showErrorDialog(this, "软件已经启动，不能同时开启多个一样的系统！");
//            System.exit(0);
//        }
//
//    }

    private void showPurchasePane() {
        hideAllPane();
        this.purchasePanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.purchasePanel.setBounds((rect.width - this.purchasePanel.getWidth()) / 2, (rect.height - this.purchasePanel.getHeight()) / 2, this.purchasePanel.getWidth(), this.purchasePanel.getHeight());
        this.bPanel.setVisible(true);
        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());
        this.helpPane.setHelpInfo("采购单过帐不影响库存和应收应付，采购单过帐将直接引起库存和应付款的改变。");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        this.hideAllBaseButtons();

        this.bPanel.setVisible(true);
        productBaseItem.setVisible(true);
        customerBaseItem.setVisible(true);
        storageBaseItem.setVisible(true);
        storageSearchBaseItem.setVisible(true);
        payableBaseItem.setVisible(true);
        int x = 10;
        int y = 30;
        productBaseItem.setLocation(x, y);
        x += productBaseItem.getWidth() + 10;
        customerBaseItem.setLocation(x, y);
        x += customerBaseItem.getWidth() + 10;
        storageBaseItem.setLocation(x, y);
        x += storageBaseItem.getWidth() + 10;
        storageSearchBaseItem.setLocation(x, y);
        x += storageSearchBaseItem.getWidth() + 10;
        payableBaseItem.setLocation(x, y);

    }

    private void showSalePane() {
        hideAllPane();
        this.salePanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.salePanel.setBounds((rect.width - this.purchasePanel.getWidth()) / 2, (rect.height - this.purchasePanel.getHeight()) / 2, this.purchasePanel.getWidth(), this.purchasePanel.getHeight());
        this.helpPane.setHelpInfo("销售订单的过帐不影响库存和应收应付，销售单过帐将直接引起库存和应收款的改变。");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        this.hideAllBaseButtons();
        this.bPanel.setVisible(true);

        productBaseItem.setVisible(true);
        customerBaseItem.setVisible(true);
        storageBaseItem.setVisible(true);
        storageSearchBaseItem.setVisible(true);
        receivableBaseItem.setVisible(true);
        //priceBaseItem.setVisible(true);
        int x = 10;
        int y = 30;
        productBaseItem.setLocation(x, y);
        x += productBaseItem.getWidth() + 10;
        customerBaseItem.setLocation(x, y);
        x += customerBaseItem.getWidth() + 10;
        storageBaseItem.setLocation(x, y);
        x += storageBaseItem.getWidth() + 10;
        storageSearchBaseItem.setLocation(x, y);
        x += storageSearchBaseItem.getWidth() + 10;
        receivableBaseItem.setLocation(x, y);
        x += receivableBaseItem.getWidth() + 10;
        //priceBaseItem.setLocation(x, y);
        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());



    }

    private void showStoragePane() {
        hideAllPane();
        this.storagePanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.storagePanel.setBounds((rect.width - this.purchasePanel.getWidth()) / 2, (rect.height - this.purchasePanel.getHeight()) / 2, this.purchasePanel.getWidth(), this.purchasePanel.getHeight());
        this.helpPane.setHelpInfo("库存管理主要包括商品的仓库之间的商品调拨、库存商品的成本调价、库存商品的报损、报溢、赠送、获赠等多种业务的处理。");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        this.hideAllBaseButtons();
        this.bPanel.setVisible(true);
        productBaseItem.setVisible(true);
        storageBaseItem.setVisible(true);
        storageSearchBaseItem.setVisible(true);

        int x = 10;
        int y = 30;
        productBaseItem.setLocation(x, y);
        x += productBaseItem.getWidth() + 10;
        storageBaseItem.setLocation(x, y);
        x += storageBaseItem.getWidth() + 10;
        storageSearchBaseItem.setLocation(x, y);
        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());


    }

    private void showClearingPane() {
        hideAllPane();
        this.clearingPanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.clearingPanel.setBounds((rect.width - this.purchasePanel.getWidth()) / 2, (rect.height - this.purchasePanel.getHeight()) / 2, this.purchasePanel.getWidth(), this.purchasePanel.getHeight());
        this.helpPane.setHelpInfo("钱流管理是专门记录公司帐面发生改变的模块，包括预付，预收，收款付款结算，日常费用开支的记录。");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        this.hideAllBaseButtons();
        this.bPanel.setVisible(true);

        this.customerBaseItem.setVisible(true);
        this.payableBaseItem.setVisible(true);
        this.receivableBaseItem.setVisible(true);


        int x = 10;
        int y = 30;
        customerBaseItem.setLocation(x, y);
        x += customerBaseItem.getWidth() + 10;

        payableBaseItem.setLocation(x, y);
        x += payableBaseItem.getWidth() + 10;
        receivableBaseItem.setLocation(x, y);

        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());


    }

    private void showBankCashPane() {
        hideAllPane();
        bankCashPanel.setVisible(true);

        Rectangle rect = this.clientPanel.getBounds();
        this.bankCashPanel.setBounds((rect.width - this.purchasePanel.getWidth()) / 2, (rect.height - this.purchasePanel.getHeight()) / 2, this.purchasePanel.getWidth(), this.purchasePanel.getHeight());
        this.helpPane.setHelpInfo("   资金管理...");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        this.hideAllBaseButtons();
        this.bPanel.setVisible(true);

        this.customerBaseItem.setVisible(true);
        this.payableBaseItem.setVisible(true);
        this.receivableBaseItem.setVisible(true);


        int x = 10;
        int y = 30;
        customerBaseItem.setLocation(x, y);
        x += customerBaseItem.getWidth() + 10;

        payableBaseItem.setLocation(x, y);
        x += payableBaseItem.getWidth() + 10;
        receivableBaseItem.setLocation(x, y);

        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());


    }

    private void showManagerPane() {
        hideAllPane();
        this.managerPanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.managerPanel.setBounds((rect.width - this.managerPanel.getWidth()) / 2, (rect.height - this.basePanel.getHeight()) / 2, this.managerPanel.getWidth(), this.managerPanel.getHeight());
        this.helpPane.setHelpInfo("老板查询是一种快捷的掌握公司运行情况的报表汇总。其它的报表系统，请通过菜单操作，位置:\"报表->");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        //base info
        this.hideAllBaseButtons();
        this.bPanel.setVisible(true);
        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());
        productBaseItem.setVisible(true);
        customerBaseItem.setVisible(true);
        //employeeSaleBaseItem.setVisible(true);
        
        int x = 10;
        int y = 30;
        productBaseItem.setLocation(x, y);
        x += productBaseItem.getWidth() + 10;
        customerBaseItem.setLocation(x, y);
        x += customerBaseItem.getWidth() + 10;
        //employeeSaleBaseItem.setLocation(x, y);
        //x += employeeSaleBaseItem.getWidth() + 10;
        
    }

    private void showReportPane() {
        hideAllPane();
        reportPanel.setVisible(true);
        Rectangle rect = this.clientPanel.getBounds();
        this.salePanel.setBounds((rect.width - this.purchasePanel.getWidth()) / 2, (rect.height - this.purchasePanel.getHeight()) / 2, this.purchasePanel.getWidth(), this.purchasePanel.getHeight());
        this.helpPane.setHelpInfo("   报表中心");
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        this.helpPane.setVisible(true);
        this.hideAllBaseButtons();
        this.bPanel.setVisible(true);
        productBaseItem.setVisible(true);
        customerBaseItem.setVisible(true);
        storageBaseItem.setVisible(true);
        storageSearchBaseItem.setVisible(true);
        receivableBaseItem.setVisible(true);
        //priceBaseItem.setVisible(true);
        int x = 10;
        int y = 30;
        productBaseItem.setLocation(x, y);
        x += productBaseItem.getWidth() + 10;
        customerBaseItem.setLocation(x, y);
        x += customerBaseItem.getWidth() + 10;
        storageBaseItem.setLocation(x, y);
        x += storageBaseItem.getWidth() + 10;
        storageSearchBaseItem.setLocation(x, y);
        x += storageSearchBaseItem.getWidth() + 10;
        receivableBaseItem.setLocation(x, y);
        x += receivableBaseItem.getWidth() + 10;
        //priceBaseItem.setLocation(x, y);
        this.bPanel.setBounds((rect.width - bPanel.getWidth()) / 2, rect.height - 150, bPanel.getWidth(), bPanel.getHeight());

    }

    private void hideAllBaseButtons() {
        productBaseItem.setVisible(false);
        storageSearchBaseItem.setVisible(false);
        storageMinMaxBaseItem.setVisible(false);
        payableBaseItem.setVisible(false);
        receivableBaseItem.setVisible(false);
        //employeeSaleBaseItem.setVisible(false);
        //priceBaseItem.setVisible(false);

        subjectRemainBaseItem.setVisible(false);
        customerBaseItem.setVisible(false);
        storageBaseItem.setVisible(false);
        subjectBaseItem.setVisible(false);
    }

    private void hideAllPane() {
        this.basePanel.setVisible(false);
        salePanel.setVisible(false);
        storagePanel.setVisible(false);
        reportPanel.setVisible(false);
        this.clearingPanel.setVisible(false);
        bankCashPanel.setVisible(false);
        initPanel.setVisible(false);
        welcomePanel.setVisible(false);
        this.managerPanel.setVisible(false);

//        this.basePanel.setVisible(false);
//        this.basePanel.setVisible(false);
//        this.basePanel.setVisible(false);
//        this.basePanel.setVisible(false);
//        this.basePanel.setVisible(false);
//        this.basePanel.setVisible(false);
//        this.basePanel.setVisible(false);
//        this.basePanel.setVisible(false);
        this.purchasePanel.setVisible(false);

    }

    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        //if (source == this.)
        if (source == this.purchaseButton) {
            this.showPurchasePane();
        } else if (source == this.saleButton) {
            this.showSalePane();
        } else if (source == this.storageButton) {
            this.showStoragePane();
        } else if (source == this.clearingButton) {
            this.showClearingPane();
        } else if (source == this.bankCashButton) {
            this.showBankCashPane();
        } else if (source == this.managerButton) {
            this.showManagerPane();
        } else if (source == this.reportButton) {
            this.showReportPane();
        } else if (source == this.baseButton) {
            this.showBasePane();
        } else if (source == this.initButton) {
            this.showInitPane();
        } else if (source == this.welcomePage) {
            this.showWelcomePane();
        } else if (source == productImageItem) {
            source = bProductManageItem;
        } else if (source == subjectImageItem) {
            source = bSubjectItem;
        } else if (source == customerImageItem) {
            source = bCustomerManageItem;
        }
        else if (source == supplierImageItem)
        {
            source = bSupplierManageItem;
        }
        else if (source == employeeImageItem)//|| source ==employeeInfoButton)
        {
            source = bEmployeeManageItem;
        } else if (source == storageImageItem) {
            source = bWarehouseItem;
        } else if (source == wStorageInImageItem) {
            source = wStorageInItem;
        } else if (source == wStorageOutImageItem) {
            source = wStorageOutItem;
        } else if (source == wStorageOutflowImageItem) {
            source = wStorageOutflowItem;
        } else if (source == wStorageLossImageItem) {
            source = wStorageLossItem;
        } else if (source == wStorageMoveImageItem) {
            source = wStorageMoveItem;
        } else if (source == wStorageCheckImageItem) {
            source = wStorageCheckItem;
        } else if (source == wStorageChangeMoneyImageItem) {
            source = wStorageChangeMoneyItem;
        } else if (source == wStorageMaxMinImageItem) {
            source = wStorageMaxMinItem;
            } else if (source == fStorageBeginManageImageItem) {
            source = fStorageBeginManageItem;
        } else if (source == bSubjectBeginManageImageItem) {
            source = bSubjectBeginManageItem;
        } else if (source == bReceivableBeginMangeImageItem) {
            source = bReceivableBeginMangeItem;
        } else if (source == bPaymentBeginManageImageItem) {
            source = bPaymentBeginManageItem;
        } else if (source == pOrderImageItem) {
            source = pOrderItem;
        } else if (source == pPurchaseImageItem) {
            source = pPurchaseItem;
        } else if (source == pPaymentImageItem) {
            source = pPaymentItem;
        } else if (source == mPayableClearingImageItem) {
            source = mPayableClearingItem;
        } else if (source == pOrderChangedImageItemList) {
            source = pOrderChangedImageItemList;
        } else if (source == pOrderChangedImageItem) {
            source = pOrderChangedItem;
        } else if (source == pOrderBackImageItem) {
            source = pOrderBack;
        } else if (source == sQuoteImageItem) {
            source = sQuoteItem;
        } else if (source == sOrderImageItem) {
            source = sOrderItem;
        } else if (source == sSaleListImageItem) {
            source = sSaleListItem;
        } else if (source == sGartheringImageItem) {
            source = sGartheringItem;
        } else if (source == sAjustPriceImageItem) {
            source = sAjustPriceItem;
        } else if (source == sOrderChangedImageItem) {
            source = sOrderChangedItem;
        } else if (source == sOrderChangedImageItemList) {
            source = sOrderChangedImageItemList;
        } else if (source == sOrderBackImageItem) {
            source = sOrderBackItem;
        } else if (source == sUserImageItem) {
            source = sUserItem;

        } else if (source == sCompanyInfoImageItem) {
            source = sCompanyInfoItem;
        } else if (source == sLogImageItem) {
            source = sLogItem;
        } else if (source == mPreReceivableImageItem) {
            source = mPreReceivableItem;
        } else if (source == mReceivableClearingImageItem) {
            source = mReceivableClearingItem;
        } else if (source == mPrePayableImageItem) {
            source = mPrePayableItem;
        } else if (source == mPayableClearingImageItem) {
            source = mPayableClearingItem;
        } else if (source == mBussinessExpenseImageItem) {
            source = mBussinessExpenseItem;
        } else if (source == mCommonExpenseImageItem) {
            source = mCommonExpenseItem;
        } else if (source == mOtherIncomeImageItem) {
            source = mOtherIncomeItem;
        } else if (source == mBankCashImageItem) {
            source = mBankCashItem;
        } else if (source == rPurchaseIndentImageItem) {
            source = rPurchaseIndentItem;
        } else if (source == rPurchaseIndentProStatImageItem) {
            source = rPurchaseIndentProStatItem;
        } else if (source == rPurchaseIndentProductImageItem) {
            source = rPurchaseIndentProductItem;
        } else if (source == rPurchaseIndentBalanceImageItem) {
            source = rPurchaseIndentBalanceItem;
        } else if (source == rPurchaseIndentProBalStatImageItem) {
            source = rPurchaseIndentProBalStatItem;
        } else if (source == rPurchaseIndentProBalDetailImageItem) {
            source = rPurchaseIndentProBalDetailItem;
        } else if (source == rPurchaseIndentSupplierStatImageItem) {
            source = rPurchaseIndentSupplierStatItem;
        } else if (source == rPurchaseIndentPersonStatImageItem) {
            source = rPurchaseIndentPersonStatItem;
        } else if (source == rProductPurchaseStatImageItem) {
            source = rProductPurchaseStatItem;
        } else if (source == rProductPurchaseDetailImageItem) {
            source = rProductPurchaseDetailItem;
        } else if (source == rSupplierPurchaseStatImageItem) {
            source = rSupplierPurchaseStatItem;
        } else if (source == rSupplierPurchaseDetailImageItem) {
            source = rSupplierPurchaseDetailItem;
        } else if (source == rEmployeePurchaseStatImageItem) {
            source = rEmployeePurchaseStatItem;
        } else if (source == rEmployeePurchaseDetailImageItem) {
            source = rEmployeePurchaseDetailItem;
        } else if (source == rProductPurchaseGatherImageItem) {
            source = rProductPurchaseGatherItem;
        } else if (source == rProductPurchasePriceWaveImageItem) {
            source = rProductPurchasePriceWaveItem;
        } else if (source == rSaleIndentImageItem) {
            source = rSaleIndentItem;
        } else if (source == rSaleIndentProStatImageItem) {
            source = rSaleIndentProStatItem;
        } else if (source == rSaleIndentProductImageItem) {
            source = rSaleIndentProductItem;
        } else if (source == rSaleIndentBalanceImageItem) {
            source = rSaleIndentBalanceItem;
        } else if (source == rSaleIndentProBalStatImageItem) {
            source = rSaleIndentProBalStatItem;
        } else if (source == rSaleIndentProBalDetailImageItem) {
            source = rSaleIndentProBalDetailItem;
        } else if (source == rSaleIndentCustomerStatImageItem) {
            source = rSaleIndentCustomerStatItem;
        } else if (source == rSaleIndentPersonStatImageItem) {
            source = rSaleIndentPersonStatItem;
        } //            else if(source == rCustomerMonthSaleCompareImageItem)
        //            {
        //                source = rCustomerMonthSaleCompareItem;
        //            }
        //            else if(source == rProductMonthSaleCompareImageItem)
        //            {
        //                source = rProductMonthSaleCompareItem;
        //            }
        //            else if(source == rCustomerSaleOrderImageItem)
        //            {
        //                source = rCustomerSaleOrderItem;
        //            }
        //            else if(source == rProductSaleOrderImageItem)
        //            {
        //                source = rProductSaleOrderItem;
        //            }
        //            else if(source == rEmployeeSaleOrderImageItem)
        //            {
        //                source = rEmployeeSaleOrderItem;
        //            }
        else if (source == rProductSaleStatImageItem) {
            source = rProductSaleStatItem;
        } else if (source == rProductSaleDetailImageItem) {
            source = rProductSaleDetailItem;
        } else if (source == rSupplierSaleStatImageItem) {
            source = rCustomerSaleStatItem;
        } else if (source == rSupplierSaleDetailImageItem) {
            source = rCustomerSaleDetailItem;
        } else if (source == rEmployeeSaleStatImageItem) {
            source = rEmployeeSaleStatItem;
        } else if (source == rEmployeeSaleDetailImageItem) {
            source = rEmployeeSaleDetailItem;
        } //            else if(source == rProductSaleGatherImageItem)
        //            {
        //                source = rProductSaleGatherItem;
        //            }
        //            else if(source == rProductPriceImageItem)
        //            {
        //                source = rProductPriceItem;
        //            }
        //            else if(source == rProductSalePriceWaveImageItem)
        //            {
        //                source = rProductSalePriceWaveItem;
        //            }
        else if (source == rSubjectBalanceImageItem) {
            source = rSubjectBalanceItem;
        } else if (source == rCashBankMonthReportImageItem) {
            source = rCashBankMonthReportItem;
        } else if (source == rCashBankDetailImageItem) {
            source = rCashBankDetailItem;
        } else if (source == rDailyFeeReportImageItem) {
            source = rDailyFeeReportItem;
        } else if (source == rDailyFeeDetailImageItem) {
            source = rDailyFeeDetailItem;
        } else if (source == rOtherIncomeMonthReportImageItem) {
            source = rOtherIncomeMonthReportItem;
        } else if (source == rOtherIncomeDetailImageItem) {
            source = rOtherIncomeDetailItem;
        } else if (source == rSupplierPayBalanceImageItem) {
            source = rSupplierPayBalanceItem;
        } else if (source == rSupplierUnbalanceDetailImageItem) {
            source = rSupplierUnbalanceDetailItem;
        } else if (source == rCustomerReceiveBalanceImageItem) {
            source = rCustomerReceiveBalanceItem;
        } else if (source == rCustomerUnbalanceDetailImageItem) {
            source = rCustomerUnbalanceDetailItem;
        } else if (source == rSupplierQueryImageItem) {
            source = rSupplierQueryItem;
        } else if (source == rCustomerQueryImageItem) {
            source = rCustomerQueryItem;
        }
        acted(source);

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        acted(source);
    }

   

    private void acted(Object source) {
             if (source == bProductManageItem) {
                
                    showProductManageWindow();
              
            } else if (source == bSubjectItem) {
                
                    showSubjectManageWindow();
                
            } else if (source == bCustomerManageItem) {
                    showCustomerManageWindow();
                
            }
            else if (source == bSupplierManageItem)
            {
                    showSupplierManageWindow();
                
            }
            else if (source == refreshItem) {
                refreshDataBase();
            } else if (source == exitItem) {
                //doClose(e);
                close();
            } else if (source == bEmployeeManageItem)//|| source ==employeeInfoButton)
            {
                    showEmployeeWindow();
                
            } else if (source == bWarehouseItem) {
                    showStorageManageWindow();
                
            } else if (source == bDatabaseItem) {
                showDatabaseTool();
            } else if (source == wStorageInItem) {
                    addStorageInForm();
                
            } else if (source == wStorageInImageItemList) {
                    //showStorageInWindow();
                    showStorageInWindow();
                
            } else if (source == wStorageOutItem) {
                
                    addStorageOutForm();
                
            } else if (source == wStorageOutImageItemList) {
                    showStorageOutWindow();
                
            } else if (source == wStorageOutflowItem) {
                
                    addOutflowForm();
                
            } else if (source == wStorageOutflowImageItemList) {
                    showStorageOutflowWindow();
                
            } else if (source == wStorageLossItem) {
                
                    addStorageLossForm();
                
            } else if (source == wStorageLossImageItemList) {
                
                    showStorageLossWindow();
                
            } else if (source == wStorageMoveItem) {
                
                    addStorageMoveForm();
                
            } else if (source == wStorageMoveImageItemList) {
                                    showStorageMoveWindow();
                
            } else if (source == wStorageCheckItem) {
                
                    addStorageCheckForm();
                
            } else if (source == wStorageCheckImageItemList) {
                    showStorageCheckWindow();
                
            } else if (source == wStorageChangeMoneyItem) {
                
                    addStorageChangeMoneyForm();
                
            } else if (source == wStorageChangeMoneyImageItemList) {
               
                    showStorageChangeMoneyWindow();
                
            } else if (source == wStorageMaxMinImageItemList) {
               
                    showStorageMaxMinWindow();

               
            } else if (source == wStorageMaxMinItem) {
                
                    addStorageMaxMinForm();

                
            } else if (source == sQuoteImageItemList) {

                
                    showSaleQuoteWindow();
                
            } else if (source == sQuoteItem) {

                
                    addSaleQuoteForm();
                
            } else if (source == sOrderImageItemList) {

                
                    showSaleOrderWindow();
               
            } else if (source == sOrderItem) {

                
                    addSaleOrderForm();
                
            } else if (source == sSaleListImageItemList) {
                
                    showSaleDetailWindow();
                
            } else if (source == sSaleListItem) {
                
                    addSaleDetailForm();
               
            } else if (source == sGartheringItem) {

               
                    addReceivableClearingForm();
                
            } else if (source == sGartheringImageItemList) {

                
                    showReceivableClearingManageWindow();
                
            } else if (source == sAjustPriceItem) {
               
                    addSaleChangeMoneyForm();
                
            } else if (source == sOrderChangedItem) {
                
                    addSaleOrderChangedForm();
               
            } else if (source == sOrderChangedImageItemList) {
                
                    showSaleOrderChangeWindow();

                
            } else if (source == sAjustPriceImageItemList) {
                
                    showSaleChangeMoneyWindow();
                
            } else if (source == sOrderBackItem) {
                
                    addSaleOrderBackForm();
                
            } else if (source == sOrderBackImageItemList) {
                
                    showSaleOrderBackWindow();
                
            } else if (source == pOrderImageItemList) {
                
                    showPurchaseOrderWindow();
                
            } else if (source == pOrderItem) {
                
                    addPurchaseOrderForm();
                
            } else if (source == pPurchaseItem) {
                
                    addPurchaseDetailForm();
                
            } else if (source == pPurchaseImageItemList) {
               
                    showPurchaseDetailWindow();
                
            }
            else if (source == hContentItem)
            {
                showHelpManual();
            }
            else if (source == pPaymentItem) {
                
                    addPurchasePaymentForm();
                
            } else if (source == pPaymentImageItemList) {
                
                    showPurchasePaymentWindow();
                
            } else if (source == pOrderChangedItem) {
                addOrderChangedForm();
            } else if (source == pOrderChangedImageItemList) {
                showPurchaseChangeOrderWindow();
            } else if (source == pOrderBack) {
                
                    addPurchaseOrderBackForm();
                
            } else if (source == pOrderBackImageItemList) {
                
                    showPurchaseOrderBackWindow();
                
            } else if (source == mPreReceivableImageItemList) {
                
                    showPrereceivableManageWindow();
                
            } else if (source == mPreReceivableItem) {
                
                    addPrereceivableForm();
                
            } else if (source == mReceivableClearingItem) {
                
                    addReceivableClearingForm();
                
            } else if (source == mReceivableClearingImageItemList) {
                
                    showReceivableClearingManageWindow();
               
            } else if (source == mPrePayableItem) {

                
                    addPrepayableForm();
                
            } else if (source == mPrePayableImageItemList) {

                
                    showPrepayableManageWindow();
                
            } else if (source == mPayableClearingImageItemList) {

                
                    showPayableClearingManageWindow();
                
            } else if (source == mPayableClearingItem) {
                
                    addPayableClearingForm();
                
            } else if (source == mBussinessExpenseImageItemList) {
                
                    showBussinessExpenseManageWindow();
                
            } else if (source == mBussinessExpenseItem) {
                
                    addBussinessExpenseForm();
               
            } else if (source == mCommonExpenseImageItemList) {
               
                    showCommonExpenseManageWindow();
               
            } else if (source == mCommonExpenseItem) {
                
                    addCommonExpenseForm();
                
            } else if (source == mOtherIncomeImageItemList) {
                
                    showOtherIncomeManageWindow();
                
            } else if (source == mOtherIncomeItem) {
              
                    addOtherIncomeForm();
                
            } else if (source == mBankCashImageItemList) {
                
                    showBankCashManageWindow();
                
            } else if (source == mBankCashItem) {
               
                    addBankCashForm();
                
            } //            else if (source == storageAlertButton) {
            //                showStorageAlertQueryDialog();
            //            }
            else if (source == fStorageBeginManageImageItemList) {

               
                    showBeginStorageManageWindow();
               
            } else if (source == fStorageBeginManageItem) {

                
                    addBeginStorageForm();
                
            } else if (source == bSubjectBeginManageImageItemList) {

                
                    showBeginSubjectManageWindow();
              
            } else if (source == bSubjectBeginManageItem) {

                
                    addBeginSubjectForm();
                
            } else if (source == bReceivableBeginMangeImageItemList) {
                
                    showBeginReceiveableManageWindow();
                
            } else if (source == bReceivableBeginMangeItem) {
                
                    addBeginReceiveableForm();
                
            } else if (source == bPaymentBeginManageImageItemList) {
                
                    showBeginPaymentManageWindow();
                
            } else if (source == bPaymentBeginManageItem) {
                
                    addBeginPaymentForm();
                
            } else if (source == this.refreshBaseInfo) {
                refreshDataBase();
            } else if (source == hAboutItem) {
                showAboutDialog();
            } else if (source == sUserItem) {
                
                    showUserManageWindow();
                
            } else if (source == sChangePasswordItem || source == changePassword) {
               
                    showChangePasswordDialog();
                
            }
            else if (source == website)
            {
                this.visitWebsite();
            }
            else if (source == sCompanyInfoItem) {
                
                    showCompanyInfoDialog();
                
            } /*else if(source == sRoleItem)
            {
            showRoleManageDialog();
            }*/ else if (source == sLogItem) {
                
                    showCSystemLogWindow();
                
            } else if (source == sOptionItem) {
                
                    showSystemOptionDialog();
                
            } else if (source == sNumberManageItem || source == this.sNumberImageItem)//liufei
            {
                
                    showNumberManageDialog();
                
            } else if (source == sListProUnitItem)//liufei
            {
                
                    showCProUnitListDialog();
                
            } else if (source == sListProShelfItem) {
                
                    showCProShelfListDialog();
                
            } else if (source == sListProTaxrateItem) {
                
                    showCProTaxrateListDialog();
                
            } else if (source == sListCusTypeItem) {
                
                    showCCusTypeListDialog();
                
            } else if (source == sListCusPriceItem) {
                
                    showCCusPriceListDialog();
                
            } else if (source == sListCusInvoiceItem) {
                
                    showCCusInvoiceListDialog();
                
            } else if (source == sListEmpDutyItem) {
                
                    showCEmpDutyListDialog();
               
            } else if (source == sListEmpDegreeItem) {
                
                    showCEmpDegreeListDialog();
               
            } else if (source == sListEmpLevelItem) {
               
                    showCEmpLevelListDialog();
                
            } else if (source == sListEmpHealthItem) {
                
                    showCEmpHealthListDialog();
                
            } else if (source == sListStoTypeItem) {
                
                    showCStoTypeListDialog();
                
            } else if (source == rStorageBalanceItem || source == rManagerStorageBalanceItem  || source == rManagerStorageBalanceImageItem) {
                
                    showStorageQueryWindow();
                
            }
            else if (source == rStorageChangeItem || source == rManagerStorageChangeItem || source == rManagerStorageChangeImageItem )
            {
                showStorageDetailQueryWindow();
            }
            else if (source == rStorageChangeMonthItem)
            {
                showStorageDetailMonthQueryWindow();
            }
            else if (source == rStorageInDetailedItem) {
                
                    showStorageInListQueryWindow();
               
            } else if (source == rStorageOutDetailedItem) {
               
                    showStorageOutListQueryWindow();
                
            } else if (source == rStorageInItem) {
               
                    showStorageInReportWindow();
                
            } else if (source == rStorageOutItem) {
                
                    showStorageOutReportWindow();
                
            } else if (source == rStorageMaxMinItem) {
                
                    showStorageMinMaxQueryWindow();
               
            } else if (source == rStorageAlertItem || source == storageMinMaxBaseItem) {
                showStorageAlertQueryDialog();
            } else if (source == rStorageMoveReporItem) {
                
                    showStorageMoveReportWindow();
              

            } else if (source == rStorageCheckReporItem) {
                
                    showStorageCheckReportWindow();
                

            } else if (source == rStorageOutflowReporItem) {
               
                    showStorageOutflowReportWindow();
                

            } else if (source == rStorageLossReporItem) {
                
                    showStorageLossReportWindow();
                

            } else if (source == rStorageChangeMoneyItem) {
                
                    showStorageChangeMoneyReportWindow();
                

            } else if (source == rStorageMoveItem) {
                
                    showStorageMoveQueryWindow();
                

            } else if (source == rStorageCheckItem) {
                
                    showStorageCheckQueryWindow();
                

            } else if (source == rStorageOutflowItem) {
                
                    showStorageOutflowQueryWindow();
                

            } else if (source == rStorageLossItem) {
                
                    showStorageLossQueryWindow();
                

            } else if (source == rPurchaseIndentItem) {
                //1111111111111111111111111111111111111111111111111111111111111111111111
               
                    showPurchaseIndentQueryWindow();
               
            } else if (source == rPurchaseIndentProStatItem) {
                
                    showPurchaseIndentProductStatWindow();
                
            } else if (source == rPurchaseIndentProductItem) {
                
                    showPurchaseIndentProductQueryWindow();
               
            } else if (source == rPurchaseIndentBalanceItem) {
                
                    showPurchaseIndentBalanceQueryWindow();
                
            } else if (source == rPurchaseIndentProBalStatItem) {
                
                    showPurchaseIndentProBalStatWindow();
               
            } else if (source == rPurchaseIndentProBalDetailItem) {
                
                    showPurchaseIndentProBalDetailWindow();
               
            } else if (source == rPurchaseIndentSupplierStatItem) {
//                sssss
               
                    showPurchaseIndentSupplierStatWindow();
                
            } else if (source == rPurchaseIndentPersonStatItem) {
                //1111
                
                    showPurchaseIndentPersonStatWindow();
                
            } else if (source == rProductPurchaseStatItem) {
               
                    showProductPurchaseStatReportWindow();
               
            } else if (source == rProductPurchaseDetailItem) {
               
                    showProductPurchaseDetailQueryWindow();
                
            } else if (source == rSupplierPurchaseStatItem) {
               
                    showSupplierPurchaseStatReportWindow();
                
            } else if (source == rSupplierPurchaseDetailItem) {
                
                    showSupplierPurchaseDetailQueryWindow();
                
            } else if (source == rEmployeePurchaseStatItem) {

                
                    showEmployeePurchaseStatReportWindow();
               
            } else if (source == rEmployeePurchaseDetailItem) {
                
                    showEmployeePurchaseDetailQueryWindow();
                
            } else if (source == rProductPurchaseGatherItem) {
                
                    showProductPurchaseGatherReportWindow();
                
            } else if (source == rProductPurchasePriceWaveItem) {
                //tenf
                
                    showProductPurchasePriceWaveReportWindow();
                
            } else if (source == rSaleIndentItem) {

                
                    showSaleIndentQueryWindow();
                
            } else if (source == rSaleIndentProStatItem) {
                
                    showSaleIndentProductStatWindow();
                
            } else if (source == rSaleIndentProductItem) {

                
                    showSaleIndentProductQueryWindow();
                
            } else if (source == rSaleIndentBalanceItem) {
                //123
                
                    showSaleIndentBalanceQueryWindow();
                
            } else if (source == rSaleIndentProBalStatItem) {
                
                    showSaleIndentProBalStatWindow();
                
            } else if (source == rSaleIndentProBalDetailItem) {
                
                    showSaleIndentProBalDetailWindow();
                
            } else if (source == rSaleIndentCustomerStatItem) {

                
                    showSaleIndentCustomerStatWindow();
                
            } else if (source == rSaleIndentPersonStatItem) {
                
                    showSaleIndentPersonStatWindow();
                
            }

            else if(source == rManagerDailyQueryItem || source == rManagerDailyQueryImageItem)//liufeiadd
            {
                showManagerDailyQueryWindow();
            }
            else if(source == rManagerMonthQueryItem || source == rManagerMonthQueryImageItem)//liufeiadd
            {
                showManagerMonthQueryWindow();
            }
            else if(source == rManagerYearQueryItem || source == rManagerYearQueryImageItem)//liufeiadd
            {
                showManagerYearQueryWindow();
            }
             else if(source == rProductsQueryItem)//liufeiadd
            {
                showProductsQueryWindow();
            }
            else if(source == rCustomersQueryItem)//liufeiadd
            {
                showCustomersQueryWindow();
            }
            else if(source == rSuppliersQueryItem)//liufeiadd
            {
                showSuppliersQueryWindow();
            }
            else if(source == rEmployeesQueryItem)//liufeiadd
            {
                showEmployeesQueryWindow();
            }
            else if (source == hQuestionMenuItem)
            {
                showQuestionDialog();
            }
            else if (source == hCustomerQuoteMenuItem)
            {
                showCustomerDialog();
            }

           
            else if (source == rProductSaleStatItem) {
                
                    showProductSaleStatWindow();
                
            } else if (source == rProductSaleDetailItem) {

                
                    showProductSaleDetailWindow();
               
            } else if (source == rCustomerSaleStatItem) {
                
                    showCustomerSaleStatWindow();
                
            } else if (source == rCustomerSaleDetailItem) {
                
                    showCustomerSaleDetailWindow();
               
            } else if (source == rEmployeeSaleStatItem) {
                
                    showEmployeeSaleStatWindow();
                
            } else if (source == rEmployeeSaleDetailItem) {
                
                    showEmployeeSaleDetailWindow();
                
            } //            else if(source == rProductSaleGatherItem)
            //            {
            //                showProductSaleGatherWindow();
            //            }
            //            else if(source == rProductPriceItem)
            //            {
            //                showProductPriceQueryWindow();
            //            }
            //            else if(source == rProductSalePriceWaveItem)
            //            {
            //                showProductSalePriceWaveWindow();
            //            }
            else if (source == rSubjectBalanceItem || source == rManagerSubjectBalanceItem  || source == rManagerSubjectBalanceImageItem) {

               
                    showSubjectQueryWindow();
                
            } else if (source == rCashBankMonthReportItem) {

              
                    showCashBankMonthReportWindow();
               
            } else if (source == rCashBankDetailItem) {
               
                    showCashBankDetailQueryWindow();
               
            } else if (source == rDailyFeeReportItem) {

                
                    showDailyFeeReportWindow();
                
            } else if (source == rDailyFeeDetailItem) {
                
                    showDailyFeeDetailQueryWindow();
                
            } else if (source == rOtherIncomeMonthReportItem) {
               
                    showOtherIncomeMonthReportWindow();
                
            } else if (source == rOtherIncomeDetailItem) {
                
                    showOtherIncomeDetailQueryWindow();
               
            } else if (source == rSupplierPayBalanceItem || source == rManagerSupplierPayBalanceItem || source == rManagerSupplierPayBalanceImageItem ) {
                
                    showSupplierPayBalanceQueryWindow();
                
            } else if (source == rSupplierUnbalanceDetailItem) {
                
                    showSupplierUnbalanceDetailQueryWindow();
                
            } else if (source == rCustomerReceiveBalanceItem || source == rManagerCustomerReceiveBalanceItem   || source == rManagerCustomerReceiveBalanceImageItem) {
                
                    showCustomerReceiveBalanceQueryWindow();
                
            } else if (source == rCustomerUnbalanceDetailItem) {
                
                    showCustomerUnbalanceDetailQueryWindow();
               
            } else if (source == rSupplierQueryItem) {
                
                    showSupplierQueryWindow();
                
            } else if (source == rCustomerQueryItem) {
                
                    showCustomerQueryWindow();
                
            } else if (source == customerBaseItem) {
                showCustomerManageWindow();

            } else if (source == productBaseItem) {
                showProductManageWindow();
            } else if (source == storageSearchBaseItem) {
                showStorageQueryWindow();
            } else if (source == payableBaseItem) {
                showSupplierUnbalanceDetailQueryWindow();
            }//"/resources/png/items/24/search.png")), "供应商应付未结");
            else if (source == receivableBaseItem) {
                showCustomerUnbalanceDetailQueryWindow();
            }//"/resources/png/items/24/search.png")), "客户应收未结");
            //else if (source == employeeSaleBaseItem) {
            //}//"/resources/png/items/24/employee.png")), "业务员业绩");
//            else if (source == priceBaseItem) {
//                showProductPriceQueryWindow();
//            }//"/resources/png/items/24/money.png")), "价格信息");
            else if (source == subjectRemainBaseItem) {
                showSubjectQueryWindow();
            }//"/resources/png/items/24/money.png")), "科目余额");
            else if (source == storageBaseItem) {
                showStorageManageWindow();
            }//"/resources/png/items/24/storage.png")), "仓库资料");
            else if (source == subjectBaseItem) {
                showSubjectManageWindow();
            }//"/resources/png/items/24/subject.png")), "会计科目");
            else if (source == salesReportImageItem) {
                showSaleReportWindow();
            } else if (source == storageReportImageItem) {
            } else if (source == purchaseReportImageItem) {
            } else if (source == bankCashReportImageItem) {
            } else if (source == clearingImageItem) {
            } else if (source == initReportImageItem) {
            } else {
                //notSupported();
            }
        }


    public void notSupported() {
        JOptionPane.showMessageDialog(this, "暂时未实现!");
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void setVisible(boolean b) {

        Rectangle rect = this.clientPanel.getBounds();
        this.helpPane.setBounds((rect.width - helpPane.getWidth()) / 2, rect.height - this.helpPane.getHeight(), helpPane.getWidth(), helpPane.getHeight());
        super.setVisible(b);
        
        if (b)
        {
            this.showWelcomePane();
            //this.checkReg();
            //
             CLoginDialog dialog = new CLoginDialog(this);
               //setVisible(false);
                dialog.setVisible(true);
                if (dialog.isPassed())
                {
                    Main.setCompany(dialog.getCompany());
                    this.company = dialog.getCompany();
                }
                else
                {
                    System.exit(0);
                    return;
                }
                setCompany(company);
                setUser(dialog.getUser());
                String role = "角色：";
                if (user.getPurchaseRole() == 1)
                {
                    role += "采购员、";

                }
                if (user.getSaleRole() == 1)
                {
                    role += "销售员、";
                }

                if (user.getStorageRole() == 1)
                {
                    role += "仓管员、";
                }

                if (user.getFinanceRole() == 1)
                {
                    role += "财务、";
                }

//                if (user.getCheckPermission() == 1)
//                {
//                    role += "  具审核权限";
//
//                }
//                else
//                {
//                    role += "  无审核权限";
//                }

                statusBar.addText("当前用户："+user.getUserName() + "   " + role + " 套帐企业：" + company.getName(), 5, 500);
            
        }

    }

    private CNaviButton purchaseButton;
    private CNaviButton saleButton;
    private CNaviButton storageButton;
    private CNaviButton clearingButton;
    private CNaviButton bankCashButton;
    private CNaviButton reportButton;
    private CNaviButton managerButton;
    private CNaviButton baseButton;
    private CNaviButton initButton;
    private CImageItem productImageItem;
    private CImageItem supplierImageItem;
    private CImageItem customerImageItem;
    private CImageItem employeeImageItem;
    private CImageItem storageImageItem;
    private CImageItem subjectImageItem;
    private CImageItem salesReportImageItem;
    private CImageItem storageReportImageItem;
    private CImageItem purchaseReportImageItem;
    private CImageItem bankCashReportImageItem;
    private CImageItem clearingImageItem;
    private CImageItem initReportImageItem;
    private CImageItem mBussinessExpenseImageItem;// = new CMenuImageItem("缁??璐圭?");
    private CLittleImageItem mBussinessExpenseImageItemList;
    private CImageItem mCommonExpenseImageItem;// = new CMenuItem("涓??璐圭?");
    private CLittleImageItem mCommonExpenseImageItemList;
    private CImageItem mOtherIncomeImageItem;//= new CMenuItem("?跺??跺?");
    private CLittleImageItem mOtherIncomeImageItemList;
    private CImageItem mBankCashImageItem;// = new CMenuItem("?惰?甯??");
    private CLittleImageItem mBankCashImageItemList;
    private CImageItem fStorageBeginManageImageItem;
    private CLittleImageItem fStorageBeginManageImageItemList;
    private CImageItem bSubjectBeginManageImageItem;// = new CImageItem("绉??浣?????绠＄?");
    private CLittleImageItem bSubjectBeginManageImageItemList;
    private CImageItem bReceivableBeginMangeImageItem;// = new CImageItem("搴?????绠＄?");
    private CLittleImageItem bReceivableBeginMangeImageItemList;
    private CImageItem bPaymentBeginManageImageItem;// = new CImageItem("搴?????绠＄?");
    private CLittleImageItem bPaymentBeginManageImageItemList;
    //purchase
    private CImageItem pOrderImageItem;// = new CMenuItem("??喘璁㈠?");
    private CLittleImageItem pOrderImageItemList;
    private CImageItem pPurchaseImageItem;// = new CMenuItem("??喘??);
    private CLittleImageItem pPurchaseImageItemList;
    private CImageItem pPaymentImageItem;// = new CMenuItem("浠??缁??");
    private CLittleImageItem pPaymentImageItemList;
    private CImageItem pOrderChangedImageItem;// = new CMenuItem("璁㈠????");
    private CLittleImageItem pOrderChangedImageItemList;
    private CImageItem pOrderBackImageItem;// = new CMenuItem("??喘??揣");
    private CLittleImageItem pOrderBackImageItemList;

    //sale
    private CImageItem sQuoteImageItem;// = new CMenuItem("????ヤ环");
    private CLittleImageItem sQuoteImageItemList;
    private CImageItem sOrderImageItem;// = new CMenuItem("???璁㈠?");
    private CLittleImageItem sOrderImageItemList;
    private CImageItem sSaleListImageItem;// = new CMenuItem("???娓??");
    private CLittleImageItem sSaleListImageItemList;
    private CImageItem sGartheringImageItem;// = new CMenuItem("?舵?缁??");
    private CLittleImageItem sGartheringImageItemList;
    private CImageItem sAjustPriceImageItem;// = new CMenuItem("???璋?环");
    private CLittleImageItem sAjustPriceImageItemList;
    private CImageItem sOrderChangedImageItem;// = new CMenuItem("璁㈠????");
    private CLittleImageItem sOrderChangedImageItemList;
    private CImageItem sOrderBackImageItem;// = new CMenuItem("?????揣");
    private CLittleImageItem sOrderBackImageItemList;
    //
    private CImageItem mPreReceivableImageItem;// = new CMenuItem("???棰??绠＄?");
    private CLittleImageItem mPreReceivableImageItemList;
    private CImageItem mReceivableClearingImageItem;// = new CMenuItem("?舵?缁??绠＄?");
    private CLittleImageItem mReceivableClearingImageItemList;
    private CImageItem mPrePayableImageItem;// = new CMenuItem("??喘棰??绠＄?");
    private CLittleImageItem mPrePayableImageItemList;
    private CImageItem mPayableClearingImageItem;// = new CMenuItem("浠??缁??绠＄?");
    private CLittleImageItem mPayableClearingImageItemList;
    // warehouse
    private CImageItem wStorageInImageItem;
    private CLittleImageItem wStorageInImageItemList;
    private CImageItem wStorageOutImageItem;
    private CLittleImageItem wStorageOutImageItemList;
    private CImageItem wStorageOutflowImageItem;
    private CLittleImageItem wStorageOutflowImageItemList;
    private CImageItem wStorageLossImageItem;
    private CLittleImageItem wStorageLossImageItemList;
    private CImageItem wStorageCheckImageItem;
    private CLittleImageItem wStorageCheckImageItemList;
    private CImageItem wStorageMoveImageItem;
    private CLittleImageItem wStorageMoveImageItemList;
    private CImageItem wStorageChangeMoneyImageItem;
    private CLittleImageItem wStorageChangeMoneyImageItemList;
    private CImageItem wStorageMaxMinImageItem;
    private CLittleImageItem wStorageMaxMinImageItemList;
    // produce

    // system
    private CImageItem sCompanyInfoImageItem;
    private CImageItem sUserImageItem;
    private CImageItem sAccountOptionItem;
    private CImageItem sLogImageItem;
    private CImageItem sNumberImageItem;
    //report purchase
    private CImageItem rPurchaseIndentImageItem;
    private CImageItem rPurchaseIndentProStatImageItem;
    private CImageItem rPurchaseIndentProductImageItem;
    private CImageItem rPurchaseIndentBalanceImageItem;////???灞??
    private CImageItem rPurchaseIndentProBalStatImageItem;////???灞??
    private CImageItem rPurchaseIndentProBalDetailImageItem;////???灞??
    private CImageItem rPurchaseIndentSupplierStatImageItem;
    private CImageItem rPurchaseIndentPersonStatImageItem;
    private CImageItem rProductPurchaseStatImageItem;
    private CImageItem rProductPurchaseDetailImageItem;
    private CImageItem rSupplierPurchaseStatImageItem;
    private CImageItem rSupplierPurchaseDetailImageItem;
    private CImageItem rEmployeePurchaseStatImageItem;
    private CImageItem rEmployeePurchaseDetailImageItem;
    private CImageItem rProductPurchaseGatherImageItem;////???灞??
    private CImageItem rProductPurchasePriceWaveImageItem;////???灞??

    //report sale
    private CImageItem rSaleIndentImageItem;
    private CImageItem rSaleIndentProStatImageItem;
    private CImageItem rSaleIndentProductImageItem;
    private CImageItem rSaleIndentBalanceImageItem;////???灞??
    private CImageItem rSaleIndentProBalStatImageItem;////???灞??
    private CImageItem rSaleIndentProBalDetailImageItem;////???灞??
    private CImageItem rSaleIndentCustomerStatImageItem;
    private CImageItem rSaleIndentPersonStatImageItem;
    private CImageItem rCustomerMonthSaleCompareImageItem;
    private CImageItem rProductMonthSaleCompareImageItem;
    private CImageItem rCustomerSaleOrderImageItem;
    private CImageItem rProductSaleOrderImageItem;
    private CImageItem rEmployeeSaleOrderImageItem;
    private CImageItem rProductSaleStatImageItem;
    private CImageItem rProductSaleDetailImageItem;
    private CImageItem rSupplierSaleStatImageItem;
    private CImageItem rSupplierSaleDetailImageItem;
    private CImageItem rEmployeeSaleStatImageItem;
    private CImageItem rEmployeeSaleDetailImageItem;
    private CImageItem rProductSaleGatherImageItem;
    private CImageItem rProductPriceImageItem;
    private CImageItem rProductSalePriceWaveImageItem;

    //report finance
    private CImageItem rSubjectBalanceImageItem;
    private CImageItem rCashBankMonthReportImageItem;
    private CImageItem rCashBankDetailImageItem;
    private CImageItem rDailyFeeReportImageItem;
    private CImageItem rDailyFeeDetailImageItem;
    private CImageItem rOtherIncomeMonthReportImageItem;
    private CImageItem rOtherIncomeDetailImageItem;

    //report balance
    private CImageItem rSupplierPayBalanceImageItem;
    private CImageItem rSupplierUnbalanceDetailImageItem;
    private CImageItem rCustomerReceiveBalanceImageItem;
    private CImageItem rCustomerUnbalanceDetailImageItem;
    private CImageItem rSupplierQueryImageItem;////???灞??
    private CImageItem rCustomerQueryImageItem;////???灞??
    private JPanel basePanel;
    private JPanel initPanel;
    private JPanel storagePanel;
    private JPanel purchasePanel;
    private JPanel salesPanel;
    private JPanel clearingPanel;
    private JPanel bankCashPanel;
    private JPanel welcomePanel;
    private JPanel bPanel;

    private JPanel managerPanel;

    private CImageItem rManagerDailyQueryImageItem;
    private CImageItem rManagerMonthQueryImageItem;
    private CImageItem rManagerYearQueryImageItem;
    private CImageItem rManagerStorageBalanceImageItem;
    private CImageItem rManagerStorageChangeImageItem;

    private CImageItem rManagerSubjectBalanceImageItem;
    private CImageItem rManagerSupplierPayBalanceImageItem;
    private CImageItem rManagerCustomerReceiveBalanceImageItem;
    //private CGridItemPane outStoragePanel;
    //private CGridItemPane checkStoragePanel;
    //private CGridItemPane moneyStoragePanel;

    private JPanel systemPanel;
    private CPanel reportPanel;
    private CBaseImageItem productBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/product2.png")), "商品资料");
    private CBaseImageItem storageSearchBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/search.png")), "库存查询");
    private CBaseImageItem storageMinMaxBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/search.png")), "库存警报");
    private CBaseImageItem payableBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/search.png")), "供应商应付未结");
    private CBaseImageItem receivableBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/search.png")), "客户应收未结");
    //private CBaseImageItem employeeSaleBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/employee.png")), "业务员业绩");
    //private CBaseImageItem priceBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/money.png")), "价格信息");
    private CBaseImageItem subjectRemainBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/money.png")), "科目余额");
    private CBaseImageItem customerBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/customer.png")), "客户资料");
    private CBaseImageItem storageBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/storage.png")), "仓库资料");
    private CBaseImageItem subjectBaseItem = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/png/items/24/subject.png")), "会计科目");
    private CBaseImageItem refreshBaseInfo = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/refresh.png")), "刷新基础信息");
    private CBaseImageItem welcomePage = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/welcomeicon.png")), "欢迎页面");
    private CBaseImageItem changePassword = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/key.png")), "修改密码");
    private CBaseImageItem website = new CBaseImageItem(new ImageIcon(this.getClass().getResource("/resources/home.png")), "产品主页");
    private ImageIcon arrowDownIcon = new ImageIcon(this.getClass().getResource("/resources/png/items/arrows/down.png"));
    private ImageIcon arrowUpIcon = new ImageIcon(this.getClass().getResource("/resources/png/items/arrows/up.png"));
    private ImageIcon arrowLeftIcon = new ImageIcon(this.getClass().getResource("/resources/png/items/arrows/left.png"));
    private ImageIcon arrowRightIcon = new ImageIcon(this.getClass().getResource("/resources/png/items/arrows/right.png"));

    public void showAboutDialog() {
        JDialog dialog = new CAboutDialog(this);
        dialog.setVisible(true);
    }

    public void showDatabaseTool() {
        try {
            if (MessageBox.showQuestionDialog(this, "对数据库进行备份及恢复操作必须先退出当前系统，您确定要这么做吗?") == 0) {
//                String command = Main.getInstallPath() +"/databasetool.exe";
//                Runtime.getRuntime().exec(command);
//                System.exit(0);
                MessageBox.showMessageDialog(this, "当前版本暂未实现!");
            }

        } catch (Exception ex) {
            MessageBox.showMessageDialog(this, "系统没有正确安装，找不到数据库工具程序!");
        }
    }

    public void showHelpBook() {
        try {
            String helpFile = Main.getInstallPath() + "/help.chm";
            Runtime.getRuntime().exec("hh.exe " + helpFile);
        } catch (Exception ex) {
        }
    }

    public void showStorageAlertQueryDialog(Company company) {
        CStorageAlertQueryDialog dialog = new CStorageAlertQueryDialog(Main.getMainFrame(), company);
        dialog.setVisible(true);
    }

    public void showStorageAlertQueryDialog() {
        CStorageAlertQueryDialog dialog = new CStorageAlertQueryDialog(Main.getMainFrame());
        dialog.setVisible(true);
    }

    public void showStorageQueryWindow() {
        if (!this.checkStoragePermission())
       {
           return;
       }
        //this.clearInternalFrames();
        JFrame roleManageDialog = new CStorageQueryWindow("库存余额查询");
        roleManageDialog.setVisible(true);

    }

    public void showSubjectQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame subjectQueryWindow = new CSubjectQueryWindow("科目余额查询");
        subjectQueryWindow.setVisible(true);
    }

    //*************************************************************************************
    public void showStorageInReportWindow() {
        //this.clearInternalFrames();

        if (!this.checkStoragePermission())
       {
           return;
       }

        JFrame roleManageDialog = new CStorageInReportWindow("库存商品入库查询");
        roleManageDialog.setVisible(true);

    }

    public void showStorageOutReportWindow() {

        if (!this.checkStoragePermission())
       {
           return;
       }
        //this.clearInternalFrames();
        JFrame window = new CStorageOutReportWindow("库存商品出库查询");
        window.setVisible(true);

    }

    public void showProductsQueryWindow()
    {
        JFrame window =  new CProductQueryWindow("商品信息查询");
        window.setVisible(true);
    }

    public void showCustomersQueryWindow()
    {

        JFrame window = new CCustomersQueryWindow("客户信息查询");
        window.setVisible(true);
    }

    public void showSuppliersQueryWindow()
    {
        JFrame window = new CSuppliersQueryWindow("供应商信息查询");
        window.setVisible(true);
    }

    public void showEmployeesQueryWindow()
    {
        JFrame window = new CEmployeeQueryWindow("职员信息查询");
        window.setVisible(true);
    }

    public void showSaleReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CReportWindow("销售查询");
        window.setVisible(true);
    }

    public void showStorageInListQueryWindow() {
        if (!this.checkStoragePermission())
       {
           return;
       }
        //this.clearInternalFrames();
        JFrame window = new CStorageInListQueryWindow("库存商品入库明细查询");
        window.setVisible(true);
    }

    public void showStorageOutListQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageOutListQueryWindow("库存商品出库明细查询");

        //this.desktopPane.add(window, -1);
        window.setVisible(true);
    }

    public void showStorageMinMaxQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageMinMaxQueryWindow("库存商品上下限查询");
        window.setVisible(true);

    }

    public void showStorageOutflowReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageOutflowReportWindow("库存报溢明细查询");
        window.setVisible(true);


    }

    public void showStorageCheckReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageCheckReportWindow("库存盘点明细查询");

        //this.desktopPane.add(window, -1);
        window.setVisible(true);
    }

    public void showStorageChangeMoneyReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageChangeMoneyReportWindow("库存变价明细查询");
        window.setVisible(true);
    }

    public void showStorageLossReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageLossReportWindow("库存报损明细查询");
        window.setVisible(true);
    }

    public void showStorageCheckQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageCheckQueryWindow("库存报盘点查询");

        window.setVisible(true);
    }

    public void showStorageMoveReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageMoveReportWindow("库存移库明细查询");
        window.setVisible(true);
    }

    public void showStorageDetailQueryWindow()
    {
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageDetailQueryWindow("库存商品变化明细查询");
        window.setVisible(true);
      
    }

    public void showStorageDetailMonthQueryWindow()
    {
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageDetailMonthQueryWindow("库存商品变化明细月查询");
        window.setVisible(true);
    }

    //show purchase report window
    public void showPurchaseIndentQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentQueryWindow("采购订单查询");
        window.setVisible(true);
    }

    public void showEmployeePurchaseDetailQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CEmployeePurchaseDetailQueryWindow("职员采购明细查询");
        window.setVisible(true);
    }

    public void showEmployeePurchaseStatReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CEmployeePurchaseStatReportWindow("职员采购统计表");
        window.setVisible(true);
    }

    public void showProductPurchaseDetailQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CProductPurchaseDetailQueryWindow("商品采购明细查询");
        //this.desktopPane.add(window, -1);
        window.setVisible(true);
    }

    public void showProductPurchaseGatherReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CProductPurchaseGatherReportWindow("商品采购汇总表");
        window.setVisible(true);
    }

    public void showProductPurchasePriceWaveReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CProductPurchasePriceWaveReportWindow("商品采购价格波动表");
        window.setVisible(true);
    }

    public void showProductPurchaseStatReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CProductPurchaseStatReportWindow("商品采购统计表");
        window.setVisible(true);
    }

    public void showPurchaseIndentBalanceQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentBalanceQueryWindow("采购订单余额查询");
        window.setVisible(true);
    }

    public void showPurchaseIndentPersonStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentPersonStatWindow("采购订单业务员统计");
        window.setVisible(true);
    }

    public void showPurchaseIndentProBalDetailWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentProBalDetailWindow("采购订单商品余额明细查询");
        window.setVisible(true);
    }

    public void showPurchaseIndentProBalStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentProBalStatWindow("采购订单商品余额统计");
        window.setVisible(true);
    }

    public void showPurchaseIndentProductQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentProductQueryWindow("采购订单商品明细");
        window.setVisible(true);
    }

    public void showPurchaseIndentProductStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentProductStatWindow("采购订单商品统计");
        window.setVisible(true);
    }

    public void showPurchaseIndentSupplierStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentSupplierStatWindow("采购订单供应商统计");
        window.setVisible(true);
    }

    public void showSupplierPurchaseDetailQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CSupplierPurchaseDetailQueryWindow("供应商采购明细查询");
        window.setVisible(true);
    }

    public void showSupplierPurchaseStatReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CSupplierPurchaseStatReportWindow("供应商采购统计表");
        window.setVisible(true);
    }

    public void showSaleIndentQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleIndentQueryWindow("销售订单查询");
        window.setVisible(true);
    }

    public void showSaleIndentProductStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleIndentProductStatWindow("销售订单商品统计");
        window.setVisible(true);
    }

    public void showSaleIndentProductQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleIndentProductQueryWindow("销售订单商品明细");
        window.setVisible(true);
    }

    public void showSaleIndentBalanceQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleIndentBalanceQueryWindow("销售订单余额查询");
        window.setVisible(true);
    }

    public void showSaleIndentProBalStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleIndentProBalStatWindow("销售订单商品余额统计");
        window.setVisible(true);
    }

    public void showSaleIndentProBalDetailWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleIndentProBalDetailWindow("销售订单商品余额明细查询");
        window.setVisible(true);
    }

    public void showSaleIndentCustomerStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleIndentCustomerStatWindow(" 客商统计");
        window.setVisible(true);
    }

    public void showSaleIndentPersonStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleIndentPersonStatWindow("销售订单业务员统计");
        window.setVisible(true);
    }

    public void showCustomerMonthSaleCompareWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CCustomerMonthSaleCompareWindow("客商月销售比较表");
        window.setVisible(true);
    }

    public void showProductMonthSaleCompareWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CProductMonthSaleCompareWindow("商品月销售比较表");
        window.setVisible(true);
    }

    public void showCustomerSaleOrderWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CCustomerSaleOrderWindow("客商销售排行榜");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showProductSaleOrderWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CProductSaleOrderWindow("商品销售排行榜");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showEmployeeSaleOrderWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CEmployeeSaleOrderWindow("职员销售排行榜");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showProductSaleStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CProductSaleStatReportWindow("商品销售统计表");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showProductSaleDetailWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CProductSaleDetailQueryWindow("商品销售明细查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCustomerSaleStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CCustomerSaleStatReportWindow("客商销售统计表");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCustomerSaleDetailWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CCustomerSaleDetailQueryWindow("客商销售明细查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showEmployeeSaleStatWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CEmployeeSaleStatReportWindow("职员销售统计表");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showEmployeeSaleDetailWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CEmployeeSaleDetailQueryWindow("职员销售明细查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showProductSaleGatherWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CProductSaleGatherReportWindow("商品销售汇总表");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showProductPriceQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CProductPriceQueryWindow("商品价格查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showProductSalePriceWaveWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CProductSalePriceWaveReportWindow("商品销售价格波动表");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCashBankMonthReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CCashBankMonthReportWindow("现金银行月报表");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCashBankDetailQueryWindow() {
        if (!this.checkFinancePermission())
       {
           return;
       }

        //this.clearInternalFrames();
        JFrame window = new CCashBankDetailQueryWindow("现金银行明细查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showManagerDailyQueryWindow()
    {
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame window = new CManagerDailyQueryWindow("日报表查询");
        window.setVisible(true);
    }

    public void showManagerMonthQueryWindow()
    {
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame window =  new CManagerMonthQueryWindow("月报表查询");
        window.setVisible(true);


    }

    public void showManagerYearQueryWindow()
    {
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame window = new CManagerYearQueryWindow("年报表查询");
        window.setVisible(true);


    }

    public void showDailyFeeReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CDailyFeeReportWindow("日常费用报表");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showDailyFeeDetailQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CDailyFeeDetailQueryWindow("日常费用明细查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showOtherIncomeMonthReportWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new COtherIncomeMonthReportWindow("其它收入月报表");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showOtherIncomeDetailQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new COtherIncomeDetailQueryWindow("其它收入明细查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSupplierPayBalanceQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CSupplierPayBalanceQueryWindow("供应商应付余额查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSupplierUnbalanceDetailQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CSupplierUnbalanceDetailQueryWindow("供应商未结明细查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCustomerReceiveBalanceQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CCustomerReceiveBalanceQueryWindow("客商应收余额查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCustomerUnbalanceDetailQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CCustomerUnbalanceDetailQueryWindow("客商未结明细查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSupplierQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CSupplierQueryWindow("供应商往来流水查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCustomerQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CCustomerQueryWindow("客商往来流水查询");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCustomerContactsReportDialog() {
    }

    public void showRoleManageDialog() {
        //this.clearInternalFrames();
        JFrame roleManageDialog = new CRoleManageWindow("角色管理");

        //this.desktopPane.add(roleManageDialog, -1);

        try {
            roleManageDialog.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showUserManageWindow() {
        //this.clearInternalFrames();
        if (!user.getUserName().equals("admin"))
        {
            MessageBox.showErrorDialog(this, "只有admin用户能做此操作!");
            return;
        }
        JFrame window = new CUserManageWindow("用户管理");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showChangePasswordDialog() {

        JDialog dialog = new CChangePasswordDialog(this);
        dialog.setVisible(true);
    }

    public void visitWebsite()
    {
        
        try {
            Runtime.getRuntime().exec("explorer http://www.free-erp.com"); // http://www.168erp.com
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        



    }

    public void showCSystemLogWindow() {
        //this.clearInternalFrames();
        if (!user.getUserName().equals("admin"))
        {
            MessageBox.showErrorDialog(this, "只有admin用户能做此操作!");
            return;
        }
        JFrame window = new CSystemLogWindow("系统日志");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showBeginPaymentManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame window = new CBeginPaymentWindow("应付期初");
        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSystemLogDialog() {
        if (!user.getUserName().equals("admin"))
        {
            MessageBox.showErrorDialog(this, "只有admin用户能做此操作!");
            return;
        }
        JDialog dialog = new CSystemLogDialog(this);
        dialog.setVisible(true);
    }

    public void showSystemOptionDialog() {
        if (!user.getUserName().equals("admin"))
        {
            MessageBox.showErrorDialog(this, "只有admin用户能做此操作!");
            return;
        }
        JDialog dialog = new CSystemOptionDialog(this);
        dialog.setVisible(true);
    }

    public void showNumberManageDialog() {
        JDialog dialog = new CSystemOptionDialog(this);
        dialog.setVisible(true);
    }

    public void showCompanyInfoDialog() {
        JDialog dialog = new CCompanyInfoDialog(this);
        dialog.setVisible(true);
    }
    //liufei

    public void showCProUnitListDialog() {
        JDialog formDialog = new CProUnitListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCProShelfListDialog() {
          JDialog formDialog = new CProShelfListDialog(Main.getMainFrame());
           formDialog.setVisible(true);
    }

    public void showCProTaxrateListDialog() {
        JDialog formDialog = new CProTaxrateListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCCusTypeListDialog() {
        JDialog formDialog = new CCusTypeListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCCusPriceListDialog() {
        JDialog formDialog = new CCusPriceListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCCusInvoiceListDialog() {
        JDialog formDialog = new CCusInvoiceListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCEmpDutyListDialog() {
        JDialog formDialog = new CEmpDutyListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCEmpDegreeListDialog() {
        JDialog formDialog = new CEmpDegreeListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCEmpLevelListDialog() {
        JDialog formDialog = new CEmpLevelListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCEmpHealthListDialog() {
        JDialog formDialog = new CEmpHealthListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showCStoTypeListDialog() {
        JDialog formDialog = new CStoTypeListDialog(Main.getMainFrame());
        formDialog.setVisible(true);
    }

    public void showProductManageWindow() {
        //this.clearInternalFrames();
        JFrame productManageWindow = new CProductManageWindow("商品信息");
        //this.desktopPane.add(productManageWindow, new Integer(1));

//        if (productManageWindow.getParent() != //this.desktopPane) {
//            //this.desktopPane.add(productManageWindow, -1);
//        }

        try {
            productManageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showQuestionDialog()
    {
        new QuestionDialog(this).setVisible(true);
    }

    public void showCustomerDialog()
    {
        new CustomerDialog(this).setVisible(true);
    }

    public void showCustomerManageWindow() {
        //this.clearInternalFrames();
        JFrame customerManageWindow = new CCustomerManageWindow("客户信息");
        //this.desktopPane.add(customerManageWindow, -1);

        try {
            customerManageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSupplierManageWindow() {
        //this.clearInternalFrames();
        JFrame customerManageWindow = new CSupplierManageWindow("供应商信息");

        //this.desktopPane.add(customerManageWindow, -1);

        try {
            customerManageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSubjectManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }

        JFrame subjectManageWindow = new CSubjectManageWindow("会计科目");
        //this.desktopPane.add(subjectManageWindow, new Integer(1));

//        if (subjectManageWindow.getParent() != //this.desktopPane) {
//            //this.desktopPane.add(subjectManageWindow, -1);
//        }

        try {
            subjectManageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showEmployeeWindow() {
        //this.clearInternalFrames();
        JFrame employeeManageWindow = new CEmployeeManageWindow("员工信息");

        //this.desktopPane.add(employeeManageWindow, -1);
        try {
            employeeManageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame storageManageWindow = new CStorageManageWindow("仓库信息");

        //this.desktopPane.add(storageManageWindow, -1);

        try {
            storageManageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageInWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame storageInWindow = new CStorageInWindow("商品入库");

        //this.desktopPane.add(storageInWindow, -1);

        try {
            storageInWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageOutflowWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageOutflowWindow("库存报溢");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageOutflowQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageOutflowQueryWindow("库存报溢");
        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageLossQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageLossQueryWindow("库存报损");
        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageMoveQueryWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageMoveQueryWindow("库存移库");
        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageLossWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageLossWindow("库存报损");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageMoveWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageMoveWindow("移库管理");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageOutWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame storageOutWindow = new CStorageOutWindow("商品出库");

        //this.desktopPane.add(storageOutWindow, -1);

        try {
            storageOutWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageCheckWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageCheckWindow("盘点管理");

        //this.desktopPane.add(window, -1);
        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageChangeMoneyWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageChangeMoneyWindow("库存变价");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showStorageMaxMinWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CStorageMinMaxWindow("库存上下限");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSaleQuoteWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleQuoteWindow("商品报价");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSaleOrderWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleOrderWindow("销售订单");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSaleDetailWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleDetailWindow("销售单");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSaleGarcheringWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleGartheringWindow("收款结算");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSaleChangeMoneyWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleChangeMoneyWindow("商品调价");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSaleOrderChangeWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleChangeOrderWindow("订单变更");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showSaleOrderBackWindow() {
        //this.clearInternalFrames();
        if (!this.checkSalePermission())
        {
            return;
        }
        JFrame window = new CSaleOrderBackWindow("销售退货");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPurchaseOrderWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseIndentWindow("采购订单");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPurchaseDetailWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseOrderWindow("采购单");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPurchasePaymentWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame window = new CPayableClearingManageWindow("付款结算");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPurchaseChangeOrderWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseChangeOrderWindow("采购订单变更");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPurchaseOrderBackWindow() {
        //this.clearInternalFrames();
        if (!this.checkPurchasePermission())
        {
            return;
        }
        JFrame window = new CPurchaseOrderBackWindow("采购退货");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showBeginStorageManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkStoragePermission())
       {
           return;
       }
        JFrame window = new CBeginStorageWindow("库存期初");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showBeginSubjectManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame window = new CBeginSubjectWindow("科目余额期初");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showBeginReceiveableManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame window = new CBeginReceivableWindow("应收期初");

        //this.desktopPane.add(window, -1);

        try {
            window.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showBussinessExpenseManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame manageWindow = new CBussinessExpenseManageWindow("经营费用");
        //this.desktopPane.add(manageWindow, new Integer(1));



        try {
            manageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCommonExpenseManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame manageWindow = new CCommonExpenseManageWindow("一般费用");
        //this.desktopPane.add(manageWindow, new Integer(1));



        try {
            manageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showOtherIncomeManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame manageWindow = new COtherIncomeManageWindow("其它收入");
        //this.desktopPane.add(manageWindow, new Integer(1));



        try {
            manageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPayableClearingManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame manageWindow = new CPayableClearingManageWindow("付款结算");
        //this.desktopPane.add(manageWindow, new Integer(1));



        try {
            manageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPrepayableManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame manageWindow = new CPrePayableManageWindow("采购预付");
        //this.desktopPane.add(manageWindow, new Integer(1));



        try {
            manageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPrereceivableManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame manageWindow = new CPreReceivableManageWindow("销售预收");
        //this.desktopPane.add(manageWindow, new Integer(1));



        try {
            manageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showReceivableClearingManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame manageWindow = new CReceivableClearingManageWindow("收款结算");
        //this.desktopPane.add(manageWindow, new Integer(1));



        try {
            manageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshDataBase() {
        if (this.dataBaseObjectPool != null) {
            this.dataBaseObjectPool.dispose();
        }
        this.dataBaseObjectPool = new ObjectsPool(this.company);
        //refresh
        if (this.productSelectPanel != null) {
            this.productSelectPanel.refresh();
        }
    }

    public void showBankCashManageWindow() {
        //this.clearInternalFrames();
        if (!this.checkFinancePermission())
       {
           return;
       }
        JFrame manageWindow = new CBankCashManageWindow("现金银行");
        //this.desktopPane.add(manageWindow, new Integer(1));



        try {
            manageWindow.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void close() {
        if (MessageBox.showQuestionDialog(MainFrame.this, "您确定要退出吗？") == 0) {
            Main.getSystemConfig().saveSystemConfig();
            System.exit(0);
        }
    }

    public void setDataBaseObjectPool(ObjectsPool dataBaseObjectPool) {
        this.dataBaseObjectPool = dataBaseObjectPool;
    }

    public CComboBoxPane getProductSelectPanel() {
//        if (this.productSelectPanel == null)
//        {
//            this.productSelectPanel = new ProductSelectPanel(this.company);
//        }
//        return this.productSelectPanel;
        return new ProductSelectPanel(this.company);
    }

    public ObjectsPool getObjectsPool() {
        if (this.listObjectPool == null) {
            this.listObjectPool = new ObjectsPool(this.company);
        }
        return this.listObjectPool;
    }

    public ObjectsPool getDataBaseObjectPool() {
        if (dataBaseObjectPool == null) {
            dataBaseObjectPool = new ObjectsPool(this.company);
        }
        return dataBaseObjectPool;
    }

    public Rectangle getClientRect() {
        return this.getBounds();
    }

    
    private IDbSupport storageDbSupport;
    private IDbSupport purchaseDbSupport;
    private IDbSupport saleDbSupport;
    private IDbSupport financeDbSupport;

    public IDbSupport getStorageDbSupport() {
        if (storageDbSupport == null) {
            storageDbSupport = new IDbSupport() {

                public void delete(Object obj) {

                    IStorageService service = Main.getServiceManager().getStorageService();
                    if (obj instanceof InStoragePo) {
                        service.deleteInStorageForm((InStoragePo) obj);
                    } else if (obj instanceof CheckStoragePo) {
                        service.deleteCheckStorageForm((CheckStoragePo) obj);
                    } else if (obj instanceof OutStoragePo) {
                        service.deleteOutStorageForm((OutStoragePo) obj);
                    } else if (obj instanceof InitialStoragePo) {
                        service.deleteInitialStorageForm((InitialStoragePo) obj);
                    } else if (obj instanceof LossStoragePo) {
                        service.deleteLossStorageForm((LossStoragePo) obj);
                    } else if (obj instanceof MinMaxStoragePo) {
                        service.deleteMinMaxStorageForm((MinMaxStoragePo) obj);
                    } else if (obj instanceof MoveStoragePo) {
                        service.deleteMoveStorageForm((MoveStoragePo) obj);
                    } else if (obj instanceof PriceStoragePo) {
                        service.deletePriceStorageForm((PriceStoragePo) obj);
                    } else if (obj instanceof OutflowStoragePo) {
                        service.deleteOutflowStorageForm((OutflowStoragePo) obj);
                    }
                }

                public void deleteAll(Collection<Object> objs) {
                }

                public void save(Object obj) {

                    IStorageService service = Main.getServiceManager().getStorageService();
                    if (obj instanceof InStoragePo) {
                        service.saveInStorageForm((InStoragePo) obj);
                    } else if (obj instanceof CheckStoragePo) {
                        service.saveCheckStorageForm((CheckStoragePo) obj);
                    } else if (obj instanceof OutStoragePo) {
                        service.saveOutStorageForm((OutStoragePo) obj);
                    } else if (obj instanceof InitialStoragePo) {
                        service.saveInitialStorageForm((InitialStoragePo) obj);
                    } else if (obj instanceof LossStoragePo) {
                        service.saveLossStorageForm((LossStoragePo) obj);
                    } else if (obj instanceof MinMaxStoragePo) {
                        service.saveMinMaxStorageForm((MinMaxStoragePo) obj);
                    } else if (obj instanceof MoveStoragePo) {
                        service.saveMoveStorageForm((MoveStoragePo) obj);
                    } else if (obj instanceof PriceStoragePo) {
                        service.savePriceStorageForm((PriceStoragePo) obj);
                    } else if (obj instanceof OutflowStoragePo) {
                        service.saveOutflowStorageForm((OutflowStoragePo) obj);
                    }

                }

                public void saveAll(Collection<Object> objs) {
                }
            };
        }
        return storageDbSupport;
    }

    public void addOrderChangedForm() {
//    dataSource =new dataSource();d

        table = new JDataTable();
        table.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (e.getClickCount() >= 2) {
//					doEdit();
                }
            }

            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            public void mousePressed(MouseEvent e) {
                int mods = e.getModifiers();
                if ((mods & InputEvent.BUTTON3_MASK) != 0) {
                    mainPopMenu.show((Component) e.getSource(), e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        });
        table.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    doEdit();
                }
            }

            private void doEdit() {
                throw new UnsupportedOperationException("Not yet implemented");
            }
        });
        this.dataSource = this.table.getDataSource();
        this.dataSource.setKeyColumnName("id");

        CChoosepurchaseOrderChangeDialog dialog = new CChoosepurchaseOrderChangeDialog(this, dataSource, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);

    }

    public void addSaleOrderChangedForm() {
        if (!this.checkSalePermission())
        {
            return;
        }
//    dataSource =new dataSource();d
        table = new JDataTable();
        table.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (e.getClickCount() >= 2) {
//					doEdit();
                }
            }

            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            public void mousePressed(MouseEvent e) {
                int mods = e.getModifiers();
                if ((mods & InputEvent.BUTTON3_MASK) != 0) {
                    mainPopMenu.show((Component) e.getSource(), e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        });
        table.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    doEdit();
                }
            }

            private void doEdit() {
                throw new UnsupportedOperationException("Not yet implemented");
            }
        });
        this.dataSource = this.table.getDataSource();
        this.dataSource.setKeyColumnName("id");

        CSaleChooseOrderChangeDialog dialog = new CSaleChooseOrderChangeDialog(this, dataSource, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);

    }

    public void addStorageInForm() {

    if (!this.checkStoragePermission())
       {
           return;
       }
        CStorageInDialog dialog = new CStorageInDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addStorageOutForm() {

    if (!this.checkStoragePermission())
       {
           return;
       }
        CStorageOutDialog dialog = new CStorageOutDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addOutflowForm() {

    if (!this.checkStoragePermission())
       {
           return;
       }
        CStorageOutflowDialog dialog = new CStorageOutflowDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addStorageMoveForm() {

        if (!this.checkStoragePermission())
       {
           return;
       }
        CStorageMoveDialog dialog = new CStorageMoveDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);

    }

    public void addStorageCheckForm() {

if (!this.checkStoragePermission())
       {
           return;
       }
        CStorageCheckDialog dialog = new CStorageCheckDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addStorageChangeMoneyForm() {
if (!this.checkStoragePermission())
       {
           return;
       }
        CStorageChangeMoneyDialog dialog = new CStorageChangeMoneyDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addSaleQuoteForm() {
        if (!this.checkSalePermission())
        {
            return;
        }
        CSaleQuoteDialog dialog = new CSaleQuoteDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addSaleDetailForm() {
        if (!this.checkSalePermission())
        {
            return;
        }
        CSaleDetailDialog dialog = new CSaleDetailDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addSaleChangeMoneyForm() {
        if (!this.checkSalePermission())
        {
            return;
        }
        CSaleChangeMoneyDialog dialog = new CSaleChangeMoneyDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addSaleOrderBackForm() {
        if (!this.checkSalePermission())
        {
            return;
        }
        CSaleOrderBackDialog dialog = new CSaleOrderBackDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addSaleOrderForm() {
        if (!this.checkSalePermission())
        {
            return;
        }
        CSaleOrderDialog dialog = new CSaleOrderDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);

    }

    public void addPurchaseOrderForm() {
        if (!this.checkPurchasePermission())
        {
            return;
        }
        CPurchaseIndentDialog dialog = new CPurchaseIndentDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addPurchaseDetailForm() {
        if (!this.checkPurchasePermission())
        {
            return;
        }
        CPurchaseOrderDialog dialog = new CPurchaseOrderDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);

    }

    public void addPurchasePaymentForm() {
        if (!this.checkPurchasePermission())
        {
            return;
        }
        CPayableClearingDialog dialog = new CPayableClearingDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addPurchaseOrderBackForm() {
        if (!this.checkPurchasePermission())
        {
            return;
        }

        CPurchaseOrderBackDialog dialog = new CPurchaseOrderBackDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addPrereceivableForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }
        CPreReceivableDialog dialog = new CPreReceivableDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addReceivableClearingForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }
        CReceivableClearingDialog dialog = new CReceivableClearingDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addPrepayableForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }
        CPrePayableDialog dialog = new CPrePayableDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addPayableClearingForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }
        CPayableClearingDialog dialog = new CPayableClearingDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addBussinessExpenseForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }
        CBussinessExpenseDialog dialog = new CBussinessExpenseDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addCommonExpenseForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }
        CCommonExpenseDialog dialog = new CCommonExpenseDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addOtherIncomeForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }
        COtherIncomeDialog dialog = new COtherIncomeDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addBankCashForm() {
        
       if (!this.checkFinancePermission())
       {
           return;
       }
        CBankCashDialog dialog = new CBankCashDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addBeginStorageForm() {
        if (!this.checkStoragePermission())
       {
           return;
       }
        CBeginStorageDialog dialog = new CBeginStorageDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addBeginSubjectForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }

        CBeginSubjectDialog dialog = new CBeginSubjectDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addBeginReceiveableForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }

        CBeginReceivableDialog dialog = new CBeginReceivableDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addBeginPaymentForm() {
        if (!this.checkFinancePermission())
       {
           return;
       }

        CBeginPaymentDialog dialog = new CBeginPaymentDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addStorageLossForm() {
        if (!this.checkStoragePermission())
       {
           return;
       }
        CStorageLossDialog dialog = new CStorageLossDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    public void addStorageMaxMinForm() {
        if (!this.checkStoragePermission())
       {
           return;
       }
        CStorageMinMaxDialog dialog = new CStorageMinMaxDialog(this, getStorageDbSupport());
        dialog.newDataRow();
        dialog.setVisible(true);
    }

    private void initHitHelpInfos()
    {
        productImageItem.setHitInfo("在使用某种商品之前需要在“商品信息”中添加该商品，一旦添加一直可以使用。您可以在");
         supplierImageItem.setHitInfo("供应商信息主要用在采购和付款的客户栏中，在相关业务发生前添加。");
        customerImageItem.setHitInfo("客户信息主要用在销售和收款的客户栏中，在相关业务发生前添加。");
        employeeImageItem.setHitInfo("员工信息主要用在各种业务发生时的经手人项。");
        storageImageItem.setHitInfo("仓库信息是进销存的基础，请在启用本系统的时候添加，也可以在以后修改。");
        subjectImageItem.setHitInfo("会计科目是用来设置企业的基础会计科目的模块");
    //salesReportImageItem.setHitInfo("待添加...");
    //storageReportImageItem.setHitInfo("待添加...");
    //purchaseReportImageItem.setHitInfo("待添加...");
    //bankCashReportImageItem.setHitInfo("待添加...");
    //clearingImageItem.setHitInfo("待添加...");
    //initReportImageItem.setHitInfo("待添加...");
    mBussinessExpenseImageItem.setHitInfo("该项费用与一般费用的差别在于此项费用涉及到客户，一般费用不记录客户。");// = new CMenuImageItem("缁??璐圭?");
    mBussinessExpenseImageItemList.setHitInfo("对经营费用单的查询，列表方式显示。");;
    mCommonExpenseImageItem.setHitInfo("日常的一些开支记录。");// = new CMenuItem("涓??璐圭?");
    mOtherIncomeImageItem.setHitInfo("非进销存方式带来的收入。");//= new CMenuItem("?跺??跺?");
    mBankCashImageItem.setHitInfo("维护您的现金及银行帐户，可以在现金和银行帐户间转帐。");
    fStorageBeginManageImageItem.setHitInfo("当期库存商品的期初数，如果已经存在该商品则不能再做期初，您可以通过盘点来改变数量。");
    bSubjectBeginManageImageItem.setHitInfo("待添加...");// = new CImageItem("绉??浣?????绠＄?");
    bReceivableBeginMangeImageItem.setHitInfo("当前客户中的应收款期初。");// = new CImageItem("搴?????绠＄?");
    bPaymentBeginManageImageItem.setHitInfo("当前供应商中的应付款期初。");// = new CImageItem("搴?????绠＄?");
   
    //purchase
    pOrderImageItem.setHitInfo("采购订单是发起某种采购意向的单据，过帐并不会改变库存和资金，它可以转成采购单。");// = new CMenuItem("??喘璁㈠?");
    pPurchaseImageItem.setHitInfo("采购单是向供应商真实的采购信息，可以导致库存和资金的改变。可以直接付款，也可以通过付款单付款。");// = new CMenuItem("??喘??);
    pPaymentImageItem.setHitInfo("付款单是清掉应付款或者向供应商付款的一种方式。");
    //pOrderChangedImageItem.setHitInfo("待添加...");// = new CMenuItem("璁㈠????");
    //pOrderChangedImageItem.setHitInfo("待添加...");
    pOrderBackImageItem.setHitInfo("采购退货会导致库存和应收款发生变化。");// = new CMenuItem("??喘??揣");

    //sale
    sQuoteImageItem.setHitInfo("商品报价单可以转成销售订单。");// = new CMenuItem("????ヤ环");
    sOrderImageItem.setHitInfo("销售订单可以转成销售单。");// = new CMenuItem("???璁㈠?");
    sSaleListImageItem.setHitInfo("待添加...");// = new CMenuItem("???娓??");
    sGartheringImageItem.setHitInfo("销售单过帐会引起库存和应收款变化，您可以直接在销售单中付款，也可以通过付款单付款");// = new CMenuItem("?舵?缁??");
    sAjustPriceImageItem.setHitInfo("商品调价单是改变商品的价格部分的基础信息");// = new CMenuItem("???璋?环");
    
    //sOrderChangedImageItem.setHitInfo("待添加...");// = new CMenuItem("璁㈠????");
    //sOrderChangedImageItem.setHitInfo("待添加...");
    sOrderBackImageItem.setHitInfo("商品退货单来处理客户的退货商品。会改变库存，发生应付");// = new CMenuItem("?????揣");
    
    //
    mPreReceivableImageItem.setHitInfo("销售预收单是预先收客户订金的单据。客户订金会记到客户的帐上去。");// = new CMenuItem("???棰??绠＄?");
    
    mReceivableClearingImageItem.setHitInfo("收款单用来清应付和为客户付款。");// = new CMenuItem("?舵?缁??绠＄?");
    
    mPrePayableImageItem.setHitInfo("采购预付单用来给供应商付订金，将记到供应商帐上。");// = new CMenuItem("??喘棰??绠＄?");
   
    mPayableClearingImageItem.setHitInfo("付款单是清掉应付款或者向供应商付款的一种方式。");// = new CMenuItem("浠??缁??绠＄?");
    
    // warehouse
    wStorageInImageItem.setHitInfo("库存的进库管理，一种非采购方式入库的途径。如样品进库，赠品进库。");
    wStorageOutImageItem.setHitInfo("库存的出库管理，一种非销售方式出库的途径。如出样，赠送等。");
    wStorageOutflowImageItem.setHitInfo("用于调整实际库存与系统帐之间的差额，当实存库存数量大于帐存数量时使用报溢单");
    wStorageLossImageItem.setHitInfo("用于调整实际库存与系统帐之间的差额，当实存数量小于帐存数量时使用报损单");
    
    wStorageCheckImageItem.setHitInfo("库存商品盘点主要指对库存商品进行定期或不定期的清查。通过盘点，可清查商品数量有无损耗和溢余。");
    wStorageMoveImageItem.setHitInfo("移库单用于本企业商品内部的流转，只改变存放位置，不改变金额。");
    wStorageChangeMoneyImageItem.setHitInfo("当库存单价与实际的单价不一样进时，可以通过此单据强行调整。");
    wStorageMaxMinImageItem.setHitInfo("此单用于对商品库存的上下限调整，便于库存报警查询。");
    // produce

    // system
    sCompanyInfoImageItem.setHitInfo("记录本公司信息，用来生成报表时用。");
    sUserImageItem.setHitInfo("管理系统的用户，可以设置用户的角色，以限制用户的权限。");
    //sLogImageItem.setHitInfo("待添加...");
    //sNumberImageItem.setHitInfo("待添加...");
    //ddd
    productImageItem.addHitListener(this);
    supplierImageItem.addHitListener(this);
    customerImageItem.addHitListener(this);
    employeeImageItem.addHitListener(this);
    storageImageItem.addHitListener(this);
    subjectImageItem.addHitListener(this);
    //salesReportImageItem.addHitListener(this);
    //storageReportImageItem.addHitListener(this);
    //purchaseReportImageItem.addHitListener(this);
    //bankCashReportImageItem.addHitListener(this);
    //clearingImageItem.addHitListener(this);
    //initReportImageItem.addHitListener(this);
    mBussinessExpenseImageItem.addHitListener(this);// = new CMenuImageItem("缁??璐圭?");
    mCommonExpenseImageItem.addHitListener(this);// = new CMenuItem("涓??璐圭?");
    mOtherIncomeImageItem.addHitListener(this);//= new CMenuItem("?跺??跺?");
    mBankCashImageItem.addHitListener(this);// = new CMenuItem("?惰?甯??");
    fStorageBeginManageImageItem.addHitListener(this);
    bSubjectBeginManageImageItem.addHitListener(this);// = new CImageItem("绉??浣?????绠＄?");
    bReceivableBeginMangeImageItem.addHitListener(this);// = new CImageItem("搴?????绠＄?");
    bPaymentBeginManageImageItem.addHitListener(this);// = new CImageItem("搴?????绠＄?");
    //purchase
    pOrderImageItem.addHitListener(this);// = new CMenuItem("??喘璁㈠?");
    pPurchaseImageItem.addHitListener(this);// = new CMenuItem("??喘??);
    pPaymentImageItem.addHitListener(this);// = new CMenuItem("浠??缁??");
    //pOrderChangedImageItem.addHitListener(this);// = new CMenuItem("璁㈠????");
    //pOrderChangedImageItem.addHitListener(this);
    pOrderBackImageItem.addHitListener(this);// = new CMenuItem("??喘??揣");

    //sale
    sQuoteImageItem.addHitListener(this);// = new CMenuItem("????ヤ环");
    sOrderImageItem.addHitListener(this);// = new CMenuItem("???璁㈠?");
    sSaleListImageItem.addHitListener(this);// = new CMenuItem("???娓??");
    sGartheringImageItem.addHitListener(this);// = new CMenuItem("?舵?缁??");
    sAjustPriceImageItem.addHitListener(this);// = new CMenuItem("???璋?环");
    //sOrderChangedImageItem.addHitListener(this);// = new CMenuItem("璁㈠????");
    //sOrderChangedImageItem.addHitListener(this);
    sOrderBackImageItem.addHitListener(this);// = new CMenuItem("?????揣");
    //
    mPreReceivableImageItem.addHitListener(this);// = new CMenuItem("???棰??绠＄?");
    mReceivableClearingImageItem.addHitListener(this);// = new CMenuItem("?舵?缁??绠＄?");
    mPrePayableImageItem.addHitListener(this);// = new CMenuItem("??喘棰??绠＄?");
    mPayableClearingImageItem.addHitListener(this);// = new CMenuItem("浠??缁??绠＄?");
    // warehouse
    wStorageInImageItem.addHitListener(this);
    wStorageOutImageItem.addHitListener(this);
    wStorageOutflowImageItem.addHitListener(this);
    wStorageLossImageItem.addHitListener(this);
    wStorageCheckImageItem.addHitListener(this);
    wStorageMoveImageItem.addHitListener(this);
    wStorageChangeMoneyImageItem.addHitListener(this);
    wStorageMaxMinImageItem.addHitListener(this);

    // produce

    // system
    sCompanyInfoImageItem.addHitListener(this);
    sUserImageItem.addHitListener(this);
    //sLogImageItem.addHitListener(this);
    //sNumberImageItem.addHitListener(this);
    }

    public void helpInfoChanged(HelpEvent evt) {
        if (evt.getHelpInfo() != null)
        {
            this.helpPane.setHelpInfo(evt.getHelpInfo());
        }
    }

    public boolean checkStoragePermission()
    {
        if (user.getStorageRole() != 1)
        {
            MessageBox.showErrorDialog(this, "对不起，您没有权限进行此操作，请与管理员联系!");
            return false;
        }
        return true;
    }

    public boolean checkPurchasePermission()
    {
        if (user.getPurchaseRole() != 1)
        {
            MessageBox.showErrorDialog(this, "对不起，您没有权限进行此操作，请与管理员联系!");
            return false;
        }
        return true;
    }

    public boolean checkSalePermission()
    {
        if (user.getSaleRole() != 1)
        {
            MessageBox.showErrorDialog(this, "对不起，您没有权限进行此操作，请与管理员联系!");
            return false;
        }
        return true;
    }

    public boolean checkFinancePermission()
    {
        if (user.getFinanceRole() != 1)
        {
            MessageBox.showErrorDialog(this, "对不起，您没有权限进行此操作，请与管理员联系!");
            return false;
        }
        return true;
    }

    public boolean checkCheckPermission()
    {
        if (user.getCheckPermission() != 1)
        {
            MessageBox.showErrorDialog(this, "对不起，您没有权限进行此操作，请与管理员联系!");
            return false;
        }
        return true;
    }

    public boolean checkPricePermission()
    {
        if (user.getPricePermission() != 1)
        {
            MessageBox.showErrorDialog(this, "对不起，您没有权限查看价格信息，请与管理员联系!");
            return false;
        }
        return true;
    }

    public void showHelpManual()
    {
        try {
            String helpFile = Main.getInstallPath() +"/help.chm";
            Runtime.getRuntime().exec("hh.exe " + helpFile);
        } catch (Exception ex) {

        }
    }
    
}
