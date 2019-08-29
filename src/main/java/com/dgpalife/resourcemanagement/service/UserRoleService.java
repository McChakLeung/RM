package com.dgpalife.resourcemanagement.service;

import java.util.List;

public interface UserRoleService {

    List<Integer> queryRoleByUserId(Long id);
}
