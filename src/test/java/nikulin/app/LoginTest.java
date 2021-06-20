package nikulin.app;

import nikulin.app.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//спринг автоматически создает структуру клаассов, который подменяет слой мвс из спринг фреймворк - итог фейковое окржуеное без лишнегр кода
public class LoginTest {
    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        assertThat(mainController).isNotNull();
    }

    @Test
    public void mockTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(containsString("Hello")))
                .andExpect((ResultMatcher) content().string(containsString("Please, sign in or sign up")));

    }

    @Test
    public void loginTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

    }

    @Test
    public void correctLoginTest() throws Exception {
        this.mockMvc.perform(formLogin().user("1").password("1"))//каак мы в контексте определили конект пэйдж и вызывает обращение к этому пейдж
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    //невыерные данные пользователя
    public void badCredentials() throws Exception{
        this.mockMvc.perform(post("/login").param("user","tema"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
