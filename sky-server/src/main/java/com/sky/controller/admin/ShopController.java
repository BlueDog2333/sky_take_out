package com.sky.controller.admin;

import com.sky.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    public Result updateShopStatus(@PathVariable("status") Integer status) {
        redisTemplate.opsForValue().set("shop_status", status);
        return Result.success();
    }
    @GetMapping("/status")
    public Result getStatus(){
        return Result.success((Integer)redisTemplate.opsForValue().get("shop_status"));
    }
}
