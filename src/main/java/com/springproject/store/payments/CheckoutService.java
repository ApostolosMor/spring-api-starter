package com.springproject.store.payments;


import com.springproject.store.order.Order;
import com.springproject.store.cart.CartEmptyException;
import com.springproject.store.cart.CartNotFoundException;
import com.springproject.store.cart.CartRepository;
import com.springproject.store.order.OrderRepository;
import com.springproject.store.auth.AuthService;
import com.springproject.store.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;



    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {


        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);

        if (cart == null){
            throw new CartNotFoundException();
        }

        if (cart.isEmpty()){
            throw new CartEmptyException();

        }

        // You must fetch the user object from the repository
        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try{
            //Checkout session
            var session = paymentGateway.createCheckoutSession(order);

            cartService.clearCart(cart.getId());

            return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
        }catch(PaymentException ex){
            orderRepository.delete(order);
            throw ex;
        }
    }

    public void handleWebhookEvent(WebhookRequest request){
        paymentGateway.parseWebhookRequest(request)
                .ifPresent(paymentResult -> {
                    var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                    order.setStatus(paymentResult.getPaymentStatus());
                    orderRepository.save(order);
                });


    }
}
