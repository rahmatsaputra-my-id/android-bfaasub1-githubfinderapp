package com.example.bffa_rahmatsaputra.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UsersItem implements Parcelable {

    public static final Creator<UsersItem> CREATOR = new Creator<UsersItem>() {
        @Override
        public UsersItem createFromParcel(Parcel in) {
            return new UsersItem(in);
        }

        @Override
        public UsersItem[] newArray(int size) {
            return new UsersItem[size];
        }
    };
    @SerializedName("follower")
    private String follower;
    @SerializedName("following")
    private String following;
    @SerializedName("name")
    private String name;
    @SerializedName("company")
    private String company;
    @SerializedName("location")
    private String location;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("repository")
    private String repository;
    @SerializedName("username")
    private String username;

    public UsersItem() {

    }

    private UsersItem(Parcel in) {
        this.follower = in.readString();
        this.following = in.readString();
        this.name = in.readString();
        this.company = in.readString();
        this.location = in.readString();
        this.avatar = in.readString();
        this.repository = in.readString();
        this.username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flag) {
        dest.writeString(this.follower);
        dest.writeString(this.following);
        dest.writeString(this.name);
        dest.writeString(this.company);
        dest.writeString(this.location);
        dest.writeString(this.avatar);
        dest.writeString(this.repository);
        dest.writeString(this.username);
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//	@Override
// 	public String toString(){
//		return
//			"UsersItem{" +
//			"follower = '" + follower + '\'' +
//			",following = '" + following + '\'' +
//			",name = '" + name + '\'' +
//			",company = '" + company + '\'' +
//			",location = '" + location + '\'' +
//			",avatar = '" + avatar + '\'' +
//			",repository = '" + repository + '\'' +
//			",username = '" + username + '\'' +
//			"}";
//		}

    @Override
    public int describeContents() {
        return 0;
    }


}