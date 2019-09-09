package com.escst.commons.sharding;


import com.alibaba.fastjson.JSON;
import com.escst.commons.utils.UuidUtils;
import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;


public class TableShardingAlgorithm implements PreciseShardingAlgorithm<String> {


    @Override
    public String doSharding(Collection<String> availableTargetNames,
                             PreciseShardingValue<String> preciseShardingValue) {
        System.out.println("collection:" + JSON.toJSONString(availableTargetNames) + ",preciseShardingValue:" + JSON.toJSONString(preciseShardingValue));
        for (String name : availableTargetNames) {
            // =与IN中分片键对应的值
            int value = preciseShardingValue.getValue().hashCode();
            // 分10个表
            int i = 10;
            // 判断路由至哪个表
            if (name.endsWith(String.valueOf(value % i))) {
                return name;
            }
        }
        throw new UnsupportedOperationException();
    }




    public static void main(String[] args) {
//        String s = "3ba322dbb1c842e990445c5b33ab026f";
//        int i = s.hashCode() % 10;
        System.out.println(Math.abs(-1));
    }

}
