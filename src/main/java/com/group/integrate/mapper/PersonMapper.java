package com.group.integrate.mapper;

import com.baomidou.mybatisplus.service.IService;
import com.group.integrate.domain.Person;
import com.group.integrate.dto.PersonDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/8/14.
 */
@Repository
public interface PersonMapper extends IService<Person>{
    @Select("SELECT * FROM person WHERE id = #{id}")
    Person selectPerson(int id);

    PersonDTO selectByPersonId(@Param(value = "id") int id);


}
