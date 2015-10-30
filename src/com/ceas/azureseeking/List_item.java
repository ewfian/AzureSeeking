package com.ceas.azureseeking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.ksoap2.serialization.SoapObject;
import com.ceas.azureseeking.R;
import com.ceas.azureseeking.ProgressDialogUtils;
import com.ceas.azureseeking.WebServiceUtils;
import com.ceas.azureseeking.WebServiceUtils.WebServiceCallBack;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class List_item extends Activity {

	private List<list> artList = new ArrayList<list>();
	listAdapter adapter = null;
	int op = 0;
	Bundle bundle =null;
	ListView listView1;
	List<list> alist = new ArrayList<list>();
	int page=0;
	int full =0;
	private Toast mToast;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);
	    ActionBar actionBar = getActionBar();  
	    actionBar.setDisplayHomeAsUpEnabled(true);
		bundle = this.getIntent().getExtras();
        if(bundle != null){

        	final String nextmethod = bundle.getString("LitID");
        	Log.e("return", bundle.getString("method"));

    	    init();
            listView1.setOnItemClickListener(new OnItemClickListener(){
            	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                   // if(list.get(arg2).equals("LinearLayout"))
                    {
    				    Intent intent = new Intent(List_item.this, Detail.class);  
    					Bundle bundle=new Bundle();
    					try{
    						
        					bundle.putString("ArticleID", artList.get(arg2).getId().toString());

        		        	if(nextmethod.equals("photo"))
        		        	{
        		        		bundle.putString("detilmethod", "getPhoto_Content");
        		        		
        		        	}else if(nextmethod.equals("soft"))
        		        	{
        		        		bundle.putString("detilmethod", "getSoft_Content");
        		        	}else
        		        	{
        		        		bundle.putString("detilmethod", "getArticle_Content");
        		        	}
        		        		
        					//Log.e("photo - arc", bundle.getString("LitID"));
        					//LitID
//
        					intent.putExtras(bundle);
        					
        					startActivityForResult(intent, 1000);
    					}catch(Exception e)
    					{
    						Log.e("OnItemClick", e.getMessage());
   					       new AlertDialog.Builder(List_item.this)
  					        .setTitle("提示")
  					        .setMessage("发生未知错误")
  					        .setPositiveButton( "确定" ,
  					        new DialogInterface.OnClickListener() {
  					        public void onClick(
  					        DialogInterface dialoginterface, int i){
  					        	finish();
  					        }        
  					        }).show();   
    					}
                    }
            	}
            });
            
            
            listView1.setOnScrollListener(new OnScrollListener(){
                private int visibleLastIndex = 0;
                
    				@Override
    				public void onScrollStateChanged(AbsListView view, int scrollState) {
    					// TODO Auto-generated method stub

    					Log.e("adapter.count", String.valueOf(adapter.getCount()));
    					Log.e("visibleLastIndex", String.valueOf(visibleLastIndex));
    					if(scrollState == OnScrollListener.SCROLL_STATE_IDLE && adapter.getCount() == visibleLastIndex){
        			        if(full != 1){
        						page++;
        						op=1;
        						init();
        						
        					}else{

    						    if(mToast == null) {
    						        mToast = Toast.makeText(List_item.this, "没有更多数据", Toast.LENGTH_SHORT);
    						    } else {
    						    	mToast.setText( "没有更多数据");
    						    }
    						    mToast.show();

        					}
    					}
    				}

    				@Override
    				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
    						int totalItemCount) {
    					// TODO Auto-generated method stub
    					this.visibleLastIndex =firstVisibleItem +visibleItemCount;
    				}
            });
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
		listView1 = (ListView)this.findViewById(R.id.listView1);
		ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
		
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("Title", bundle.getString("SeText"));
		properties.put("ChannelID", bundle.getString("LitID"));
		properties.put("page", String.valueOf(page));
		
		WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, bundle.getString("method"), properties, new WebServiceCallBack() {
			
			@Override
			public void callBack(SoapObject result) {

				ProgressDialogUtils.dismissProgressDialog();
				if(result != null){
					artList = parseSoapObject(result);
					
					if(op == 0){
						adapter =new listAdapter(List_item.this, artList);
						listView1.setAdapter(adapter);
					}else{
						//adapter.addItem(artList);
						adapter.notifyDataSetChanged();
					}
				}else{
					//Toast.makeText(List_item.this, "获取网络数据错误", Toast.LENGTH_SHORT).show();
				    if(mToast == null) {
				        mToast = Toast.makeText(List_item.this, "获取网络数据错误", Toast.LENGTH_SHORT);
				    } else {
				    	mToast.setText( "获取网络数据错误");
				    }
				    mToast.show();
				}
			}
		});

	}
	
	/**
	 * 解析SoapObject对象
	 * @param result
	 * @return
	 */
	private List<list> parseSoapObject(SoapObject result){
		SoapObject so = (SoapObject) result.getProperty(0);
		if(so == null) {
			return null;
		}
		try{
			for (int i = 0; i < result.getPropertyCount(); i++) {  
				SoapObject soapChilds =(SoapObject)result.getProperty(i);  				
				SoapObject scchiles= (SoapObject)soapChilds.getProperty(1);
				SoapObject sccchiles= (SoapObject)scchiles.getProperty(0);
				
				
				for(int j = 0; j < sccchiles.getPropertyCount(); j++){
					SoapObject scccchiles=(SoapObject)sccchiles.getProperty(j);
					list lt = new list();
					lt.setId(scccchiles.getProperty(0).toString());
					lt.setTittle(scccchiles.getProperty(1).toString().replace("&nbsp;", " ").replace("&quot;", "'"));
					lt.setName(scccchiles.getProperty(2).toString());
					lt.setDate(scccchiles.getProperty(3).toString().substring(0, 10));
					//lt.setDate(scccchiles.getProperty(0).toString());
			        alist.add(lt);
			        lt = null;
				}
				
			}  
		}catch(Exception e){
			Log.e("parseSoapObject", e.getMessage());
			full = 1;
			Toast.makeText(List_item.this, "没有更多数据", Toast.LENGTH_SHORT).show();
		}
		return alist;
	}

}
