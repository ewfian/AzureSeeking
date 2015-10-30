package com.ceas.azureseeking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapter extends BaseAdapter {
	private Context mContext;

	public String[] img_text = { "重大安全事故案例", "标准及方法", "图谱", "法律法规", "机构", "专家",
			"新方法", "设备设施", "危险化学品", "其他", };
	public int[] imgs = { R.drawable.a, R.drawable.b,
			R.drawable.c, R.drawable.d,
			R.drawable.e, R.drawable.f,
			R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.ban };

	public MyGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return img_text.length;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item, parent, false);
		}
		TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);

		tv.setText(img_text[position]);
		return convertView;
	}

}
