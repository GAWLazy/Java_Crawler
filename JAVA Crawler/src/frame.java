import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame extends JFrame {
	int space;
	JTextField timeT;
	boolean sign=false;
	public frame() {
		setTitle("΢�����Ѱ��ȡ����");
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel time=new JLabel();
		time.setText("���ʼ��ʱ��");
		time.setBounds(10,20,100,30);
		add(time);
		
		timeT=new JTextField();
		timeT.setBounds(100,20,50,30);
		add(timeT);
		
		
		JButton start=new JButton();
		start.setText("��ʼ");
		start.setBounds(200,20,80,30);
		start.addActionListener(new start_s());
		add(start);
		
		JButton stop=new JButton();
		stop.setText("ֹͣ");
		stop.setBounds(300,20,80,30);
		stop.addActionListener(new stop_s());
		add(stop);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class start_s implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			space=(Integer.parseInt(timeT.getText()))*1000;//��ȡʱ�� ʱ�丳ֵ
			JOptionPane.showMessageDialog(null, "���ʼ��ʱ��Ϊ"+timeT.getText()+"��","��ʼ����",JOptionPane.YES_OPTION);
			setVisible(true);
					}
		}
	class stop_s implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				sign=false;
				JOptionPane.showMessageDialog(null, "ֹͣ����","ֹͣ����",JOptionPane.YES_OPTION);
						}
	}
}
