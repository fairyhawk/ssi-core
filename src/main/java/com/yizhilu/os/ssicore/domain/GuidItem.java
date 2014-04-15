package com.yizhilu.os.ssicore.domain;

import java.io.Serializable;

/**
 * 
 * ClassName  com.yizhilu.common.vo
 * Description 
 * User: liuqg
 * Date: 2013-7-4 下午3:44:39
 */
public class GuidItem implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1509796537146826859L;

    private Long id;
    
    private Long autoId;
    
    private String project;
    
    private String description="";

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public Long getAutoId() {
       return autoId;
   }

   public void setAutoId(Long autoId) {
       this.autoId = autoId;
   }

   public String getProject() {
       return project;
   }

   public void setProject(String project) {
       this.project = project;
   }

   public String getDescription() {
       return description;
   }

   public void setDescription(String description) {
       this.description = description;
   }
    
    
   

}
