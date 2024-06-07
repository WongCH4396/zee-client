package tech.gamesupport.center.inner.service.product.model;

import tech.gamesupport.center.inner.bean.DBSQLType;

public class SQLInfo {

    private DBSQLType sqlType;
    private String table;
    private String schema;
    private Object data;
    private Object key;

    public DBSQLType getSqlType() {
        return sqlType;
    }

    public void setSqlType(DBSQLType sqlType) {
        this.sqlType = sqlType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }



    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private DBSQLType sqlType;
        private String table;
        private String schema;
        private Object data;
        private Object key;

        public Builder sqlType(DBSQLType sqlType) {
            this.sqlType = sqlType;
            return this;
        }
        public Builder table(String table) {
            this.table = table;
            return this;
        }
        public Builder schema(String schema) {
            this.schema = schema;
            return this;
        }
        public Builder data(Object data) {
            this.data = data;
            return this;
        }
        public Builder key(Object key) {
            this.key = key;
            return this;
        }

        public SQLInfo build() {
            SQLInfo sqlInfo = new SQLInfo();
            sqlInfo.sqlType = this.sqlType;
            sqlInfo.table = this.table;
            sqlInfo.schema = this.schema;
            sqlInfo.data = this.data;
            sqlInfo.key = this.key;
            return sqlInfo;
        }


    }

}
