package JAVA_Crawler;
import java.sql.SQLException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class main {
		public static void main(String[] args) throws InterruptedException, SQLException, JavaLayerException, FileNotFoundException {
		frame fram=new frame();//���ڳ���
	    TestJsoup e=new TestJsoup();
	    //new Chart_test();
	    new Thread(){//���̲߳���
	    	public void run(){
	    		BufferedInputStream buffer = null;
				try {
					buffer = new BufferedInputStream(new FileInputStream("F:\\Java\\JAVA_Crawler\\music\\music.mp3"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            try {
					new Player(buffer).play();
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	}.start();
	    	
		while (true) {
			Thread.sleep(fram.space);//���ʱ��
			e.main(null);
			
	    }
	}
}

