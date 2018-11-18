package com.jack.spiderone.entity;

import lombok.Data;

/**
 * create by jack 2018/11/18
 *
 * @author jack
 * @date: 2018/11/18 11:26
 * @Description:
 */
@Data
public class CommentModel {

    /**
     * 评论的id
     */
    private String CommentId;
    //评论的菜品
    private String ItemId;
    //评论的内容
    private String Content;
    //评论的时间
    private String CreateTime;
    //评论作者的名称
    private String OpenUserName;
}
