package com.taotao.controller;

import com.taotao.common.vo.EasyUIDateGrideResult;
import com.taotao.common.vo.TaoTaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    /**
     * 分页展示所有窗口
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDateGrideResult getItemList(Integer page,Integer rows){
        EasyUIDateGrideResult result = itemService.getItemList(page,rows);
        return result;
    }

    /**
     * 添加商品的controller
     * @param item 利用给一个pojo类接收form表单传递的参数 类中的名字要和表单中一致
     * @param desc item中没有desc,也就是商品内容 这个也要一致
     * @param itemParams 这个名字和表单中提交的name一样 代表传回来的额json
     * @return 返回一个 自定义响应格式
     */
    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaoTaoResult createItem(TbItem item,String desc ,String itemParams){
        TaoTaoResult result = itemService.createItem(item,desc,itemParams);
        return result;
    }


}
