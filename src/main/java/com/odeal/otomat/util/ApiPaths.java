package com.odeal.otomat.util;

public final class ApiPaths {

    private static final String BASE_PATH = "/";

    public static final class AccountCtrl {
        public static final String CTRL = BASE_PATH + "token";
    }

    public static final class UserCtrl {
        public static final String CTRL = BASE_PATH + "users";
    }

    public static final class OrderInfoCtrl {
        public static final String CTRL = BASE_PATH + "order";
    }

    public static final class ProductCtrl {
        public static final String CTRL = BASE_PATH + "product";
    }
}
