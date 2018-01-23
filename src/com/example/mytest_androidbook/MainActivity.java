package com.example.mytest_androidbook;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.Inflater;

import org.apache.http.Header;

import com.example.mytest_androidbook.adapter.NewsAdapter;
import com.example.mytest_androidbook.bean.NewsInfo;
import com.example.mytest_androidbook.utils.NewsInfoService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.image.SmartImageView.SmartImageView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private ListView lv_news;
	private LinearLayout loading;
	private List<NewsInfo> newsInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_news = (ListView) findViewById(R.id.list_news);
        loading = (LinearLayout) findViewById(R.id.loading);
        fillData2();
    }
    
    //使用AsyncHttpClient访问网络
	private void fillData2() {
		//创建AsyncHttpClient实例
		String url = getString(R.string.serverurl);
		Toast.makeText(MainActivity.this, "url:" + url, 0).show();
		AsyncHttpClient asynHttpClient = new AsyncHttpClient();
		asynHttpClient.get(url, 
				new AsyncHttpResponseHandler() {
					
					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] content) {
						Toast.makeText(MainActivity.this, "访问成功 " + new String(content), 0).show();
						//将Byte数组转换成输入流
						ByteArrayInputStream inputstream = new ByteArrayInputStream(content);
						//调用NewsInfoService工具类解析xml文件
						newsInfos = NewsInfoService.getNewsInfos(inputstream);
						if(newsInfos == null){
							Toast.makeText(MainActivity.this, "解析失败", 0).show();
						}else{
							//更新界面
							loading.setVisibility(View.INVISIBLE);
							lv_news.setAdapter(new NewsAdapter(MainActivity.this, newsInfos));
						}
					}
					
					//请求失败
					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] content, Throwable arg3) {
						Toast.makeText(MainActivity.this, "访问失败", 0).show();
					}
				});
	}
}
