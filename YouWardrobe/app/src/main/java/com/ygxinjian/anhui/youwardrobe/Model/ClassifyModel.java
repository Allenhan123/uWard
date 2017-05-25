package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/5/25.
 */

public class ClassifyModel extends NetResultModel {


    /**
     * result : {"data":[{"ProdID":1,"ProdTitle":"时节时令","ProdDesc":"时节时令","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089//Images/1/img1.jpg","Url":"http://115.159.116.34:8089/http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=1&uid=18656009327"},{"ProdID":2,"ProdTitle":"小清新","ProdDesc":"清新的时令着装","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089//Images/1/img1.jpg","Url":"http://115.159.116.34:8089/http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=2&uid=18656009327"},{"ProdID":25,"ProdTitle":"靓丽脱俗","ProdDesc":"淡雅的美，不失靓丽的风景。","Elated":"0","Size":"","ImgUrl":"","Url":"http://115.159.116.34:8089/http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=25&uid=18656009327"},{"ProdID":26,"ProdTitle":"时节时令","ProdDesc":"时节时令","Elated":"0","Size":"","ImgUrl":"","Url":"http://115.159.116.34:8089/http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=26&uid=18656009327"}]}
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
             * ProdTitle : 时节时令
             * ProdDesc : 时节时令
             * Elated : 0
             * Size : S|M|L|XL|XXL|XXXL|均码
             * ImgUrl : http://115.159.116.34:8089//Images/1/img1.jpg
             * Url : http://115.159.116.34:8089/http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=1&uid=18656009327
             */

            private int ProdID;
            private String ProdTitle;
            private String ProdDesc;
            private String Elated;
            private String Size;
            private String ImgUrl;
            private String Url;

            public int getProdID() {
                return ProdID;
            }

            public void setProdID(int ProdID) {
                this.ProdID = ProdID;
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
        }
    }
}
