package com.springproject.store.services;


import com.springproject.store.dtos.CartDto;
import com.springproject.store.dtos.CartItemDto;
import com.springproject.store.entities.Cart;
import com.springproject.store.exceptions.CartNotFoundException;
import com.springproject.store.exceptions.ProductNotFoundException;
import com.springproject.store.mappers.CartMapper;
import com.springproject.store.repositories.CartRepository;
import com.springproject.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private ProductRepository productRepository;


    public CartDto createCart(){
        var cart = new Cart();
        
        cartRepository.save(cart);

        return cartMapper.toDto(cart);
    }


    public CartItemDto addToCart(Long cartId, Long productId){



        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }

        var product  = productRepository.findById(productId).orElse(null);

        if(product == null){
           throw new ProductNotFoundException();
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public CartDto getCart(Long cartId){

        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }

        return cartMapper.toDto(cart);
    }

    public CartItemDto updateItem(Long cartId, Long productId, Integer quantity){

        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }

        var cartItem = cart.getItem(productId);

        if (cartItem == null){
            throw new ProductNotFoundException();
        }

        cartItem.setQuantity(quantity);
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public void removeItem(Long cartId, Long productId){

        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }
        cart.removeItem(productId);

        cartRepository.save(cart);
    }

    public void clearCart(Long cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null){
            throw new CartNotFoundException();
        }
        cart.clear();

        cartRepository.save(cart);

    }
}
