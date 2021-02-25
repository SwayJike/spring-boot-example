package com.jourwon.spring.boot.service.fallback;

import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.HttpApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JourWon
 * @date 2021/2/25
 */
@Component
public class HttpApiFallback implements HttpApi {
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
}
