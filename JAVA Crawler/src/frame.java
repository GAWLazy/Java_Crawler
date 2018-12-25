import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame extends JFrame {
	int space;
	JTextField timeT;
	boolean sign=false;
	public frame() {
		setTitle("微博热搜榜获取工具");
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel time=new JLabel();
		time.setText("访问间隔时间");
		time.setBounds(10,20,100,30);
		add(time);
		
		timeT=new JTextField();
		timeT.setBounds(100,20,50,30);
		add(timeT);
		
		
		JButton start=new JButton();
		start.setText("开始");
		start.setBounds(200,20,80,30);
		start.addActionListener(new start_s());
		add(start);
		
		JButton stop=new JButton();
		stop.setText("停止");
		stop.setBounds(300,20,80,30);
		stop.addActionListener(new stop_s());
		add(stop);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class start_s implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			space=(Integer.parseInt(timeT.getText()))*1000;//获取时间 时间赋值
			JOptionPane.showMessageDialog(null, "访问间隔时间为"+timeT.getText()+"秒","开始访问",JOptionPane.YES_OPTION);
			setVisible(true);
					}
		}
	class stop_s implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				sign=false;
				JOptionPane.showMessageDialog(null, "停止访问","停止访问",JOptionPane.YES_OPTION);
						}
	}
}
