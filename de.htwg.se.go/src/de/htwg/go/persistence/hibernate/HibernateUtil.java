package de.htwg.go.persistence.hibernate;

/**
 * Created by michaelknoch on 24.06.15.
 */
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory SESSIONFACTORY;

    static {
        final AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure("/hibernate.cfg.xml");
        SESSIONFACTORY = cfg.buildSessionFactory();
    }

    private HibernateUtil() {

    }

    public static SessionFactory getInstance() {
        return SESSIONFACTORY;
    }

}