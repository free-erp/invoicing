package org.free_erp.service.constants;

public interface ServiceConstants
{
	int DEFAULT_PRODUCT_CATALOG = 999999999;
	int RESERVED_COMPANY = 9999999;
	int TEST_CVS = 10000;
    //storage
    int DRAFT_STATUS = 0;
    int FORMAL_STATUS = 1;
    int DISCARD_STATUS = 2;

    int UNFINISHED = 10;
    int FINISHED = 11;

    int ALL_STATUS = 99;

    String DRAFT_STRING = "�µ�";
    String FORMAL_STRING = "�ᵥ";
    String DISCARD_STRING = "����";
    String UNKNOWN = "δ����״̬";
    //default subject catalog id:
    int SUBJECT_CATALOG_BANK_CASH = 1;
    int SUBJECT_CATALOG_BUSSINESS_EXPENSE = 2;
    int SUBJECT_CATALOG_COMMON_EXPENSE = 3;
    int SUBJECT_CATALOG_OTHER_INCOME = 4;    

    //subject status
    int SUBJECT_RESERVED_STATUS = 1;
    int SUBJECT_COMMON_STATUS = 0;

    int SYSTEM_STATUS = 0;

    //�����ݿ�һ��
    /**
     * �ֽ�
     */
    int CLEAR_TYPE_CASH = 1;
    /**
     * ����
     */
    int CLEAR_TYPE_CREDIT = 2;
    /**
     * �ʿ�
     */
    int CLEAR_TYPE_ACCOUNT = 3;
    
}
