package com.rdlab.marketplace.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class DaoImpl<T> extends GenericDaoImpl<T> implements GenericDao<T> {


}
