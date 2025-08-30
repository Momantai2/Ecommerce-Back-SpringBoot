package com.ecommerce.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.ItemStripeDTO;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;


@RestController
@RequestMapping("/api/pago")
@CrossOrigin(origins = "http://localhost:4200") // ajusta según tu entorno
public class PagoController {

  @PostMapping("/crear-sesion")
    public ResponseEntity<Map<String, String>> crearSesion(@RequestBody List<ItemStripeDTO> items) {
        try {
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

            for (ItemStripeDTO item : items) {
                lineItems.add(
                    SessionCreateParams.LineItem.builder()
                        .setQuantity((long) item.getCantidad())
                        .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("pen")
                                .setUnitAmount((long) (item.getPrecio() * 100)) // en centavos
                                .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName(item.getNombre())
                                        .build()
                                )
                                .build()
                        )
                        .build()
                );
            }

            SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:4200/success")
                .setCancelUrl("http://localhost:4200/cancel")
                .addAllLineItem(lineItems)
                .build();

            Session session = Session.create(params);

            Map<String, String> response = new HashMap<>();
            response.put("id", session.getId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
    e.printStackTrace();
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", e.getMessage());
    return ResponseEntity.status(500).body(errorResponse);
}

    }
}