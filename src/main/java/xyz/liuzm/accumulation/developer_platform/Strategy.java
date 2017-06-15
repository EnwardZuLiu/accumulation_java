package xyz.liuzm.accumulation.developer_platform;

public interface Strategy {

    /**
     * 得到用户的需要的数据
     *
     * 1 原始数据
     * 2 返回值类型
     *
     * @param data
     * @return
     */
    ParameterValueObject handle(OriginalData data);

}
