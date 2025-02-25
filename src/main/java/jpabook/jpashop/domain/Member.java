package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue //시퀀스
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장타입
    private Address address;

    @OneToMany(mappedBy = "member") //order table에 있는 member 필드에 의해서 맵핑 됨. 읽기 전용
    private List<Order> orders = new ArrayList<>(); //이 컬렉션을 함부로 바꾸지마라. 하이버네이트가 엔티티를 영속화할 때 내장 컬렉션으로 변경된다.
}