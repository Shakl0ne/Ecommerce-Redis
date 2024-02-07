package com.hmdp.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hmdp.utils.RedisConstants.SHOP_TYPE_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Autowired
    RedisService redisService;

    @Override
    public Result queryTypeList() {
        // 1.从 Redis 中查询商铺缓存
        Set<String> typeSet = redisService.zrange(SHOP_TYPE_KEY, 0, -1);
        if (CollectionUtil.isNotEmpty(typeSet)) {
            List<ShopType> typeList = typeSet.stream().map(s -> JSONUtil.toBean(s, ShopType.class)).collect(Collectors.toList());
            return Result.ok(typeList);
        }
        // 2.若 Redis 中无该数据的缓存，则查询数据库
        List<ShopType> typeList = query().orderByAsc("sort").list();

        // 3.判断数据库中是否存在
        if (CollectionUtil.isEmpty(typeList)) {
            // 3.1.数据库中也不存在，则返回 false
            return Result.fail("分类不存在！");
        }
        // 3.2.数据库中存在，则将查询到的信息存入 Redis
        for (ShopType type : typeList) {
            redisService.zadd(SHOP_TYPE_KEY,JSONUtil.toJsonStr(type),type.getSort());
        }
        return Result.ok(typeList);
    }
}
