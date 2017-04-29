/*
package sigep.util;

import sigep.beans.BaseBean;
import sigep.model.EntityBase;

import java.util.ArrayList;
import java.util.List;

public class SigepMapper {
    public static List<EntityBase> mapBeanListToModelList(List<BaseBean> beanList){
        List<EntityBase> modelList = new ArrayList<EntityBase>(beanList.size());
        for(BaseBean bean : beanList)
        {
            modelList.add(new EntityBase(bean));
        }
        return modelList;
    }

    public static List<BaseBean> mapModelListToBeanList(List<EntityBase> modelList){
        List<BaseBean> beanList = new ArrayList<BaseBean>(modelList.size());
        for(EntityBase entity : modelList)
        {
            beanList.add(entity.getBean());
        }
        return beanList;
    }
}
*/
