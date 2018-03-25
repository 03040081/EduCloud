package com.zsc.zzc.educloud.model.db;

import com.zsc.zzc.educloud.model.bean.Collection;
import com.zsc.zzc.educloud.model.bean.Record;
import com.zsc.zzc.educloud.model.bean.SearchKey;
import com.zsc.zzc.educloud.model.bean.User;

import java.util.List;

import io.realm.Realm;

public interface DBHelper {
    Realm getRealm();

    //-------------------收藏相关----------------------
    void insertCollection(Collection bean);

    void deleteCollection(int videoId);

    void deleteAllCollection();

    boolean queryCollectionId(int videoId);

    List<Collection> getCollectionList();

    //-----------------播放记录相关---------------------
    void insertRecord(Record bean, int maxSize);

    void deleteRecord(int videoId);

    boolean queryRecordId(int videoId);

    List<Record> getRecordList();

    void deleteAllRecord();

    //-----------------搜索记录相关---------------------
    void insertSearchHistory(SearchKey bean);

    List<SearchKey> getSearchHistoryList(String value);

    void deleteSearchHistoryList(String value);

    void deleteSearchHistoryAll();

    List<SearchKey> getSearchHistoryListAll();

    //--------用户--------------------------------------
    void insertUserInfo(User user);

    void deleteUserInfo(int userId);

    User getUserInfo();
}
