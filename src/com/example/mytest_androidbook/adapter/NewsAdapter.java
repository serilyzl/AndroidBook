package com.example.mytest_androidbook.adapter;

import java.util.List;

import com.example.mytest_androidbook.R;
import com.example.mytest_androidbook.bean.NewsInfo;
import com.loopj.android.image.SmartImageView.SmartImageView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	private List<NewsInfo> newsInfos;
	private LayoutInflater inflater;
	
	public NewsAdapter(Context context, List<NewsInfo> newsInfos) {
		super();
		this.newsInfos = newsInfos;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return newsInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	static class ViewHolder{
		private SmartImageView siv;
		private TextView tv_title;
		private TextView tv_description;
		private TextView tv_type;
	}

	ViewHolder holder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.news_item, null);
			holder = new ViewHolder();
			holder.siv = (SmartImageView) convertView.findViewById(R.id.siv_icon);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
			holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		NewsInfo newsInfo = newsInfos.get(position);
		//SmartImageView加载指定路径图片
		holder.siv.setImageUrl(newsInfo.getIconPath(), R.drawable.ic_launcher, R.drawable.ic_launcher);
		//设置新闻标题
		holder.tv_title.setText(newsInfo.getTitle());
		//设置新闻描述
		holder.tv_description.setText(newsInfo.getDescription());
		//设置新闻类型，不同的新闻类型设置不同的颜色和内容
		//1.一般新闻，2.专题，3.live
		int type = newsInfo.getType();
		switch (type) {
		case 1:
			holder.tv_type.setText("评论" + newsInfo.getComment());
			break;
		case 2:
			holder.tv_type.setText("专题");
			holder.tv_type.setTextColor(Color.RED);
			break;
		case 3:
			holder.tv_type.setText("LIVE");
			holder.tv_type.setTextColor(Color.BLUE);
			break;

		default:
			break;
		}
		return convertView;
	}

}
