package com.zsc.zzc.educloud.model;

import com.zsc.zzc.educloud.model.bean.Collection;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.Record;
import com.zsc.zzc.educloud.model.bean.SearchKey;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.db.DBHelper;
import com.zsc.zzc.educloud.model.http.HttpHelper;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

public class DataManager implements HttpHelper, DBHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
    }


    @Override
    public Realm getRealm() {
        return mDbHelper.getRealm();
    }

    @Override
    public void insertCollection(Collection bean) {
        mDbHelper.insertCollection(bean);
    }

    @Override
    public void deleteCollection(String videoId) {
        mDbHelper.deleteCollection(videoId);
    }

    @Override
    public void deleteAllCollection() {
        mDbHelper.deleteAllCollection();
    }

    @Override
    public boolean queryCollectionId(String videoId) {
        return mDbHelper.queryCollectionId(videoId);
    }

    @Override
    public List<Collection> getCollectionList() {
        return mDbHelper.getCollectionList();
    }

    @Override
    public void insertRecord(Record bean, int maxSize) {
        mDbHelper.insertRecord(bean, maxSize);
    }

    @Override
    public void deleteRecord(String videoId) {
        mDbHelper.deleteRecord(videoId);
    }

    @Override
    public boolean queryRecordId(String videoId) {
        return mDbHelper.queryRecordId(videoId);
    }

    @Override
    public List<Record> getRecordList() {
        return mDbHelper.getRecordList();
    }

    @Override
    public void deleteAllRecord() {
        mDbHelper.deleteAllRecord();
    }

    @Override
    public void insertSearchHistory(SearchKey bean) {
        mDbHelper.insertSearchHistory(bean);
    }

    @Override
    public List<SearchKey> getSearchHistoryList(String value) {
        return mDbHelper.getSearchHistoryList(value);
    }

    @Override
    public void deleteSearchHistoryList(String value) {
        mDbHelper.deleteSearchHistoryList(value);
    }

    @Override
    public void deleteSearchHistoryAll() {
        mDbHelper.deleteSearchHistoryAll();
    }

    @Override
    public List<SearchKey> getSearchHistoryListAll() {
        return mDbHelper.getSearchHistoryListAll();
    }

    @Override
    public void insertUserInfo(User user) {
        mDbHelper.insertUserInfo(user);
    }

    @Override
    public void deleteUserInfo(String userId) {
        mDbHelper.deleteUserInfo(userId);
    }

    @Override
    public User getUserInfo() {
        return mDbHelper.getUserInfo();
    }

    ///////////////////////////////////////////////////////////
    @Override
    public Observable<VideoHttpResponse<List<Course>>> fetchRecommendVideos() {
        return mHttpHelper.fetchRecommendVideos();
    }
///////////////////////////////////////////////////////////

}
