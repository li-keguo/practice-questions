package com.algorithm.od;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LISP.
 * <p>
 * <p>
 * 仿lisp运算 <br>
 * 题目描述： <br>
 * LISP 语言唯一的语法就是括号要配对。 形如(OP P1 P2 …)，括号内元素由单个空格分割。 其中第一个
 * 元素 OP 为操作符，后续元素均为其参数，参数个数取决于操作符类型 注意：参数 P1, P2 也有可能是另外
 * 一个嵌套的(OP P1 P2 …) 当前 OP 类型为 add / sub / mul / div（全小写），分别代表整数的加减乘除法
 * 简单起见，所有 OP 参数个数均为 2  <br>
 * 举例:  <br>
 * 输入：(mul 3 -7) 输出： -21  <br>
 * 输入：(add 1 2) 输出：3  <br>
 * 输入：(sub(mul 2 4) (div 9 3)) 输出：5  <br>
 * 输入：(div 1 0) 输出：error 题目涉及数字均为整数，可能为负；  <br>
 * 不考虑 32 位溢出翻转，计算过程中也不会发生 32 位溢出翻转 除零错误时，  <br>
 * 输出 “error”，除法遇除不尽，向下取整，即 3 / 2 = 1  <br>
 * 输入描述：  <br>
 * 输入为长度不超过 512 的字符串，用例保证了无语法错误
 * 输出描述： <br>
 * 输出计算结果或者“error” <br>
 * 示例 1 <br>
 * 输入：(div 12 (sub 45 45)) <br>
 * 输出： <br>
 * error <br>
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class LISP {
    
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();
        final ScriptEngine js = factory.getEngineByName("js");
        final Object eval = js.eval("23*73");
        System.out.println(eval);
        
        final Cal cal = new Cal();
        cal.print("(sub(mul 2 4) (div 9 3))");
        cal.print("(div 12 (sub 45 45))");
        cal.print("(mul 3 -7)");
        
    }
    
    static class Cal {
        protected List<CalMethod> pool = new ArrayList<>(4);
        
        public Cal() {
            pool.add(new Add(this));
            pool.add(new Sub(this));
            pool.add(new Div(this));
            pool.add(new Mul(this));
        }
        
        void print(String expression) {
            try {
                final int result = cal(expression);
                System.out.println("最终结果：" + result);
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        
        int cal(String expression) {
            expression = expression.trim();
            if (expression.startsWith("(") && expression.endsWith(")")) {
                expression = expression.substring(1, expression.length() - 1).trim();
            }
            for (CalMethod method : pool) {
                if (method.match(expression)) {
                    return method.cal(expression);
                }
            }
            return 0;
        }
    }
    
    public static interface CalMethod {
        
        String name();
        
        default boolean match(String expression) {
            return expression.startsWith(name());
        }
        
        int cal(String expression);
    }
    
    public static abstract class BaseCalMethod implements CalMethod {
        
        protected final Cal cal;
        
        protected BaseCalMethod(Cal cal) {
            this.cal = cal;
        }
        
        @Override
        public int cal(String expression) {
            expression = expression.trim();
            if (expression.startsWith(name())) {
                final Integer one = findParamOne(expression.substring(name().length()));
                final Integer two = findParamTwo(expression.substring(name().length()));
                System.out.printf("计算表达式：%s [one: %d,two: %d]\n", expression, one, two);
                return cal(one, two);
            }
            return 0;
        }
        
        protected abstract int cal(Integer one, Integer two);
        
        protected Integer findParamOne(String expression) {
            expression = expression.trim();
            if (expression.startsWith("(")) {
                int count = 0;
                for (int i = 0; i < expression.length(); i++) {
                    if (Objects.equals(expression.charAt(i), '(')) {
                        count++;
                    }
                    if (Objects.equals(expression.charAt(i), ')')) {
                        count--;
                    }
                    if (count == 0) {
                        return cal.cal(expression.substring(1, i));
                    }
                }
            }
            return Integer.parseInt(expression.split(" ")[0]);
        }
        
        
        protected Integer findParamTwo(String expression) {
            expression = expression.trim();
            if (expression.endsWith(")")) {
                int count = 0;
                for (int i = expression.length() - 1; i > 0; i--) {
                    if (Objects.equals(expression.charAt(i), ')')) {
                        count++;
                    }
                    if (Objects.equals(expression.charAt(i), '(')) {
                        count--;
                    }
                    if (count == 0) {
                        return cal.cal(expression.substring(i));
                    }
                }
            }
            final String[] s = expression.split(" ");
            return Integer.parseInt(s[s.length - 1]);
        }
    }
    
    static class Add extends BaseCalMethod implements CalMethod {
        Add(Cal cal) {
            super(cal);
        }
        
        @Override
        public String name() {
            return "add";
        }
        
        @Override
        protected int cal(Integer one, Integer two) {
            return one + two;
        }
    }
    
    static class Sub extends BaseCalMethod implements CalMethod {
        Sub(Cal cal) {
            super(cal);
        }
        
        @Override
        public String name() {
            return "sub";
        }
        
        @Override
        protected int cal(Integer one, Integer two) {
            return one - two;
        }
    }
    
    static class Div extends BaseCalMethod implements CalMethod {
        Div(Cal cal) {
            super(cal);
        }
        
        @Override
        public String name() {
            return "div";
        }
        
        @Override
        protected int cal(Integer one, Integer two) {
            return one / two;
        }
    }
    
    static class Mul extends BaseCalMethod implements CalMethod {
        Mul(Cal cal) {
            super(cal);
        }
        
        @Override
        public String name() {
            return "mul";
        }
        
        @Override
        protected int cal(Integer one, Integer two) {
            return one * two;
        }
    }
    
}
