package com.example.wuye.bean;

import java.util.List;

/**
 * Created by WUYE on 2018/10/11.
 */

public class NewsMenu {
    public int retcode;
    public List<NewsMenuData> data;
    public List<Integer>  extend;



    public class NewsMenuData {
     public int id;
     public String title;
     public int type;
     public List<NewsTabData> children;

        @Override
        public String toString() {
            return "NewsMenuData [title=" + title + ", children=" + children
                    + "]";
        }
    }

    public class NewsTabData {
        public int id;
        public String title;
        public int type;
        public String url;
        @Override
        public String toString() {
            return "NewsTabData [title=" + title + "]";
        }

    }

    @Override
    public String toString() {
        return "NewsMenu [data=" + data + "]";
    }
}
