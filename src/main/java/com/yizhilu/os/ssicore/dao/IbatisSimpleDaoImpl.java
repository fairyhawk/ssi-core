package com.yizhilu.os.ssicore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.yizhilu.os.ssicore.domain.PageQuery;
import com.yizhilu.os.ssicore.domain.PageResult;

/**
 * ibatis简单crud操作共用dao类 User: guoqiang.liu Date: 2008-11-28 Time: 23:52:47
 */
@SuppressWarnings({ "unchecked", "deprecation" })
public class IbatisSimpleDaoImpl extends SqlMapClientDaoSupport implements ISimpleDao {
    public <T> T createEntity(String xmlId, Object parObj) {
        return (T) getSqlMapClientTemplate().insert(xmlId, parObj);
    }

    public <K, E> Map<K, E> getForMap(String xmlId, Object parObj, String mapKey) {
        return getSqlMapClientTemplate().queryForMap(xmlId, parObj, mapKey);
    }
    public <T> List<T> getForList(String xmlId) {
        return getSqlMapClientTemplate().queryForList(xmlId);
    }
    public <T> List<T> getForList(String xmlId, Object parObj) {
        return getSqlMapClientTemplate().queryForList(xmlId, parObj);
    }

    public <T> T getEntity(String xmlId, Object parObj) {
        return (T) getSqlMapClientTemplate().queryForObject(xmlId, parObj);
    }

    public void updateEntity(String xmlId, Object parObj) {

        getSqlMapClientTemplate().update(xmlId, parObj);
    }

    public int update(String xmlId, Object parObj) {
        return getSqlMapClientTemplate().update(xmlId, parObj);
    }

    public List createBatchEntity(String xmlId, List objList) throws SQLException {
        List cusList = new ArrayList();
        try {
            getSqlMapClientTemplate().getSqlMapClient().startTransaction();
            getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().setAutoCommit(false);

            for (int i = 0; objList != null && i < objList.size(); i++) {
                cusList.add(getSqlMapClientTemplate().getSqlMapClient().insert(xmlId, objList.get(i)));
            }

            getSqlMapClientTemplate().getSqlMapClient().commitTransaction();

            getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().setAutoCommit(true);

        } catch (Exception e) {
            getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().rollback();
        } finally {
            getSqlMapClientTemplate().getSqlMapClient().endTransaction();
        }
        return cusList;

    }

    public void deleteEntity(String xmlId, Object parObj) {
        getSqlMapClientTemplate().delete(xmlId, parObj);
    }

    public int delete(String xmlId, Object parObj) {
        return getSqlMapClientTemplate().delete(xmlId, parObj);
    }

    /**
     * 分页查询
     * 
     * @param xmlId
     *            结果集查询sql
     * @param countXmlId
     *            结果集总数查询sql
     * @param pageQuery
     *            查询条件
     * @return PageResultVO
     */
    public PageResult getPageResult(String xmlId, String countXmlId, PageQuery pageQuery) {
        List resultList = getForList(xmlId, pageQuery);// 获得记录集
        Integer totalRecord = getEntity(countXmlId, pageQuery);// 获得总记录数
        PageResult pageResult = new PageResult();
        pageResult.setCurrentPage(pageQuery.getCurrentPage());// page中加入当前页
        pageResult.setPageResult(resultList);// 加入结果集
        if (resultList == null || resultList.size() == 0) {
            pageResult.setTotalRecord(0);
        } else {
            pageResult.setTotalRecord(totalRecord);// 加入总记录数
        }
        return pageResult;
    }

    public void batchExecuteSingleSql(final String xmlId, final List parList, final String methodType, final int countToBeExecute) {
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            private int executeBatch(int count, SqlMapExecutor executor) throws SQLException {
                if (count >= countToBeExecute) {
                    executor.executeBatch();
                    count = 0;
                }
                return count;
            }

            public Object doInSqlMapClient(SqlMapExecutor sqlMapExecutor) throws SQLException {
                sqlMapExecutor.startBatch();
                int count = 0;
                if (BATCH_EXECUTE_METHOD_TYPE_INSERT.equals(methodType)) {
                    for (Object parObj : parList) {
                        sqlMapExecutor.insert(xmlId, parObj);
                        count = executeBatch(++count, sqlMapExecutor);
                    }
                } else if (BATCH_EXECUTE_METHOD_TYPE_UPDATE.equals(methodType)) {
                    for (Object parObj : parList) {
                        sqlMapExecutor.update(xmlId, parObj);
                        count = executeBatch(++count, sqlMapExecutor);
                    }
                } else if (BATCH_EXECUTE_METHOD_TYPE_DELETE.equals(methodType)) {
                    for (Object parObj : parList) {
                        sqlMapExecutor.delete(xmlId, parObj);
                        count = executeBatch(++count, sqlMapExecutor);
                    }
                }
                sqlMapExecutor.executeBatch();
                return null;
            }
        });
    }
}
