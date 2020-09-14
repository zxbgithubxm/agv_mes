package main.java.cn.ac.casqiem.agv.orderchain.mapper;

import cn.ac.casqiem.agv.orderchain.entity.OrderChainTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderChainTemplateMapper extends BaseMapper<OrderChainTemplate> {

    @Select("SELECT * FROM order_chain_template WHERE NAME = #{name}")
    OrderChainTemplate findOneByName(@Param("name")String name);
}
