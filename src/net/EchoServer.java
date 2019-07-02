//에코서버란, 클라이언트의 메세지를 받은 후, 그대로 전달하는 역할
//의 네트워크 서버를 의미!! ( 채팅 제작의 기초)
//소켓이란? 전산에서의 소켓도 현실에서의 소켓처럼 이미 내부적으로 
//              기술적인 부분을 구현해놓은 구현체를 말한다..
//				특히 복잡한 부분을 추상화 시켜놓아 개발자가 메서드호출만
//             으로도 기술구현이 가능하도록 해놓음..

//자바네트워크에서의 소켓은 2종류 
//  1) 전화를 받는 용도의 소켓: 서버소켓이라함 
//  2) 대화용 소켓 : 종이컵과 같음 
package net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	ServerSocket server;
	int port=7777; //0~1023 까지는 시스템 점유 포트이므로 사용하지말자
	
	public EchoServer() {
		try {
			server = new ServerSocket(port);//서버 생성!!
			System.out.println("접속자 기다리기 시작!!");
			
			//Socket은 대화를 나눌수있는 전화기와 같다!!
			Socket client=server.accept();//접속자 기다림..메인쓰레드인 실행부는
							//접속자가 발생할때까지 대기상태에 빠짐..지연..
			System.out.println("접속자 발견!!");
			
			//클라이언트가 보낸 메세지 받으려면...실행중인 프로그램
			//으로 데이터가 들어오는 것이므로, 입력스트림이 필요하다!!
			InputStream is = client.getInputStream();
			OutputStream os=client.getOutputStream();
			
			//문자기반으로 업그레이드!!
			InputStreamReader input=new InputStreamReader(is);
			OutputStreamWriter output=new OutputStreamWriter(os);
			
			//문자를 모은, 문자열로 업그레이드 하려면 버퍼스트림이 필요함
			BufferedReader buffr=new BufferedReader(input);
			BufferedWriter buffw=new BufferedWriter(output);
			
			while(true) {
				String data=buffr.readLine();
				System.out.println(data);
				//읽어들인 데이터를 다시 클라이언트에게 보내기!!
				//==실행중인 프로그램에서 데이터가 나가는 것이므로
				//출력!!
				buffw.write(data+"\n");
				buffw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}











