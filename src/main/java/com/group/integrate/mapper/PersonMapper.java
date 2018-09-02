package com.group.integrate.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.group.integrate.domain.Person;
import com.group.integrate.dto.PersonDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/8/14.
 */
//@Repository
public interface PersonMapper extends BaseMapper<Person> {
    @Select("SELECT * FROM person WHERE id = #{id}")
    Person selectPerson(int id);

    PersonDTO selectByPersonId(@Param(value = "id") int id);


    List<Person> selectPersonsByPage(Page mybatiesPage);
}
