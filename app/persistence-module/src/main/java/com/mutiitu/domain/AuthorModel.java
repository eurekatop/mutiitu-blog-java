package com.mutiitu.domain;

import java.util.ArrayList;
import java.util.List;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;
import com.google.gson.annotations.Expose;
import com.mutiitu.domain.cms.CmsEntryModel;
import com.mutiitu.persistence.BaseModel;
import lombok.EqualsAndHashCode;

@Table(name = "Author")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class AuthorModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Expose
    @Column(name = "NAME")
    String name;

    @Expose
    @Column(name = "SURNAME")
    String surname;

    @Column(name = "PASSWORD")
    String password;

    @Expose
    @Transient
    List<BlogEntryModel> blogEntries = new ArrayList<>();

    @Expose
    @Transient
    List<CmsEntryModel> cmsEntries = new ArrayList<>();

    @OriginalStates
    AuthorModel originalStates;
}
