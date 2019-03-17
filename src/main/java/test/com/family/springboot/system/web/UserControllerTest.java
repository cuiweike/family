package test.com.family.springboot.system.web; 

import com.family.springboot.SpringbootApplication;
import com.family.springboot.system.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/** 
* UserController Tester. 
* 
* @author <Authors name> 
* @since <pre>三月 17, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class UserControllerTest { 

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

@Before
public void before() throws Exception {
    mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getData(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd, HttpServletRequest request) 
* 
*/ 
@Test
public void testGetData() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteUser(String userIdArr, HttpServletRequest request) 
* 
*/ 
@Test
public void testDeleteUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getSex() 
* 
*/ 
@Test
public void testGetSex() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRole() 
* 
*/ 
@Test
public void testGetRole() throws Exception { 
//TODO: Test goes here...
    MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/userController/getRole")).andReturn();
    int status=mvcResult.getResponse().getStatus();
    String str=mvcResult.getResponse().getContentAsString();
    Assert.assertEquals("成功",200,status);
//    Assert.assertEquals("记录数","4",str);
} 

/** 
* 
* Method: verifyUser(String userId, String userName) 
* 
*/ 
@Test
public void testVerifyUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insertOrUpdateUser(String userId, String userName, String password, String sex, String roleId, String brithday, HttpServletRequest request) 
* 
*/ 
@Test
public void testInsertOrUpdateUser() throws Exception { 
//TODO: Test goes here... 
} 


} 
