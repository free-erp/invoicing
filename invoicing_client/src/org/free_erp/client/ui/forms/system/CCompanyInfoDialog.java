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
import com.jdatabeans.beans.CImageItem;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.Md5;
import com.jdatabeans.beans.CPagePane;
import com.jdatabeans.beans.ValueChangedEvent;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.logic.ICompanyService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @Changer liufei
 */

public class CCompanyInfoDialog extends CDialog implements ActionListener,ValueChangedListener
{
    	 //  private ICompanyDao dao;
    protected IDataSource dataSource;
    protected CImageItem logoField;
    //protected JDataField id;
	protected JDataField name;
	protected JDataField ceo;
	protected JDataField trade;
	protected JDataField contact;
	protected JDataField email;
	protected JDataField phone;
	protected JDataField mobile;
	protected JDataField fax;
	protected JDataField address;
	protected JDataField post;
	protected JDataField webSite;
	protected JDataField qq;
	protected JDataField msn;
    protected CButton changeLogoButton = new CButton("�ϴ�LogoͼƬ");
	//protected JButton save; 
	private Company company;
    ICompanyService companyService = Main.getServiceManager().getCompanyService();
    private CButton submitButton;
    private CButton cancelButton;
    private boolean addCompany = false;
    private boolean passed = false;
    public CCompanyInfoDialog(Frame parent)
    {
		super(parent);
		initComps();
    }
    
    public CCompanyInfoDialog(JDialog parent, Company company, boolean addCompany)
    {
		super(parent);
        this.company = company;
		initComps();
        this.addCompany = addCompany;
    }

    public CCompanyInfoDialog(JDialog parent, Company company)
    {
		super(parent);
        this.company = company;
		initComps();
        
    }

    public boolean isPassed()
    {
        return this.passed;
    }

