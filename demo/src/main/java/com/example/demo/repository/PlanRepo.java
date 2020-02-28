package com.example.demo.repository;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;


@Repository
public class PlanRepo {
	public static final String KEY = "ITEM";
    private RedisTemplate<String,String> redisTemplate;
    private ValueOperations valueOperations;

    public PlanRepo(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        valueOperations =redisTemplate.opsForValue();
    }
    //get plan by id
    public Object getPlan(String planId) {
    	return valueOperations.get(planId);
    	
    }

    //add plan into redis
    public void addPlan(JSONObject plan){
        //hashOperations.put(KEY,plan.get("objectId"),plan.toString());
        valueOperations.set(plan.get("objectId"), plan.toString());
    }
    
    //delete plan by id
    public void deletePlan(String id) {
    	redisTemplate.delete(id);
    }

}
