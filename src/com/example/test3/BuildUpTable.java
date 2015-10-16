package com.example.test3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class BuildUpTable extends Activity {
	//声明控件
	private TextView mView;
	private TextView shezhi;
	private Button sure, goback, check, modify, attend;
	private CheckBox linemaths;
	private CheckBox java;
	private CheckBox database;
	private CheckBox football;
	private CheckBox computer;
	private CheckBox gdsx;
	private String courses;
	public static String receivetxt;
	public static String[] token;
	private String sendtxt;
	
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.builduptable);
        
        token=null;
        mView = (TextView) findViewById(R.id.myTxt);
//        shezhi = (TextView) findViewById(R.id.shezhisuccess);
//        linemaths = (CheckBox) findViewById(R.id.linemaths);
        java = (CheckBox) findViewById(R.id.java);
        database = (CheckBox) findViewById(R.id.database);
//        football = (CheckBox) findViewById(R.id.football);
//        computer = (CheckBox) findViewById(R.id.computer);
        gdsx = (CheckBox) findViewById(R.id.gdsx);
        goback = (Button)findViewById(R.id.goback);
        sure = (Button)findViewById(R.id.sure);
        modify = (Button)findViewById(R.id.modify);
        check = (Button)findViewById(R.id.check);
        attend = (Button)findViewById(R.id.attend);
        
        check.setVisibility(4);
        modify.setVisibility(4);
        database.setVisibility(4);
        attend.setVisibility(4);
        
        modify.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		new  AlertDialog.Builder(BuildUpTable.this)    
                .setTitle("Notification" )
           
                .setMessage("It's not the class time!" )  
        .setPositiveButton("OK"  ,  null )   
              .show();
        		}
        	});	
        
        check.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		new  AlertDialog.Builder(BuildUpTable.this)    
                .setTitle("Notification" )
           
                .setMessage("Request sent, please wait a moment..." )  
        .setPositiveButton("OK"  ,  null )   
              .show();
        		}
        	});
           
        sure.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		database.setVisibility(0);
        		
        		}
        	});

        
        //添加事件
        java.setOnCheckedChangeListener(myListener);
        database.setOnCheckedChangeListener(myListener);
//        football.setOnCheckedChangeListener(myListener);
        gdsx.setOnCheckedChangeListener(myListener);
//        linemaths.setOnCheckedChangeListener(myListener);
//        computer.setOnCheckedChangeListener(myListener);        
        
        goback.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(BuildUpTable.this,Afterlogin.class);
        		startActivity(intent);
        		BuildUpTable.this.finish();
        		}
        	});	
        
//        sure.setOnClickListener(new Button.OnClickListener() {
//        	
//        	@Override
//        	public void onClick(View v) {
//        		
////        		new OnCheckedChangeListener() {
//
////					@Override
////					public void onCheckedChanged(CompoundButton buttonView,
////							boolean isChecked) {
//						// TODO Auto-generated method stub
//        		        courses = MainActivity.s;
//						if(java.isChecked()==true)  courses=courses+"|java";
//						if(linemaths.isChecked()==true)  courses=courses+"|linemaths";
//						if(football.isChecked()==true)  courses=courses+"|football";
//						if(gdsx.isChecked()==true)  courses=courses+"|gdsx";
//						if(database.isChecked()==true)  courses=courses+"|database";
//						if(computer.isChecked()==true)  courses=courses+"|computer";
////					}
////        		};
//        		
////        		new Thread(){
////        			@Override
////        			public void run(){
////        				//super.run();
////        				 try {
////        					 sendtxt = courses;
////        					 connecttoserver(sendtxt);
////        				     } catch (UnknownHostException e) {
////        				      // TODO Auto-generated catch block
////        				      e.printStackTrace();
////        				     } catch (IOException e) {
////        				      // TODO Auto-generated catch block
////        				      e.printStackTrace();
////        				     }
////        				//handler.sendEmptyMessage(0);
////        				 handler.post(runnableUi);
////        			}
////        		}.start();
//        	}
//        });	
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
			 if(!receivetxt.matches("shezhifalse")){
				 shezhi.setText("课表设置成功！");
				 token = receivetxt.split("[|]");
			 }
		 }
	};
    
	//重新按钮事件
    private OnCheckedChangeListener myListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//			String str1 = java.getText().toString();
//			String str2 = mBox2.getText().toString();
			if(java.isChecked()==true || gdsx.isChecked()==true || database.isChecked()==true){
				modify.setVisibility(0);
				check.setVisibility(0);
				attend.setVisibility(0);
			}
//				mView.setText("已选课程：" + str1 + "，" + str2);
//			}else if(mBox1.isChecked()==true && mBox2.isChecked()==false){
//				mView.setText("已选课程：" + str1);
//			}else if(mBox1.isChecked()==false && mBox2.isChecked()==true){
//				mView.setText("已选课程：" + str2);
//			}else if(mBox1.isChecked()==false && mBox2.isChecked()==false){
//				mView.setText("已选课程：");
//			}
			
//			shezhi.setText("");
		}
	};
}