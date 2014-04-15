package com.yizhilu.os.ssicore.dao;

import com.yizhilu.os.ssicore.domain.PageQuery;
import com.yizhilu.os.ssicore.domain.PageResult;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;

/**
 * 简单crud操作共用dao接口
 * User: guoqiang.liu
 * Date: 2008-11-29
 * Time: 0:44:25
 * To change this template use File | Settings | File Templates.
 */
public interface ISimpleDao { 
    public static String BATCH_EXECUTE_METHOD_TYPE_INSERT = "insert";
    public static String BATCH_EXECUTE_METHOD_TYPE_DELETE = "delete";
    public static String BATCH_EXECUTE_METHOD_TYPE_UPDATE = "update";

    public static int BATCH_EXECUTE_DEFAULT_COUNT = 200;

    public <T> T createEntity(String xmlId,Object parObj);

    public <K,E> Map<K,E> getForMap(String xmlId,Object parObj,String mapKey);

    public <T> List<T> getForList(String xmlId,Object parObj);
    
    public <T> List<T> getForList(String xmlId);
    
    public <T> T getEntity(String xmlId,Object parObj);

    public void updateEntity(String xmlId,Object parObj);

    /**
     * 执行修改业务,并返回执行结果
     *
     * @param xmlId
     * @param parObj
     * @return 返回执行结果状态影响的行数
     */
    public int update(String xmlId,Object parObj);

    public void deleteEntity(String xmlId,Object parObj);
    
    /**
     * 执行删除业务,并返回执行结果
     * 
     * @param xmlId
     * @param parObj
     * @return 返回执行结果状态影响的行数
     */
    public int delete(String xmlId,Object parObj);
    

    public PageResult getPageResult(String xmlId,String countXmlId, PageQuery pageQuery);

    public void batchExecuteSingleSql(String xmlId,List parList,String methodType,int countToBeExecute);
    
    public List createBatchEntity(String xmlId,List objList) throws SQLException;
}
