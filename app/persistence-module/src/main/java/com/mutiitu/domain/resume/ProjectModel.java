package com.mutiitu.domain.resume;

import java.util.ArrayList;
import java.util.List;

import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;

import com.google.gson.annotations.Expose;
import com.mutiitu.persistence.BaseModel;

import lombok.EqualsAndHashCode;

@Table(name = "Project")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class ProjectModel extends BaseModel {
    @Expose
    String type;
    
    @Expose
    String jobTitle;
    
    @Expose
    String description;
    
    @Transient
    List<SkillModel> skills = new ArrayList<>();
}
