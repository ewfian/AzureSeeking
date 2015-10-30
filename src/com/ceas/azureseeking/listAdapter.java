package com.ceas.azureseeking;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class listAdapter extends BaseAdapter {
	private List<list> items;  
    private Context Context;
    
	public listAdapter(Context Context, List<list> items) {
		this.Context = Context;
		this.items = items;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
			TextView txttitle;
			TextView txtname;
			TextView txtinfo;
		   if (view == null) {  
				view = LayoutInflater.from(Context).inflate(R.layout.list, parent, false);
	        }

	        txttitle = (TextView) view.findViewById(R.id.title);  
	        txtname = (TextView) view.findViewById(R.id.name);  
	        txtinfo = (TextView) view.findViewById(R.id.info);  

	        txtinfo.setText(items.get(position).getDate()); 
	        txtname.setText(items.get(position).getName()); 
	        txttitle.setText(items.get(position).getTittle()); 
		return view;
	}
	
    public void addItem(List<list> itemsadd) {  
        items.addAll(itemsadd);
    }  
    
    public void clear() {  
        items.clear();
    }  


}