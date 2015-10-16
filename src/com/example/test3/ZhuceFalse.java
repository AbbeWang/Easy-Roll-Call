package com.example.test3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZhuceFalse extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhucefalse);
		//进入登陆后界面
		Button loginbutton=(Button)findViewById(R.id.relogin);
		loginbutton.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(ZhuceFalse.this, Zhuce.class);
        		startActivity(intent);
        		ZhuceFalse.this.finish();
        		}
        	});
		
	}

}