package com.micheal.micheal_application.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.micheal.micheal_application.Fragment.Message.Entity.CityEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  解析JSON工具类
 */
public class JsonUtil {
    private static final String TAG = "print";
    private  static String[] letterLabels = {"A","B","C","D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    /**
     *  解析城市列表
     */
    public static List<CityEntity> getCityJson(String json){

        List<CityEntity> list = null;
        if(json != null) {
            list = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                int code = jsonObject.getInt("retcode");// 得到结果返回码
                if (code == 0) {
                    jsonObject = jsonObject.getJSONObject("cities");// 获取数组中的各省首字母
                    for (int i = 0; i < letterLabels.length; i++) {

                        CityEntity cityEntity = new CityEntity(letterLabels[i], 0); // 手动创建一个标签对象，加入集合中
                        list.add(cityEntity);

                        JSONArray jsonArray = jsonObject.optJSONArray(letterLabels[i]);//解析字母标签
                        if(jsonArray!=null) {
                            TypeToken<List<CityEntity>> typeToken = new TypeToken<List<CityEntity>>() {};
                            List<CityEntity> cityEntities = new Gson().fromJson(jsonArray.toString(), typeToken.getType());
                            list.addAll(cityEntities);// 把该标签下的城市添加总的城市列表中
                        }
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return list;
    }


}
