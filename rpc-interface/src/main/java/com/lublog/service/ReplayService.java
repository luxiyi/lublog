package com.lublog.service;

import com.lublog.po.Orderre;

import java.util.List;

/**
 * @Description: ReplayServicejava类作用描述
 * @Author: lxy
 * @time: 2020/4/6 22:00
 */
public interface ReplayService {
    public List<Orderre> findAllOrderre(String luser);
    public void addOrderre(String luser, String oname, int ocount, int bid);
}
