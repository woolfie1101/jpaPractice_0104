package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    /*
        JPA 스펙상 Entity나 Embeddable 타입은 기본 생성자가 필수 (삭제 하면 안됨)
        이렇게 사용하지 못하게 막음 (protected 생성자)
        Address address = new Address();
        address.setCity("Seoul");  // Setter가 없어서 불가능

        이렇게 사용하도록 강제 (public 생성자)
        Address address = new Address("Seoul", "강남대로", "12345");
     */
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
