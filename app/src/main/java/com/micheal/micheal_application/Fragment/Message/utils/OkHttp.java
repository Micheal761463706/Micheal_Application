package com.micheal.micheal_application.Fragment.Message.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class OkHttp {

    private OkHttpClient client = new OkHttpClient();
    private String url;

    public String Get(final String urlString){
        /**
         *  1.requestUrl是一个字符串变量代表这个URL是为了JSON请求
         *  2.再下一步我们需要实例化一个 Call 对象
         *  3.实例化Call对象后，我们现在可以 Execute（执行）她。
         Executing一个Call后将会返回一个 Response，并且会抛出一个 IOException的异常,这就是为什么们会用一个try，catch块包裹她。
         *  4.执行完我们的Call后，我们需要通过使用 response.isSuccessful()来检查Call对象是否执行成功，
         通过response.isSuccessful()的返回值为true或者是false来判断。
         这我们仅仅是一个测试，如果Call成功的话，我们将会通过Log来打印我们的response。
         *  5.为了修补这个问题，我们只需要让我们的Call执行在非主线程内，所以利用一个 asynchronous callback（异步的callBack）。
         让我们call异步的方法是通过调用我们Call对象的 enqueue()方法。
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request mRequest = new Request.Builder()
                        .url(urlString)
                        .build();
                Call mCall = client.newCall(mRequest);
                try {
                    Response response = client.newCall(mRequest).execute();
                    if(response.isSuccessful()){
                        //The call was successful.print it to the log
                        Log.v("OKHttp", response.body().string());
                        url = response.body().string();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(!response.isSuccessful()){
                            //The call was successful. print it to the log
                            throw new IOException("Unexpected code " + response);
                        }
                        Headers responseHeaders = response.headers();
                        for (int i = 0; i < responseHeaders.size(); i++) {
                            System.out.println("3:"+responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }
                        System.out.println("2:"+ response.body().string());
                    }
                });
            }

        }).start();
        return url;
    }

}
