package com.dengyuecang.www.utils.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by acang on 2017/1/20.
 */
public class JpushUtils {

    protected static final Logger LOG = LoggerFactory.getLogger(JpushUtils.class);

    public static void sendTo(String alias,String alert,String message){

        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(JpushCommonConstant.MASTER_SECRET, JpushCommonConstant.APP_KEY, null, clientConfig);


        PushPayload payload = buildPushObject_all_alias_alert(alias,alert,message);

        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("Got result - " + result);
//            Thread.sleep(5000);
            //如果使用 NettyHttpClient，需要手动调用 close 方法退出进程
//            jpushClient.close();
        } catch (APIConnectionException e) {
//            LOG.error("Connection error. Should retry later. ", e);
//            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
//            LOG.error("Error response from JPush server. Should review and fix it. ", e);
//            LOG.info("HTTP Status: " + e.getStatus());
//            LOG.info("Error Code: " + e.getErrorCode());
//            LOG.info("Error Message: " + e.getErrorMessage());
//            LOG.info("Msg ID: " + e.getMsgId());
//            LOG.error("Sendno: " + payload.getSendno());
        }

    }

    public static PushPayload buildPushObject_all_alias_alert(String alias,String alert,String message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .setMessage(Message.content(message))
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                .build();
    }

    public static void main(String[] args) {
        JpushUtils.sendTo("18500902882","测试引入","type:zan");
    }

}
