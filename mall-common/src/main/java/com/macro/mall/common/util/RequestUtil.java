package com.macro.mall.common.util;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 请求工具类
 * Created by macro on 2020/10/8.
 */
public class RequestUtil {


    private static final String UNKNOWN = "UNKNOWN";

    private static final String HEAD_FORWARDED = "x-forwarded-for";

    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String LOCALHOST_IP_A = "127.0.0.1";

    private static final String LOCALHOST_IP_B = "0:0:0:0:0:0:0:1";

    private static final int IP_LIMIT_NUM = 15;


    /**
     * 获取请求真实IP地址
     */
    public static String getRequestIp(HttpServletRequest request) {
        //通过HTTP代理服务器转发时添加
        String ipAddress = request.getHeader(HEAD_FORWARDED);
        if (StrUtil.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader(PROXY_CLIENT_IP);
        }
        if (StrUtil.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (StrUtil.isBlank(ipAddress)  || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            // 从本地访问时根据网卡取本机配置的IP
            if (ipAddress.equals(LOCALHOST_IP_A) || ipAddress.equals(LOCALHOST_IP_B)) {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inetAddress.getHostAddress();
            }
        }
        // 通过多个代理转发的情况，第一个IP为客户端真实IP，多个IP会按照','分割
        if (ipAddress != null && ipAddress.length() > IP_LIMIT_NUM) {
            if (ipAddress.indexOf(StrUtil.COMMA) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(StrUtil.COMMA));
            }
        }
        return ipAddress;
    }

}
