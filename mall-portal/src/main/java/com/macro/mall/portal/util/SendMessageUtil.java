package com.macro.mall.portal.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author LEEMER
 * Create Date: 2019-01-18
 */
public class SendMessageUtil {
    private static final String SMS_Url = "http://sms.webchinese.cn/web_api/";

    /**
     * @param Uid SMS用户id  ： LEEMER
     * @param Key 接口秘钥：SMS登录可查（非登录密码）
     * @param sendPhoneNum 短信发送目标号码
     * @param desc 短信内容
     * @return Integer(1：成功码，其他失败，具体参见注释)
     */
    public static Integer send(String Uid,String Key,String sendPhoneNum,String desc){

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(SMS_Url);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码

        //设置参数
        NameValuePair[] data = {
                new NameValuePair("Uid", Uid),//登录用户名
                new NameValuePair("Key", Key),//秘钥
                new NameValuePair("smsMob", sendPhoneNum),
                new NameValuePair("smsText", desc)
        };

        post.setRequestBody(data);

        try {
            client.executeMethod(post);
        } catch (Exception e) {  e.printStackTrace();  }


        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }

        String result ="";
        try {
            result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        } catch (Exception e) {  e.printStackTrace();  }

        post.releaseConnection();

        return Integer.parseInt(result);
    }
    /**
     *  -1  没有该用户账户
     -2 接口密钥不正确 [查看密钥]不是账户登陆密码
     -21    MD5接口密钥加密不正确
     -3 短信数量不足
     -11    该用户被禁用
     -14    短信内容出现非法字符
     -4 手机号格式不正确
     -41    手机号码为空
     -42    短信内容为空
     -51    短信签名格式不正确接口签名格式为：【签名内容】
     -6 IP限制
     大于0    短信发送数量
     以上作为补充
     */
    public static String getMessage(Integer code){
        String message;
        if(code > 0 ) {
            message = "SMS-f发送成功！短信量还有" + code + "条";
        }else if(code == -1){
            message = "SMS-没有该用户账户";
        }else if(code == -2){
            message = "SMS-接口密钥不正确";
        }else if(code == -21){
            message = "SMS-MD5接口密钥加密不正确";
        }else if(code == -3){
            message = "SMS-短信数量不足";
        }else if(code == -11){
            message = "SMS-该用户被禁用";
        }else if(code == -14){
            message = "SMS-短信内容出现非法字符";
        }else if(code == -4){
            message = "SMS-手机号格式不正确";
        }else if(code == -41){
            message = "SMS-手机号码为空";
        }else if(code == -42){
            message = "SMS-短信内容为空";
        }else if(code == -51){
            message = "SMS-短信签名格式不正确接口签名格式为：【签名内容】";
        }else if(code == -6){
            message = "SMS-IP限制";
        }else{
            message = "其他错误";
        }
        return message;
    }

}

