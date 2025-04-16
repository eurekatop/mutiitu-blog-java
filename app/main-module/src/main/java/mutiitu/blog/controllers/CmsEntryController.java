package mutiitu.blog.controllers;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import mutiitu.blog.layouts.cmsEntries.CmsEntriesLayout;
import mutiitu.blog.layouts.cmsEntries.CmsEntriesLayoutDetail;
import mutiitu.blog.services.CMSEntryService;

import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HttpResponse;

@Controller
public class CmsEntryController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private final CMSEntryService cmsEntryService;

    @Inject
    CmsEntriesLayout layoutList;

    @Inject
    CmsEntriesLayoutDetail detailLayout;


    @Inject
    public CmsEntryController(CMSEntryService entryService) {
        this.cmsEntryService = entryService;
    }

    @Path(Value = "/entries/list")
    @Method(Value = "GET")
    public HttpResponse all() {
        return layoutList.render(cmsEntryService.GetAll(4000));

    }


    @Path(Value = "/entry")
    @Method(Value = "GET")
    public HttpResponse all(String slug) {
        return detailLayout.render(cmsEntryService.GetBydSlug(slug));

    }

}
