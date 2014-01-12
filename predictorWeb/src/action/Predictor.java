package action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.backpro;
import logic.reqRatesCounter;

public class Predictor extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Predictor() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Double> pdctsrc = new ArrayList<Double>();	//预测训练集
		double[] pdctres = backpro.dataPrecessing(pdctsrc, "Z:\\train.in", 12, 12, 12);		//预测结果
		//pdctsrc.add(1.0);
		//double[] pdctres = {1.0, 1.0};
		String src = "[{hour : 0 , rate : "+pdctsrc.get(0)+"}";
		for (int i=1;i<pdctsrc.size();i++){
			src += ", {hour : " + i + ", rate : " +pdctsrc.get(i) + "}";
		}
		
		String res = "[{hour : 0 , rate : "+pdctres[0]+"}";
		for (int i=1;i<pdctres.length;i++){
			res += ", {hour : " + i + ", rate : " +pdctres[i] + "}";
		}
		
		
		/*File targetfile =  new File("/predict.out");
		BufferedWriter bout;
		File targetfile1 =  new File("/predict.in");
		BufferedWriter bout1;
		try {
			bout = new BufferedWriter(new FileWriter(targetfile,true));
								//   ip time stutus inb outb per mtd pth bsu bwr  
			bout.write(res);
			bout.close();
			
			bout1 = new BufferedWriter(new FileWriter(targetfile1,true));
			//   ip time stutus inb outb per mtd pth bsu bwr  
			bout1.write(src);
			bout1.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println(src);
		out.println(res);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String appname = request.getParameter("uname");
		String reqinfo; 
		int [] maxrate = new int[1];
		int [] minrate = new int[1];	//最大、最小请求率，还原归一化的rate
		int interval;
		try {
			reqinfo = reqRatesCounter.counter(maxrate, minrate, "Z:\\logAnalyzer140102\\data\\" + appname); //访问信息 rate\tnumError\tperiod\n 
																											//此处rate为归一化后的，需还原
			interval = maxrate[0] - minrate[0];																
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
			out.flush();
			out.close();
			return;
		}
		String train = getTraindata(reqinfo);	
		ArrayList<Double> pdctsrc = new ArrayList<Double>();	//预测训练集
		double[] pdctres = backpro.dataPrecessing(pdctsrc, train, 12, 12, 12);		//预测结果
		Scanner sinScanner=new Scanner(reqinfo);
		String line = sinScanner.nextLine();
		String[] strs = line.split("\t");
		String src = "[{hour : 0 , rate : "+ strs[1] +" , numError : "+ strs[2] + " , period : " + strs[3] + "}";
		int i = 0;
		while (sinScanner.hasNext()){
			line = sinScanner.nextLine();
			strs = line.split("\t");
			src += ", {hour : " + (++i)%24 + " , rate : " + strs[1] +" , numError : "+ strs[2] + " , period : " + strs[3] + "}";
		}
		src += "]";
		String res = "[{hour : 0 , rate : "+String.format("%.0f", pdctres[0]*interval+minrate[0])+"}";
		for (int j=1;j<pdctres.length;j++){
			res += ", {hour : " + j%24 + " , rate : " +String.format("%.0f", pdctres[j]*interval+minrate[0]) + "}";
		}
		res += "]";
		out.println(src);	//输出训练集
		out.println(res);	//输出预测结果
		out.flush();
		out.close();
	}

	private String getTraindata(String reqinfo) {
		// TODO Auto-generated method stub
		Scanner sinScanner=new Scanner(reqinfo);
		String line;
		String res = "";
		while (sinScanner.hasNext()){
			line = sinScanner.nextLine();
			res += line.split("\t")[0] + "\n";
		}
		return res;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
