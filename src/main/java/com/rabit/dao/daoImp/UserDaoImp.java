package com.rabit.dao.daoImp;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.rabit.dao.UserDao;
import com.rabit.daoImp.AbstractBaseMongoTemplete;
import com.rabit.modle.UserModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
@Component(value = "UserDaoImp")
public class UserDaoImp extends AbstractBaseMongoTemplete implements UserDao{
    public List<UserModel> findAll() {
        // 需要设置集合对应的尸体类和相应的集合名，从而查询结果直接映射
        List<UserModel> userList = mongoTemplate.findAll(UserModel.class,
                "user");
        return userList;
    }

    public void insertUser(UserModel user) {
        // 设置需要插入到数据库的文档对象
        DBObject object = new BasicDBObject();
        object.put("userName", user.getUserName());
        object.put("password", user.getPassword());
        mongoTemplate.insert(object, "user");
    }

    public void removeUser(String userName) {
        // 设置删除条件，如果条件内容为空则删除所有
        Query query = new Query();
        Criteria criteria = new Criteria("userName");
        criteria.is(userName);
        query.addCriteria(criteria);
        mongoTemplate.remove(query, "user");
    }

    public void updateUser(UserModel user) {
        // 设置修改条件
        Query query = new Query();
        Criteria criteria = new Criteria("userName");
        criteria.is(user.getUserName());
        query.addCriteria(criteria);
        // 设置修改内容
        Update update = Update.update("password", user.getPassword());
        // 参数：查询条件，更改结果，集合名
        mongoTemplate.updateFirst(query, update, "user");
    }

    public List<UserModel> findForRequery(String userName) {
        Query query = new Query();
        Criteria criteria = new Criteria("userName");
        criteria.is(userName);
        query.addCriteria(criteria);
        // 查询条件，集合对应的实体类，集合名
        List<UserModel> userList = mongoTemplate.find(query, UserModel.class,
                "user");
        return userList;
    }
}
