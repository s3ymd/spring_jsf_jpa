package jp.co._3sss.service;

import org.springframework.data.repository.CrudRepository;

import jp.co._3sss.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
