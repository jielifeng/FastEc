package com.example.latte_ec2.deltest.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hasee on 2017-10-17.
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id(autoincrement = true)
    private Long id;
    private String name = null;
    private String avatar = null;
    private String address = null;
    private String gender = null;
    @Generated(hash = 2044749090)
    public UserProfile(Long id, String name, String avatar, String address,
            String gender) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.address = address;
        this.gender = gender;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
