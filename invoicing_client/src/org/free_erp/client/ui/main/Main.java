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
package org.free_erp.client.ui.main;

import java.awt.Font;
import javax.swing.UIManager;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.core.SplashWindow;
import org.free_erp.client.ui.util.ApplicationConfig;
import org.free_erp.client.ui.util.PathUtilities;
import org.free_erp.service.clientservice.IServiceManager;
import org.free_erp.service.clientservice.LocalServiceManager;
import org.free_erp.client.ui.util.SystemConfig;
import org.free_erp.service.entity.base.Company;
import java.awt.Color;

/**
 * @author TengJianfa
 */
public class Main {

    public static int systemType = 3; //
    private static final int MAIN_SYS_SINGLE = 0;
    private static final int MAIN_SYS_NET = 1;
    private static final int STOCK_SYS_SINGLE = 3;
    private static final int STOCK_SYS_NET = 4;

    private static ApplicationConfig config;
    private static MainFrame mainForm;
    private static Company company;
    private static IServiceManager serviceManager;
    private static final int service_style = 2;
    public static final int WEBSERVICE_STYLE = 1;
    public static final int LOCAL_SERVICE_STYLE = 2;
    private static String installPath;
    private static SystemConfig systemConfig;
    private static int logUpdateTime = 20000;

    public static int getLogUpdateTime() {
        return logUpdateTime;
    }

    public static void setLogUpdateTime(int time) {
        logUpdateTime = time;
    }

    public static MainFrame getMainFrame() {
        if (mainForm == null) {
            mainForm = new MainFrame();
        }
        return mainForm;
    }

    public static String getInstallPath() {
        if (installPath == null) {
            installPath = new PathUtilities().getInstallPath();
        }
        //System.out.println("path:" + installPath);
        return installPath;
    }

    public static ApplicationConfig getApplicationConfig() {
        if (config == null) {
            config = new ApplicationConfig();
        }
        return config;
    }

    public static boolean isWebServiceStyle() {
        ApplicationConfig config = getApplicationConfig();
        String p = config.getProperty("service-style");
        if (p != null) {
            return Integer.valueOf(p) == WEBSERVICE_STYLE;
        }
        return false;//service_style == WEBSERVICE_STYLE;
    }

    public static IServiceManager getServiceManager() {
        if (serviceManager == null) {
//			if (service_style == WEBSERVICE_STYLE)
//			{
//				serviceManager = new WebServiceManager();	//to changed 需要添加一个配置文件
//			} else
//			{
            serviceManager = new LocalServiceManager(getInstallPath(), PathUtilities.getArgString());	//web service版本请将这一行注掉
            //}
        }
        return serviceManager;
    }

    private static void installDefaults() {
        UIManager.getDefaults().put("menu.font", new Font("宋体", Font.PLAIN, 12));
        UIManager.getDefaults().put("required.background", new Color(255, 255, 204));
        UIManager.getDefaults().put("Table.gridColor", new Color(103, 103, 103));
        UIManager.getDefaults().put("Tree.hash", new Color(103, 103, 103));
        try {
            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setCompany(Company p) {
        company = p;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        installDefaults();
        SplashWindow w = new SplashWindow();
        w.setVisible(true);
        try {
            getServiceManager();//启动时候加载的内容
        } catch (Exception ex) {
            MessageBox.showErrorDialog(w, "服务器没有开启,或网路不通!----原始错误:" + ex.getMessage());
            ex.printStackTrace();
            System.exit(-1);
        }
        systemConfig = new SystemConfig();
        systemConfig.initSystemConfig();
        mainForm = getMainFrame();

//                CLoginDialog dialog = new CLoginDialog(getMainFrame());
//                w.dispose();//setVisible(false);
//                dialog.setVisible(true);
//                if (dialog.isPassed())
//                {
//                    company = dialog.getCompany();
//                }
//                else
//                {
//                    System.exit(0);
//                    return;
//                }
//                mainForm.setCompany(company);
//                mainForm.setUser(dialog.getUser());
        w.dispose();
        mainForm.setVisible(true);
        /*  Runnable app = new Runnable()
         {
         public void run()
         {
         installDefaults();
         SplashWindow w = new SplashWindow();
         w.setVisible(true);
         try
         {
         getServiceManager();//启动时候加载的内容
         } catch (Exception ex)
         {
         MessageBox.showErrorDialog(w, "服务器没有开启,或网路不通!----原始错误:" + ex.getMessage());
         System.exit(-1);
			
         }
         systemConfig = new SystemConfig();
         systemConfig.initSystemConfig();
         mainFrame = getMainFrame();
         w.dispose();//setVisible(false);
         mainFrame.setVisible(true);                
         }
         };
			
         new Thread(new ApplicationLoader(), app).start();
         */

    }

    public static SystemConfig getSystemConfig() {
        return systemConfig;
    }
}
