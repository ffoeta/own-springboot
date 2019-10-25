package com.spring.utils;

import com.spring.model.Brand;

import java.util.UUID;

public interface Constants {
     static final UUID BRAND_NONE = UUID.fromString("37d09b2e-f67e-11e9-802a-5aa538984bd8");
     static final UUID CATEGORY_NONE = UUID.fromString("37d09fac-f67e-11e9-802a-5aa538984bd8");

     static final String ADMIN_ENDPOINT_V1 = "/api/v1/admin/**";
     static final String LOGIN_ENDPOINT_V1 = "/api/v1/auth/**";
     static final String USER_ENDPOINT_V1 = "/api/v1/user/**";
     static final String REST_ENDPOINT_V1 = "/api/v1/rest/**";

     static final String AUTH_ENDPOINT_V2 = "/api/v2/auth/**";
     static final String ADMIN_ENDPOINT_V2 = "/api/v2/authorized/head/**";
     static final String USER_ENDPOINT_V2 = "/api/v2/authorized/user/**";
     static final String API_ENDPOINT_V2 = "/api/v2/rest/**";
}
