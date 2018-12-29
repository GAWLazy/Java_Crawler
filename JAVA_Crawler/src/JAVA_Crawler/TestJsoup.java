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
    	 //�ؼ�����ȡ
    	 Linkdb e=new Linkdb();
    	 TestJsoup t = new TestJsoup();
    	 e.connectToAccess();
    	 String time=e.Time_get();
         Document doc = t.getDocument("https://s.weibo.com/top/summary?cate=realtimehot");
         Elements elements1 = doc.select("[class=data]");
         Elements elements2 = elements1.select("tbody");
         Elements elements3 = elements2.select("tr");
         Elements elements4 = elements3.select("[class=td-02]");
         String collect[]=new String[51];//��������
         String Search[]=new String[51];//������ͳ������
         String List[]=new String[51];//��������
         for(int i = 0;i<=50;i++) {collect[i] = elements4.get(i).text();List[i]=collect[i].replaceAll("\'", " ");}//�����Ų����滻
         e.alter_l(time); //�½�ʱ���ֶ�
         e.alter_t(time);
         for(int i = 0;i<=50;i++) {
        	if(i==0) {
        		int a=e.select_top(List[i]);//������ѯ��� �ж��Ƿ���ڸ��ö���
        		 if(a==0) {e.insert_Top(List[i],time);}
        		 else {e.update_Top(List[i],time);}
        		 }
        	
        	 else{
        		 int b=e.select_list(List[i].substring(0,List[i].lastIndexOf(" ")));//������ѯ��� �ж��Ƿ���ڸùؼ���
        			 if(b==0) {
        			 Search[i]=List[i].substring(List[i].lastIndexOf(" "));//��ȡ������;
        			 e.insert_List(List[i].substring(0,List[i].lastIndexOf(" ")),Search[i],time);//����ؼ��ʼ�¼��������
                     }//���������  
        		 else {
        			 Search[i]=List[i].substring(List[i].lastIndexOf(" "));//��ȡ������;
        			 e.update_List(List[i].substring(0,List[i].lastIndexOf(" ")),Search[i],time);//����������
        		 }
        			 }
        			 
        	 
        		System.out.println("����ɹ�����"); 
        	 }
        		 }
        	 
     }