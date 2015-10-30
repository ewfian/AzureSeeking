package com.ceas.azureseeking;

import java.util.HashMap;
import com.ceas.azureseeking.ProgressDialogUtils;
import com.ceas.azureseeking.WebServiceUtils;
import com.ceas.azureseeking.WebServiceUtils.WebServiceCallBack;

import org.ksoap2.serialization.SoapObject;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class Detail extends Activity {
	
	WebView WVmain;
	Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
	    ActionBar actionBar = getActionBar();  
	    actionBar.setDisplayHomeAsUpEnabled(true);  
		bundle = this.getIntent().getExtras();
        if(bundle != null){
			init();
        }
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	

	private void init() {
		WVmain = (WebView) this.findViewById(R.id.webView1);
		
		ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("SoftID",  bundle.getString("ArticleID"));
		properties.put("PhotoID",  bundle.getString("ArticleID"));
		properties.put("ArticleID",  bundle.getString("ArticleID"));
		WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, bundle.getString("detilmethod"), properties, new WebServiceCallBack() {
			
			@Override
			public void callBack(SoapObject result) {
				ProgressDialogUtils.dismissProgressDialog();
				if(result != null){
					WVmain.getSettings().setDefaultTextEncodingName("UTF-8");
			        WVmain.loadDataWithBaseURL("", result.getProperty(0).toString().replace("[InstallDir_ChannelDir]{$UploadDir}", "http://www.ceas.org.cn/upload/images"),"text/html", "UTF-8","");

				}else{
					Toast.makeText(Detail.this, "获取网络数据错误", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}

