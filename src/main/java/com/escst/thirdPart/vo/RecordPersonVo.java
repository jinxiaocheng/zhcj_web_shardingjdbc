package com.escst.thirdPart.vo;

import java.math.BigDecimal;

/**
 * @author dwj
 * @desc
 * @date 14:41 1/8/2018
 */
public class RecordPersonVo {

    /**
     *@Fileds  trainDepart 培训单位
     **/
    private String trainDepart;
    /**
     *@Fileds trainName 培训名称
     **/
    private String trainName;
    /**
     *@Fileds trainType 培训类型
     **/
    private String trainType;
    /**
     *@Fileds trainTime 培训时间
     **/
    private String trainTime;
    /**
     *@Fileds trainPeriod 培训学时
     **/
    private String trainPeriod;
    /**
     *@Fileds sGrade 成绩  (该值为-1时,证明该培训记录未出题 且 为合格状态ISPass=1)
     **/
    private BigDecimal sGrade;
    /**
     *@Fileds iSPass 合格与否  0:不合格 1:合格
     **/
    private String iSPass;

    public String getTrainDepart() {
        return trainDepart;
    }

    public void setTrainDepart(String trainDepart) {
        this.trainDepart = trainDepart;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public String getTrainPeriod() {
        return trainPeriod;
    }

    public void setTrainPeriod(String trainPeriod) {
        this.trainPeriod = trainPeriod;
    }

    public BigDecimal getsGrade() {
        return sGrade;
    }

    public void setsGrade(BigDecimal sGrade) {
        this.sGrade = sGrade;
    }

    public String getiSPass() {
        return iSPass;
    }

    public void setiSPass(String iSPass) {
        this.iSPass = iSPass;
    }
}
