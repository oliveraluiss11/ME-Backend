package com.jayway.market_express.rider;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jayway.market_express.common.constant.PathConstant.RIDER_PATH;

@RestController
@RequestMapping(RIDER_PATH)
@RequiredArgsConstructor
public class RiderController {
    private final RiderService riderService;

    @PostMapping
    public ResponseEntity<CreateRiderResponse> registerRider(@RequestBody CreateRiderRequest request) {
        CreateRiderResponse response = riderService.registerRider(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
