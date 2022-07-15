package com.algorithm.test;

import java.util.HashMap;
import java.util.Map;

public class StockPrice {
    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        // 时间戳为 [1] ，对应的股票价格为 [10] 。
        stockPrice.update(1, 10);
        // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
        stockPrice.update(2, 5);
        // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
        stockPrice.current();
        // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
        stockPrice.maximum();
        // 之前时间戳为 1 的价格错误，价格更新为 3 。
        stockPrice.update(1, 3);
        // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
        // 返回 5 ，更正后最高价格为 5 。
        stockPrice.maximum();
        // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
        stockPrice.update(4, 2);
        // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
        stockPrice.minimum();
    }
    
    private final Map<Integer, Integer> priceMap;
    private Integer max;
    
    private Integer maxTime;
    private Integer min;
    private Integer current;
    
    public StockPrice() {
        priceMap = new HashMap<>();
        max = 0;
        min = Integer.MAX_VALUE;
        maxTime = 0;
    }
    
    public void update(int timestamp, int price) {
        if (priceMap.containsKey(timestamp)) {
            priceMap.put(timestamp, price);
            min = priceMap.values().stream().min(Integer::compareTo).get();
            max = priceMap.values().stream().max(Integer::compareTo).get();
        } else {
            priceMap.put(timestamp, price);
        }
        min = Math.min(price, min);
        max = Math.max(price, max);
        maxTime = Math.max(timestamp, maxTime);
        if (timestamp == maxTime) {
            current = price;
        }
    }
    
    public int current() {
        return current;
    }
    
    public int maximum() {
        return max;
    }
    
    public int minimum() {
        return min;
    }
}
