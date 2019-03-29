package com.taotao.service;

import com.taotao.common.vo.EasyUIDateGrideResult;
import com.taotao.common.vo.TaoTaoResult;

public interface ItemParamService {
    //查询规格参数并显示
    EasyUIDateGrideResult getItemParamList (int page,int rows);

    TaoTaoResult getItemParamByCid(Long cid);

    TaoTaoResult addItemParam(Long cid,String paramData);

    TaoTaoResult deleteParam(Long id);
}

