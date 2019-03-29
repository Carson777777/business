package com.taotao.service.impl;

import com.taotao.common.vo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //根据parent_id查询分类列表
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
        //转换成树形列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat:tbItemCats){
            EasyUITreeNode treeNode = new EasyUITreeNode();
            treeNode.setId(tbItemCat.getId()) ;
            treeNode.setText(tbItemCat.getName());
            treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            resultList.add(treeNode);
        }
        return resultList;
    }
}
