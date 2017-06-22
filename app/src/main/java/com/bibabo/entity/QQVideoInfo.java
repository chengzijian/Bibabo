package com.bibabo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by zijian.cheng on 2017/6/21.
 */

public class QQVideoInfo implements Serializable {

    private static final long serialVersionUID = -2864689024433827268L;
    @SerializedName("dltype")
    private int dltype;
    @SerializedName("exem")
    private int exem;
    @SerializedName("fl")
    private FlBean fl;
    @SerializedName("hs")
    private int hs;
    @SerializedName("ip")
    private String ip;
    @SerializedName("ls")
    private int ls;
    @SerializedName("preview")
    private int preview;
    @SerializedName("s")
    private String s;
    @SerializedName("sfl")
    private SflBean sfl;
    @SerializedName("tm")
    private int tm;
    @SerializedName("vl")
    private VlBean vl;

    public int getDltype() {
        return dltype;
    }

    public void setDltype(int dltype) {
        this.dltype = dltype;
    }

    public int getExem() {
        return exem;
    }

    public void setExem(int exem) {
        this.exem = exem;
    }

    public FlBean getFl() {
        return fl;
    }

    public void setFl(FlBean fl) {
        this.fl = fl;
    }

    public int getHs() {
        return hs;
    }

    public void setHs(int hs) {
        this.hs = hs;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getLs() {
        return ls;
    }

    public void setLs(int ls) {
        this.ls = ls;
    }

    public int getPreview() {
        return preview;
    }

    public void setPreview(int preview) {
        this.preview = preview;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public SflBean getSfl() {
        return sfl;
    }

    public void setSfl(SflBean sfl) {
        this.sfl = sfl;
    }

    public int getTm() {
        return tm;
    }

    public void setTm(int tm) {
        this.tm = tm;
    }

    public VlBean getVl() {
        return vl;
    }

    public void setVl(VlBean vl) {
        this.vl = vl;
    }

    public static class FlBean {
        @SerializedName("cnt")
        private int cnt;
        @SerializedName("fi")
        private List<FiBean> fi;

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public List<FiBean> getFi() {
            return fi;
        }

        public void setFi(List<FiBean> fi) {
            this.fi = fi;
        }

        public static class FiBean {
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("lmt")
            private int lmt;
            @SerializedName("sb")
            private int sb;
            @SerializedName("cname")
            private String cname;
            @SerializedName("br")
            private int br;
            @SerializedName("drm")
            private int drm;
            @SerializedName("video")
            private int video;
            @SerializedName("fs")
            private int fs;
            @SerializedName("sl")
            private int sl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getLmt() {
                return lmt;
            }

            public void setLmt(int lmt) {
                this.lmt = lmt;
            }

            public int getSb() {
                return sb;
            }

            public void setSb(int sb) {
                this.sb = sb;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public int getBr() {
                return br;
            }

            public void setBr(int br) {
                this.br = br;
            }

            public int getDrm() {
                return drm;
            }

            public void setDrm(int drm) {
                this.drm = drm;
            }

            public int getVideo() {
                return video;
            }

            public void setVideo(int video) {
                this.video = video;
            }

            public int getFs() {
                return fs;
            }

            public void setFs(int fs) {
                this.fs = fs;
            }

            public int getSl() {
                return sl;
            }

            public void setSl(int sl) {
                this.sl = sl;
            }
        }
    }

    public static class SflBean {
        @SerializedName("cnt")
        private int cnt;

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }
    }

    public static class VlBean {
        @SerializedName("cnt")
        private int cnt;
        @SerializedName("vi")
        private List<ViBean> vi;

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public List<ViBean> getVi() {
            return vi;
        }

        public void setVi(List<ViBean> vi) {
            this.vi = vi;
        }

        public static class ViBean {

            @SerializedName("br")
            private int br;
            @SerializedName("ch")
            private int ch;
            @SerializedName("cl")
            private ClBean cl;
            @SerializedName("ct")
            private int ct;
            @SerializedName("drm")
            private int drm;
            @SerializedName("dsb")
            private int dsb;
            @SerializedName("fclip")
            private int fclip;
            @SerializedName("fmd5")
            private String fmd5;
            @SerializedName("fn")
            private String fn;
            @SerializedName("fs")
            private int fs;
            @SerializedName("fst")
            private int fst;
            @SerializedName("fvkey")
            private String fvkey;
            @SerializedName("head")
            private int head;
            @SerializedName("hevc")
            private int hevc;
            @SerializedName("iflag")
            private int iflag;
            @SerializedName("level")
            private int level;
            @SerializedName("lnk")
            private String lnk;
            @SerializedName("logo")
            private int logo;
            @SerializedName("mst")
            private int mst;
            @SerializedName("share")
            private int share;
            @SerializedName("sp")
            private int sp;
            @SerializedName("st")
            private int st;
            @SerializedName("tail")
            private int tail;
            @SerializedName("td")
            private String td;
            @SerializedName("ti")
            private String ti;
            @SerializedName("type")
            private int type;
            @SerializedName("ul")
            private UlBean ul;
            @SerializedName("vh")
            private int vh;
            @SerializedName("vid")
            private String vid;
            @SerializedName("videotype")
            private int videotype;
            @SerializedName("vr")
            private int vr;
            @SerializedName("vst")
            private int vst;
            @SerializedName("vw")
            private int vw;
            @SerializedName("wh")
            private double wh;
            @SerializedName("wl")
            private WlBean wl;
            @SerializedName("pl")
            private List<PlBean> pl;

            public int getBr() {
                return br;
            }

            public void setBr(int br) {
                this.br = br;
            }

            public int getCh() {
                return ch;
            }

            public void setCh(int ch) {
                this.ch = ch;
            }

            public ClBean getCl() {
                return cl;
            }

            public void setCl(ClBean cl) {
                this.cl = cl;
            }

            public int getCt() {
                return ct;
            }

            public void setCt(int ct) {
                this.ct = ct;
            }

            public int getDrm() {
                return drm;
            }

            public void setDrm(int drm) {
                this.drm = drm;
            }

            public int getDsb() {
                return dsb;
            }

            public void setDsb(int dsb) {
                this.dsb = dsb;
            }

            public int getFclip() {
                return fclip;
            }

            public void setFclip(int fclip) {
                this.fclip = fclip;
            }

            public String getFmd5() {
                return fmd5;
            }

            public void setFmd5(String fmd5) {
                this.fmd5 = fmd5;
            }

            public String getFn() {
                return fn;
            }

            public void setFn(String fn) {
                this.fn = fn;
            }

            public int getFs() {
                return fs;
            }

            public void setFs(int fs) {
                this.fs = fs;
            }

            public int getFst() {
                return fst;
            }

            public void setFst(int fst) {
                this.fst = fst;
            }

            public String getFvkey() {
                return fvkey;
            }

            public void setFvkey(String fvkey) {
                this.fvkey = fvkey;
            }

            public int getHead() {
                return head;
            }

            public void setHead(int head) {
                this.head = head;
            }

            public int getHevc() {
                return hevc;
            }

            public void setHevc(int hevc) {
                this.hevc = hevc;
            }

            public int getIflag() {
                return iflag;
            }

            public void setIflag(int iflag) {
                this.iflag = iflag;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getLnk() {
                return lnk;
            }

            public void setLnk(String lnk) {
                this.lnk = lnk;
            }

            public int getLogo() {
                return logo;
            }

            public void setLogo(int logo) {
                this.logo = logo;
            }

            public int getMst() {
                return mst;
            }

            public void setMst(int mst) {
                this.mst = mst;
            }

            public int getShare() {
                return share;
            }

            public void setShare(int share) {
                this.share = share;
            }

            public int getSp() {
                return sp;
            }

            public void setSp(int sp) {
                this.sp = sp;
            }

            public int getSt() {
                return st;
            }

            public void setSt(int st) {
                this.st = st;
            }

            public int getTail() {
                return tail;
            }

            public void setTail(int tail) {
                this.tail = tail;
            }

            public String getTd() {
                return td;
            }

            public void setTd(String td) {
                this.td = td;
            }

            public String getTi() {
                return ti;
            }

            public void setTi(String ti) {
                this.ti = ti;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public UlBean getUl() {
                return ul;
            }

            public void setUl(UlBean ul) {
                this.ul = ul;
            }

            public int getVh() {
                return vh;
            }

            public void setVh(int vh) {
                this.vh = vh;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public int getVideotype() {
                return videotype;
            }

            public void setVideotype(int videotype) {
                this.videotype = videotype;
            }

            public int getVr() {
                return vr;
            }

            public void setVr(int vr) {
                this.vr = vr;
            }

            public int getVst() {
                return vst;
            }

            public void setVst(int vst) {
                this.vst = vst;
            }

            public int getVw() {
                return vw;
            }

            public void setVw(int vw) {
                this.vw = vw;
            }

            public double getWh() {
                return wh;
            }

            public void setWh(double wh) {
                this.wh = wh;
            }

            public WlBean getWl() {
                return wl;
            }

            public void setWl(WlBean wl) {
                this.wl = wl;
            }

            public List<PlBean> getPl() {
                return pl;
            }

            public void setPl(List<PlBean> pl) {
                this.pl = pl;
            }

            public static class ClBean {
                @SerializedName("fc")
                private int fc;
                @SerializedName("ci")
                private List<CiBean> ci;

                public int getFc() {
                    return fc;
                }

                public void setFc(int fc) {
                    this.fc = fc;
                }

                public List<CiBean> getCi() {
                    return ci;
                }

                public void setCi(List<CiBean> ci) {
                    this.ci = ci;
                }

                public static class CiBean {
                    @SerializedName("idx")
                    private int idx;
                    @SerializedName("cs")
                    private int cs;
                    @SerializedName("cd")
                    private String cd;
                    @SerializedName("cmd5")
                    private String cmd5;
                    @SerializedName("keyid")
                    private String keyid;

                    public int getIdx() {
                        return idx;
                    }

                    public void setIdx(int idx) {
                        this.idx = idx;
                    }

                    public int getCs() {
                        return cs;
                    }

                    public void setCs(int cs) {
                        this.cs = cs;
                    }

                    public String getCd() {
                        return cd;
                    }

                    public void setCd(String cd) {
                        this.cd = cd;
                    }

                    public String getCmd5() {
                        return cmd5;
                    }

                    public void setCmd5(String cmd5) {
                        this.cmd5 = cmd5;
                    }

                    public String getKeyid() {
                        return keyid;
                    }

                    public void setKeyid(String keyid) {
                        this.keyid = keyid;
                    }
                }
            }

            public static class UlBean {
                private List<UiBean> ui;

                public List<UiBean> getUi() {
                    return ui;
                }

                public void setUi(List<UiBean> ui) {
                    this.ui = ui;
                }

                public static class UiBean {

                    @SerializedName("url")
                    private String url;
                    @SerializedName("vt")
                    private int vt;
                    @SerializedName("dtc")
                    private int dtc;
                    @SerializedName("dt")
                    private int dt;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getVt() {
                        return vt;
                    }

                    public void setVt(int vt) {
                        this.vt = vt;
                    }

                    public int getDtc() {
                        return dtc;
                    }

                    public void setDtc(int dtc) {
                        this.dtc = dtc;
                    }

                    public int getDt() {
                        return dt;
                    }

                    public void setDt(int dt) {
                        this.dt = dt;
                    }
                }
            }

            public static class WlBean {
                @SerializedName("wi")
                private List<?> wi;

                public List<?> getWi() {
                    return wi;
                }

                public void setWi(List<?> wi) {
                    this.wi = wi;
                }
            }

            public static class PlBean {
                @SerializedName("cnt")
                private int cnt;
                @SerializedName("pd")
                private List<PdBean> pd;

                public int getCnt() {
                    return cnt;
                }

                public void setCnt(int cnt) {
                    this.cnt = cnt;
                }

                public List<PdBean> getPd() {
                    return pd;
                }

                public void setPd(List<PdBean> pd) {
                    this.pd = pd;
                }

                public static class PdBean {

                    @SerializedName("cd")
                    private int cd;
                    @SerializedName("h")
                    private int h;
                    @SerializedName("w")
                    private int w;
                    @SerializedName("r")
                    private int r;
                    @SerializedName("c")
                    private int c;
                    @SerializedName("fmt")
                    private int fmt;
                    @SerializedName("fn")
                    private String fn;
                    @SerializedName("url")
                    private String url;

                    public int getCd() {
                        return cd;
                    }

                    public void setCd(int cd) {
                        this.cd = cd;
                    }

                    public int getH() {
                        return h;
                    }

                    public void setH(int h) {
                        this.h = h;
                    }

                    public int getW() {
                        return w;
                    }

                    public void setW(int w) {
                        this.w = w;
                    }

                    public int getR() {
                        return r;
                    }

                    public void setR(int r) {
                        this.r = r;
                    }

                    public int getC() {
                        return c;
                    }

                    public void setC(int c) {
                        this.c = c;
                    }

                    public int getFmt() {
                        return fmt;
                    }

                    public void setFmt(int fmt) {
                        this.fmt = fmt;
                    }

                    public String getFn() {
                        return fn;
                    }

                    public void setFn(String fn) {
                        this.fn = fn;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }
        }
    }
}
