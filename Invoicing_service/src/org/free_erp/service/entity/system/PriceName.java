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
package org.free_erp.service.entity.system;

import org.free_erp.service.entity.base.Company;

/**
 *
 * @author TengJianfa 13003311398
 */
public class PriceName implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
    private int id;
    private Company company;
    private String priceName0;
    private String priceName1;
    private String priceName2;
    private String priceName3;
    private String priceName4;
    private String priceName5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getPriceName0() {
        return priceName0;
    }

    public void setPriceName0(String priceName0) {
        this.priceName0 = priceName0;
    }

    public String getPriceName1() {
        return priceName1;
    }

    public void setPriceName1(String priceName1) {
        this.priceName1 = priceName1;
    }

    public String getPriceName2() {
        return priceName2;
    }

    public void setPriceName2(String priceName2) {
        this.priceName2 = priceName2;
    }

    public String getPriceName3() {
        return priceName3;
    }

    public void setPriceName3(String priceName3) {
        this.priceName3 = priceName3;
    }

    public String getPriceName4() {
        return priceName4;
    }

    public void setPriceName4(String priceName4) {
        this.priceName4 = priceName4;
    }

    public String getPriceName5() {
        return priceName5;
    }

    public void setPriceName5(String priceName5) {
        this.priceName5 = priceName5;
    }
    
}
