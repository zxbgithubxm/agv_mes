package test.java.cn.ac.casqiem.agv.orderchain;

import cn.ac.casqiem.agv.orderchain.config.RCSProperties;
import cn.ac.casqiem.agv.orderchain.dto.DispatchOrderChain;
import cn.ac.casqiem.agv.orderchain.dto.User;
import cn.ac.casqiem.agv.orderchain.entity.OrderChainTemplate;
import cn.ac.casqiem.agv.orderchain.service.OrderChainTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RCSLoginTest {

    @Autowired
    private RCSProperties rcsProperties;

    @Test
    public void config() {
        System.out.println(rcsProperties);
    }


    @Test
    public void login() {
        String loginURL = "http://61.131.48.180:19523/api/login";
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        Mono<ClientResponse> result = WebClient.create()
                .post().uri(loginURL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange();
        ClientResponse response = result.block();
        if (response.statusCode() == HttpStatus.OK) {
            ResponseCookie cookie = response.cookies().getFirst("JSESSIONID");
            System.out.println("kdf" + cookie.getMaxAge() + "  " + cookie.getValue() + cookie.getName());
        }

    }
}
