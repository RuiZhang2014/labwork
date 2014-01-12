package analyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import model.Log;

public class Outputer {
	
	private static File data =  new File("data");
	private static SimpleDateFormat file_df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void init(){
		if  (!data.exists()) {data.mkdirs();}
	}
	/*   
	 * 输出格式 ： 以\t分隔
	 * ip time status inbyte outbyte period method path baseurl browser
	 */
	
	public static void output(Log log) {
		String name = log.getHost();
		name = name.replace(':', '_');
		File  dir = new File(data,name);
		if (!dir.exists()) dir.mkdirs();
		File targetfile =  new File(dir,file_df.format(log.getTime())+".txt");
		BufferedWriter bout;
		try {
			bout = new BufferedWriter(new FileWriter(targetfile,true));
								//   ip time stutus inb outb per mtd pth bsu bwr  
			bout.write(String.format("%s\t%s\t%d\t%d\t%d\t%d\t%s\t%s\t%s\t%s", 
									log.getIp(),sdf.format(log.getTime()),log.getStatus(),log.getInbyte(),log.getOutbyte(),log.getPeriod(),
									log.getMethod(),log.getPath(),log.getBaseurl(),log.getBrowser()));
			bout.newLine();
			bout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
