package com.study.yang.genericparadigm;

public enum OperateLevel {
    //加
    ADD("+", 0),
    //减
    SUBTRACT("-", 0),
    //乘
    MULTIPLY("*", 1),
    //除
    DIVIDE("/", 1),
    //左括号
    LEFT_PARENTHESIS("(", 3),
    //右括号
    RIGHT_PARENTHESIS(")", 2);

    private String operate;
    private int level;

    OperateLevel(String operate, int level) {
        this.operate = operate;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}

