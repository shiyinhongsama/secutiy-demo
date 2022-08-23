package cn.syh.demo.mapper;

import cn.syh.demo.entity.Role;
import cn.syh.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<Role> getRolesByUid(Integer uid);

    User loadUserByUsername(String username);

}
