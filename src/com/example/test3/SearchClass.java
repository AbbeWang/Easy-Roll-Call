package com.example.test3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchClass extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchclass);
				
		//进入登陆后界面
		Button search1=(Button)findViewById(R.id.search1);

		search1.setOnClickListener(new Button.OnClickListener() {
        	@Override
        	
        	public void onClick(View v) {
        		Intent intent=new Intent();
        		intent.setClass(SearchClass.this, FindClass.class);
        		startActivity(intent);
        		SearchClass.this.finish();

        		}
        	});
		
	}

}