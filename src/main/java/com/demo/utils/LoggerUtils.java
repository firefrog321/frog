package com.demo.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 日志工具类
 * @author Mr.Deng
 * Created on 2018/3/28 14:44
 **/
public class LoggerUtils {

    /**
     * 获取客户端ip地址
     *
     * @param request
     * @return
     */
    public static String getCliectIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }
        return ip;
    }
    public static String getAddressByIP(String ip)
    {
        try
        {
            if("127.0.0.1".equals(ip)) return null;

            URL url = new URL( "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                    "UTF-8"));
            String line = null;
            String result="";
            while((line = reader.readLine()) != null)
            {
                result+=line;
            }
            reader.close();
            result=result.replaceAll(":","");
            result=result.replaceAll("\"","");
            result=result.replaceAll(",","");
            String region = result.substring(result.indexOf( "region")+6,result.indexOf( "city" ));
            String city = result.substring(result.indexOf( "city" )+4,result.indexOf( "county" ));
            String isp = result.substring(result.indexOf( "isp" )+3,result.indexOf( "country_id" ));

            return region+city+isp;
        }
        catch( Exception e)
        {
            return "读取失败";
        }
    }
    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    public static String getRequestType(HttpServletRequest request) {
        return request.getHeader("X-Requested-With");
    }
}
