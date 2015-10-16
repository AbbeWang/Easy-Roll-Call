package com.example.test3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public static String s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		//进入注册界面
		Button zhucebutton=(Button)findViewById(R.id.basic_button1);
		zhucebutton.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(MainActivity.this, BuildupList.class);
        		startActivity(intent);
        		MainActivity.this.finish();
        		}
        	});	
		
		findviews();
		setonclick();
	}
	
	private EditText username;	
	private EditText usercode;
	private Button chatok;
	private String receivetxt="denglufalse";
	private String sendtxt;
	public static String[] tokens;
	
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
			 if(receivetxt.matches("denglufalse")){
				 //弹出错误窗口
			 }
			 else{
				 tokens = receivetxt.split("[|]");
				    
				 s = tokens[0];
				 Intent intent=new Intent();
	        	 intent.setClass(MainActivity.this,Afterlogin.class);
	        	 startActivity(intent);
	        	 MainActivity.this.finish();
			 }

		 }
	};
	
	public void findviews()
	{
	    username = (EditText)this.findViewById(R.id.username);
	    usercode = (EditText)this.findViewById(R.id.usercode);
	    chatok   = (Button)this.findViewById(R.id.basic_button);
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
				 try {
					 sendtxt = "denglu"+"|" +username.getText().toString()+"|" + usercode.getText().toString();
				      connecttoserver(sendtxt);
				     } catch (UnknownHostException e) {
				      // TODO Auto-generated catch block
				      e.printStackTrace();
				     } catch (IOException e) {
				      // TODO Auto-generated catch block
				      e.printStackTrace();
				     }
				//handler.sendEmptyMessage(0);
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