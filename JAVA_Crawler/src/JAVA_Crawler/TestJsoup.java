package JAVA_Crawler;
import java.io.IOException;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TestJsoup {
	public  Document getDocument (String url){
        try {
             return Jsoup.connect(url).timeout(5000).get();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
     }
     public void main(String[] args) throws SQLException {
    	 //关键词爬取
    	 Linkdb e=new Linkdb();
    	 TestJsoup t = new TestJsoup();
    	 e.connectToAccess();
    	 String time=e.Time_get();
         Document doc = t.getDocument("https://s.weibo.com/top/summary?cate=realtimehot");
         Elements elements1 = doc.select("[class=data]");
         Elements elements2 = elements1.select("tbody");
         Elements elements3 = elements2.select("tr");
         Elements elements4 = elements3.select("[class=td-02]");
         String collect[]=new String[51];//捕获数组
         String Search[]=new String[51];//搜索度统计数组
         String List[]=new String[51];//整理数组
         for(int i = 0;i<=50;i++) {collect[i] = elements4.get(i).text();List[i]=collect[i].replaceAll("\'", " ");}//单引号不能替换
         e.alter_l(time); //新建时间字段
         e.alter_t(time);
         for(int i = 0;i<=50;i++) {
        	if(i==0) {
        		int a=e.select_top(List[i]);//基础查询语句 判断是否存在该置顶词
        		 if(a==0) {e.insert_Top(List[i],time);}
        		 else {e.update_Top(List[i],time);}
        		 }
        	
        	 else{
        		 int b=e.select_list(List[i].substring(0,List[i].lastIndexOf(" ")));//基础查询语句 判断是否存在该关键词
        			 if(b==0) {
        			 Search[i]=List[i].substring(List[i].lastIndexOf(" "));//提取搜索量;
        			 e.insert_List(List[i].substring(0,List[i].lastIndexOf(" ")),Search[i],time);//插入关键词记录及搜索量
                     }//输出搜索量  
        		 else {
        			 Search[i]=List[i].substring(List[i].lastIndexOf(" "));//提取搜索量;
        			 e.update_List(List[i].substring(0,List[i].lastIndexOf(" ")),Search[i],time);//更新搜索量
        		 }
        			 }
        			 
        	 
        		System.out.println("程序成功运行"); 
        	 }
        		 }
        	 
     }