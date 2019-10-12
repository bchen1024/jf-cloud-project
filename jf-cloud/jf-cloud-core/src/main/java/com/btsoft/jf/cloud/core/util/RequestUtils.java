package com.btsoft.jf.cloud.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.btsoft.jf.cloud.core.constant.StringConstants;
import org.springframework.util.StringUtils;

/**
 * 
 * @author chenbin
 * @date 2018年6月16日 上午12:03:58
 */
public class RequestUtils {
	
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if ("127.0.0.1".equals(ipAddress)) {
					// 根据网卡取本机配置的IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					if(inet!=null){
						ipAddress = inet.getHostAddress();
					}
				}
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) {
				if (ipAddress.indexOf(StringConstants.SPOT) > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(StringConstants.COMMA));
				}
			}
		} catch (Exception e) {
			ipAddress="";
		}
		return ipAddress;
	}
}
