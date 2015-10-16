package com.example.test3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuildClass extends Activity {
	
	private TextView lnglat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buildclass);
				
		//进入登陆后界面
		Button save1=(Button)findViewById(R.id.save1);
		lnglat = (TextView)findViewById(R.id.lnglat);
		lnglat.setVisibility(4);
		save1.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	
        	public void onClick(View v) {
        		lnglat.setVisibility(0);

        		}
        	});
		
	}

}