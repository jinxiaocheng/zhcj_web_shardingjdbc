package com.escst.commons.tree;

import com.escst.commons.entity.BaseEntity;
import com.escst.video.vo.NvrVO;

import java.util.List;

public class TreeEntity extends BaseEntity implements java.io.Serializable, Comparable<TreeEntity> {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 2476518309140979290L;
    private String id;
    private String pId;                // 父节点id
    private String name;               // 树节点名称
    private Boolean checked = false;   // 是否勾选状态
    private Boolean open = false;      //是否展开
    private String icon;               //图标，可以自定义图标展示
    private Boolean nocheck = false;
    private int isLeaf; //是否是叶子节点 0否1是

    private Integer level;             //层级.1:区;2:工地;3:摄像头

    private NvrVO nvrVO;               //nvr信息

    private Integer channelNo;         //通道号

    /**
     * 标识.1:可云台控制;0:不能云台控制
     **/
    private int flag;

    private String xml;

    //操作权限
    private String operationAuthority;

    /**
     * 是否是父节点,open为子节点,closed为父节点
     */
    private String state = "open";

    /**
     * 工地ID
     */
    private String constructionId;

    /**
     * 子节点数量
     */
    private String count;

    /**
     * 排序
     */
    private int sortNum;

    /**
     * 类型
     */
    private Integer type;


    /**
     * 加密后的NVR信息字符串
     **/
    private String encryptNvr;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public Boolean getNocheck() {
        return nocheck;
    }

    public void setNocheck(Boolean nocheck) {
        this.nocheck = nocheck;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public NvrVO getNvrVO() {
        return nvrVO;
    }

    public void setNvrVO(NvrVO nvrVO) {
        this.nvrVO = nvrVO;
    }

    public String getOperationAuthority() {
        return operationAuthority;
    }

    public void setOperationAuthority(String operationAuthority) {
        this.operationAuthority = operationAuthority;
    }

    public Integer getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(Integer channelNo) {
        this.channelNo = channelNo;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getEncryptNvr() {
        return encryptNvr;
    }

    public void setEncryptNvr(String encryptNvr) {
        this.encryptNvr = encryptNvr;
    }

    @Override
    public int compareTo(TreeEntity o) {
        // 先按照flag平台排序
        int i = this.getFlag() - o.getFlag();
        if (i == 0) {
            // 如果flag相等再用sortNum进行排序
            return this.getSortNum() - o.getSortNum();
        }
        return i;
    }


}
