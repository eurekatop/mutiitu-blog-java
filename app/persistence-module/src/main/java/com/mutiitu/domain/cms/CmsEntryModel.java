package com.mutiitu.domain.cms;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;
import lombok.EqualsAndHashCode;

import com.google.gson.annotations.Expose;
import com.mutiitu.persistence.BaseModel;

@Table(name = "CmsEntry")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class CmsEntryModel extends BaseModel {
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Expose
    @Column(name = "TITLE")
    String title;

    @Expose
    @Column(name = "CONTENT")
    String content;

    @Expose
    @Column(name = "STATUS")
    String status;

    @Expose
    @Column(name = "AUTHOR_ID")
    Integer authorId;

    @Expose
    @Column(name = "DATE")
    String date;

    @Expose
    @Column(name = "EXCERPT")
    String excerpt;

    @Expose
    @Column(name = "THUMBNAIL")
    String thumbnail;

    @Expose
    @Column(name = "SLUG")
    String slug;

    @Transient
    @OriginalStates
    CmsEntryModel originalStates;
}
