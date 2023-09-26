package ma.zs.generator.project.ws.rest.provided.converter;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractConverter<T, VO> {

    public abstract T toItem(VO vo);

    public abstract VO toVo(T item);

    public List<T> toItem(List<VO> vos) {
        if (vos == null || vos.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<T> items = new ArrayList();
            for (VO vo : vos) {
                items.add(toItem(vo));
            }
            return items;
        }
    }

    public List<VO> toVo(List<T> items) {
        if (items == null || items.isEmpty()) {
            return null;
        } else {
            List<VO> vos = new ArrayList();
            for (T item : items) {
                vos.add(toVo(item));
            }
            return vos;
        }
    }
}
