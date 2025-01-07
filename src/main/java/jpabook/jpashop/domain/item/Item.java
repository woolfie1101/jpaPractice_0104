package jpabook.jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item { //추상클래스로 만든다. 구현체를 가지고 만들기 때문

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    // entity 안에 비즈니스 로직을 넣는게 좋다. 응집도가 좋다.
    // setter를 사용하는 것이 아니라 이런 식으로 사용하는 것이 객체 지향적이다.
    /**
     * stock 증가 (재고 증가)
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소 (재고 감소)
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
