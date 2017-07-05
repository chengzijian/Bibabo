package com.bibabo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 影片信息
 * Created by zijian.cheng on 2017/7/5.
 */

public class MovieCoverInfo implements Serializable {

    private static final long serialVersionUID = -2864619024433827268L;

    /**
     * id : 2xxul4n2j8y0rxi
     * pre_play_plats_id : null
     * publish_date : 2017-05-05
     * plid :
     * langue : 印度语
     * real_exclusive : 0
     * brief :
     * leading_actor_id : ["79725","1546046","1565049","1565050","1565444"]
     * current_num : null
     * payfree_num : null
     * recommend_episodes : null
     * clips_ids : ["a0398w1ct48","s0024jsao4q","w00243qjm35","e0024lst5ee","i0024li05uw","g0024kq82wu","e002445km6w","p0504ye3lhz","w0501pgkyro","d0501wf00gw","b00224qwn2a","n0399gt40qe","g0399mtihlz","c0399swvbdp","j0396vxuckt","c0397yetnna","g03523thzni","s0519s545lq","q0024h1o7g5","o00249x50u7","t0024g443de","w0500550bt9"]
     * positive_view_today_count : 8682167
     * web20_imgtag : {"tag_1":{"id":"-1","param":"","param2":"","text":""},"tag_2":{"id":"20005","param":"http://i.gtimg.cn/qqlive/images/mark/mark_5.png","param2":"","text":"VIP"},"tag_3":{"id":"-1","param":"","param2":"","text":""},"tag_4":{"id":"40003","param":"mark_sd","param2":"","text":"蓝光"}}
     * area : 14
     * type_name : 电影
     * tag : []
     * emotion_ids : null
     * horizontal_pic_url : http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/hori/2/2xxul4n2j8y0rxi.jpg
     * mtime_score : null
     * type : 1
     * update_desc :
     * title : 摔跤吧！爸爸
     * leading_actor : ["阿米尔·汗","沙克希·坦沃","法缇玛·萨那·纱卡","桑亚·玛荷塔","阿帕尔夏克提·库拉那"]
     * vip_ids : [{"F":7,"V":"a0024c3w6lf"},{"F":7,"V":"d0024papg96"}]
     * alias : ["我和我的冠军女儿","摔跤吧！老爸","摔跤家族"]
     * current_topic : null
     * second_title : 阿米尔汗神作口碑炸裂
     * positive_trailer : 1
     * video_ids : ["a0024c3w6lf","d0024papg96"]
     * series_id :
     * plot_id : null
     * series_num : 1
     * area_id : 153512+
     * downright : ["1","2","3","4","5","8","9"]
     * category_map : [10139,"正片",1037,"电影",1,"电影"]
     * is_trailer : 0
     * subtype : ["剧情","传记","院线"]
     * main_genre : 剧情
     * area_name : 印度
     * outsite_flag : 0
     * view_today_count : 9348051
     * series_name : 摔跤吧！爸爸
     * vertical_pic_url : http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/2/2xxul4n2j8y0rxi.jpg
     * list_show_style : null
     * data_checkup_grade : 4
     * costar_id : ["1567197","1567198","1567199","1567200"]
     * positive_view_all_count : 8943327
     * cover_checkup_grade : 4
     * pre_play_timelong_id : null
     * column_id : 0
     * rank_group_id : null
     * douban_score : 9.2
     * digital_mode : 194711
     * year : 2017
     * title_en : Dangal
     * description : 马哈维亚·辛格·珀尕（阿米尔·汗饰）曾是印度国家摔跤冠军，因生活所迫放弃摔跤。他希望让儿子可以帮他完成梦想——赢得世界级金牌。结果生了四个女儿本以为梦想就此破碎的辛格却意外发现女儿身上的惊人天赋，看到冠军希望的他决定不能让女儿的天赋浪费，像其他女孩一样只能洗衣做饭过一生，再三考虑之后，与妻子约定一年时间按照摔跤手的标准训练两个女儿：换掉裙子 、剪掉了长发，让她们练习摔跤，并赢得一个又一个冠军，最终赢来了成为榜样激励千千万万女性的机会……
     * cover_id : 2xxul4n2j8y0rxi
     * pay_status : 5
     * playright : ["1","2","3","4","5","8","9"]
     * recommend_episodes_icon : null
     * keyword : null
     * view_all_count : 69571604
     * copyright_id : 585
     * update_notify_desc : null
     * imdb_score : 8.7
     * episode_all : null
     * episode_updated : null
     * director : ["涅提·蒂瓦里"]
     * nomal_ids : [{"F":7,"V":"a0024c3w6lf","E":1},{"F":7,"V":"d0024papg96","E":2}]
     * score : {"c_mix_score":"9.6","hot":"9.99259","score":"9.6"}
     * sub_genre : ["传记","运动","院线"]
     * data_flag : 3997952
     * vipPage : true
     * isZhengpian : true
     * hasVplusRank : false
     * urlHasVid : true
     * payInfo : {"c_status":5,"c_vip_price":0,"c_single_price":500,"c_discount":0}
     * movie_comment_set : true
     */

