package com.mutiitu.domain;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Expose
    @Column(name = "TITLE")
    String title;

    @Expose
    @Column(name = "SLUG")
    String slug;

    @Expose
    @Column(name = "EXCERPT")
    String excerpt;

    @Expose
    @Column(name = "CONTENT")
    String content;

    @Expose
    @Column(name = "STATUS")
    String status;

    @Expose
    @Column(name = "CONTENT_TYPE")
    String contentType; // ej: "note", "article", "linklog"


    @Expose
    @Column(name = "TAGS")
    String tags; // ej: "link,tech,tools"


    @Expose
    @Column(name = "THUMBNAIL")
    String thumbnail;

    @Expose
    @Column(name = "AUTHOR_ID")
    Integer authorId;

    @Expose
    @Column(name = "CREATED_AT")
    Date createdAt;


    @Expose
    @Column(name = "UPDATED_AT")
    Date updatedAt;

    @Transient
    CmsEntryModel originalStates;
}

