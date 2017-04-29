package sigep.service;

import sigep.beans.TagBean;
import sigep.data.dao.TagDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Tag;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TagService extends BaseService<Tag, TagBean> {
    @Inject
    private TagDao daoTag;




    @Override
    public TagBean findById(Long idEntity) {
        return null;
    }

    @Override
    public TagBean create(TagBean bean) throws SIGEPException {
        return null;
    }

    @Override
    public void update(TagBean bean) throws SIGEPException {

    }

    @Override
    public void bussinessValidation(Tag entity) throws SIGEPValidationException {

    }

    public List<TagBean> createByList(List<TagBean> tags){

        List<TagBean> tagsB = new ArrayList<TagBean>();

        for (int i = 0; i < tags.size() ; i++) {
            TagBean tag = tags.get(i);
            tagsB.add(daoTag.create(new Tag (tag)).getBean());
        }

        return tagsB;
    }


}
