package com.mutiitu.domain.resume;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;

import com.mutiitu.persistence.BaseModel;

import lombok.EqualsAndHashCode;

@Table(name = "WorkExperience")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class WorkExperienceModel extends BaseModel {
    Date startDate;
    Date endDate;
    
    @Transient
    EmployerModel employer;

    @Transient
    List<ProjectModel> projects = new ArrayList<>();
}
