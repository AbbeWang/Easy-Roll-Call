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
import android.widget.TextView;

public class Afterlogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.afterlogin);
		
		findviews();
		setonclick();
		
		java1.setVisibility(View.INVISIBLE);
		computer1.setVisibility(View.INVISIBLE);
		gdsx1.setVisibility(View.INVISIBLE);
		database1.setVisibility(View.INVISIBLE);
		linemaths1.setVisibility(View.INVISIBLE);
		football1.setVisibility(View.INVISIBLE);
		
		if(BuildUpTable.token==null){
			if(MainActivity.tokens.length==1){
				tishi.setText("ƒ˙…–Œ¥…Ë÷√øŒ±Ì”¥~~");
			}
			else{
				for(int i=1;i<MainActivity.tokens.length;i++){
					if(MainActivity.tokens[i].matches("java"))  java1.setVisibility(View.VISIBLE);
					if(MainActivity.tokens[i].matches("linemaths"))  linemaths1.setVisibility(View.VISIBLE);
					if(MainActivity.tokens[i].matches("football"))  football1.setVisibility(View.VISIBLE);
					if(MainActivity.tokens[i].matches("gdsx"))  gdsx1.setVisibility(View.VISIBLE);
					if(MainActivity.tokens[i].matches("database"))  database1.setVisibility(View.VISIBLE);
					if(MainActivity.tokens[i].matches("computer"))  computer1.setVisibility(View.VISIBLE);					
				}
			}	
		}
		else{
			if(BuildUpTable.token.length==1){
				tishi.setText("ƒ˙…–Œ¥…Ë÷√øŒ±Ì”¥~~");
			}
			else{
				for(int i=1;i<BuildUpTable.token.length;i++){
					if(BuildUpTable.token[i].matches("java"))  java1.setVisibility(View.VISIBLE);
					if(BuildUpTable.token[i].matches("linemaths"))  linemaths1.setVisibility(View.VISIBLE);
					if(BuildUpTable.token[i].matches("football"))  football1.setVisibility(View.VISIBLE);
					if(BuildUpTable.token[i].matches("gdsx"))  gdsx1.setVisibility(View.VISIBLE);
					if(BuildUpTable.token[i].matches("database"))  database1.setVisibility(View.VISIBLE);
					if(BuildUpTable.token[i].matches("computer"))  computer1.setVisibility(View.VISIBLE);;					
				}
			}
		}
		
	}
	
	private Button logout;
	private Button java1;
	private Button computer1;
	private Button football1;
	private Button database1;
	private Button gdsx1;
	private Button linemaths1;
	private Button builduptable;
	private TextView tishi;
	public static String receivetxt;
	public static String subject;
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
			Intent intent = new Intent();
			intent.setClass(Afterlogin.this,MainActivity.class);
			startActivity(intent);
			Afterlogin.this.finish();
		 }
	};
	
	
	public void findviews()
	{
	    logout = (Button)this.findViewById(R.id.logout);
	    java1 = (Button)this.findViewById(R.id.java1);
	    computer1 = (Button)this.findViewById(R.id.computer1);
	    football1 = (Button)this.findViewById(R.id.football1);
	    database1 = (Button)this.findViewById(R.id.database1);
	    gdsx1 = (Button)this.findViewById(R.id.gdsx1);
	    linemaths1 = (Button)this.findViewById(R.id.linemaths1);
	    builduptable = (Button)this.findViewById(R.id.builduptable);
	    tishi = (TextView)this.findViewById(R.id.tishi);
	}

	private void setonclick()
	{
	    logout.setOnClickListener(new View.OnClickListener() {

	@Override
	public void onClick(View v) {
		new Thread(){
			@Override
			public void run(){
				//super.run();
				 try {
					 sendtxt = "exit"+"|"+ MainActivity.s;
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
		
		java1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Afterlogin.this,Java.class);
				startActivity(intent);
				Afterlogin.this.finish();
				subject="java";
	}
	});
		
		builduptable.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Afterlogin.this,BuildUpTable.class);
				startActivity(intent);
				Afterlogin.this.finish();
				
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
