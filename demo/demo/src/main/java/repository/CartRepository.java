package repository;

import entity.CartProduct;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends MongoRepository<CartProduct, String> {
	//@Query("{'$and': [{'price': {'$gte': ?0, '$lte': ?1}}, {'name': {'$regex': ?2, '$options': 'i'}}]}")
    List<CartProduct> findByPriceBetweenAndNameLikeIgnoreCase(int priceFrom, int priceTo, String name, Sort sort);

    //@Query("{'price': {'$gte': ?0, '$lte': ?1}}")
    List<CartProduct> findByPriceBetween(int from, int to);

    //@Query("{'name': {'$regex': ?0, '$options': 'i'}}")
    List<CartProduct> findByNameLikeIgnoreCase(String name);
    
    CartProduct findByName(String name);

    //@Query(value = "{'_id': {'$in': ?0}}", count = true)
    int countByIdIn(List<String> ids);

    //@Query(value = "{'_id': {'$in': ?0}}", exists = true)
    boolean existsByIdIn(List<String> ids);

    //@Query(delete = true)
    void deleteByIdIn(List<String> ids);

    //@Query(sort = "{'name': 1, 'price': -1}")
    List<CartProduct> findByIdInOrderByNameAscPriceDesc(List<String> ids);
}
