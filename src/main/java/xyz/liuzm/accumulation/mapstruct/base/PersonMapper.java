package xyz.liuzm.accumulation.mapstruct.base;

import org.mapstruct.Mapper;

/**
 * 用来对 Person 以及 Human 进行相互转化
 */
@Mapper
public interface PersonMapper {

    /**
     * 将 Human 对象自动转化为 Person
     * @param human
     * @return
     */
    Person HumanToPerson(Human human);

}
