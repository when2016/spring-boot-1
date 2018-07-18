package xyz.wongs;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.wongs.async.AsyncTask;
import xyz.wongs.async.AsyncTaskByCustomizeThreadPool;

import java.util.concurrent.Future;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author WCNGS@QQ.CO
 * @version V1.0
 * @Title:
 * @Package spring-boot xyz.wongs
 * @Description: TODO
 * @date 2018/6/20 16:01
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = {MockServletContext.class, Application.class})
public class SpringBootTestCase {

    private MockMvc mvc;

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private AsyncTaskByCustomizeThreadPool asyncTaskByCustomizeThreadPool;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(
////                new HelloController(),
////                new UserController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 方法实现说明
     * @method      testAsyncTaskByCustomizeThreadPool
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param
     * @return      void
     * @exception
     * @date        2018/6/21 16:03
     */
    @Test
    public void testAsyncTaskByCustomizeThreadPool() throws Exception{
        asyncTaskByCustomizeThreadPool.doTaskOne();
        asyncTaskByCustomizeThreadPool.doTaskTwo();
        asyncTaskByCustomizeThreadPool.doTaskThree();
        Thread.currentThread().join();
    }


    /**
     * 方法实现说明
     *
     * @param
     * @return void
     * @throws
     * @method testAsyncTask
     * @author WCNGS@QQ.COM
     * @version
     * @date 2018/6/21 14:52
     * @see
     */
    @Test
    public void testAsyncTask() throws Exception {

        long start = System.currentTimeMillis();

        Future<String> task1 = asyncTask.doTaskOne();
        Future<String> task2 = asyncTask.doTaskTwo();
        Future<String> task3 = asyncTask.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    public void testUserControllerGetUsers() throws Exception {
        //测试UserController
        // 1、get查一下user列表，应该为空
        RequestBuilder request = get("/users/");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testUserControllerPostUsers() throws Exception {
        RequestBuilder request = null;
        // 2、post提交一个user
        request = post("/users/")
                //.param("id", "1")
                .param("name", "测试大师")
                .param("age", "20");
        mvc.perform(request)
//				.andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("success")));
    }

}
