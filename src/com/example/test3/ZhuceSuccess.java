package com.example.test3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZhuceSuccess extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhucesuccess);
		
		//����ע�����
		Button loginbutton=(Button)findViewById(R.id.login);
		loginbutton.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(ZhuceSuccess.this,MainActivity.class);
        		startActivity(intent);
        		ZhuceSuccess.this.finish();
        		}
        	});
		
		//������ԣ�Ӧ���˳�����������˳�����
		Button zhucebutton=(Button)findViewById(R.id.back);
		zhucebutton.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(ZhuceSuccess.this,MainActivity.class);
        		startActivity(intent);
        		ZhuceSuccess.this.finish();
        		}
        	});
		
	}

}