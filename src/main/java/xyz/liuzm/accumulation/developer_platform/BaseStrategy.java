package xyz.liuzm.accumulation.developer_platform;

/**
 * Created by liuzuming on 1/16/17.
 */
public class BaseStrategy implements Strategy {

    @Override
    public ParameterValueObject handle(OriginalData o) {
        String s = (String) o.getOriginal();
        ParameterValueObject p = new ParameterValueObject();
        p.setClazz(o.getClazz());
        p.setValue(s);
        return p;
    }
}