    public void initComps()
    {
        if (company == null)
        {
            company = companyService.getCompany(Main.getMainFrame().getCompany().getId());
        }
        this.getContentPane().setLayout(new BorderLayout());
		this.setTitle("��˾��Ϣ");
		this.setBounds(0, 200, 660, 320);
                
		//JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new JPanel();
		p.setLayout(null);
		//p.setPreferredSize(new Dimension(640, 300));
        p.setPreferredSize(new Dimension(640, 300));
		this.getContentPane().add("Center", p);
                CPagePane panel = new CPagePane();
		p.setLayout(null);
                p.add(panel);
                panel.setBounds(10, 5, 630, 240);
        //id= new JDataField();
        //id.setText(String.valueOf(company.getId()));
		//id.setEditable(false);
        name= new JDataField();
        name.setRequired(true);
        //id.setRequired(true);
        name.setDisplayName("����");
        //id.setDisplayName("���");
        ceo= new JDataField();
        trade= new JDataField();
        contact= new JDataField();
        email= new JDataField();
        phone= new JDataField();
        mobile= new JDataField();
        fax= new JDataField();
        address= new JDataField();
        post= new JDataField();
        webSite= new JDataField();
        name.setMaxLength(30);
        ceo.setMaxLength(10);
        trade.setMaxLength(30);
        contact.setMaxLength(30);
        email.setMaxLength(60);
        phone.setMaxLength(20);
        mobile.setMaxLength(20);
        fax.setMaxLength(20);
        address.setMaxLength(30);
        post.setMaxLength(6);
        webSite.setMaxLength(30);

        this.logoField = new CImageItem();

        //id.addValueChangedListener(this);
        name.addValueChangedListener(this);
        ceo.addValueChangedListener(this);
        trade.addValueChangedListener(this);
        contact.addValueChangedListener(this);
        email.addValueChangedListener(this);
        phone.addValueChangedListener(this);
        mobile.addValueChangedListener(this);
        fax.addValueChangedListener(this);
        address.addValueChangedListener(this);
        post.addValueChangedListener(this);
        webSite.addValueChangedListener(this);

          
        qq= new JDataField();
        msn= new JDataField();

        ceo.setText(company.getCeo());
        name.setText(company.getName());
        trade.setText(company.getTrade());
        contact.setText(company.getContact());
        email.setText(company.getEmail());
        phone.setText(company.getPhone());
        mobile.setText(company.getMobile());
        fax.setText(company.getFax());
        address.setText(company.getAddress());
        post.setText(company.getPost());
        webSite.setText(company.getWebsite());
        if (company.getImage() != null)
        {
            this.logoField.setImage(company.getImage().getScaledInstance(32, 32, Image.SCALE_REPLICATE));
            ////temp
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                ImageIO.write((RenderedImage) company.getImage(), "png", bos);
                this.logoField.setImageBuffer(bos.toByteArray());
            } catch (IOException ex) {
                Logger.getLogger(CCompanyInfoDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            ////
        }
        submitButton = new CButton("ȷ��(&O)");
		cancelButton=new CButton("ȡ��(&C)");
        this.submitButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
        int x = 100;
        int y = 30;
                
        //panel.addComponent(id, x, y, 100, 20, "���", 60);
		//panel.addComponent(name, x + 170, y, 280, 20, "��˾", 60);
        panel.addComponent(name, x, y, 450, 20, "��˾", 60);
        //panel.addComponent(this.logoField, x + 370, y, 48, 48);
        y += ROW_SPAN;
		panel.addComponent(ceo, x, y, 100, 20, "�ܾ���", 60);
		panel.addComponent(trade, x + 170, y, 280, 20, "��˾��ҵ", 60);
        
		y += ROW_SPAN;
		panel.addComponent(contact, x, y, 100, 20, "��ϵ��", 60);
		panel.addComponent(email, x + 170, y, 280, 20, "E-mail", 60);
        //panel.addComponent(this.changeLogoButton, x + 340, y, 120, 22);

		y += ROW_SPAN;
		panel.addComponent(phone, x, y, 100, 20, "�绰", 60);
		panel.addComponent(mobile, x + 170, y, 120, 20, "�ֻ�", 60);
		panel.addComponent(fax, x + 350, y, 100, 20, "����", 40);
		y += ROW_SPAN;
		panel.addComponent(post, x, y, 100, 20, "�ʱ�", 60);
		panel.addComponent(webSite, x + 170, y, 280, 20, "��ַ", 60);
		y += ROW_SPAN;
		panel.addComponent(address, x, y, 450, 20, "��ַ", 60);
		
        p.add(this.submitButton);
        this.submitButton.setBounds(400, 260, 80, 25);
        p.add(this.cancelButton);
        this.cancelButton.setBounds(490, 260, 80, 25);
        this.setDefaultButton(submitButton);
		this.setCancelButton(cancelButton);
        changeLogoButton.addActionListener(this);
        if (company.getCdKey() != null&&!company.getCdKey().equals(""))
        {
            this.name.setEditable(false);
        }
    } 
  
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource() == submitButton)
      {
          //company.setId(Integer.valueOf(this.id.getText()));
          company.setName( name.getText());
	      company.setCeo(ceo.getText());
	      company.setTrade( trade.getText());
	      company.setContact(contact.getText());
	      company.setEmail(email.getText());
	      company.setPhone(phone.getText());
          company.setMobile(mobile.getText());
	      company.setFax(fax.getText());
          company.setAddress(address.getText());
	      company.setPost(post.getText());
          company.setWebsite(webSite.getText());
          byte[] imageBuffer = logoField.getImageBuffer();
          if (imageBuffer != null)
          {
              try
              {
                  InputStream stream = new ByteArrayInputStream(imageBuffer);
                  //company.setBlobImage(Hibernate.createBlob(stream));
              }
              catch(Exception ex)
              {
                  ex.printStackTrace();
              }
          }
//          company.setImageBuffer(logoField.getImageBuffer());
          if (this.addCompany)
          {
                User user = new User();
                 user.setUserName("admin");
                 String password = "admin";
                 password = Md5.md5Encode(password);
                 user.setPassword(password);
                 user.setName("��������Ա");
                 user.setCompany(company);
                 //Main.getServiceManager().getPermissionsService().savePermission(PermissionUtil.setPermission(purview, user));
                 if (!companyService.saveCompany(company))
                 {
                     JOptionPane.showMessageDialog(this, "�޸�ʧ�ܣ�");
                     return;
                 }
                 Main.getServiceManager().getSystemManageService().addCompanyAccount(company, user);
                 Main.getServiceManager().getSystemManageService().saveUser(user);
                 this.setVisible(false);
          }
          else if (companyService.saveCompany(company))
		   {

               this.setVisible(false);

               //this.dispose();
		   }
		   else 
		   {
		          JOptionPane.showMessageDialog(this, "�޸�ʧ�ܣ�");
		   }
           this.passed = true;

      }
      else if(e.getSource() ==cancelButton)
       {
           //this.dispose();
           this.setVisible(false);
       }
      else if (e.getSource() == this.changeLogoButton)
      {
          try
          {

            JFileChooser chooser = new JFileChooser();
            chooser.setApproveButtonText("ѡ��ͼƬ");
			chooser.setDialogTitle("ѡ��logoͼƬ");
            chooser.setFileFilter(new FileFilter()
            {
                    @Override
                    public boolean accept(File pathname) {
                        String fileName = pathname.toString().toLowerCase();
                        if (fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png") || pathname.isDirectory())
                        {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "ͼƬ�ļ�(*.gif, *.jpg, *.png";
                    }


            });
            int result = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (result == JFileChooser.APPROVE_OPTION && file != null)
            {
                this.logoField.uploadImageFile(chooser.getSelectedFile().toString(), 32, 32);
            }
          }            
          catch(Exception ex)
          {
              MessageBox.showErrorDialog(this, "������:" + ex.getMessage());
          }
      }

    }

   public void valueChanged(ValueChangedEvent evt)
    {
//        if(evt.getSource() == this.id)
//        {
//            if(this.id.getText() != null)
//            {
//                try
//                {
//                    int number = Integer.valueOf(this.id.getText());
//                }
//                catch(Exception ex)
//                {
//                    MessageBox.showErrorDialog(this, "ֻ���������֣�");
//                    this.id.setText("");
//                }
//            }
//        }
        if(evt.getSource() == this.name)
        {
            if(this.name.getText() != null && this.name.getText().length() > 30)
            {
                MessageBox.showErrorDialog(this, "����˾�����Ȳ��ܳ���30�� ");
                this.name.setText("");
            }
        }
        if(evt.getSource() == this.ceo)
        {
            if(this.ceo.getText() != null && this.ceo.getText().length() > 10)
            {
                MessageBox.showErrorDialog(this, "���ܾ������Ȳ��ܳ���10�� ");
                this.ceo.setText("");
            }
        }
        if(evt.getSource() == this.trade)
        {
            if(this.trade.getText() != null && this.trade.getText().length() > 30)
            {
                MessageBox.showErrorDialog(this, "����˾��ҵ�����Ȳ��ܳ���30�� ");
                this.trade.setText("");
            }
        }
        if(evt.getSource() == this.contact)
        {
            if(this.contact.getText() != null && this.contact.getText().length() > 10)
            {
                MessageBox.showErrorDialog(this, "����ϵ�ˡ����Ȳ��ܳ���10�� ");
                this.contact.setText("");
            }
        }
        if(evt.getSource() == this.email)
        {
            if(this.email.getText() != null && this.email.getText().length() > 60)
            {
                MessageBox.showErrorDialog(this, "��E-mail�����Ȳ��ܳ���60�� ");
                this.email.setText("");
            }
        }
        if(evt.getSource() == this.phone)
        {
            if(this.phone.getText() != null && this.phone.getText().length() > 20)
            {
                MessageBox.showErrorDialog(this, "���绰�����Ȳ��ܳ���20�� ");
                this.phone.setText("");
            }
        }
        if(evt.getSource() == this.mobile)
        {
            if(this.mobile.getText() != null && this.mobile.getText().length() > 20)
            {
                MessageBox.showErrorDialog(this, "���ֻ������Ȳ��ܳ���20�� ");
                this.mobile.setText("");
            }
        }
        if(evt.getSource() == this.fax)
        {
            if(this.fax.getText() != null && this.fax.getText().length() > 20)
            {
                MessageBox.showErrorDialog(this, "�����桱���Ȳ��ܳ���20�� ");
                this.fax.setText("");
            }
        }
        if(evt.getSource() == this.address)
        {
            if(this.address.getText() != null && this.address.getText().length() > 60)
            {
                MessageBox.showErrorDialog(this, "����ַ�����Ȳ��ܳ���60�� ");
                this.address.setText("");
            }
        }
        if(evt.getSource() == this.post)
        {
            if(this.post.getText() != null && this.post.getText().length() > 6)
            {
                MessageBox.showErrorDialog(this, "���ʱࡱ���Ȳ��ܳ���6�� ");
                this.post.setText("");
            }
        }
        if(evt.getSource() == this.webSite)
        {
            if(this.webSite.getText() != null && this.webSite.getText().length() > 30)
            {
                MessageBox.showErrorDialog(this, "����ַ�����Ȳ��ܳ���30�� ");
                this.webSite.setText("");
            }
        }
    }
}

