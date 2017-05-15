/*正则表达式实例
网页爬虫抓取页面邮箱地址
*/
import java.io.*;
import java.net.*;
import java.util.regex.*;

public class Regex{
	public static void main(String[] args)throws Exception{
		getMails(args[0]);
	}
	
	public static void getMails(String s)throws Exception{
		//URL url = new URL("http://127.0.0.1:8080/regexTest/index.html");
		URL url = new URL(s);
		URLConnection conn = url.openConnection();
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = null;
		//正则表达式表示邮箱
		String mail = "\\w+@\\w+(\\.\\w+)+";
		Pattern p = Pattern.compile(mail);
		
		while((line = bufIn.readLine())!=null){
			Matcher m = p.matcher(line);
			while(m.find()){
				System.out.println(m.group());
				//如需对爬取的邮箱进行任何操作，在此进行
			}
		}
	}
}