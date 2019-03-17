package com.family.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zjc 递归树结构
 */
public class MenuTreeUtil {
    //public static Map<String, Object> mapArray = new LinkedHashMap<String, Object>();
    public List<Map<String, Object>> menuCommon;
    public List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    public List<Map<String, Object>> menuList(List<Map<String, Object>> menu) {
        this.menuCommon = menu;
        for (Map<String, Object> x : menu) {
            Map<String, Object> mapArr = new LinkedHashMap<String, Object>();
            if (null == x.get("PID")) {
                mapArr.put("id", x.get("ID").toString());
                mapArr.put("text", x.get("TEXT").toString());
                mapArr.put("pid", x.get("PID"));
                mapArr.put("value",x.get("VALUE"));
                //父节点展开
                mapArr.put("state", "closed");
                //mapArr.put("expanded", true);
                mapArr.put("children", menuChild(x.get("ID").toString()));
                list.add(mapArr);
            }
        }
        return list;
    }

    public List<Map<String, Object>> menuChild(String id) {
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> childrenLists = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> a : menuCommon) {
            Map<String, Object> childArray = new LinkedHashMap<String, Object>();
            //判断是否被选中
//            if (null != a.get("checked")) {
//                childArray.put("checked", true);
//            }
            //有父节点存在
            if (id.equals(a.get("PID"))) {
                childArray.put("id", a.get("ID").toString());
                childArray.put("text", a.get("TEXT").toString());
                childArray.put("pid", a.get("PID").toString());
                childrenLists = menuChild(a.get("ID").toString());
                childArray.put("children", childrenLists);
                childArray.put("value",a.get("VALUE"));
                //最后一层子节点状态更改
                if (childrenLists.size() == 0) {
                    childArray.put("state", "open");
                    //最后一层被选中时标记
                    if (null != a.get("checked")) {
                        childArray.put("checked", true);
                    }
                } else {
                    childArray.put("state", "closed");
                }
                lists.add(childArray);
            }
        }
        return lists;
    }
}
