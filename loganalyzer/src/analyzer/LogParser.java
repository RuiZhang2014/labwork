package analyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Log;

public class LogParser {
//182.129.215.39 hxydbd.duapp.com [2013-12-16 18:10:44] 200 1663 1377 0 "GET /wp-content/themes/medicine/images/socials/linkedin.png HTTP/1.1" "http://hxydbd.duapp.com/wp-admin/widgets.php" "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36" pgv_pvi=6691857464;
//182.129.215.39 hxydbd.duapp.com [2013-12-16 18:10:44] 404 1565 345 0 "GET /favicon.ico HTTP/1.1" "" "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36" pgv_pvi=6691857464;
//182.129.215.39 hxydbd.duapp.com [2013-12-16 18:10:43] 200 1953 30405 6 "GET /wp-admin/load-scripts.php?c=1&load%5B%5D=hoverIntent,common,admin-bar,jquery-ui-core,jquery-ui-widget,jquery-ui-mouse,jquery-ui-sortable,jquery-ui-draggable,jquery-ui-dr&load%5B%5D=oppable,admin-widgets,svg-painter,heartbeat,wp-auth-check&ver=3.8 HTTP/1.1" "http://hxydbd.duapp.com/wp-admin/widgets.php" "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36" wordpress_c0dd095f28ad61e5555130441c3e836d=admin%7C1387360373%7C7964cb7a973ba4508f62c851d0e5212f;
//114.243.46.158 wekeepwalking.duapp.com [2013-12-16 22:37:27] 200 1234 6485 1220 "GET / HTTP/1.1" "" "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36" 
	
														//  (ip) (host)	 (date)  (status) (in) (out)  (period)  "(info)"   "(from)"    "(browner)"  (cookie)
	private static final Pattern pattern = Pattern.compile("(.*) (.*) \\[(.*)\\] (\\d+) (\\d+) (\\d+) (\\d+) \\\"(.*)\\\" \\\"(.*)\\\" \\\"(.*)\\\"(.*)?");
	
	private static BufferedWriter bout = null;
	
	public static void init() throws IOException{
		File file = new File("error.log");
		bout = new BufferedWriter(new FileWriter(file,true));
	}
	
	public static Log getLog(String trim) throws IOException {
		if (trim == null || trim.length() == 0) return null;
		////Pattern pattern = Pattern.compile("(.*) (.*) \\[(.*)\\] (\\d+) (\\d+) (\\d+) (\\d+) \\\"(.*)\\\" \\\"(.*)\\\" \\\"(.*)\\\"(.*)?");
		
		Matcher matcher = pattern.matcher(trim);
		if (matcher.find()) {
			String ip =  matcher.group(1);
			String host =  matcher.group(2);
			String time =  matcher.group(3);
			String status =  matcher.group(4);
			String inbyte =  matcher.group(5);
			String outbyte =  matcher.group(6);
			String period =  matcher.group(7);
			String info =  matcher.group(8);
			String base =  matcher.group(9);
			String browser =  matcher.group(10);
			String cookie = "";
			if  (matcher.groupCount() == 12) cookie = matcher.group(11);
			try {
				Log log = new Log(ip,host,time,status,inbyte,outbyte,period,info,base,browser,cookie);
				return log;
			} catch (Exception e) {
				e.printStackTrace();
				bout.write(trim);
				bout.newLine();
				return null;
			}
			
		}else {
			bout.write(trim);
			bout.newLine();
			return null;
		}
		
	}
	public static void destroy() throws IOException{
		if (bout != null) bout.close();
	}
	public static void main(String[] args) {
		//if (trim == null || trim.length() == 0) return null;
		//Pattern pattern = Pattern.compile("(.*) (.*) \\[(.*)\\] (\\d+) (\\d+) (\\d+) (\\d+) \\\"(.*)\\\" \\\"(.*)\\\" \\\"(.*)\\\"(.*)?");
		System.out.println("test");
		String str = "182.129.215.39 hxydbd.duapp.com [2013-12-16 18:10:44] 404 1565 345 0 \"GET /favicon.ico HTTP/1.1\" \"\" \"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36\" pgv_pvi=6691857464;";
		
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.find());
		System.out.println(matcher.groupCount());
		for (int i = 0; i< matcher.groupCount(); i++) System.out.println(i+ " : " + matcher.group(i));
		
		//return null;
	}

}
