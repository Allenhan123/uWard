package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/3/24.
 */

public class HomeCategoryModel extends BaseModel {


    /**
     * code : 400
     * message : success
     * result : {"data":[{"ID":1,"Title":"时节时令","Description":"时节时令","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]},{"ID":2,"Title":"豆蔻年华","Description":"豆蔻年华","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]},{"ID":3,"Title":"简约中性","Description":"简约中性","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]},{"ID":4,"Title":"时尚休闲","Description":"时尚休闲","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]},{"ID":5,"Title":"都市丽人","Description":"都市丽人","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]},{"ID":6,"Title":"文艺复古","Description":"文艺复古","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]},{"ID":7,"Title":"民族风情","Description":"民族风情","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]},{"ID":8,"Title":"原创设计","Description":"原创设计","ImgUrls":[{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]}]}
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
             * ID : 1
             * Title : 时节时令
             * Description : 时节时令
             * ImgUrls : [{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"},{"ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg"}]
             */

            private int ID;
            private String Title;
            private String Description;
            private List<ImgUrlsBean> ImgUrls;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
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

            public List<ImgUrlsBean> getImgUrls() {
                return ImgUrls;
            }

            public void setImgUrls(List<ImgUrlsBean> ImgUrls) {
                this.ImgUrls = ImgUrls;
            }

            public static class ImgUrlsBean {
                /**
                 * ImgUrl : http://115.159.116.34:8089/Images/1/img1.jpg
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
}
