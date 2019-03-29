package com.taotao.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.EasyUIDateGrideResult;
import com.taotao.common.vo.TaoTaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    TbItemParamMapper tbItemParamMapper;

    /**
     * 得到商品规格参数的列表
     * @param page 多少页
     * @param rows 一页多少行
     * @return
     */
    @Override
    public EasyUIDateGrideResult getItemParamList(int page, int rows) {
        //进行分页,输入页数和行数
        PageHelper.startPage(page,rows);

        //调用dao层的接口进行查询
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

//        //取出的paramDate需要解析一下才可应,只显示分类组
//        for(TbItemParam item:tbItemParams){
//            //json的结构
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                JsonNode node = objectMapper.readTree(item.getParamData());
//                String str = "";
//                for(int i = 0;i<node.size();i++){
//                    JsonNode childNode = node.get(i);
//                    str = str + childNode.get("group").toString();
//                }
//                System.out.println(str);
//                item.setParamData(str);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        PageInfo info = new PageInfo(tbItemParams);
        EasyUIDateGrideResult result = new EasyUIDateGrideResult();
        result.setTotal(info.getTotal());
        result.setRows(tbItemParams);
        return result;
    }

    /**
     * 确认商品规格模板是不是存在
     * @param cid 商品类目的id
     * @return
     */
    @Override
    public TaoTaoResult getItemParamByCid(Long cid) {
        //根据cid查询规格参模板
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria =  example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam>list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()!=0){
            TbItemParam tbItemParam = list.get(0);
            return TaoTaoResult.ok(tbItemParam);
        }
        return TaoTaoResult.ok();
    }

    /**
     * 添加商品模板
     * @param cid 商品模板的类别的主键
     * @param paramData 商品模板的主要信息
     * @return TaoTaoResult
     */
    @Override
    public TaoTaoResult addItemParam(Long cid, String paramData) {
        TbItemParam param = new TbItemParam();
        param.setItemCatId(cid);
        param.setParamData(paramData);
        Date date = new Date();
        param.setCreated(date);
        param.setUpdated(date);
        tbItemParamMapper.insertSelective(param);
        return TaoTaoResult.ok();
    }

    /**
     * 删除商品模板
     * @param id 模板的id
     * @return
     */
    @Override
    public TaoTaoResult deleteParam(Long id) {
        //调用mapper进行删除操作
        tbItemParamMapper.deleteByPrimaryKey(id);
        return TaoTaoResult.ok();
    }
}
