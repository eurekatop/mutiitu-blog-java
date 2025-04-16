package mutiitu.blog.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.dao.CMSEntryDao;
import com.mutiitu.domain.CmsEntryModel;
import com.mutiitu.domain.CmsEntryModel_;

//TODO: this is a repository
public class CMSEntryService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    CMSEntryDao CmsEntryDao;

    public CmsEntryModel GetBydId(int id) {
        return CmsEntryDao.getById(id);
    }

    public CmsEntryModel GetBydSlug(String slug) {
        return CmsEntryDao.getBySlug(slug);
    }

    public List<CmsEntryModel> GetAll(int count) {
        return CmsEntryDao.getCmsEntries(4000);
    }

    public void Add(CmsEntryModel model) {
        CmsEntryDao.insert(model);
    }

    public List<String> SelectAllSlugs() {
        var entity = new CmsEntryModel_();

        List<String> fieldsToSelect = Arrays.asList(
                entity.slug.getName());

        return CmsEntryDao.getPartialDynamic(Integer.MAX_VALUE, fieldsToSelect, String.class);
    }


}
