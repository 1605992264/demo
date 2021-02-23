package com.yexiao.demo.extra.weixin.subscribe;

/**
 * @author xuhf
 * @date 2020/8/24 15:27
 * 图文消息对象
 **/
public class ImgAndTextDO {

    /**
     * 图文消息缩略图的media_id，可以在素材管理-新增素材中获得
     * */
    private String thumb_media_id;
    /**
     * 图文消息的作者
     * */
    private String author;
    /**
     * 图文消息的标题
     * */
    private String title;
    /**
     * 在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。
     * */
    private String content_source_url;
    /**
     * 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文
     * */
    private String content;
    /**
     * 图文消息的描述，如本字段为空，则默认抓取正文前64个字
     * */
    private String digest;
    /**
     * 是否显示封面，1为显示，0为不显示
     * */
    private String show_cover_pic;
    /**
     * Uint32 是否打开评论，0不打开，1打开
     * */
    private String need_open_comment;
    /**
     * Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
     * */
    private String only_fans_can_comment;

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }

    public String getNeed_open_comment() {
        return need_open_comment;
    }

    public void setNeed_open_comment(String need_open_comment) {
        this.need_open_comment = need_open_comment;
    }

    public String getOnly_fans_can_comment() {
        return only_fans_can_comment;
    }

    public void setOnly_fans_can_comment(String only_fans_can_comment) {
        this.only_fans_can_comment = only_fans_can_comment;
    }
}
