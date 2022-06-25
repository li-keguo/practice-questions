package com.algorithm.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Straight.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class Straight {
    public static final Map<String, Integer> POKER = new HashMap<>();
    public static final Map<Integer, String> POKER_R = new HashMap<>();
    
    static {
        int count = 0;
        for (int i = count; i <= 10; i++) {
            POKER.put(i + "", count++);
        }
        POKER.put("J", count++);
        POKER.put("Q", count++);
        POKER.put("K", count++);
        POKER.put("A", count++);
        POKER.put("2", count);
        System.out.println(POKER);
        POKER.forEach((k, v) -> POKER_R.put(v, k));
    }
    
    public static void main(String[] args) {
        String s = "2 9 J 10 3 4 K A 7 Q A 5 6";
        final List<String> pokers = Arrays.stream(s.split("\\s"))
                .sorted(Comparator.comparing(POKER::get))
                .collect(Collectors.toList());
        show(pokers);
        final List<Integer> pokerRs = pokers.stream()
                .map(POKER::get)
                .collect(Collectors.toList());
        
        int end = pokerRs.size();
        for (int i = 0; i < end; ) {
            final List<Integer> result = playCards(pokerRs, i);
            if (result == null) {
                i++;
                continue;
            }
            showR("出牌：", result);
            end = end - result.size();
        }
        showR("剩余牌：", pokerRs);
    }
    
    public static List<Integer> playCards(List<Integer> pokers, int start) {
        Integer last = pokers.get(start);
        List<Integer> removed = new ArrayList<>();
        removed.add(last);
        for (int i = start; i < pokers.size(); i++) {
            if (Objects.equals(pokers.get(i), POKER.get("2"))){
                continue;
            }
            if (pokers.get(i) == last + 1) {
                removed.add(pokers.get(i));
                pokers.set(i, pokers.get(i) + 100);
                last++;
            }
        }
        if (removed.size() >= 5) {
            pokers.removeIf(p -> p > 100);
            return removed;
        }
        for (int i = 0; i < pokers.size(); i++) {
            if (pokers.get(i) > 100) {
                pokers.set(i, pokers.get(i) - 100);
            }
        }
        return null;
    }
    
    public static void show(final List<String> pokers) {
        System.out.println(String.join(",", pokers));
    }
    
    public static void showR(String message, final List<Integer> pokers) {
        System.out.print(message);
        System.out.println(pokers.stream()
                .map(POKER_R::get)
                .collect(Collectors.joining(",")));
    }
    
}
