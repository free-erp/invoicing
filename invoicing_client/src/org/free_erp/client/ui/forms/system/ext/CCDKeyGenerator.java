/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.client.ui.forms.system.ext;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CField;
import com.jdatabeans.util.MessageBox;

import org.free_erp.client.ui.main.Main;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author afa
 */
public class CCDKeyGenerator extends JDialog implements ActionListener
{
    private CField companyName = new CField();
    private CField cdKey = new CField();
    private CButton okButton = new CButton("生成(&O)");
    private CButton cancelButton = new CButton("取消(&C)");
    public CCDKeyGenerator(Frame frame)
    {
        super(frame);
        initComp();
        this.setBounds(100, 100, 350, 180);//, WIDTH, WIDTH, WIDTH);
    }

    private void initComp()
    {
        this.getContentPane().setLayout(null);
		this.setTitle("请输入注册信息:");
		this.setSize(350, 180);
        int x = 100;
        int y = 40;
        this.getContentPane().add(companyName);
        companyName.setBounds(x, y, 200, 22);
        JLabel l = new JLabel("公司名称:");
        this.getContentPane().add(l);
        l.setBounds(x - 60, y, 200, 22);

        //this.addDisplayText("请输入公司名称，全名!", 10, 10, 200, 22);
        //this.addComponent(companyName, , "公司名称", 60);
        y += 30;
        this.getContentPane().add(cdKey);
        cdKey.setBounds(x, y, 200, 22);
        l = new JLabel("注册码:");
        this.getContentPane().add(l);
        l.setBounds(x - 60, y, 200, 22);
        //this.addComponent(cdKey, x, y, 200, 22, "注册码", 60);
        y += 30;
        this.getContentPane().add(okButton);
        okButton.setBounds(x, y, 80, 22);

        this.getContentPane().add(cancelButton);
        cancelButton.setBounds(x + 100, y, 80, 22);
        //this.addComponent(okButton, x, y, 80, 22);
        //this.addComponent(cancelButton, x + 100, y, 80, 22);
       
        this.addWindowListener(new WindowAdapter()
        {

            public void windowClosing(WindowEvent e) {
                
                    System.exit(0);
                
            }

        });
        this.okButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
//        this.setDefaultButton(okButton);
//        this.setCancelButton(cancelButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.okButton)
        {
            checkReg();

        }
        else if (e.getSource() == this.cancelButton)
        {
            System.exit(0);
        }
    }

//    public String generateCDKey(String companyName)
//    {
//        final String adjustCode = "thereisapigheadintheworld!" ;
//        if (companyName.length() < 4)
//        {
//            return null;
//        }
//        String text = "";
//        for(int i = 0; i < companyName.length(); i++)
//        {
//            int v = companyName.charAt(i);
//            if (v > 128)
//            {
//                text += String.valueOf(v % 100);
//            }
//            else
//            {
//                text += String.valueOf(v);
//            }
//        }
//        int l = text.length();
//        if (l < 20)
//        {
//            text += "andshelovedmonkeykingbutthekingdoesntloveher";
//        }
//        else if (l > 20)
//        {
//            text = text.substring(l - 20);
//        }
//        String cdKeyString = "";
//        int i = 0;
//        while(cdKeyString.length() < 19)
//        {
//            int temp = text.charAt(i) + adjustCode.charAt(i);
//            char a = (char)(temp % 25 + 'A');
//            cdKeyString += a;
//            if ((i + 1) % 4 == 0 && cdKeyString.length() < 19)
//            {
//                cdKeyString += "-";
//            }
//            i++;
//        }
//       return cdKeyString;
//    }
//    //final String[] keys = new String[]{'中', '国', '江', '苏', };
//    private String filterString(String s)
//    {
//
//    }

    private void checkReg()
    {
        String name = this.companyName.getText();
        if (name.length() < 6)
        {
            MessageBox.showErrorDialog(this, "公司名称长度太短，至少6个字");
        }
        this.cdKey.setText(generateCDKey(name));
    }

    public String generateCDKey(String companyName)
    {
        final String adjustCode = "thereisapigheadintheworld!" ;
        if (companyName.length() < 4)
        {
            return null;
        }
        String text = "";
        for(int i = 0; i < companyName.length(); i++)
        {
            int v = companyName.charAt(i);
            if (v > 128)
            {
                text += String.valueOf(v % 100);
            }
            else
            {
                text += String.valueOf(v);
            }
        }
        int l = text.length();
        if (l < 20)
        {
            text += "andshelovedmonkeykingbutthekingdoesntloveher";
        }
        else if (l > 20)
        {
            text = text.substring(l - 20);
        }
        String cdKeyString = "";
        int i = 0;
        while(cdKeyString.length() < 19)
        {
            int temp = text.charAt(i) + adjustCode.charAt(i);
            char a = (char)(temp % 25 + 'A');
            cdKeyString += a;
            if ((i + 1) % 4 == 0 && cdKeyString.length() < 19)
            {
                cdKeyString += "-";
            }
            i++;
        }
       return cdKeyString;
    }

    public static void main(String args[])
    {
        new CCDKeyGenerator(null).setVisible(true);
    }

}
