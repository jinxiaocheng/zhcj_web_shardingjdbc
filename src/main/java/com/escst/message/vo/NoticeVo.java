package com.escst.message.vo;

import com.escst.commons.vo.MessageVo;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 15:13 7/8/2018
 */
public class NoticeVo {

    /**
     *@Fileds vos 通知列表
     **/
    private List<MessageVo> vos;

    /**
     *@Fileds  isMenu 是否有菜单
     **/
    private Boolean isMenu;

    public List<MessageVo> getVos() {
        return vos;
    }

    public void setVos(List<MessageVo> vos) {
        this.vos = vos;
    }

    public Boolean getMenu() {
        return isMenu;
    }

    public void setMenu(Boolean menu) {
        isMenu = menu;
    }
}
