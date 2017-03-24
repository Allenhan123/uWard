package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by handongqiang on 17/3/24.
 */

public class RecommendSingleModel extends BaseModel {

    /**
     * code : 400
     * message : success
     * result : {"data":[{"ID":1,"Title":"时节时令","Description":"时节时令","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=1"},{"ID":2,"Title":"时节时令","Description":"时节时令","Elated":"0","Size":"S|M|L|XL|XXL|XXXL|均码","ImgUrl":"http://115.159.116.34:8089/Images/1/img1.jpg","Url":"Interface/i_Interface.aspx/m=prod_details&prod_id=2"}]}
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
             * Elated : 0
             * Size : S|M|L|XL|XXL|XXXL|均码
             * ImgUrl : http://115.159.116.34:8089/Images/1/img1.jpg
             * Url : Interface/i_Interface.aspx/m=prod_details&prod_id=1
             */

            private int ID;
            private String Title;
            private String Description;
            private String Elated;
            private String Size;
            private String ImgUrl;
            private String Url;

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
