/**
 * JDK 9 的模块化使用，确保模块化可以正确使用
 */
module xyz.liuzm.accumulation.jdk9.module {
//    requires lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires rxjava;

    exports xyz.liuzm.accumulation.jdk9.module;
}