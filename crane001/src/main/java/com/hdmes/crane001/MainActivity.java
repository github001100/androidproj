package com.hdmes.crane001;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    protected Toast toast = null;//定义一个吐司
    protected Context context;
    private ListView lv;
    private List<Food> data = new ArrayList<Food>();
    private MyAdapter mAdapter;
    private ProgressDialog pd;
    private String Stated;
    private String strTmp;
    private String strTmp2;

    public boolean netStatus = false;
    public boolean wifi;
    public boolean internet;
    //消息处理机制 Handler +Message +Looper+UI线程(主线程)
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT).show();
                    if (toast != null) {
                        toast.cancel();
                        toast = Toast.makeText(getApplicationContext(), "用户名或密码错误!", Toast.LENGTH_SHORT);
                    } else {
                        toast = Toast.makeText(getApplicationContext(), "用户名或密码错误!", Toast.LENGTH_SHORT);
                    }
                    toast.show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //网络状态检查
        context = getApplicationContext();
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        //if (internet || wifi) {
        //执行相关操作
        //netStatus = true;
        //Toast.makeText(context, "网络已连接！", Toast.LENGTH_SHORT).show();
        //获取按钮资源
        Button btn1 = (Button) findViewById(R.id.button_login);
        btn1.setOnClickListener(new Button.OnClickListener() {//设置监听
            public void onClick(View v) {
                EditText Ev1 = (EditText) findViewById(R.id.editText_username);
                EditText Ev2 = (EditText) findViewById(R.id.editText2_userpass);
                strTmp = Ev1.getText().toString();
                strTmp2 = Ev2.getText().toString();
                if (TextUtils.isEmpty(strTmp) & TextUtils.isEmpty(strTmp2)) {
                    //toast.setText("请输入用户名！");
                    if (toast != null) {
                        toast.cancel();
                        toast = Toast.makeText(getApplicationContext(), "请输入用户名和密码", Toast.LENGTH_SHORT);
                    } else {
                        toast = Toast.makeText(getApplicationContext(), "请输入用户名和密码", Toast.LENGTH_SHORT);
                    }
                    toast.show();
                } else if (TextUtils.isEmpty(strTmp)) {
                    if(toast!=null){
                        toast.cancel();
                        toast = Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_SHORT);
                    }else {
                        toast = Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_SHORT);
                    }
                    toast.show();
                } else if (TextUtils.isEmpty(strTmp2)) {
                    if(toast!=null){
                        toast.cancel();
                        toast = Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT);
                    }else {
                        toast = Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT);
                    }
                    toast.show();
                } else {
                    if (internet || wifi) {
                        netStatus=true;
                        lv = (ListView) findViewById(R.id.lv);
                        pd = new ProgressDialog(MainActivity.this);
                        mAdapter = new MyAdapter(MainActivity.this, data);
                        //lv.setAdapter(mAdapter);
                        new MainActivity.MyFoodTask().execute();
                    } else {
                        if (toast != null) {
                            toast.cancel();
                            toast = Toast.makeText(context, "亲，网络异常，请检查网络连接！", Toast.LENGTH_SHORT);
                        } else {
                            toast = Toast.makeText(context, "亲，网络异常，请检查网络连接！", Toast.LENGTH_SHORT);
                        }
                        toast.show();
                    }
                }
            }
        });
        //} else {
        //Toast.makeText(context, "亲，网络异常，请检查网络连接！", Toast.LENGTH_LONG).show();
        //}
    }

    private class MyFoodTask extends AsyncTask<String, Void, Map<String, Object>> {

        @Override
        protected void onPreExecute() {

            pd.setMessage("Loading...");

        }

        /*
        *
        * 通过 web Http 获取服务器数据
        * */
        @Override
        protected Map<String, Object> doInBackground(String... params) {

            String path = "http://192.168.0.188:8098/UserAccount/UserLogin_Me";
            /*         新 URL 请求方法
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://www.baidu.com");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                InputStream in = connection.getInputStream();
                // 下面对获取到的输入流进行读取
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                Message message = new Message();
                //message.what = SHOW_RESPONSE;
                // 将服务器返回的结果存放到Message中
                message.obj = response.toString();
                //handler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }*/
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(path);
            HttpPost hpost = new HttpPost(path);
            BasicNameValuePair UserName = new BasicNameValuePair("UserName", strTmp);
            BasicNameValuePair UserPassword = new BasicNameValuePair("UserPassword", strTmp2);
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            //把BasicNameValuePair放入集合中
            parameters.add(UserName);
            parameters.add(UserPassword);
            try {
                UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(parameters, "utf-8");
                hpost.setEntity(entity1);
                HttpResponse resp = client.execute(hpost);//请求耗时操作 网络
                //HttpResponse resp = client.execute(get);
                int rp = resp.getStatusLine().getStatusCode();
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
        /*
        * onPostExecute
        * */
        @Override
        protected void onPostExecute(Map<String, Object> result) {
            pd.dismiss();
            //isNextPage = (Boolean) result.get("isNextPage");
            data.addAll((List<Food>) result.get("foodList"));
            mAdapter.notifyDataSetChanged();
            for (int i = 0; i < data.size(); i++) {
                Food f = data.get(i);
                new MainActivity.MyFoodTask.MyImgTask().execute(f);
            }
        }
        /*
        * parseJson
        * 将服务器返回的数据进行Json解析
        * */
        protected Map<String, Object> parseJson(String json) throws Exception {

            Map<String, Object> result = new HashMap<String, Object>();
            List<Food> lists = new ArrayList<Food>();
            Food f = null;
            f = new Food();
            JSONObject smallObj = new JSONObject(json);
            //实体entity赋值
            //f.setId(smallObj.getInt("Stated"));
            f.setName(smallObj.getString("UserName"));
            f.setDesc(smallObj.getString("Stated"));
            f.setImgPath(smallObj.getString("Stated"));
            lists.add(f);

            result.put("foodList", lists);
            data.clear();//初始化List 否则List 逐渐增加
            data.addAll((List<Food>) result.get("foodList"));
            Food st = data.get(0);
            Stated = st.getDesc();//Stated返回状态 success Or error

            if (Stated.toString().trim().equals("error".toString().trim())) {

                //子线程：通知UI线程更新UI
                try {
                    Thread.sleep(0);
                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {//登录成功，返回首页相关操作
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);

                finish();//结束登录页面
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FormActivity.class);
                intent.putExtra("username", strTmp);//给intent 添加额外数据--当前登录用户
                //startActivityForResult(intent,1);//判断登录是否成功
                startActivity(intent);
            }
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