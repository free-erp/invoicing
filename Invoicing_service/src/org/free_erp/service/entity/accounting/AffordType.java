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
package org.free_erp.service.entity.accounting;

import org.free_erp.service.entity.base.Company;

/**
 * AffordType entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class AffordType implements java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 1l;
	private String name;
	
	

	// Constructors

	/** default constructor */
	public AffordType() {
	}

	
	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        @Override
    public boolean equals(Object obj) {
         if (obj == null || !(obj instanceof AffordType))
        {
            return false;
        }
        return ((AffordType)obj).getId() == id;
    }

    @Override
    public String toString() {
        return name;
    }

	private int id;
    private Company company;

}