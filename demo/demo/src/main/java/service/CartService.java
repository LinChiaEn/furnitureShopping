package service;

import exception.NotFoundException;
import parameter.ProductQueryParameter;
import repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.CartRepository;

import entity.CartProduct;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    public CartProduct getProduct(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }
    
    public CartProduct findProduct(CartProduct request) {
    	return repository.findByName(request.getName());		
    }
    
    public CartProduct createProduct(CartProduct request) {
    	CartProduct product = new CartProduct();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImg(request.getImg());
        product.setSale(request.getSale());
        product.setCategory(request.getCategory());
        product.setQuantity(1);

        return repository.insert(product);
    }

    public CartProduct replaceNumOfProduct(String id, boolean increse) {
    	CartProduct product = getProduct(id);
    	if(increse) product.setQuantity(product.getQuantity()+1);
    	else product.setQuantity(product.getQuantity()-1);
    	return repository.save(product);
    }
    
    public CartProduct replaceProduct(String id, CartProduct request) {
    	CartProduct oldProduct = getProduct(id);

    	CartProduct product = new CartProduct();
        product.setId(oldProduct.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return repository.save(product);
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }
    
    public List<CartProduct> getProducts(ProductQueryParameter param) {
        String keyword = Optional.ofNullable(param.getKeyword()).orElse("");
        int priceFrom = Optional.ofNullable(param.getPriceFrom()).orElse(0);
        int priceTo = Optional.ofNullable(param.getPriceTo()).orElse(Integer.MAX_VALUE);
        
        Sort sort = genSortingStrategy(param.getOrderBy(), param.getSortRule());
        
        return repository.findByPriceBetweenAndNameLikeIgnoreCase(priceFrom, priceTo, keyword, sort);
    }
  

    private Sort genSortingStrategy(String orderBy, String sortRule) {
        Sort sort = Sort.unsorted();
        if (Objects.nonNull(orderBy) && Objects.nonNull(sortRule)) {
            Sort.Direction direction = Sort.Direction.fromString(sortRule);
            sort = Sort.by(direction, orderBy);
        }

        return sort;
    }
}
