package dao.impl;

import dao.GenericDao;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

public abstract class GenericDaoImpl<T extends Serializable> {
    private Class<T> clazz;

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public GenericDaoImpl(Class<T> clazz){
        this.clazz = clazz;
    }

    public T findOne(Long id) {
        return getCurrentSession().get(clazz, id);
    }

    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(Long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
