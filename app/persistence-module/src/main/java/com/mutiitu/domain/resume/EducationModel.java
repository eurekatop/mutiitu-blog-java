package com.mutiitu.domain.resume;

import java.util.Date;

import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

import com.mutiitu.persistence.BaseModel;

public class EducationModel extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Date startDate;
    Date endDate;

    String academicDegree;
    String nameOfInstitution;
    String address;
}
