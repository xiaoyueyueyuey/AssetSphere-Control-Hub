package com.ach.domain.asset.classification;

import com.ach.domain.common.Repository;

public interface AssetClassifinationRepository extends Repository<AssetClassificationModel> {
    Boolean checkRoomNameIsUnique(String roomName);

    Boolean checkRoomNameIsUnique(String roomName, Integer excludeId);

    Boolean checkACNameIsUnique(String acName);

    Boolean checkACNameIsUnique(String acName, Integer excludeId);

}
