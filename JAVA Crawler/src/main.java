import java.sql.SQLException;

public class main {
		public static void main(String[] args) throws InterruptedException, SQLException {
	    frame fram=new frame();//���ڳ���
	    //Chart_test test=new Chart_test();
		//Linkdb example=new Linkdb();
	    TestJsoup e=new TestJsoup();
		while (true) {
			Thread.sleep(5000);//���ʱ��
			e.main(null);
	    }
	}
}
