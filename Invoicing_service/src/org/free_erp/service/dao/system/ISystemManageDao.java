package org.free_erp.service.dao.system;

import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.system.CreateNumber;
import org.free_erp.service.entity.system.NumberPrefix;
import org.free_erp.service.entity.system.PriceName;
import org.free_erp.service.entity.vo.SystemLogQueryVo;

/**
 *
 * Changer liufei
 */
public interface ISystemManageDao {

    public void saveUser(User user);

    public void upDateUser(User user);

    public User findUser(Company company, String userName);

    public User findUser(int userId);

    public void deleteUser(User user);

    public boolean isValidUser(Company company, String name, String password);

    public boolean isValidUser(int companyId, String name, String password);

    public boolean isExistedUser(int companyId, String name);

    public Company findCompany(int companyId);

    public List<User> findAllUser(Company company);//liufei

    public List<SystemLog> findSystemLogs(SystemLogQueryVo vo);//liufei

    public List<SystemLog> findSystemLogs(Company company);//liufei

    public void saveSystemLog(SystemLog systemLog);//liufei

    public void deleteSystemLogs(Company company);//liufei

    public List<SystemLog> findLatestLogs(int id);//afa

    public NumberPrefix getNumberPrefix(Company company);//liufei

    public List<NumberPrefix> getNumberPrefixs(Company company);//liufei

    public void saveNumberPrefix(NumberPrefix systemOption);//liufei

    public CreateNumber getAutoNumber(Company company);//liufei

    public List<CreateNumber> getAutoNumbers(Company company);//liufei

    public void saveAutoNumber(CreateNumber createNumber);//liufei

    public PriceName getPriceName(Company company);

    public List<PriceName> getPriceNames(Company company);
}
