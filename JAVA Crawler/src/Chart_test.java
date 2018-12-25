import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Chart_test extends JFrame {
	private List<Integer> values;// ������ܵ���������
	private static final int MAX_VALUE = 180;// ���ܵ����������ֵ
	private static final int MAX_COUNT_OF_VALUES = 50;// ��ౣ�����ݵĸ���
	// private
	private MyCanvas trendChartCanvas = new MyCanvas();
	// ����������
	private final int FREAME_X = 50;
	private final int FREAME_Y = 50;
	private final int FREAME_WIDTH = 600;// ��
	private final int FREAME_HEIGHT = 250;// ��
	// ԭ������
	private final int Origin_X = FREAME_X + 50;
	private final int Origin_Y = FREAME_Y + FREAME_HEIGHT - 30;
	// X,Y���յ�����
	private final int XAxis_X = FREAME_X + FREAME_WIDTH - 30;
	private final int XAxis_Y = Origin_Y;
	private final int YAxis_X = Origin_X;
	private final int YAxis_Y = FREAME_Y + 30;
	// X���ϵ�ʱ��ֶ�ֵ��1�ֶ�=40���أ�
	private final int TIME_INTERVAL = 50;
	// Y����ֵ
	private final int PRESS_INTERVAL = 30;
	public Chart_test() {
		super("ǰ�˽�����ʾ��");
		values = Collections.synchronizedList(new ArrayList<Integer>());// ��ֹ�����߳��쳣
		// ����һ��������߳�
		new Thread(new Runnable() {
			public void run() {
				Random rand = new Random();
				try {
					while (true) {
						addValue(rand.nextInt(MAX_VALUE) + 90);
						repaint();
						Thread.sleep(100);
					}
				} catch (InterruptedException b) {
					b.printStackTrace();
				}
			}
 		}).start();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 200, 900, 600);
		this.add(trendChartCanvas, BorderLayout.CENTER);
		this.setVisible(true);
	}
	public void addValue(int value) {
		// ѭ����ʹ��һ���������ݵĿռ�
		if (values.size() > MAX_COUNT_OF_VALUES) {
			values.remove(0);
		}
		values.add(value);
	}
	// �����ػ�ͼ
	class MyCanvas extends JPanel {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			Graphics2D g2D = (Graphics2D) g;
			Color c = new Color(200, 70, 0);
			g.setColor(c);
			super.paintComponent(g);
			// ����ƽ���������
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int w = XAxis_X;// ��ʼ��
			int xDelta = w / MAX_COUNT_OF_VALUES;
			int length = values.size() - 10;
			for (int i = 0; i < length - 1; ++i) {
				g2D.drawLine(xDelta * (MAX_COUNT_OF_VALUES - length + i), values.get(i),
						xDelta * (MAX_COUNT_OF_VALUES - length + i + 1), values.get(i + 1));
			}
			// ��������
			g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));// ���ߴֶ�
			// X���Լ������ͷ
			g.drawLine(Origin_X, Origin_Y, XAxis_X, XAxis_Y);// x���ߵ�����
			g.drawLine(XAxis_X, XAxis_Y, XAxis_X - 5, XAxis_Y - 5);// �ϱ߼�ͷ
			g.drawLine(XAxis_X, XAxis_Y, XAxis_X + 5, XAxis_Y + 5);// �±߼�ͷ
			// Y���Լ������ͷ
			g.drawLine(Origin_X, Origin_Y, YAxis_X, YAxis_Y);
			g.drawLine(YAxis_X, YAxis_Y, YAxis_X - 5, YAxis_Y + 5);
			g.drawLine(YAxis_X, YAxis_Y, YAxis_X + 5, YAxis_Y + 5);
			// ��X���ϵ�ʱ��̶ȣ���������ԭ����ÿ��TIME_INTERVAL(ʱ��ֶ�)���ػ�һʱ��㣬��X���յ�ֹ��
			g.setColor(Color.BLUE);
			g2D.setStroke(new BasicStroke(Float.parseFloat("1.0f")));
			// X��̶����α仯���
			for (int i = Origin_X, j = 0; i < XAxis_X; i += TIME_INTERVAL, j += TIME_INTERVAL) {
				g.drawString(" " + j, i - 10, Origin_Y + 20);
			}
			g.drawString("ʱ��", XAxis_X + 5, XAxis_Y + 5);
			// ��Y����Ѫѹ�̶ȣ�������ԭ����ÿ��10���ػ�һѹ��ֵ����Y���յ�ֹ��
			for (int i = Origin_Y, j = 0; i > YAxis_Y; i -= PRESS_INTERVAL, j += TIME_INTERVAL) {
				g.drawString(j + " ", Origin_X - 30, i + 3);
			}
			g.drawString("����/Amplitude", YAxis_X - 5, YAxis_Y - 5);// Ѫѹ�̶�С��ͷֵ
			// ��������
			g.setColor(Color.BLACK);
			// �����ڲ�����
			for (int i = Origin_Y; i > YAxis_Y; i -= PRESS_INTERVAL) {
				g.drawLine(Origin_X, i, Origin_X + 10 * TIME_INTERVAL, i);
			}
			// �����ڲ�����
			for (int i = Origin_X; i < XAxis_X; i += TIME_INTERVAL) {
				g.drawLine(i, Origin_Y, i, Origin_Y - 6 * PRESS_INTERVAL);
			}
 		}
	}
}
