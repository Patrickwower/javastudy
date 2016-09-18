package com.dengyuecang.www.utils.sharesdk;

import com.dengyuecang.www.service.common.CommonConstant;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Created by acang on 16/7/19.
 */
public class SmsUtil {

    public final static String SMS_VERIFY_URL = "https://webapi.sms.mob.com/sms/verify";

    public static String verifyCode(String params){
    //"appkey=127d0cc8fd910&phone=18500902882&zone=86&code=9045"
        String result = requestData(SMS_VERIFY_URL, params);
        return result;

    }

    public static void main(String[] args) throws Exception {
        String result = requestData(SMS_VERIFY_URL,
                "appkey=127d0cc8fd910&phone=15286860860&zone=86&code=5723");
        System.out.println(result);
    }

    /**
     * 发起https 请求
     * @param address
     * @param params
     * @return
     */
    public  static String requestData(String address ,String params){

        if (CommonConstant.SDK_MSG_LOCK){
            return "{\"status\":200}";
        }

        HttpURLConnection conn = null;
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
                public X509Certificate[] getAcceptedIssuers(){return null;}
                public void checkClientTrusted(X509Certificate[] certs, String authType){}
                public void checkServerTrusted(X509Certificate[] certs, String authType){}
            }};

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());

            //ip host verify
            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String urlHostName, SSLSession session) {
                    return urlHostName.equals(session.getPeerHost());
                }
            };

            //set ip host verify
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");// POST
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            // set params ;post params
            if (params!=null) {
                conn.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.write(params.getBytes(Charset.forName("UTF-8")));
                out.flush();
                out.close();
            }
            conn.connect();
            //get result
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String result = parsRtn(conn.getInputStream());
                System.out.println("验证码验证结果:"+result);
                return result;
            } else {
                System.out.println(conn.getResponseCode() + " "+ conn.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return null;
    }

    /**
     * 获取返回数据
     * @param is
     * @return
     * @throws IOException
     */
    private  static String parsRtn(InputStream is) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = null;
        boolean first = true;
        while ((line = reader.readLine()) != null) {
            if(first){
                first = false;
            }else{
                buffer.append("\n");
            }
            buffer.append(line);
        }
        return buffer.toString();
    }

}
