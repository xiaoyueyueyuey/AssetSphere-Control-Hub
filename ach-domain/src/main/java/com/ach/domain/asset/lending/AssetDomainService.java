package com.ach.domain.asset.lending;

public interface AssetDomainService {
    void returnAsset(Long assetId);

    void lentOutAsset(Long assetId);
}
