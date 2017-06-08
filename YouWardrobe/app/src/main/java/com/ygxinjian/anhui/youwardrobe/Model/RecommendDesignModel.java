package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/6/7.
 */

public class RecommendDesignModel extends BaseModel {

    /**
     * code : 400
     * message : success
     * result : {"data":[{"ProdID":1,"ClassifyID":1,"ClassifyTitle":"新品推荐","ClassifyDesc":"时尚新品及时推送，满足美的要求。","ProdTitle":"新品推荐1","ProdDesc":"新品推荐1","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"/Images/CommodityImg/20170527/1b0ed7ec-a70f-4e0b-ac0c-073f4663fb0a.jpg","Url":"http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=1"},{"ProdID":2,"ClassifyID":1,"ClassifyTitle":"新品推荐","ClassifyDesc":"时尚新品及时推送，满足美的要求。","ProdTitle":"新品推荐2","ProdDesc":"新品推荐2","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"/Images/CommodityImg/20170527/68808c4c-5653-4687-8133-2f1382d62223.jpg","Url":"http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=2"},{"ProdID":5,"ClassifyID":3,"ClassifyTitle":"简约中性","ClassifyDesc":" ","ProdTitle":"简约中性1","ProdDesc":"简约中性1","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"/Images/CommodityImg/20170527/c67db552-79d5-4c83-9fdb-bd2fa775e8f6.jpg","Url":"http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=5"},{"ProdID":6,"ClassifyID":3,"ClassifyTitle":"简约中性","ClassifyDesc":" ","ProdTitle":"简约中性2","ProdDesc":"简约中性2","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"/Images/CommodityImg/20170527/6fa254c2-2d84-410f-b296-f4584f244265.jpg","Url":"http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=6"},{"ProdID":7,"ClassifyID":4,"ClassifyTitle":"时尚休闲","ClassifyDesc":" ","ProdTitle":"时尚休闲1","ProdDesc":"时尚休闲2","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"/Images/CommodityImg/20170527/d766a198-a352-4787-89a3-7ea6a82ba0e0.jpeg","Url":"http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=7"},{"ProdID":0,"ClassifyID":0,"ClassifyTitle":"其他推荐","ClassifyDesc":"其他套装推荐","ProdTitle":"其他推荐","ProdDesc":"其他套装推荐","Elated":"0","Size":"","ImgUrl":"其他推荐图片地址","Url":"其他推荐链接地址"}]}
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
             * ProdID : 1
             * ClassifyID : 1
             * ClassifyTitle : 新品推荐
             * ClassifyDesc : 时尚新品及时推送，满足美的要求。
             * ProdTitle : 新品推荐1
             * ProdDesc : 新品推荐1
             * Elated : 0
             * Size : S|M|L|XL|XXL|XXXL|均码
             * ImgUrl : /Images/CommodityImg/20170527/1b0ed7ec-a70f-4e0b-ac0c-073f4663fb0a.jpg
             * Url : http://115.159.116.34:8089/Interface/i_Interface.aspx/m=prod_details&prod_id=1
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
