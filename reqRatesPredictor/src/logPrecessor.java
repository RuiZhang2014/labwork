import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class logPrecessor {

	/**
	 * @param args
	 */
	private String name;
	public logPrecessor(String na){
		name = na;
	}
	public String read(){
		String res ="";
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(name);
			Scanner sinScanner=new Scanner(fileInputStream);
			int i = 0;
			while (i++<10000){  //24k*
				res += sinScanner.nextLine()+"\n";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void write(String path , String con){
		try {
			FileWriter fw = new FileWriter(path);
			fw.write(con);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logPrecessor lp = new logPrecessor("Z:\\baelog\\baelog2 (7)");
		lp.write("log", lp.read());
		System.out.print("end\n");
	}

}
