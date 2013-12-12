package org.free_erp.client.ui.core.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.main.Main;

public class ProductManageAction extends AbstractAction
{
	
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		MainFrame mainFrame = Main.getMainFrame();
		mainFrame.showProductManageWindow();
	}

}
