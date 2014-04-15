package com.yizhilu.os.ssicore.service;
/**
 * 
 * ClassName  com.yizhilu.common.service
 * Description 
 * User: liuqg
 * Date: 2013-7-4 下午3:43:32
 */
public interface GuidGeneratorService {

    public String getGuid(String type);

    public String getGuid();

    public String gainCode(String prefix);

    public String getSpecialId(final String project, int length,boolean appendJvmId);

    public String gainCode(String prefix,int length);
}
