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
package org.free_erp.service.logic;

import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.system.CreateNumber;
import org.free_erp.service.entity.system.NumberPrefix;
import org.free_erp.service.entity.system.PriceName;
import org.free_erp.service.entity.vo.SystemLogQueryVo;
//@WebService(targetNamespace = "http://service.erp.jzh.com")
/**
 *
 *  Changer liufei
 */
public interface ISystemManageService
{
	public boolean isValidUser(Company company, String userName, String password);

	public boolean isValidUser(int companyId, String userName, String password);

    public boolean isExistedUser(int companyId, String name);

	public Company getCompany(int companyId);

	public User getUser(Company company, String userName);

	public String getTestString();

    public void saveUser(User user);

    public void upDateUser(User user);

    public List<User> findAllUser(Company company);//liufei

    public void saveSystemLog(SystemLog systemLog);//liufei

    public void deleteSystemLogs(Company company);//liufei

    public List<SystemLog> findLatestLogs(int id);//afa find the latest log from id

    public List<SystemLog> findSystemLogs(SystemLogQueryVo vo);//liufei

    public List<SystemLog> findSystemLogs(Company company);//liufei

    public NumberPrefix getNumberPrefix(Company company);//liufei

    public void saveNumberPrefix(NumberPrefix systemOption);//liufei

    public String getOptionType(Company company, int type);//liufei

    public String getAutoNumber(Company company, int type);//liufei

    public void saveAutoNumber(CreateNumber createNumber);//liufei

    public String getAutomaticNumber(Company company, int numType);//liufei

    public String getSubjectNumber(Company company, int numType, int catageID);//liufei

    public void addCompanyAccount(Company company,User user);//liufei

    public boolean checkRegInfo(Company company);

    public PriceName getPriceName(Company company);

    public List<PriceName> getPriceNames(Company company);

    public void savePriceName(PriceName priceName);
    public List<NumberPrefix> getNumberPrefixs(Company company);//liufei
    public List<CreateNumber> getAutoNumbers(Company company);//liufei
}
