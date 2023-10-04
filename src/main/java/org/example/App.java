package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a simple hibernate application that demonstrates
 * how to work with a Many-To-Many relationship
 */
public class App {
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
            .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // try with resources
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // Get objects from the DB
            Movie movie = session.get(Movie.class, 1);
            System.out.println(movie.getActors());

            System.out.println("~~~~~~~~~~~~");
            Actor actor = session.get(Actor.class, 2);
            System.out.println(actor.getMovies());

            session.getTransaction().commit();
        }
    }
}
