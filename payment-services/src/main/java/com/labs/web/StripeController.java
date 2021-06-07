package com.labs.web;


import com.google.gson.Gson;
import com.labs.entities.CheckoutPayment;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Path("/payment")
@Produces(MediaType.APPLICATION_JSON)
public class StripeController {
    // create a Gson object
    private static Gson gson = new Gson();

    /**
     * Payment with Stripe checkout page
     *
     * @throws StripeException
     */
    @POST
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException, StripeException {
        // We initilize stripe object with the api key
        init();
        // We create a  stripe session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();
        // create a stripe session
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we putted inside the response data you can get more info from the session object
        responseData.put("id", session.getId());
        // We can return only the sessionId as a String
        return gson.toJson(responseData);
    }
    private static void init() {
        Stripe.apiKey = "sk_test_51IozPQCfmk9JAqI9CSnciMfDNWLkZnZYsUCIk48Sbuv31XD2ivdk2EI54uZbHWaCgMw6FJwoA9FKKrTzGG5ScdKE009syJrUaL";
    }
}
