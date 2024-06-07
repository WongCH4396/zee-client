package tech.gamesupport.center.inner.service.product.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static tech.gamesupport.center.inner.core.Globals.serializeObjectMapper;
import static tech.gamesupport.center.inner.core.Globals.sqlFieldsObjectMapper;

public class OperationLogMessage {
    static DateTimeFormatter date_formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    private ZonedDateTime operateDate;
    private String operateKey;
    private int operateVersion;
    private String operateDetail;
    private Long playerUserId;
    private String operateReason;
    private String sqlInfo;
    private String extraData1;
    private String extraData2;
    private String extraData3;

    public ZonedDateTime getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(ZonedDateTime operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateKey() {
        return operateKey;
    }

    public void setOperateKey(String operateKey) {
        this.operateKey = operateKey;
    }

    public int getOperateVersion() {
        return operateVersion;
    }

    public void setOperateVersion(int operateVersion) {
        this.operateVersion = operateVersion;
    }

    public String getOperateDetail() {
        return operateDetail;
    }

    public void setOperateDetail(String operateDetail) {
        this.operateDetail = operateDetail;
    }

    public Long getPlayerUserId() {
        return playerUserId;
    }

    public void setPlayerUserId(Long playerUserId) {
        this.playerUserId = playerUserId;
    }

    public String getOperateReason() {
        return operateReason;
    }

    public void setOperateReason(String operateReason) {
        this.operateReason = operateReason;
    }

    public String getSqlInfo() {
        return sqlInfo;
    }

    public void setSqlInfo(String sqlInfo) {
        this.sqlInfo = sqlInfo;
    }

    public String getExtraData1() {
        return extraData1;
    }

    public void setExtraData1(String extraData1) {
        this.extraData1 = extraData1;
    }

    public String getExtraData2() {
        return extraData2;
    }

    public void setExtraData2(String extraData2) {
        this.extraData2 = extraData2;
    }

    public String getExtraData3() {
        return extraData3;
    }

    public void setExtraData3(String extraData3) {
        this.extraData3 = extraData3;
    }

    public static final class Builder {

        private ZonedDateTime operateDate;
        private String operateKey;
        private int operateVersion = 1;
        private Object operateDetail;
        private Long playerUserId;
        private String operateReason;
        private SQLInfo sqlInfo;
        private String extraData1;
        private String extraData2;
        private String extraData3;

        public Builder operateDate(ZonedDateTime operateDate) {
            this.operateDate = operateDate;
            return this;
        }

        public Builder operateKey(String operateKey) {
            this.operateKey = operateKey;
            return this;
        }

        public Builder operateVersion(int operateVersion) {
            this.operateVersion = operateVersion;
            return this;
        }

        public Builder operateDetail(Object operateDetail) {
            this.operateDetail = operateDetail;
            return this;
        }

        public Builder playerUserId(long playerUserId) {
            this.playerUserId = playerUserId;
            return this;
        }

        public Builder operateReason(String operateReason) {
            this.operateReason = operateReason;
            return this;
        }

        public Builder sqlInfo(SQLInfo sqlInfo) {
            this.sqlInfo = sqlInfo;
            return this;
        }

        public Builder extraData1(String extraData1) {
            this.extraData1 = extraData1;
            return this;
        }

        public Builder extraData2(String extraData2) {
            this.extraData2 = extraData2;
            return this;
        }

        public Builder extraData3(String extraData3) {
            this.extraData3 = extraData3;
            return this;
        }

        public OperationLogMessage build() {
            OperationLogMessage message = new OperationLogMessage();
            message.operateDate = this.operateDate;
            message.operateKey = this.operateKey;
            message.operateVersion = this.operateVersion;
            if (operateDetail instanceof String) {
                message.operateDetail = (String) this.operateDetail;
            } else {
                message.operateDetail = serializeObjectMapper.valueToTree(this.operateDetail).toString();
            }
            message.playerUserId = this.playerUserId;
            message.operateReason = this.operateReason;

            ObjectNode objectNode = sqlFieldsObjectMapper.createObjectNode();
            objectNode.put("table", this.sqlInfo.getSchema() + "." + this.sqlInfo.getTable());
            String sqlTypeStr = this.sqlInfo.getSqlType().name().toLowerCase(Locale.ROOT);
            objectNode.set(sqlTypeStr, sqlFieldsObjectMapper.valueToTree(this.sqlInfo.getData()));
            if (this.sqlInfo.getKey() == null) {
                objectNode.set("where", sqlFieldsObjectMapper.valueToTree(this.sqlInfo.getKey()));
            }
            message.sqlInfo = objectNode.toString();
            message.extraData1 = this.extraData1;
            message.extraData2 = this.extraData2;
            message.extraData3 = this.extraData3;
            return message;
        }


    }


}
