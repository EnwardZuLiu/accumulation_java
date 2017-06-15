package xyz.liuzm.accumulation.developer_platform;

import com.google.common.base.Strings;
import lombok.Getter;

@Getter
public class OriginalData {

    public static class Builder {
        private Object o;
        private String key;
        private String clazz;
        private ReturnType returnType;
        private String strategy;
        private Destination destination;

        private Builder(){}

        public static Builder instance() {
            return new Builder();
        }

        public Builder addOriginal(Object o, String key) {
            this.o = o;
            this.key = key;
            return this;
        }

        public Builder addClazzType(String s) {
            this.clazz = s;
            return this;
        }

        public Builder addReturnType(String s) {
            if (Strings.isNullOrEmpty(s)) {
                throw new RuntimeException("返回值类型不可以为空");
            }
            if (s.equalsIgnoreCase("value_class")) {
                this.returnType = ReturnType.VALUE_CLASS;
            } else if (s.equalsIgnoreCase("value_class_size")) {
                this.returnType = ReturnType.VALUE_CLASS_SIZE;
            } else {
                throw new RuntimeException("返回值类型不可以为空");
            }
            return this;
        }

        // 该参数的处理策略
        public Builder addHandleStrategy(String strategy) {
            if (Strings.isNullOrEmpty(strategy)) {
                throw new RuntimeException("处理策略不可以为空");
            }
            char[] charArray = strategy.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            strategy = String.valueOf(charArray).trim() + "Strategy";
            this.strategy = strategy;
            return this;
        }

        public Builder addDestination(String d) {
            if (Strings.isNullOrEmpty(d)) {
                throw new RuntimeException();
            }
            if (d.equalsIgnoreCase("metadata")) {
                this.destination = Destination.METADATA;
            } else if (d.equalsIgnoreCase("parameter_json")) {
                this.destination = Destination.PARAMETER_JSON;
            } else if (d.equalsIgnoreCase("fixed_value")) {
                this.destination = Destination.FIXED_VALUE;
            } else {
                throw new RuntimeException();
            }
            return this;
        }

        public OriginalData build() {
            return new OriginalData(this);
        }
    }

    public enum ReturnType {
        VALUE_CLASS, VALUE_CLASS_SIZE;
    }

    public enum Destination {
        METADATA, PARAMETER_JSON, FIXED_VALUE;
    }




    private final Object original; // 用来存放初始数据

    private final String clazz; // 该变量用来存放参数类型（string,stringArray,int,fileArray）以计算系统的parameterJson为准

    private final ReturnType returnType; //返回需要的是 ParameterValueObject 对象还是 ParameterValueAndSizeObject 对象

    private final String strategy; //该数据的处理策略，针对不同的数据需要对应不同的处理方式

    private final Destination destination; //该数据用来存放到那个地方， metadata、parameterJson、固定值

    private final String key; // 如果是 metadata 一般指的是 dataNode 元素的is属性

    public OriginalData(Builder b) {
        this.original = b.o;
        this.clazz = b.clazz;
        this.returnType = b.returnType;
        this.strategy = b.strategy;
        this.destination = b.destination;
        this.key = b.key;
    }

}
