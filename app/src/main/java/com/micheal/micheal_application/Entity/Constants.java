package com.micheal.micheal_application.Entity;

/**
 *  常用接口
 */
public interface Constants {

    /**
     *  请求码常量
     */
    interface Code{
        int SELECT_CITY_REQUEST = 0x11; // 启动城市页面的请求码
        int SELECT_CITY_RESUEST = 0x12; // 返回城市页面的请求码
    }

    /**
     *  接口链接常量
     */
    interface URL{

        /**
         *  选择城市
         */
        String CITY_SELECT = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&act=kftcitylistnew&channel=71&devid=866500021200250&appname=QQHouse&mod=appkft";

        /**
         *  资讯  ListView内容
         * 1)进入时：reqnum=10,pageflag=0,buttonmore=0;
         * 2)点击查看更多时：reqnum=20,pageflag=0,buttonmore=1;
         * 3)刷新时：reqnum=20,pageflag=1,buttonmore=1;
         */
        String Message_NEWS = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=%d&pageflag=%d&act=newslist&channel=71&buttonmore=%d&cityid=%d";

        /**
         *  资讯  图片详情
         */
        String Message_DETAIL = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=newsdetail&channel=71&newsid=%s";
        /**
         *  资讯  内容详情
         */
        String NEWS_DETAIL = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=newsdetail&channel=71&newsid=%s";

    }

    /**
     *  常量名
     */
    interface KEY_NAME{
        String CITY_ID = "cityId";               // 城市ID
        String CITY_NAME = "cityName";          // 城市名称
        String MESSAGE_DATA = "messageData";   // 头部数据

    }
}
