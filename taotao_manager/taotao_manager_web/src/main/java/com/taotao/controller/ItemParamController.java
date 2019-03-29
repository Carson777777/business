package com.taotao.controller;

import com.taotao.common.vo.EasyUIDateGrideResult;
import com.taotao.common.vo.TaoTaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品模板参数管理
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    ItemParamService itemParamService;
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDateGrideResult getItemParamList(Integer page,Integer rows){
        EasyUIDateGrideResult result = itemParamService.getItemParamList(page,rows);
        return result;
    }

    /**
     * 判断模板是否存在 和 展示规格参数表
     * @param cid 商品类目的主键
     * @return 淘淘result
     *
     */
    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaoTaoResult getItemParamByCid(@PathVariable Long cid){
        TaoTaoResult result = itemParamService.getItemParamByCid(cid);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaoTaoResult addItemParam(@PathVariable Long cid, @RequestParam String paramData){
        TaoTaoResult result = itemParamService.addItemParam(cid,paramData);
        return result;
    }

    /**
     * 删除商品规格参数模板
     * @param ids 规格参数模板的id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public TaoTaoResult deleteParam(Long ids){
        TaoTaoResult result = itemParamService.deleteParam(ids);
        return result;
    }
}
