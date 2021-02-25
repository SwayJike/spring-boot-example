package com.jourwon.spring.boot.service.fallback;

import com.github.lianjiatech.retrofit.spring.boot.degrade.FallbackFactory;
import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.HttpApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JourWon
 * @date 2021/2/25
 */
@Component
@Slf4j
public class HttpDegradeFallbackFactory implements FallbackFactory<HttpApi> {
    @Override
    public HttpApi create(Throwable cause) {
        log.error("触发熔断了! ", cause.getMessage(), cause);
        return new HttpApi() {

            @Override
            public UserVO getById(Long userId) {
                return null;
            }

            @Override
            public List<UserVO> list() {
                return null;
            }

            @Override
            public CommonPageVO<UserVO> page1(Integer pageNum, Integer pageSize, String username, String email, String mobilePhoneNumber) {
                return null;
            }

            @Override
            public boolean updateUser(Long userId, UpdateUserDTO updateUserDTO) {
                return false;
            }

            @Override
            public boolean removeById(Long userId) {
                return false;
            }

            @Override
            public boolean insert(InsertUserDTO insertUserDTO) {
                return false;
            }
        };
    }
}
