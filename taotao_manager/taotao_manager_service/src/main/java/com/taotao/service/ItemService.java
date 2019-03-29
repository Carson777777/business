package com.taotao.service;

import com.taotao.common.vo.EasyUIDateGrideResult;
import com.taotao.common.vo.TaoTaoResult;
import com.taotao.pojo.TbItem;

import java.util.List;

public interface ItemService {
    //根据id查询商品信息
    TbItem getItemById(Long itemId);

    /**
     * 查询所有的商品列表
     * @param page 多少页
     * @param rows 多少行
     * @return 分页的包装类
     */
    EasyUIDateGrideResult getItemList(int page,int rows);

    /**
     * 添加一个商品
     * @param item 商品基本信息
     * @param desc 商品的详细信息
     * @return 淘淘返回的包装类
     */
    TaoTaoResult createItem (TbItem item,String desc,String  itemparam);

//    String getItemParam(long itemid);
}
