package com.ygxinjian.anhui.youwardrobe.Model;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by Olivine on 2017/6/12.
 */

public class AddressBodyModel extends BaseModel {


    //选填
    private String Country;   //选填默认中国
    private int AddressFlag;   //AddressFlag=地址标记（选填 0 家 / 1 办公）
    //必填
    private String uid;
    private String Province;
    private String City;   //县
    private String Township;   //乡镇
    private String Street;   //街道
    private String Address;   //详细地址
    private String zipCode;   //zipCode 邮编
    private int IsDefault;   // IsDefault=是否为默认地址（0否1是）
    private String Contact;   //  Contact=联系人姓名
    private String Telephone;   //  Telephone=联系人电话



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAddressFlag() {
        return AddressFlag;
    }

    public void setAddressFlag(int addressFlag) {
        AddressFlag = addressFlag;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getzipCode() {
        return zipCode;
    }

    public void setzipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getIsDefault() {
        return IsDefault;
    }

    public void setIsDefault(int isDefault) {
        IsDefault = isDefault;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getTownship() {
        return Township;
    }

    public void setTownship(String township) {
        Township = township;
    }

}
