package com.sm.service;

import com.sm.utils.ResultEntity;

public interface TeaService {
    ResultEntity teaLogin(String account, String password);
}
