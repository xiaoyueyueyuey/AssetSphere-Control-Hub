package com.ach.domain.asset.lending;

import com.ach.domain.common.Repository;

public interface AssetLendingRepository extends Repository<AssetLendingModel> {


    Boolean checkACNameIsUnique(String acName);

    Boolean checkACNameIsUnique(String acName, Integer excludeId);

}
