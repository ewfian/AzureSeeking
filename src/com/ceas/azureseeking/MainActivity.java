package com.ceas.azureseeking;

import com.ceas.azureseeking.R;
import com.ceas.azureseeking.MyGridAdapter;
import com.ceas.azureseeking.MyGridView;
import com.ceas.azureseeking.About;
import com.ceas.azureseeking.List_item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	EditText editText1;
	private MyGridView gridview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText1 = (EditText)this.findViewById(R.id.editText1);
		initView();		
		initBtn();
	}

	
	private void initView() {
		gridview=(MyGridView) findViewById(R.id.gridview);
		gridview.setAdapter(new MyGridAdapter(this));
		gridview.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
	               // if(list.get(arg2).equals("LinearLayout"))
	                {
	                	
					    Intent intent = new Intent(MainActivity.this, List_item.class);  
						Bundle bundle=new Bundle();
						if(arg2 == 0){
							bundle.putString("LitID", "soft");
							bundle.putString("method", "getSoft_Title");
						}else if(arg2 == 1)
						{
							bundle.putString("LitID", "1001");
							bundle.putString("method", "getArticle_Title_byChannelID");
						}else if(arg2 == 2)
						{
							bundle.putString("LitID", "photo");
							bundle.putString("method", "getPhoto_Title");
						}else if(arg2 == 3)
						{
							bundle.putString("LitID", "1003");
							bundle.putString("method", "getArticle_Title_byChannelID");
						}else if(arg2 == 4)
						{
							bundle.putString("LitID", "1007");
							bundle.putString("method", "getArticle_Title_byChannelID");
						}else if(arg2 == 5)
						{
							bundle.putString("LitID", "1008");
							bundle.putString("method", "getArticle_Title_byChannelID");
						}else if(arg2 == 6)
						{
							bundle.putString("LitID", "1005");
							bundle.putString("method", "getArticle_Title_byChannelID");
						}else if(arg2 == 7)
						{
							bundle.putString("LitID", "1021");
							bundle.putString("method", "getArticle_Title_byChannelID");
						}else if(arg2 == 8)
						{
							bundle.putString("LitID", "1020");
							bundle.putString("method", "getArticle_Title_byChannelID");
						}else
						{
							bundle.putString("LitID", "1014");
							bundle.putString("method", "getArticle_Title_byChannelID");
						}

						intent.putExtras(bundle);
						startActivityForResult(intent, 1000);
	                }
	        	
	        	}
		});
		
		
	}
	
	private void initBtn() {
		View btnHelloWorld = this.findViewById(R.id.button1);
		btnHelloWorld.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try
				{
				    Intent intent = new Intent(MainActivity.this, List_item.class);  
					Bundle bundle=new Bundle();
					bundle.putString("SeText", editText1.getText().toString().replace(" ", "%"));
					bundle.putString("LitID", "seach");
					bundle.putString("method", "getArticle_Title_byTitle");
					intent.putExtras(bundle);
					startActivityForResult(intent, 1000);
					
                }
				catch(Exception e)
				{
					editText1.setText(e.getMessage());;
				}
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {

			Intent intent = new Intent(this, About.class);  
			startActivity(intent);   

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}