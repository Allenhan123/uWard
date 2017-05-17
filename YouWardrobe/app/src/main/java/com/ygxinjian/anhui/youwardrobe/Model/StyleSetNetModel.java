package com.ygxinjian.anhui.youwardrobe.Model;

import java.util.List;

/**
 * Created by Olivine on 2017/5/17.
 */

public class StyleSetNetModel extends NetResultModel {


    /**
     * result : {"data":[{"ClassifyID":1,"ClassifyTitle":"新品推荐","ClassifyDesc":"时尚新品及时推送，满足美的要求。","CoverImg":"http://115.159.116.34:8089//Images/classify1.jpg","Checked":true},{"ClassifyID":2,"ClassifyTitle":"豆蔻年华","ClassifyDesc":"","CoverImg":"http://115.159.116.34:8089//Images/classify2.jpg","Checked":true},{"ClassifyID":3,"ClassifyTitle":"简约中性","ClassifyDesc":"","CoverImg":"http://115.159.116.34:8089//Images/classify3.jpg","Checked":false},{"ClassifyID":4,"ClassifyTitle":"时尚休闲","ClassifyDesc":"","CoverImg":"http://115.159.116.34:8089//Images/classify4.jpg","Checked":true},{"ClassifyID":5,"ClassifyTitle":"都市丽人","ClassifyDesc":"","CoverImg":"http://115.159.116.34:8089//Images/classify5.jpg","Checked":true},{"ClassifyID":6,"ClassifyTitle":"文艺复古","ClassifyDesc":"","CoverImg":"http://115.159.116.34:8089//Images/classify6.jpg","Checked":false},{"ClassifyID":7,"ClassifyTitle":"民族风情","ClassifyDesc":"","CoverImg":"http://115.159.116.34:8089//Images/classify7.jpg","Checked":false},{"ClassifyID":8,"ClassifyTitle":"原创设计","ClassifyDesc":"","CoverImg":"http://115.159.116.34:8089//Images/classify8.jpg","Checked":true}]}
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
             * ClassifyID : 1
             * ClassifyTitle : 新品推荐
             * ClassifyDesc : 时尚新品及时推送，满足美的要求。
             * CoverImg : http://115.159.116.34:8089//Images/classify1.jpg
             * Checked : true
             */

            private int ClassifyID;
            private String ClassifyTitle;
            private String ClassifyDesc;
            private String CoverImg;
            private boolean Checked;

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

            public String getCoverImg() {
                return CoverImg;
            }

            public void setCoverImg(String CoverImg) {
                this.CoverImg = CoverImg;
            }

            public boolean isChecked() {
                return Checked;
            }

            public void setChecked(boolean Checked) {
                this.Checked = Checked;
            }
        }
    }
}
