package com.study.yang.genericparadigm;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Pattern;

public class ReversePolish {

    /**
     * 数据队列
     */
    public Stack<String> numbers = new Stack<String>();

    /**
     * 数组操作对象
     */
    private Stack<OperateLevel> operates = new Stack<>();

    /**
     * 计算器
     * 示例：
     * 3+2*7-9*4
     * [3, 2, 7, *, +, 9, 4, *, -]
     *
     * 9+(3-1)*3+10/2
     * [9, 3, 1, -, 3, *, +, 10, 2, /, +]
     *
     * @param calculateStr
     * @return
     */
    public int calculate(String calculateStr) {
        numbers.clear();
        operates.clear();
        int record = 0;
        for (int i = 0; i < calculateStr.length(); i++) {
            String chars = calculateStr.charAt(i) + "";
            if (isNumber(chars)) {
                //代表连续多次的数字字符
                if ((i == 0 || Math.abs(record - i) == 1) && numbers.size() > 0) {
                    //弹出栈顶元素
                    String lastChar = numbers.pop();
                    //将弹出之后的元素与当前元素相结合赋值给chars
                    chars = lastChar + chars;
                }
                numbers.push(chars);
                record = i;
            } else {
                OperateLevel stackTop = null;
                if (operates.size() > 0) {
                    //拿栈顶的元素
                    stackTop = operates.peek();
                }
                OperateLevel currentOperate = getCurrentOperate(chars);
                //栈顶元素为空,说明当前符号栈还没有添加符号
                if (stackTop == null) {
                    operates.push(currentOperate);
                } else {
                    int level = operateLevel(currentOperate, stackTop);
                    //当前元素优先级高于栈顶元素 或者当前元素为左括号 或者栈顶元素为左括号
                    if (level > 0 || currentOperate == OperateLevel.LEFT_PARENTHESIS || stackTop == OperateLevel.LEFT_PARENTHESIS) {
                        operates.push(currentOperate);
                    } else {//优先级小于或等于栈顶操作符优先级的时候，将操作符弹栈直至栈顶操作优先级小于当前操作符优先级或为左括号
                        OperateLevel popLevel = operates.pop();
                        while (level <= 0 && popLevel != null) {
                            //碰到左括号时不用再弹栈
                            if (popLevel == OperateLevel.LEFT_PARENTHESIS) {
                                break;
                            }
                            //将弹出的操作符保存到数据集合中
                            String operate = popLevel.getOperate();
                            numbers.push(operate);
                            //每遇到一个操作符就将该操作符之前的两个数据结合该操作符进行一次运算
                           // calculate();
                            if (operates.size() > 0) {
                                popLevel = operates.pop();
                                level = operateLevel(currentOperate, stackTop);
                            } else {
                                break;
                            }
                        }
                        if (currentOperate != OperateLevel.RIGHT_PARENTHESIS) {
                            operates.push(currentOperate);
                        }
                    }
                }
            }
        }
        if (operates.empty()) {
            System.out.println(numbers.pop());
        }

        while (!operates.empty()) {
            numbers.push(operates.pop().getOperate());
          //  calculate();
        }
        return 0;
    }

    /**
     * 计算数据以实现四则运算
     */
    private void calculate() {
        System.out.println(numbers);
        //数据栈中元素大小超过3
        while (numbers.size() >= 3) {
            String tempOperate = numbers.pop();
            String tempFirst = numbers.pop();
            String tempSecond = numbers.pop();
            double doubleFirst = Double.parseDouble(tempFirst);
            double doubleSecond = Double.parseDouble(tempSecond);
            String result = "";
            switch (tempOperate) {
                case "+":
                    result = doubleFirst + doubleSecond + "";
                    break;
                case "-":
                    result = doubleSecond - doubleFirst + "";
                    break;
                case "*":
                    result = doubleFirst * doubleSecond + "";
                    break;
                case "/":
                    result = doubleSecond / doubleFirst + "";
                    break;
            }
            numbers.push(result);
        }
        System.out.println(numbers);
    }

    private OperateLevel getCurrentOperate(String chars) {
        if ("+".equals(chars)) {
            return OperateLevel.ADD;
        } else if ("-".equals(chars)) {
            return OperateLevel.SUBTRACT;
        } else if ("x".equals(chars)) {
            return OperateLevel.MULTIPLY;
        } else if ("*".equals(chars)) {
            return OperateLevel.MULTIPLY;
        } else if ("/".equals(chars)) {
            return OperateLevel.DIVIDE;
        } else if ("÷".equals(chars)) {
            return OperateLevel.DIVIDE;
        } else if ("(".equals(chars)) {
            return OperateLevel.LEFT_PARENTHESIS;
        } else if (")".equals(chars)) {
            return OperateLevel.RIGHT_PARENTHESIS;
        }
        return null;
    }

    /**
     * 返回>0代表当前操作符优先级大于栈顶操作符
     * 返回0代表当前操作符优先级等于栈顶操作符
     * 返回<0代表当前操作符优先级等于栈顶操作符
     *
     * @return
     */
    public int operateLevel(OperateLevel currentOperateLevel, OperateLevel stactTopOperateLevel) {
        if (currentOperateLevel == OperateLevel.RIGHT_PARENTHESIS) {
            return 0;
        }
        return currentOperateLevel.getLevel() - stactTopOperateLevel.getLevel();
    }

    /**
     * Regex.IsMatch(inputerstr, "^([0-9]{1,}[.][0-9]*)$")
     *
     * @param chars
     * @return
     */
    public boolean isNumber(String chars) {
        if (chars == null)
            return false;
        if (".".equals(chars))
            return true;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(chars).matches();
    }


    @Override
    public String toString() {
        return numbers.toString();
    }

    public static void main(String[] args) {
        String operateStr = "9+(3-1)*3+10/2";//"(3+2)*3";////"10/2";//
        ReversePolish operateLevel = new ReversePolish();
        operateLevel.calculate(operateStr);
        System.out.println(operateLevel.numbers);
    }
}
