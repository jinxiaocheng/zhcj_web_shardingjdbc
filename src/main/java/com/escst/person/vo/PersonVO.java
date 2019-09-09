package com.escst.person.vo;

import java.io.Serializable;

/**
 * @desc 人员管理展示对象
 * @Author caozx
 * @createDate 2017/8/17 11:22
 */
public class PersonVO implements Serializable {
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 2337814605713460392L;

    private String id;
    /**
     * 人员名称
     **/
    private String name;
    /**
     * 性别1:男;0:女
     **/
    private int sex;
    /**
     * 职称1:项目经理;2:施工员;3:预算员;4:材料员;5:质检员;6:安全员;7:办公室人员
     **/
    private int title;
    /**
     * 工种1:钳工;2:泥工;3:木工;4:水电工;5:钢筋工;6:混凝土工
     **/
    private int workType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getWorkType() {
        return workType;
    }

    public void setWorkType(int workType) {
        this.workType = workType;
    }
}
