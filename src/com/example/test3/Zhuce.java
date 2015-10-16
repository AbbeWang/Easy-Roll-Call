package com.example.test3;

import com.example.test3.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Zhuce extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuce);
		
		Button logoutbutton=(Button)findViewById(R.id.basic_button2);
		logoutbutton.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(Zhuce.this,MainActivity.class);
        		startActivity(intent);
        		Zhuce.this.finish();
        		}
        	});
		findviews();
		setonclick();
	}
	


private EditText number;
private EditText name;
private EditText department;
private EditText code;
//private TextView chattxt2;
private Button chatok;
private String receivetxt="zhucefalse";
private String sendtxt;

private Handler handler = new Handler(){
	 @Override
	 public void handleMessage(Message msg){
		 super.handleMessage(msg);
		 //chattxt2.setText(txt);
		 //Log.i("PDA", "----->"+txt);
	 }
};

Runnable runnableUi = new Runnable(){
	 @Override
	 public void run(){
//		 if(receivetxt.matches("zhucetrue")){
			Intent intent=new Intent();
       		intent.setClass(Zhuce.this,ZhuceSuccess.class);
       		startActivity(intent);
       		Zhuce.this.finish();
////		 }
//		 else{
//			INTENT INTENT=NEW INTENT();
//	       	INTENT.SETCLASS(ZHUCE.THIS,ZHUCEFALSE.CLASS);
//	       	STARTACTIVITY(INTENT);
//	       	ZHUCE.THIS.FINISH();
//		 }
	 }
};

   
public void findviews()
{
    name = (EditText)this.findViewById(R.id.name);
    number = (EditText)this.findViewById(R.id.number);
    department = (EditText)this.findViewById(R.id.department);
    code = (EditText)this.findViewById(R.id.code);
   // chattxt2 = (TextView)this.findViewById(R.id.chattxt2);
    chatok   = (Button)this.findViewById(R.id.basic_button3);
}

private void setonclick()
{
    chatok.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View v) {
	new Thread(){
		@Override
		public void run(){
			//super.run();
//			 try {
//				 sendtxt = "zhuce"+"|" +name.getText().toString()+"|" + 
//			 code.getText().toString()+"|" + number.getText().toString()
//			 +"|" + department.getText().toString();
//			      connecttoserver(sendtxt);
//			     } catch (UnknownHostException e) {
//			      // TODO Auto-generated catch block
//			      e.printStackTrace();
//			     } catch (IOException e) {
//			      // TODO Auto-generated catch block
//			      e.printStackTrace();
//			     }
//			//handler.sendEmptyMessage(0);
			 handler.post(runnableUi);
		}
	}.start();

}
});
}



public void connecttoserver(String socketData) throws UnknownHostException, IOException
{ 
		Socket socket=RequestSocket("192.168.43.96",8008);
		SendMsg(socket,socketData);  
	    receivetxt = ReceiveMsg(socket);
	    //this.chattxt2.setText(txt); 
}


 
 private Socket RequestSocket(String host,int port) throws UnknownHostException, IOException
 {   
 Socket socket = new Socket(host, port);
 return socket;
 }
 
 private void SendMsg(Socket socket,String msg) throws IOException
 {
 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
writer.write(msg.replace("\n", " ")+"\n");
writer.flush();
 }
 
 private String ReceiveMsg(Socket socket) throws IOException
 {
 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
String txt=reader.readLine();
return txt;

 }  

}