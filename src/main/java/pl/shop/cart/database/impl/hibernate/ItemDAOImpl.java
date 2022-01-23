package pl.shop.cart.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.shop.cart.database.IItemDAO;
import pl.shop.cart.database.IItemDAO;
import pl.shop.cart.model.Item;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemDAOImpl implements IItemDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Item> getItems() {
        Session session = this.sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM pl.shop.cart.model.Item");
        List<Item> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Item> getItemById(int itemId) {
        Session session = this.sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM  pl.shop.cart.model.Item WHERE id = :id");
        query.setParameter("id", itemId);
        try {
            Item item = query.getSingleResult();
            session.close();
            return Optional.of(item);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateItem(Item item) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(item);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
