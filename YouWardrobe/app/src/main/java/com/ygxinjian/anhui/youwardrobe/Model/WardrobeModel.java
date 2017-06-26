package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/6/23.
 */

public class WardrobeModel extends BaseModel {

    /**
     * code : 400
     * message : success
     * result : {"data":[{"ClassifyID":1,"ClassifyTitle":"新品推荐","ClassifyDesc":"时尚新品及时推送，满足美的要求。","ClassifyURL":"http://115.159.116.34:8089/Interface/i_Interface.aspx?m=home_classify_list&uid=18656009327&classify_id=1","Items":[{"CartID":"2","ProdID":"2","ProdTitle":"新品推荐2","ProdDesc":"新品推荐2","ImgUrl":"http://115.159.116.34:8089//Images/CommodityImg/20170527/68808c4c-5653-4687-8133-2f1382d62223.jpg"}]}]}
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
             * ClassifyID : 1
             * ClassifyTitle : 新品推荐
             * ClassifyDesc : 时尚新品及时推送，满足美的要求。
             * ClassifyURL : http://115.159.116.34:8089/Interface/i_Interface.aspx?m=home_classify_list&uid=18656009327&classify_id=1
             * Items : [{"CartID":"2","ProdID":"2","ProdTitle":"新品推荐2","ProdDesc":"新品推荐2","ImgUrl":"http://115.159.116.34:8089//Images/CommodityImg/20170527/68808c4c-5653-4687-8133-2f1382d62223.jpg"}]
             */

            private boolean choosed;
            private int ClassifyID;
            private String ClassifyTitle;
            private String ClassifyDesc;
            private String ClassifyURL;
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

            public void setChoosed(boolean choosed) {
                this.choosed = choosed;
            }

            public boolean isChoosed() {
                return choosed;
            }

            public static class ItemsBean {
                /**
                 * CartID : 2
                 * ProdID : 2
                 * ProdTitle : 新品推荐2
                 * ProdDesc : 新品推荐2
                 * ImgUrl : http://115.159.116.34:8089//Images/CommodityImg/20170527/68808c4c-5653-4687-8133-2f1382d62223.jpg
                 */

                private boolean choosed;

                private String CartID;
                private String ProdID;
                private String ProdTitle;
                private String ProdDesc;
                private String ImgUrl;

                public String getCartID() {
                    return CartID;
                }

                public void setCartID(String CartID) {
                    this.CartID = CartID;
                }

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

                public boolean isChoosed() {
                    return choosed;
                }

                public void setChoosed(boolean choosed) {
                    this.choosed = choosed;
                }
            }
        }
    }
}
