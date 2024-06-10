package com.ach.domain.asset.asset;

import com.ach.domain.common.Repository;

public interface AssetRepository extends Repository<AssetModel> {


    Boolean checkACNameIsUnique(String acName);

    Boolean checkACNameIsUnique(String acName, Integer excludeId);

}