    @SerializedName("id")
    private String id;
    @SerializedName("pre_play_plats_id")
    private String pre_play_plats_id;
    @SerializedName("publish_date")
    private String publish_date;
    @SerializedName("plid")
    private String plid;
    @SerializedName("langue")
    private String langue;
    @SerializedName("real_exclusive")
    private String real_exclusive;
    @SerializedName("brief")
    private String brief;
    @SerializedName("current_num")
    private String current_num;
    @SerializedName("payfree_num")
    private String payfree_num;
    @SerializedName("recommend_episodes")
    private String recommend_episodes;
    @SerializedName("positive_view_today_count")
    private long positive_view_today_count;
    @SerializedName("web20_imgtag")
    private String web20_imgtag;
    @SerializedName("area")
    private int area;
    @SerializedName("type_name")
    private String type_name;
    @SerializedName("emotion_ids")
    private String emotion_ids;
    @SerializedName("horizontal_pic_url")
    private String horizontal_pic_url;
    @SerializedName("mtime_score")
    private String mtime_score;
    @SerializedName("type")
    private int type;
    @SerializedName("update_desc")
    private String update_desc;
    @SerializedName("title")
    private String title;
    @SerializedName("current_topic")
    private String current_topic;
    @SerializedName("second_title")
    private String second_title;
    @SerializedName("positive_trailer")
    private int positive_trailer;
    @SerializedName("series_id")
    private String series_id;
    @SerializedName("plot_id")
    private String plot_id;
    @SerializedName("series_num")
    private String series_num;
    @SerializedName("area_id")
    private String area_id;
    @SerializedName("is_trailer")
    private int is_trailer;
    @SerializedName("main_genre")
    private String main_genre;
    @SerializedName("area_name")
    private String area_name;
    @SerializedName("outsite_flag")
    private int outsite_flag;
    @SerializedName("view_today_count")
    private long view_today_count;
    @SerializedName("series_name")
    private String series_name;
    @SerializedName("vertical_pic_url")
    private String vertical_pic_url;
    @SerializedName("list_show_style")
    private String list_show_style;
    @SerializedName("data_checkup_grade")
    private int data_checkup_grade;
    @SerializedName("positive_view_all_count")
    private long positive_view_all_count;
    @SerializedName("cover_checkup_grade")
    private int cover_checkup_grade;
    @SerializedName("pre_play_timelong_id")
    private String pre_play_timelong_id;
    @SerializedName("column_id")
    private int column_id;
    @SerializedName("rank_group_id")
    private String rank_group_id;
    @SerializedName("douban_score")
    private String douban_score;
    @SerializedName("digital_mode")
    private long digital_mode;
    @SerializedName("year")
    private String year;
    @SerializedName("title_en")
    private String title_en;
    @SerializedName("description")
    private String description;
    @SerializedName("cover_id")
    private String cover_id;
    @SerializedName("pay_status")
    private int pay_status;
    @SerializedName("recommend_episodes_icon")
    private String recommend_episodes_icon;
    @SerializedName("keyword")
    private String keyword;
    @SerializedName("view_all_count")
    private long view_all_count;
    @SerializedName("copyright_id")
    private int copyright_id;
    @SerializedName("update_notify_desc")
    private String update_notify_desc;
    @SerializedName("imdb_score")
    private String imdb_score;
    @SerializedName("episode_all")
    private String episode_all;
    @SerializedName("episode_updated")
    private String episode_updated;
    @SerializedName("score")
    private ScoreBean score;
    @SerializedName("data_flag")
    private long data_flag;
    @SerializedName("vipPage")
    private boolean vipPage;
    @SerializedName("isZhengpian")
    private boolean isZhengpian;
    @SerializedName("hasVplusRank")
    private boolean hasVplusRank;
    @SerializedName("urlHasVid")
    private boolean urlHasVid;
    @SerializedName("payInfo")
    private PayInfoBean payInfo;
    @SerializedName("movie_comment_set")
    private String movie_comment_set;
    @SerializedName("leading_actor_id")
    private List<String> leading_actor_id;
    @SerializedName("clips_ids")
    private List<String> clips_ids;
    @SerializedName("tag")
    private List<String> tag;
    @SerializedName("leading_actor")
    private List<String> leading_actor;
    @SerializedName("vip_ids")
    private List<VipIdsBean> vip_ids;
    @SerializedName("alias")
    private List<String> alias;
    @SerializedName("video_ids")
    private List<String> video_ids;
    @SerializedName("downright")
    private List<String> downright;
    @SerializedName("category_map")
    private List<String> category_map;
    @SerializedName("subtype")
    private List<String> subtype;
    @SerializedName("costar_id")
    private List<String> costar_id;
    @SerializedName("playright")
    private List<String> playright;
    @SerializedName("director")
    private List<String> director;
    @SerializedName("nomal_ids")
    private List<NomalIdsBean> nomal_ids;
    @SerializedName("sub_genre")
    private List<String> sub_genre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPre_play_plats_id() {
        return pre_play_plats_id;
    }

