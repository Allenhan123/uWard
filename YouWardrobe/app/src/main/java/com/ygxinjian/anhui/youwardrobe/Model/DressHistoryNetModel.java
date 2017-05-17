package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by Olivine on 2017/5/17.
 */

public class DressHistoryNetModel extends NetResultModel {

    /**
     * result : {"data":[{"ProdID":1,"ClassifyID":1,"ClassifyTitle":"新品推荐","ClassifyDesc":"时尚新品及时推送，满足美的要求。","ProdTitle":"时节时令","ProdDesc":"时节时令","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"/Images/1/img1.jpg","Url":"http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=1","TransType":"租赁","TransCost":"199.00","TransTime":"2017-04-10 23:55:41"}]}
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
             * ProdID : 1
             * ClassifyID : 1
             * ClassifyTitle : 新品推荐
             * ClassifyDesc : 时尚新品及时推送，满足美的要求。
             * ProdTitle : 时节时令
             * ProdDesc : 时节时令
             * Elated : 0
             * Size : S|M|L|XL|XXL|XXXL|均码
             * ImgUrl : /Images/1/img1.jpg
             * Url : http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=1
             * TransType : 租赁
             * TransCost : 199.00
             * TransTime : 2017-04-10 23:55:41
             */

            private int ProdID;
            private int ClassifyID;
            private String ClassifyTitle;
            private String ClassifyDesc;
            private String ProdTitle;
            private String ProdDesc;
            private String Elated;
            private String Size;
            private String ImgUrl;
            private String Url;
            private String TransType;
            private String TransCost;
            private String TransTime;

            public int getProdID() {
                return ProdID;
            }

            public void setProdID(int ProdID) {
                this.ProdID = ProdID;
            }

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

            public String getProdTitle() {
                return ProdTitle;
            }

            public void setProdTitle(String ProdTitle) {
                this.ProdTitle = ProdTitle;
            }

            public String getProdDesc() {
                return ProdDesc;
            }

            public void setProdDesc(String ProdDesc) {
                this.ProdDesc = ProdDesc;
            }

            public String getElated() {
                return Elated;
            }

            public void setElated(String Elated) {
                this.Elated = Elated;
            }

            public String getSize() {
                return Size;
            }

            public void setSize(String Size) {
                this.Size = Size;
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

            public String getTransType() {
                return TransType;
            }

            public void setTransType(String TransType) {
                this.TransType = TransType;
            }

            public String getTransCost() {
                return TransCost;
            }

            public void setTransCost(String TransCost) {
                this.TransCost = TransCost;
            }

            public String getTransTime() {
                return TransTime;
            }

            public void setTransTime(String TransTime) {
                this.TransTime = TransTime;
            }
        }
    }
}
