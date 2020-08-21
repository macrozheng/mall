//package com.macro.mall.portal.util.wechat;
//
//import com.platform.utils.ResourceUtil;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLContexts;
//
//import javax.net.ssl.SSLContext;
//import java.io.InputStream;
//import java.security.KeyStore;
//
//@SuppressWarnings("deprecation")
//public class WechatConfig {
//
//    private static SSLConnectionSocketFactory sslcsf;
//
//    public static SSLConnectionSocketFactory getSslcsf() {
//        if (null == sslcsf) {
//            setSsslcsf();
//        }
//        return sslcsf;
//    }
//
//    private static void setSsslcsf() {
//        try {
//            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            Thread.currentThread().getContextClassLoader();
//            InputStream instream = new WechatRefundApiResult().getClass().getResourceAsStream(ResourceUtil.getConfigByName("wx.certName"));
//            try {
//                keyStore.load(instream, ResourceUtil.getConfigByName("wx.mchId").toCharArray());
//            } finally {
//                instream.close();
//            }
//            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, ResourceUtil.getConfigByName("wx.mchId").toCharArray()).build();
//            sslcsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
