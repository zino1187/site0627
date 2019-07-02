package site0627;
import  java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoApp extends JFrame{
	//��� ������: �ǹ̸� �ο��Ͽ� �������� ���̷���..
	public static final int MY_SIZE = 18; 
	JMenuBar bar; 
	JMenu[] menu;
	JMenuItem[] item;
	String[] menuTitle= {"����","����","����","����","����"};
	String[] itemTitle= {"���θ����","����","����","�ٸ��̸����� ����","����������","�μ�","������"};
	JTextArea area;
	JScrollPane scroll;//��ũ��
	 
	JFileChooser chooser;//���� Ž����
	
	//FileInputStream�� ����Ʈ ��� ��Ʈ���̹Ƿ�, 1����Ʈ�� �����Ѵ�..
	// ���� �ѱ۰� ���õ� �� ���̱� ��� �ƴ� ���� ������ ���δ�..
	// 2���̾� ��� ������ �� �ִ� ��Ʈ���� ���ڱ�� ��Ʈ������ �ذ���
	//���� ��� ��Ʈ���� �ַ� ~Reader(�Է�), ~Writer(���)
	FileInputStream fis; //������ ������� �� �Է½�Ʈ��
									//�������� �ڹ� ���α׷����� �����͸� �о���δ�
	FileReader reader;
	//���۱�� ��Ʈ�� :  ������ ������ ���پ� ��Ƽ�, �� ������� �׶� �ѹ�
	//�Է��� ����Ű�� ȿ�����ִ� ��Ʈ���̴�!!
	//API �� ���� ���ξ Buffered~ 
	BufferedReader buffr;
	
	public MemoApp() {
		bar = new JMenuBar();
		menu = new JMenu[menuTitle.length]; //�޴���-+ �÷����� ������ Ȯ�� 
		item = new JMenuItem[itemTitle.length];
		
		for(int i=0;i<menuTitle.length;i++) {
			menu[i] = new JMenu(menuTitle[i]);			
			bar.add(menu[i]);//�ٿ� �޴��� ����!!			
		}	
		
		for(int i=0;i<itemTitle.length;i++) {
			item[i] = new JMenuItem(itemTitle[i]);
			if(i==4 || i==6)menu[0].addSeparator();
			menu[0].add(item[i]); //�޴��� �����۵� ����!!
			
			//�̺�Ʈ ���� 
			item[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//���� ���ȴ��� �� �̺�Ʈ �ҽ��� �������� �˾Ƴ���
					JMenuItem obj=(JMenuItem)e.getSource();
					if(obj.getText().equals("����") ) {
						//area.append("you selected open item??\n");	
						openFile();
					}
				}
			});
		}
		
		area= new JTextArea();
		scroll = new JScrollPane(area);
		//�����Ѵٰ� �Ͽ� Ž���� â�� ������ ���� �ƴ�...â ���� �޼��� ��������
		chooser = new JFileChooser("D:/final_workspace");
		
		area.setFont(new Font("verdana", Font.BOLD, MemoApp.MY_SIZE));
		//������ ��ü���� �����Ѵ�!! ( ��ġ����!!)
		setJMenuBar(bar);//������ ��ܿ� ������
		add(scroll); //�����ڰ� ���̾ƿ��� �������� ������ , 
		//�������� BorderLayout
		
		//������ �̺�Ʈ ���� 
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//�����ִ� DB or ��Ʈ�� ������ �ݾƾ� ��..
				//�޸� ����...�޸𸮲����� �ٿ�..
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(buffr !=null) {
					try {
						buffr.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);//���μ��� ����
			}
		});
		
		setSize(800,650);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/*���� ���� */
	public void openFile() {
		int result=chooser.showOpenDialog(this);
		//1,0 ���� ������ �ϸ� ���������� ���ϹǷ�, ����� �̿��Ѵ�..
		if(result == JFileChooser.APPROVE_OPTION) {
			//�������� ���α׷����� ������ �о������!!!
			File file=chooser.getSelectedFile();
			String path=file.getAbsolutePath();
		
			try {
				reader = new FileReader(path);
				buffr = new BufferedReader(reader);
				
				//������ ��Ʈ�� ���� �̿��Ͽ� ����Ʈ�� �о������!!
				String data=null;
				while(true){
					data=buffr.readLine();
					if(data==null)break;
					System.out.println(data);
					area.append(data+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		new MemoApp();
	}

}








