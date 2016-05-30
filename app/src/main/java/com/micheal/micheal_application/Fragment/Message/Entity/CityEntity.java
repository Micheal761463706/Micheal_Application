package com.micheal.micheal_application.Fragment.Message.Entity;

/**
 *  选择城市的实体类
 */
public class CityEntity {
    private int cityid;
    private String cityname;
    private int type = 1; // 类型   0:(标签)   1:(城市)

    public CityEntity(){

    }

    public int getCityId() {
        return cityid;
    }

    public void setCityId(int cityId) {
        this.cityid = cityId;
    }

    public String getCityNamee() {
        return cityname;
    }

    public void setCityName(String compareName) {
        this.cityname = compareName;
    }

    public int getMobileType() {
        return type;
    }

    public void setMobileType(int mobileType) {
        this.type = mobileType;
    }

    public CityEntity(String cityName, int mobileType) {
        this.cityname = cityName;
        this.type = mobileType;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "cityId=" + cityid +
                ", cityName='" + cityname + '\'' +
                ", mobileType=" + type +
                '}';
    }

    /**
     *
     * @param label
     * @return
     */
    public boolean eqLabel (String label){
        if(type == 0 && cityname.equals(label)){
            return true;
        }
        return false;
    }


}
