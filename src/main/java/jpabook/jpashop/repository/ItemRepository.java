package jpabook.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { //저장하기 전까지 id가 없다. 새로 생성한 객체
            em.persist(item); //저장
        } else {
            em.merge(item); //update 와 비슷
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { //여러개 찾는 것은 jpql을 사용한다.
        return em.createQuery("select i from Item i", Item.class)
            .getResultList();
    }
}
