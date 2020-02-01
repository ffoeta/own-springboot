package com.spring.rest.V2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.dto.V1.open.AuthenticationRequestDtoV1;
import com.spring.security.jwt.JwtTokenFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class AuthTest {

//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Autowired
//    AuthRouter authRouter;
//
//    @Autowired
//    private WebApplicationContext context;


//    @Test
//    public void UserSecurityTestV1() throws Exception{
//        this.mockMvc.perform(get(Constants.USER_ENDPOINT_V1))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void AdminSecurityTestV1() throws Exception{
//        this.mockMvc.perform(get(Constants.ADMIN_ENDPOINT_V1))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void UserSecurityTestV2() throws Exception{
//        this.mockMvc.perform(get(Constants.USER_ENDPOINT_V2))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void AdminSecurityTestV2() throws Exception{
//        this.mockMvc.perform(get(Constants.ADMIN_ENDPOINT_V2))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void LoginTestV1() throws Exception{
//        this.mockMvc.perform(formLogin().user("test").password("test"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void LoginTestV2() throws Exception{
//
//        ObjectMapper mapper = new ObjectMapper();
//        AuthenticationRequestDtoV1 authenticationRequestDtoV1 = new AuthenticationRequestDtoV1();
//        authenticationRequestDtoV1.setUsername("test");
//        authenticationRequestDtoV1.setPassword("test");
//
//        String json = mapper.writeValueAsString(authenticationRequestDtoV1);
//
//        this.mockMvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(json))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

//    @Test
//    public void SignUpTestFailedV2() throws Exception{
//
//        ObjectMapper mapper = new ObjectMapper();
//        AuthenticationRequestDtoV1 authenticationRequestDtoV1 = new AuthenticationRequestDtoV1();
//        authenticationRequestDtoV1.setUsername("test");
//        authenticationRequestDtoV1.setPassword("test");
//
//        String json = mapper.writeValueAsString(authenticationRequestDtoV1);
//
//        this.mockMvc.perform(post("/api/v2/auth/signup").contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(json))
//                .andDo(print())
//                .andExpect();
//    }
//}
