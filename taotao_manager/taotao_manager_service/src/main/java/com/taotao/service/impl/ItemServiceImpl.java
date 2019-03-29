package com.taotao.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.utils.IdUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.vo.EasyUIDateGrideResult;
import com.taotao.common.vo.TaoTaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Override
    public TbItem getItemById(Long itemId) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public EasyUIDateGrideResult getItemList(int page, int rows) {
        //显示第几页,一页显示多少行
        PageHelper.startPage(page,rows);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem>list = tbItemMapper.selectByExample(example);
        PageInfo<TbItem>pageInfo = new PageInfo<>(list);
        //返回一个处理结果
        EasyUIDateGrideResult result = new EasyUIDateGrideResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    /**
     * 1.接收Tbitem对象.String desc商品描述参数
     * 2.在Tbitem对象中补全属性,包括id,status,create,update
     * 3.调用Mapper的insert方法插入数据
     * 4.创建一个tbitemDesc对象,补全属性
     * 5.调用mapper方法插入到商品描述表
     * 6.返回Taotaoresult对象
     * 注意这里进行事务处理了,不能使用try-catch并且catch中不抛出异常,那样事务会提交
     */
    public TaoTaoResult createItem(TbItem item, String desc ,String itemparam) {
        //生成id
        long id = IdUtil.genItemId();
        item.setId(id);
        //补全商品状态,1-正常,2-下架,3-删除
        item.setStatus((byte)1);
        //创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //插入商品基本信息
        tbItemMapper.insert(item);

        //插入商品描述
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        itemDesc.setItemDesc(desc);
        tbItemDescMapper.insert(itemDesc);

        //添加商品规格参数处理
        TbItemParamItem paramItem = new TbItemParamItem();
        paramItem.setItemId(id);
        paramItem.setParamData(itemparam);
        paramItem.setCreated(date);
        paramItem.setUpdated(date);
        tbItemParamItemMapper.insertSelective(paramItem);

        //成功返回自定义响应
        return TaoTaoResult.ok();
    }

//    /**
//     * 根据商品id查询规格参数
//     * @param itemid 商品主键
//     * @return
//     */
//    @Override
//    public String getItemParam(long itemid) {
//        TbItemParamItemExample example = new TbItemParamItemExample();
//        TbItemParamItemExample.Criteria criteria = example.createCriteria();
//        criteria.andItemIdEqualTo(itemid);
//        //执行查询
//        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
//        if(list == null && list.size()==0){
//            return "";
//        }
//        TbItemParamItem itemParamItem = list.get(0);
//        String paramData = itemParamItem.getParamData();
//        List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
//        //遍历list生成html
//
//        return null;
//    }
}
