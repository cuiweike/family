package test.com.family.springboot.system.service.impl; 

import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/** 
* UserServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>三月 17, 2019</pre> 
* @version 1.0 
*/ 
public class UserServiceImplTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc= MockMvcBuilders.webAppContextSetup(ctx).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getSql(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd) 
* 
*/ 
@Test
public void testGetSql() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTotal(String sql) 
* 
*/ 
@Test
public void testGetTotal() throws Exception {
//TODO: Test goes here...
    MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/emp").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
    //返回状态码
    int status=mvcResult.getResponse().getStatus();
    //返回内容
    String str=mvcResult.getResponse().getContentAsString();
    Assert.assertEquals("成功","200",status);
    Assert.assertEquals("记录数","4",str);

}

/** 
* 
* Method: getRows(String sql, int rn, int rowNumber) 
* 
*/ 
@Test
public void testGetRows() throws Exception { 
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

/** 
* 
* Method: getUser(String userName) 
* 
*/ 
@Test
public void testGetUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRows2(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd, int rn, int rowNumber) 
* 
*/ 
@Test
public void testGetRows2() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRows3(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd, int rn, int rowNumber) 
* 
*/ 
@Test
public void testGetRows3() throws Exception { 
//TODO: Test goes here... 
} 


} 
