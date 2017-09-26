package com.hdmes.crane001;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
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

public class Crane2Activity extends AppCompatActivity {
    private ListView lv;

    private List<Crane> data = new ArrayList<Crane>();

    private CraneAdapter craneAdapter;

    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crane2);
        //
        //lv = (ListView) findViewById(R.id.lv2);

        //pd = new ProgressDialog(this);

        //craneAdapter = new CraneAdapter(this, data);

        //lv.setAdapter(craneAdapter);

        //new MyCraneTask().execute();

    }

    private class MyCraneTask extends AsyncTask<String, Void, Map<String, Object>> {
        @Override
        protected void onPreExecute() {

            pd.setMessage("Loading...");

        }

        @Override
        protected Map<String, Object> doInBackground(String... params) {

            String path = "http://192.168.0.188:8088/SystemManage/Crane/GetGridJson1";
            HttpClient client = new DefaultHttpClient();
            HttpPost hpost = new HttpPost(path);
/*            BasicNameValuePair keyvalue = new BasicNameValuePair("keyvalue", "12");
            BasicNameValuePair start_tiem = new BasicNameValuePair("start_time", "2017-01-01");
            BasicNameValuePair stop_time = new BasicNameValuePair("stop_time", "2017-12-31");
            BasicNameValuePair choice_crane = new BasicNameValuePair("choice_crane", "005A");
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            //把BasicNameValuePair放入集合中
            parameters.add(keyvalue);
            parameters.add(start_tiem);
            parameters.add(stop_time);
            parameters.add(choice_crane);*/

            try {
                //UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(parameters, "utf-8");
                //hpost.setEntity(entity1);
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
            data.addAll((List<Crane>) result.get("craneList"));
            craneAdapter.notifyDataSetChanged();

            for (int i = 0; i < data.size(); i++) {

                Crane f = data.get(i);

                new MyImgTask().execute(f);

            }
        }

        protected Map<String, Object> parseJson(String json) throws Exception {

            Map<String, Object> result = new HashMap<String, Object>();
            List<Crane> lists = new ArrayList<Crane>();
            JSONArray  array = new JSONArray(json);
            //JSONObject bigObj = new JSONObject(json);
            //result.put("isNextPage", bigObj.getBoolean("isNextPage"));//
            //JSONArray array = bigObj.getJSONArray(json);
            Crane f = null;

            for (int i = 0; i < array.length(); i++) {
                f = new Crane();
                JSONObject smallObj = array.getJSONObject(i);

                f.setFu_Id(smallObj.getString("fu_id"));
                f.setEqui_Num(smallObj.getString("EquipmentNum"));
                f.setCraneIP(smallObj.getString("ClientIP"));
                f.setCraneAres(smallObj.getString("CraneArea"));
                lists.add(f);
            }
            result.put("craneList", lists);
            return result;

        }

        /**
         *
         * 未用的
         */
        private class MyImgTask extends AsyncTask<Crane, Void, Crane> {

            @Override
            protected Crane doInBackground(Crane... params) {

                Crane f = params[0];
                String imgPath = "http://192.168.0.188:8088/SystemManage/Crane20D/GetGridJson1" + f.getCranePath();

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
            protected void onPostExecute(Crane result) {
                craneAdapter.notifyDataSetChanged();
            }
        }
    }

}
