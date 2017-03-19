package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by ${Ua} on 2017/3/19.
 */

public class BannerModel extends NetResultModel {

    /**
     * code : 400
     * message : success
     * result : {"data":[{"PutTime":"2017-03-09 00:00:00","Title":"YOU衣柜用户指南","Description":"点击查看用户指南及会员注册详情","ImgUrl":"http://115.159.116.34:8089/Images/img1.jpg","Url":"http://www.baidu.com/"},{"PutTime":"2017-03-09 00:00:00","Title":"图片1标题","Description":"图片1详情","ImgUrl":"http://115.159.116.34:8089/Images/img2.jpg","Url":"http://www.baidu.com/"},{"PutTime":"2017-03-09 00:00:00","Title":"图片2标题","Description":"图片2详情","ImgUrl":"http://115.159.116.34:8089/Images/img3.jpg","Url":"http://www.baidu.com/"}]}
     */

    private ResultBean result;


    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * PutTime : 2017-03-09 00:00:00
         * Title : YOU衣柜用户指南
         * Description : 点击查看用户指南及会员注册详情
         * ImgUrl : http://115.159.116.34:8089/Images/img1.jpg
         * Url : http://www.baidu.com/
         */

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String PutTime;
            private String Title;
            private String Description;
            private String ImgUrl;
            private String Url;

            public String getPutTime() {
                return PutTime;
            }

            public void setPutTime(String PutTime) {
                this.PutTime = PutTime;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public String getUrl() {
                return Url;
            }

            public void setUrl(String Url) {
                this.Url = Url;
            }
        }
    }
}
