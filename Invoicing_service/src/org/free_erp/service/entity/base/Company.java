/* * Copyright 2013, TengJianfa , and other individual contributors as indicated * by the @authors tag. See the copyright.txt in the distribution for a * full listing of individual contributors. * * This is free software; you can redistribute it and/or modify it * under the terms of the GNU Lesser General Public License as * published by the Free Software Foundation; either version 2.1 of * the License, or (at your option) any later version. * * This software is distributed in the hope that it will be useful, * but WITHOUT ANY WARRANTY; without even the implied warranty of * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU * Lesser General Public License for more details. * * You should have received a copy of the GNU Lesser General Public * License along with this software; if not, write to the Free * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA * 02110-1301 USA, or see the FSF site: http://www.fsf.org. */package org.free_erp.service.entity.base;import java.awt.Image;import java.io.IOException;import java.sql.Blob;import java.sql.SQLException;import java.util.Date;import javax.imageio.ImageIO;import org.free_erp.service.entity.ErpObject;/** * Company entity. *  * @author TengJianfa mobile: 086-13003311398 */public class Company extends ErpObject implements java.io.Serializable{	// Fields    private static final long serialVersionUID = 1l;	private int id;	private String name;	private String ceo;	private String trade;	private String contact;	private String email;	private String phone;	private String mobile;	private String fax;	private String address;	private String post;	private String website;    //private Blob logoImage;	private String comments;    private Blob blobImage;    private Image image;    private String cdKey;    private String bank;	private String bankAccount;    private Integer status = 0;    	// Constructors	/** default constructor */	public Company()	{        this.createDate = new Date();	}    public String getBank() {        return bank;    }    public void setBank(String bank) {        this.bank = bank;    }    public String getBankAccount() {        return bankAccount;    }    public void setBankAccount(String bankAccount) {        this.bankAccount = bankAccount;    }    public String getCdKey() {        return cdKey;    }    public void setCdKey(String cdKey) {        this.cdKey = cdKey;    }	public String getName()	{		return name;	}	public void setName(String name)	{		this.name = name;	}	public String getCeo()	{		return this.ceo;	}	public void setCeo(String ceo)	{		this.ceo = ceo;	}	public String getTrade()	{		return this.trade;	}	public void setTrade(String trade)	{		this.trade = trade;	}	public String getContact()	{		return this.contact;	}	public void setContact(String contact)	{		this.contact = contact;	}	public String getEmail()	{		return this.email;	}	public void setEmail(String email)	{		this.email = email;	}	public String getPhone()	{		return this.phone;	}	public void setPhone(String phone)	{		this.phone = phone;	}	public String getMobile()	{		return this.mobile;	}	public void setMobile(String mobile)	{		this.mobile = mobile;	}	public String getFax()	{		return this.fax;	}	public void setFax(String fax)	{		this.fax = fax;	}	public String getAddress()	{		return this.address;	}	public void setAddress(String address)	{		this.address = address;	}	public String getPost()	{		return this.post;	}	public void setPost(String post)	{		this.post = post;	}	public String getWebsite()	{		return this.website;	}	public void setWebsite(String website)	{		this.website = website;	}	public String getComments()	{		return this.comments;	}	public void setComments(String comments)	{		this.comments = comments;	}	public int getId()	{        return id;	}	public void setId(int id)	{		this.id = id;	}		@Override	public boolean equals(Object obj)	{		// TODO Auto-generated method stub		if (obj instanceof Company)		{			return ((Company)obj).getId() == this.id;		}		return false;	}	@Override	public int hashCode()	{		// TODO Auto-generated method stub		return Integer.valueOf(id).hashCode();	}	@Override	public String toString()	{		return this.name;	}    public Blob getBlobImage() {        return this.blobImage;//        if (logoImage != null)//        {//            try//            {//                OutputStream out = new ByteArrayOutputStream();//                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);//                encoder.encode((BufferedImage)logoImage);//                byte[] bytes = new byte[1024000];//                out.write(bytes);//                InputStream in = new ByteArrayInputStream(bytes);//                Blob blob = Hibernate.createBlob(in);//                in.close();//                return blob;//            }//            catch(Exception ex)//            {//                ex.printStackTrace();//                return null;//            }//        }//        return null;    }    public void setBlobImage(Blob blob) {        this.blobImage = blob;            }        public byte[] getImageBuffer() {        return imageBuffer;    }    public void setImageBuffer(byte[] imageBuffer) {        this.imageBuffer = imageBuffer;//        if (imageBuffer != null)//        {//            this.blobImage = Hibernate.createBlob(imageBuffer);//        }    }    public void createImage() throws IOException, SQLException    {        if (this.blobImage != null)        {            image = ImageIO.read(blobImage.getBinaryStream());        }    }        public Image getImage()    {        return this.image;    }    public Integer getStatus() {        return status;    }    public void setStatus(Integer status) {        this.status = status;    }    private byte[] imageBuffer;}