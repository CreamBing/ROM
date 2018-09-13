package com.netposa.rom.model.zimg;

import java.util.Date;

public class FaceTrainEntity {
    private Integer id;

    private String md5;

    private Integer x;

    private Integer y;

    private Integer w;

    private Integer h;

    private Date createTime;

    private Date updateTime;

    private Boolean hasTrained;

    private String name;

    private Integer label;

    private Date trainTime;

    private String forecastName;

    private Date forecastTime;

    private Boolean forecastResult;

    private Boolean hasDeleted;

    private Boolean hasRecognized;

    private Date recognizeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getHasTrained() {
        return hasTrained;
    }

    public void setHasTrained(Boolean hasTrained) {
        this.hasTrained = hasTrained;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Date getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(Date trainTime) {
        this.trainTime = trainTime;
    }

    public String getForecastName() {
        return forecastName;
    }

    public void setForecastName(String forecastName) {
        this.forecastName = forecastName == null ? null : forecastName.trim();
    }

    public Date getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(Date forecastTime) {
        this.forecastTime = forecastTime;
    }

    public Boolean getForecastResult() {
        return forecastResult;
    }

    public void setForecastResult(Boolean forecastResult) {
        this.forecastResult = forecastResult;
    }

    public Boolean getHasDeleted() {
        return hasDeleted;
    }

    public void setHasDeleted(Boolean hasDeleted) {
        this.hasDeleted = hasDeleted;
    }

    public Boolean getHasRecognized() {
        return hasRecognized;
    }

    public void setHasRecognized(Boolean hasRecognized) {
        this.hasRecognized = hasRecognized;
    }

    public Date getRecognizeTime() {
        return recognizeTime;
    }

    public void setRecognizeTime(Date recognizeTime) {
        this.recognizeTime = recognizeTime;
    }
}