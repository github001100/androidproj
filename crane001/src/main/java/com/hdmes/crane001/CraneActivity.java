package com.hdmes.crane001;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class CraneActivity extends Activity {
    private ListView lv;

    private List<Food> data = new ArrayList<Food>();

    private MyAdapter mAdapter;

    private ProgressDialog pd;

    private boolean isNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crane);

        //
        lv = (ListView) findViewById(R.id.lv);

        pd = new ProgressDialog(this);

        mAdapter = new MyAdapter(this, data);

        lv.setAdapter(mAdapter);

        new MyFoodTask().execute();

    }

    private class MyFoodTask extends AsyncTask<String, Void, Map<String, Object>> {
        @Override
        protected void onPreExecute() {

            pd.setMessage("Loading...");

        }

        @Override
        protected Map<String, Object> doInBackground(String... params) {

            //获取数据信息需要 参数传递 否则为NULL
            String path = "http://192.168.0.188:8088/SystemManage/Crane20D/GetGridJson1";
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(path);

            HttpPost hpost = new HttpPost(path);
            BasicNameValuePair keyvalue = new BasicNameValuePair("keyvalue", "12");
            BasicNameValuePair start_tiem = new BasicNameValuePair("start_time", "2017-01-01");
            BasicNameValuePair stop_time = new BasicNameValuePair("stop_time", "2017-12-31");
            BasicNameValuePair choice_crane = new BasicNameValuePair("choice_crane", "005A");
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            //把BasicNameValuePair放入集合中
            parameters.add(keyvalue);
            parameters.add(start_tiem);
            parameters.add(stop_time);
            parameters.add(choice_crane);

            try {
                UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(parameters, "utf-8");
                hpost.setEntity(entity1);
                HttpResponse resp = client.execute(hpost);
                //HttpResponse resp = client.execute(get);
                //int rp = resp.getStatusLine().getStatusCode();

                if (resp.getStatusLine().getStatusCode() == 200) {

                    HttpEntity entity = resp.getEntity();
                    String result = EntityUtils.toString(entity);
                    Log.i("111", "result=" + result);
                    Map<String, Object> map = parseJson(result);
                    return map;

                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Map<String, Object> result) {

            pd.dismiss();
            //isNextPage = (Boolean) result.get("isNextPage");
            data.addAll((List<Food>) result.get("foodList"));
            mAdapter.notifyDataSetChanged();

            for (int i = 0; i < data.size(); i++) {

                Food f = data.get(i);

                new MyImgTask().execute(f);

            }
        }

        protected Map<String, Object> parseJson(String json) throws Exception {

            Map<String, Object> result = new HashMap<String, Object>();
            List<Food> lists = new ArrayList<Food>();
            JSONArray  array = new JSONArray(json);
            //JSONObject bigObj = new JSONObject(json);
            //result.put("isNextPage", bigObj.getBoolean("isNextPage"));//
            //JSONArray array = bigObj.getJSONArray(json);
            Food f = null;

            for (int i = 0; i < array.length(); i++) {
                f = new Food();
                JSONObject smallObj = array.getJSONObject(i);

                f.setId(smallObj.getInt("fu_id"));
                f.setName(smallObj.getString("fu_id"));
                f.setDesc(smallObj.getString("ClientIP"));
                f.setImgPath(smallObj.getString("fu_id"));
                lists.add(f);
            }
            result.put("foodList", lists);
            return result;

        }

        private class MyImgTask extends AsyncTask<Food, Void, Food> {

            @Override
            protected Food doInBackground(Food... params) {

                Food f = params[0];
                String imgPath = "http://192.168.0.188:8088/SystemManage/Crane20D/GetGridJson1" + f.getImgPath();

                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(imgPath);
                try {
                    HttpResponse resp = client.execute(get);
                    if (resp.getStatusLine().getStatusCode() == 200) {

                        HttpEntity entity = resp.getEntity();
                        byte img[] = EntityUtils.toByteArray(entity);
                        f.setImgData(img);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return f;
            }

            @Override
            protected void onPostExecute(Food result) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}

//  @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setTitle("起重机监测统计");
//        setContentView(R.layout.activity_crane);//主窗体
//
//
//    }
