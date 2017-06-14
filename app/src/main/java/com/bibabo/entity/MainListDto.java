package com.bibabo.entity;

import java.io.Serializable;

/**
 */
public class MainListDto implements Serializable {
    private static final long serialVersionUID = -2864689024433827268L;
    private String name;
    private String pic;
    private String link;
    private String clicknumber;

    public MainListDto(String name, String pic, String link, String clicknumber) {
        this.name = name;
        this.pic = pic;
        this.link = link;
        this.clicknumber = clicknumber;
    }

    public String getName() {
        return name;
    }

    public String getClicknumber() {
        return clicknumber;
    }

    public String getPic() {
        return pic;
    }

    public String getLink() {
        return link;
    }
}