    public void setPre_play_plats_id(String pre_play_plats_id) {
        this.pre_play_plats_id = pre_play_plats_id;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getPlid() {
        return plid;
    }

    public void setPlid(String plid) {
        this.plid = plid;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getReal_exclusive() {
        return real_exclusive;
    }

    public void setReal_exclusive(String real_exclusive) {
        this.real_exclusive = real_exclusive;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCurrent_num() {
        return current_num;
    }

    public void setCurrent_num(String current_num) {
        this.current_num = current_num;
    }

    public String getPayfree_num() {
        return payfree_num;
    }

    public void setPayfree_num(String payfree_num) {
        this.payfree_num = payfree_num;
    }

    public String getRecommend_episodes() {
        return recommend_episodes;
    }

    public void setRecommend_episodes(String recommend_episodes) {
        this.recommend_episodes = recommend_episodes;
    }

    public long getPositive_view_today_count() {
        return positive_view_today_count;
    }

    public void setPositive_view_today_count(int positive_view_today_count) {
        this.positive_view_today_count = positive_view_today_count;
    }

//    public String getWeb20_imgtag() {
//        return web20_imgtag;
//    }
//
//    public void setWeb20_imgtag(String web20_imgtag) {
//        this.web20_imgtag = web20_imgtag;
//    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getEmotion_ids() {
        return emotion_ids;
    }

    public void setEmotion_ids(String emotion_ids) {
        this.emotion_ids = emotion_ids;
    }

    public String getHorizontal_pic_url() {
        return horizontal_pic_url;
    }

    public void setHorizontal_pic_url(String horizontal_pic_url) {
        this.horizontal_pic_url = horizontal_pic_url;
    }

    public String getMtime_score() {
        return mtime_score;
    }

    public void setMtime_score(String mtime_score) {
        this.mtime_score = mtime_score;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpdate_desc() {
        return update_desc;
    }

    public void setUpdate_desc(String update_desc) {
        this.update_desc = update_desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrent_topic() {
        return current_topic;
    }

    public void setCurrent_topic(String current_topic) {
        this.current_topic = current_topic;
    }

    public String getSecond_title() {
        return second_title;
    }

    public void setSecond_title(String second_title) {
        this.second_title = second_title;
    }

    public int getPositive_trailer() {
        return positive_trailer;
    }

    public void setPositive_trailer(int positive_trailer) {
        this.positive_trailer = positive_trailer;
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public String getPlot_id() {
        return plot_id;
    }

    public void setPlot_id(String plot_id) {
        this.plot_id = plot_id;
    }

    public String getSeries_num() {
        return series_num;
    }

    public void setSeries_num(String series_num) {
        this.series_num = series_num;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public int getIs_trailer() {
        return is_trailer;
    }

    public void setIs_trailer(int is_trailer) {
        this.is_trailer = is_trailer;
    }

    public String getMain_genre() {
        return main_genre;
    }

    public void setMain_genre(String main_genre) {
        this.main_genre = main_genre;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public int getOutsite_flag() {
        return outsite_flag;
    }

    public void setOutsite_flag(int outsite_flag) {
        this.outsite_flag = outsite_flag;
    }

    public long getView_today_count() {
        return view_today_count;
    }

    public void setView_today_count(int view_today_count) {
        this.view_today_count = view_today_count;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public String getVertical_pic_url() {
        return vertical_pic_url;
    }

    public void setVertical_pic_url(String vertical_pic_url) {
        this.vertical_pic_url = vertical_pic_url;
    }

    public String getList_show_style() {
        return list_show_style;
    }

    public void setList_show_style(String list_show_style) {
        this.list_show_style = list_show_style;
    }

    public int getData_checkup_grade() {
        return data_checkup_grade;
    }

    public void setData_checkup_grade(int data_checkup_grade) {
        this.data_checkup_grade = data_checkup_grade;
    }

    public long getPositive_view_all_count() {
        return positive_view_all_count;
    }

    public void setPositive_view_all_count(long positive_view_all_count) {
        this.positive_view_all_count = positive_view_all_count;
    }

    public int getCover_checkup_grade() {
        return cover_checkup_grade;
    }

    public void setCover_checkup_grade(int cover_checkup_grade) {
        this.cover_checkup_grade = cover_checkup_grade;
    }

    public String getPre_play_timelong_id() {
        return pre_play_timelong_id;
    }

    public void setPre_play_timelong_id(String pre_play_timelong_id) {
        this.pre_play_timelong_id = pre_play_timelong_id;
    }

    public int getColumn_id() {
        return column_id;
    }

    public void setColumn_id(int column_id) {
        this.column_id = column_id;
    }

    public String getRank_group_id() {
        return rank_group_id;
    }

    public void setRank_group_id(String rank_group_id) {
        this.rank_group_id = rank_group_id;
    }

    public String getDouban_score() {
        return douban_score;
    }

    public void setDouban_score(String douban_score) {
        this.douban_score = douban_score;
    }

    public long getDigital_mode() {
        return digital_mode;
    }

    public void setDigital_mode(int digital_mode) {
        this.digital_mode = digital_mode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover_id() {
        return cover_id;
    }

    public void setCover_id(String cover_id) {
        this.cover_id = cover_id;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public String getRecommend_episodes_icon() {
        return recommend_episodes_icon;
    }

    public void setRecommend_episodes_icon(String recommend_episodes_icon) {
        this.recommend_episodes_icon = recommend_episodes_icon;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public long getView_all_count() {
        return view_all_count;
    }

    public void setView_all_count(long view_all_count) {
        this.view_all_count = view_all_count;
    }

    public int getCopyright_id() {
        return copyright_id;
    }

    public void setCopyright_id(int copyright_id) {
        this.copyright_id = copyright_id;
    }

    public String getUpdate_notify_desc() {
        return update_notify_desc;
    }

    public void setUpdate_notify_desc(String update_notify_desc) {
        this.update_notify_desc = update_notify_desc;
    }

    public String getImdb_score() {
        return imdb_score;
    }

    public void setImdb_score(String imdb_score) {
        this.imdb_score = imdb_score;
    }

    public String getEpisode_all() {
        return episode_all;
    }

    public void setEpisode_all(String episode_all) {
        this.episode_all = episode_all;
    }

    public String getEpisode_updated() {
        return episode_updated;
    }

    public void setEpisode_updated(String episode_updated) {
        this.episode_updated = episode_updated;
    }

    public ScoreBean getScore() {
        return score;
    }

    public void setScore(ScoreBean score) {
        this.score = score;
    }

    public long getData_flag() {
        return data_flag;
    }

    public void setData_flag(long data_flag) {
        this.data_flag = data_flag;
    }

    public boolean isVipPage() {
        return vipPage;
    }

    public void setVipPage(boolean vipPage) {
        this.vipPage = vipPage;
    }

    public boolean isIsZhengpian() {
        return isZhengpian;
    }

    public void setIsZhengpian(boolean isZhengpian) {
        this.isZhengpian = isZhengpian;
    }

    public boolean isHasVplusRank() {
        return hasVplusRank;
    }

    public void setHasVplusRank(boolean hasVplusRank) {
        this.hasVplusRank = hasVplusRank;
    }

    public boolean isUrlHasVid() {
        return urlHasVid;
    }

    public void setUrlHasVid(boolean urlHasVid) {
        this.urlHasVid = urlHasVid;
    }

    public PayInfoBean getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfoBean payInfo) {
        this.payInfo = payInfo;
    }

    public String getMovie_comment_set() {
        return movie_comment_set;
    }

    public void setMovie_comment_set(String movie_comment_set) {
        this.movie_comment_set = movie_comment_set;
    }

    public List<String> getLeading_actor_id() {
        return leading_actor_id;
    }

    public void setLeading_actor_id(List<String> leading_actor_id) {
        this.leading_actor_id = leading_actor_id;
    }

    public List<String> getClips_ids() {
        return clips_ids;
    }

    public void setClips_ids(List<String> clips_ids) {
        this.clips_ids = clips_ids;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> getLeading_actor() {
        return leading_actor;
    }

    public void setLeading_actor(List<String> leading_actor) {
        this.leading_actor = leading_actor;
    }

    public List<VipIdsBean> getVip_ids() {
        return vip_ids;
    }

    public void setVip_ids(List<VipIdsBean> vip_ids) {
        this.vip_ids = vip_ids;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public List<String> getVideo_ids() {
        return video_ids;
    }

    public void setVideo_ids(List<String> video_ids) {
        this.video_ids = video_ids;
    }

    public List<String> getDownright() {
        return downright;
    }

    public void setDownright(List<String> downright) {
        this.downright = downright;
    }

    public List<String> getCategory_map() {
        return category_map;
    }

    public void setCategory_map(List<String> category_map) {
        this.category_map = category_map;
    }

    public List<String> getSubtype() {
        return subtype;
    }

    public void setSubtype(List<String> subtype) {
        this.subtype = subtype;
    }

    public List<String> getCostar_id() {
        return costar_id;
    }

    public void setCostar_id(List<String> costar_id) {
        this.costar_id = costar_id;
    }

    public List<String> getPlayright() {
        return playright;
    }

    public void setPlayright(List<String> playright) {
        this.playright = playright;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<NomalIdsBean> getNomal_ids() {
        return nomal_ids;
    }

    public void setNomal_ids(List<NomalIdsBean> nomal_ids) {
        this.nomal_ids = nomal_ids;
    }

    public List<String> getSub_genre() {
        return sub_genre;
    }

    public void setSub_genre(List<String> sub_genre) {
        this.sub_genre = sub_genre;
    }

    public static class ScoreBean {
        /**
         * c_mix_score : 9.6
         * hot : 9.99259
         * score : 9.6
         */

        @SerializedName("c_mix_score")
        private String c_mix_score;
        @SerializedName("hot")
        private String hot;
        @SerializedName("score")
        private String score;

        public String getC_mix_score() {
            return c_mix_score;
        }

        public void setC_mix_score(String c_mix_score) {
            this.c_mix_score = c_mix_score;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }

    public static class PayInfoBean {
        /**
         * c_status : 5
         * c_vip_price : 0
         * c_single_price : 500
         * c_discount : 0
         */

        @SerializedName("c_status")
        private int c_status;
        @SerializedName("c_vip_price")
        private int c_vip_price;
        @SerializedName("c_single_price")
        private int c_single_price;
        @SerializedName("c_discount")
        private int c_discount;

        public int getC_status() {
            return c_status;
        }

        public void setC_status(int c_status) {
            this.c_status = c_status;
        }

        public int getC_vip_price() {
            return c_vip_price;
        }

        public void setC_vip_price(int c_vip_price) {
            this.c_vip_price = c_vip_price;
        }

        public int getC_single_price() {
            return c_single_price;
        }

        public void setC_single_price(int c_single_price) {
            this.c_single_price = c_single_price;
        }

        public int getC_discount() {
            return c_discount;
        }

        public void setC_discount(int c_discount) {
            this.c_discount = c_discount;
        }
    }

    public static class VipIdsBean {
        /**
         * F : 7
         * V : a0024c3w6lf
         */

        @SerializedName("F")
        private int F;
        @SerializedName("V")
        private String V;

        public int getF() {
            return F;
        }

        public void setF(int F) {
            this.F = F;
        }

        public String getV() {
            return V;
        }

        public void setV(String V) {
            this.V = V;
        }
    }

    public static class NomalIdsBean {
        /**
         * F : 7
         * V : a0024c3w6lf
         * E : 1
         */

        @SerializedName("F")
        private int F;
        @SerializedName("V")
        private String V;
        @SerializedName("E")
        private int E;

        public int getF() {
            return F;
        }

        public void setF(int F) {
            this.F = F;
        }

        public String getV() {
            return V;
        }

        public void setV(String V) {
            this.V = V;
        }

        public int getE() {
            return E;
        }

        public void setE(int E) {
            this.E = E;
        }
    }
}
