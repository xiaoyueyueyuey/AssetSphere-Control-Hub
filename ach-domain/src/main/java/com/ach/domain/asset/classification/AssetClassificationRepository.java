package com.ach.domain.asset.classification;

import com.ach.domain.Repository;

public interface AssetClassificationRepository extends Repository<AssetClassificationModel> {


    Boolean checkACNameIsUnique(String acName);

    Boolean checkACNameIsUnique(String acName, Integer excludeId);

}
