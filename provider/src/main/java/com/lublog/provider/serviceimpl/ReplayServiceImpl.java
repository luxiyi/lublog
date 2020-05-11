package com.lublog.provider.serviceimpl;

import com.lublog.provider.dao.ReplayMapper;
import com.lublog.pojo.Orderre;
import com.lublog.service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: java类作用描述ReplayServiceImpl
 * @Author: lxy
 * @time: 2020/4/16 0:45
 */
@Component
public class ReplayServiceImpl implements ReplayService {
   @Autowired
    private ReplayMapper replayMapper;

    @Override
    public List<Orderre> findAllOrderre(String luser) {
        return replayMapper.findAllOrderre(luser);
    }

    @Override
    public void addOrderre(String luser, String oname, int ocount, int bid) {
        replayMapper.addOrderre(luser, oname, ocount, bid);
    }

}
