package com.dengyuecang.www.service.circle.model.message;

import com.dengyuecang.www.service.circle.model.MomentResponse;

/**
 * Created by acang on 2017/1/13.
 */
public class MessageResponse {

    private String messageId;

    private MessageSender sender;

    private MomentResponse moment;

    private String type;

    private String serviceId;

    private String status;

    private String ctime;

    private String timestamp;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MessageSender getSender() {
        return sender;
    }

    public void setSender(MessageSender sender) {
        this.sender = sender;
    }

    public MomentResponse getMoment() {
        return moment;
    }

    public void setMoment(MomentResponse moment) {
        this.moment = moment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
