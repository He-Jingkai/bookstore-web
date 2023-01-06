package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.entity.DateT;
import com.hjk.hjkbookstore_backend.entity.Order;

public interface TempClass {
    DateT insertANewTimeStamp();
    Order saveAnOrder(Integer userid, DateT dateTToUse) ;
 }
