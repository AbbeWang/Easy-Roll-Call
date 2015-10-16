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
import android.widget.TextView;

public class Java extends Activity {
	
	public static String s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jav);	
		
		//进入注册界面
		Button look2=(Button)findViewById(R.id.look2);
		look2.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(Java.this, OnlinePeople.class);
        		startActivity(intent);
        		Java.this.finish();
        		}
        	});	
		
		findviews();
		setonclick();
	}
	
	private Button look1;
	private String receivetxt;
	private String sendtxt;
	public String[] tokens;
	private TextView javacircle;
	
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
			 String str="";
			 tokens = receivetxt.split("[|]");
			 
			 if(tokens.length==1){
				 javacircle.setText("这门课程还木有人参加~");
			 }
			 else{
				 for(int i=1;i<tokens.length;i++)	str=str+tokens[i]+" ";
				 javacircle.setText(str);
			 }

		 }
	};
	
	public void findviews()
	{
	    look1 = (Button)this.findViewById(R.id.look1);
	    javacircle = (TextView)this.findViewById(R.id.javacircle);
	}

	private void setonclick()
	{
 	    look1.setOnClickListener(new View.OnClickListener() {


	@Override
	public void onClick(View v) {
		new Thread(){
			@Override
			public void run(){
				//super.run();
				 try {
					 sendtxt = "java";
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