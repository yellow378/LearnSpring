package org.lwx.learnspring.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lwx.learnspring.LearnSpringApplication;
import org.lwx.learnspring.dao.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = LearnSpringApplication.class)
@RunWith(SpringRunner.class)
@MapperScan("org.lwx.learnspring.mapper")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserById(){
        UserDO userDO = userMapper.getUserById(1);
        System.out.println(userDO);
        Assert.assertNotNull(userDO);
        Assert.assertEquals("org/lwx", userDO.getUsername());
    }
}
