package com.escst.thirdPart.vo;

/**
 * @author dwj
 * @desc
 * @date 9:59 2/8/2018
 */
public class CountVo {

    /**
     *@Fileds departId 演示箱Id
     **/
    private String departId;
    /**
     *@Fileds departName 演示箱名称
     **/
    private String departName;
    /**
     *@Fileds totalPersonNow 现有总人数
     **/
    private int totalPersonNow;
    /**
     *@Fileds totalPersonHistory 历史总人数
     **/
    private int totalPersonHistory;
    /**
     *@Fileds totalPersonTrain 累计培训人数
     **/
    private int totalPersonTrain;
    /**
     *@Fileds totalTrain 累计培训次数
     **/
    private int totalTrain;
    /**
     *@Fileds avgPersonTrain 人均培训次数
     **/
    private int avgPersonTrain;
    /**
     *@Fileds avgPersonTrainHour 人均培训学时
     **/
    private int avgPersonTrainHour;
    /**
     *@Fileds totalPersonExam 考试人次
     **/
    private int totalPersonExam;
    /**
     *@Fileds totalExamPass 考试合格人次
     **/
    private int totalExamPass;
    /**
     *@Fileds examPassPercent 考试合格率
     **/
    private String examPassPercent;
    /**
     *@Fileds trainPercent 培训率
     **/
    private String trainPercent;
    /**
     *@Fileds  totalTrainHour 累计学时
     **/
    private String totalTrainHour;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public int getTotalPersonNow() {
        return totalPersonNow;
    }

    public void setTotalPersonNow(int totalPersonNow) {
        this.totalPersonNow = totalPersonNow;
    }

    public int getTotalPersonHistory() {
        return totalPersonHistory;
    }

    public void setTotalPersonHistory(int totalPersonHistory) {
        this.totalPersonHistory = totalPersonHistory;
    }

    public int getTotalPersonTrain() {
        return totalPersonTrain;
    }

    public void setTotalPersonTrain(int totalPersonTrain) {
        this.totalPersonTrain = totalPersonTrain;
    }

    public int getTotalTrain() {
        return totalTrain;
    }

    public void setTotalTrain(int totalTrain) {
        this.totalTrain = totalTrain;
    }

    public int getAvgPersonTrain() {
        return avgPersonTrain;
    }

    public void setAvgPersonTrain(int avgPersonTrain) {
        this.avgPersonTrain = avgPersonTrain;
    }

    public int getAvgPersonTrainHour() {
        return avgPersonTrainHour;
    }

    public void setAvgPersonTrainHour(int avgPersonTrainHour) {
        this.avgPersonTrainHour = avgPersonTrainHour;
    }

    public int getTotalPersonExam() {
        return totalPersonExam;
    }

    public void setTotalPersonExam(int totalPersonExam) {
        this.totalPersonExam = totalPersonExam;
    }

    public int getTotalExamPass() {
        return totalExamPass;
    }

    public void setTotalExamPass(int totalExamPass) {
        this.totalExamPass = totalExamPass;
    }

    public String getExamPassPercent() {
        return examPassPercent;
    }

    public void setExamPassPercent(String examPassPercent) {
        this.examPassPercent = examPassPercent;
    }

    public String getTrainPercent() {
        return trainPercent;
    }

    public void setTrainPercent(String trainPercent) {
        this.trainPercent = trainPercent;
    }

    public String getTotalTrainHour() {
        return totalTrainHour;
    }

    public void setTotalTrainHour(String totalTrainHour) {
        this.totalTrainHour = totalTrainHour;
    }
}
