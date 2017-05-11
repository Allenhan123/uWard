package com.ygxinjian.anhui.youwardrobe.Model;

/**
 * Created by Olivine on 2017/5/11.
 */

public class SaveUserInfoNetModel extends NetResultModel {


    public static class RequestModel {
        //        CardType=证件类型& CardNo=证件编码& Sex=性别& Birthday=生日&headImage=头像二进制流(编码BASE64)
        private int uid;
        private int CardType;
        private String CardNo;
        private int Sex;
        private String Birthday;
        private String headImage;

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String birthday) {
            Birthday = birthday;
        }

        public String getCardNo() {
            return CardNo;
        }

        public void setCardNo(String cardNo) {
            CardNo = cardNo;
        }

        public int getCardType() {
            return CardType;
        }

        public void setCardType(int cardType) {
            CardType = cardType;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public int getSex() {
            return Sex;
        }

        public void setSex(int sex) {
            Sex = sex;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }


}
