package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 114.243.46.158
 * wekeepwalking.duapp.com
 *  [2013-12-16 22:38:10]
 *  404
 *  1227 
 *  345 
 *  0 
 *  "GET /favicon.ico HTTP/1.1" 
 *  "" 
 *  "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36" 
 *  BAEID=1AA07825C5F1C32FB1CF922B9E8236D2:FG=1
 * */
public class Log {
	private static SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public Log(String ip, String host, String time, String status,
			String inbyte, String outbyte, String period, String info,
			String base, String browser, String cookie) throws Exception {
		this.ip = ip;
		this.host = host;
		this.time = sdf.parse(time);
		this.status =  Integer.valueOf(status);
		this.inbyte =  Integer.valueOf(inbyte);
		this.outbyte = Integer.valueOf(outbyte);
		this.period  =  Integer.valueOf(period);
		this.baseurl =  base;
		this.browser =  browser;
		this.cookie = cookie;
		String[] infos =  info.split(" ");
		this.method = infos[0];
		this.path = infos[1];
		this.version =  infos[2];
	}
	private String ip;
	private String host;
	private Date time;
	private int status;
	private int inbyte;
	private int outbyte;
	private int period;
	private String method;
	private String path;
	private String version;
	private String baseurl;
	private String browser;
	private String cookie;
	

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getInbyte() {
		return inbyte;
	}
	public void setInbyte(int inbyte) {
		this.inbyte = inbyte;
	}
	public int getOutbyte() {
		return outbyte;
	}
	public void setOutbyte(int outbyte) {
		this.outbyte = outbyte;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBaseurl() {
		return baseurl;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
