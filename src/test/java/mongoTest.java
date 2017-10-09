import com.rabit.dao.UserDao;
import com.rabit.modle.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class mongoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void monFindTest() {
        List<UserModel> userModels = userDao.findAll();
        if (userModels != null && userModels.size() > 0) {
            for (UserModel user : userModels) {
                System.out.println(user.getUserName() + ":"
                        + user.getPassword());
            }
        }
    }

    @Test
    public void monInsertTest() {
        UserModel user = new UserModel("1test111", "123456");
        userDao.insertUser(user);
        this.monFindTest();
    }
    @Test
    public void monRemoveTest() {
        String userName = "test111";
        userDao.removeUser(userName);
        this.monFindTest();
    }

    @Test
    public void monUpdateTest() {
        UserModel user = new UserModel("test111", "test");
        userDao.updateUser(user);
        this.monFindTest();
    }


    @Test
    public void monFindForRuq() {
        String userName = "test111";
        List<UserModel> userModels = userDao.findForRequery(userName);
        if (userModels != null && userModels.size() > 0) {
            for (UserModel user : userModels) {
                System.out.println(user.getUserName() + ":"
                        + user.getPassword());
            }
        }
    }


}
