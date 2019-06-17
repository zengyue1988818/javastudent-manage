package com.sm.service;

import com.sm.utils.ResultEntity;

public interface StuService {
    ResultEntity stuLogin(String account, String password);
}
