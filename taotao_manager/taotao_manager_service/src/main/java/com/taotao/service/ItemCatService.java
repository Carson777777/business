package com.taotao.service;

import com.taotao.common.vo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
