package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/3/24.
 */

public class HomeCategoryModel extends NetResultModel {


    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ClassifyID : 1
         * ClassifyTitle : 时节时令
         * ClassifyDesc :
         * ClassifyURL : Interface/i_Interface.aspx?m=home_classify_list&uid=18656009327&classify_id=1
         * Items : [{"ProdID":"1","ProdTitle":"时节时令","ProdDesc":"时节时令","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ProdID":"2","ProdTitle":"时节时令","ProdDesc":"时节时令","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ProdID":"25","ProdTitle":"时节时令","ProdDesc":"时节时令","ImgUrl":" "},{"ProdID":"26","ProdTitle":"时节时令","ProdDesc":"时节时令","ImgUrl":" "}]
         */

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private int ClassifyID;
            private String ClassifyTitle;
            private String ClassifyDesc;
            private String ClassifyURL;
            /**
             * ProdID : 1
             * ProdTitle : 时节时令
             * ProdDesc : 时节时令
             * ImgUrl : http://115.159.116.34:8089/Images/1/img1.jpg
             */

            private List<ItemsBean> Items;

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

            public List<ItemsBean> getItems() {
                return Items;
            }

            public void setItems(List<ItemsBean> Items) {
                this.Items = Items;
            }

            public static class ItemsBean {
                private String ProdID;
                private String ProdTitle;
                private String ProdDesc;
                private String ImgUrl;

                public String getProdID() {
                    return ProdID;
                }

                public void setProdID(String ProdID) {
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

                public String getImgUrl() {
                    return ImgUrl;
                }

                public void setImgUrl(String ImgUrl) {
                    this.ImgUrl = ImgUrl;
                }
            }
        }
    }
}