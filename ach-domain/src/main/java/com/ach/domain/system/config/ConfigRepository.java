package com.ach.domain.system.config;


import com.ach.domain.common.Repository;

import java.util.Set;

public interface ConfigRepository extends Repository<ConfigModel> {
    Set<String> getConfigOptionSet(Integer configId);
}
