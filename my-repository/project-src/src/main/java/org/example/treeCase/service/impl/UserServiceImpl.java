package org.example.treeCase.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.treeCase.mapper.UserMapper;
import org.example.treeCase.model.User;
import org.example.treeCase.service.UserService;
import org.example.treeCase.utils.SqlSessionFactoryUtils;

public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User queryEmail(String email) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryEmail(email);

        sqlSession.close();
        return user;
    }

    @Override
    public User signIn(String email, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUser(email, password);

        sqlSession.close();
        return user;
    }

    @Override
    public User queryName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryName(name);

        sqlSession.close();
        return user;
    }


    @Override
    public void signUp(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public String queryNameByEmail(String email) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String username = mapper.queryNameByEmail(email);

        sqlSession.close();
        return username;
    }
}
