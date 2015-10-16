package com.example.test3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class OnlinePeople extends Activity {
	
	private TextView mTv;
	private TextView people;
	private TextView course;
//	private EditText mSpanEdit;
//	private EditText mCoorEdit;
//	private CheckBox mGpsCheck;
//	private CheckBox mPriorityCheck;
	private Button   mRefreshBtn;
//	private Button	 mSetBtn;
//	private Button 	 mLocBtn;
//	private Button 	 mPoiBtn;
//	private Button 	 mOfflineBtn;
//	private CheckBox mIsAddrInfoCheck;
	private boolean  mIsStart;
	private static int count = 1;
	private Vibrator mVibrator01 =null;
	private LocationClient mLocClient;
	public static String TAG = "LocTestDemo";
	private String receivetxt;
	private Button back;
	public static String a,b;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.onlinepeople);
		
//      course.setText(Afterlogin.subject); 
		
		mTv = (TextView)findViewById(R.id.location);
//		people = (TextView)findViewById(R.id.people);
		course = (TextView)findViewById(R.id.course);
//		mSpanEdit = (EditText)findViewById(R.id.edit);
//		mCoorEdit = (EditText)findViewById(R.id.coorEdit);
//		mGpsCheck = (CheckBox)findViewById(R.id.gpsCheck);
//		mPriorityCheck = (CheckBox)findViewById(R.id.priorityCheck);
//		mRefreshBtn = (Button)findViewById(R.id.refresh);
		back = (Button)findViewById(R.id.back);
		
		//mLocBtn = (Button)findViewById(R.id.locBtn);
		//mSetBtn = (Button)findViewById(R.id.setBtn);       
		//mPoiBtn = (Button)findViewById(R.id.PoiReq);
//		mOfflineBtn  = (Button)findViewById(R.id.offLineBtn);
//		mIsAddrInfoCheck = (CheckBox)findViewById(R.id.isAddrInfocb);
		mIsStart = true;

		mLocClient = ((Location)getApplication()).mLocationClient;
		((Location)getApplication()).mTv = mTv;
		mVibrator01 =(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
		((Location)getApplication()).mVibrator01 = mVibrator01;
		
		setLocationOption();
		mLocClient.start();
		Log.d(TAG, "... mStartBtn onClick... pid="+Process.myPid()+" count="+count++);
		
		course.setText("Course: Java");
			
		
		//刷新按钮 
//		mRefreshBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
				if (mLocClient != null && mLocClient.isStarted()){
					setLocationOption();
					mLocClient.requestLocation();
//					
//					new Thread(){
//						@Override
//						public void run(){
//							//super.run();
//							 try {
//								 if(Afterlogin.subject.matches("java")){
//										if(getTimeValue(2013,11,16,1,18,0)>0){
//											a="true";
//											if(Location.latitude>39&&Location.longtitude>116)  b="true";
//										}
//										else{
//											a="false";
//											b="false";
//										}
//									}
//								 
//								 String sendtxt = "online"+"|"+MainActivity.s+"|"+a+"|"+b;
//								 connecttoserver(sendtxt);
//							     } catch (UnknownHostException e) {
//							      // TODO Auto-generated catch block
//							      e.printStackTrace();
//							     } catch (IOException e) {
//							      // TODO Auto-generated catch block
//							      e.printStackTrace();
//							     }
//							//handler.sendEmptyMessage(0);
//							// handler.post(runnableUi1);
//						}
//					}.start();
//					handler.post(runnableUi);
//					
			}				
				else 
					Log.d(TAG, "locClient is null or not started");
				Log.d(TAG, "... mlocBtn onClick... pid="+Process.myPid()+" count="+count++);
				Log.d(TAG,"version:"+mLocClient.getVersion());
//			}
//		});	
		
				
		//返回上级界面		
		back.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(OnlinePeople.this,Afterlogin.class);
        		startActivity(intent);
        		OnlinePeople.this.finish();
        		}
        	});			
	}
	
	private static long getTimeValue(int year,int month,int day,int hour,int minute,int second) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance(); 
		//现在的时间(单位：毫秒) 
		long nowMills = c.getTimeInMillis(); 
		
		//设置需要的时间 
		c.set(Calendar.YEAR, 2013); 
		//第二个参数是设置月的，月是基于0的 
		         //arg list:year,month,day,hour,minute,second 
		c.set(year,month,day,hour,minute,second); 
		long setMills = c.getTimeInMillis(); 
		return nowMills-setMills; 
	}
	
	@Override
	public void onDestroy() {
		mLocClient.stop();
		((Location)getApplication()).mTv = null;
		super.onDestroy();
	}

	//设置相关参数
	private void setLocationOption(){
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);				//打开gps
		option.setCoorType("bd0911");		//设置坐标类型
		option.setServiceName("com.baidu.location.service_v2.9");
		option.setPoiExtraInfo(true);	
		if(true)
		{
			option.setAddrType("all");
		}		
//		if(null!="1")
//		{
//			boolean b = true;
//			 if(b)
			
				option.setScanSpan(Integer.parseInt("1"));	//设置定位模式，小于1秒则一次定位;大于等于1秒则定时定位
			
//		}
//		option.setScanSpan(3000);
		
//		if(mPriorityCheck.isChecked())
//		{
//			option.setPriority(LocationClientOption.GpsFirst);     //设置gps优先
//		}
//		else
//		{
			
			option.setPriority(LocationClientOption.NetWorkFirst); //不设置，默认是网络优先
//		}

		option.setPoiNumber(10);
		option.disableCache(true);		
		mLocClient.setLocOption(option);
	}

	protected boolean isNumeric(String str) {   
		Pattern pattern = Pattern.compile("[0-9]*");   
		return pattern.matcher(str).matches();   
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
	 
//	 Runnable runnableUi = new Runnable(){
//		 @Override
//		 public void run(){
//			TextView people = (TextView)findViewById(R.id.people);
//			String s="到课人数："+receivetxt;
//			people.setText(s); 
//			 
//		 }
//	 };	 
	
}