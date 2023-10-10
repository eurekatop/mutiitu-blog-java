package com.mutiitu.domain;

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

@Table(name = "BlogEntry")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class BlogEntryModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Expose
    @Column(name = "TITLE")
    String title;

    @Expose
    @Column(name = "SUBTITLE")
    String subtitle;

    @Expose
    @Column(name = "CONTENT")
    String content;

    @Column(name = "AUTHOR_ID")
    Integer authorId;

    @Expose
    @Transient
    AuthorModel author;

    @OriginalStates
    BlogEntryModel originalStates;
}
