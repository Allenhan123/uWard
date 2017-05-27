package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/5/27.
 */

public class RentModel extends NetResultModel {

    /**
     * result : {"data":[{"ClassifyID":12,"ClassifyTitle":"高档礼服","ClassifyDesc":" ","ClassifyURL":"http://115.159.116.34:8089/Interface/i_Interface.aspx?m=when_classify_list&uid=18656009327&classify_id=12","ImgURL":"http://115.159.116.34:8089//Images/classify2_1.jpg"},{"ClassifyID":13,"ClassifyTitle":"COSPLAY","ClassifyDesc":" ","ClassifyURL":"http://115.159.116.34:8089/Interface/i_Interface.aspx?m=when_classify_list&uid=18656009327&classify_id=13","ImgURL":"http://115.159.116.34:8089//Images/classify2_2.jpg"},{"ClassifyID":14,"ClassifyTitle":"演出服","ClassifyDesc":" ","ClassifyURL":"http://115.159.116.34:8089/Interface/i_Interface.aspx?m=when_classify_list&uid=18656009327&classify_id=14","ImgURL":"http://115.159.116.34:8089//Images/classify2_3.jpg"},{"ClassifyID":15,"ClassifyTitle":"应聘正装","ClassifyDesc":"第一印象很重要哦。","ClassifyURL":"http://115.159.116.34:8089/Interface/i_Interface.aspx?m=when_classify_list&uid=18656009327&classify_id=15","ImgURL":"http://115.159.116.34:8089//Images/classify2_4.jpg"}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * ClassifyID : 12
             * ClassifyTitle : 高档礼服
             * ClassifyDesc :
             * ClassifyURL : http://115.159.116.34:8089/Interface/i_Interface.aspx?m=when_classify_list&uid=18656009327&classify_id=12
             * ImgURL : http://115.159.116.34:8089//Images/classify2_1.jpg
             */

            private int ClassifyID;
            private String ClassifyTitle;
            private String ClassifyDesc;
            private String ClassifyURL;
            private String ImgURL;

            public int getClassifyID() {
                return ClassifyID;
            }

            public void setClassifyID(int ClassifyID) {
                this.ClassifyID = ClassifyID;
            }

            public String getClassifyTitle() {
                return ClassifyTitle;
            }

            public void setClassifyTitle(String ClassifyTitle) {
                this.ClassifyTitle = ClassifyTitle;
            }

            public String getClassifyDesc() {
                return ClassifyDesc;
            }

            public void setClassifyDesc(String ClassifyDesc) {
                this.ClassifyDesc = ClassifyDesc;
            }

            public String getClassifyURL() {
                return ClassifyURL;
            }

            public void setClassifyURL(String ClassifyURL) {
                this.ClassifyURL = ClassifyURL;
            }

            public String getImgURL() {
                return ImgURL;
            }

            public void setImgURL(String ImgURL) {
                this.ImgURL = ImgURL;
            }
        }
    }
}
