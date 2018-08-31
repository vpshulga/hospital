package util;


import db.DataSource;
import entities.User;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import static db.DataSource.*;

public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, DataSource.DRIVER);
                settings.put(Environment.URL, DataSource.URL);
                settings.put(Environment.USER, DataSource.USER);
                settings.put(Environment.PASS, DataSource.PASSWORD);
                settings.put(Environment.HBM2DDL_AUTO, DataSource.HBM2DDL_AUTO);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, DataSource.CURRENT_SESSION_CONTEXT_CLASS);

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernate Registry Builder created");

                MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(User.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                logger.info("SessionFactory created.");
            } catch (Exception e) {
                logger.error("SessionFactory creation failed");
                logger.error(e.getMessage(), e);
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
}
