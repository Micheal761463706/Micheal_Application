package com.micheal.micheal_application.Entity;

/**
 *  选择城市的实体类
 */
public class CityEntity {
    private int cityId;
    private String compareName;
    private int mobileType = 1; // 类型   0:(标签)   1:(城市)

    public CityEntity(){

    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCompareName() {
        return compareName;
    }

    public void setCompareName(String compareName) {
        this.compareName = compareName;
    }

    public int getMobileType() {
        return mobileType;
    }

    public void setMobileType(int mobileType) {
        this.mobileType = mobileType;
    }

    public CityEntity(String compareName, int mobileType) {
        this.compareName = compareName;
        this.mobileType = mobileType;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "cityId=" + cityId +
                ", compareName='" + compareName + '\'' +
                ", mobileType=" + mobileType +
                '}';
    }
}
