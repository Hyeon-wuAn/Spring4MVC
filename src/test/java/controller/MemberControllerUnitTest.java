package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import zero.hello.spring4.controller.IndexController;
import zero.hello.spring4.controller.MemberController;
import zero.hello.spring4.model.Member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/servlet-context.xml",
        "classpath:spring/root-context.xml"})
@WebAppConfiguration
public class MemberControllerUnitTest {
    @Autowired WebApplicationContext wctx;

    private MockMvc mockMvc;


    @Before
    public void setUp() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(
                this.wctx) .build();
    }

    // 테스트 수행
    @Test
    public void joinTest() throws Exception {
        mockMvc.perform(get("/member/join"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/join.tiles"));
    }

    @Test
    @Transactional

    public void joinokTest() throws Exception {
        mockMvc.perform(post("/member/join")
                .param("userid", "abc123")
                .param("passwd", "987xyz")
                .param("name", "abc123")
                .param("email", "abc123@987xyz.co.kr") )
                .andExpect(redirectedUrl("/member/login"));
    }

    @Test
    public void loginokTest() throws Exception {
        mockMvc.perform(post("/member/login")
                .param("userid", "abc123")
                .param("passwd", "987xyz"))
                .andExpect(redirectedUrl("/member/myinfo"));
    }

    @Test
    public void myinfoTest() throws Exception {
        // 모조 세션객체를 하나 만들어서 아이디를 저장해 둠
        MockHttpSession sess = new MockHttpSession();
        Member m = new Member();
        m.setUserid("abc123");
        sess.setAttribute("member",m);

        MvcResult mvcResult = mockMvc.perform(
                get("/member/myinfo").session(sess))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getModelAndView());
    }

}
