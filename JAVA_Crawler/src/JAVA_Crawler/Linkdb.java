package JAVA_Crawler;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Linkdb {
	
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	public void connectToAccess(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false","root","LHMbdbqandr2015");
			System.out.println("与Access数据库连接成功！");
			stmt=con.createStatement();
		}catch(Exception e){
			System.out.println("连接Access数据库错误！");
			System.out.println(e.getMessage());
		}
	}
	
	public void closeConnection(){
		try{
			con.close();
			System.out.println("数据库关闭成功！");
			}
		catch(SQLException e){
			System.out.println("数据库关闭错误！");
			System.out.println(e.getMessage());
		}
	}
	
	public void alter_l(String k) {
		try {
			String sql="ALTER TABLE list add "+k+" int(10)";
			stmt.execute(sql);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	
	public void alter_t(String k) {
		try {
			String sql="ALTER TABLE top add "+k+" varchar(10)";
			stmt.execute(sql);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	
	public int select_list(String i) throws SQLException {
		try {
			stmt=con.createStatement();
			String sql="select count(*) from list where k='"+i+"';";
			rs=stmt.executeQuery(sql);//得到结果！！！！！！
		}catch(SQLException e){
			e.printStackTrace();
		}
		rs.next();
		return rs.getInt(1);//这里提取返回值
	}
	
	public int select_top(String i) throws SQLException {
		try {
			stmt=con.createStatement();
			String sql="select count(*) from top where k='"+i+"';";
			rs=stmt.executeQuery(sql);//得到结果！！！！！！
		}catch(SQLException e){
			e.printStackTrace();
		}
		rs.next();
		return rs.getInt(1);//这里提取返回值
	}
	
	public void insert_Top(String i,String k) throws SQLException {
		String sql="insert into top (k) values ('"+i+"')";
		stmt.executeUpdate(sql);
		String sql_1="update top set "+k+"='TOP' where k='"+i+"'";
		stmt.executeUpdate(sql_1);
	}
	
	public void update_Top(String i,String k) throws SQLException {
		String sql="update top set "+k+"='TOP' where k='"+i+"'";
		System.out.println(sql);
		stmt.executeUpdate(sql);
	}
	
	public void insert_List(String i,String j,String k) throws SQLException {
		stmt=con.createStatement();
		String sql="insert into list (k,"+k+") values ('"+i+"',"+j+")";
		stmt.executeUpdate(sql);
	}
	
	public void update_List(String i,String j,String k) throws SQLException {
		String sql="update list set "+k+"="+j+" where k='"+i+"'";
		System.out.println(sql);
		stmt.executeUpdate(sql);
	}
	
	public String Time_get(){
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time=df.format(day);
		String t_1=time.replace(' ','_');
		String t_2=t_1.replace(':','_');
		String t_3=t_2.replace('-', '_');
		return t_3;
	}
	
	public void sa() throws SQLException {
		String sql="select k from list";
		rs=stmt.executeQuery(sql);
		while(rs.next()){
	        String k = rs.getString(1);
	        System.out.println("关键词："+k);
	    	}
	}	
}