/*  1:   */ package com.dengyuecang.api.utils;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.Iterator;
/*  5:   */ import java.util.List;
/*  6:   */ import java.util.Map;
/*  7:   */ import java.util.Set;
/*  8:   */ import org.apache.http.Consts;
/*  9:   */ import org.apache.http.HttpEntity;
/* 10:   */ import org.apache.http.HttpResponse;
/* 11:   */ import org.apache.http.NameValuePair;
/* 12:   */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/* 13:   */ import org.apache.http.client.methods.HttpPost;
/* 14:   */ import org.apache.http.impl.client.CloseableHttpClient;
/* 15:   */ import org.apache.http.impl.client.HttpClients;
/* 16:   */ import org.apache.http.message.BasicNameValuePair;
/* 17:   */ import org.apache.http.util.EntityUtils;
/* 18:   */ 
/* 19:   */ public class HttpUtil
/* 20:   */ {
/* 21:   */   public static String sendPost(String url, Map<String, String> params, Map<String, String> headers)
/* 22:   */   {
/* 23:24 */     CloseableHttpClient httpClient = null;
/* 24:   */     try
/* 25:   */     {
/* 26:28 */       httpClient = HttpClients.createDefault();
/* 27:   */       
/* 28:30 */       HttpPost httpPost = new HttpPost(url);
/* 29:33 */       if (headers != null)
/* 30:   */       {
/* 31:34 */         Set<String> keys = headers.keySet();
/* 32:35 */         for (Iterator<String> i = keys.iterator(); i.hasNext();)
/* 33:   */         {
/* 34:36 */           String key = (String)i.next();
/* 35:37 */           httpPost.addHeader(key, (String)headers.get(key));
/* 36:   */         }
/* 37:   */       }
/* 38:42 */       List<NameValuePair> nvps = new ArrayList();
/* 39:   */       
/* 40:44 */       Set<String> keys = params.keySet();
/* 41:46 */       for (String key : keys) {
/* 42:47 */         nvps.add(new BasicNameValuePair(key, (String)params.get(key)));
/* 43:   */       }
/* 44:53 */       httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
/* 45:   */       
/* 46:55 */       HttpResponse response = httpClient.execute(httpPost);
/* 47:   */       
/* 48:57 */       HttpEntity entity = response.getEntity();
/* 49:   */       

/* 51:60 */       return EntityUtils.toString(entity,"utf-8");
/* 52:   */     }
/* 53:   */     catch (Exception e)
/* 54:   */     {
/* 55:62 */       e.printStackTrace();
/* 56:   */     }
/* 57:63 */     return "error";
/* 58:   */   }
/* 59:   */ }


/* Location:           E:\tmp\项目\data\WEB-INF\classes\
 * Qualified Name:     com.longinf.h5game.web.utils.HttpUtil
 * JD-Core Version:    0.7.0.1
 */