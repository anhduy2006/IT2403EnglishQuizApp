/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.pojo;

import com.dht.utils.MyConnectSingleton;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionQueryBuilder {

    /**
     * @return the query
     */
    public StringBuilder getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(StringBuilder query) {
        this.query = query;
    }

    /**
     * @return the where
     */
    public StringBuilder getWhere() {
        return where;
    }

    /**
     * @param where the where to set
     */
    public void setWhere(StringBuilder where) {
        this.where = where;
    }

    /**
     * @return the orderby
     */
    public String getOrderby(String rand) {
        return orderby;
    }

    /**
     * @param orderby the orderby to set
     */
   

    /**
     * @return the params
     */
    public List<Object> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(List<Object> params) {
        this.params = params;
    }
    private StringBuilder query;
    private StringBuilder where;
    private String orderby = "id DESC";
    private List<Object> params;

    public QuestionQueryBuilder() {
        this.query = new StringBuilder("SELECT * FROM question WHERE 1=1 %s ORDER BY %s");
        this.where = new StringBuilder();
        this.params = new ArrayList<>();
    }
    public QuestionQueryBuilder setOrderby(String kw) {
        this.orderby = kw;
        return this;
    }
     public QuestionQueryBuilder widthKeywords(String kw) {
         if (kw != null && !kw.isEmpty()) {
             this.getWhere().append(" AND content like concat('%', ?, '%')");
             this.getParams().add(kw);
     }
    return this;
     }
     public QuestionQueryBuilder widthCategory(Category c) {
         if (c != null) {
             this.getWhere().append(" AND category_id = ?");
             this.getParams().add(c.getId());
         }
         return this;
     }
     public QuestionQueryBuilder widthLevel(Level l) {
         if (l != null) {
             this.getWhere().append(" And level_id = ?") ;
             this.getParams().add(l.getId());
            }
         return this;
     }
     public QuestionQueryBuilder widthLevel(int lvlId) {
         if (lvlId > 0) {
             this.getWhere().append(" And level_id = ?") ;
             this.getParams().add(lvlId);
            }
         return this;
     }
     public QuestionQueryBuilder setlimit(int limit) {
         if (this.getQuery().toString().toLowerCase().contains("limit") ) {
             this.getQuery().append(" LIMIT ?");
             this.getParams().add(limit);
            
         }
         return this;
     }
          public QuestionQueryBuilder setlimit(String limit) {
              this.setlimit(Integer.parseInt(limit));
            
         
         return this;
     }
     public PreparedStatement build() throws SQLException {
         String sql = String.format(this.getQuery().toString(),this.getWhere().toString(), this.getOrderby("rand()"));
         PreparedStatement stm = MyConnectSingleton.getInstance().connect().prepareCall(sql);
         for (int i = 0; i < getParams().size(); i++) {
            stm.setObject(i + 1, this.getParams().get(i));
        }
         return stm;
     }
}
     
