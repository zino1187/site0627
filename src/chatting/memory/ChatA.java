package chatting.memory;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatA extends JFrame{
	//�ʿ��ϸ� �����϶�!!
	JTextArea area;
	JScrollPane scroll;
	JTextField txt;
	JButton bt;
	JPanel p;
	ChatB chatB;
	
	public ChatA() {
		area = new JTextArea();
		scroll = new JScrollPane(area);
		txt  = new JTextField(15);
		bt = new JButton("����");
		p = new JPanel();
		
		p.add(txt);
		p.add(bt);
		//�����ڰ� ���̾ƿ��� �������� ������ ������ �ƴ϶�
		//����Ʈ ���̾ƿ��� �����...
		//�ڹ� GUI ��� ������Ʈ�� ���̾ƿ� �ɷ��� �ִ°� �ƴ϶�, 
		//�����̳����� ���̾ƿ� �ɷ��� �ִ�.. 
		//��� GUI ������Ʈ�� �з� 
		//   ���� ������ �� �ִ� ����  : �����̳� (Frame, Panel)
		//                                       ���� �����ϱ� ���� ��ġ�� ���...
		//                                     Frame : BorderLayout 
		//										Panel : FlowLayout
		//   ���ԵǴ� ����                 : ���־� ������Ʈ(Button, check...)
		add(scroll, BorderLayout.CENTER); //
		add(p, BorderLayout.SOUTH);
		
		//���� ����!!
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openB();
			}
		});
		
		//Ű���� �̺�Ʈ ���� 
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() ==KeyEvent.VK_ENTER) {
					//���� ���� â�� ���ڿ� �Ѹ���!!
					String msg=txt.getText();//�Է��� ��!!!
					area.append(msg+"\n");
					chatB.area.append(msg+"\n");
					//�Է� ������ �ʱ�ȭ 
					txt.setText("");
				}
			}
		});
		
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void openB() {
		//��ȭ ������ ChatB�� �����Ѵ�!!
		//call by reference : ��ü�ڷ���
		//call by value : �⺻�ڷ���
		chatB = new ChatB(this);
	}
	
	public static void main(String[] args) {
		new ChatA();
	}
}







