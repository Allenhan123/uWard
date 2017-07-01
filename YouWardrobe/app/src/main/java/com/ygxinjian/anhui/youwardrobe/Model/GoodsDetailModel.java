package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/7/1.
 */

public class GoodsDetailModel extends NetResultModel {


    /**
     * result : {"ProdID":"3","ProdTitle":"唯美清新 1","ProdDesc":"唯美清新1","Elated":"0","Size":"均码","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089//Images/CommodityImg/20170527/d0c72347-9445-490b-8d1a-84bb5a8b3310.jpg"},{"ImgUrl":"http://115.159.116.34:8089//Images/1/img2.jpg"},{"ImgUrl":"http://115.159.116.34:8089//Images/1/img3.jpg"},{"ImgUrl":"http://115.159.116.34:8089//Images/1/img4.jpg"},{"ImgUrl":"http://115.159.116.34:8089//Images/1/img5.jpg"}]}
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
         * ProdID : 3
         * ProdTitle : 唯美清新 1
         * ProdDesc : 唯美清新1
         * Elated : 0
         * Size : 均码
         * ImgUrls : [{"ImgUrl":"http://115.159.116.34:8089//Images/CommodityImg/20170527/d0c72347-9445-490b-8d1a-84bb5a8b3310.jpg"},{"ImgUrl":"http://115.159.116.34:8089//Images/1/img2.jpg"},{"ImgUrl":"http://115.159.116.34:8089//Images/1/img3.jpg"},{"ImgUrl":"http://115.159.116.34:8089//Images/1/img4.jpg"},{"ImgUrl":"http://115.159.116.34:8089//Images/1/img5.jpg"}]
         */

        private String ProdID;
        private String ProdTitle;
        private String ProdDesc;
        private String Elated;
        private String Size;
        private List<ImgUrlsBean> ImgUrls;

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

        public List<ImgUrlsBean> getImgUrls() {
            return ImgUrls;
        }

        public void setImgUrls(List<ImgUrlsBean> ImgUrls) {
            this.ImgUrls = ImgUrls;
        }

        public static class ImgUrlsBean {
            /**
             * ImgUrl : http://115.159.116.34:8089//Images/CommodityImg/20170527/d0c72347-9445-490b-8d1a-84bb5a8b3310.jpg
             */

            private String ImgUrl;

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }
        }
    }
}
