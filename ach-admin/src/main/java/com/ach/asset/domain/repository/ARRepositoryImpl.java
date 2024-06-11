package com.ach.asset.domain.repository;

import com.ach.domain.asset.receipt.ARModel;
import com.ach.domain.asset.receipt.ARRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ARRepositoryImpl implements ARRepository {
    @Override
    public ARModel findByIdOrError(Long id) {
        return null;
    }

    @Override
    public Long save(ARModel model) {
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return null;
    }
}
