package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.dao.UserDao;
import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.dto.UserDTO;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 用户service实现类
 *
 * @author JourWon
 * @date 2021/2/6
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDTO getByPrimaryKey(Long userId) {
        Optional<User> optional = userDao.findById(userId);
        if (!optional.isPresent()) {
            throw new RuntimeException("查不到userId为" + userId + "对应的用户");
        }

        return BeanTransformUtils.transform(optional.get(), UserDTO.class);
    }

    @Override
    public List<UserDTO> listUsers() {
        List<User> listUsers = userDao.findAll();
        return BeanTransformUtils.transformList(listUsers, UserDTO.class);
    }

    @Override
    public CommonPageVO<UserVO> page(UserQuery userQuery) {
        User user = BeanTransformUtils.transform(userQuery, User.class);
        // jpa做分页查询时，分页从0开始
        Page<User> page = userDao.findAll(PageRequest.of(userQuery.getPageNum() - 1, userQuery.getPageSize()));
        List<UserVO> list = BeanTransformUtils.transformList(page.getContent(), UserVO.class);
        return new CommonPageVO<>(page.getNumber() + 1, page.getSize(), page.getNumberOfElements(), page.getTotalPages(), page.getTotalElements(), list);
    }

    @Override
    public boolean deleteByPrimaryKey(Long userId) {
        userDao.deleteById(userId);
        return true;
    }

    @Override
    public boolean insert(InsertUserDTO insertUserDTO) {
        User user = BeanTransformUtils.transform(insertUserDTO, User.class);
        userDao.save(user);
        return true;
    }

    @Override
    public boolean updateByPrimaryKey(UpdateUserDTO updateUserDTO) {
        User user = BeanTransformUtils.transform(updateUserDTO, User.class);
        userDao.save(user);
        return true;
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return BeanTransformUtils.transform(user, UserDTO.class);
    }

    @Override
    public Long countByUsername(String username) {
        return userDao.countByUsername(username);
    }

    @Override
    public List<UserDTO> findByEmailLike(String email) {
        List<User> list = userDao.findByEmailLike('%' + email + '%');
        return BeanTransformUtils.transformList(list, UserDTO.class);
    }

    @Override
    public Integer updateByUsernameAndUserId(String username, Long userId) {
        return userDao.updateByUsernameAndUserId(username, userId);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userDao.findByEmail(email);
        return BeanTransformUtils.transform(user, UserDTO.class);
    }

}
