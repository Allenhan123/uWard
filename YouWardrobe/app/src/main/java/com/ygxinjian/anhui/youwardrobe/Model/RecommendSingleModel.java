package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/3/24.
 */

public class RecommendSingleModel extends BaseModel {


    /**
     * code : 400
     * message : success
     * result : {"data":[{"ProdID":5,"ClassifyID":3,"ClassifyTitle":"简约中性","ClassifyDesc":" ","ProdTitle":"简约中性","ProdDesc":"简约中性","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=5"},{"ProdID":6,"ClassifyID":3,"ClassifyTitle":"简约中性","ClassifyDesc":" ","ProdTitle":"简约中性","ProdDesc":"简约中性","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=6"},{"ProdID":7,"ClassifyID":4,"ClassifyTitle":"时尚休闲","ClassifyDesc":" ","ProdTitle":"时尚休闲","ProdDesc":"时尚休闲","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=7"},{"ProdID":8,"ClassifyID":4,"ClassifyTitle":"时尚休闲","ClassifyDesc":" ","ProdTitle":"时尚休闲","ProdDesc":"时尚休闲","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=8"},{"ProdID":9,"ClassifyID":5,"ClassifyTitle":"都市丽人","ClassifyDesc":" ","ProdTitle":"都市丽人","ProdDesc":"都市丽人","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=9"},{"ProdID":10,"ClassifyID":5,"ClassifyTitle":"都市丽人","ClassifyDesc":" ","ProdTitle":"都市丽人","ProdDesc":"都市丽人","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=10"},{"ProdID":11,"ClassifyID":6,"ClassifyTitle":"文艺复古","ClassifyDesc":" ","ProdTitle":"文艺复古","ProdDesc":"文艺复古","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=11"},{"ProdID":12,"ClassifyID":6,"ClassifyTitle":"文艺复古","ClassifyDesc":" ","ProdTitle":"文艺复古","ProdDesc":"文艺复古","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=12"},{"ProdID":13,"ClassifyID":7,"ClassifyTitle":"民族风情","ClassifyDesc":" ","ProdTitle":"民族风情","ProdDesc":"民族风情","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=13"},{"ProdID":14,"ClassifyID":7,"ClassifyTitle":"民族风情","ClassifyDesc":" ","ProdTitle":"民族风情","ProdDesc":"民族风情","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=14"},{"ProdID":15,"ClassifyID":8,"ClassifyTitle":"原创设计","ClassifyDesc":" ","ProdTitle":"原创设计","ProdDesc":"原创设计","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=15"},{"ProdID":16,"ClassifyID":8,"ClassifyTitle":"原创设计","ClassifyDesc":" ","ProdTitle":"原创设计","ProdDesc":"原创设计","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=16"}]}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
             * ProdID : 5
             * ClassifyID : 3
             * ClassifyTitle : 简约中性
             * ClassifyDesc :
             * ProdTitle : 简约中性
             * ProdDesc : 简约中性
             * Elated : 0
             * Size : S|M|L|XL|XXL|XXXL|均码
             * ImgUrl : http://115.159.116.34:8089/Images/1/img1.jpg
             * Url : Interface/i_Interface.aspx/m=prod_details&prod_id=5
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
        }
    }
}
