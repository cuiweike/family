package com.family.springboot.money.service;

import java.util.List;
import java.util.Map;

public interface EnumFundsService {
    List<Map<String, Object>> getData();

    boolean saveEnum(String id, String parentId, String name);
}
